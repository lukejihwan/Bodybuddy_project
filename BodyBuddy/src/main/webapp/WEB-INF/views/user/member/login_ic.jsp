<%@page import="com.edu.bodybuddy.domain.member.Member"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	Member member = (Member)request.getAttribute("Member");
	String email = member.getEmail();
	String pass = member.getPassword().getPass();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="../inc/header_link.jsp" %>
</head>
<body>
	<form id="form1" action="/auth/login_check" method="post">
		<input type="hidden" name="email" value="<%=email%>">
		<input type="hidden" name="pass" value="<%=pass%>">
	</form>
</body>
<script type="text/javascript">
$(function(){
	$("#form1").submit();
})
</script>
</html>