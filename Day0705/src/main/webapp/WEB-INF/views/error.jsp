<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
		<!--  -->
	<meta http-equiv="refresh" content="5;${pageContext.request.contextPath }"><!-- 5초 지난 뒤 홈 페이지로 -->
	<title>Insert title here</title>
	<style type="text/css">
		#wrapper{
			width : 700;
			height: auto;
			text-align : center;
			margin : 0 auto;		
		}
	</style>
</head>
<body>
	<div id="wrapper">
		<h2>[ 오류 발생 ]</h2>
		<img src="resources/error.png">
		<p>5초 뒤에 첫 화면으로 이동합니다. 잠시 후 다시 이용해주세요</p>
		<p>오류 메세지 : ${errormsg}</p>
	</div>
</body>
</html>