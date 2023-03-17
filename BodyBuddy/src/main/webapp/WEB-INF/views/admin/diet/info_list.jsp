<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@page import="com.edu.bodybuddy.domain.diet.DietInfo"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	List<DietInfo> dietInfoList=(List)request.getAttribute("dietInfoList");	
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>식단정보 관리자 페이지</title>
<%@ include file="../inc/header_link.jsp"%>
<style type="text/css">
	.box-style{
		width:90px;
		height:95px;
		border:1px solid #ccc;
		display:inline-block;
		margin:5px;
	}
	.box-style img{
		width:65px;
		height:55px;
	}
	.box-style div{
		text-align:right;
		margin-right:5px;
		font-weight:bold;
	}
</style>
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
							<h1 class="m-0">식단정보 관리자 페이지</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">상품관리> 상품리스트</li>
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
				<div class="container-fluid">
				
					<!-- Main row -->
					<div class="row">
						<div class="col">

							<div class="col-12">
								<div class="card">
									<div class="card-header">
										<h3 class="card-title"></h3>

										<div class="card-tools">
											<div class="input-group input-group-sm" style="width: 150px;">
												<input type="text" name="table_search" class="form-control float-right" placeholder="Search">

												<div class="input-group-append">
													<button type="submit" class="btn btn-default">
														<i class="fas fa-search"></i>
													</button>
												</div>
											</div>
										</div>
									</div>
									<!-- /.card-header -->
									<div class="card-body table-responsive p-0">
										<table class="table table-hover text-nowrap">
											<thead>
												<tr>
													<th>No</th>
													<th>분류</th>
													<th>제목</th>
													<th>소제목</th>
													<th>등록일</th>
												</tr>
											</thead>
											<tbody>
											<%for (DietInfo dietInfo:dietInfoList){ %>
												<tr onclick="location.href='/admin/diet/info_detail?diet_info_idx=<%=dietInfo.getDiet_info_idx() %>'">
													<td><%=dietInfo.getDiet_info_idx()%></td>
													<td><%=dietInfo.getDietCategory().getDiet_category_name()%></td>
													<td><%=dietInfo.getTitle()%></td>
													<td><%=dietInfo.getSubtitle()%></td>
													<td><%=dietInfo.getRegdate().substring(0,10)%></td>
													<td><img src="<%=dietInfo.getPreview()%>"  style="width:50px;height:50px;"></td>
												</tr>
											<%} %>

											</tbody>
										</table>
									</div>
									<!-- /.card-body -->
								</div>
								<!-- /.card -->
							</div>


							<div class="form-group row">
									<div class="col">
										<button type="button" class="btn btn-success float-right" id="bt_regist">글 쓰기</button>																	
									</div>
								</div>
								<!-- ./ -->
							
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
		$(function(){
			
			//글 쓰기 버튼 
			$("#bt_regist").click(function(){
				location.href="/admin/diet/info_registform";
			});
			
		});
	</script>
</body>
</html>

