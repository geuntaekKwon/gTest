<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		input#content{
			width: 600px;
			height: 200px;
		}
	</style>
</head>
<body>
	<div id="wrapper">
	<h2>[ 게시글 수정 ]</h2>
		<form action="boardUpdate?boardnum=${board.boardnum}" method="POST" enctype="multipart/form-data">
			<table border="1">
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" value = "${board.title}" /></td>
				</tr>
				<tr>
					<th>글쓴이</th>
					<td>${loginId}</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><input type="file" name="upload" />${board.originalfile}</td>
				</tr>
				<tr>
					<th>글내용</th>
					<td><input type="text" value="${board.content}" name="content" id="content"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="글수정" />
						<a href="boardList" >목록으로</a> <!-- 매번 목록을 계속 가져오기 위해서 웹주소명을 쓴다. -->
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>