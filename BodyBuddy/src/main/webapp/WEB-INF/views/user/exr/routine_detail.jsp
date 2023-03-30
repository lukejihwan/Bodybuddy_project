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
<style type="text/css">
	.hero-section{
		background-image: url("/resources/user/images/exr/routine_back.jpg");
	}
		.comment-area{
		/* background:pink; */
		border:solid 2px gray;
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
		<div class="container" id="app1">
			<h3>루틴 공유 게시판</h3>

			<div class="row">
				<div class="col">
                    <hr>
                    <h1 id="title"><%= exrRoutine.getTitle() %></h1>
                    <span><a href="#"><i class="fa fa-twitter  float-right" onclick="interest()">찜하기</i></a></span>
                    <br/>
                    <span><%= exrRoutine.getWriter() %> | <%= exrRoutine.getRegdate().substring(0, 10) + " " + exrRoutine.getRegdate().substring(10, exrRoutine.getRegdate().length()-2) %></span>
                    <span class="float-right">조회 <%= exrRoutine.getHit() %> | 추천 {{recommend}}</span>
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
						<i class="fa-solid fa-thumbs-up"></i>추천 {{recommend}}</button>
				</div>

					<hr>
				</div>

			<div class="comment-area">
				<div class="section">

					<div class="col-sm-9">
						<label class="control-label" for="textarea">Comments</label>
						<form id="form1">
							<input type="hidden" name="member.member_idx"
								value="<sec:authorize access="isAuthenticated()"><sec:authentication property="principal.member.member_idx"/></sec:authorize>">
							<input type="hidden" name="recommend"
								value="<%=exrRoutine.getRecommend()%>"> <input
								type="hidden" name="exr_routine_idx"
								value="<%=exrRoutine.getExr_routine_idx()%>">
							<textarea class="form-control" name="content" rows="6"
								placeholder="댓글 입력 창"></textarea>
							<input type="text" class="form-control" name="writer"
								value="<sec:authorize access="isAuthenticated()"><sec:authentication property="principal.member.nickname"/></sec:authorize>" />
							<button id="bt_comment" class="btn btn-default" type="button">등록</button>
						</form>
					</div>

					<div class="col-sm-9">

						<!-- Vue 템플릿 시작 예정 -->
						<template v-for="comment in commentList">
							<row :dto="comment" :depth="comment.depth*70"
								:key="comment.exr_today_comment_idx" />
						</template>


						<div id="replySection" class="col-sm-9 mt-3">
							<hr>
							<form id="form2">
								<input type="hidden" name="exr_routine_idx"
									value="<%=exrRoutine.getExr_routine_idx()%>">
								<textarea class="form-control" name="content" rows="6"
									placeholder="댓글 입력 창"></textarea>
								<input type="text" class="form-control" name="writer"
									value="<sec:authorize access="isAuthenticated()"><sec:authentication property="principal.member.nickname"/></sec:authorize>" />
								<button id="bt_reply" class="btn btn-default" type="button">등록</button>
							</form>
						</div>

						<!-- ./템플릿 -->
					</div>
				</div>
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
	let selectRow;
	
	// 댓글 목록 출력
	const row={
			template:`
				<div class="replyform" :style="'margin-left:'+c_depth+'px;'">
					<hr>
					<h4 class="user-title mb10">{{comment.content}}</h4>
					<div>
						<button type="button" class="btn btn btn-danger float-right" @click="del(comment.exr_routine_comment_idx)">삭제</button>
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
					
					
					// 답글
					$("#bt_reply").click(function() {
						reply(comment);
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
						}
					});
				}
			}
		}
	
			
	
	// 댓글 등록
	function regist(){
		<sec:authorize access="isAnonymous()">
			Swal.fire({
				title:"로그인해야 사용할 수 있는 기능입니다",
				icon:"warning",
				confirmButtonText:"확인",
				confirmButtonColor: '#c5f016'
			});
			return;
		</sec:authorize>
		
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
				
				location.href="/exr/routine_detail/"+$("input[name='exr_routine_idx']").val();
			},
		});
	}

	//대댓글 등록
 	function reply(comment){
		//if (!confirm("답글을 등록하시겠습니까?")) {return;}
		let formData=new FormData();
		formData.append("exrRoutine.exr_routine_idx", $("input[name='exr_routine_idx']").val());
		formData.append("content", $("#form2 textarea[name='content']").val());
		formData.append("writer", $("#form2 input[name='writer']").val());
		formData.append("post", comment.post);
		
		$.ajax({
			url:"/rest/exr/routine/comment/reply",
			type:"POST",
			contentType:false,
			processData:false,
			data:formData,
			success:function(result, status, xhr){
				alert(result.msg);
				
				location.href="/exr/routine_detail/"+$("input[name='exr_routine_idx']").val();
			},
		});
	}
	
	
	
	function getComments(){
		$.ajax({
			url:"/rest/exr/routine/comment/"+$("input[name='exr_routine_idx']").val(),
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
	
	
	// 추천하기
	function recommend(){
		$.ajax({
			url:"/rest/exr/routine/recommend/"+$("input[name='exr_routine_idx']").val(),
			type:"GET",
			success:function(result, status, xhr){
				app1.recommend=result;
			},
			error:function(xhr, status, err){
				console.log(xhr.responseText);
			}
		});
	}
	
	function interest(){
		let data = {
				idx: $("input[name='exr_routine_idx']").val(),
				table_name: "운동루틴",
				title: $("#title").text()
		}
		$.ajax({
			type: "post",
			url: "/mypage/interest",
			data: data,
			success: (result)=>{
				console.log(result);
				alert(result.msg);
			}
		})
	}

	
	function init(){
		app1=new Vue({
			el:"#app1",
			data:{
				commentList:[],
				recommend:$("input[name='recommend']").val()
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
		$("#replySection").hide();

		
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
