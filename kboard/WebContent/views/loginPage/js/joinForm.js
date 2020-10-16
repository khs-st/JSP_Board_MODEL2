function confirm_leave() {
	var confirm_leave = confirm("회원탈퇴를 진행하시겠습니까?");

	if (confirm_leave == true) {
		alert("회원탈퇴에 성공하였습니다.");
		location.href = "/leave.do";
	} else if (confirm_leave == false) {
		alert("취소하였습니다.");
	}
}

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

	// 아이디 길이, 영,소문자 체크(정규표현식)
	var regExpId = new RegExp("^[a-z0-9]{4,20}$", "g");
	// 아이디로 실행
	if (regExpId.exec(id) == null) {
		alert("잘못된 아이디 형식입니다.");
		$('#id').val("");
		$('#id').focus();
		return false;
	}

	// 비밀번호 길이, 영,소문자 체크(정규표현식)
	var regExpPw = new RegExp("^[a-z0-9]{4,30}$", "g");
	// 비밀번호로 실행
	if (regExpPw.exec(pw) == null) {
		alert("잘못된 비밀번호 형식입니다.");
		$('#pw').val("");
		$('#pw_confirm').val("");
		$('#pw').focus();
		return false;
	}
	// 이메일 길이, 영,소문자 체크(정규표현식)
	var regExpEmail = new RegExp(
			"^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$",
			"g");
	// 이메일로 실행
	if (regExpEmail.exec(mb_email) == null) {
		alert("잘못된 이메일 형식입니다.");
		$('#mb_email').val("");
		$('#mb_email').focus();
		return false;
	}
	// 이름 길이, 영,소문자 체크(정규표현식)
	var regExpName = new RegExp("^[가-힣]{2,4}|[a-zA-Z]{2,10}\\s[a-zA-Z]{2,10}$",
			"g");
	// 이름으로 실행
	if (regExpName.exec(mb_name) == null) {
		alert("잘못된 이름 형식입니다.");
		$('#mb_name').val("");
		$('#mb_name').focus();
		return false;
	}

	$('#joinForm').submit();
}
// 로그인 중복체크(ajax)

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
