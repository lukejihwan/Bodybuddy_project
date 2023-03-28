<%@page import="com.edu.bodybuddy.domain.exr.ExrCategory"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
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
	
		<!-- 로그인체크 -->
	<%@include file="../inc/loginCheck.jsp"%>

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
                      	<p class="hero-text">오늘의 러닝 기록을 공유해보세요</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- ./hero section end -->

	<!-- content start -->
	<div class="space-medium">
		<div class="container">
			<h3>오운완 입력폼</h3>
			<hr>
			<div class="row">
				<div class="col-sm-12">
					<form id="form1">
						<div class="form-group">
						<input type="hidden" name="member_idx" value="<sec:authorize access="isAuthenticated()"><sec:authentication property="principal.member.member_idx"/></sec:authorize>">
							<input type="text" class="form-control" name="title" placeholder="[scrap]" >
						</div>
						<div class="form-group">
							<input type="text" class="form-control" name="writer" value="<sec:authorize access="isAuthenticated()"><sec:authentication property="principal.member.nickname"/></sec:authorize>">
						</div>


						<div class="form-group">
							<textarea id="summernote" name="content"></textarea>
						</div>
						
						<div class="form-group">
							<button type="button" class="btn btn-primary" id="bt_list">목록</button>
							<button type="button" class="btn btn-default pull-right" id="bt_regist">등록</button>
						</div>
					</form>
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

	function registAsync(){
		let formData=new FormData();
		formData.append("member.member_idx", $("#form1 *[name='member_idx']").val());
		formData.append("title", $("#form1 input[name='title']").val());
		formData.append("writer", $("#form1 input[name='writer']").val());
		formData.append("content", $("#form1 textarea[name='content']").val());
		
			$.ajax({
			url:"/rest/exr/today",
			type:"post",
			contentType:false,
			processData:false,
			data:formData,
			
			success:function(result, status, xhr){
				alert(result.msg);
				console.log("성공시 출력 ", result);
				location.href="/exr/today_list";
			},
			
			error:function(xhr, status, err){
				console.log("에러시 출력 ", xhr.responseText);
			}
		});
	}


	/***onLoad***/
	$(function(){
		
		// 목록 페이지 이동
		$("#bt_list").click(function(){
			location.href="/exr/today_list";
		});
		
		// 등록
		$("#bt_regist").click(function(){
			registAsync();

		});
		
		
		// 써머 노트 적용
		$('#summernote').summernote({
			height:400
		});
	});

</script>
</html>
