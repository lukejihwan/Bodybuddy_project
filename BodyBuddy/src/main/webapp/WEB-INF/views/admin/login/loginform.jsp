<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet" href="/resources/admin/plugins/fontawesome-free/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="/resources/admin/dist/css/adminlte.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript">
	
	function loginCheck() {
		let formdata = $("#form1").serialize();
		$.ajax({
			url : "login.jsp",
			type : "post",
			data : formdata,
			success : function(result) {
				$("#loding-bar").hide();
				console.log(result.msg);
				if(result.code==1){
					$(location).attr("href", "/admin")
				}
			}
		})
	}

	$(function() {
		$("#loding-bar").hide();

		$("#bt_login").click(function() {
			$("#loding-bar").show();
			loginCheck();

		})
	})
</script>
</head>
<body>
	<div class="wrapper">
		<div class="card card-info">
			<div class="card-header">
				<h3 class="card-title">관리자 로그인</h3>
			</div>
			<div class="form-group row">
					<div class="overlay" id="loding-bar">
						<i class="fas fa-3x fa-sync-alt fa-spin"></i>
						<div class="text-bold pt-2">Loading...</div>
					</div>
			</div>
			<!-- /.card-header -->
			<!-- form start -->
			<form class="form-horizontal" id="form1">
				<div class="card-body">
					<div class="form-group row">
						<label for="inputEmail3" class="col-sm-2 col-form-label">ID</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="admin_id" placeholder="아이디 입력">
						</div>
					</div>
					<div class="form-group row">
						<label for="inputPassword3" class="col-sm-2 col-form-label">Password</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" name="admin_pass" placeholder="비밀번호 입력">
						</div>
					</div>
					<div class="form-group row">
						<div class="offset-sm-2 col-sm-10">
							<div class="form-check">
								<input type="checkbox" class="form-check-input" id="exampleCheck2"> <label class="form-check-label" for="exampleCheck2">Remember me</label>
							</div>
						</div>
					</div>
				</div>
				<!-- /.card-body -->
				<div class="card-footer">
					<button type="button" class="btn btn-info" id="bt_login">로그인</button>
					<button type="button" class="btn btn-default float-right" id="bt_cancel">취소</button>
				</div>
				<!-- /.card-footer -->
			</form>
		</div>
	</div>
</body>

</html>