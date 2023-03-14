<%@page import="com.edu.bodybuddy.domain.exr.ExrCategory"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	List<ExrCategory> exrCategoryList=(List)request.getAttribute("exrCategoryList");
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

							
								<div class="form-group row">
									<!-- 셀렉박스 이사감 -->
									
									<button type="button" class="btn btn-danger btn-md" data-toggle="modal" data-target="#myModal" id="bt_category">카테고리 관리하기</button>


									<!-- 카테고리 입력폼이 될 모달 -->
									<div class="modal" id="myModal">
										<div class="modal-dialog">
											<div class="modal-content">

												<!-- Modal Header -->
												<div class="modal-header">
													<h4 class="modal-title">운동 카테고리 관리하기</h4>
													<button type="button" class="close" data-dismiss="modal">&times;</button>
												</div>

												<!-- Modal body -->
												<div class="modal-body">
													<form id="form1">

														<div class="col">
															<input type="text" name="exr_category_name">
															<button type="button" class="btn btn-warning" id="bt_category_regist">등록</button>
														</div>
													</form>

													<div>
														<table class="table">
															<thead>
																<tr>
																	<th>카테고리 번호</th>
																	<th>카테고리 이름</th>
																	<th>수정</th>
																	<th>삭제</th>
																</tr>
															</thead>
															<tbody>
																<template v-for="category in categoryList">
																	<row :category="category"
																		:key="category.exr_category_idx" />
																</template>
															</tbody>
														</table>
													</div>

												</div>
												<!-- Modal body./ -->

												<!-- Modal footer -->
												<div class="modal-footer">
													<button type="button" class="btn btn-danger"
														data-dismiss="modal">Close</button>
												</div>

											</div>
										</div>
									</div>
									<!-- 카테고리 모달./ -->
								</div>
								
								
								<form id="form2">
								<select class="form-control" name="exr_category_idx">
									<option value="0">카테고리 선택</option>
									<%for (ExrCategory category : exrCategoryList) {%>
									<option value="<%=category.getExr_category_idx()%>"><%=category.getExr_category_name()%></option>
									<%}%>

								</select>


								<div class="form-group row">
									<div class="col">
										<input type="text" name="title" class="form-control" placeholder="제목">
									</div>
								</div>						
											
								
								<div class="form-group row">
									<div class="col">
										<input type="file" name="file" class="form-control" multiple>
									</div>
								</div>
								
								<div class="form-group row">
									<div class="col">
										<template v-for="json in imageList">
											<imagebox :src="json.src" :key="json.key" :idx="json.key"/>
										</template>
									</div>
								</div>
												
								<div class="form-group row">
									<div class="col">
										<textarea name="content1" class="form-control">내용1</textarea>
									</div>
								</div>
												
								<div class="form-group row">
									<div class="col">
										<textarea name="content2" class="form-control">내용2</textarea>
									</div>
								</div>
								</form>
								
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
	let app1;
	let key=0;

	
	/*----------------------
		글 등록
	----------------------*/ 
	function regist(){
	
		let formData=new FormData();
		formData.append("exrCategory.exr_category_idx", $("#form2 select[name='exr_category_idx']").val());
		formData.append("title", $("#form2 input[name='title']").val());
		formData.append("content1", $("#form2 textarea[name='content1']").val());
		formData.append("content2", $("#form2 textarea[name='content2']").val());
		

		for(let i=0; i<app1.imageList.length; i++){
			let file=app1.imageList[i].file;
			formData.append("photo", file);
		}

 		$.ajax({
			url:"/admin/rest/exr/notice",
			type:"post",
			contentType:false,
			processData:false,
			data:formData,
			
			success:function(result, status, xhr){
				alert(result.msg);
				console.log("성공시 출력 ", result.msg);
				
				// 내용 비워주기
				$("input[name='title']").val("");
				$("textarea[name='content']").val("");
			},
			
			error:function(xhr, status, err){
				console.log("err ", err);
				console.log("status ", status);
				console.log("xhr ", xhr);
				console.log("에러시 출력 ", xhr.responseText);
				
			}
		});
	}
	
	
	/*----------------------
		모달창 등록
	----------------------*/ 
	function categoryRegist(){
		console.log($("input[name='exr_category_name']").val());
		
		$.ajax({
			url:"/admin/rest/exr/category",
			type:"post",
			data:{
				exr_category_name:$("input[name='exr_category_name']").val()
			},
			success:function(result, status, xhr){
				console.log("결과 "+result);
				getCategoryList();
			},
			error:function(xhr, status, err){
				console.log("err ", err);
				console.log("status ", status);
				console.log("xhr ", xhr);
				
				let json=JSON.parse(xhr.responseText);		// string --> json
				console.log("에러 발생시 출력", json.msg);
			}
		});
		
	}
	
	
	
	
	/*--------------------------
		카테고리 목록 테이블 뷰
 	 --------------------------*/ 
	const row={
			template:`
				<tr>
					<td>{{dto.exr_category_idx}}</td>
					<td @click="getDetail(dto)"><a href=#>{{dto.exr_category_name}}</a></td>
					<td><button type="button" class="btn btn-warning" id="bt_category_edit" @click="edit(dto)">수정</button></td>
					<td><button type="button" class="btn btn-warning" id="bt_category_del" @click="del(dto.exr_category_idx)">삭제</button></td>
				</tr>
			`,
			props:["category"],
			data(){
				return{
					dto:this.category
				}	
			},
			methods:{
				getDetail:function(category){
					$("input[name='exr_category_name']").val(category.exr_category_name);
				},
				edit:function(category){
					let json={};
					json['exr_category_name']=$("#form1 input[name='exr_category_name']").val();
					console.log(json);
					
					$.ajax({
						url:"/admin/rest/exr/category",
						type:"put",
						contentType:"application/json;charset=utf-8",
						processData:false,
						data:JSON.stringify(json),						
						success:function(result, status, xhr){
							alert(result.msg);
							getCategoryList();
						},
						error:function(xhr, status, err){
							console.log(xhr.responseText);
						}
					});
				},
				del:function(exr_category_idx){
					if(!confirm("삭제하시겠습니까?")){
						return;
					}
					
					$.ajax({
						url:"/admin/rest/exr/category/"+exr_category_idx,
						type:"delete",
					
						success:function(result, status, xhr){
							alert(result.msg);
							getCategoryList();
						}
					});
				}
				
			}
	}
	
	
	
	
	function getCategoryList(){
		$.ajax({
			url:"/admin/rest/exr/category_list",
			type:"GET",
			success:function(result, status, xhr){
				app1.categoryList=result;
				//console.log("앱의 카테고리 리스트! ", app1.categoryList);
			},
			error:function(xhr, status, err){
				console.log(xhr.responseText);
			}
		});
	}
	
	
	
	/*------------------------
		이미지 미리보기 뷰
	------------------------*/ 
	const imagebox={
		template:`
			<div class="box-style">
				<div @click=delImg(p_idx)><a href="#">X</a></div>
				<img :src="p_src"/>
			</div>	
		`,
		props:["src", "idx"],
		data(){
			return{
				p_src:this.src,
				p_idx:this.idx
			};
		},
		methods:{
			delImg:function(idx){
				for(let i=0;i<app1.imageList.length;i++){
					let json=app1.imageList[i];
					
					if(json.key == idx){
						app1.imageList.splice(i , 1); 	//요소,개수
					}
				}
			}
		}
	}
	
	
	function init(){
		app1=new Vue({
			el:"#app1",
			data:{
				count:3,
				imageList:[],
				categoryList:[]
			},
			components:{
				imagebox,
				row
			}
		});
	}
	
	
	
	/*----------------------
		미리보기
	----------------------*/ 
	function preview(files){
		
		for(let i=0; i<files.length; i++){
			let file=files[i];
			
			if(checkDuplicate(file)<1){
			
				let reader=new FileReader();
				reader.onload=function(e){
					console.log(file);
					key++;
					
					let json=[];
					json['src']=e.target.result;
					json['name']=file.name;
					json['file']=file;
					json['key']=key;
					
					app1.imageList.push(json);
				};
				
				reader.readAsDataURL(file);
				console.log("앱 1의 이미지 리스트~~~", app1.imageList);
			}
		}
	}
	
	
	function checkDuplicate(file){
		let count=0;
		for(let i=0; i<app1.imageList.length; i++){
			let json=app1.imageList[i];
			if(file.name==json.name){
				count++;
			}
		}
		return count;
	}
	
	
	
	$(function(){

		// 뷰 적용
		init();

		// 키테고리 목록 가져오는 함수
		getCategoryList();
		
		// 모달창에서 카테고리 등록 버튼
		$("#bt_category_regist").click(function(){
			categoryRegist();
		});
		
		
		
		$("input[name='file']").change(function(){
			preview(this.files);
		});
		
		$("#bt_regist").click(function(){
			regist();
		});
		

		// 리스트 페이지 이동
		$("#bt_list").click(function(){
			location.href="/admin/exr/notice/list";
		});
		

		// 써머 노트 적용
		$('#detail').summernote({
			height:200
		});
		
	});

	</script>
</body>
</html>

