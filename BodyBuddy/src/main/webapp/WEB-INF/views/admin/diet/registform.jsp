<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>식단카테고리등록</title>
<%@ include file="../inc/header_link.jsp"%>
<!-- include libraries(jQuery, bootstrap) -->

<!-- include summernote css/js-->
<link href="summernote-bs5.css" rel="stylesheet">
<script src="summernote-bs5.js"></script>
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
				<div class="container-fluid">
	
					<!-- 카테고리 추가 폼 디자인-->
						<div class="modal" id="myModal">
								<div class="modal-dialog modal-lg">
									<div class="modal-content">

										<!-- Modal Header -->
										<div class="modal-header">
											<h4 class="modal-title">식단 카테고리</h4>
											<button type="button" class="close" data-dismiss="modal">&times;</button>
										</div>

										<!-- Modal body -->
										<div class="modal-body"></div>
											<form id="form1">
												<input type="hidden" name="_method" value="PUT"/>
												<div class="col">
													<input type="text" name="diet_category_name" >
													<button type="button" class="btn btn-success" id="bt_category_regist">등록</button>											    
												</div>
											</form>
											
											<div>
												 <table class="table table-hover">
												    <thead>
												      <tr>
												        <th>번호</th>
												        <th colspan="2">식단분류</th>
												        <th></th>												        
												      </tr>
												    </thead>
												    <tbody>
												      <template v-for="category in categoryList">
												      	<row :obj="category" :key="category.diet_category_idx" />
												      </template>
												    </tbody>
												  </table>
											</div>

										<!-- Modal footer -->
										<div class="modal-footer">
											<button type="button" class="btn btn-success"
												data-dismiss="modal">닫기</button>
										</div>

									</div>
								</div>
							</div>
					<!-- 모달 끝/ -->
	

					<!-- 내용 -->
					<div class="row">
						<div class="col">
							<div class="form-group row">
								<div class="form-group row col-sm-3">
								<button type="button" class="btn  btn-success btn-md" 
									data-toggle="modal" data-target="#myModal" id="bt_category">카테고리설정</button>
								</div>
								
								<select class="form-control" name="category_idx">
									<option value="0">카테고리 선택</option>
									<option value=""></option>
								</select>
							</div>

							<div class="form-group row">
								<div class="col">
									<input type="text" name="product_name" class="form-control"
										placeholder="제목">
								</div>
							</div>
							
							<div class="form-group row">
								<div class="col">
									<textarea name="detail" class="form-control" id="detail">내용</textarea>
								</div>
							</div>
							<div class="form-group row">
								<div class="col">
									<div class="custom-control custom-checkbox">
                          				<input class="custom-control-input" type="checkbox" id="customCheckbox1" value="option1">
                          				<label for="customCheckbox1" class="custom-control-label">~1000kcal</label>
                        			</div>
									<div class="custom-control custom-checkbox">
                          				<input class="custom-control-input" type="checkbox" id="customCheckbox2" value="option2">
                          				<label for="customCheckbox2" class="custom-control-label">~1300kcal</label>
                        			</div>
									<div class="custom-control custom-checkbox">
                          				<input class="custom-control-input" type="checkbox" id="customCheckbox3" value="option3">
                          				<label for="customCheckbox3" class="custom-control-label">~1500kcal</label>
                        			</div>
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
	
	
	<script type="text/javascript">
		let app1;

		/*------------------------------------
						테이블 뷰
		-------------------------------------*/
		const row={
				template:`
					<tr>
						<td>{{category.diet_category_idx}}</td>
						<td @click="getDetail(category)"><a href="#">{{category.diet_category_name}}</a></td>
						<td><button class="btn btn-success" id="bt_category_edit" @click="edit(category)">수정</button></td>
						<td><button class="btn btn-success" id="bt_category_del" @click="del(category)">삭제</button></td>
					</tr>
				`,
				props:["obj"],
				data(){
					return{
						category:this.obj	
					}
				},
				methods:{
					getDetail:function(category){
						$("input[name='diet_category_name']").val(category.diet_category_name);
					},
					edit:function(category){
						let json={};
						json['diet_category_name']=$("#form1 input[name='diet_category_name']").val();
						
						$.ajax({
							url:"/admin/rest/diet/category",
							type:"put",
							contentType:"application/json;charset=utf-8",
							data:JSON.stringify(json),
							success:function(result,status,xhr){
								getCategoryList();
							}
						});
					},
					del:function(diet_category_idx){
						$.ajax({
							url:"/admin/rest/diet/category"+diet_category_idx,
							type:"delete",
							success:function(result, status, xhr){
								getCategoryList();
							}
						});
					}
				}
		}

		
		/*------------------------------------
					카테고리 등록하기
		-------------------------------------*/
		function categoryRegist(){
			$.ajax({
				url:"/admin/rest/diet/regist",
				type:"post",
				data:{
					diet_category_name:$("input[name='diet_category_name']").val()
				},
				success:function(result, status,xhr){
					getCategoryList();
				}
			});
		}
		
		
		
		/*------------------------------------
					카테고리 불러오기
		-------------------------------------*/
		function getCategoryList(){
			$.ajax({
				url:"/admin/rest/diet/category_list",
				type:"get",
				success:function(result, status,xhr){
					app1.categoryList=result;
				}
				
			});
		}
		
		
		function init(){
			app1=new Vue({
				el:"#app1",
				data:{
						categoryList:[]		
				},
				conponents:{
					row
				}
			});
		}
	
	
	
		$(function() {
			// 써머 노트 적용
			$('#detail').summernote({
				height : 200
			});
			
			//뷰 적용
			init();
			
			//카테고리 가져오기
			getCategoryList();
			
			//모달 등록버튼
			$("bt_category_regist").click(function(){
				categoryRegist();
			});
			

		});
	</script>
	
	</body>
</html>

	