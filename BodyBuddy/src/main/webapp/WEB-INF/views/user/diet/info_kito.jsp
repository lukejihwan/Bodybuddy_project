<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@page import="java.util.List"%>
<%@page import="com.edu.bodybuddy.domain.diet.DietInfo"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%
	List<DietInfo> dietInfoList=(List)request.getAttribute("dietInfoList");
	//System.out.println("뷰에서 확인 "+dietInfoList);
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
    </div>
    
     <!-- ./hero section end -->
     <div class="top-bar">
     	<div class="container">-</div>
     </div>
    
<!-- 본문 시작 -->    
    <div class="content">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    <div class="content-area">
                        <div class="row">
                            <div class="col-md-8 col-sm-12">
                                <div class="row">
                                	<!-- 사진 들어갈 곳 -->
                                    <div class="col-md-12 col-sm-12"><img src="/resources/user/images/diet/kito1.jpg" class="" alt="Ketogenic">
                                    <p></p>
                                    
                                    <!-- 내용 -->
                                    <div class="mb30">
                                        <h1>키토제닉(저탄고지)(Ketogenic)</h1>
                                        <p></p>
                                        <p>영양소의 질과 양을 제한없이 하루에 필요한 영양소로 이루어진 식사로서 사람의 영양상태를 균형있게 해주기 위한 목적으로 이루어져 있습니다.</p>                                       
                                        <p>특정한 식품이나 질감상의 조정이 필요치 않은 사람에게 적용되며 영챵적 측면과 함께 균형식에 대한 효과를 거둘 수 있도록 합니다.</p>
                                        <p>과일, 채소, 통곡물, 무지방 또는 저지방 우유 및 유제품을 강조합니다. 해산물, 살코기 및 가금류, 계란, 콩류, 견과류 및 씨앗과 같은 다양한 단백질 식품을 포함합니다. 첨가당, 나트륨, 포화 지방, 트랜스 지방 콜레스테롤이 적습니다.</p>
                                      	<p>전분과 섬유질이 충분한 음식을 섭취할 수 있으며, 너무 많은 설탕과 나트륨은 피해야합니다. 되도록 하루 세 끼의 균형 잡힌 식사를 하며, 1회 섭취량을 확인하고 다양한 음식을 조금씩 먹습니다. </p>  
                                    </div>
                                    </div>
                                </div>
                                
                               <!-- 하루 영양소 -->
                               <div class="container">  
                               <h4>일반식의 영양기준</h4>        
								  <table class="table table-dark">
								    <thead>
								      <tr>
								        <th>열량(kcal)</th>
								        <th>당질(g)</th>
								        <th>단백질(g)</th>
								        <th>지질(g)</th>
								      </tr>
								    </thead>
								    <tbody>
								      <tr>
								        <td>2205</td>
								        <td>338</td>
								        <td>112</td>
								        <td>46</td>
								      </tr>
								      </tr>
								    </tbody>
								  </table>
								</div>
                                
                                <!-- 식단리스트 -->
                                <div class="comments-area pinside40 outline mb40">
                                        <ul class="comment-list listnone">
                                            <li class="comment">
                                                <div class="comment-body">
                                                <%for (DietInfo dietInfo:dietInfoList){%>
                                                    <div class="">
                                                        <div class="comment-author"><img src="<%=dietInfo.getPreview() %>" alt="preview" style="width:100px;height:100px";> </div>
                                                        <div class="comment-info">
                                                            <div class="comment-header">
                                                                <div class="reply"><a href="/diet/info_detail?diet_info_idx=<%=dietInfo.getDiet_info_idx() %>" class="title"><i class="fa fa-mail-reply"></i> 글 보기</a></div>
                                                                <h4 class="user-title mb10"><%=dietInfo.getTitle() %></h4>
                                                                <div class="comment-meta"><span class="comment-meta-date"><%=dietInfo.getRegdate().substring(0,10) %></span></div>
                                                            </div>
                                                            <div class="comment-content">
                                                                <p><%=dietInfo.getSubtitle() %></p>
                                                            </div>
                                                            <hr>  
                                                        </div>
                                                    </div>
                                                <%} %>
                                                </div>    
                                            </li>
                                        </ul>  
                                    </div>
                                    <!-- 식단리스트 끝 -->
                          	</div>

           <!-- 본문 끝 -->                 
                            <div class="col-md-4 col-sm-12 col-xs-12">
                                <div class="row">
                                    <div class="col-md-12 col-sm-12 col-xs-12">
                                    
                                       	<!-- 오른쪽 사이드 바 -->
                                        <div class="side-nav mb30  ">
                                            <ul class="listnone sidenav">
                                                <li><a href="/diet/info_general/1"><i class="icon-2 service-sidenav-icon "></i><span class="sidenav-text">일반식</span></a></li>
                                                <li class="active"> <a href="/diet/info_kito/2"><i class="icon-1 service-sidenav-icon"></i><span class="sidenav-text">키토제닉</span> </a></li>
                                                <li> <a href="/diet/info_fish/3"><i class="icon-4 service-sidenav-icon"></i><span class="sidenav-text">지중해식</span> </a></li>
                                                <li> <a href="/diet/info_vegan/4"><i class="icon-5 service-sidenav-icon"></i><span class="sidenav-text">비건식</span> </a></li>
                                                <li> <a href="/diet/info_time"><i class="icon-6 service-sidenav-icon"></i><span class="sidenav-text">간헐적단식</span> </a></li>
                                            </ul>
                                        </div>
                                        
                                    </div>
                                    <!-- 오른쪽 하단 광고
                                    <div class="col-md-12 col-sm-6 col-xs-12">
                                        <div class="widget-cta">
                                            <div class="cta-img">
                                                <div class="cta-content pinside30">
                                                    <h1 class="primary-title  ">Special offer!</h1>
                                                    <small>Memberships from only </small>
                                                    <h2 class="offer-price">$10.00*</h2>
                                                </div>
                                            </div>
                                            <a href="#" class="btn btn-block btn-default">Join Us NOW</a>
                                        </div>
                                    </div>
                                    -->
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

</body>

<script type="text/javascript">
	

</script>

</html>
