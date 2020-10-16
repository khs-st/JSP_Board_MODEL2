<%@page import="com.kb.www.vo.ArticleVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.kb.www.common.loginmanager.LoginManager"%>
<%
	ArticleVO vo2 = (ArticleVO) request.getAttribute("article");
LoginManager lm = LoginManager.getInstance();
String id = lm.getMemberId(session);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일상 게시판</title>
<!-- 애니매이션 담당 JQUERY -->
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<!-- 부트스트랩 JS  -->
<script src="js/bootstrap.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/customs.css">
<script src="js/custom.js"></script>
</head>
<body>
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
				<!-- <--- 현재 접속 페이지가 메인이란 걸 알려줌 -->
				<li class="active"><a href="/list.do">게시판</a></li>
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
						<li><a href="/memberinfo.do">회원정보수정</a></li>
						<li><button onclick="confirm_leave()">회원탈퇴</button></li>
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
						<td style="background-color: #b7b7ff; text-align: center;">번호</td>
						<td style="background-color: #b7b7ff; text-align: center;">작성자</td>
						<td style="background-color: #b7b7ff; text-align: center;">제목</td>
						<td style="background-color: #b7b7ff; text-align: center;">내용</td>
						<td style="background-color: #b7b7ff; text-align: center;">조회수</td>
						<td style="background-color: #b7b7ff; text-align: center;">작성날짜</td>
					</tr>
					<tr>
						<td><%=vo2.getArticleNum()%></td>
						<td><%=vo2.getId()%>
						<td><%=vo2.getArticleTitle()%></td>
						<td><%=vo2.getArticleContent()%></td>
						<td><%=vo2.getHit()%></td>
						<td><%=vo2.getWriteDate()%></td>
					</tr>
				</tbody>
			</table>
		</div>
		<button class="btn btn-primary pull-right"
			onclick="location.href='/list.do'">뒤로가기</button>
		<%
			if (id != null && id.equals(vo2.getId())) {
		%>
		<button class="btn btn-primary pull-right"
			onclick="location.href='/update.do?num=<%=vo2.getArticleNum()%>'">수정</button>
		<button class="btn btn-primary pull-right"
			onclick="location.href='/delete.do?num=<%=vo2.getArticleNum()%>'">삭제</button>
		<%
			}
		%>
	</div>
</body>
</html>