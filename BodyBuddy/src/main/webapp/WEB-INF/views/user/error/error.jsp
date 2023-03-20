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
	<h1><%=error%></h1>
	<p></p>
	<h1><span id="test">5</span>초 후에 로그인 페이지로 이동합니다.</h1>
</body>
<script type="text/javascript">
let count = 4;

$(function(){
	
	setInterval(() => {
		$("#test").text(count);
		count--;
		if(count<0){
			location.href="/auth/login";
		}
	}, 1000);
	
})
</script>
</html>