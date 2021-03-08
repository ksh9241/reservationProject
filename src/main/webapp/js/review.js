document.addEventListener("DOMContentLoaded",function(){
	reviewList()
})

function scoreAndCommentCount(detail){
	document.querySelector(".green").textContent = detail.comments.length+"건"
	if(detail.averageScore > 0){
		let averageScore = detail.averageScore.toFixed(1)
		document.querySelector(".text_value").firstElementChild.textContent = averageScore
		document.querySelector(".graph_value").style.width = (averageScore/5)*100+"%"
	}else{
		document.querySelector(".text_value").firstElementChild.textContent = 0
		document.querySelector(".graph_value").style.width = (0/5)*100+"%"
	}
}

function commentList(detail){
	let noImgCommentTemplate = document.getElementById("noImgCommentSample").innerHTML
	let ImgCommentTemplate = document.getElementById("ImgCommentSample").innerHTML
	let noImgCommentHandlebar = Handlebars.compile(noImgCommentTemplate)
	let ImgCommentHandlebar = Handlebars.compile(ImgCommentTemplate)
	let resultImg = ""
	let resultNoImg = ""
	let ul = document.querySelector(".list_short_review")
	
	//핸들러 dateFormat Helper method
	Handlebars.registerHelper("dateFormat",function(reservationDate){
		let date = new Date(reservationDate)
		let year = date.getFullYear()
		let month = date.getMonth()+1<10?"0"+(date.getMonth()+1):date.getMonth()+1
		let day = date.getDate()
		return year+'.'+month+'.'+day
	})
	for(let i=0;i<detail.comments.length;i++){
		if(detail.comments[i].commentImages.length>0){
			resultImg = ImgCommentHandlebar(detail.comments[i])
			ul.innerHTML += resultImg
		}else{
			resultNoImg = noImgCommentHandlebar(detail.comments[i])
			ul.innerHTML += resultNoImg
		}
	}
	if(detail.comments.length === 0){
		let li = document.createElement("li")
			li.textContent = "등록된 한줄평이 없습니다."
		console.log(li)
		ul.appendChild(li)
	}
}

function reviewList(){
	let displayInfoId = document.querySelector("#displayInfoId").value
	let xhr = new XMLHttpRequest
	xhr.addEventListener("load",function(){
		if(xhr.readyState === 4){
			if(xhr.status === 200){
				let detail = JSON.parse(xhr.responseText)
				console.log(detail);
				
				//평점 및 한줄평 갯수 처리
				scoreAndCommentCount(detail)
				
				//한줄평 처리하기
				commentList(detail)
			}
		}
	})
	xhr.open("GET","products/"+displayInfoId)
	xhr.send()
}