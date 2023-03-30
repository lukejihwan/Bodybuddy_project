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
                                    <div class="col-md-12 col-sm-12"><img src="/resources/user/images/diet/vegan1.png" class="" alt="Vegan">
                                    <p></p>
                                    
                                    <!-- 내용 -->
                                    <div class="mb30">
                                        <h1>비건식(Vegan Diet)</h1>
                                        <p></p>
                                        <p>동물성 식품을 제한하고 과일·채소·곡물 등 식물성 식품을 섭취하는 식습관을 지향하는 생활 양식입니다.</p>                                       
                                        <p>동물성 음식은 보통 동물로 만든 음식과, 동물로부터 나온 유제품(우유, 버터, 치즈, 요구르트 등), 동물의 알, 동물 성분을 물에 넣고 끓인 국물과 어류까지도 포함하는 말이지만, 일부 엄격하지 않은 채식의 경우에는 동물의 고기를 제외한 일부의 동물성 음식을 먹는 경우도 있습니다.</p>
                                    </div>
                                    
                                    <!-- 채식주의자 디자인 -->
                                    <div class="">
				                        <h3>채식주의자 분류</h3>
				                       	<ul>
					                        <li><h4>비건(Vegan)</h4> 유제품과 동물의 알, 벌꿀을 포함한 모든 종류의 동물성 음식을 먹지 않고, 짐승의 가죽으로 만든 옷이나 화장품류처럼 동물성 원료가 포함된 모든 상품도 사용하지 않는 경우. </li>
					                        <li><h4>생채식주의(Raw veganism)</h4>식물성 재료를 열을 이용해 조리하지 않고 먹거나, 효소가 파괴되는 온도인 48 °C(118 °F) 이상으로는 열을 가하지 않습니다. 조리과정에서 영양소가 파괴되거나 변형되는 것을 막기 위한 경우가 많습니다.</li>
					                        <li><h4>과식주의(Fruitarianism)</h4>과일과 견과류의 열매와 씨앗 등, 식물에게 해를 끼치지 않는 부분만 먹는 경우. 일부 열매주의자는 나무에 매달려 있는 열매는 먹지 않고, 다 익어 땅에 떨어진 열매만 먹는 경우도 있습니다.</li>
				                       		<li><h4>락토 베지테리언(lacto vegetarian)</h4>고기와 동물의 알은 먹지 않지만 유제품은 먹는 경우.</li>
				                       		<li><h4>오보 베지테리언(ovo vegetarian) </h4>유제품은 먹지 않지만 동물의 알은 먹는 경우.</li>
				                       		<li><h4>락토-오보 베지테리언(lacto-ovo vegetarian) </h4>유제품과 동물의 알은 먹는 경우.</li>
				                       
				                       
				                        </ul>
		                            </div>   
		                            <p></p>    
                                    
                                    
                                    </div>
                                	</div>
                                	<br/>
                                
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
                                                                <div class="reply"><a href="/diet/info_detail/<%=dietInfo.getDiet_info_idx() %>" class="title"><i class="fa fa-mail-reply"></i> 글 보기</a></div>
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
                                                <li> <a href="/diet/info_fish/3"><i class="icon-4 service-sidenav-icon"></i><span class="sidenav-text">지중해식</span> </a></li>
                                                <li class="active"> <a href="/diet/info_vegan/4"><i class="icon-5 service-sidenav-icon"></i><span class="sidenav-text">비건식</span> </a></li>
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
