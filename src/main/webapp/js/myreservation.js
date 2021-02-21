document.addEventListener("DOMContentLoaded",function(){
	reservations();
	
})

function reservations(){
	let email = document.getElementById("email").value;
	console.log(email);
	let xhr = new XMLHttpRequest
	xhr.addEventListener("load",function(){
		if(xhr.readyState === 4){
			if(xhr.status === 200){
				let reservations = JSON.parse(xhr.responseText)
				
				myReservationData(reservations)
				
			}//if status End------
		}//if ready state End--------
	})// load EventListener End------
	xhr.open("GET","reservations?email="+email);
	xhr.send();
}

//요일 포맷
function dateFormat(date){
	let reservationDate = new Date(date);
	let year =  reservationDate.getFullYear();
	let month = (reservationDate.getMonth()+1)<10?"0"+(reservationDate.getMonth()+1):(reservationDate.getMonth()+1);
	let day = reservationDate.getDate();
	let week = ['일','월','화','수','목','금','토'];
	let reservationWeek = week[new Date(year+"-"+month+"-"+day).getDay()];
	return year+"."+month+"."+day+"("+reservationWeek+")";
}
				
//금액 포맷
function priceFormat(price){
	return price.toLocaleString("ko-KR");
}

function myReservationData(reservations){
		console.log(reservations)
		
		let myListAll = document.querySelector(".wrap_mylist");
				let size = reservations.size
				if(size<1){
					myListAll.querySelector(".confirmed").style.display = "none";
					myListAll.querySelector(".used").style.display = "none";
					myListAll.querySelector(".cancel").style.display = "none";
					//document.querySelector(".wrap_mylist li").style.display = "block"; //예약 신청중
				}else{
					document.querySelector(".err").style.display = "none";
				}
				
				//카테고리 버튼 클릭 시 이벤트 처리
				document.querySelector(".summary_board").addEventListener("click",function(evt){
					document.querySelector(".summary_board .on").classList.remove("on");
					let li = evt.target.closest("li");
					li.querySelector(".link_summary_board").classList.add("on");
					
					let liAll = document.querySelectorAll(".summary_board li");
					
					if(liAll[0] === li){ //전체
						myListAll.querySelector(".confirmed").style.display = "block";
						myListAll.querySelector(".used").style.display = "block";
						myListAll.querySelector(".cancel").style.display = "block";
						//myListAll.querySelector("li").style.display = "none"; 
						
						//예약 리스트 없을 때 처리
						if(document.querySelector(".summary_board li:nth-of-type(1)").querySelector(".figure").textContent == 0){
							document.querySelector(".err").style.display = "block";
						}else{
							document.querySelector(".err").style.display = "none";
						}
					}else if(liAll[1] === li){ //이용예정 
						myListAll.querySelector(".confirmed").style.display = "block";
						myListAll.querySelector(".used").style.display = "none";
						myListAll.querySelector(".cancel").style.display = "none";
						//myListAll.querySelector("li").style.display = "none";
						
						//예약 리스트 없을 때 처리
						if(document.querySelector(".summary_board li:nth-of-type(2)").querySelector(".figure").textContent == 0){
							document.querySelector(".err").style.display = "block";
						}else{
							document.querySelector(".err").style.display = "none";
						}
					}else if(liAll[2] === li){ //이용완료
						myListAll.querySelector(".confirmed").style.display = "none";
						myListAll.querySelector(".used").style.display = "block";
						myListAll.querySelector(".cancel").style.display = "none";
						//myListAll.querySelector("li").style.display = "none";
						
						//예약 리스트 없을 때 처리
						if(document.querySelector(".summary_board li:nth-of-type(3)").querySelector(".figure").textContent == 0){
							document.querySelector(".err").style.display = "block";
						}else{
							document.querySelector(".err").style.display = "none";
						}
					}else if(liAll[3] === li){ //reservations.cancelYn
						myListAll.querySelector(".confirmed").style.display = "none";
						myListAll.querySelector(".used").style.display = "none";
						myListAll.querySelector(".cancel").style.display = "block";
						//myListAll.querySelector("li").style.display = "none";
						
						//예약 리스트 없을 때 처리
						if(document.querySelector(".summary_board li:nth-of-type(4)").querySelector(".figure").textContent == 0){
							document.querySelector(".err").style.display = "block";
						}else{
							document.querySelector(".err").style.display = "none";
						}
					}
				})// 카테고리 버튼 이벤트 End ------
		
		let confirmedSample = document.querySelector("#confirmedSample").innerHTML;
		let confirmedDiv = document.createElement("div");
		let usedSample = document.querySelector("#usedSample").innerHTML;
		let usedDiv = document.createElement("div");
		let cancelSample = document.querySelector("#cancelSample").innerHTML;
		let cancelDiv = document.createElement("div");
				
		//카테고리 수량 초기화
		let confirmedCount = 0;
		let usedCount = 0;
		let cancelCount = 0;
					
			for(let i=0;i<reservations.reservations.length;i++){
				let str = "";
				let today = new Date();
				let reservationDate = new Date(reservations.reservations[i].reservationDate);
					if(today.getTime()-reservationDate.getTime()>0 && !reservations.reservations[i].cancelYn){
						str = usedSample.replace("{reservationInfoId}",reservations.reservations[i].reservationInfoId)
									  .replace("{productDescription}",reservations.reservations[i].displayInfo.productDescription)
									  .replace("{reservationDate}",dateFormat(reservations.reservations[i].reservationDate))
									  .replace("{placeStreet}",reservations.reservations[i].displayInfo.placeStreet)
									  .replace("{totalPrice}",priceFormat(reservations.reservations[i].totalPrice))
									  .replace("{reserv}",reservations.reservations[i].reservationInfoId)
									  .replace("{product}",reservations.reservations[i].productId)
							usedDiv.innerHTML += str;
							
							usedCount++;
							
					}else if(today.getTime()-reservationDate.getTime()<0 && !reservations.reservations[i].cancelYn){
						str = confirmedSample.replace("{reservationInfoId}",reservations.reservations[i].reservationInfoId)
									  .replace("{productDescription}",reservations.reservations[i].displayInfo.productDescription)
									  .replace("{reservationDate}",dateFormat(reservations.reservations[i].reservationDate))
									  .replace("{placeStreet}",reservations.reservations[i].displayInfo.placeStreet)
									  .replace("{totalPrice}",priceFormat(reservations.reservations[i].totalPrice));
							confirmedDiv.innerHTML += str;
							
							confirmedCount++;
							
					}else if(reservations.reservations[i].cancelYn){
						str = cancelSample.replace("{reservationInfoId}",reservations.reservations[i].reservationInfoId)
									  .replace("{productDescription}",reservations.reservations[i].displayInfo.productDescription)
									  .replace("{reservationDate}",dateFormat(reservations.reservations[i].reservationDate))
									  .replace("{placeStreet}",reservations.reservations[i].displayInfo.placeStreet)
									  .replace("{totalPrice}",priceFormat(reservations.reservations[i].totalPrice));
							cancelDiv.innerHTML += str;
							
							cancelCount++;
						}
					}
					document.querySelector(".confirmed").appendChild(confirmedDiv);
					document.querySelector(".used").appendChild(usedDiv);
					document.querySelector(".cancel").appendChild(cancelDiv);
					
					//카테고리 수량 체크
					document.querySelector(".summary_board li:nth-of-type(1)").querySelector(".figure").textContent = reservations.size;
					document.querySelector(".summary_board li:nth-of-type(2)").querySelector(".figure").textContent = confirmedCount
					document.querySelector(".summary_board li:nth-of-type(3)").querySelector(".figure").textContent = usedCount
					document.querySelector(".summary_board li:nth-of-type(4)").querySelector(".figure").textContent = cancelCount
					
					//취소 버튼 이벤트 처리
					cancelEvent()
					
}//myReservationDate End-----

function cancelEvent(){
	let confirmedList = document.querySelector(".confirmed").lastElementChild.querySelectorAll("article")
					for(let i=0;i<confirmedList.length;i++){
						confirmedList[i].querySelector(".btn").addEventListener("click",function(evt){
							// reservationInfoId
							let reservationInfoId = evt.target.closest(".card_detail").querySelector(".booking_number").textContent.split(".")[1] 
							if(confirm("정말 삭제하시겠습니까?") === true){
								let xhr  = new XMLHttpRequest
								xhr.addEventListener("load",function(){
									if(xhr.readyState === 4){
										if(xhr.status === 200){
											reservations();
											
											//이부분 수동으로 위치 옮겨줬는데 reservations()부르면 ajax실행되서 자동으로 처리되고싶음.
											let selectList = evt.target.closest(".card_item")
											selectList.querySelector(".card_detail").removeChild(selectList.querySelector(".card_detail").lastElementChild)
											selectList.removeChild(selectList.lastElementChild)
											document.querySelector(".cancel").lastElementChild.appendChild(selectList)
										}
									}
								})
								xhr.open("PUT","reservations/"+reservationInfoId)
								xhr.send()
						}
					})
				}// 취소 이벤트 처리 End-----
}