<%@page import="com.edu.bodybuddy.domain.exr.ExrNotice"%>
<%@page import="com.edu.bodybuddy.domain.exr.ExrCategory"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	List<ExrNotice> exrNoticeList=(List)request.getAttribute("exrNoticeList");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>운동 정보 관리자 페이지</title>
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
			<section class="content" id="app1">
				<div class="container-fluid">
				
					<!-- Main row -->
					<div class="row">
						<div class="col">



							<div class="col-12">
								<div class="card">
									<div class="card-header">
										<h3 class="card-title">ExrNotice Table List</h3>

										<div class="card-tools">
											<div class="input-group input-group-sm" style="width: 150px;">
												<input type="text" name="table_search"
													class="form-control float-right" placeholder="Search">

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
													<th>pk</th>
													<th>카테고리</th>
													<th>제목</th>
													<th>이미지 개수</th>
													<th>등록일</th>
												</tr>
											</thead>
											<tbody>
											<%for(ExrNotice exrNotice:exrNoticeList){ %>
												<tr>
													<td><%=exrNotice.getExr_notice_idx() %></td>
													<td><%=exrNotice.getExrCategory().getExr_category_name() %></td>
													<td><a href="/admin/exr/notice/detail?exr_notice_idx=<%=exrNotice.getExr_notice_idx() %>"><%=exrNotice.getTitle() %></a></td>
													<td><%=exrNotice.getExrNoticeImgList().size() %></td>
													<td><%=exrNotice.getRegdate()%></td>
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
										<button type="button" class="btn btn-outline-danger" id="bt_regist">등록</button>									
										<button type="button" class="btn btn-outline-danger" id="bt_list">목록</button>									
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

	</script>
</body>
</html>

