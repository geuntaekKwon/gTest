<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
		pre{
			width : 600px;
			height : 200px;
			overflow : auto;		
		}
		div#wrapper table {
			margin:0 auto;
		}
	</style>
</head>
<script type="text/javascript">
	function whenUpdate(replynum, text){
		document.getElementById("submit_btn").value = "수정완료";
		document.getElementById("text").value = text;
		
		document.getElementById("submit_btn").onclick = function(){
			if(document.getElementById("submit_btn").value == "수정완료"){
				var updatedText = document.getElementById("text").value;
				location.href = "replyUpdate?replynum="+ replynum +"&boardnum=${board.boardnum}&text=" + updatedText;
			}//if
		}//function
	}//whenUpdate
	
	function replyWrite(){
		//var updatedtext = document.getElementById("text").value;
		document.getElementById("replyWrite").submit();
	}//whenSubmitButtonCliecked
	
	function deleteClick(){
		del_form = document.getElementById("fm").action = 'boardDelete?boardnum=${board.boardnum}';
		document.getElementById("fm").submit();
	}//deleteClick
	
</script>
<body>
	<div id="wrapper">
	<h1> [ 기만자 권근택 ]</h1>
		<form action="boardUpdate" method="get" id="fm">
			<input type="hidden" name="boardnum" value="${board.boardnum}"><!-- hidden : 우리 눈에 보이지는 않지만 사용자 몰래 정보 일부를 가지고 갈 때 -->
			<table border="1">
				<tr>
					<th>제목</th>
					<td>${board.title}</td>
				</tr>
				<tr>
					<th>글쓴이</th>
					<td>${board.custid}</td>
				</tr>
				<tr>
					<th>작성날짜</th>
					<td>${board.inputdate}</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td>
					
						<%-- image/jpeg , image/gif, image/png 이런식으로 넘어옴. 그리고 이 image를 '마임타입' 이라고 함..
						 --%>
						 <c:if test="${contenttype eq 'image'}">
						 	<img src="download?boardnum=${board.boardnum}">
						 </c:if>
						 
					
						<c:if test="${board.originalfile ne null}">
							<a href="download?boardnum=${board.boardnum}">${board.originalfile}</a>
						</c:if>
						
					</td>
				</tr>
				<tr>
					<th>글내용</th>
					<td>
						<pre>${board.content}</pre> <!-- 내가 쓴 글 그대로 점프랑 모두 다 -->
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="글수정" />
						<input type="button" value="삭제" id="del_board" onclick="deleteClick();"/>
						<a href="boardList?currentPage=${currentPage}&searchtype=${searchtype}&searchword=${searchword}" >목록으로</a> <!-- 매번 목록을 계속 가져오기 위해서 웹주소명을 쓴다. -->
					</td>
				</tr>
			</table>
		</form>
		<!-- 댓글입력 : 로그인한 사람만 쓸 수 있음 -->
		<c:if test="${not empty loginId}">
			<form id="replyWrite" action="replyWrite?boardnum=${board.boardnum}" method="POST">
				<table>
					<tr>
						<td>
							<input type="text" name ="text" id = "text">
							<input type="button" name ="댓글입력" value = "댓글입력" id = "submit_btn" onclick = "replyWrite();">
						</td>
					</tr>
				</table>
			</form>
		</c:if><!-- 댓글 입력부분 -->
		
		<!-- 댓글 출력 부분 -->
		<div class="replydisply">
			<table>
				<c:forEach var = "reply" items = "${replyList}">
					<tr>
						<td class = "replytext">
							${reply.text}
						</td>
						<td class = "replyid">
							${reply.custid}
						</td>
						<td class = "replydate">
							${reply.inputdate}
						</td>
						<c:if test="${reply.custid == custid}">
							<td class = "replybtn">
								<input type = "button" value = "수정" onclick="whenUpdate('${reply.replynum}','${reply.text}')">
								<form action="replyDelete?replynum=${reply.replynum}&boardnum=${board.boardnum}" method = "POST">
									<input type = "submit" value = "삭제" id = "del_re">
								</form>
							</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
		</div>
		
		
	</div><!-- end #wrapper -->
</body>
</html>