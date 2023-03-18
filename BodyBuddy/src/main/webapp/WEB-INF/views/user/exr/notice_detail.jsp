<%@page import="com.edu.bodybuddy.domain.exr.ExrCategory"%>
<%@page import="com.edu.bodybuddy.domain.exr.ExrNotice"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	ExrNotice exrNotice=(ExrNotice)request.getAttribute("exrNotice");
	List<ExrCategory> exrCategoryList=(List<ExrCategory>)request.getAttribute("exrCategoryList");
	System.out.println("디테일페이지 "+exrCategoryList);

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
    <div class="space-medium">
        <div class="container">
            <div class="row">

				상세페이지 올 예정


				<div class="content">
					<div class="container">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12">
								<div class="content-area">
									<div class="row">
										<div class="col-md-8 col-sm-12">
											<div class="row">
												<div class="col-md-12 col-sm-12">
													<img src="/resources/data/exr/<%//=exrNotice.getExrNoticeImgList().get(0).getFilename() %>"
														class="img-responsive mb30" alt="Fitness Website Template">
													<div class="mb30">
														<h1><%=exrNotice.getTitle() %></h1>
														<p>Donec eros turpissemper nenibh velultrices
															pellentesque ristiam eu odio lionec consectetur nisi
															egetlorem dapibus graviroin posuere eros eget purus
															cursus dignissim molestie tellus euismtiam vulputate
															lorem is elementum sollicitudi uspendisse ante
															turpissollicitudin egeleo sitamet pharetra vehicula
															dihasellus ipsum efficitur, libero sit amet placerat
															ullamcorper risusex vehicula quamnec molestie.</p>
														<p>Nulla lobortis interdum suscipell entesque
															efficitur sollicitudin magnaeu vehicula libero luctus
															etonec odio velit, viverra at scelerisque id efficitur
															ullamcorper turpiusce et enim eu elit semper fringtiam
															faucibus on quamut consectetur gravida tellus sem
															lobortis miuis consectetur magna lorem vitae lectuone.</p>
															
														<p>내용! : <%=exrNotice.getContent() %></p>
														
														<img src="/resources/data/exr/<%//=exrNotice.getExrNoticeImgList().get(1).getFilename() %>"
															class="alignright img-responsive" alt="">
														<p>Suscipell entesque efficitur sollicitudin magnaeu
															vehicula libero luctus etonec odio velit, viverra ate
															scelerisque id efficitur ullamcorper turpiusce etes enim
															eu elit semper fringtiam faucibus.</p>
														<P>Entesque efficitur sollicitudin magnaeu vehicula
															libero luctus etonec odio velit, viverra ate elerisque id
															efficitur ullamcorper enim eu elit semper ones fringtiam
															faucibus turpiusce etes.Donec eros turpissemper nenibh
															velultrices pellentesque ristiam eu odio lionec
															consectetur.</P>
															
														<img src="/resources/data/exr/<%//=exrNotice.getExrNoticeImgList().get(2).getFilename() %>"
														
															class="alignleft img-responsive" alt="">
														<p>Suscipell entesque efficitur sollicitudin magnaeu
															vehicula libero luctus etonec odio velit, viverra ate
															scelerisque id efficitur ullamcorper turpiusce etes enim
															eu elit semper fringtiam faucibus.</p>
														<p class="mb30">Entesque efficitur sollicitudin
															magnaeu vehicula libero luctus etonec odio velit, viverra
															ate elerisque id efficitur ullamcorper enim eu elit
															semper ones fringtiam faucibus turpiusce etes. Nulla
															lobortis interdum suscipell entesque efficitur
															sollicitudin magnaeu vehicula libero</p>
														<p class="mb30">Nulla lobortis interdum suscipell
															entesque efficitur sollicitudin magnaeu vehicula libero
															luctus etonec odio velit, viverra at scelerisque id
															efficitur ullamcorper turpiusce et enim eu elit semper
															fringtiam faucibus on quamut consectetur gravida tellus
															sem lobortis miuis consectetur magna lorem vitae
															lectuone. Nulla lobortis interdum suscipell entesque
															efficitur sollicitudin magnaeu vehicula libero.</p>
															
														<p>내용2 : <%//=exrNotice.getContent() %></p>
														
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-4 col-sm-12 col-xs-12">
											<div class="row">
												<div class="col-md-12 col-sm-12 col-xs-12">
													<div class="side-nav mb30  ">
														<ul class="listnone sidenav">
										
															<%for(ExrCategory exrCategory:exrCategoryList){
																	String status=null;
																	if(exrCategory.getExr_category_name().equals(exrNotice.getExrCategory().getExr_category_name())){
																		status="active";
																	}else{
																		status="";
																}%>
																	<li class="<%=status%>"><a href="#"><iclass="icon-2 service-sidenav-icon "></i>
																	<span class="sidenav-text"><%=exrCategory.getExr_category_name() %></span></a></li>
															<%} %>
														</ul>
													</div>
												</div>
	
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<a href="/exr/notice" class="btn btn-default">목록</a>	
					
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

</html>
