<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="bootstrapTaglib.jsp" %>
<%@ include file="modal.jsp" %>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="css/all.css">
	<link rel="stylesheet" type="text/css" href="css/modal.css">
	<link rel="stylesheet" type="text/css" href="css/newWrite.css">
	<script src="js/newWrite.js"></script>
<title>Insert title here</title>
</head>
<body>

	<h1>수정</h1>
	<hr>
	
	<div id="all_body_div">
		<form action="modi.do" method="post" name="newWrite_form" id="modiForm" enctype="multipart/form-data">
		
		<c:forEach items="${detail}" var ="detail">
		<input type="hidden" name="list" value="${detail.bid }">	 
		<table id="newWriteTable">
			<tr>
				<th id="newWrite_th">구분(필수)</th>
				<td id="newWriteTable_td">
					<select name="boardtype" class="form-select" required>
						<option value="" >선택해주세요</option>
						<option value="유지보수" ${detail.boardtype=="유지보수"? "selected":""}>유지보수</option>
						<option value="문의사항" ${detail.boardtype=="문의사항"? "selected":""}>문의사항</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>작성자(필수)</th>
				<td id="newWriteTable_td">
					<input name="username" value="${detail.username}" disabled class="form-control">
				</td>
			</tr>
			<tr>
				<th>분류(필수)</th>
				<td id="newWriteTable_td">
					<input type="radio" name="boardcategory" id="radio_1" value="홈페이지" class="form-check-input" ${detail.boardcategory == "홈페이지"? "checked":""} >
					<label for="radio_1">홈페이지</label>
					
					<input type="radio" name="boardcategory" id="radio_2" value="네트워크" class="form-check-input" ${detail.boardcategory == "네트워크"? "checked":""}>
					<label for="radio_2">네트워크</label>
					
					<input type="radio" name="boardcategory" id="radio_3" value="서버" class="form-check-input" ${detail.boardcategory == "서버"? "checked":""}>
					<label for="radio_3">서버</label>
				</td>
			</tr>
			<tr>
				<th>고객유형</th>

				<td id="newWriteTable_td">
						<c:set value="${detail.usertype }" var="usertypeArr"/>
						
								<input type="checkbox" name="usertype" id="check_1" value="호스팅" class="form-check-input" <c:if test="${fn:contains(usertypeArr,'호스팅')}">checked</c:if>>
								<label for="check_1" >호스팅</label>
								
								<input type="checkbox" name="usertype" id="check_2" value="유지보수" class="form-check-input" <c:if test="${fn:contains(usertypeArr,'유지보수') }">checked</c:if>>
								<label for="check_2" >유지보수</label>
							
								<input type="checkbox" name="usertype" id="check_3" value="서버임대" class="form-check-input" <c:if test="${fn:contains(usertypeArr,'서버임대') }">checked</c:if>>
								<label for="check_3" >서버임대</label>

								<input type="checkbox" name="usertype" id="check_4" value="기타" class="form-check-input" <c:if test="${fn:contains(usertypeArr,'기타') }">checked</c:if>>
								<label for="check_4" >기타</label>
				</td>
			</tr>
			<tr>
				<th>제목(필수)</th>
				<td id="newWriteTable_td">
					<input name="title" value="${detail.title }" class="form-control" required>
				</td>
			</tr>
			<tr>
				<th>내용(필수)</th>
				<td id="newWriteTable_td">
					<textarea name="content" rows="10" cols="" class="form-control" required>${detail.content }</textarea>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td id="newWriteTable_td">
					<input type="file" id="input_file" name="realfilename" accept="*" 
						class="form-control" title="💡최대용량은 2MB 입니다. " > <span id="span"></span>
					
					<input type="hidden" name="systemfilename" value="${detail.systemfilename }">
					<input type="hidden" name="systemfilename_copy" value="${detail.systemfilename }">
					
					&nbsp;&nbsp; ${empty detail.realfilename == true? '':detail.realfilename }
					<c:if test="${not empty detail.realfilename }">
						<button type="submit" formmethod="post" formaction="fdel.do" form="modiForm"  class="btn btn-secondary btn-sm"
								onclick="return confirm('업로드 파일을 삭제하시겠습니까? 영구삭제 됩니다.');" id="uploadDel_btn">  
							삭제
						</button>
					</c:if>
					<div id="imgPreview"></div>
				</td>
			</tr>
		</table>
		
		</c:forEach>
		
			<div id="newWrite_button_div">
<!-- 				<input type="submit" value="저장" onclick="checkinput();" class="btn btn btn-primary" > -->
				<input type="submit" value="저장" onclick="checkEXT();" class="btn btn btn-primary" >
				<input type="button" value="취소" onclick="location.replace('list')" class="btn btn btn-primary" >
			</div>
		</form>
	</div>



</body>
</html>