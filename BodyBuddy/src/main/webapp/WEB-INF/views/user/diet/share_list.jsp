<%@page import="com.edu.bodybuddy.util.PageManager"%>
<%@page import="com.edu.bodybuddy.domain.diet.DietShare"%>
<%@page import="java.util.List"%>
<%@page import="com.edu.bodybuddy.domain.diet.DietCategory"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%
	
	List<DietCategory> dietCategoryList=(List)request.getAttribute("dietCategoryList");
	List<DietShare> dietShareList=(List)request.getAttribute("dietShareList");
	DietShare dietShare=(DietShare)request.getAttribute("dietShare");
	PageManager pageManager=(PageManager)request.getAttribute("pageManager");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../inc/header_link.jsp" %>
<%@include file="../inc/list_css.jsp"%>
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
                      <h1 class="hero-title">식단 공유 게시판</h1>
                      <p class="small-caps mb30 text-white">페이지 소제목 올 곳</p>
                      <p class="hero-text">페이지 간단한 설명이 올 곳. 이 탬플릿 페이지마다 써먹어도 괜찮을듯</p>
                  </div>
              </div>
          </div>
      	</div>
    </div>
     <!-- ./hero section end -->
     

    
    <!-- content start -->
    <div class="space-medium" id="app1">
        <div class="container">
        	<div class="row">
        		<h1 style="margin-left:15px; margin-right:320px">식단공유 게시판</h1>
        		<input type="hidden" name="page" value="<%=pageManager.getCurrentPage()%>">
				<input type="hidden" name="num" value="<%=pageManager.getNum()%>">
				<h3><a href="/diet/share_list/1">전체보기</a></h3>
        		<%for(DietCategory dietCategory:dietCategoryList){ %>
	            	<h3 style="margin-left:30px"><a href="javascript:getCategoryList(<%=dietCategory.getDiet_category_idx() %>)"><%=dietCategory.getDiet_category_name()%></a></h3> 
	            <%} %> 
        	</div>
            <hr>
            <div class="row">
	            	<template v-for="dietShare in dietShareList">
	            		<row :obj="dietShare" :key="dietShare.diet_share_idx">
	            	</template>            	
            </div>
            </div>
        </div>
    </div>
    <!-- /content end -->
    
    <!-- 페이징 처리 -->
			<div class="row">
				<div class="col" style="margin-left:460px">
					<div class="st-pagination">
						<ul class="pagination">
							<% if(pageManager.getFirstPage()!=1){ %>
								<li><a href="/diet/tip_list/<%= pageManager.getFirstPage()-1 %>" aria-label="previous"><span aria-hidden="false">이전</span></a></li>
							<%}else{ %>
								<li><a href="javascript:alert('첫 페이지 입니다')" aria-label="previous"><span aria-hidden="true">이전</span></a></li>
							<%} %>
							<%for(int i=pageManager.getFirstPage(); i<=10; i++){
								//if(i>pageManager.getTotalPage()) break; 
							%>
								<li <% if(pageManager.getCurrentPage()==i) out.print("class=\"active\""); %>> <a href="/diet/share_list/<%=i %>"><%=i %></a></li>
							<%} %>
							<%if(pageManager.getLastPage()<pageManager.getTotalPage()){ %>
								<li><a href="/diet/tip/list/<%=pageManager.getLastPage()+1 %>" aria-label="Next"><span aria-hidden="true">다음</span></a></li>
							<%}else{ %>
								<li><a href="javascript:alert('마지막 페이지 입니다')" aria-label="Next"><span aria-hidden="true">다음</span></a></li>
							<%} %>
						</ul>
					</div>
				</div>
				<div class="col-right" style="margin-right:180px">
					<button type="button" class="btn btn-default" id="bt_regist">글쓰기</button>
				</div>
			</div>
	<!-- 페이징 처리 끝 -->
    
    
    		<!-- 검색처리 -->
			<div class="row">
				<div class="col-lg-offset-4 col-lg-6" style="margin-left:370px">
					<div class="widget widget-search mb10">
						<form>
							<div class="input-group">
								<input type="text" class="form-control" placeholder="검색어를 입력하세요" name="keyword">
								<button class="btn btn-default" type="button"><i class="fa fa-search" id="bt_search"></i></button>
							</div>
						</form>
					</div>
				</div>				
			</div>
			<!-- 검색처리 끝 -->

			
    <!-- black footer_space -->
    <%@include file="../inc/footer_space.jsp" %>
    
    <!-- tiny footer -->
    <%@include file="../inc/footer_tiny.jsp" %>
    
	<%@include file="../inc/footer_link.jsp" %>

</body>
<script type="text/javascript">
	let app1;
	
	const row={
		template:`
			<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
            	<div class="post-block mb30">
	                <div class="post-img">
	                    <a @click="getDetail(dietShare)" class="imghover"><img :src="dietShare.preview" style="width:400px;height:250px;"></a>
	                </div>
	                <div class="post-header">
	                    <div class="post-title">
	                        <h3><a href="" class="text-white">{{dietShare.title}}</a></h3>
	                    </div>
	                    <div class="post-meta"> <span class="meta-date">{{dietShare.dietCategory.diet_category_name}}</span></div>
	                    <div class="post-meta"> <span class="meta-date">{{dietShare.regdate.substring(0,10)}}   {{dietShare.hit}} | {{dietShare.recommend}}</span></div>
	                </div>
            	</div>
        	</div>
		`,	
		props:["obj"],
		data(){
			return{
				dietShare:this.obj
			}
		},
		methods:{
			getDetail:function(dietShare){
				location.href="/diet/share_detail/"+dietShare.diet_share_idx;
			}
		}
	}
	
	//글 목록 조회
	function getShareList(){
		$.ajax({
			url:"/rest/diet/share_list?page="+$("input[name='page']").val(),
			type:"get",
			success:function(result, status,xhr){
				app1.dietShareList=result;
			}
		});
	}
	
	//카테고리별 목록 출력 
	function getCategoryList(category_idx){
		$.ajax({
			url:"/rest/diet/share_list/category/"+category_idx,
			type:"get",
			success:function(result, status, xhr){
				app1.dietShareList=result;
			}
		});
	}
	
	//글 검색
	function search(){
		$.ajax({
			url:"/rest/diet/share/search?keyword="+$("input[name='keyword']").val(),
			type:"get",
			success:function(result,status, xhr){
				app1.dietShareList=result;
			}
		});
	}
	
	function init(){
		app1=new Vue({
			el:"#app1",
			data:{
				dietShareList:[]
			},
			components:{
				row
			}
		});
	}

	$(function(){
		init();
		getShareList();
		
		//글쓰기 버튼
		$("#bt_regist").click(function(){
			location.href="/diet/share_registform";
		});
		
		//검색 버튼
		$("#bt_search").click(function(){
			search();
		});
	
	});

</script>

</html>
