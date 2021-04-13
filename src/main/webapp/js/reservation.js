document.addEventListener("DOMContentLoaded",function(){
	let loginEmail = document.querySelector("#loginEmail").value;
	if(loginEmail.length>0){
		emailByreservationInfo(loginEmail)
	}
	detail();
	reservationEnd();
})

let AdultCount = 0;
let AdultPrice = 0;
let AdultDiscount = 0;
let YoungCount = 0;
let YoungPrice = 0;
let YoungDiscount = 0;
let SetCount = 0;
let SetPrice = 0;
let BabyCount = 0;
let BabyPrice = 0;
let BabyDiscount = 0;
let index = 0;

function emailByreservationInfo(email){
	let xhr = new XMLHttpRequest;
	xhr.addEventListener("load",function(){
		if(xhr.readyState === 4){
			if(xhr.status === 200){
				let reservationInfo = JSON.parse(xhr.responseText);
				if(reservationInfo.name != undefined && reservationInfo.tel != undefined){
					document.querySelector("#name").value = reservationInfo.name
					document.querySelector("#name").setAttribute("readonly",true)
					document.querySelector("#tel").value = reservationInfo.tel
					document.querySelector("#tel").setAttribute("readonly",true)
					document.querySelector("#email").value = reservationInfo.email
					document.querySelector("#email").setAttribute("readonly",true)
				}else{
					document.querySelector("#email").value = reservationInfo.email
					document.querySelector("#email").setAttribute("readonly",true)
				}
			}
		}
	})
	xhr.open("GET","eamilByReservationInfo?email="+email)
	xhr.send()
}

function detail(){
	let displayInfoId = document.querySelector("#displayInfoId").value;
	let xhr = new XMLHttpRequest;
	xhr.addEventListener("load",function(){
		if(xhr.readyState === 4){
			if(xhr.status === 200){
				let detail = JSON.parse(xhr.responseText);
				reservation(detail);
			}
		}
	})
	xhr.open("GET","products/"+displayInfoId);
	xhr.send();
}

function format(totalPrice){
	return totalPrice.toLocaleString("ko-KR");
}

function tikketPriceReset(detail){
	let tikket = document.querySelectorAll(".ticket_body .qty")
	let size = detail.productPrices.length;
	
	for(let i=0;i<size;i++){
		if(detail.productPrices[i].priceTypeName === "A"){
			AdultPrice = detail.productPrices[i].price
			AdultDiscount = detail.productPrices[i].discountRate
			tikket[0].setAttribute("productPriceId",detail.productPrices[i].productPriceId);
			SetPrice = AdultPrice * 2
			tikket[2].setAttribute("productPriceId",detail.productPrices[i].productPriceId);
		}else if(detail.productPrices[i].priceTypeName === "Y"){
			YoungPrice = detail.productPrices[i].price
			YoungDiscount = detail.productPrices[i].discountRate
			tikket[3].setAttribute("productPriceId",detail.productPrices[i].productPriceId);
		}else if(detail.productPrices[i].priceTypeName === "B"){
			BabyPrice = detail.productPrices[i].price
			BabyDiscount = detail.productPrices[i].discountRate
			tikket[1].setAttribute("productPriceId",detail.productPrices[i].productPriceId);
		}
	}
}

