<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../inc/header_link.jsp" %>
</head>

<body class="animsition">
    <!-- top-bar start-->
	<%@include file="../inc/topbar.jsp" %>
    <!-- /top-bar end-->

    <!-- hero section start -->
    <div class="hero-section">
		<!-- navigation-->
	   	<%@include file="../inc/header_navi.jsp" %>
	    <!-- /navigation end -->
    </div>
     <!-- ./hero section end -->
     <div class="top-bar">
     	<div class="container">-</div>
     </div>

    
<div class="container">
    <h1>로그인</h1>
    <h2>로그인 유저 : </h2>
    <p sec:authentication="principal"></p>
    <div sec:authorize="isAnonymous()">
        <form method="post">
            <div class="form-group">
                <label>아이디</label>
                <input type="text" name="member_id" class="form-control" placeholder="아이디 입력해주세요">
            </div>
            <div class="form-group">
                <label>비밀번호</label>
                <input type="password" class="form-control" name="member_pass" placeholder="비밀번호 입력해주세요">
            </div>
            <button type="submit" class="btn btn-success">로그인</button>
            <button type="button" class="btn btn-primary" onClick="location.href='/regist'">회원 가입</button>
            <p></p>
            <button type="button" class="btn btn-warning" id="bt_kakao">카카오로그인</button>
        </form>
        <br/>
    </div>
    <div sec:authorize="isAuthenticated()" style="background-color:pink; padding:1em;">
        <a href="/logout">로그아웃</a>
    </div>

</div>

<br><br>
<a href="/user">유저</a><br>
<a href="/manager" sec:authorize="hasRole('ROLE_USER')">이것도 보여야됩니다 유저</a><br>
<a href="/admin" sec:authorize="hasRole('ROLE_ADMIN')">어드민</a><br>

<br><br>
<a href="/info">Form 로그인 정보</a>

    <!-- black footer_space -->
    <%@include file="../inc/footer_space.jsp" %>
    
    <!-- tiny footer -->
    <%@include file="../inc/footer_tiny.jsp" %>
    
	<%@include file="../inc/footer_link.jsp" %>
</body>

</html>