<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="./inc/header_link.jsp" %>
</head>

<body class="animsition">
    <!-- top-bar start-->
	<%@include file="./inc/topbar.jsp" %>
    <!-- /top-bar end-->

    <!-- hero section start -->
    <div class="hero-section">
		<!-- navigation-->
	   	<%@include file="./inc/header_navi.jsp" %>
	    <!-- /navigation end -->
    </div>
     <!-- ./hero section end -->
     <div class="top-bar">
     	<div class="container">-</div>
     </div>

    
    <!-- content start -->
    <div class="space-medium">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
                    <div class="service-block pdt60 mb30">
                        <h1 class="default-title mb30">마이페이지</h1>
                        <p class="mb40">내가 쓴 글, 즐겨찾기, 회원정보 수정 등을 할 수 있는 곳입니다</p>
                        <a href="classes-list.html" class="btn btn-default">View ALL Classes</a>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
                    <div class="service-block outline mb30">
                        <div class="service-icon">
                            <i class="icon-default icon-quote"></i>
                        </div>
                        <div class="service-content pinside40">
                            <h2 class="service-title mb20"><a href="/mypost" class="title">내가 쓴 글</a> </h2>
                            <p class="mb60">내가 작성한 글을 볼 수 있습니다</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
                    <div class="service-block outline mb30">
                        <div class="service-icon">
                            <i class="icon-default icon-speech-bubble"></i>
                        </div>
                        <div class="service-content pinside40">
                            <h2 class="service-title mb20"><a href="classes-list.html" class="title">내가 쓴 댓글</a></h2>
                            <p class="mb60">내가 작성한 댓글을 볼 수 있습니다</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
                    <div class="service-block outline mb30">
                        <div class="service-icon">
                            <i class="icon-default icon-favorite"></i>
                        </div>
                        <div class="service-content pinside40">
                            <h2 class="service-title mb20"><a href="classes-list.html" class="title">즐겨찾기</a></h2>
                            <p class="mb60">내가 스크랩한 글들을 모아 볼 수 있습니다</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
                    <div class="service-block outline mb30">
                        <div class="service-icon">
                            <i class="icon-default icon-user-1"></i>
                        </div>
                        <div class="service-content pinside40">
                            <h2 class="service-title mb20"><a href="classes-list.html" class="title">회원정보 수정</a></h2>
                            <p class="mb60">회원정보를 수정할 수 있습니다</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
                    <div class="service-block outline mb30">
                        <div class="service-icon">
                            <i class="icon-default icon-envelope-1"></i>
                        </div>
                        <div class="service-content pinside40">
                            <h2 class="service-title mb20"><a href="classes-list.html" class="title">문의/신고내역</a></h2>
                            <p class="mb60">나의 문의와 신고내역을 볼 수 있습니다</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /content end -->

    <!-- black footer_space -->
    <%@include file="./inc/footer_space.jsp" %>
    
    <!-- tiny footer -->
    <%@include file="./inc/footer_tiny.jsp" %>
    
	<%@include file="./inc/footer_link.jsp" %>

</body>

</html>
