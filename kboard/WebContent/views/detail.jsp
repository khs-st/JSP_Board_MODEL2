<%@page import="com.kb.www.vo.ArticleVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%
	ArticleVO vo2 = (ArticleVO) request.getAttribute("article");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일상 게시판</title>
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
				<li><a href="index.jsp">메인</a></li>
				<li class="active"><a href="list.do">게시판</a></li>
				<li><a href="index.jsp">공지사항</a></li>
				<li><a href="list">1:1 문의</a></li>
			</ul>
			<%
				if (id == null) {
			%>
			<ul class="nav navbar-nav navbar-right">

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">접속하기<span class="caret"></span></a>

					<ul class="dropdown-menu">

						<li><a href="login.do">로그인</a></li>
						<li><a href="join.do">회원가입</a></li>

					</ul></li>
			</ul>
			<%
				} else {
			%>
			<ul class="nav navbar-nav navbar-right">

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">회원관리<span class="caret"></span></a>

					<ul class="dropdown-menu">
						<li><a href="logoutAction.jsp">로그아웃</a></li>
					</ul></li>
			</ul>
			<%
				}
			%>
		</div>
	</nav>
	<!-- 게시판 화면 -->
	<div class="container">
		<div class="row">
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="6"
							style="background-color: #eeeeee; text-align: center;">게시판
							목록</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="background-color: #b7b7ff; text-align: center;">작성자</td>
						<td style="background-color: #b7b7ff; text-align: center;">번호</td>
						<td style="background-color: #b7b7ff; text-align: center;">제목</td>
						<td style="background-color: #b7b7ff; text-align: center;">내용</td>
						<td style="background-color: #b7b7ff; text-align: center;">조회수</td>
						<td style="background-color: #b7b7ff; text-align: center;">작성날짜</td>
					</tr>
					<tr>
						<td><%=vo2.getId()%>
						<td><%=vo2.getArticleNum()%></td>
						<td><%=vo2.getArticleTitle()%></td>
						<td><%=vo2.getArticleContent()%></td>
						<td><%=vo2.getHit()%></td>
						<td><%=vo2.getWriteDate()%></td>
					</tr>
				</tbody>
			</table>
		</div>
		<button  class="btn btn-primary pull-right"  onclick="location.href='/list.do'">뒤로가기</button>
		<%
			if (id != null && id.equals(vo2.getId())) {
		%>
		<button  class="btn btn-primary pull-right" 
			onclick="location.href='/update.do?'">수정</button>
		<button  class="btn btn-primary pull-right" 
			onclick="location.href='/delete.do'">삭제</button>
		<%
			}
		%>
	</div>
</body>
</html>