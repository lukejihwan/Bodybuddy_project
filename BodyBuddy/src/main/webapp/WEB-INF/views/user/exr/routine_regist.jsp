<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../inc/header_link.jsp"%>
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
						<h1 class="hero-title">루틴 공유 게시판</h1>
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
		<div class="container">
			<h3>루틴 공유 입력폼</h3>
			<h1>Share Your Excercise Routine !</h1>
			<hr>
			<div class="row">
				<div class="col-sm-12">
					<form id="form1">
						<div class="form-group">
							<input type="text" class="form-control" name="title" placeholder="제목을 입력하세요" style="height:100px; font-size:30px">
						</div>
						<div class="form-group">
							<input type="text" class="form-control" name="writer" placeholder="작성자...">
						</div>
						
						<div class="form-group">
							<select class="form-control" name="exr_category_idx">
								<option value="0">카테고리 선택</option>
								<option value=""></option>
							</select>
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

	/***onLoad***/
	$(function(){
		$("#bt_list").click(function(){
			location.href="/exr/routine";
		});
		
		// 써머 노트 적용
		$('#summernote').summernote({
			height:400
		});
	});

</script>
</html>
