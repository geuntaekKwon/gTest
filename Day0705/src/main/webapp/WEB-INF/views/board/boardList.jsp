<%@page import="sesoc.global.webTest.vo.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	body {
		font-family : sans-serif;
		font-size : 0.9em;
	}
	input[type=submit] {
		border : none;
	}
	div#wrapper {
		width:900px;
		margin : 0 auto;
		text-align : center;
	}
	table {
		width : 900px;
	}
	.title {
		width : 450px;
	}
	th {
		background : #efefef;
	}
	.write {
		text-align : right;
	}
	.home {
		text-align : left;
	}
		a {
		display : inline-block;
	}
	a, a:link, a:visited, a:active {
		text-decoration : none;
	}
	a:hover {
		font-weight:bolder;
		color : #F00;
	}
	.btn {
		text-align : center;
		border-radius : 0.5em;
		width : 50px;
		margin-top : 10px;
		padding : 5px;
		background : #eee;
		
	}
	input[type=submit]:hover{
		font-weight:bold;
	}
	#search {
		text-align:right;
	}
</style>
</head>
<body>
<div id="wrapper">

<h2>[ 게시판 글 목록 ]</h2>
<div class="home">
	<a href="${pageContext.request.contextPath}/"><img src="resources/images.jpg" /></a>

	<!-- 특정 글 검색 -->
	<form id="search" action ="boardList" method="POST" >
	<select name="searchtype">
		<option value="title">제목</option>
		<option value="custid">작성자</option>
		<option value="content">내용</option>
	</select>
	<input type="text" name="searchword" /> 
	<input class="btn" type="submit" value="검색" />
	</form>
</div>
<!-- 게시글 목록 시작 -->
<table border='1'>
	<tr>
		<th>번호</th>
		<th class="title">글제목</th>
		<th>글쓴날</th>
		<th>글쓴이</th>
		<th>조회수</th>
	</tr>
	
	<!-- 게시글 출력 -->
	<c:forEach var="board" items="${boardList}" varStatus="stat">
	<tr>
		<td>${stat.count}</td>
		<td class="title">
			<c:if test="${board.originalfile ne null}">
				<img src="resources/images.jpg">
			</c:if>
			<a href="boardDetail?boardnum=${board.boardnum}&currentPage=${navi.currentPage}&searchtype=${searchtype}&searchword=${searchword}">${board.title}</a>
		</td>
		<td>${board.inputdate}</td>
		<td>${board.custid}</td>
		<td>${board.hits}</td>
	</tr>	
	</c:forEach>
</table>
<div class="write"><a class="btn" href="boardWrite">글쓰기</a></div>

<!-- Paging 출력 부분 -->
<div id="navigator">

	<a href="boardList?currentPage=${navi.currentPage - navi.pagePerGroup}&searchword=${searchword}&searchtype=${searchtype}">◁◁</a>
	<a href="boardList?currentPage=${navi.currentPage - 1}&searchword=${searchword}&searchtype=${searchtype}">◀</a>
		<c:forEach var="page" begin="${navi.startPageGroup}" end="${navi.endPageGroup}">
			<c:if test="${navi.currentPage eq page}">
				<span style="color: blue; font-weight: bolder; font-size: 2em;">${page}</span>
			</c:if>
			<c:if test="${navi.currentPage ne page}">
	 			<a href="boardList?currentPage=${page}&searchword=${searchword}&searchtype=${searchtype}">${page}</a>
			</c:if>
		</c:forEach>
		&nbsp;&nbsp;
	<a href="boardList?currentPage=${navi.currentPage + 1}&searchword=${searchword}&searchtype=${searchtype}">▶</a>
	<a href="boardList?currentPage=${navi.currentPage + navi.pagePerGroup}&searchword=${searchword}&searchtype=${searchtype}">▷▷</a>

</div>
<hr>
</body>
</html>





