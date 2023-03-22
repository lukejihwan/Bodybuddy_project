<%@page import="com.edu.bodybuddy.domain.diet.DietInfo"%>
<%@page import="com.edu.bodybuddy.domain.diet.DietCategory"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<%
	DietInfo dietInfo=(DietInfo)request.getAttribute("dietInfo");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>식단카테고리등록</title>
<%@ include file="../inc/header_link.jsp"%>
<style type="text/css">
.box-style {
	width: 90px;
	height: 95px;
	border: 1px solid #ccc;
	display: inline-block;
	margin: 5px;
}

.box-style img {
	width: 65px;
	height: 55px;
}

.box-style div {
	text-align: right;
	margin-right: 5px;
	font-weight: bold;
}
</style>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">

		<!-- Preloader -->
		<%@ include file="../inc/preloader.jsp"%>

		<!-- Navbar -->
		<%@ include file="../inc/navbar.jsp"%>
		<!-- /.navbar -->

		<!-- Main Sidebar Container -->
		<%@ include file="../inc/sidebar_left.jsp"%>


		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">식단정보등록</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">식단게시판> 식단정보등록</li>
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
			<section class="content" id="app1">
			<form id="form1">
				<div class="container-fluid">
					<!-- 내용 -->
					<div class="row">
						<div class="col">
							<div class="form-group row">
								<div class="form-group row col-sm-3">
									
								</div>
							</div>

							<div class="form-group row">
								<div class="col">
									<input type="hidden" name="diet_info_idx" class="form-control" value="<%=dietInfo.getDiet_info_idx()%>">
									<input type="text" name="title" class="form-control" value="<%=dietInfo.getTitle()%>">
								</div>
							</div>
							
							<div class="form-group row">
								<div class="col">
									<input type="text" name="subtitle" class="form-control" value="<%=dietInfo.getSubtitle()%>">
								</div>
							</div>

							<div class="form-group">
								<textarea id="summernote" name="content"><%=dietInfo.getContent() %></textarea>
							</div>
							
							<div class="form-group">
								<input type="hidden" name="preview" value="<%=dietInfo.getPreview()%>">
							</div>
							
							<div class="form-group row">
								<input type="number" name="kcal" class="form-control" value="<%=dietInfo.getKcal()%>">
							</div>
							<div class="form-group row">
								<input type="number" name="carbohydrate" class="form-control" value="<%=dietInfo.getCarbohydrate()%>">
							</div>
							<div class="form-group row">
								<input type="number" name="protein" class="form-control" value="<%=dietInfo.getProtein()%>">
							</div>
							<div class="form-group row">
								<input type="number" name="province" class="form-control" value="<%=dietInfo.getProvince()%>">
							</div>
							
							<div class="form-group row">
								<div class="col">
									<button type="button" class="btn btn-light" id="bt_list">취소</button>
									<button type="button" class="btn btn-success float-right" id="bt_del">삭제</button>
									<button type="button" class="btn btn-success float-right" id="bt_edit">수정</button>
								</div>
							</div>
						</div>
					</div>
				
					<!-- /.row (main row) -->
				</div>
				</form>
				<!-- /.container-fluid -->

			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<%@ include file="../inc/footer.jsp"%>

		<!-- Control Sidebar -->
		<%@ include file="../inc/sidebar_right.jsp"%>
		<!-- /.control-sidebar -->
	</div>
	<!-- ./wrapper -->
	<%@ include file="../inc/footer_link.jsp"%>


	<script type="text/javascript">
		
		//글 수정 
		function edit(){
			if(!confirm("수정하시겠습니까?")){
				return;
			}
			getPreview();
			let json={};
			
			json['diet_info_idx']=$("#form1 input[name='diet_info_idx']").val();
			json['title']=$("#form1 input[name='title']").val();
			json['subtitle']=$("#form1 input[name='subtitle']").val();
			json['content']=$("#form1 textarea[name='content']").val();
			json['preview']=$("#form1 input[name='preview']").val();
			json['kcal']=$("#form1 input[name='kcal']").val();
			json['carbohydrate']=$("#form1 input[name='carbohydrate']").val();
			json['protein']=$("#form1 input[name='protein']").val();
			json['province']=$("#form1 input[name='province']").val();
			
			$.ajax({
				url:"/admin/rest/diet/edit",
				type:"put",
				contentType:"application/json;charset=utf-8",
				data:JSON.stringify(json), 
				processData:false,
				success:function(result, status, xhr){
					alert("글 수정 완료");
					location.href="/admin/diet/info_list";
				}
			});
		}
		
		//글 삭제 
		function del(){
			if(!confirm("삭제하시겠습니까?")){
				return;
			}	
			
			$.ajax({
				url:"/admin/rest/diet/del/"+$("#form1 input[name='diet_info_idx']").val(),
				type:"delete",
				success:function(result, status, xhr){
					alert("글 삭제 완료");
					location.href="/admin/diet/info_list";
				}
			});
		}
		
		//프리뷰 구하기
		function getPreview(){
			let domParser = new DOMParser();
			let doc = domParser.parseFromString($("#form1 textarea[name='content']").val(), "text/html");
			if(doc.querySelector("img") != null){
				$("#form1 input[name='preview']").val(doc.querySelector("img").src);
			}
		}
	
	
	
		$(function(){
			// 써머 노트 적용
			$('#summernote').summernote({
				height:400
			});		
			
			//글 쓰기 버튼 
			$("#bt_regist").click(function(){
				location.href="/admin/diet/info_registform";
			});
			
			//글 수정 버튼 
			$("#bt_edit").click(function(){
				edit();
			});
			
			//글 삭제 버튼 
			$("#bt_del").click(function(){
				del();
			});
			
		});
	</script>

</body>
</html>

