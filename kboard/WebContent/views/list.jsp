<%@page import="com.kb.www.vo.ArticleVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="com.kb.www.pagenation.Pagenation"%>
<%@page import="com.kb.www.common.loginmanager.LoginManager"%>

<%
	ArrayList<ArticleVO> list = (ArrayList<ArticleVO>) request.getAttribute("list");
LoginManager lm = LoginManager.getInstance();
String id = lm.getMemberId(session);
Pagenation pagenation = (Pagenation) request.getAttribute("pagenation");
String nowPage = request.getParameter("pn");
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
<link rel="stylesheet" href="css/custom.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/custom.css">
<!-- 페이지네이션을 위한 script 구문 -->
<script>
        function goDetail(num) {
            location.href =
                "/detail.do?pn=" + <%=nowPage%> +"&num=" + num;
        }

        function searchArticle() {
            var filter = $('#filter option:selected').val();
            var keyword = $('#keyword').val();
            location.href ="/list.do?pn=1&filter=" + filter + "&keyword=" + keyword;
        }
    </script>
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
				<li class="active"><a href="/list.do?pn=1">게시판</a></li>
				<li><a href="index.jsp">공지사항</a></li>
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
						<li onclick="confirm_leave()"><a>회원탈퇴</a></li>
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
		<!-- 검색기능 -->
		<div class=" pull-right" id="search" style="margin-bottom: 10px;">
			<select class="btn btn-primary" name="filter" id="filter">
				<option class="btn btn-primary" value="all" selected>전체</option>
				<option class="btn btn-primary" value="subject" selected>제목</option>
				<option class="btn btn-primary" value="content">내용</option>
			</select> <input type="text" name="keyword" id="keyword">
			<button class="btn btn-primary" onclick="searchArticle()">검색</button>
		</div>
		<!-- 게시물 목록 -->
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
						<td style="background-color: #b7b7ff; text-align: center;">작성자</td>
						<td style="background-color: #b7b7ff; text-align: center;">제목</td>
						<td style="background-color: #b7b7ff; text-align: center;">조회수</td>
						<td style="background-color: #b7b7ff; text-align: center;">작성날짜</td>
					</tr>
					<%
						if (list.size() > 0) {
					%>
					<%
						for (int i = 0; i < list.size(); i++) {
					%>
					<tr onclick="goDetail(<%=list.get(i).getArticleNum()%>)">
						<td><%=list.get(i).getArticleNum()%></td>
						<td><%=list.get(i).getId()%>
						<td><%=list.get(i).getArticleTitle()%></td>
						<td><%=list.get(i).getHit()%></td>
						<td><%=list.get(i).getWriteDate()%></td>
					</tr>
					<%
						}
					%>
					<%
						} else {
					%>
					<tr>
						<td colspan="5">게시글이 없습니다.</td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
		<div style="text-align: center; margin: auto;">

			<span> <%
 	if (pagenation.getStartPage() == 1) {
 %> <a href="/list.do?pn=<%=pagenation.getStartPage()%>"
				class="btn btn-success btn-arraw-left">이전</a> <%
 	} else {
 %> <a href="/list.do?pn=<%=pagenation.getStartPage() - 1%>"
				class="btn btn-success btn-arraw-left">이전</a> <%
 	}
 %>
			</span>
			<%
				for (int i = pagenation.getStartPage(); i <= pagenation.getEndPage(); i++) {
			%>
			<span> <a href="/list.do?pn=<%=i%>"> <%=i%>
			</a>
			</span>
			<%
				}
			%>
			<span> <a href="/list.do?pn=<%=pagenation.getEndPage() + 1%>"
				class="btn btn-success btn=arraw-left">다음</a>
			</span>
		</div>
		<form action="/write.do">
			<input type="submit" class="btn btn-primary pull-right" value="글쓰기">
		</form>
	</div>
	<%
		System.out.println(pagenation.getStartPage());
	System.out.println(pagenation.getStartPage());
	System.out.println(pagenation.getEndPage());
	%>
</body>
</html>