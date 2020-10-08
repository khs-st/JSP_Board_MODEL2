<%@page import="com.kb.www.vo.ArticleVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%
	ArrayList<ArticleVO> list = (ArrayList<ArticleVO>) request.getAttribute("list");
System.out.println(list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일상 게시판</title>
<script src="https://code.jquery.com/jquery-1.11.3.js"
	type="text/javascript"></script>
<script type="text/javascript">
	function ShowDetail(articleNum) {
		location.href = "detail.do?num=" + articleNum;
	}

	$(document).ready(function() {
		$('tr').hover(function() {
			$(this).css('color', 'blue');
		}, function() {
			$(this).css('color', 'black');
		});
	});
	function searchArticle(){
		var filter=$('#filter option:selected').val();
		var keyword=$('#keyword').val();
		location.href="/list.do?pn=1&filter="+fliter
				+"&keyword"+keyword;
	}
</script>
<link rel="stylesheet" href="css/custom.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/custom.css">
</head>
<body>
	<%
		String id = null;
	if (session.getAttribute("id") != null) {
		id = (String) session.getAttribute("id");
	}
	%>
	<nav class="navbar navbar-default">

		<div class="navbar-header">

			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expaned="false">

				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>

			</button>

			<a class="navbar-brand" href="index.jsp">kobalja의 게시판</a>

		</div>

		<div class="collapse navbar-collapse"
			id="#bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="index.jsp">메인</a></li>
				<!-- <--- 현재 접속 페이지가 메인이란 걸 알려줌 -->
				<li><a href="/list.do">게시판</a></li>
				<li><a href="index.jsp">공지사항</a></li>
				<li><a href="/list.do">1:1 문의</a></li>
			</ul>
			<%
				if (id == null) {
			%>
			<ul class="nav navbar-nav navbar-right">

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">접속하기<span class="caret"></span></a>

					<ul class="dropdown-menu">

						<li><a href="/login.do">로그인</a></li>
						<li><a href="/join.do">회원가입</a></li>

					</ul></li>
			</ul>
			<%
				}
			if (id != null) {
			%>
			<ul class="nav navbar-nav navbar-right">

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"><%=id%>님<span class="caret"></span></a>

					<ul class="dropdown-menu">
						<li><a href="/logout.do">로그아웃</a></li>
						<li><a href="/updateinfo.do">회원정보수정</a></li>
						<%
							}
						if (id != null && id.equals("admin")) {
						%>
						<li><a href="/history.do">회원히스토리</a></li>
						<%
							}
						%>
					</ul></li>
			</ul>
		</div>

	</nav>
	<!-- 검색기능 -->
	<select name="filter" id="filter">
		<option value="subject" selected>제목</option>
		<option value="contents">내용</option>
	</select>
	<input type="text" name="keyword" id="keywrod">
	<button onclick="">검색</button>
	<!-- 게시판 화면 -->
	<div class="container">
		<div class="row">
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="5"
							style="background-color: #eeeeee; text-align: center;">게시판
							목록</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="background-color: #b7b7ff; text-align: center;">번호</td>
						<td style="background-color: #b7b7ff; text-align: center;">제목</td>
						<td style="background-color: #b7b7ff; text-align: center;">조회수</td>
						<td style="background-color: #b7b7ff; text-align: center;">작성자</td>
						<td style="background-color: #b7b7ff; text-align: center;">작성날짜</td>
					</tr>
					<%
						if (list.size() > 0) {
						for (int i = 0; i < list.size(); i++) {
					%>
					<tr onclick="ShowDetail(<%=list.get(i).getArticleNum()%>)">
						<td><%=list.get(i).getArticleNum()%></td>
						<td><%=list.get(i).getArticleTitle()%></td>
						<td><%=list.get(i).getHit()%></td>
						<td><%=list.get(i).getId()%>
						<td><%=list.get(i).getWriteDate()%></td>
					</tr>
					<%
						}
					} else {
					%>
					<tr>
						<td colspan="3">게시글이 없습니다.</td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>