<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.ArrayList"%>

<%@ page import="com.kb.www.vo.MemberHistoryVO"%>
<%@ page import="com.kb.www.common.parser.Parser"%>
<%@ page import="com.kb.www.pagenation.Pagenation"%>
<%@page import="com.kb.www.common.loginmanager.LoginManager"%>
<%
ArrayList<MemberHistoryVO> list = (ArrayList<MemberHistoryVO>) request.getAttribute("list");
LoginManager lm = LoginManager.getInstance();
String id = lm.getMemberId(session);
Pagenation pagenation = (Pagenation) request.getAttribute("pagenation");
String nowPage = request.getParameter("pn");
%>
<html>
<head>
<meta charset="UTF-8">
<title>회원 히스토리페이지</title>
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
	function searchArticle() {
		var filter = $('#filter option:selected').val();
		var keyword = $('#keyword').val();
		location.href = "/history.do?pn=1&filter=" + filter + "&keyword="
				+ keyword;
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
						<li class="active" onclick="confirm_leave()"><a>회원탈퇴</a></li>
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
	<div class="container">
		<div class="row">
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="5"
							style="background-color: #eeeeee; text-align: center;">회원
							히스토리 목록</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="background-color: #b7b7ff; text-align: center;">구분</td>
						<td style="background-color: #b7b7ff; text-align: center;">발생일시</td>
						<td style="background-color: #b7b7ff; text-align: center;">이름</td>
						<td style="background-color: #b7b7ff; text-align: center;">이메일</td>
						<td style="background-color: #b7b7ff; text-align: center;">작성날짜</td>
					</tr>
					<%
						for (int i = 0; i < list.size(); i++) {
					%>
					<tr>
						<td><%=Parser.parseMemberEventType(list.get(i).getEvt_type())%>
						</td>
						<td><%=list.get(i).getDttm()%></td>
						<td><%=list.get(i).getName()%></td>
						<td><%=list.get(i).getEmail()%></td>
						<td><%=list.get(i).getGender()%></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
			<form action="/index.jsp">
				<input type="submit" class="btn btn-primary pull-right"
					value="홈으로 이동">
			</form>
		</div>
	</div>
</body>
</html>
