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
		<div class="container mb-5">
			<div class="row">
				<div class="col-2">
					<%@include file="./inc/side-navi.jsp"%>
				</div>
				<div class="col-10">
					<div class="space-medium">
						<!-- 문의게시판 시작 -->
						<div class="container">
							<div class="row">
								<div class="col">
									<h1>1:1 문의내역</h1>
									<hr>
								</div>
							</div>
							<!-- end of row -->
							<div class="row">
								<div class="col table-responsive">
									<table class="table table-hover">
										<thead>
											<tr>
												<th>No</th>
												<th>제목</th>
												<th>등록일</th>
											</tr>
										</thead>
										<tbody>

										</tbody>
									</table>
								</div>
								<!-- end of col -->
							</div>
							<!-- end of row -->
							<div class="row">
								<div class="col text-right">
									<button type="button" class="btn btn-default" id="bt_ask">글쓰기</button>
								</div>
							</div>
							<!-- end of row -->
							<div class="row mt-3">
								<div class="col text-center d-flex justify-content-center">
									<div class="st-pagination">
										<!--st-pagination-->
									      <ul class="pagination">
									        <li> <a href="#" aria-label="previous"><span aria-hidden="true">previous</span></a></li>
									        <li class="active"><a href="#">1</a></li>
									        <li><a href="#">2</a></li>
									        <li><a href="#">3</a></li>
									        <li> <a href="#" aria-label="Next"><span aria-hidden="true">next</span></a></li>
									      </ul>
										<!--/st-pagination-->
									</div>
								</div>
							</div>
							<!-- end of row -->
						</div>
						<!-- /문의게시판 끝 -->
					</div>
					<!-- end of space-medium -->
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
    	$($("ul#sidenav li")[INTEREST]).addClass('active');
	}
    
    $(function() {
    	init();
	})
    </script>
</body>

</html>
