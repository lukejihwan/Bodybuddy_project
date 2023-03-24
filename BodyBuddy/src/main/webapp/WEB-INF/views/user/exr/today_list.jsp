<%@page import="com.edu.bodybuddy.domain.exr.ExrCategory"%>
<%@page import="com.edu.bodybuddy.util.PageManager"%>
<%@page import="com.edu.bodybuddy.domain.exr.ExrRoutine"%>
<%@page import="com.edu.bodybuddy.domain.exr.ExrNotice"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	
%>
<!DOCTYPE html>
<html lang="en">
<head profile="http://www.w3.org/2005/10/profile">

<%@include file="../inc/header_link.jsp" %>
<style type="text/css">
	.hero-section{
		background-image: url("/resources/user/images/exr/routine_back.jpg");
	}
</style>
</head>

<body class="animsition">
	<!-- top-bar start-->
	<%@include file="../inc/topbar.jsp" %>
    <!-- /top-bar end-->

    <!-- hero section start -->
    <div class="hero-section">
		<!-- navigation-->
	   	<%@include file="../inc/header_navi.jsp" %>
	    <!-- /navigation end -->
        <div class="container">
          <div class="row">
              <div class="col-lg-6 col-md-6 col-sm-12  col-xs-12">
                  <div class="hero-caption pinside50">
                      <h1 class="hero-title">오운완 게시판</h1>
                      <p class="small-caps mb30 text-white">BodyBuddy Excercise Routine Share Here.</p>
                      <p class="hero-text">하루의 러닝 기록을 기록하는 공간입니다</p>
                      <a href="/exr/routine/registform" class="btn btn-default">지금 기록하기</a>
                  </div>
              </div>
          </div>
      	</div>
    </div>
     <!-- ./hero section end -->
     
    <!-- content start -->
    <div class="space-medium">
        <div class="container" id="app1">

			<div class="card-header">
				<div class="row">

					<div class="col-7">
						<h3 class="card-title">오운완 게시판</h3>

						<div class="card-tools">
							<form action="form1">


								<input type="text" name="keyword" placeholder="제목 검색"
									style="width: 80%">
								<button class="btn btn-default" type="button" id="bt_search">
									<i class="fa fa-search"></i>
								</button>
							</form>
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
							<th>카테고리</th>
							<th>제목</th>
							<th>작성자</th>
							<th>등록일</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
						<template v-for="exrRoutine in exrRoutineList">
							<row :dto="exrRoutine" :key="exrRoutine.exr_routine_idx"
								:num="exrRoutine.commentList.length" />
						</template>
					</tbody>
				</table>
			</div>
			<!-- /.card-body -->


			<!-- /.card -->
			<!-- <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center"> -->


			<div class="row">
				<div class="col text-center">
					<div class="st-pagination">
						<ul class="pagination">



						</ul>
					</div>
				</div>
			</div>


			<div class="text-lg-end">
				<a href="/exr/routine/registform" class="btn btn-default">글쓰기</a>
			</div>

		</div>
    </div>


    <!-- /content end -->
    
	<!-- black footer_space -->
    <%@include file="../inc/footer_space.jsp" %>

    <!-- tiny footer -->
    <%@include file="../inc/footer_tiny.jsp" %>
    
    <%@include file="../inc/footer_link.jsp" %>
<script type="text/javascript">
	let app1;
	
	// 카테고리별 목록 출력
	function getCategory(idx){
		//alert(idx);
		getExrRoutineList("/rest/exr/routine_category/"+idx);
	}
	
	
	const row={
		template:`
			<tr>
			    <td>{{exrRoutine.exr_routine_idx}}</td>
			    <td>{{exrRoutine.exrCategory.exr_category_name}}</td>
			    <td><a href="#" @click="getDetail(exrRoutine)">{{exrRoutine.title}}
			    <span class="comment-count" style="color:red">[&nbsp{{this.num}}&nbsp]</span>
			    </a></td>
			    <td>{{exrRoutine.writer}}</td>
			    <td>{{exrRoutine.regdate.substr(0, 10)}}</td>
			    <td>{{exrRoutine.hit}}</td>
			</tr>
		`,
		props:["dto", "num"],
		data(){
			return{
				exrRoutine:this.dto,
				commentCount:this.num
			}
		},
		methods:{
			getDetail:function(exrRoutine){
				location.href="/exr/routine_detail/"+exrRoutine.exr_routine_idx;
			}
		}
	}

	
	// 리스트 조회
	// url을 매개변수로 받아서 재사용한다!
	function getExrRoutineList(url){
		$.ajax({
			url:url,
			type:"GET",
			success:function(result, status, xhr){
				app1.exrRoutineList=result;
				console.log(app1.exrRoutineList);
				
			},
			error:function(xhr, status, err){
				console.log(xhr.responseText);
			}
		});
	}

	
	
	function init(){
		app1=new Vue({
			el:"#app1",
			data:{
				exrRoutineList:[]
			},
			components:{
				row
			}
		});
	}
	
	

	$(function(){
		init();
		
		// 처음 보여질 때는 전체 목록을 조회한다
		getExrRoutineList("/rest/exr/routine_list?pg="+$("input[name='pg']").val());
		
		// 키워드 검색
		$("#bt_search").click(function(){
			getExrRoutineList("/rest/exr/routine/search?keyword="+$("input[name='keyword']").val());
		});
		
	});

</script>
</body>
</html>
