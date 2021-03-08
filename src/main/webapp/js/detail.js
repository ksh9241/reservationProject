let count = 1
document.addEventListener("DOMContentLoaded",function(){
	let displayInfoId = document.querySelector("#detailId").getAttribute("value");
	detailById(displayInfoId);
})

function updateCarouselImage(detail){
	let imgUl = document.querySelector(".visual_img")
	let carouselTemplate = document.querySelector("#carouselTemplate").innerHTML
	let carouselHandlebarTemplate = Handlebars.compile(carouselTemplate)
	let resultHTML = carouselHandlebarTemplate(detail)
	imgUl.innerHTML += resultHTML
	
	//이미지가 1개 이상일 경우(carousel처리하기 위한 clone생성)
	if(imgUl.querySelectorAll("li").length>1){
		let firstImg = document.querySelector(".visual_img").firstElementChild
		let lastImg = document.querySelector(".visual_img").lastElementChild
		let firstImgClone = firstImg.cloneNode(true)
		let lastImgClone = lastImg.cloneNode(true)
		firstImgClone.id = "firstClone"
		lastImgClone.id = "lastClone"
		imgUl.insertAdjacentElement("afterbegin",lastImgClone)
		imgUl.insertAdjacentElement("beforeend",firstImgClone)
	}
}

function nextButton(){
	let imgUl = document.querySelector(".visual_img")
	let width = document.querySelector(".container_visual").clientWidth;
	count++
	imgUl.style.transform = "translateX("+-width * count+"px)";
	imgUl.style.transition = "transform 0.4s ease-in-out";
	if(count<3){
		document.querySelector(".figure_pagination").firstElementChild.textContent = count;
	}
	document.querySelector(".btn_prev").firstElementChild.classList.remove("off");
	document.querySelector(".btn_nxt").firstElementChild.classList.add("off");
}

function prevButton(){
	let imgUl = document.querySelector(".visual_img")
	let width = document.querySelector(".container_visual").clientWidth;
	count--
	imgUl.style.transform = "translateX("+(-width * count)+"px)";
	document.querySelector(".visual_img").style.transition = "transform 0.4s ease-in-out";
	if(count>0){
		document.querySelector(".figure_pagination").firstElementChild.textContent = count; 
	}
	document.querySelector(".btn_prev").firstElementChild.classList.add("off");
	document.querySelector(".btn_nxt").firstElementChild.classList.remove("off");
}

function transactionEvent(){
	let imgUl = document.querySelector(".visual_img")
	let Imgli = imgUl.querySelectorAll("li")
	let width = document.querySelector(".container_visual").clientWidth;
	if(Imgli[count].id === "firstClone"){
		count = 1;
		document.querySelector(".figure_pagination").firstElementChild.textContent = count;
		imgUl.style.transition = "none";
		imgUl.style.transform = "translateX("+-width * count+"px)"
	}else if(Imgli[count].id === "lastClone"){
		count = 2
		document.querySelector(".figure_pagination").firstElementChild.textContent = count;
		imgUl.style.transition = "none";
		imgUl.style.transform = "translateX("+-width * count+"px)"
	}
}

function openContent(){
	this.style.display = "none"
	document.querySelector(".store_details").classList.remove("close3");
	document.querySelector(".section_store_details ._close").style.display = "block";
}

function closeContent(){
	this.style.display = "none"
	document.querySelector(".store_details").classList.add("close3");
	document.querySelector(".section_store_details ._open").style.display = "block";
}

function reservation(){
	let displayInfoId = document.querySelector("#detailId").getAttribute("value");
	location.href = "http://localhost:14816/api/reservation?id="+displayInfoId;
}

function scoreEvent(detail){
	if(detail.averageScore>0){
		let average = detail.averageScore.toFixed(1);
		let percent = (average / 5) * 100;
		document.querySelector(".text_value").firstElementChild.textContent = average;
		document.querySelector(".graph_value").style.width = percent+"%";
	}else{
		document.querySelector(".text_value").firstElementChild.textContent = 0;
		document.querySelector(".graph_value").style.width = 0+"%";
	}
}

