<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<!-- content 부분만 비워둔 기본 템플릿 -->
<!-- hero섹션이 포함되어있음 -->
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
        <div class="container">
          <div class="row">
              <div class="col-lg-6 col-md-6 col-sm-12  col-xs-12">
                  <div class="hero-caption pinside50">
                      <h1 class="hero-title">페이지 제목 올 곳</h1>
                      <p class="small-caps mb30 text-white">페이지 소제목 올 곳</p>
                      <p class="hero-text">페이지 간단한 설명이 올 곳. 이 탬플릿 페이지마다 써먹어도 괜찮을듯</p>
                      <a href="classes-list.html" class="btn btn-default">링크 필요하면 사용할 버튼</a>
                  </div>
              </div>
          </div>
      	</div>
    </div>
     <!-- ./hero section end -->
     
    <!-- content start -->
    
  <!-- /content end -->
    
	<!-- black footer_space -->
    <%@include file="./inc/footer_space.jsp" %>
    

    
    <!-- tiny footer -->
    <%@include file="./inc/footer_tiny.jsp" %>
    
    <%@include file="./inc/footer_link.jsp" %>
    
</body>

</html>
