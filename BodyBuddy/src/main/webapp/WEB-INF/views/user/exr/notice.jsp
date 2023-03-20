<%@page import="com.edu.bodybuddy.domain.exr.ExrNotice"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	List<ExrNotice> exrNoticeList=(List<ExrNotice>)request.getAttribute("exrNoticeList");
	System.out.println("노티스 페이지 "+exrNoticeList);
%>
<!DOCTYPE html>
<html lang="en">
<head profile="http://www.w3.org/2005/10/profile">

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
                      <h1 class="hero-title">운동 정보 게시판</h1>
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
    <div class="space-medium">
        <div class="container">
            <div class="row">


				<%int n=0;%> 
				<%for(ExrNotice exrNotice:exrNoticeList){ %>
					<% n++;%>
	                <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
	                    <div class="service-block outline mb30">
	                        <div class="service-icon">
	                            <i class="icon-default icon-<%=n%>"></i>
	                        </div>
	                        <div class="service-content pinside40">
	                            <h2 class="service-title mb20"><a href="/exr/notice/<%=exrNotice.getExr_notice_idx() %>" class="title"><%=exrNotice.getTitle() %></a> </h2>
	                            <p class="mb60"><%//=exrNotice.getContent1() %></p>
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

</html>
