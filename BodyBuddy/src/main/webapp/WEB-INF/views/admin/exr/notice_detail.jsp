<%@page import="com.edu.bodybuddy.domain.exr.ExrNoticeImg"%>
<%@page import="com.edu.bodybuddy.domain.exr.ExrNotice"%>
<%@page import="com.edu.bodybuddy.domain.exr.ExrCategory"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	ExrNotice exrNotice=(ExrNotice)request.getAttribute("exrNotice");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>운동 정보 관리자 페이지</title>
<%@ include file="../inc/header_link.jsp"%>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">

		<!-- Preloader -->
		<%@ include file="../inc/preloader.jsp" %>
		
		<!-- Navbar -->
		<%@ include file="../inc/navbar.jsp" %>
		<!-- /.navbar -->

		<!-- Main Sidebar Container -->
		<%@ include file="../inc/sidebar_left.jsp" %>
		
		
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">운동 정보 관리자 페이지</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">상품관리> 상품등록</li>
							</ol>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">
				<!-- Main row -->
					<div class="row">
						<div class="col">
								
								<!-- 입력 요소들 -->
									<form id="form1">
										<div class="form-group row">
											<div class="col">
												
												<input type="hidden" name="exr_notice_idx" class="form-control" value="<%=exrNotice.getExr_notice_idx() %>">
												<input type="text" name="title" class="form-control" value="<%=exrNotice.getTitle() %>">
											</div>
										</div>						
		
									
										<div class="form-group row">
											<div class="col">
												<textarea id="summernote" name="content" class="form-control"><%=exrNotice.getContent() %></textarea>
											</div>
										</div>
									</form>
									
									<div class="form-group row">
										<div class="col">
											<button type="button" class="btn btn-outline-danger" id="bt_list">목록</button>									
											<button type="button" class="btn btn-outline-danger" id="bt_edit">수정</button>									
											<button type="button" class="btn btn-outline-danger" id="bt_del">삭제</button>									
										</div>
									</div>
								<!-- 입력 요소들./ -->
							
						</div>
					</div>
					<!-- /.row (main row) -->
				</div>
				<!-- /.container-fluid -->
			
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		
		<%@ include file="../inc/footer.jsp" %>		

		<!-- Control Sidebar -->
		<%@ include file="../inc/sidebar_right.jsp" %>
		<!-- /.control-sidebar -->
	</div>
	<!-- ./wrapper -->
	<%@ include file="../inc/footer_link.jsp" %>
	<script type="text/javascript">
	
	
	// 사진 배열 때문에 비동기 전송해야 할 것 같은데..
	function edit(){
		let json={};
		json['exr_notice_idx'] = $("#form1 input[name='exr_notice_idx']").val();
		json['title'] = $("#form1 input[name='title']").val();
		json['content'] = $("#form1 textarea[name='content']").val();
		
		$.ajax({
			url:"/admin/rest/exr/notice",
			type:"PUT",
			contentType:"application/json;charset=utf-8",
			processData : false,
			data:JSON.stringify(json),
			
			success:function(result, status, xhr){
				alert(result.msg);
				
				// redirect
				//location.href="/admin/exr/notice/detail?exr_notice_idx="+$("#form1 input[name='exr_notice_idx']").val();
			}
		});
		
	}
	
	
	/***	onLoad ***/
	$(function(){
		
		
		// 리스트 페이지 이동
		$("#bt_list").click(function(){
			location.href="/admin/exr/notice/list";
		});

		
		// 글 수정
		$("#bt_edit").click(function(){
			if(confirm("수정하시겠습니까?")){
				edit();
			}
		});
		
		
		// 글 삭제
		$("#bt_del").click(function(){
			if(!confirm("게시글을 삭제하시겠습니까?")){
				return;
			}
			
			location.href="/admin/exr/notice/delete?exr_notice_idx="+$("input[name='exr_notice_idx']").val();
		});
		
		
		// 써머 노트 적용
		$('#summernote').summernote({
			height:600
		});
		
	});

	</script>
</body>
</html>