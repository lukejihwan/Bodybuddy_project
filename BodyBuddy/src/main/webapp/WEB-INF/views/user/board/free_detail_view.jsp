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
<style type="text/css">
.comment-right button, .comment-right span{
	margin-right: 10px;
}
.comment-content{
	cursor: pointer;
}
.comment-content:hover>span{
	background-color: #c5f016;
}

.rep>:first-child>:last-child{
	border-left: 2px solid rgba(0, 0, 0, 0.2);
}

@media (max-width: 767.98px){
	.comment-wrapper-1{
		padding-left: 30px;
	}
	.comment-wrapper-2{
		padding-left: 60px;
	}
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
                    <span class="float-right">조회 <%= board.getHit() %> | 추천 {{recommend}}</span>
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
					<button class="btn btn-default" id="bt_recommend"><i class="fa-solid fa-thumbs-up"></i> {{recommend}}</button>
				</div>
			</div>
			<hr>
			<!-- end of row -->
			<button class="btn btn-primary" id="bt_list">목록</button>
			<button type="button" class="btn btn-danger pull-right writer-check" id="bt_del">삭제</button>
			<button type="button" class="btn btn-default pull-right writer-check" style="margin-right: 10px" id="bt_edit">수정</button>
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
				<div :class="'comment-wrapper-'+comment.depth+(comment.depth==0?'':' rep')">
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
									<button type="button" class="btn btn-danger btn-sm float-right comment-writer-check" :value="comment.member.member_idx" @click="deleteComment(board_comment_idx)">
									<i class="fa-solid fa-xmark"></i></button>
									<button type="button" class="btn btn-default btn-sm float-right comment-writer-check" :value="comment.member.member_idx" @click="toggleForm('edit')">
									<i class="fa-solid fa-pen-to-square"></i></button>
									<span>{{comment.regdate.substr(5, 5)}} {{comment.regdate.substr(11, 5)}}</span>
								</div>
							</div>
						</div>
					</div>
					<!-- end of row -->
					
					
					<div class="row">
						<div :class="'col-md-'+(comment.depth+1)"></div>
						<div :class="'col-md-'+(12-(comment.depth+1))">
							<form class="row" :id="'form-comment-'+board_comment_idx" style="display: none;">
								<input type="hidden" class="for-send" name="<%= boardCommentIdxName %>" :value="board_comment_idx"/>
								<input type="hidden" class="for-send" name="<%= boardName+"."+boardIdxName %>" :value="comment.<%= boardName+"."+boardIdxName %>"/>
								<input type="hidden" class="for-send" name="member.member_idx" value='<sec:authorize access="isAuthenticated()"><sec:authentication property="principal.member.member_idx"/></sec:authorize>'/>
								<input type="hidden" class="for-send" name="writer" value='<sec:authorize access="isAuthenticated()"><sec:authentication property="principal.member.nickname"/></sec:authorize>'/>
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
					<input type="hidden" class="for-send" name="member.member_idx" value='<sec:authorize access="isAuthenticated()"><sec:authentication property="principal.member.member_idx"/></sec:authorize>'/>
					<input type="hidden" class="for-send" name="writer" value='<sec:authorize access="isAuthenticated()"><sec:authentication property="principal.member.nickname"/></sec:authorize>'/>
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
		getBoard();
		writerCheck();
		
		$("#bt_edit").click(()=>{
			location.href="<%= DetailEditURI + board_idx %>";
		});
		$("#bt_del").click(()=>{
			del();
		});
		$("#bt_list").click(()=>{
			location.href="<%= listURI+1 %>";
		});
		$("#bt_recommend").click(()=>{
			recommend();
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
	        	recommend:5
	        },
	        updated(){
	        	//commentList가 렌더링 된 이후에 로그인 한사용자가 작성한 댓글만 수정, 삭제 버튼 보여짐
	        	commentWriterCheck();
	        }
		});
	}
	
	function del() {
		Swal.fire({
		  title: '게시글을 삭제하시겠습니까?',
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#c5f016',
		  cancelButtonColor: '#d33',
		  confirmButtonText: '네, 삭제할래요',
		  cancelButtonText: '아니요, 삭제하지 않겠습니다'
		}).then((result) => {
			if (result.isConfirmed) {
				location.href="<%= deleteURI + board_idx %>";
		  	}
		})
	}
	
	function registComment(value){
		<sec:authorize access="isAnonymous()">
			Swal.fire({
				title:"로그인해야 사용할 수 있는 기능입니다",
				icon:"warning",
				confirmButtonText:"확인",
				confirmButtonColor: '#c5f016'
			});
			return;
		</sec:authorize>
		
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
		
		Swal.fire({
			  title: '댓글을 수정하시겠습니까?',
			  icon: 'question',
			  showCancelButton: true,
			  confirmButtonColor: '#c5f016',
			  cancelButtonColor: '#d33',
			  confirmButtonText: '네, 수정할래요',
			  cancelButtonText: '아니요, 수정하지 않겠습니다'
			}).then((result) => {
				if (result.isConfirmed) {
					$.ajax({
						url:"/rest/board/<%= boardName %>/comment",
						type:"PUT",
						contentType:"application/json;charset=utf-8",
						processData:false,
						data:JSON.stringify(json),
						success:(result, status, xhr)=>{
							console.log(result.msg);
							Swal.fire(
								"수정 성공",
								"",
								"success"
							).then(()=>{
								$("#form-comment-"+value+" textarea").val("");
								getList();
								$("#form-comment-"+value).hide(400);
							});
						},
						error:(xhr, status, err)=>{
							console.log(xhr);
							Swal.fire(
								"수정 실패",
								"",
								'error'
							);
						}
					});
			  	}
			})
		
		
	
		
	}
	
	function deleteComment(value) {
		
		Swal.fire({
			  title: '댓글을 삭제하시겠습니까?',
			  icon: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#c5f016',
			  cancelButtonColor: '#d33',
			  confirmButtonText: '네, 삭제할래요',
			  cancelButtonText: '아니요, 삭제하지 않겠습니다'
			}).then((result)=>{
				if (result.isConfirmed) {
					$.ajax({
						url:"/rest/board/<%= boardName %>/comment/"+value,
						type:"DELETE",
						success:(result, status, xhr)=>{
							console.log(result.msg);
							Swal.fire(
								"삭제 성공",
								"",
								"success"
							).then(()=>{
								getList();
							});
						},
						error:(xhr, status, err)=>{
							console.log(xhr);
							Swal.fire(
								"삭제 실패",
								"",
								'error'
							);
						}
					});
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
	
	function recommend() {
		let json = {};
		json["<%= boardIdxName %>"] = "<%= board_idx %>";
		//json["member.member_idx"] = ?;
		$.ajax({
			url:"/rest/board/<%= boardName %>/recommend",
			type:"PUT",
			contentType:"application/json;charset=utf-8",
			processData:false,
			data:JSON.stringify(json),
			success:(result, status, xhr)=>{
				console.log(result.msg);
				getBoard();
			},
			error:(xhr, status, err)=>{
				console.log(xhr);
			}
		});
	}
	
	function getBoard() {
		$.ajax({
			url:"/rest/board/freeBoard/"+<%= board_idx %>,
			type:"GET",
			success:(result, status, xhr)=>{
				app1.recommend=result.recommend;
			},
			error:(xhr, status, err)=>{
				console.log(xhr);
			}
		});
	}
	
	function writerCheck() {
		<sec:authorize access="isAuthenticated()">
			if('<sec:authentication property="principal.member.member_idx"/>' != <%= board.getMember().getMember_idx() %>){
				$.each($(".writer-check"), (i, item)=>{
			    	$(item).hide();
				});
			}
		</sec:authorize>
	}
	
	function commentWriterCheck() {
		<sec:authorize access="isAuthenticated()">
			$.each($(".comment-writer-check"), (i, item)=>{
				if('<sec:authentication property="principal.member.member_idx"/>' != item.value){
			    	$(item).hide();
				}
		    	
			});
		</sec:authorize>
		<sec:authorize access="isAnonymous()">
			$.each($(".comment-writer-check"), (i, item)=>{
		    	$(item).hide();
			});
		</sec:authorize>
	}
</script>
</html>
