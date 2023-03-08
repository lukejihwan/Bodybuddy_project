<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>식단정보 등록페이지</title>
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
								<li class="breadcrumb-item active">식단정보관리> 식단정보등록</li>
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

							<div class="form-group row">
								<div class="col">
									<input type="text" name="product_name" class="form-control"
										placeholder=글제목">
								</div>
							</div>
							<div class="form-group row">
								<div class="col">
									<input type="text" name="product_name" class="form-control"
										placeholder="소제목">
								</div>
							</div>
							<div class="form-group row">
								<div class="col">
									<textarea name="detail" class="form-control" id="detail">글내용</textarea>
								</div>
							</div>

							<!-- 사진등록 -->
							<div class="form-group row">
								<div class="col">
									<input type="file" name="file" class="form-control" multiple>
								</div>
							</div>

							<div class="form-group row">
								<div class="col">
									<div class="col-sm-6">
										<!-- checkbox -->
										<div class="form-group">
											<div class="custom-control custom-checkbox">
												<input class="custom-control-input" type="checkbox"
													id="customCheckbox1" value="option1"> <label
													for="customCheckbox1" class="custom-control-label">Custom
													Checkbox</label>
											</div>
											<div class="custom-control custom-checkbox">
												<input class="custom-control-input" type="checkbox"
													id="customCheckbox2" checked=""> <label
													for="customCheckbox2" class="custom-control-label">Custom
													Checkbox checked</label>
											</div>
										</div>
									</div>
								</div>
							</div>



							<div class="form-group row">
								<div class="col">
									<template v-for="json in imageList">
										<imagebox :src="json.src" :key="json.key" :idx="json.key" />
									</template>
								</div>
							</div>



							<div class="form-group row">
								<div class="col">
									<button type="button" class="btn btn-success btn-md"
										id="bt_regist">등록</button>
									<button type="button" class="btn btn-success btn-md"
										id="bt_list">목록</button>
								</div>
							</div>

						</div>
					</div>
					<!-- /.row (main row) -->
				</div>
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
</body>
</html>

