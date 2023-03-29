<%@page import="com.edu.bodybuddy.domain.exr.ExrTodayComment"%>
<%@page import="com.edu.bodybuddy.domain.exr.ExrToday"%>
<%@page import="com.edu.bodybuddy.domain.exr.ExrTip"%>
<%@page import="com.edu.bodybuddy.domain.exr.ExrRoutine"%>
<%@page import="com.edu.bodybuddy.domain.exr.ExrCategory"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	
	ExrToday exrToday=(ExrToday)request.getAttribute("exrToday");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../inc/header_link.jsp"%>
<style type="text/css">
#body{
    background-size: 100%;
}
	.hero-section{
		background-image: url("/resources/user/images/exr/todaybg.jpg");
	}
	.comment-area{
		/* background:pink; */
		border:solid 2px gray;
	}
	.test{
		background:white;
		margin: 10px;
	}
	.section{
		margin:30px;
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
						<h1 class="hero-title">오운완 게시판</h1>
						<p class="small-caps mb30 text-white">BodyBuddy Excercise Routine Share Here.</p>
                      	<p class="hero-text">자신만의 운동 루틴을 기록해보세요!</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- ./hero section end -->

	<!-- content start -->
	<div class="space-medium">
		<div class="container" id="app1">
			<h3>오운완 게시판</h3>

				<div class="col">
                    <hr>
                    <h1><%= exrToday.getTitle() %></h1>
                    <span><a href="#"><i class="fa fa-twitter  float-right">찜하기</i></a></span>
                    <br/>
                    <span><%= exrToday.getWriter() %> | <%= exrToday.getRegdate().substring(0, 10) + " " + exrToday.getRegdate().substring(10, exrToday.getRegdate().length()-2) %></span>
                    <span class="float-right">조회 <%= exrToday.getHit() %> | 추천 {{recommend}}</span>
                    <hr>
				</div>
			
			
				<div class="col-sm-12">

					<div class="form-group">
						<textarea id="summernote" name="content"><%=exrToday.getContent() %></textarea>
					</div>


					<div class="form-group">
						<button type="button" class="btn btn-primary" id="bt_list">목록</button>
						<button type="button" class="btn btn-outline-success" id="bt_edit">수정</button>
						<button type="button" class="btn btn-outline-danger" id="bt_delete">삭제</button>
														<!-- 어드민 -->
						<sec:authorize access="hasRole('ADMIN')">
							<button type="button" class="btn btn-secondary" id="bt_point" style="float:right">확인</button>
						</sec:authorize>
					</div>
	
					<div class="col-md-12 mt-5 mb-4 text-center">
						<button class="btn btn-default" id="bt_recommend">
							<i class="fa-solid fa-thumbs-up"></i>추천 {{recommend}}</button>
					</div>
	
				</div>
				<hr>
				
				<div class="comment-area">
					<div class="section">
						<div class="col-sm-9">
							<label class="control-label" for="textarea">Comments</label>
							<form id="form1">
								<input type="hidden" name="recommend"
									value="<%=exrToday.getRecommend()%>"> <input
									type="hidden" name="exr_today_idx"
									value="<%=exrToday.getExr_today_idx()%>">
	
								<textarea class="form-control" name="content" rows="6"
									placeholder="댓글 입력 창"></textarea>
								<input type="text" class="form-control" name="writer"
									placeholder="작성자" />
									<button id="bt_comment" class="btn btn-default" type="button">등록</button>
							</form>
						</div>
	
						<div class="col-sm-9">
							<template v-for="(comment, i) in commentList">
								<!-- <input type="hidden" name="depth" value="comment.depth"> -->
								<row :dto="comment" :key="comment.exr_today_comment_idx" :depth="comment.depth*70" />
							</template>
	
							<!-- reply-form -->
							<hr>
							<div id="replySection" class="col-sm-9 mt-3">
								<label class="control-label" for="textarea">님에게 답변</label>
								<form id="form2">
									<input type="hidden" name="exr_today_idx" value="<%=exrToday.getExr_today_idx()%>">
									<textarea class="form-control" name="content" rows="6" placeholder="답변 작성"></textarea>
									<input type="text" class="form-control" name="writer" />
									<button id="bt_reply" class="btn btn-default" type="button">답변
										등록</button>
								</form>
							</div>
							<!-- ./reply-form -->

					</div>
				</div><!-- ./comment-area -->

		</div>
	</div>
	<!-- end of container -->
	<!-- /content end -->

	<!-- black footer_space -->
	<%@include file="../inc/footer_space.jsp"%>

	<!-- tiny footer -->
	<%@include file="../inc/footer_tiny.jsp"%>

	<%@include file="../inc/footer_link.jsp"%>

</body>
<script type="text/javascript">
	let app1;
	
	// 추천하기
	function recommend(){
		$.ajax({
			url:"/rest/exr/today/recommend/"+$("input[name='exr_today_idx']").val(),
			type:"GET",
			success:function(result, status, xhr){
				
				app1.recommend=result;
			},
			error:function(xhr, status, err){
				console.log(xhr.responseText);
			}
		});
	}
	
	
	// 댓글 목록 조회
	function getComments(){
		$.ajax({
			url:"/rest/exr/today/comment/"+$("input[name='exr_today_idx']").val(),
			type:"GET",
			success:function(result, status, xhr){
				console.log("결과 확인 들어갑니다", result);
				app1.commentList=result;
			},
			error:function(xhr, status, err){
				console.log(xhr.responseText);
			}
		});
	}
	
	
	// 댓글 등록
	function regist(){
		
		let formData=new FormData();
		formData.append("exrToday.exr_today_idx", $("input[name='exr_today_idx']").val());
		formData.append("content", $("#form1 textarea[name='content']").val());
		formData.append("writer", $("#form1 input[name='writer']").val());
		
		$.ajax({
			url:"/rest/exr/today/comment",
			type:"POST",
			contentType:false,
			processData:false,
			data:formData,
			success:function(result, status, xhr){
				alert(result.msg);
				
				// redirect
				location.href="/exr/today_detail?exr_today_idx="+$("input[name='exr_today_idx']").val();
			},
		});
	}
	
	
	//대댓글 등록
 	function reply(comment){
		let formData=new FormData();
		formData.append("exrToday.exr_today_idx", $("input[name='exr_today_idx']").val());
		formData.append("content", $("#form2 textarea[name='content']").val());
		formData.append("writer", $("#form2 input[name='writer']").val());
		formData.append("post", comment.post);
		
		$.ajax({
			url:"/rest/exr/today/comment_reply",
			type:"POST",
			contentType:false,
			processData:false,
			data:formData,
			success:function(result, status, xhr){
				alert(result.msg);
				location.href="/exr/today/detail?exr_today_idx="+$("input[name='exr_today_idx']").val();
			}
		});
	}
	
	
	
	// 댓글 목록 출력
	const row={
			template:`
				<div class="test" :style="'margin-left:'+c_depth+'px;'">
					<hr>
					<h4 class="user-title mb10">{{comment.content}}</h4>
					<div>
						<button type="button" class="btn btn btn-danger float-right" @click="del(comment.exr_today_comment_idx)">삭제</button>
						<button id="bt_comment" class="btn btn-default float-right" type="button" @click="replyForm(comment)">답글달기</button>
					</div>
					<div class="comment-meta">
						<span class="comment-meta-date">{{comment.writer}}</span>
						<span class="comment-meta-date">{{comment.regdate}}</span>
					</div>
					<div class="comment-content">
						<p>{{comment.writer}}</p>
					</div>
				</div>
			`,
			props:["dto", "depth"],
			data(){
				return{
					comment:this.dto,
					c_depth:this.depth
				}
			},
			
			methods:{
				// 답글 폼 등장
				replyForm:function(comment){
					$("#replySection").show();
					
					
					// 대댓글 달기
					$("#bt_reply").click(function() {
						reply(comment);
					});
				},
				
				// 댓글 삭제
				del:function(idx){
					if(!confirm("댓글을 삭제하시겠습니까?")){
						return;
					}
					
					$.ajax({
						url:"/rest/exr/today/comment/"+idx,
						type:"delete",
						success:function(result, status, xhr){
							alert(result.msg);
							getComments();
						}
					});
				}
			}
		}

	
	
	function init(){
		app1=new Vue({
			el:"#app1",
			data:{
				recommend:$("input[name='recommend']").val(),
				commentList:[]
			},
			components:{
				row
			}
		});
	}
	
	
	
	/***onLoad***/
	$(function() {
		getComments();
		init();
		
		// 대댓글 폼
		$("#replySection").hide();
		
		// 목록 페이지 이동
		$("#bt_list").click(function() {
			location.href = "/exr/today_list";
		});
		

		// 게시글 수정
		$("#bt_edit").click(function() {
			location.href = "/exr/today/editform?exr_today_idx="+$("input[name='exr_today_idx']").val();
		});
		
		
		// 게시글 삭제
		$("#bt_delete").click(function() {
			if (confirm("삭제하시겠습니까?")) {
				location.href = "/exr/today/delete?exr_today_idx="+$("input[name='exr_today_idx']").val();
			}
		});

		
		// 추천
		$("#bt_recommend").click(function() {
			recommend();
		});
		
		
		// 댓글 작성
		$("#bt_comment").click(function() {
			regist();
		});
		
		
		// 써머 노트 적용
		$('#summernote').summernote({
			height : 600
		});

	});
</script>
</html>
