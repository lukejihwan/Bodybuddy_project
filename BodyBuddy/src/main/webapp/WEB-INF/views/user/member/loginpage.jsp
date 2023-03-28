<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@include file="../inc/header_link.jsp" %>
<style>
	.sns{width: 200px; height: 80px}
	img{width: 100%; height: 100%}
</style>
</head>

<body class="animsition">
<%@include file="../inc/topbar.jsp" %>
<div class="page-header">
<%@include file="../inc/header_navi.jsp" %>
	<div class="container">
        <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                <div class="page-caption pinside40">
                    <h1 class="page-title">로그인</h1>
                    <p>사이트 기능 이용을 위해서 로그인 해주세요</p>
                </div>
            </div>
        </div>
    </div>
</div>
    <!-- content start -->
<div class="container" style="padding: 100px">
	<form action="/auth/login_check" method="post">
	    <div class="form-group">
	        <label for="email">이메일</label>
	        <input type="text" name="email" class="form-control" placeholder="이메일을 입력해주세요">
	    </div>
	    <div class="form-group">
	        <label for="pass">비밀번호</label>
	        <input type="password" class="form-control" name="pass" placeholder="비밀번호 입력해주세요">
	    </div>
	    <div class="form-group form-group text-center py-3">
		    <button type="submit" class="btn btn-success mx-1">로그인</button>
		    <button type="button" class="btn btn-primary mx-1" onClick="location.href='/auth/join'">회원 가입</button>
	    </div>
	    <div class="form-group text-center py-3">
		    <button type="button" class="btn mx-1 sns" onclick="socialLogin('kakao')"><img src="/resources/user/images/kakaologin.png"></button>
		    <button type="button" class="btn mx-1 sns" onclick="socialLogin('google')"><img src="/resources/user/images/googlelogin.png"></button>
		    <button type="button" class="btn mx-1 sns" onclick="socialLogin('naver')"><img src="/resources/user/images/naverlogin.png"></button>
	    </div>
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

<script type="text/javascript">
function socialLogin(vendor) {
	console.log(vendor);
	$.ajax({
		type: "get",
		url: "/auth/social/"+vendor,
		success: function(result) {
			location.href=result.msg;
		}
	})
}
</script>

</html>