<%@ page contentType="text/html; charset=UTF-8"%>
<%
	Exception e = (Exception)request.getAttribute("e");
	String error = e.getMessage();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
</head>
<body>
</body>
<script type="text/javascript">
$(function(){
	alert("<%=error%>");
	location.href="/login";
})
</script>
</html>