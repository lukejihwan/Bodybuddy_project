<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>상품 등록</title>
<%@ include file="../inc/header_link.jsp"%>
<%
	System.out.println("어드민 페이지");
%>
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
							<h1 class="m-0">상품등록</h1>
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
								<div class="col">
									<input type="text" name="product_name" class="form-control" placeholder="제목">
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
									<textarea name="detail" class="form-control" id="detail">내용</textarea>
								</div>
							</div>


							<div class="form-group row">
								<div class="col">
									<button type="button" class="btn btn-danger btn-md" id="bt_regist">등록</button>							
									<button type="button" class="btn btn-danger btn-md" id="bt_list">목록</button>									
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
						app1.imageList.splice(i , 1); //요소,개수
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
				imageList:[]
				
			},
			components:{
				imagebox
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
	
	
	/*----------------------
		등록
	----------------------*/ 
	function regist(){
		// 파일 업로드를 커스터 마이징
		let formData=new FormData();
									// 명칭 주의!!
		formData.append("category.category_idx", $("select[name='category_idx']").val());
		formData.append("product_name", $("input[name='product_name']").val());
		formData.append("brand", $("input[name='brand']").val());
		formData.append("price", $("input[name='price']").val());
		formData.append("discount", $("input[name='discount']").val());
		formData.append("detail", $("textarea[name='detail']").val());
		
 		for(let i=0; i<app1.imageList.length; i++){			
			let json=app1.imageList[i];
			formData.append("photo", json.file);
		}
 		
		$.ajax({
			url:"/admin/rest/product",
			type:"post",
			contentType:false,
			processData:false,
			data:formData,
			
			success:function(result, status, xhr){
				alert(result.msg);
			},
			
			error:function(xhr, status, err){
				console.log("err ", err);
				console.log("status ", status);
				console.log("xhr ", xhr);
				
				let json=JSON.parse(xhr.responseText);		// string --> json
				alert(json.msg);
			}
		});
	}
	
	
	$(function(){
		// 써머 노트 적용
		$('#detail').summernote({
			height:200
		});
		
		// 뷰 적용
		init();
		
		$("input[name='file']").change(function(){
			preview(this.files);
		});
		
		$("#bt_regist").click(function(){
			regist();
		});
		
		$("#bt_list").click(function(){
			location.href="/admin/product/list";
		});
		
	});

	</script>
</body>
</html>

