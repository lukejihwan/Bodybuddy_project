<%@page import="com.edu.bodybuddy.domain.board.FreeBoard"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	FreeBoard board = (FreeBoard)request.getAttribute("board");
	String listURI = "/board/free_list/"; // 게시글 목록 uri ex. /board/free_list/
	String deleteURI = "/board/free_delete?free_board_idx="; //게시글 삭제요청 uri ex. /board/free_delete?free_board_idx=
	String DetailEditURI = "/board/free_detail_edit/"; //게시글 수정페이지 uri
	int board_idx = board.getFree_board_idx(); // board idx 값
	String boardIdxName = "free_board_idx"; //board idx 컬럼명
	String boardCommentIdxName = "free_board_comment_idx"; //board_comment_idx 컬럼명
	String boardName = "freeBoard"; // 게시판 명
%>
<!DOCTYPE html>
<!-- content 부분만 비워둔 기본 템플릿 -->
<!-- hero섹션이 포함되어있음 -->
<html lang="en">
<head>
<%@include file="../inc/header_link.jsp"%>
<script src="https://kit.fontawesome.com/99ef7b560b.js" crossorigin="anonymous"></script>
<style type="text/css">
.comment-right button, span{
	margin-right: 10px;
}
.comment-content{
	cursor: pointer;
	
}
.comment-content:hover>span{
	background-color: #c5f016;
}

