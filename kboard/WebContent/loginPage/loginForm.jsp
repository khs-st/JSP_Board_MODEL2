<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<meta charset="UTF-8">
<head>
<title>회원가입</title>
</head>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script>
	function validateCheck() {
		var id = $('#id').val();
		var p = $('#pw').val();
		if (!id) {
			alert("아이디를 입력해주세요.");
			$('#id').focus();
			return false;
		}
		if (!pw) {
			alert("아이디를 입력해주세요.");
			$('#pw').focus();
			return false;
		}
	}
</script>

<body>
	<form action="/loginProc.do" method="post"
		onsubmit="return validateCheck()">
		아이디: <input type="text" name="id" id="id"><br /> 비밀번호: <input
			type="password" name="pw" id="pw"><br /> <br /> <input
			type="submit" value="로그인"> <br />
		<button type="button" onclick="location.href='/'">취소</button>
	</form>
</body>
</html>
