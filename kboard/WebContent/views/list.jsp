<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kb.www.vo.ArticleVO"%>
<%@page import="java.util.ArrayList"%>
<%
	ArrayList<ArticleVO> list = (ArrayList<ArticleVO>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IntelliJ 게시판</title>
<style>
h1, table {
	text-align: center;
	margin: auto;
}

table, tr, td {
	height: 30px;
	border: 1px solid black;
}

td {
	width: 100px;
}

#table {
	text-align: center;
	margin: auto;
	width: 600px;
}
</style>
<script src="https://code.jquery.com/jquery-1.11.3.js"
	type="text/javascript"></script>

<script type="text/javascript">
        function ShowDetail(articleNum) {
            location.href = "detail.do?num=" + articleNum;
        }

        $(document).ready(function () {
            $('tr').hover(function () {
                $(this).css('color', 'blue');
            }, function () {
                $(this).css('color', 'black');
            });
        });
    </script>
</head>
<h1>GitHub 커밋테스트!</h1>
<br />
<body>
	Rmwu
</body>
</html>