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
                                        <p>저탄수화물 고지방(저탄고지) 다이어트 또는 키토제닉(Ketogenic) 다이어트는 식이요법의 일종으로[1], 단순히 지방을 많이 먹는 것이 아니라 열량의 총 섭취량은 유지하면서 섭취 비중 가운데 탄수화물(당질)이 들어간 음식을 줄이고 지방이 들어간 음식을 늘려, 체내 인슐린 저항성을 낮추는 것을 목표로 합니다.</p>                                       
                                        <p>LCHF에서 제시하는 조건을 맞추려면 탄수화물처럼 보이는 것은 의식적으로 기피해야 합니다.</p>
                                        <p>물론 그렇다고 탄수화물을 전혀 섭취하지 말라는 것은 아니지만 효과를 보기 위해선 정말 조금만 섭취해야 제대로 된 다이어트 효과를 볼수있으며 지방은 탄수화물 감소에 따른 열량 부족을 대체하는 수단인 것입니다.</p>
                                      	<p>참고로 탄수화물의 절대량만 줄이면 그만큼 당뇨약의 복용량도 줄일 수 있기 때문에, 아예 소식을 하거나, 백미를 현미로 바꿔주는 것만으로도 비슷한 효과를 누릴 수 있습니다.</p>  
                                    </div>
                                    </div>
                                </div>
                                
                                <!-- 실행방법 -->
                              	<div class="">
			                    	<h3>키토제닉의 원리</h3>
			                        	<ul>
			                            	<li><h4>지방에 대한 관점</h4>일반적으로 우리는 지방을 좋지 않은 것으로 인식합니다. 이는 몸에 쌓인 지방과 먹는 지방을 혼동하여 생기는 일인데, 실제로 다이어트를 해보면 지방을 분해하는 것이 목적이지만 필수 지방산의 경우 견과류 등으로 섭취하는 것을 요구할 정도로 굉장히 중요합니다.
			                            	우리 몸에 쌓이는 지방은 일반적으로 잉여 탄수화물에서 합성된 중성지방입니다. <br/> LCHF는 기본적으로 좋은 지방을 많이 섭취하는 것이 신체에 악영향을 주지 않고, 탄수화물의 섭취보다 오히려 좋다고 강조합니다. 또한 섭취하는 양질의 지방은 성인병의 원인이 되는 것으로 보지 않고, 탄수화물의 과섭취로 인한 비만 등으로 체내에 이미 보유하고 있는 내장지방 등에서 발생하는 것에 집중합니다.</li>
			                               	<p></p>
			                                <li><h4>인슐린에 대한 관점</h4>기본 원리는 인슐린의 분비가 활발할수록 체지방이 증가할 위험이 높아지는데 착안하여, 일차적으로 인슐린의 분비를 억제하는 것에 있습니다. 따라서 탄수화물을 엄격하게 제한하여 혈당을 낮추고, 이를 통해 인슐린의 분비를 최소화하여 체지방의 축적을 막는 것이 우선입니다. <br/>
			                                 탄수화물의 제한으로 인한 부족 열량은 지방을 충분히 섭취하여 메꾸면서, 기존 탄수화물의 과량 섭취로 인한 식욕억제 호르몬인 렙틴의 저항성을 극복하여 포만감을 주어 식사량 자체가 줄어들게 됩니다. <br/>
			                                 최종적으로는 계속적으로 탄수화물을 통제하여 축적된 포도당과 글리코겐을 모두 소모한 신체가 체지방 연소 결과 발생하는 케톤을 주 에너지원으로 사용하게 되는 케토시스(Ketosis) 상태를 이끌어내어, 지속적인 체지방 감량을 이끌어내는 게 주 전략입니다.</li>
			                            </ul>
		                        </div>   
		                        <p></p> 
		                        <br/>
		                         
		                        <!-- 장점, 단점 -->
		                        <div class="col">
			                    	<h3>키토제닉의 장단점</h3>
			                    		<ul>
			                    			<div class="row">
			                        		<li><h4>장점</h4>식욕의 통제<br/>지속적인 체지방 감량<br/>호르몬 밸런스의 개선</li>
			                          		<li style="margin-left:200px"><h4>단점</h4>높은 시행 난이도<br/>높은 식단 비용<br/>지방간, 고지혈증</li>			                    			
			                    			</div>
			                         	</ul>
		                		</div>   
		                       	<p></p> 
                                
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
