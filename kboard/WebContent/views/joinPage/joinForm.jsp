<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<meta charset="UTF-8">
<head>
<title>회원가입</title>
</head>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="css/custom.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/custom.css">
<script>
	var isChecked = false;

	function checkGender() {

		var arrRadio = document.getElementsByName("mb_gender");

		for (var i = 0; i < arrRadio.length; i++) {

			if (arrRadio[i].checked) {
				alert(arrRadio[i].value + "를 선택하셨습니다.")
				break;
			}
		}
	}

	function validateCheck() {
		var id = $('#id').val();
		var pw = $('#pw').val();
		var pw_cofonrim = $('#pw_confirm').val();
		var mb_name = $('#mb_name').val();
		var mb_email = $('#mb_email').val();
		if (!id) {
			alert("아이디를 입력해주세요.");
			$('#id').focus();
			return false;
		}
		if (!pw) {
			alert("비밀번호를 입력해주세요.");
			$('#pw').focus();
			return false;
		}
		if (!mb_name) {
			alert("이름을 입력해주세요.");
			$('#mb_name').focus();
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

		if (!isChecked) {
			alert("아이디 중복 검사를 해주세요.");
			return false;
		}

		//아이디 길이, 영,소문자 체크(정규표현식)
		var regExpId = new RegExp("^[a-z0-9]{4,20}$", "g");
		//아이디로 실행
		if (regExpId.exec(id) == null) {
			alert("잘못된 아이디 형식입니다.");
			$('#id').val("");
			$('#id').focus();
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
		//이름으로 실행
		if (regExpName.exec(mb_name) == null) {
			alert("잘못된 이름 형식입니다.");
			$('#mb_name').val("");
			$('#mb_name').focus();
			return false;
		}

		$('#joinForm').submit();
	}
	//로그인 중복체크(ajax)

	function checkId() {
		var id = $('#id').val();

		if (!id) {
			alert("아이디를 입력해주세요.");
			return;
		}

		$.ajax({
			url : "/checkid.ajax",
			type : "post",
			data : {
				id : id
			},
			dataType : "json",
			error : function() {
				alert("통신 실패");
			},
			success : function(data) {
				if (data.id == "true") {
					// 중복된 아이디 없음
					isChecked = true;
					alert("사용할 수 있는 아이디 입니다.");
				} else {
					// 중복된 아이디 있음
					alert("사용할 수 없는 아이디 입니다.");
					$('#id').val("");
					$('#id').focus();
				}
			}
		});
	}

	function initCheckId() {
		isChecked = false;
	}
</script>

<body>
	<div style="width: 750px;" class="container">
		<form action="/joinProc.do" method="post" id="joinForm">
			<h3 style="text-align: center;">회원가입</h3>
			<div class="form-group">
				<label for="id">아이디</label>
				<div class="input-group">
					<input type="text" class="form-control" placeholder="아이디" name="id"
						id="id" maxlength="20" oninput="initCheckId()"> <span
						class="input-group-btn">
						<button type="button" class="btn btn-primary"
							style="font-family: 'Noto Sans KR', sans-serif;" id="dup"
							onclick="checkId()">중복확인</button>
					</span>
				</div>
			</div>
			<div class="form-group">
				<label for="pw">비밀번호</label> <input type="password"
					class="form-control" placeholder="비밀번호" name="pw" id="pw"
					maxlength="20">
			</div>
			<div class="form-group">
				<label for="pw_confirm">비밀번호 확인</label> <input type="password"
					class="form-control" placeholder="비밀번호확인" name="pw_confirm"
					id="pw_confirm" maxlength="20">
			</div>
			<div class="form-group">
				<label for="name">이름</label> <input type="text" class="form-control"
					placeholder="이름" name="mb_name" id="mb_name" maxlength="20">
			</div>
			<div class="form-group" style="text-align: center;">
				<div class="btn-group" data-toggle="buttons">
					<label class="radio-inline btn btn-danger"> <input
						type="radio" id="man" name="mb_gender" value="남자"
						autocomplete="off" required>남자
					</label> <label class="radio-inline btn btn-success"> <input
						type="radio" id="woman" name="mb_gender" autocomplete="off"
						value="여자" required>여자
					</label> <br /> <input type="button" class="btn btn-primary"
						value="성별 확인하기" style="border-radius: 10px; margin-top: 10px;"
						onclick="checkGender()">
				</div>
			</div>
			<div class="form-group">
				<label for="email">이메일</label> <input type="email"
					class="form-control" placeholder="이메일" id="mb_email"
					name="mb_email" maxlength="100">
			</div>
			<div class="form-group">
				<input type="button" class="btn btn-primary form-control"
					value="회원가입" onclick="validateCheck()">
			</div>
			<div class="form-group">
				<button type="button" class="btn btn-primary form-control"
					onclick="location.href='/'">취소</button>
			</div>
		</form>
	</div>
</body>
</html>
