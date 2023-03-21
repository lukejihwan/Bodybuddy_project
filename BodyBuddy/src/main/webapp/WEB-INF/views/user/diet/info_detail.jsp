<%@page import="com.edu.bodybuddy.domain.diet.DietInfo"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	List<DietInfo> dietInfoList=(List)request.getAttribute("dietInfoList");
	DietInfo dietInfo=(DietInfo)request.getAttribute("dietInfo");
%>
<!DOCTYPE html>
<!-- content 부분만 비워둔 기본 템플릿 -->
<!-- hero섹션이 포함되어있음 -->
<html lang="en">
<head>
<%@include file="../inc/header_link.jsp"%>
<script src="https://kit.fontawesome.com/99ef7b560b.js" crossorigin="anonymous"></script>

<style type="text/css">
.comment-right button{
	margin: 0 10px 0 10px;
}
.comment-content{
	cursor: pointer
}
</style>
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
					
				</div>
			</div>
		</div>
	</div>
	<!-- ./hero section end -->

	<!-- content start -->
	<div class="space-medium" id="app1">
		<div class="container">
			<div class="row">
				<div class="col">
                    <h1><%=dietInfo.getDietCategory().getDiet_category_name() %></h1>
                    <hr>
                    <h3></h3>
                    <br/>
                    <span><%=dietInfo.getTitle() %></span>
                    <span class="float-right"><%=dietInfo.getRegdate().substring(0,10) %></span>
                    <hr>
				</div>
			</div>
			<!-- end of row -->
			<br/>
			<div class="row">
				<div class="col-md-12">
					<%=	dietInfo.getContent() %>	
								
					<!-- 그래프 시작 -->
					<div class="col-md-12">
						<div class="progress-group">
							<span class="progress-text" style="color:#4374D9"><b>열량</b></span>
							<span class="progress-number" style="margin-left:980px"><b>160</b>/200</span>
							<div class="progress sm">
		  						<div class="progress-bar" style="width:60%"></div>
							</div>
						</div>
						<div class="progress-group">
							<span class="progress-text" style="color:#3c8dbc"><b>탄수화물</b></span>
							<span class="progress-number" style="margin-left:953px"><b>160</b>/200</span>
							<div class="progress sm">
		  						<div class="progress-bar bg-info" style="width:20%"></div>
							</div>
						</div>
						<div class="progress-group">
							<span class="progress-text" style="color:#CC3D3D"><b>단백질</b></span>
							<span class="progress-number" style="margin-left:967px"><b>160</b>/200</span>
							<div class="progress sm">
		  						<div class="progress-bar bg-danger" style="width:20%"></div>
							</div>
						</div>
						<div class="progress-group">
							<span class="progress-text" style="color:#f39c12"><b>지방</b></span>
							<span class="progress-number" style="margin-left:980px"><b>160</b>/200</span>
							<div class="progress sm">
		  						<div class="progress-bar bg-warning" style="width:20%"></div>
							</div>
						</div>
					</div>
					<!-- 그래프 끝 -->
				</div>	
			</div>
			<hr>
			<!-- end of row -->
			<button class="btn btn-primary float-right" id="bt_list">목록</button>
		</div>
		<!-- end of container -->
	</div>
	<!-- end of space-medium -->
	<!-- /content end -->

	<!-- black footer_space -->
	<%@include file="../inc/footer_space.jsp"%>



	<!-- tiny footer -->
	<%@include file="../inc/footer_tiny.jsp"%>

	<%@include file="../inc/footer_link.jsp"%>

</body>

<script type="text/javascript">
	$(function(){
		//목록버튼 
		$("#bt_list").click(function(){
			history.back();
		});
	});
</script>
</html>
