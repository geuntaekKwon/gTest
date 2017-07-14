<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	div#wrapper{
		width : 800px;
		margin : 0 auto;
		
	}
	div#wrapper>h2 {
		text-align : center;
	}
	th {
		width: 100px;
	}
	td {
		width:600px;
	}
	div#wrapper table {
		margin:0 auto;
	}
</style>
</head>
<body>
<div id="wrapper">
<h2>[ 게시판 글쓰기 ]</h2>
<form action="boardWrite" method="POST" enctype="multipart/form-data">
	<table border="1">
	<tr>
		<th>제목</th>
		<td><input type="text" name="title" required/></td>
	</tr>
	<tr>
		<th>글쓴이</th>
		<td>${loginId}</td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td><input type="file" name="upload" /></td>
	</tr>
	<tr>
		<th>글내용</th>
		<td><textarea name="content" cols="60" rows="10"></textarea></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="글등록" />
			<input type="reset" value="취소" />
			<a href="boardList" >목록으로</a>
		</td>
	</tr>
	</table>
</form>
</div>
</body>
</html>