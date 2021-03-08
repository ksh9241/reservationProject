document.addEventListener("DOMContentLoaded", function() {
	categoryListAll(); /*카테고리 리스트*/
	carousel(); /*프로모션 리스트*/
})

let totalCount = 0;
let start = 0;
let plusStart = 4;
let categoryCount = 0;
let categoryId = 0;
function setTimeOut(ul,counter){
	let size = document.querySelector(".container_visual").clientWidth;
	let li=ul.querySelectorAll("li");
	let len=ul.querySelectorAll("li").length;
			
	ul.style.transform = "translateX(" + (-size * counter) +"px)";
	ul.style.transition = "transform 0.4s ease-in-out";	
	if(counter<len){
		setTimeout(function(){
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
				console.log(carousel)
				let promotionTemplate = document.querySelector("#promotionItem").innerHTML
				let promotionHandlebar = Handlebars.compile(promotionTemplate)
				let resultPromotion = ""
				let len = carousel.items.length;
				for(let i=0;i<len;i++){
					resultPromotion += promotionHandlebar(carousel.items[i])
				}
				img.innerHTML = resultPromotion;		
				
				let first = img.firstElementChild;
				let clonefirst = first.cloneNode(true);
				clonefirst.id="firstClone";
				img.insertAdjacentElement("beforeend",clonefirst);
				setTimeOut(img,0);
			}
		}
	})
	xhr.open("GET", "promotionImg");
	xhr.send();
}

function categoryList(category){
	let categoryUl = document.querySelector(".event_tab_lst");
	let categoryTemplate = document.querySelector("#categoryTemplate").innerHTML;
	let categoryHandlebar = Handlebars.compile(categoryTemplate)
	let resultCategory = ""
	for (let i = 0; i < category.items.length; i++) {
		resultCategory += categoryHandlebar(category.items[i])
		totalCount += category.items[i].count;
	}
	document.querySelector(".pink").textContent = totalCount+"개";
	categoryUl.innerHTML += resultCategory
	categoryCount = totalCount; //59
}

function viewButtonEvent(){
	start += plusStart 
	mainProductById(categoryId,start);
	if(start+4>=categoryCount){
		document.querySelector(".more").style.display = "none";
	}
}

function categoryListAll() {
	let xhr = new XMLHttpRequest;
	xhr.addEventListener("load", function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				mainProductById(categoryId,0);
				let category = JSON.parse(xhr.responseText);
				let categoryUl = document.querySelector(".event_tab_lst");
				console.log(category);
				
				//카테고리 리스트 재정의
				categoryList(category)
				
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
						document.querySelector(".pink").textContent = categoryCount+"개";
					} //if end
				});
				document.querySelector(".more").addEventListener("click",viewButtonEvent);
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
					let itemTemplate = document.querySelector("#itemList").innerHTML
					let itemHandlebar = Handlebars.compile(itemTemplate)
					let resultItem = ""
					for(let i = 0; i < mainProductList.items.length; i++){
						resultItem = itemHandlebar(mainProductList.items[i])
						if(i % 2 == 0){
							productUl[0].innerHTML += resultItem;
						}else{
							productUl[1].innerHTML += resultItem;
						}
					}
				}
			}
		})
	xhr.open("GET", "products?categoryId="+categoryId+"&start="+start); 
	xhr.send();
}