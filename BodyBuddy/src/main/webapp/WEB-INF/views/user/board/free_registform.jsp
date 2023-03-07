<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<!-- content 부분만 비워둔 기본 템플릿 -->
<!-- hero섹션이 포함되어있음 -->
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
						<h1 class="hero-title">자유게시판</h1>
						<p class="small-caps mb30 text-white"></p>
						<p class="hero-text">자유롭게 소통하는 게시판입니다</p>
						<!-- <a href="classes-list.html" class="btn btn-default">링크 필요하면
							사용할 버튼</a> -->
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
				<form id="form1">
					<div class="form-group">
						<input type="text" class="form-control" name="title" placeholder="제목...">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" name="writer" placeholder="작성자...">
					</div>
					<div class="form-group">
						<textarea id="summernote" name="content"></textarea>
					</div>
					<div class="form-group">
						<button type="button" class="btn btn-default" id="bt_regist">등록</button>
						<button type="button" class="btn btn-primary" id="bt_list">목록</button>
					</div>
				</form>
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
	$(()=>{
		$('#summernote').summernote({
			minHeight:200
		});
		
		$("#bt_regist").click(()=>{
			regist();
		});
		
		$("#bt_list").click(()=>{
			location.href = "/board/free_list/1";
		});
	});
	
	function regist() {
		$("#form1").attr({
			action:"/board/free_regist",
			method:"POST"
		});
		
		$("#form1").submit();
	}
</script>
</html>
