<%@page import="com.edu.bodybuddy.domain.diet.DietTipComments"%>
<%@page import="com.edu.bodybuddy.domain.diet.DietTip"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	DietTip dietTip=(DietTip)request.getAttribute("dietTip");
%>
<!DOCTYPE html>
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
                    <h1>식단팁 게시판</h1>
                    <hr>
                    <h3><%=dietTip.getTitle() %></h3>
                    <input type="hidden" class="form-control" name="diet_tip_idx"value="<%=dietTip.getDiet_tip_idx()%>">

                                     
                    <span class="float-right"><img src="/resources/user/images/diet/heart.png" style="width:20px; height:20px"> 찜하기</span>

					<span class="float-right" onclick="interest()"><a href="#"><img src="/resources/user/images/diet/heart.png" style="width:20px; height:20px"> 찜하기</a></span>

                    <br/>
                    <span><%=dietTip.getWriter() %> | <%=dietTip.getRegdate().substring(0,10) %></span>
                    <span class="float-right">조회 <%=dietTip.getHit() %> | 추천 {{recommend}}</span>
                    <hr>
				</div>
			</div>
			<!-- end of row -->
			<br/>
			<div class="row">
				<div class="col-md-12">
					<%=dietTip.getContent() %>
				</div>
				
				<div class="col-md-12 mt-5 mb-4 text-center">
					<button class="btn btn-default" id="bt_recommend"><i class="fa-solid fa-thumbs-up"></i> {{recommend}}</button>
				</div>	
			</div>
			<hr>
			<!-- end of row -->
			<button class="btn btn-primary float-left" id="bt_list">목록</button>
			<button type="button" class="btn btn-default pull-right" id="bt_del">삭제</button>
			<button type="button" class="btn btn-default pull-right" id="bt_edit">수정</button>
		
			<!-- 댓글영역 -->
			<br/>
			<br/>
			<br/>
			<br/>
			<div class="comment-area">
			<div class="col-sm-12" id="">
					<label class="control-label" for="textarea">Comments</label>
					<form id="form">
						<div class="col">
							<div class="col-md-12">
							<input type="hidden" name="member.member_idx"
							value="<sec:authorize access="isAuthenticated()"><sec:authentication property="principal.member.member_idx"/></sec:authorize>">
							<input type="text" class="form-control" name="writer"
							value="<sec:authorize access="isAuthenticated()"><sec:authentication property="principal.member.nickname"/></sec:authorize>" />	
								<textarea rows="3" class="form-control for-send" name="content" placeholder="댓글 작성..."></textarea>
								<button type="button" class="btn btn-primary float-right" id="bt_comments">등록</button>
							</div>
						</div>
					</form>
			</div>
			<br/>
			<br/>
			<br/>
			<template v-for="comment in commentsList">
				<row :obj="comment" />
			</template>
			<!-- 댓글영역 끝-->
			
		
		
		</div>
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
			<h4 class="user-title mb8">{{comment.content}}</h4>
			<div>
			<button type="button" class="btn-sm btn-danger float-right" @click="del(comment.diet_tip_comments_idx)">삭제</button>
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
		props:["obj"],
		data(){
			return{
				comment:this.obj
			}
		},
		methods:{
			del:function(idx){
				if(!confirm("댓글을 삭제하시겠습니까?")){
					return;
				}
				
				$.ajax({
					url:"/rest/diet/tip/comments/"+idx,
					type:"delete",
					success:function(result, status, xhr){
						alert(result.msg);
						getComments();
					}
				});
			}
		}
	}
	
	/*--------------------------------------
					글 관련 
	---------------------------------------*/

	//글 삭제 
	function del(){
		if(!confirm("삭제하시겠습니까?")){
			return;
		}	
		
		$.ajax({
			url:"/rest/diet/tip_del/"+$("input[name='diet_tip_idx']").val(),
			type:"delete",
			success:function(result, status, xhr){
				alert("글 삭제 완료");
				location.href="/diet/tip_list";
			}
		});		
	}
	
	//디테일페이지 불러오기
	function getDetail(){
		$.ajax({
			url:"/rest/diet/tip_detail/"+<%= dietTip.getDiet_tip_idx() %>,
			type:"get",
			success:(result, status, xhr)=>{
				app1.recommend=result.recommend;
			},
			error:(xhr, status, err)=>{
				console.log(xhr);
			}
		});
	}
	
	//글 추천 
	function recommend(){
		let json = {};
		json["diet_tip_idx"] = "<%= dietTip.getDiet_tip_idx()%>";
		
		$.ajax({
			url:"/rest/diet/tip/recommend",
			type:"put",
			contentType:"application/json;charset=utf-8",
			processData:false,
			data:JSON.stringify(json),
			success:(result, status, xhr)=>{
				alert("추천완료");
				getDetail();	
			
			},
		});
	}
	
	/*--------------------------------------
					댓글 관련 
	---------------------------------------*/
	
	//댓글 등록 
	function commentsRegist(){
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
		formData.append("dietTip.diet_tip_idx", $("input[name='diet_tip_idx']").val());
		formData.append("content", $("#form textarea[name='content']").val());
		formData.append("writer", $("#form input[name='writer']").val());
	
		$.ajax({
			url:"/rest/diet/tip/comments/regist",
			type:"post",
			contentType:false,
			processData:false,
			data:formData,
			success:function(result, status, xhr){
				alert(result.msg);
				
				getComments();
				
				// 내용 비워주기
				$("#form textarea[name='content']").val("");
				//$("#form input[name='writer']").val("");
			}
		});
	}
	
	//댓글 목록 
	function getComments(){
		$.ajax({
			url:"/rest/diet/tip/comments/"+$("input[name='diet_tip_idx']").val(),
			type:"get",
			success:function(result, status, xhr){
				app1.commentsList=result;
			},
			error:function(xhr, status, err){
				console.log(xhr.responseText);
			}
		});
	}

	//찜하기
	function interest(){
		let data = {
			idx: <%=dietTip.getDiet_tip_idx()%>,
			table_name: "식단팁",
			title: "<%=dietTip.getTitle()%>"
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
			components:{
				row
			},
			data:{
				recommend:[],
				commentsList:[]
			}
		});
	}
	
	$(function(){
		init();
		getDetail();

		//댓글 목록
		getComments();
		
		//목록버튼 
		$("#bt_list").click(function(){
			location.href="/diet/tip_list";
		});
		
		//수정버튼
		$("#bt_edit").click(function(){
			location.href="/diet/tip_edit/<%=dietTip.getDiet_tip_idx() %>";
		});
		
		//삭제버튼 
		$("#bt_del").click(function(){
			del();
		});
		
		//추천버튼
		$("#bt_recommend").click(function(){
			recommend();
		});
		
		//댓글등록 버튼 
		$("#bt_comments").click(function() {
			if (confirm("댓글을 등록하시겠습니까?")) {
				commentsRegist();
			}
		});
	});
</script>
</html>
