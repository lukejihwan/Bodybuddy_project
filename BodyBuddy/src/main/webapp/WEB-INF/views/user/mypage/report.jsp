<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<!-- content 부분만 비워둔 기본 템플릿 -->
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
     
  	<!-- content start -->
    <div class="content">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    <div class="content-area">
                        <div class="row">
		                   <div class="col-md-4 col-sm-12 col-xs-12">
                                <div class="row">
                                    <div class="col-md-7 col-sm-7 col-xs-7">
                                        <%@include file="./inc/side-navi.jsp"%>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
 
    <!-- /content end -->
    
	<!-- black footer_space -->
    <%@include file="../inc/footer_space.jsp" %>
    

    
    <!-- tiny footer -->
    <%@include file="../inc/footer_tiny.jsp" %>
    
    <%@include file="../inc/footer_link.jsp" %>
    <script>
    
    function init() {
    	$("ul#sidenav li").removeClass();
    	$($("ul#sidenav li")[REPORT]).addClass('active');
	}
    
    $(function() {
    	init();
	})
    </script>
</body>

</html>