@media (min-width: 767.98px){
	
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
				<comment_form :idx="<%= board_idx %>"/>
			</template>
			<br/>
			<template v-for="(comment) in commentList">
				<comment :key="comment.<%= boardCommentIdxName %>" :comment="comment"/>
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
	
	const formState = {
			"reply":{
				placeholder:"댓글 작성...",
				buttonText:"등록"
			},
			"edit":{
				placeholder:"댓글 수정...",
				buttonText:"수정"
			}
	}
	
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
								<div :class="'col-md-7 '+(comment.depth < depthLimit ? 'comment-content' : '') " @click="toggleForm('reply')">
									<span>{{comment.comment}}</span>
								</div>
								<div class="col-md-3 comment-right">
									<button type="button" class="btn btn-danger btn-sm float-right" @click="deleteComment(board_comment_idx)">
									<i class="fa-solid fa-xmark"></i></button>
									<button type="button" class="btn btn-default btn-sm float-right" :value="board_comment_idx" @click="toggleForm('edit')">
									<i class="fa-solid fa-pen-to-square"></i></button>
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
							<form class="row" :id="'form-comment-'+board_comment_idx" style="display: none;">
								<input type="hidden" class="for-send" name="<%= boardCommentIdxName %>" :value="board_comment_idx"/>
								<input type="hidden" class="for-send" name="<%= boardName+"."+boardIdxName %>" :value="comment.<%= boardName+"."+boardIdxName %>"/>
								<input type="hidden" class="for-send" name="post" :value="comment.post"/>
								<input type="hidden" class="for-send" name="step" :value="comment.step"/>
								<input type="hidden" class="for-send" name="depth" :value="comment.depth"/>
								<div class="col-md-10">
									<textarea rows="5" class="form-control for-send" style="margin-top:10px;" name="comment" placeholder="댓글 작성..." maxlength="500"></textarea>
								</div>
								<div class="col-md-2 d-flex align-items-center  justify-content-center">
									<button type="button" class="btn btn-secondary bt_regist_comment" @click="handleComment(board_comment_idx)">등록</button>
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
				toggleForm:function(state){
					/* console.log("comment",this.comment);
					console.log("post",this.comment.post);
					console.log("step",this.comment.step);
					console.log("depth", this.comment.depth); */

					
					if(this.comment.depth>=this.depthLimit && state=="reply") return;
					
					if(($("#form-comment-"+this.board_comment_idx).is(':visible'))) {
						this.toggleFlag = true;
					}else{
						this.toggleFlag = false;
					}
					
					$.each(app1.commentList, (i, item)=>{
						let itemCommentIdx = item["<%= boardCommentIdxName %>"];
						if(itemCommentIdx!=this.board_comment_idx){
					    	$("#form-comment-"+itemCommentIdx).hide(400);
						}
					});
					
					
					//console.log("stateFlag",this.stateFlag);
					if(this.toggleFlag){
						$("#form-comment-"+this.board_comment_idx).hide(400, ()=>{
							console.log("state",state);
							if(this.stateFlag!=state){
								this.setForm(state);
								$("#form-comment-"+this.board_comment_idx).show(400);
							}
							this.stateFlag=state;
						});
					}else{
						this.setForm(state);
						$("#form-comment-"+this.board_comment_idx).show(400);
						this.stateFlag=state;
					}
					
					
				},
				handleComment: function(value){
					console.log(this.stateFlag);
					
					if(this.stateFlag=="reply"){
						window.registComment(value);
					}else if(this.stateFlag=="edit"){
						window.editComment(value);
					}
				},
				deleteComment: window.deleteComment,
				setForm:function(state){
					$("#form-comment-"+this.board_comment_idx+" textarea").val("");
					$("#form-comment-"+this.board_comment_idx+" textarea").attr("placeholder", formState[state].placeholder);
					$("#form-comment-"+this.board_comment_idx+" button").text(formState[state].buttonText);
				}
			},
			data(){
				return {
					toggleFlag:false, //form 보여질지 말지 toggle 기능을 결정하는 flag
					board_comment_idx:this.comment["<%= boardCommentIdxName %>"],
					depthLimit:2,
					stateFlag:"", //열린 폼이 답글 달기인지 댓글 수정하기 인지 결정하는 플래그. 기본값 없어도 됨
				};
			}
	};
	
	const comment_form = {
			template:`
				<form class="row" id="form-comment-0">
					<input type="hidden" name="<%= boardName+"."+boardIdxName %>" :value="idx"/>
					<div class="col-md-10">
						<textarea rows="5" class="form-control" style="margin-top:10px;" name="comment" placeholder="댓글 작성..." maxlength="500"></textarea>
					</div>
					<div class="col-md-2 d-flex align-items-center  justify-content-center">
						<button type="button" class="btn btn-secondary bt_regist_comment" @click="registComment(0)">등록</button>
					</div>
				</form>
			`,	
			props:["idx"],
			methods:{
				registComment: window.registComment
			}
	};
	
	
	let a;
	$(()=>{
		init();
		getList();	
		
		$("#bt_edit").click(()=>{
			location.href="<%= DetailEditURI + board_idx %>";
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
	        },
		});
	}
	
	function del() {
		if(!confirm("삭제하시겠습니까?")) return; 
		

		location.href="<%= deleteURI + board_idx %>";
	}
	
	function registComment(value){
		console.log($("#form-comment-"+value).serialize());
		$.ajax({
			url:"/rest/board/<%= boardName %>/comment",
			type:"POST",
			data:$("#form-comment-"+value).serialize(),
			success:(result, status, xhr)=>{
				console.log(result);
				$("#form-comment-"+value+" textarea").val("");
				if(value!=0){
					$("#form-comment-"+value).hide(400, ()=>{
						getList();
					});
				}else{
					getList();
				}
			},
			error:(xhr, status, err)=>{
				console.log("ajax 실패 ", xhr);
			}
		});
	}
	
	function editComment(value) {
		let json = {};
		$.each($("#form-comment-"+value+" .for-send"), (i, item)=>{
		    json[item.name] = item.value;
		});
		
		$.ajax({
			url:"/rest/board/<%= boardName %>/comment",
			type:"PUT",
			contentType:"application/json;charset=utf-8",
			processData:false,
			data:JSON.stringify(json),
			success:(result, status, xhr)=>{
				console.log(result.msg);
				getList();
			},
			error:(xhr, status, err)=>{
				console.log(xhr);
			}
		});
	
		
	}
	
	function deleteComment(value) {
		
		$.ajax({
			url:"/rest/board/<%= boardName %>/comment/"+value,
			type:"DELETE",
			success:(result, status, xhr)=>{
				console.log(result.msg);
				getList();
			},
			error:(xhr, status, err)=>{
				console.log(xhr);
			}
		});
	}
	
	function getList() {
		$.ajax({
			url:"/rest/board/<%= boardName %>/comment/board/<%= board_idx %>",
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
