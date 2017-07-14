<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	div#wrapper {
		width : 800px;
		margin : 0 auto;
		text-align : center;
	}
	
	ul{
		list-style-type: none;
	}
</style>
<script>
window.onload = function() {
	document.getElementById("delete").onclick = deleteCustomer;
}

function deleteCustomer() {
	var answer = confirm("정말로 탈퇴하시겠습니까?");
	alert(answer);
	if(answer) {
		
		location.href="${pageContext.request.contextPath}/customer/delete";
		
	}
	return false;
}
</script>
</head>
<body>
<div id="wrapper">
	<h2>[ SEBank ]</h2>
	<ul>
		<c:if test="${empty loginId }">
			<li><a href="joinForm" >회원가입</a></li>
			<li><a href="login" >로그인</a></li>
		</c:if>
		<c:if test="${not empty loginId }">
			<li><a href="logout" >${name}님, 로그아웃</a></li>
			<li><a href="update" >개인정보수정</a></li>
			<li><a id="delete" href="#">탈퇴</a></li>
		</c:if>
		
		<li><a href="boardList" >게시판</a></li>
		
	</ul>
	<img alt="첫화면" src="resources/images.jpg">
</div>
</body>
</html>