<%@page import="com.edu.bodybuddy.domain.exr.ExrToday"%>
<%@page import="com.edu.bodybuddy.domain.exr.ExrTip"%>
<%@page import="com.edu.bodybuddy.domain.exr.ExrRoutine"%>
<%@page import="com.edu.bodybuddy.domain.exr.ExrCategory"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	ExrToday exrToday=(ExrToday)request.getAttribute("exrToday");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../inc/header_link.jsp"%>
<style type="text/css">
	.hero-section{
		background-image: url("/resources/user/images/exr/todaybg.jpg");
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
					<div class="hero-caption pinside50">
						<h1 class="hero-title">오운완 게시판</h1>
						<p class="small-caps mb30 text-white">BodyBuddy Excercise Routine Share Here.</p>
                      	<p class="hero-text">자신만의 운동 루틴을 기록해보세요!</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- ./hero section end -->

	<!-- content start -->
	<div class="space-medium">
		<div class="container" id="app1">
			<h3>오운완 게시판</h3>
			<h1>Share Your Excercise Routine !</h1>
			<hr>
			
			<div class="row">
				<div class="col-sm-12">
					<form id="form1">
						<div class="form-group">
							<input type="hidden" name="exr_today_idx" value="<%=exrToday.getExr_today_idx()%>">
							<input type="text" class="form-control" name="title" value="<%=exrToday.getTitle()%>" style="height: 100px; font-size: 30px">
						</div>
						<div class="form-group">
							<input type="text" class="form-control" name="writer" value="<%=exrToday.getWriter()%>">
						</div>

						<div class="form-group">
							<textarea id="summernote" name="content"><%=exrToday.getContent()%></textarea>
						</div>

						<div class="form-group">
							<button type="button" class="btn btn-primary" id="bt_list">목록</button>
							<button type="button" class="btn btn-outline-success" id="bt_edit">재등록</button>
							<button type="button" class="btn btn-outline-danger" id="bt_delete">삭제</button>
						</div>
					</form>

					<hr>

				</div>

			</div>
		</div>
			<!-- end of row -->
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

	// 수정
	function edit(){
		let json={};
		json['exr_today_idx'] = $("#form1 input[name='exr_today_idx']").val();
		json['title'] = $("#form1 input[name='title']").val();
		json['writer'] = $("#form1 input[name='writer']").val();
		json['content'] = $("#form1 textarea[name='content']").val();
		
		$.ajax({
			url:"/rest/exr/today",
			type:"PUT",
			contentType:"application/json;charset=utf-8",
			processData : false,
			data:JSON.stringify(json),
			
			success:function(result, status, xhr){
				alert(result.msg);
				// redirect
				location.href="/exr/today/detail?exr_today_idx="+$("#form1 input[name='exr_today_idx']").val();
			}
		});
		
	}
	
	
	
	/***onLoad***/
	$(function() {
		
		// 목록 페이지 이동
		$("#bt_list").click(function() {
			location.href = "/exr/today_list";
		});
		

		// 수정
		$("#bt_edit").click(function() {
			if (confirm("수정하시겠습니까?")) {
				edit();
			}
		});
		
		
		// 삭제
		$("#bt_delete").click(function() {
			if (confirm("삭제하시겠습니까?")) {
				location.href = "/exr/today/delete?exr_today_idx="+$("input[name='exr_today_idx']").val();
			}
		});

		
		// 써머 노트 적용
		$('#summernote').summernote({
			height : 600
		});

	});
</script>
</html>
