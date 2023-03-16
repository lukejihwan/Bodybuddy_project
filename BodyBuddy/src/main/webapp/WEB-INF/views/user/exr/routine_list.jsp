<%@page import="com.edu.bodybuddy.util.PageManager"%>
<%@page import="com.edu.bodybuddy.domain.exr.ExrRoutine"%>
<%@page import="com.edu.bodybuddy.domain.exr.ExrNotice"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	PageManager pageManager=(PageManager)request.getAttribute("pageManager");

	String listURI = "/exr/exr_routine_list/"; // href 이동 주소 이것만 변경하면 됨. 뒤에 / 붙일 것 ex. /board/free_list/
	String detailURI = "/exr/routine/";

%>
<!DOCTYPE html>
<html lang="en">
<head profile="http://www.w3.org/2005/10/profile">

<%@include file="../inc/header_link.jsp" %>
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
                      <h1 class="hero-title">루틴 공유 게시판</h1>
                      <p class="small-caps mb30 text-white">BodyBuddy Excercise Routine Share Here.</p>
                      <p class="hero-text">자신만의 운동 루틴을 기록해보세요!</p>
                      <a href="/exr/routine/regist" class="btn btn-default">지금 기록하기</a>
                  </div>
              </div>
          </div>
      	</div>
    </div>
     <!-- ./hero section end -->
     
    <!-- content start -->
    <div class="space-medium">
        <div class="container" id="app1">
            <div class="row">

				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<div class="row">

								<div class="col-6">
									<h3 class="card-title">루틴 공유 게시판</h3>

									<div class="card-tools">
										<form action="form1">
											<div class="input-group input-group-sm" style="width: 250px;">
												<input type="text" name="keyword" placeholder="제목 검색">
												<div class="input-group-append">
													<button class="btn btn-default" type="button"
														id="bt_search">
														<i class="fa fa-search"></i>
													</button>
												</div>
											</div>
										</form>
									</div>

								</div>
								<!-- 검색 태그들이 올 곳 -->
								<div class="col-6">
									<a href="#" title="Beginners">Beginners</a>
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
									<%int num=pageManager.getNum();	//--> 이 수를 템플릿의 데이터로 넘기려면? %>
									<template v-for="exrRoutine in exrRoutineList">
										<row :dto="exrRoutine" :key="exrRoutine.exr_routine_idx" :num="<%=num %>"/>
									</template>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->
					</div>
					<!-- /.card -->
					<!-- <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center"> -->
				
					<div class="row">
						<div class="col text-center">
							<div class="st-pagination">
								<!--st-pagination-->
								<ul class="pagination">
									<% if(pageManager.getFirstPage()!=1){ %>
									<li><a href="<%= listURI %><%= pageManager.getFirstPage()-1  %>" aria-label="previous"><span aria-hidden="false">이전</span></a></li>
									<% } %>
									
									<% for(int i =pageManager.getFirstPage();i<=pageManager.getLastPage();i++){ %>
									<% if(i>pageManager.getTotalPage()) break; %>
									<li <% if(pageManager.getCurrentPage()==i) out.print("class=\"active\""); %>> <a href="<%= listURI %><%= i %>"><%= i %></a></li>
									<% } %>
									
									<% if(pageManager.getLastPage()<pageManager.getTotalPage()){ %>
									<li><a href="<%= listURI %><%= pageManager.getLastPage()+1 %>" aria-label="Next"><span aria-hidden="true">다음</span></a></li>
									<% } %>
								</ul>
							</div>
						</div>
					</div>

					<div class="text-lg-end">
						<a href="/exr/routine/registform" class="btn btn-default">글쓰기</a>
					</div>
				</div>

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
	
	const row={
		template:`
			<tr>
			    <td>{{exrRoutine.exr_routine_idx}}</td>
			    <td>{{exrRoutine.exrCategory.exr_category_name}}</td>
			    <td><a href="#" @click="getDetail(exrRoutine)">{{exrRoutine.title}}</a></td>
			    <td>{{exrRoutine.writer}}</td>
			    <td>{{exrRoutine.regdate}}</td>
			    <td>{{exrRoutine.hit}}</td>
			</tr>
		`,
		props:["dto", "num"],
		data(){
			return{
				exrRoutine:this.dto,
				n:this.num
			}
		},
		methods:{
			getDetail:function(exrRoutine){
				location.href="/exr/routine/"+exrRoutine.exr_routine_idx;
			}
		}
		
	}

	
	// 리스트 조회
	// url을 매개변수로 받아서 처리!
	function getExrRoutineList(url){
		$.ajax({
			url:url,
			type:"GET",
			success:function(result, status, xhr){
				app1.exrRoutineList=result;
				console.log(app1.exrRoutineList);
				app1.num=app1.exrRoutineList.length;
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
				exrRoutineList:[],
			},
			components:{
				row
			}
		});
	}
	
	
	

	$(function(){
		init();
		getExrRoutineList("/rest/exr/routine_list");
		
		// 키워드 검색
		$("#bt_search").click(function(){
			getExrRoutineList("/rest/exr/routine/search?keyword="+$("input[name='keyword']").val());
		});
		
	});

</script>
</body>
</html>
