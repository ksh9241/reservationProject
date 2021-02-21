document.addEventListener("DOMContentLoaded",function(){
	reviewWrite()
})

function reviewWrite(){
	let productId = document.getElementById("productId").value
	let reservationInfoId = document.getElementById("reservationInfoId").value
	let scoreList = document.querySelectorAll(".rating input")
	console.log(productId+","+reservationInfoId)
	let fileName = ""
	let fileType = ""
	let formData = new FormData()
	//별점 클릭이벤트
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
	
	//리뷰 textarea클릭 시 활성화 이벤트
	document.querySelector(".review_write_info").addEventListener("click",function(evt){
		evt.target.closest(".review_write_info").style.display = "none"
		document.querySelector(".review_textarea").focus()
	})
	
	//리뷰 쓸때마다 제한수 처리
	document.querySelector(".review_textarea").addEventListener("keyup",function(evt){
		document.querySelector(".guide_review").firstElementChild.textContent = evt.target.value.length
	})
	
	//업로드 파일 이벤트 처리
	document.querySelector(".hidden_input").addEventListener("change",function(evt){
		let imgView = document.querySelector(".item_preview_thumbs")
		let myImage = document.querySelector(".item_thumb")
		
		if(evt.target.files[0]!=undefined){
			fileName = evt.target.files[0].name
			fileType = evt.target.files[0].type
			document.querySelector(".guide_review").lastElementChild.textContent = fileName
			imgView.querySelector(".item").style.display = "block"
			//imgView.querySelector("img").setAttribute("src",)
			
			let reader = new FileReader()
			reader.addEventListener("load",function(e){
			myImage.src = reader.result
			},false)
			reader.readAsDataURL(evt.target.files[0])
			
			
			formData.append("imageData",evt.target.files[0])
			//document.querySelector(".item_thumb").setAttribute("src",formData.)
			
		}else{
			document.querySelector(".guide_review").lastElementChild.textContent =  "(최소5자이상)"
			fileName = ""
			fileType = ""
			imgView.querySelector(".item").style.display = "none"
			
			//이부분 formData() 삭제해야됨
		}
	})
	
	//업로드 파일 삭제
	document.querySelector(".spr_book").addEventListener("click",function(evt){
		evt.target.closest("li").style.display = "none"
		//이부분 formData() 삭제해야됨
	})
	
	//리뷰 등록
	document.querySelector(".bk_btn").addEventListener("click",function(){
		let check = true
		
		
		let Score = document.querySelector(".star_rank").textContent
		let comment = document.querySelector(".review_textarea").value
		
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
			
		
	})
	
}

//선택 객체의 prevChild 클래스 checked추가 메소드
function scorePrevAll(list,breakDate){
	console.log(list)
	console.log(breakDate)
	for(let i=0;i<list.length;i++){
		if(list[i].value<=breakDate.value){
			list[i].classList.add("checked")
			//break
		}else{
			list[i].classList.remove("checked")
		}
		
	}
}