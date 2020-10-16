function confirm_leave() {
	var confirm_leave = confirm("회원탈퇴를 진행하시겠습니까?");

	if (confirm_leave == true) {
		alert("회원탈퇴에 성공하였습니다.");
		location.href = "/leave.do";
	} else if (confirm_leave == false) {
		alert("취소하였습니다.");
	}
}

function ShowDetail(articleNum) {
	location.href = "detail.do?num=" + articleNum;
}

function searchArticle() {
	var filter = $('#filter option:selected').val();
	var keyword = $('#keyword').val();
	location.href = "/list.do?pn=1&filter=" + fliter + "&keyword" + keyword;
}

function checkData() {
	var subject = $('#subject').val();
	if (!subject) {
		alert("제목을 입력하세요.");
		$('#subject').focus();
		return false;
	}
	var content = $('#content').val();
	if (!content) {
		alert("내용을 입력하세요.");
		$('#content').focus();
		return false;
	}
}

function ShowDetail(articleNum) {
	location.href = "detail.do?num=" + articleNum;
}

function searchArticle() {
	var filter = $('#filter option:selected').val();
	var keyword = $('#keyword').val();
	location.href = "/list.do?pn=1&filter=" + fliter + "&keyword" + keyword;
}

//정규표현식 검사

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
	$('#Modify').submit();
}

//로그인폼
function validateCheck2() {
	var id = $('#id').val();
	var pw = $('#pw').val();
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
}