function CommentEvent(detail){
	let commentUl = document.querySelector(".list_short_review")
	let commentImgTemplate = document.getElementById("commentImgTemplate").innerHTML
	let commentTemplate = document.querySelector("#commentTemplate").innerHTML
	let ImgTemplateHandlebar = Handlebars.compile(commentImgTemplate)
	let TemplateHandlebar = Handlebars.compile(commentTemplate)
	let resultCommentHTML = ""
	
	//formatDate
	Handlebars.registerHelper("formatDate",function(reservationDate){
		let date = new Date(reservationDate)
		return date.getFullYear()+"."+(date.getMonth()+1)+"."+date.getDate()
	})
		
	for(let i=0;i<3;i++){
		if(detail.comments.length<1){
			let li = document.createElement("li")
			let h4 = document.createElement("h4")
			h4.textContent = "등록된 한줄평이 없습니다."
			li.appendChild(h4)
			document.querySelector(".list_short_review").appendChild(li);
			break;
		}
		if(detail.comments[i].commentImages.length>0){
			resultCommentHTML = ImgTemplateHandlebar(detail.comments[i])
			commentUl.innerHTML += resultCommentHTML
		}else{
			resultCommentHTML = TemplateHandlebar(detail.comments[i])
			commentUl.innerHTML += resultCommentHTML
		}
	}
}

function editGoLoad(detail){
	document.querySelector(".store_map").setAttribute("src",detail.displayInfoImage.saveFileName);
	document.querySelector(".store_addr_bold").textContent = detail.displayInfo.placeStreet
	document.querySelector(".addr_old_detail").textContent = detail.displayInfo.placeLot
	document.querySelector(".addr_detail").textContent = detail.displayInfo.placeName
	document.querySelector(".store_tel").textContent = detail.displayInfo.telephone
}

function goLoadEvent(){
	this.classList.add("active");
	document.querySelector("._detail .anchor").classList.remove("active");
	document.querySelector(".detail_area_wrap").classList.add("hide");
	document.querySelector(".detail_location").classList.remove("hide");
}

function productInfoEvent(){
	this.classList.add("active");
	document.querySelector("._path .anchor").classList.remove("active");
	document.querySelector(".detail_area_wrap").classList.remove("hide");
	document.querySelector(".detail_location").classList.add("hide");
}

function imageButtonEvent(detail){
	let imgUl = document.querySelector(".visual_img")
	let width = document.querySelector(".container_visual").clientWidth;
	if(detail.productImages.length>1){
		imgUl.style.transform = "translateX("+-width * count+"px)"
		//nxt button
		document.querySelector(".group_visual .nxt").addEventListener("click",nextButton)
		
		//prev button
		document.querySelector(".group_visual .prev").addEventListener("click",prevButton)
		
		//transactionChange
		imgUl.addEventListener("transitionend",transactionEvent)
	}else{
		document.querySelector(".group_visual .nxt").style.display = "none"
		document.querySelector(".group_visual .prev").style.display = "none"
	}
}
function detailById(displayInfoId){
	let xhr=new XMLHttpRequest;
	xhr.addEventListener("load",function(){
		if(xhr.readyState === 4){
			if(xhr.status === 200){
				let detail=JSON.parse(xhr.responseText);
				console.log(detail);
				
				//이미지 넣기 
				updateCarouselImage(detail)
				
				// 이미지 버튼 이벤트 처리
				imageButtonEvent(detail)
				
				//이미지 개수 체크 후 이미지 페이지 텍스트 수정처리
				document.querySelector(".figure_pagination").lastElementChild.firstElementChild.textContent = detail.productImages.length;
				// 상세설명 데이터 추가
				document.querySelector(".store_details .dsc").textContent = detail.displayInfo.productContent;
				
				//펼쳐보기, 접기 처리 이벤트
				document.querySelector(".section_store_details ._open").addEventListener("click",openContent)
				document.querySelector(".section_store_details ._close").addEventListener("click",closeContent)
				
				//예매하기 버튼 이벤트
				document.querySelector(".section_btn").addEventListener("click",reservation)
				
				//총 Comment 갯수
				document.querySelector(".join_count .green").textContent = detail.comments.length + "건";
				
				//평점 처리
				scoreEvent(detail)
				
				//한줄평 처리하는 곳 
				CommentEvent(detail)
								
				//오시는 길 정보 수정 (엔에이치 티켓링크 정보 처리 뭐로할지 모르겠음)
				editGoLoad(detail)
				
				//상품정보, 오시는길 태그 선택시 이벤트 처리
				document.querySelector(".detail_info_lst .in_dsc").textContent = detail.displayInfo.productContent
				document.querySelector(".info_tab_lst ._path .anchor").addEventListener("click",goLoadEvent)
				document.querySelector(".info_tab_lst ._detail .anchor").addEventListener("click",productInfoEvent)
			}
		}
	})
	xhr.open("GET","products/"+displayInfoId);
	xhr.send();
}