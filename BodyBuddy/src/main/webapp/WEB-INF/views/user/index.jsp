<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="./inc/header_link.jsp" %>
<%@include file="./inc/adminlte.jsp" %>
<style type="text/css">
	.card-primary:not(.card-outline)>.card-header{
		background-color: #c5f016;
	}
	.card-primary:not(.card-outline)>.card-header, .card-primary:not(.card-outline)>.card-header a {
	    color: #383838;
	}
	.clickable{
		cursor: pointer;
	}
</style>
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
                      <h1 class="hero-title">환영합니다!</h1>
                      <p class="hero-text">당신의 건강 파트너, 바디바디입니다</p>
                  </div>
              </div>
          </div>
      	</div>
    </div>
     <!-- ./hero section end -->
     
    <!-- content start -->
    
    <div class="space-medium"  id="app1">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                	<template>
                        <rank :ranklist="rankList"/>
                	</template>
                </div>
            </div>
            <!-- end of row -->
            <div class="row">
                <div class="col-md-6">
                	<template>
                		<board_rank id="exr_routine"/>
                	</template>
                	<template>
                		<board_rank id="exr_tip"/>
                	</template>
                	<template>
                		<board_rank id="exr_today"/>
                	</template>
                </div>
                <div class="col-md-6">
                	<template>
                		<board_rank id="diet_share"/>
                	</template>
                	<template>
                		<board_rank id="diet_tip"/>
                	</template>
                	<template>
                		<board_rank id="free_board"/>
                	</template>
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
<script src="/resources/user/js/main.js"></script>

</script>
</html>
