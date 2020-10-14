<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="com.kb.www.common.loginmanager.LoginManager"%>
<%
	LoginManager lm = LoginManager.getInstance();
String id = lm.getMemberId(session);
%>
<html>
<meta charset="UTF-8">
<head>
<title>회원정보수정</title>
</head>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap"
	rel="stylesheet">
<!-- 애니매이션 담당 JQUERY -->
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/customs.css">
<!-- 부트스트랩 JS  -->
<script src="js/bootstrap.js"></script>
<script>
	function validateCheck() {
		var pw = $('#pw').val();
		var pw_cofonrim = $('#pw_confirm').val();
		var mb_name = $('#mb_name').val();
		var mb_email = $('#mb_email').val();

		if (!pw) {
			alert("비밀번호를 입력해주세요.");
			$('#pw').focus();
			return false;
		}
		if (!mb_email) {
			alert("이메일을 입력해주세요.");
			$('#mb_email').focus();
			return false;
		}
		if (!pw_cofonrim) {
			alert("비밀번호확인을 입력해주세요.");
			$('#pw_confirm').focus();
			return false;
		}
		if (!pw_cofonrim) {
			alert("비밀번호가 다릅니다.");
			$('#pw').val("");
			$('#pw_confirm').val("");
			$('#pw').focus();
			return false;
		}

		//비밀번호 길이, 영,소문자 체크(정규표현식)
		var regExpPw = new RegExp("^[a-z0-9]{4,30}$", "g");
		//비밀번호로 실행
		if (regExpPw.exec(pw) == null) {
			alert("잘못된 비밀번호 형식입니다.");
			$('#pw').val("");
			$('#pw_confirm').val("");
			$('#pw').focus();
			return false;
		}
		//이메일 길이, 영,소문자 체크(정규표현식)
		var regExpEmail = new RegExp(
				"^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$",
				"g");
		//이메일로 실행
		if (regExpEmail.exec(mb_email) == null) {
			alert("잘못된 이메일 형식입니다.");
			$('#mb_email').val("");
			$('#mb_email').focus();
			return false;
		}
		//이름 길이, 영,소문자 체크(정규표현식)
		var regExpName = new RegExp(
				"^[가-힣]{2,4}|[a-zA-Z]{2,10}\\s[a-zA-Z]{2,10}$", "g");
</script>

<body>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expaned="false">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/index.jsp">kobalja의 게시판</a>
		</div>

		<div class="collapse navbar-collapse"
			id="#bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="/index.jsp">메인</a></li>
				<!-- <--- 현재 접속 페이지가 메인이란 걸 알려줌 -->
				<li><a href="/list.do">게시판</a></li>
				<li><a href="/index.jsp">공지사항</a></li>
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
						<li class="active"><a href="/join.do">회원가입</a></li>

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
						<li class="active"><a href="/memberinfo.do">회원정보수정</a></li>
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
	<div style="width: 750px;" class="container">
		<form action="/memberinfoPro.do" method="post" id="joinForm">
			<h3 style="text-align: center;">회원정보수정</h3>
			<div class="form-group">
				<label for="id">아이디 수정</label>
				<div class="form-group">
					<input type="text" class="form-control" placeholder="아이디" name="id"
						id="id" maxlength="20" oninput="initCheckId()">
				</div>
			</div>
			<div class="form-group">
				<label for="pw">비밀번호 수정</label> <input type="password"
					class="form-control" placeholder="비밀번호" name="pw" id="pw"
					maxlength="20">
			</div>
			<div class="form-group">
				<label for="pw_confirm">비밀번호 수정 확인</label> <input type="password"
					class="form-control" placeholder="비밀번호확인" name="pw_confirm"
					id="pw_confirm" maxlength="20">
			</div>
			<div class="form-group">
				<label for="email">이메일</label> <input type="email"
					class="form-control" placeholder="이메일" id="mb_email"
					name="mb_email" maxlength="100">
			</div>
			<div class="form-group">
				<input type="button" class="btn btn-primary form-control"
					value="회원정보수정" onclick="validateCheck()">
			</div>
			<div class="form-group">
				<button type="button" class="btn btn-primary form-control"
					onclick="location.href='/index.jsp'">취소</button>
			</div>
		</form>
	</div>
</body>
</html>
