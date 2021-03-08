document.addEventListener("DOMContentLoaded",function(){
	reviewWrite()
})

let formData = new FormData()

function clickScoreEvent(){
	let scoreList = document.querySelectorAll(".rating input")
	for(let i=0;i<scoreList.length;i++){
		scoreList[i].addEventListener("click",function(evt){
			scorePrevAll(scoreList,evt.target)
			let starRank = document.querySelector(".star_rank")
			starRank.textContent = evt.target.value
			if(starRank.textContent != 0){
				starRank.classList.remove("gray_star")
			}else{
				starRank.classList.add("gray_star")
			}
		})
	}
}

function scorePrevAll(list,breakDate){
	for(let i=0;i<list.length;i++){
		if(list[i].value<=breakDate.value){
			list[i].classList.add("checked")
			list[i].checked = true;
			//break
		}else{
			list[i].classList.remove("checked")
			list[i].checked = false;
		}
		
	}
}

function clickReviewCommentEvent(evt){
	evt.target.closest(".review_write_info").style.display = "none"
	document.querySelector(".review_textarea").focus()
}

function limitCommentCount(evt){
	document.querySelector(".guide_review").firstElementChild.textContent = evt.target.value.length
}

function fileUpLoad(evt){
	let imgView = document.querySelector(".item_preview_thumbs")
	let myImage = document.querySelector(".item_thumb")
	console.log(evt.target.files[0])
	if(evt.target.files[0]!=undefined){
		document.querySelector(".guide_review").lastElementChild.textContent = evt.target.files[0].name
		imgView.querySelector(".item").style.display = "block"
		
		//선택한 파일 미리보기 처리하는 함수
		/*let reader = new FileReader()
		reader.addEventListener("load",function(){
		myImage.src = reader.result
		},false)
		reader.readAsDataURL(evt.target.files[0])*/
		//debugger;
		myImage.src = window.URL.createObjectURL(evt.target.files[0])
		
		//데이터 보낼 때 사용하는 함수
		formData.delete("imageData")
		formData.append("imageData",evt.target.files[0])
	}else{
		document.querySelector(".guide_review").lastElementChild.textContent =  "(최소5자이상)"
		imgView.querySelector(".item").style.display = "none"
		//이부분 formData() 삭제해야됨
		formData.delete("imageData")
	}
}

function deleteUpLoadData(evt){
	evt.target.closest("li").style.display = "none"
	formData.delete("imageData")
}

function insertReviewEvent(){
	let productId = document.getElementById("productId").value
	let reservationInfoId = document.getElementById("reservationInfoId").value
	let Score = document.querySelector(".star_rank").textContent
	let comment = document.querySelector(".review_textarea").value
	let check = true
	
	formData.append("productId",productId)
	formData.append("reservationInfoId",reservationInfoId)
	formData.append("Score",Score)
	formData.append("Comment",comment)
	for(let key of formData.keys()){
		console.log(key)
		}
	for(let value of formData.values()){ 
		console.log(value)
	}
	
	if(Score<=0 && comment.length === 0){
		check = false
	}
	if(check){
		let xhr = new XMLHttpRequest
		xhr.addEventListener("load",function(){
			if(xhr.readyState === 4){
				if(xhr.status === 200){
					let email = JSON.parse(xhr.responseText)
					location.href = "http://localhost:14816/api/myreservation?resrv_email="+email.email
				}
			}
		})
		xhr.open("POST","reservations/"+reservationInfoId+"/comments")
		xhr.send(formData)
	}
}

function reviewWrite(){
	//별점 클릭이벤트
	clickScoreEvent()
	
	//리뷰 textarea클릭 시 활성화 이벤트
	document.querySelector(".review_write_info").addEventListener("click",clickReviewCommentEvent)
	
	//리뷰 쓸때마다 제한수 처리
	document.querySelector(".review_textarea").addEventListener("keyup",limitCommentCount)
	
	//업로드 파일 이벤트 처리
	document.querySelector(".hidden_input").addEventListener("change",fileUpLoad)
	
	//업로드 파일 삭제
	document.querySelector(".spr_book").addEventListener("click",deleteUpLoadData)
	
	//리뷰 등록
	document.querySelector(".bk_btn").addEventListener("click",insertReviewEvent)
}

