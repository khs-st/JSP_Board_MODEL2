<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
	int pageCount = (int) request.getAttribute("pageCount");
String nowPage = request.getParameter("pageNum");
%>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기 화면</title>
<style>
#subject, #content {
	text-align: center;
	margin: auto;
}
</style>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script>
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
</script>

</head>
<body>
	<form action="/register.do?pageNum=<%=nowPage%>" method="post"
		onsubmit="checkData()">
		글 제목 <input type="text" name="subject" id="subject" MAXLENGTH="100"><br />
		글 내용
		<div>
			<c:import url="/editor/editorSkinForRegister.jsp"></c:import>
		</div>
		<textarea name="content" id="content" cols="30" rows="10"></textarea>
		<br /> <input type="submit" value="글쓰기">
	</form>
</body>
</html>
