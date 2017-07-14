<%@page import="sesoc.global.webTest.vo.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
window.onload=function() {
	document.getElementById("idCheck").onclick=function() {
		var custid = document.getElementById("custid");
		
		if(!(custid.value.charAt(0) >= 'a' && custid.value<= 'z')) {
			alert("아이디는 3~10자리 영문자 소문자로 시작해야 합니다.");
			return;
		}
		if(!(custid.value.length >=3 && custid.value.length <= 10)) {
			alert("아이디는 3~10자리 영문자 소문자로 시작해야 합니다.");
			return;
		}
		var checkForm = document.getElementById("checkForm");
		checkForm.submit();
	};
	document.getElementById("idSelect").onclick=function() {
		
	}
};
function idSelect(id) {
	opener.document.getElementById("custid").value=id;
	this.close();
}
</script>
</head>
<body>
<div>
	<h2>[ 아이디 중복확인 ] </h2>
	<form id="checkForm" action="idCheck" method="POST">
		<input type="text" id="custid" name="custid" placeholder="3~10아이디 입력" />
		<input id="idCheck" type="button" value="중복확인" />  
	</form>
</div>
<div id="result">
<%
	if(!(request.getMethod().equals("POST"))) return;

	Customer customer = (Customer)request.getAttribute("customer");
	String id = (String)request.getAttribute("id");
	if(customer == null) { %>
		<div>${id} 는 사용할 수 있는 아이디</div>
		<input  type="button" value="ID사용하기" onclick="idSelect('${id}')">
	<%} else { %>
		<div>${id} 는 사용할 수 없는 아이디입니다.</div>
	<% } %>
</div>
</body>
</html>





















