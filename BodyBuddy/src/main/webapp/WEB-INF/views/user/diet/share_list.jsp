<%@page import="com.edu.bodybuddy.util.PageManager"%>
<%@page import="com.edu.bodybuddy.domain.diet.DietShare"%>
<%@page import="java.util.List"%>
<%@page import="com.edu.bodybuddy.domain.diet.DietCategory"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%
	
	List<DietCategory> dietCategoryList=(List)request.getAttribute("dietCategoryList");
	List<DietShare> dietShareList=(List)request.getAttribute("dietShareList");
	DietShare dietShare=(DietShare)request.getAttribute("dietShare");
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
				<h3><a href="/diet/share_list/1">전체보기</a></h3>
        		<%for(DietCategory dietCategory:dietCategoryList){ %>
	            	<h3 style="margin-left:30px"><a href="javascript:getCategoryList(<%=dietCategory.getDiet_category_idx() %>)"><%=dietCategory.getDiet_category_name()%></a></h3> 
	            <%} %> 
        	</div>
            <hr>
            <div class="row">
	            	<template v-for="(dietShare, i) in currentList">
						<row :obj="dietShare" :key="dietShare.diet_share_idx" :num="num-i">
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
							<li id="paging-area"></li>
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

<script type="text/javascript" src="/resources/user/js/page/Pager2.js"></script>
<script type="text/javascript">
	let app1;
	
	let pager=new Pager();
	let currentPage=1;
	
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
		props:["obj", "num"],
		data(){
			return{
				dietShare:this.obj,
				n:this.num
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
			url:"/rest/diet/share_list",
			type:"get",
			success:function(result, status,xhr){
				app1.dietShareList=result;
				
				//페이징 처리 
				pageLink(currentPage);
				
				//페이징 번호 처리 
				$("#paging-area").append("<a href='#'>이전</a>");
				for(let i=pager.firstPage; i<=pager.lastPage; i++){
					if(i >pager.totalPage)break;
					$("#paging-area").append("<a href='javascript:pageLink("+i+")' style='margin:2px'>"+i+"</a>");
				}
				$("#paging-area").append("<a href='#'>다음</a>");
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
				
				pageLink(currentPage);
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
				pageLink(currentPage);
			}
		});
	}
	
	//페이징처리
	function pageLink(n){
		pager.init(app1.dietShareList, n);
		
		app1.num=pager.num;
		app1.currentList.splice(0, app1.currentList.length);
		
		for(let i=pager.curPos; i<pager.curPos+pager.pageSize; i++){
			if(pager.num<1){
				break;
			}
			pager.num--;
			app1.currentList.push(app1.dietShareList[i]);
		}
	}
	
	function init(){
		app1=new Vue({
			el:"#app1",
			data:{
				dietShareList:[],
				currentList:[],
				num:0
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
