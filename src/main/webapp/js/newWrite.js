/**
 * 
 */

 function checkinput(){
	
	if(document.newWrite_form.boardtype.value.length==0){
		alert('구분을 선택해 주세요.');
		newWrite_form.boardtype.focus();
		return false;
	}
	if(document.newWrite_form.username.value.length==0){
		alert('작성자를 입력해 주세요.');
		newWrite_form.username.focus();
		return false;
	}
	if(!radio_1.checked && !radio_2.checked && !radio_3.checked){
		alert('분류를 하나 이상 선택하세요.');
		newWrite_form.radio_1.focus();
		return false;
	}
	if(document.newWrite_form.title.value.length==0){
		alert('제목을 작성해 주세요.');
		newWrite_form.title.focus();
		return false;
	}
	if(document.newWrite_form.content.value.length==0){
		alert('내용을 작성해 주세요.');
		newWrite_form.content.focus();
		return false;
	}
	
	alert('저장 하였습니다.');
	return true;
}

//첨부파일 변경시 checkEXT(); 실행 후 사이즈 체크
window.onload = function(){

		const modal = document.getElementById("modal");
		let input_file_span = document.getElementById('span');
		let input_file = document.getElementById("input_file");
		
		if(input_file != null){ 
			
			input_file.addEventListener("change", ()=>{
				
				checkEXT();
				
				const maxsize = 2*1024*1024;
				let filesize = input_file.files[0].size ? input_file.files[0].size:0;
				
				console.log("이미지 크기 : "+ filesize);
			
				//파일 크기 체크
				if(maxsize < filesize){
					modal.style.display = "flex";
					input_file.value="";
					return false;
				}else{
					let filesize_KB = Math.round(filesize/1024);
					input_file_span.innerHTML =  "&nbsp;("+filesize_KB+"&nbsp;KB)";
				}

			});
			
			
			const modal_btn = document.getElementById('btn');
			modal_btn.addEventListener("click", ()=>{
				modal.style.display = "none";
				input_file.focus();			
			});
		}
}

//window.onload -> input_file 변화시에 쓰이는 확장자 체크 함수
function checkEXT(){
	const extOK = ["jpg","jpeg","png","gif","psd","pdd","tif","ai","eps"];
	const filenameSplit = input_file.value.split('\.');
	let extEnd = filenameSplit[filenameSplit.length-1];

			if(extOK.indexOf(extEnd) != -1){
			}else if(input_file != ""){
			}else{
				alert('지원하지 않은 확장자. 가능한 확장자는 '+extOK+' 입니다.');
				input_file.value="";
				input_file.focus();
			}
}



//페이지 상세보기 삭제input태그

function clickDelBtn(systemfilename){
	const urlParmas = new URL(location.href).searchParams;
	const list = urlParmas.get("list");
	
	const ans = confirm('정말 삭제하시겠습니까?');
	
	if(!ans)	{
		return false;	
	}
		location.href="/delete.do?list="+list+"&systemfilename="+systemfilename;
	return true;
}

