<%@page import="com.edu.bodybuddy.domain.board.FreeBoard"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	FreeBoard board = (FreeBoard)request.getAttribute("board");
	String listURI = "/board/free_list/"; //ex. /board/free_list/
	String deleteURI = "/board/free_delete?free_board_idx="; //ex. /board/free_delete?free_board_idx=
	String DetailEditURI = "/board/free_detail_edit/";
	int idx = board.getFree_board_idx();
%>
<!DOCTYPE html>
<!-- content 부분만 비워둔 기본 템플릿 -->
<!-- hero섹션이 포함되어있음 -->
<html lang="en">
<head>
<%@include file="../inc/header_link.jsp"%>
<script src="https://kit.fontawesome.com/99ef7b560b.js" crossorigin="anonymous"></script>
<style type="text/css">
.comment-right button{
	margin: 0 10px 0 10px;
}
.comment-content{
	cursor: pointer
}
</style>
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
						<h1 class="hero-title">자유게시판</h1>
						<p class="small-caps mb30 text-white"></p>
						<p class="hero-text">자유롭게 소통하는 게시판입니다</p>
						<!-- <a href="classes-list.html" class="btn btn-default">링크 필요하면
							사용할 버튼</a> -->
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
				<div class="col">
                    <h1><a href="<%= listURI+1 %>">자유게시판</a></h1>
                    <hr>
                    <h3><%= board.getTitle() %></h3><br/>
                    <span><%= board.getWriter() %> | <%= board.getRegdate().substring(0, 10) + " " + board.getRegdate().substring(10, board.getRegdate().length()-2) %></span>
                    <span class="float-right">조회 <%= board.getHit() %> | 추천 <%= board.getRecommend() %></span>
                    <hr>
				</div>
			</div>
			<!-- end of row -->
			<br/>
			<div class="row">
				<div class="col-md-12">
					<%= board.getContent() %>
				</div>
				<div class="col-md-12 mt-5 mb-4 text-center">
					<button class="btn btn-default" id="bt_recommend"><i class="fa-solid fa-thumbs-up"></i> <%= board.getRecommend() %></button>
				</div>
			</div>
			<hr>
			<!-- end of row -->
			<button class="btn btn-primary" id="bt_list">목록</button>
			<button type="button" class="btn btn-danger pull-right" id="bt_del">삭제</button>
			<button type="button" class="btn btn-default pull-right" style="margin-right: 10px" id="bt_edit">수정</button>
			<hr>
			<template>
				<comment_form :idx="<%= idx %>"/>
			</template>
			<br/>
			<template v-for="(comment, i) in commentList">
				<comment :key="i" :comment="comment"/>
			</template>

			
			
		</div>
		<!-- end of container -->
	</div>
	<!-- end of space-medium -->
	<!-- /content end -->

	<!-- black footer_space -->
	<%@include file="../inc/footer_space.jsp"%>



	<!-- tiny footer -->
	<%@include file="../inc/footer_tiny.jsp"%>

	<%@include file="../inc/footer_link.jsp"%>

</body>
<script type="text/javascript">
	
	let app1;
	
	const comment = {
			template:`
				<div>
					<div class="row">
						<div :class="'col-md-'+comment.depth"></div>
						<div :class="'col-md-'+(12-comment.depth)">
							<div class="row">
								<div class="col-md-2">
									<span>{{comment.writer}}</span>
								</div>
								<div class="col-md-7 comment-content" @click="toggleForm()">
									<p>{{comment.comment}}</p>
								</div>
								<div class="col-md-3 comment-right">
									<button type="button" class="btn btn-default btn-sm float-right">
									<i class="fa-solid fa-xmark"></i></button>
									<span class="float-right">{{comment.regdate.substr(5, 5)}} {{comment.regdate.substr(11, 5)}}</span>
								</div>
							</div>
						</div>
					</div>
					<!-- end of row -->
					
					
					<div class="row">
						<div :class="'col-md-'+(comment.depth+1)"></div>
						<div :class="'col-md-'+(12-(comment.depth+1))">
							<!-- 여기 board_comment_idx 부분 꼭 수정해야 함 -->
							<form class="row" :id="'form-comment-'+board_comment_idx">
								<input type="hidden" name="free_board_comment_idx" :value="board_comment_idx"/>
								<input type="hidden" name="idx" :value="comment.freeBoard.free_board_idx"/>
								<div class="col-md-10">
									<textarea rows="5" class="form-control" style="margin-top:10px;" name="comment" placeholder="댓글 작성..."></textarea>
								</div>
								<div class="col-md-2 d-flex align-items-center  justify-content-center">
									<button type="button" class="btn btn-secondary bt_regist_comment" :value="board_comment_idx">등록</button>
								</div>
							</form>
						</div>
					</div>
					<hr>
				</div>
				<!-- end of row -->
			`,
			props:['depth', "comment"],
			methods:{
				toggleForm:function(){
					console.log("comment",this.comment);
					console.log("post",this.comment.post);
					console.log("step",this.comment.step);
					console.log("depth", this.comment.depth);
				}
			},
			data(){
				return {
					flag:false,
					board_comment_idx:this.comment.free_board_comment_idx,
				};
			}
	};
	
	const comment_form = {
			template:`
				<form class="row" id="form-comment-0">
					<input type="hidden" name="idx" :value="idx"/>
					<div class="col-md-10">
						<textarea rows="5" class="form-control" style="margin-top:10px;" name="comment" placeholder="댓글 작성..."></textarea>
					</div>
					<div class="col-md-2 d-flex align-items-center  justify-content-center">
						<button type="button" class="btn btn-secondary bt_regist_comment" value="0">등록</button>
					</div>
				</form>
			`,	
			props:["idx"],
	};
	
	
	let a;
	$(()=>{
		init();
		getList();	
		
		$("#bt_edit").click(()=>{
			location.href="<%= DetailEditURI + idx %>";
		});
		$("#bt_del").click(()=>{
			del();
		});
		$("#bt_list").click(()=>{
			location.href="<%= listURI+1 %>";
		});
		
	});
	
	function init() {
		app1 = new Vue({
			el: "#app1",
	        components:{
	            comment,
	            comment_form
	        },
	        data:{
	        	commentList:[],
	        	event_flag:true
	        },
	        updated(){
	        		
	      		if(this.event_flag){
		        	$.each($(".bt_regist_comment"), (i, item)=>{
		        		$(item).click((e)=>{
		        			registComment(e.target.value);
		        		});
		        	});
		        	this.event_flag=false;
	      		}
	        }
		});
	}
	
	function del() {
		if(!confirm("삭제하시겠습니까?")) return; 
		

		location.href="<%= deleteURI + idx %>";
	}
	
	function registComment(index) {
		$.ajax({
			url:"/rest/board/free_board/comment",
			type:"POST",
			data:$("#form-comment-"+index).serialize(),
			success:(result, status, xhr)=>{
				console.log(result);
				getList();
			},
			error:(xhr, status, err)=>{
				console.log("ajax 실패 ", xhr);
			}
		});
	}
	
	function getList() {
		$.ajax({
			url:"/rest/board/free_board/comment/board/<%= idx %>",
			type:"GET",
			success:(result, status, xhr)=>{
				app1.commentList = result;			
			},
			error:(xhr, status, err)=>{
				console.log("ajax 실패 ", xhr);
			}
		});
	}
</script>
</html>
