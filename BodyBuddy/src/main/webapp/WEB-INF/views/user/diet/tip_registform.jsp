<%@page import="java.util.List"%>
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
				
			</div>
		</div>
	</div>
	<!-- ./hero section end -->

	<!-- content start -->
	<div class="space-medium">
		<div class="container">
			<h1>식단 팁 게시판</h1>
			<hr>
			<div class="row">
				<div class="col-sm-12">
					<form id="form">
						<div class="form-group">
							<input type="text" class="form-control" name="title" placeholder="제목을 입력하세요" style="height:100px; font-size:30px">
						</div>
						<div class="form-group">
							<!-- 작성자... -->
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
	//글 등록하기
	function regist(){
		let formData=new FormData();
		
		formData.append("title", $("#form input[name='title']").val());
		formData.append("content", $("#form textarea[name='content']").val());
		
		$.ajax({
			url:"/rest/diet/tip_regist",
			type:"post",
			contentType:false,
			processData:false,
			data:formData,
			success:function(result, status, xhr){
				alert("글 등록 완료");
				location.href="/diet/tip_list"
			}
		});
	}

	$(function(){
		// 써머 노트 적용
		$('#summernote').summernote({
			height:400
		});
		
		// 목록 페이지 이동
		$("#bt_list").click(function(){
			location.href="/diet/tip_list";
		});
		
		// 등록
		$("#bt_regist").click(function(){
			regist();
		});

	});

</script>
</html>
