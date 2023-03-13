<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@include file="../inc/header_link.jsp" %>
</head>

<body class="animsition">
<%@include file="../inc/topbar.jsp" %>
<div class="page-header">
<%@include file="../inc/header_navi.jsp" %>
	<div class="container">
        <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                <div class="page-caption pinside40">
                    <h1 class="page-title">로그인 페이지입니다</h1>
                    <p>이부분 로그인페이지에서는 빼는게 나을거같음</p>
                </div>
            </div>
        </div>
    </div>
</div>
    <!-- content start -->
<div class="container">
	<form action="/login_check" method="post">
	    <div class="form-group">
	        <label for="email">이메일</label>
	        <input type="text" name="email" class="form-control" placeholder="이메일을 입력해주세요">
	    </div>
	    <div class="form-group">
	        <label for="pass">비밀번호</label>
	        <input type="password" class="form-control" name="pass" placeholder="비밀번호 입력해주세요">
	    </div>
	    <button type="submit" class="btn btn-success">로그인</button>
	    <button type="button" class="btn btn-primary" onClick="location.href='/join'">회원 가입</button>
	    <p></p>
	    <button type="button" class="btn btn-warning" id="bt_kakao">카카오로그인</button>
	</form>
</div>
    <!-- /content end -->
    
   	<!-- black footer_space -->
    <%@include file="../inc/footer_space.jsp" %>
    
    <!-- tiny footer -->
    <%@include file="../inc/footer_tiny.jsp" %>
    
    <!-- back to top icon -->
    <a href="#0" class="cd-top" title="Go to top">Top</a>
    
    <%@include file="../inc/footer_link.jsp" %>
    
</body>

</html>