function editTikketCount(){
	let tikket = document.querySelectorAll(".ticket_body .qty")
	if(AdultPrice!=0){
		let adul = tikket[0].querySelector(".count_control_input");
			adul.setAttribute("value",AdultCount);
			tikket[0].querySelector(".total_price").textContent = format(AdultCount * AdultPrice);
			tikket[0].querySelector(".price").textContent = format(AdultPrice);
			tikket[0].querySelector(".product_dsc").textContent = format(AdultPrice - (AdultDiscount/100 * AdultPrice))+"원 ("+AdultDiscount+"% 할인가)"
	}else{
		tikket[0].style.display = "none";
	}
	if(BabyPrice!=0){
		let baby = tikket[1].querySelector(".count_control_input");
			baby.setAttribute("value",BabyCount);
			tikket[1].querySelector(".total_price").textContent = format(BabyCount * BabyPrice);
			tikket[1].querySelector(".price").textContent = format(BabyPrice);
			tikket[1].querySelector(".product_dsc").textContent = format(BabyPrice - (BabyDiscount/100 * BabyPrice))+"원 ("+BabyDiscount+"% 할인가)"
	}else{
		tikket[1].style.display = "none";
	}
	if(SetPrice!=0){
		let set = tikket[2].querySelector(".count_control_input");
			set.setAttribute("value",SetCount);
			tikket[2].querySelector(".total_price").textContent = format(SetCount * SetPrice);
			tikket[2].querySelector(".price").textContent = format(SetPrice)
	}else{
		tikket[2].style.display = "none";
	}
	if(YoungPrice!=0){
		let young = tikket[3].querySelector(".count_control_input");
			young.setAttribute("value",YoungCount);
			tikket[3].querySelector(".total_price").textContent = format(YoungCount * YoungPrice);
			tikket[3].querySelector(".price").textContent = format(YoungPrice);
			tikket[3].querySelector(".product_dsc").textContent = format(YoungPrice - (YoungDiscount/100 * YoungPrice))+"원 ("+YoungDiscount+"% 할인가)"
	}else{
		tikket[3].style.display = "none";
	}
}

function tikketButtonEvent(){
	let tikket = document.querySelectorAll(".ticket_body .qty")
	let len = tikket.length;
	for(let i=0;i<len;i++){
		if(tikket[i].querySelector(".count_control_input").value == 0){
			tikket[i].querySelector(".btn_plus_minus").classList.add("disabled");
			tikket[i].querySelector(".individual_price").classList.remove("on_color");
			tikket[i].querySelector(".count_control_input").classList.add("disabled");
		}else{
			tikket[i].querySelector(".btn_plus_minus").classList.remove("disabled");
			tikket[i].querySelector(".individual_price").classList.add("on_color");
			tikket[i].querySelector(".count_control_input").classList.remove("disabled");
		}
	}
}

function minusCountEvent(){
	let tikket = document.querySelectorAll(".ticket_body .qty")
	let len = tikket.length;
	for(let i=0;i<len;i++){
		tikket[i].querySelector(".ico_minus3").addEventListener("click",function(evt){
			index = i;
			switch(index){
				case 0 : if(AdultCount>0)AdultCount--; break;
				case 1 : if(BabyCount>0)BabyCount--; break;
				case 2 : if(SetCount>0)SetCount--; break;
				case 3 : if(YoungCount>0)YoungCount--; break;
			}
		editTikketCount();
		tikketButtonEvent()
		document.querySelector("#totalCount").innerText = (AdultCount+BabyCount+SetCount+YoungCount)
			//티켓 예매 수량이 1개이상이라면 disable 클래스 제거
			if((AdultCount+BabyCount+SetCount+YoungCount) > 0 ){
				
				document.querySelector(".bk_btn_wrap").classList.remove("disable")
			}else{
				document.querySelector(".bk_btn_wrap").classList.add("disable")
			}
			//reservationEnd() 이부분때문에 중복처리되는거같음
		})
	}
}

function plusCountEvent(){
	let tikket = document.querySelectorAll(".ticket_body .qty")
	let len = tikket.length;
	for(let i=0;i<len;i++){
		tikket[i].querySelector(".ico_plus3").addEventListener("click",function(evt){
			index = i;
			switch(index){
				case 0 : AdultCount++; break;
				case 1 : BabyCount++; break;
				case 2 : SetCount++; break;
				case 3 : YoungCount++; break;
			}
		editTikketCount();
		tikketButtonEvent()
		document.querySelector("#totalCount").innerText = (AdultCount+BabyCount+SetCount+YoungCount)
			//티켓 예매 수량이 1개이상이라면 disable 클래스 제거
			if((AdultCount+BabyCount+SetCount+YoungCount) > 0 ){
				document.querySelector(".bk_btn_wrap").classList.remove("disable")
			}else{
				document.querySelector(".bk_btn_wrap").classList.add("disable")
			}
		})
	}
}

