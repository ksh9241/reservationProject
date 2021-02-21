document.addEventListener("DOMContentLoaded",function(){
	reviewList()
	
})
function formatDate(date){
	
	let formatDate = new Date(date)
	let year = formatDate.getFullYear()
	let month = (formatDate.getMonth()+1)<10?"0"+(formatDate.getMonth()+1):(formatDate.getMonth()+1)
	let day = formatDate.getDate()
	return year+"."+month+"."+day
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
				document.querySelector(".green").textContent = detail.comments.length+"건"
				if(detail.averageScore > 0){
					let averageScore = detail.averageScore.toFixed(1)
					document.querySelector(".text_value").firstElementChild.textContent = averageScore
					document.querySelector(".graph_value").style.width = (averageScore/5)*100+"%"
				}else{
					document.querySelector(".text_value").firstElementChild.textContent = 0
					document.querySelector(".graph_value").style.width = (0/5)*100+"%"
				}
				
				//한줄평 처리하기
				let noImgCommentSample = document.getElementById("noImgCommentSample").innerHTML
				let ImgCommentSample = document.getElementById("ImgCommentSample").innerHTML
				let ul = document.querySelector(".list_short_review")
				let div = document.createElement("div")
				for(let i=0;i<detail.comments.length;i++){
					let str = ""
					if(detail.comments[i].commentImages.length>0){
						str = ImgCommentSample.replace("{saveFileName}",detail.comments[i].commentImages[0].saveFileName)
									  .replace("{productDescription}",detail.displayInfo.productDescription)
								   	  .replace("{comment}",detail.comments[i].comment)
									  .replace("{score}",detail.comments[i].score)
									  .replace("{reservationName}",detail.comments[i].reservationName)
									  .replace("{reservationDate}",formatDate(detail.comments[i].reservationDate))
						div.innerHTML += str
					}else{
						str = noImgCommentSample.replace("{productDescription}",detail.displayInfo.productDescription)
												.replace("{comment}",detail.comments[i].comment)
												.replace("{score}",detail.comments[i].score)
												.replace("{reservationName}",detail.comments[i].reservationName)
												.replace("{reservationDate}",formatDate(detail.comments[i].reservationDate))
						div.innerHTML += str
					}
				}
				if(detail.comments.length === 0){
					let li = document.createElement("li").textContent = "등록된 한줄평이 없습니다."
					div.innerHTML = li
				}
				ul.appendChild(div)		
			}
		}
	})
	xhr.open("GET","products/"+displayInfoId)
	xhr.send()
}