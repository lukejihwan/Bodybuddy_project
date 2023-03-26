<%@page import="com.edu.bodybuddy.util.PageManager"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%
	//PageManager pageManager=(PageManager)request.getAttribute("pageManager");
%>


<!-- content 부분만 비워둔 기본 템플릿 -->
<!-- hero섹션이 포함되어있음 -->
<html lang="en">
<head>
<%@include file="../inc/header_link.jsp"%>
<%@include file="../inc/list_css.jsp"%>
</head>
<body class="animsition">
	<!-- top-bar start-->
	<%@include file="../inc/topbar.jsp"%>
	<!-- /top-bar end-->

	<!-- hero section start -->
	<div class="hero-section">
		<!-- navigation-->
		<%@include file="../inc/header_navi.jsp"%>
		<!-- /navigation end -->
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-12  col-xs-12">
					<div class="hero-caption pinside50">
						<h1 class="hero-title">칼로리계산기</h1>
						<p class="small-caps mb30 text-white"></p>
						<p class="hero-text">칼로리를 계산해볼 수 있는 게시판</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- ./hero section end -->
	
	<!-- content start -->
	<div class="content" id="app1">
		<div class="container">
			<div class="row">
				<div class="col">
                    <h1>식단팁 게시판</h1>
                    <hr>
                    
            <!-- 검색처리 -->
			<div class="row">
				<div class="col-lg-offset-4 col-lg-8" style="margin-left:180px">
					<div class="widget widget-search mb10">
						<form>
							<div class="input-group">
								<input type="text" class="form-control" placeholder="검색어를 입력하세요" name="keyword">
								<button class="btn btn-default" type="button"><i class="fa fa-search" id="bt_search"></i></button>
							</div>
						</form>
					</div>
				</div>				
			</div>
			<!-- 검색처리 끝 -->
                    
				</div>
			</div>
			<!-- end of row -->
			<form id="form">
			
			<div class="row">
				<div class="col table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>No</th>
								<th>음식명</th>
								<th>단위</th>
								<th>칼로리</th>
								<th><input type="checkbox"></th>
							</tr>
						</thead>
						<tbody>
						<%for(int i=0; i<10; i++){ %>
							<tr>
								<th>No</th>
								<th>제목</th>
								<th>글쓴이</th>
								<th>작성일</th>
								<th><input type="checkbox"></th>
							</tr>
						<%} %>
						</tbody>
					</table>
				</div>
				<!-- end of col -->
			</div>
			</form>

			
			<!-- 페이징 처리 -->
			
			<!-- 페이징 처리 끝 -->
			
			
			
		
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
	function test(){
		
		
		
		
		
		
		
		$.ajax({
			url:"/rest/diet/kcal/test",
			type:"get",
			success:function(result, status, xhr){
				console.log(result);
			}
		});
	}



	$(function(){
		test();	
	});

</script>
</html>
