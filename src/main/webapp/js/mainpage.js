function plusStart(start){
	start+=4;
	return start;
}

function setTimeOut(ul,counter){
	let size = document.querySelector(".container_visual").clientWidth;
	let li=ul.querySelectorAll("li");
	let len=ul.querySelectorAll("li").length;
			
	ul.style.transform = "translateX(" + (-size * counter) +"px)";
	ul.style.transition = "transform 0.4s ease-in-out";	
	if(counter<len){
		setTimeout(function(){
			/* 반대로 버튼클릭할때 처리할 부분이라 필요없음.
			if(li[counter].id === "lastClone"){
				console.log("안들어옴");
				ul.style.transition = "none";
				counter = len - 2;
				ul.style.transform = "translateX(" + (-size * counter) +"px)";
			}
			*/
			if(li[counter].id === "firstClone"){
				counter = 0;
				ul.style.transition = "none";
				ul.style.transform = "translateX(" + (-size * counter) +"px)";
			}
			counter++;
			setTimeOut(ul,counter);
		},1000);
	}else{
		setTimeOut(ul,0);
	}
}

function carousel() {
	let xhr = new XMLHttpRequest;
	xhr.addEventListener("load", function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				let img = document.querySelector(".visual_img");
				let carousel = JSON.parse(xhr.responseText);
				let len = carousel.items.length;
				let str = "";
				for (let i = 0; i < len; i++) {
					let promotionSample = document.querySelector("#promotionItem").innerHTML;
					str += promotionSample.replace("{saveFileName}", carousel.items[i].saveFileName)
										  .replace("{id}",carousel.items[i].productId);
				}
				img.innerHTML = str;
				
				let first = img.firstElementChild;
				let clonefirst = first.cloneNode(true);
				clonefirst.id="firstClone";
				img.insertAdjacentElement("beforeend",clonefirst);
				
				// 반대로 버튼 클릭할 때 필요한 부분이라 당장필요없음.
				//let last = img.lastElementChild;
				//let clonelast = last.cloneNode(true);
				//clonelast.id = "lastClone";
				//img.insertAdjacentElement("afterbegin",clonelast);
								
				setTimeOut(img,0);
			}
		}
	})
	xhr.open("GET", "promotionImg");
	xhr.send();
}

function categoryListAll() {
	let categoryId = 0;
	let start = 0;
	let totalCount = 0;
	let xhr = new XMLHttpRequest;
	xhr.addEventListener("load", function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				
				mainProductById(categoryId,0);
				let category = JSON.parse(xhr.responseText);
				let categoryUl = document.querySelector(".event_tab_lst");
				let str = "";
				console.log(category);
				//JSON객체 카운트
				for (let i = 0; i < category.items.length; i++) {
					let categorySample = document.querySelector("#categorySample").innerHTML;
					str += categorySample.replace("{id}", category.items[i].id)
										 .replace("{name}", category.items[i].name);
						totalCount += category.items[i].count;
				}
				let categoryCount = totalCount; //59
				categoryUl.innerHTML += str;
				let count = document.querySelector(".pink").innerText;
				let str2 = "";
				str2 = count.replace("{count}", totalCount);
				document.querySelector(".pink").innerText = str2;
				
				//카테고리 클릭 이벤트
				categoryUl.addEventListener("click", function(evt) {
					document.querySelector(".more").style.display = "block";
					let productUl = document.querySelectorAll(".lst_event_box");
					//카테고리 누를 때마다 시작값 초기화.
					start = 0;
					
					let li = evt.target.closest("li");
					if (li) { //ul태그의 빈틈이 눌릴 경우 오류가 발생하는 것을 방지하기 위해
						this.querySelector(".active").setAttribute("class", "anchor");
						li.querySelector("a").setAttribute("class", "anchor active");
						categoryId = li.getAttribute("data-category");
						
						productUl[0].innerHTML="";
						productUl[1].innerHTML="";
						
						mainProductById(categoryId,0);
						
						if (categoryId != 0) {
							categoryCount = category.items[categoryId - 1].count;
						}else{
							categoryCount = totalCount;
						}
						str2 = count.replace("{count}", categoryCount);
						document.querySelector(".pink").innerText = str2;
					} //if end
				});
				
				document.querySelector(".more").addEventListener("click",function(){
					start = plusStart(start);
					mainProductById(categoryId,start);
					if(start+4>=categoryCount){
						document.querySelector(".more").style.display = "none";
					}
				});
			}
		}
	})
	xhr.open("GET", "categoryList");
	xhr.send();
}



//MainProductListById
function mainProductById(categoryId,start) {
	let xhr = new XMLHttpRequest;
	xhr.addEventListener("load", function() {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					
					let mainProductList = JSON.parse(xhr.responseText);
					
					let productUl = document.querySelectorAll(".lst_event_box");
					let str1 = "";
					let str2 = "";
					for (let i = 0; i < mainProductList.items.length; i++) {
						let itemList = document.querySelector("#itemList").innerHTML;
						let change = itemList.replace("{id}", mainProductList.items[i].displayInfoId)
							.replace("{description}", mainProductList.items[i].productDescription)
							.replace("{description}", mainProductList.items[i].productDescription)
							.replace("{saveFileName}", mainProductList.items[i].saveFileName)
							.replace("{placeName}", mainProductList.items[i].placeName)
							.replace("{content}", mainProductList.items[i].productContent);
						if (i % 2 == 0) {
							str1 = change;
							productUl[0].innerHTML += str1;
							str1="";
						} else {
							str2 = change;
							productUl[1].innerHTML += str2;
							str2="";
						}
					}
				}
			}
		})
	 
	xhr.open("GET", "products?categoryId="+categoryId+"&start="+start); 
	xhr.send();
}

document.addEventListener("DOMContentLoaded", function() {
	categoryListAll(); /*카테고리 리스트*/
	carousel(); /*프로모션 리스트*/
})