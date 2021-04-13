document.addEventListener("DOMContentLoaded",function(){
	let commentId = document.querySelector("#commentId").value
	pasingEditData(commentId);
})

let formData = new FormData()


function pasingEditData(commentId){
	let xhr = new XMLHttpRequest ;
	xhr.addEventListener("load",function(){
		if(xhr.readyState === 4){
			if(xhr.status === 200){
				let data = JSON.parse(xhr.responseText);
				console.log(data);
				
				//데이터 파싱
				if(data){
					dataPasing(data)
				}
				
				//별점 이벤트처리
				document.querySelector(".rating").addEventListener("click",function(evt){
					PrevStar(evt.target.value)
					document.querySelector(".star_rank").innerText = evt.target.value;
				})
				
				//텍스트 글자수 처리
				checkTextLength(data)
				
				//파일 처리
				fileUpload(data)
				
				//파일 삭제
				deleteFile()
				
				//댓글 수정
				editReservation(commentId,data.product_id)
			}
		}
	})
	xhr.open("GET","reservation/"+commentId+"/edit");
	xhr.send();
}

function dataPasing(data){
	
	if(data.save_file_name){
		let myImage = document.querySelector(".item_thumb");
		myImage.src = "img/"+data.save_file_name;
		document.querySelector(".lst_thumb li").style.display = "block";
		formData.append("save_file_name",data.save_file_name)
	}
	document.querySelector(".review_write_info").style.display = "none";
	document.querySelector(".review_textarea").value = data.comment;
	document.querySelector(".star_rank").innerText = data.score;
	document.querySelector(".star_rank").classList.remove("gray_star");
	PrevStar(data.score);
	
	document.querySelector(".guide_review").firstElementChild.innerText = document.querySelector(".review_textarea").value.length
	if(data.file_name){
		document.querySelector(".guide_review").lastElementChild.innerText = data.file_name
	}
}

function PrevStar(score){
	let starList = document.querySelectorAll(".rating input")
	for(let i=0;i<starList.length;i++){
		if(i<score){
			starList[i].classList.add("checked");
			starList[i].checked = true;			
		}else{
			starList[i].classList.remove("checked");
			starList[i].checked = false;			
		}
	}
}

function checkTextLength(data){
	document.querySelector(".review_textarea").addEventListener("keyup",function(){
		document.querySelector(".guide_review").firstElementChild.innerText = this.value.length
		document.querySelector(".guide_review").lastElementChild.innerText = data.file_name
	})
}

function fileUpload(data){
	document.querySelector(".hidden_input").addEventListener("change",function(){
		if(this.files[0] != undefined){
			document.querySelector(".lst_thumb li").style.display = "block";
			let myImage = document.querySelector(".item_thumb");
			myImage.src = window.URL.createObjectURL(this.files[0]); 
			document.querySelector(".guide_review").lastElementChild.innerText = this.files[0].name
			console.log(this.files[0])
			formData.append("imageData",this.files[0])
			formData.append("save_file_name",data.save_file_name)
		}
	})
}

function deleteFile(){
	document.querySelector(".spr_book").addEventListener("click",function(){
		formData.delete("imageData")
		formData.delete("save_file_name")
		document.querySelector(".lst_thumb li").style.display = "none";
		document.querySelector(".guide_review").lastElementChild.innerText = "(최소5자이상)";
		
	})
}

function editReservation(commentId,productId){
	document.querySelector(".bk_btn").addEventListener("click",function(){
		let Score = document.querySelector(".star_rank").textContent
		let comment = document.querySelector(".review_textarea").value
		let check = true
		
		formData.append("score",Score)
		formData.append("comment",comment)
		
		if(Score<=0 && comment.length === 0){
			check = false
		}
		
		if(check){
			let xhr = new XMLHttpRequest;
			xhr.addEventListener("load",function(){
				if(xhr.readyState === 4){
					if(xhr.status === 200){
						document.location.href = "detail?id="+productId
					}
				}
			})
			xhr.open("POST","reservations/"+commentId+"/commentEdit")
			xhr.send(formData)
		}
	})
}