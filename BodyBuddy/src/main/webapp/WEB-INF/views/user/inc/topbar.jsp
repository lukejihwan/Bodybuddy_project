<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<link rel="shortcut icon" href="#">
<div class="top-bar">
    <div class="container">
        <div class="row">
        	<div class="col-md-3 col-sm-4 col-xs-12">
                <div class="top-search">
                    <div class="custom-search-input">
                        <div class="input-group  ">
                            <input type="text" class="  search-query form-control" placeholder="Search">
                            <span class="input-group-btn">
                    			<button class="btn btn-default" type="button"> <i class="fa fa-search"></i> </button>
                    		</span>
                    	</div>
                    </div>
                </div>
            </div>
            <div class="col-md-3 hidden-sm hidden-xs bdr">
                <div class="time-info">
                    <p class="time-text">~~님 현재 기록 등등</p>
                </div>
            </div>
            <div class="col-md-3 col-sm-4 hidden-xs bdr">
                <div class="call-info">
                    <!-- call-info -->
                    <sec:authorize access="isAnonymous()"><p class="call-no">환영합니다</p></sec:authorize>
                    <sec:authorize access="isAuthenticated()"><p class="call-no"><sec:authentication property="principal.member"/></p></sec:authorize>
                </div>
                <!-- /.call-info -->
            </div>
            <div class="col-md-3 col-sm-4 hidden-xs bdr">
                <div class="mail-info">
                    <sec:authorize access="isAnonymous()"><p class="mail-text"><a href="/auth/login">로그인</a>  /  <a href="/auth/join">회원가입</a></p></sec:authorize>
                    <sec:authorize access="isAuthenticated()"><p class="mail-text"><a href="/mypage"><sec:authentication property="principal.member.nickname"/>  님 </a>  /  <a href="/logout">로그아웃</a></p></sec:authorize>
                </div>
            </div>
        </div>
    </div>
</div>