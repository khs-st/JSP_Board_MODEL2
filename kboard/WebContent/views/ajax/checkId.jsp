<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	int count = (int) request.getAttribute("count");
%>

<%
	if (count > 0) {
%>
{"id" : "false"}
<%
	} else {
%>
{"id" : "true"}
<%
	}
%>