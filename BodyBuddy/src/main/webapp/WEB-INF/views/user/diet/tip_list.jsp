<%@page import="com.edu.bodybuddy.util.PageManager"%>
<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@page import="com.edu.bodybuddy.domain.diet.DietTip"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%
	List<DietTip> dietTipList=(List)request.getAttribute("dietTipList");
%>
<!DOCTYPE html>
<!-- content 부분만 비워둔 기본 템플릿 -->
<!-- hero섹션이 포함되어있음 -->
<html lang="en">
<head>
<%@include file="../inc/header_link.jsp"%>
<%@include file="../inc/list_css.jsp"%>
</head>
<body class="animsition">
	<!-- top-bar start-->
	<%@include file="../inc/topbar.jsp"%>
	<!-- /top-bar end-->

	<!-- hero section start -->
	<div class="hero-section">
		<!-- navigation-->
		<%@include file="../inc/header_navi.jsp"%>
		<!-- /navigation end -->
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-12  col-xs-12">
					<div class="hero-caption pinside50">
						<h1 class="hero-title">식단 팁 게시판</h1>
						<p class="small-caps mb30 text-white"></p>
						<p class="hero-text">식단에 대한 팁 공유 게시판</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- ./hero section end -->

	<!-- content start -->
	<div class="content" id="app1">
		<div class="container">
			<div class="row">
				<div class="col">
                    <h1>식단팁 게시판</h1>
                    <hr>
				</div>
			</div>
			<!-- end of row -->
			<form id="form">			
			<div class="row">
				<div class="col table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>No</th>
								<th>제목</th>
								<th>글쓴이</th>
								<th>작성일</th>
								<th>추천</th>
								<th>조회</th>
							</tr>
						</thead>
						<tbody>
							<template v-for="(dietTip, i) in currentList">
								<row :obj="dietTip" :key="dietTip.diet_tip_idx" :num="num-i">
							</template>
						</tbody>
					</table>
				</div>
				<!-- end of col -->
			</div>
			</form>

			
			<!-- 페이징 처리 -->
			<div class="row">
				<div class="col" style="margin-left:400px">
					<div class="st-pagination">
						<ul class="pagination">
							<li id="paging-area"></li>
						</ul>
					</div>
				</div>
				<div class="col-right">
					<button type="button" class="btn btn-default" id="bt_regist">글쓰기</button>
				</div>
			</div>
			<!-- 페이징 처리 끝 -->
			
			
			<!-- 검색처리 -->
			<div class="row">
				<div class="col-lg-offset-4 col-lg-8" style="margin-left:180px">
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
		
		</div>
	</div>
	<!-- /content end -->


	<!-- black footer_space -->
	<%@include file="../inc/footer_space.jsp"%>



	<!-- tiny footer -->
	<%@include file="../inc/footer_tiny.jsp"%>

	<%@include file="../inc/footer_link.jsp"%>

</body>

<script type="text/javascript" src="/resources/user/js/page/Pager.js"></script>
<script type="text/javascript">
	let app1;
	
	let pager=new Pager();
	let currentPage=1;
	
	const row={
		template:`
			<tr @click="getDetail(dietTip)">
				<td>{{dietTip.diet_tip_idx}}</td>
				<td>{{dietTip.title}}</td>
				<td>{{dietTip.writer}}</td>
				<td>{{dietTip.regdate.substring(0,10)}}</td>
				<td>{{dietTip.recommend}}</td>
				<td>{{dietTip.hit}}</td>
			</tr>
		`,	
		props:["obj", "num"],
		data(){
			return{
				dietTip:this.obj,
				n:this.num
			}	
		},
		methods:{
			getDetail:function(dietTip){
				location.href="/diet/tip_detail/"+dietTip.diet_tip_idx;
			}
		}
	}
	
	function init(){
		app1=new Vue({
			el:"#app1",
			data:{
				dietTipList:[], //전체 배열
				currentList:[], //현재 페이지에 보여질 배열
				num:0
			},
			components:{
				row
			}
		});
	}
	
	//페이징처리
	function pageLink(n){
		pager.init(app1.dietTipList, n);
		
		app1.num=pager.num;
		app1.currentList.splice(0, app1.currentList.length);
		
		for(let i=pager.curPos; i<pager.curPos+pager.pageSize; i++){
			if(pager.num<1){
				break;
			}
			pager.num--;
			app1.currentList.push(app1.dietTipList[i]);
		}
	}
	
	//글 목록 조회
	function getTipList(){
		$.ajax({
			url:"/rest/diet/tip_list",       //?page="+$("input[name='page']").val(),
			type:"get",
			success:function(result,status, xhr){
				app1.dietTipList=result;
				console.log(result);
				
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
	
	//글 검색
	function search(){
		$.ajax({
			url:"/rest/diet/tip/search?keyword="+$("input[name='keyword']").val(),
			type:"get",
			success:function(result,status, xhr){
				app1.dietTipList=result;
				pageLink(currentPage);
			}
		});
	}
	

	$(function(){
		init();
		getTipList();
		
		//글쓰기 버튼
		$("#bt_regist").click(function(){
			<sec:authorize access="isAnonymous()">
			Swal.fire({
				title:"로그인해야 사용할 수 있는 기능입니다",
				icon:"warning",
				confirmButtonText:"확인",
				confirmButtonColor: '#c5f016'
			});
			return;
		</sec:authorize>
		location.href="/diet/tip_registform";
		});
		
		//검색버튼 
		$("#bt_search").click(function(){
			search();
		});
	});
</script>

</html>
