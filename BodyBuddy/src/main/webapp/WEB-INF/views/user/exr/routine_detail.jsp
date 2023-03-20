<%@page import="com.edu.bodybuddy.domain.exr.ExrRoutine"%>
<%@page import="com.edu.bodybuddy.domain.exr.ExrCategory"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	List<ExrCategory> exrCategoryList=(List<ExrCategory>)request.getAttribute("exrCategoryList");
	
	ExrRoutine exrRoutine=(ExrRoutine)request.getAttribute("exrRoutine");
	//System.out.println("확인"+exrRoutine);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../inc/header_link.jsp"%>
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
						<h1 class="hero-title">루틴 공유 게시판</h1>
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
		<div class="container">
			<h3>루틴 공유 입력폼</h3>

			<div class="row">
				<div class="col">
                    <hr>
                    <h1><%= exrRoutine.getTitle() %></h1>
                    <span><a href="#"><i class="fa fa-twitter  float-right">찜하기</i></a></span>
                    <br/>
                    <span><%= exrRoutine.getWriter() %> | <%= exrRoutine.getRegdate().substring(0, 10) + " " + exrRoutine.getRegdate().substring(10, exrRoutine.getRegdate().length()-2) %></span>
                    <span class="float-right">조회 <%= exrRoutine.getHit() %> | 추천 <%= exrRoutine.getRecommend() %></span>
                    <hr>
				</div>
			</div>
			
			
				<div class="col-sm-12">


				<%=exrRoutine.getContent() %>

				<div class="form-group">
					<button type="button" class="btn btn-primary" id="bt_list">목록</button>
					<button type="button" class="btn btn-outline-success" id="bt_edit">수정</button>
				</div>

				<div class="col-md-12 mt-5 mb-4 text-center">
					<button class="btn btn-default" id="bt_recommend">
						<i class="fa-solid fa-thumbs-up"></i>추천 <%=exrRoutine.getRecommend() %></button>
				</div>

					<hr>
				</div>
				
				<div class="col-sm-9">
					<label class="control-label" for="textarea">Comments</label>
					<form id="form1">
						<input type="hidden" name="exr_routine_idx" value="<%=exrRoutine.getExr_routine_idx()%>">
						<textarea class="form-control" name="content"rows="6" placeholder="댓글 입력 창"></textarea>
						<input type="text" class="form-control" name="writer" placeholder="작성자"/>
						<button id="bt_comment" class="btn btn-default" type="button">등록</button>
					</form>
				</div>

				<div class="col-sm-9" id="app1">
					
					<!-- Vue 템플릿 시작 예정 -->
					<template v-for="comment in commentList">
						<row :dto="comment"/>
					</template>
					
					<!-- ./템플릿 -->
				</div>


			</div>
			<!-- end of row -->
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
	
	const row={
			template:`
				<div>
					<hr>
					<h4 class="user-title mb10">{{comment.content}}</h4>
					<div>
						<button type="button" class="btn btn btn-danger float-right" @click="del(comment.exr_routine_comment_idx)">삭제</button>
						<button id="bt_comment" class="btn btn-default float-right" type="button" @click="reply()">답글달기</button>
					</div>
					<div class="comment-meta">
						<span class="comment-meta-date">{{comment.regdate}}</span>
					</div>
					<div class="comment-content">
						<p>{{comment.writer}}</p>
					</div>
				</div>
			`,
			props:["dto"],
			data(){
				return{
					comment:this.dto					
				}
			},
			methods:{
				// 답글 등록(대댓글)
				reply:function(){
					if (!confirm("답글을 등록하시겠습니까?")) {return;}
					
					let formData=new FormData();
					formData.append("exrRoutine.exr_routine_idx", $("input[name='exr_routine_idx']").val());
					formData.append("content", $("#form1 textarea[name='content']").val());
					formData.append("writer", $("#form1 input[name='writer']").val());
					
					$.ajax({
						url:"/rest/exr/routine/comment/reply",
						type:"POST",
						contentType:false,
						processData:false,
						data:formData,
						success:function(result, status, xhr){
							alert(result.msg);
							
							getComments();
							// 내용 비워주기
							$("#form1 textarea[name='content']").val("");
							$("#form1 input[name='writer']").val("");
						},
					});
				},
				
				// 댓글 삭제
				del:function(idx){
					if(!confirm("답글을 삭제하시겠습니까?")){
						return;
					}
					
					$.ajax({
						url:"/rest/exr/routine/comment/"+idx,
						type:"delete",
						success:function(result, status, xhr){
							alert(result.msg);
							getComments();
						},
					});
				}
			}
		}

/* 	function reply(){
		let formData=new FormData();
		formData.append("exrRoutine.exr_routine_idx", $("input[name='exr_routine_idx']").val());
		formData.append("content", $("#form1 textarea[name='content']").val());
		formData.append("writer", $("#form1 input[name='writer']").val());
		
		$.ajax({
			url:"/rest/exr/routine/reply",
			type:"POST",
			contentType:false,
			processData:false,
			data:formData,
			success:function(result, status, xhr){
				alert(result.msg);
				
				getComments();
				// 내용 비워주기
				$("#form1 textarea[name='content']").val("");
				$("#form1 input[name='writer']").val("");
			},
		});
	} */
					
					
	function regist(){
		let formData=new FormData();
		formData.append("exrRoutine.exr_routine_idx", $("input[name='exr_routine_idx']").val());
		formData.append("content", $("#form1 textarea[name='content']").val());
		formData.append("writer", $("#form1 input[name='writer']").val());
		
		$.ajax({
			url:"/rest/exr/routine/comment",
			type:"POST",
			contentType:false,
			processData:false,
			data:formData,
			success:function(result, status, xhr){
				alert(result.msg);
				
				getComments();
				// 내용 비워주기
				$("#form1 textarea[name='content']").val("");
				$("#form1 input[name='writer']").val("");
			},
		});
	}

	
	function getComments(){
		$.ajax({
			url:"/rest/exr/routine/comment/"+$("input[name='exr_routine_idx']").val(),
			type:"GET",
			success:function(result, status, xhr){
				app1.commentList=result;
				console.log(app1.exrRoutineList);
			},
			error:function(xhr, status, err){
				console.log(xhr.responseText);
			}
		});
	}
	
	
	// 추천하기
	function recommend(){
		$.ajax({
			url:"/rest/exr/routine/recommend/"+$("input[name='exr_routine_idx']").val(),
			type:"GET",
			success:function(result, status, xhr){
				alert(result.msg);
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
				commentList:[]
			},
			components:{
				row
			}
		});
	}
	
	
	/***onLoad***/
	$(function() {
		init();
		
		// 댓글 목록 가져오기
		getComments();
		

		// 목록 페이지 이동
		$("#bt_list").click(function() {
			location.href = "/exr/routine_list/1";
		});

		// 수정
		$("#bt_edit").click(function() {
			if (confirm("수정하시겠습니까?")) {
				location.href = "/exr/routine/edit/"+$("input[name='exr_routine_idx']").val();
			}
		});
		
		// 댓글
		$("#bt_comment").click(function() {
			if (confirm("댓글을 등록하시겠습니까?")) {
				regist();
			}
		});
		
		// 추천
		$("#bt_recommend").click(function() {
			recommend();
		});

	});
</script>
</html>
