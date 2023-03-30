<%@page import="com.edu.bodybuddy.domain.diet.DietInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.edu.bodybuddy.domain.diet.DietCategory"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%
	List<DietCategory> dietCategoryList=(List)request.getAttribute("dietCategoryList");
	//System.out.println("뭐지뭐지 "+dietCategoryList);
%>

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
        <div class="container">
          <div class="row">
              <div class="col-lg-6 col-md-6 col-sm-12  col-xs-12">
                  <div class="hero-caption pinside50">
                      <h1 class="hero-title">식단 정보 게시판</h1>
                      <p class="small-caps mb30 text-white"></p>
                      <p class="hero-text">식단에 대한 모든 것이 존재합니다!</p>
                  </div>
              </div>
          </div>
      	</div>
    </div>
     <!-- ./hero section end -->
     

    
    <!-- content start -->
    <div class="space-medium">
        <div class="container">
            <div class="row">
            <%int n=0; %>
            <%for(DietCategory dietCategory:dietCategoryList) {%>
            	<%n++; %>
                <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
                    <div class="service-block outline mb30">
                        <div class="service-icon">
                            <i class=""><img src="/resources/user/images/diet/<%=n %>.png" style="width:300px; height:300px"></i>
                        </div>
                        <div class="service-content pinside40">
                            <h2 class="service-title mb20"><a href="/diet/info_<%=dietCategory.getDiet_category_subname() %>/<%=dietCategory.getDiet_category_idx() %>" class="title"><%=dietCategory.getDiet_category_name() %> </a> </h2>
                            <p class="mb60"></p>
                        </div>
                    </div>
                </div>
             <%} %>
            </div>
        </div>
    </div>
    <!-- /content end -->

    <!-- black footer_space -->
    <%@include file="../inc/footer_space.jsp" %>
    
    <!-- tiny footer -->
    <%@include file="../inc/footer_tiny.jsp" %>
    
	<%@include file="../inc/footer_link.jsp" %>

</body>
<script type="text/javascript">
	
</script>

</html>
