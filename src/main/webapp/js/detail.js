document.addEventListener("DOMContentLoaded",function(){
	let displayInfoId = document.querySelector("#detailId").getAttribute("value");
	detailById(displayInfoId);
})

function detailById(displayInfoId){
	let xhr=new XMLHttpRequest;
	xhr.addEventListener("load",function(){
		if(xhr.readyState === 4){
			if(xhr.status === 200){
				let detail=JSON.parse(xhr.responseText);
				sessionStorage.setItem("detail",detail);
				
				console.log(detail);
				//이미지 넣기 (1. sample script에 만들고 반복문으로 처리하기. 2. 원래 4개있던거로 이용해서 최대2개 처리하기)
				for(let i=1;i<=detail.productImages.length;i++){
					document.querySelector(".visual_img .item:nth-of-type("+i+")").querySelector(".img_thumb").setAttribute("src",detail.productImages[i-1].saveFileName);
				}
				
				//이미지 개수 체크 후 이미지 페이지 텍스트 수정처리
				document.querySelector(".figure_pagination").lastElementChild.firstElementChild.textContent = detail.productImages.length;
				// 상세설명 데이터 추가
				document.querySelector(".store_details .dsc").textContent = detail.displayInfo.productContent;
				// 이미지 버튼 이벤트 처리
				let width = document.querySelector(".container_visual").clientWidth;
				
				if(detail.productImages.length>1){
					//nxt button
					document.querySelector(".group_visual .nxt").addEventListener("click",function(){
						document.querySelector(".visual_img").style.transform = "translateX("+-width+"px)";
						document.querySelector(".visual_img").style.transition = "transform 0.4s ease-in-out";
						document.querySelector(".figure_pagination").firstElementChild.textContent = 2; //이거 어떻게 처리할 것인가?
						document.querySelector(".btn_prev").firstElementChild.classList.remove("off");
						document.querySelector(".btn_nxt").firstElementChild.classList.add("off");
					})
					
					//prev button
					document.querySelector(".group_visual .prev").addEventListener("click",function(){
						document.querySelector(".visual_img").style.transform = "translateX("+0+"px)"; //0으로 바꾸는 방법 말고 기존값에서 +width값을 할순없는가?
						document.querySelector(".visual_img").style.transition = "transform 0.4s ease-in-out"; 
						document.querySelector(".figure_pagination").firstElementChild.textContent = 1; //이거 어떻게 처리할 것인가?
						document.querySelector(".btn_prev").firstElementChild.classList.add("off");
						document.querySelector(".btn_nxt").firstElementChild.classList.remove("off");
					})
					
				}
				
				//펼쳐보기, 접기 처리 이벤트
				document.querySelector(".section_store_details ._open").addEventListener("click",function(){
					this.style.display = "none"
					document.querySelector(".store_details").classList.remove("close3");
					document.querySelector(".section_store_details ._close").style.display = "block";
				})
				document.querySelector(".section_store_details ._close").addEventListener("click",function(){
					this.style.display = "none"
					document.querySelector(".store_details").classList.add("close3");
					document.querySelector(".section_store_details ._open").style.display = "block";
				})
				
				//예매하기 버튼 이벤트
				document.querySelector(".section_btn").addEventListener("click",function(){
					location.href = "http://localhost:14816/api/reservation?id="+displayInfoId;
				})
				
				//총 Comment 갯수
				document.querySelector(".join_count .green").textContent = detail.comments.length + "건";
				
				//평점 처리
				if(detail.averageScore>0){
					let average = detail.averageScore.toFixed(1);
					let percent = (average / 5) * 100;
					document.querySelector(".text_value").firstElementChild.textContent = average;
					document.querySelector(".graph_value").style.width = percent+"%";
				}else{
					document.querySelector(".text_value").firstElementChild.textContent = 0;
					document.querySelector(".graph_value").style.width = 0+"%";
				}
				
				let commentImgSample = document.querySelector("#commentImgSample").innerHTML
				let commentSample = document.getElementById("commentSample").innerHTML
				let commentUl = document.querySelector(".list_short_review")
				//한줄평 처리하는 곳 
				if(detail.comments.length>0){
					for(let i=1;i<=3;i++){
					let date = new Date(detail.comments[i-1].createDate);
					let today = date.getFullYear()+"."+(date.getMonth()+1)+"."+date.getDate();
					let str = ""
						if(detail.comments[i-1].commentImages.length>0){
							str = commentImgSample.replace("{comment}",detail.comments[i-1].comment)
												  .replace("{score}",detail.comments[i-1].score+".0")
												  .replace("{reservationName}",detail.comments[i-1].reservationName)
												  .replace("{date}",today)
										  		  .replace("{saveFileName}",detail.comments[i-1].commentImages[0].saveFileName)
							commentUl.innerHTML += str	
						}else{
							str = commentSample.replace("{comment}",detail.comments[i-1].comment)
												  .replace("{score}",detail.comments[i-1].score+".0")
												  .replace("{reservationName}",detail.comments[i-1].reservationName)
												  .replace("{date}",today)
							commentUl.innerHTML += str	
						}
					}
				}else{
					let li = document.createElement("li")
					let h4 = document.createElement("h4")
					h4.textContent = "등록된 한줄평이 없습니다."
					li.appendChild(h4)
					document.querySelector(".list_short_review").appendChild(li);
				}
				
				//오시는 길 정보 수정 (엔에이치 티켓링크 정보 처리 뭐로할지 모르겠음)
				document.querySelector(".store_map").setAttribute("src",detail.displayInfoImage.saveFileName);
				
				document.querySelector(".store_addr_bold").textContent = detail.displayInfo.placeStreet
				document.querySelector(".addr_old_detail").textContent = detail.displayInfo.placeLot
				document.querySelector(".addr_detail").textContent = detail.displayInfo.placeName
				document.querySelector(".store_tel").textContent = detail.displayInfo.telephone
				
				//상품정보, 오시는길 태그 선택시 이벤트 처리
				document.querySelector(".info_tab_lst ._path .anchor").addEventListener("click",function(){
					this.classList.add("active");
					document.querySelector("._detail .anchor").classList.remove("active");
					document.querySelector(".detail_area_wrap").classList.add("hide");
					document.querySelector(".detail_location").classList.remove("hide");
				})
				document.querySelector(".info_tab_lst ._detail .anchor").addEventListener("click",function(){
					this.classList.add("active");
					document.querySelector("._path .anchor").classList.remove("active");
					document.querySelector(".detail_area_wrap").classList.remove("hide");
					document.querySelector(".detail_location").classList.add("hide");
				})
				
				
				
			}
		}
	})
	xhr.open("GET","products/"+displayInfoId);
	xhr.send();
}