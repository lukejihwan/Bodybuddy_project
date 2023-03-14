<%@page import="com.edu.bodybuddy.domain.diet.DietCategory"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<%
	List<DietCategory> dietCategoryList=(List)request.getAttribute("dietCategoryList");
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
						<form id="form1">
							<div class="col">
								<input type="hidden" name="_method" />
								<input type="hidden" name="diet_category_idx" /> 
							  	<input type="text" class="form-control col-sm-10" placeholder="카테고리 입력" name="diet_category_name">
								<button type="button" class="btn btn-success" id="bt_category_regist">등록</button>
							</div>
						</form>
							<div>
							<table class="table table-hover">
								<thead>
									<tr>
										<th>번호</th>
										<th>식단분류</th>
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
								<div class="col">
									<button type="button" class="btn btn-success" id="bt_category_edit">수정</button>
									<button type="button" class="btn btn-success" id="bt_category_del">삭제</button>
								</div>
							<button type="button" class="btn btn-success" data-dismiss="modal">닫기</button>
							</div>
					</div>
				</div>
			</div>
			<!-- 모달 끝/ -->
			
				<form id="form2">
				<div class="container-fluid">
					<!-- 내용 -->
					<div class="row">
						<div class="col">
							<div class="form-group row">
								<div class="form-group row col-sm-3">
									<button type="button" class="btn  btn-success btn-md" data-toggle="modal" data-target="#myModal" id="bt_category">카테고리설정</button>
								</div>
								
								<select class="form-control" name="category_idx">
									<option value="0">카테고리 선택</option>
									<%
									for (DietCategory dietCategory : dietCategoryList) {
									%>
									<option value="<%=dietCategory.getDiet_category_idx()%>"><%=dietCategory.getDiet_category_name()%></option>
									<%}%>
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
									<button type="button" class="btn btn-success btn-md" id="bt_list">목록</button>
									<button type="button" class="btn btn-success btn-md" id="bt_regist">등록</button>
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
	let app1;
	let selectRow;
	
	const row={
			template:`
				<tr>
					<td>{{category.diet_category_idx}}</td>
					<td @click="getDetail(category)"><a href="#">{{category.diet_category_name}}</a></td>
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
					$("#form1 input[name='diet_category_idx']").val(category.diet_category_idx);
					$("#form1 input[name='diet_category_name']").val(category.diet_category_name);
					selectRow=this; //수정된 내용 컴포넌트에 반영
				}
			}
	};
	
	app1=new Vue({
		el:"#app1",
		components:{
			row
		},
		data:{
			categoryList:[]
		}	
	});
	
	
	/*------------------------------------
				카테고리 관련 
	-------------------------------------*/
	
	//카테고리 등록
	function categoryRegist(){
		$.ajax({
			url:"/admin/rest/diet/category",
			type:"post",
			data:{
				diet_category_name:$("input[name='diet_category_name']").val()
			},
			success:function(result, status,xhr){
				categoryList();
				
				// 내용 비워주기
				$("input[name='diet_category_name']").val("");
			},
			error:function(xhr, status, err){
				console.log("오류ㅜㅜ");
			}
		});
	}
	
	//카테고리 리스트
	function categoryList(){
		$.ajax({
			url:"/admin/rest/diet/list",
			type:"get",
			success:function(result, status, xhr){
				app1.categoryList=result;
			}
		});
	}
	
	//카테고리 수정
	function categoryEdit(){
		if(!confirm("수정하시겠습니까?")){
			return;
		}
		
		let json={};
		json['diet_category_idx']=$("#form1 input[name='diet_category_idx']").val();
		json['diet_category_name']=$("#form1 input[name='diet_category_name']").val();
		
		$.ajax({
			url:"/admin/rest/diet/category",
			type:"put",
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify(json), 
			processData:false,
			success:function(result, status, xhr){
				selectRow.category=json;
				
				// 내용 비워주기
				$("input[name='diet_category_name']").val("");
			}
		});
	}
	
	//카테고리 삭제 
	function categoryDel(){
		if(!confirm("삭제하시겠습니까?")){
			return;
		}	
		
		$.ajax({
			url:"/admin/rest/diet/category/"+$("#form1 input[name='diet_category_idx']").val(),
			type:"delete",
			success:function(result, status, xhr){
				categoryList();
				
				// 내용 비워주기
				$("input[name='diet_category_name']").val("");
			}
		});
	}
	


	
		
	/*------------------------------------
					버튼 등록
	-------------------------------------*/	
	$(function() {

		//카테고리 생성
		categoryList();
		
		//모달 카테고리 등록버튼
		$("#bt_category_regist").click(function(){
			categoryRegist();
		});
		
		//모달 카테고리 수정버튼
		$("#bt_category_edit").click(function(){
			categoryEdit();
		});
		
		//모달 카테고리 삭제버튼
		$("#bt_category_del").click(function(){
			categoryDel();
		});
	});
</script>

</body>
</html>

