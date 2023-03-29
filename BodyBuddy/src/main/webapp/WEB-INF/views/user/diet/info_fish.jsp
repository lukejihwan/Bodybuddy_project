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
                                    <div class="col-md-12 col-sm-12"><img src="/resources/user/images/diet/fish1.jpg" class="" alt="Mediterranean">
                                    <p></p>
                                    
                                    <!-- 내용 -->
                                    <div class="mb30">
                                        <h1>지중해식(Mediterranean)</h1>
                                        <p></p>
                                        <p>식이섬유와 항산화 특성을 가지는 성분들을 많이, 그리고 자주 섭취하는 영양적으로 균형 잡힌 식단입니다.</p>                                       
                                        <p>다량의 올레산, 폴리페놀, 불포화 지방산이 포함되어 있으므로 항염증 특성을 가지게 되며, 비만, 제2형 당뇨병, 염증성 질환과 심혈관계 질환의 예방이나 치료에 도움이 된다고 알려져 있습니다.</p>
                                        <p>지중해식 식단은 장내 미생물의 다양성 증가, 유익한 대사산물인 짧은사슬지방산(Short Chain Fatty Acids, SCFAs) 생산, 밀착연접과 점액 분비 조절로 인한 장벽 상태 향상 등의 효과가 있습니다.</p> 
                                    	<p></p>
                                    	<p>하지만 일부 국가를 제외한 주민에게 낯설 수 있다는 점. 그리스, 스페인 등 지중해 연안국의 주민들은 제 땅에서 제철에 나는 로컬푸드지만 다른 나라 사람들은 똑같은 음식 재료를 구하기 어렵거나, 값이 비싸고, 입맛에 맞지 않을 수도 있습니다.</p>
		                                <p>한국이라면 연어 스테이크는 고등어구이, 올리브유를 뿌린 샐러드는 들기름을 친 나물 무침으로 대체해도 비슷한 효과를 얻을 수 있습니다. 지중해식 식단의 핵심은 가공육, 붉은 살 육류를 생선이나 가금류로 대체하고, 무엇보다 채소를 많이 먹는 데에 있습니다.</p>
                                    
                                    </div>
                                    </div>
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
                                                <li> <a href="/diet/info_kito/2"><i class="icon-1 service-sidenav-icon"></i><span class="sidenav-text">키토제닉</span> </a></li>
                                                <li class="active"> <a href="/diet/info_fish/3"><i class="icon-4 service-sidenav-icon"></i><span class="sidenav-text">지중해식</span> </a></li>
                                                <li> <a href="/diet/info_vegan/4"><i class="icon-5 service-sidenav-icon"></i><span class="sidenav-text">비건식</span> </a></li>
                                                <li> <a href="/diet/info_time/5"><i class="icon-6 service-sidenav-icon"></i><span class="sidenav-text">간헐적단식</span> </a></li>
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
