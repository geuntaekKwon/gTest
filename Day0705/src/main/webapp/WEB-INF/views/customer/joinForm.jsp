<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	span#msg {
		color : #ff0000;
	}
</style>
<script>
/* 유효성 검사 */
window.onload = function() {
	var custid    = document.getElementById("custid");
	var password  = document.getElementById("password");
	var password2 = document.getElementById("password2");
	var name      = document.getElementById("name");
	var idno      = document.getElementById("idno");
	
	document.getElementById("idCheck").onclick=function() {
		window.open("idCheck.jsp", "win", "width=400, height=300");
	};
	document.getElementById("send_btn").onclick=check;

};
function check() {
	if(custid.value.length < 3 || custid.value.length > 10) {
		if(custid.value.charAt(0)< 'a'|| custid.value.charAt(0)>'z') {
			alert("아이디는 3~10자리의 영문자 소문자여야 합니다.");
			return false;
		}
	}
	return true;
}
</script>

</head>
<body>
<div id="wrapper">
	<h2>[ 회원 가입 ]</h2>
	<form action="joinForm" method="POST">
	<table border="1">
		<tr>
			<th>아이디</th>
			<td>
				<input type="text" id="custid" name="custid" placeholder="3~10자리 아이디"/>
				<input type="button" id="idCheck" value="아이디 중복확인" />
			</td>	
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>
				<input type="password" id="password" name="password" placeholder="3~10자리 비밀번호" />
			</td>	
		</tr>
		<tr>
			<th>비밀번호 확인</th>
			<td>
				<input type="password" id="password2" />
			</td>	
		</tr>
		<tr>
			<th>이름</th>
			<td>
				<input type="text" id="name" name="name" />
			</td>	
		</tr>
		<tr>
			<th>E-Mail</th>
			<td>
				<input type="email" id="email" name="email" />
			</td>	
		</tr>	
		<tr>
			<th>회원구분</th>
			<td>
				<input type="radio" id="division" name="division" value="personal" checked="checked"/>개인회원 &nbsp;
				<input type="radio" id="division" name="division" value="company" />기업회원
			</td>	
		</tr>
		<tr>
			<th>식별번호</th>
			<td>
				<input type="text" id="idno" name="idno"  />
				<span id="msg">개인회원 : 주민번호 / 기업회원 : 사업자등록번호</span>
			</td>	
		</tr>
		<tr>
			<th>주소</th>
			<td>
				<input type="text" id="address" name="address"  size="50"/>
			</td>	
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" id="send_btn" value="가입" />
				<input type="reset" value="지우기" />
				<a href="../index.jsp" >돌아가기</a>	
			</td>	
		</tr>				
		</table>
	</form>
</div>
</body>
</html>