function reservation(detail){
	console.log(detail);
	
	//티켓 가격 초기화
	tikketPriceReset(detail)
	
	//티켓 수량 기본값 설정
	editTikketCount()
	
	//티켓 버튼 비활성화
	tikketButtonEvent()
	
	//수량 빼기 이벤트
	minusCountEvent()
	
	//수량 더하기 이벤트
	plusCountEvent()
}

function noticeViewEvent(){
	let agreement = document.querySelectorAll(".section_booking_agreement .agreement .btn_text");
	for(let i=0;i<agreement.length;i++){
		agreement[i].addEventListener("click",function(evt){
			evt.target.closest(".agreement").classList.add("open");
		})
	}
}

function reservationEnd(){
		document.querySelector(".bk_btn").addEventListener("click",function(){			
			let check = true;
			let displayInfoId = document.querySelector("#displayInfoId").value;
			let tikket=document.querySelectorAll(".ticket_body .qty");
			
			let reservationName = document.querySelector("#name").value
			let regExp = /^\d{3}-\d{3,4}-\d{4}$/;
			let regExpEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
			let reservationTelephone = document.querySelector("#tel").value
			let reservationEmail = document.querySelector("#email").value;
			
			//reservationName.isEmpty || reservationName === null 처리가 안됌.
			if(reservationName.length<1){
				alert("예매자를 입력하세요");
				document.querySelector("#name").focus();
				check = false;
			}
			else if(!regExp.test(reservationTelephone)){
				alert("연락처를 올바르게 입력하세요");
				document.querySelector("#tel").focus();
				check = false;
			}else if(!regExpEmail.test(reservationEmail)){
				alert("이메일을 올바르게 입력하세요");
				document.querySelector("#email").focus();
				check = false;
			}
			
			//예매일 생성(예매일생성 규칙 : 예매일 기준  오늘포함해서 1-5일 랜덤값으로 서버에서 생성해서 내려줌)
			let today = new Date();
			let month = today.getMonth()+1<10 ? "0"+(today.getMonth()+1) : today.getMonth()+1;
			let reservationYearMonthDay = today.getFullYear()+"-"+month+"-"+(today.getDate()+Math.floor(Math.random()*6));
			
			//티켓정보 받아서 처리할 부분
			let price=[]
			for(let i=0;i<tikket.length;i++){
				if(tikket[i].querySelector(".count_control_input").value!=0){
					price.push({
						"count":tikket[i].querySelector(".count_control_input").value,
						"productPriceId":tikket[i].getAttribute("productpriceid"),
						"reservationInfoId": 0,
			      		"reservationInfoPriceId": 0
					})
				}
			}
			let reservationParam
			if(check){
				reservationParam = {
				"displayInfoId":displayInfoId,
				"prices" : price,
				  "productId": displayInfoId,
				  "reservationEmail": reservationEmail,
				  "reservationName": reservationName,
				  "reservationTelephone": reservationTelephone,
				  "reservationYearMonthDay": reservationYearMonthDay
				}
			}
			
			if(check){ //이 부분 post로 처리하기.
				let xhr = new XMLHttpRequest;
				xhr.addEventListener("load",function(){
					if(xhr.readyState === 4){
						if(xhr.status === 200){
							let email = JSON.parse(xhr.responseText)
							alert("예매가 완료되었습니다.")
							location.href = "http://localhost:14816/api/myreservation?resrv_email="+email.email
						}
					}
				})
				xhr.open("POST","reservations",true)
				xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8")
				xhr.send(JSON.stringify(reservationParam));
			}
			
		})
	
	//약관 더보기
	noticeViewEvent()
}