<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@include file="../inc/header_link.jsp" %>
<style>
	.sns{width: 200px; height: 80px}
	img{width: 100%; height: 100%}
	label{color: white}
	.line {
		display:flex;
		flex-basis:100%;
		align-items:center;
		color: rgb(255, 255, 255);
		font-size:14px;
		margin:8px 0px;
	}
	.line::before{
		content:"";
		flex-grow:1;
		margin:0px 16px;
		background: rgb(255, 255, 255);
		height:1px;
		font-size:0px;
		line-height: 0px;
	}
	.line::after {
		content:"";
		flex-grow:1;
		margin:0px 16px;
		background: rgb(255, 255, 255);
		height:1px;
		font-size:0px;
		line-height: 0px;
	}
	input{
		background: #e3e3e3;
	}
</style>
</head>

<body class="animsition" style=" background: black">
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
	<div id="wrapper">
		<div class="container justify-content-center" style="padding: 100px;">
			<div class="logo" style="text-align: center">
				<a href="/"><img src="/resources/user/images/logo.png" style="width: 300px"></a>
			</div>
			<hr>
			<form action="/auth/login_check" method="post">
				<div class="form-group">
					<label for="email">이메일</label>
					<input type="text" name="email" class="form-control" placeholder="이메일을 입력해주세요">
				</div>
				<div class="form-group">
					<label for="pass">비밀번호</label>
					<input type="password" class="form-control" name="pass" placeholder="비밀번호 입력해주세요">
				</div>
				<div class="form-group  text-center py-3">
					<button type="submit" class="btn btn-default"> 로그인 </button>
					<button type="button" class="btn btn-primary" onClick="location.href='/auth/join'">회원가입</button>
				</div>
				<div class="form-group text-center py-3">
					<span><a href="#" onclick="findPass()">비밀번호 찾기</a></span>
				</div>
				<div class="line">또는</div>
				<div class="form-group text-center py-3">
					<button type="button" class="btn mx-1 sns" onclick="socialLogin('kakao')"><img src="/resources/user/images/kakaologin.png"></button>
					<button type="button" class="btn mx-1 sns" onclick="socialLogin('google')"><img src="/resources/user/images/googlelogin.png"></button>
					<button type="button" class="btn mx-1 sns" onclick="socialLogin('naver')"><img src="/resources/user/images/naverlogin.png"></button>
				</div>
			</form>
		</div>
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

function findPass(){

}
</script>

</html>