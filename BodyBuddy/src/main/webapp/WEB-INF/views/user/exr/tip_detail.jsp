<%@page import="com.edu.bodybuddy.domain.exr.ExrTip"%>
<%@page import="com.edu.bodybuddy.domain.exr.ExrRoutine"%>
<%@page import="com.edu.bodybuddy.domain.exr.ExrCategory"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	
	ExrTip exrTip=(ExrTip)request.getAttribute("exrTip");
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
	<div class="hero-section" style="background:#EF5350">
		<!-- navigation-->
		<%@include file="../inc/header_navi.jsp"%>
		<!-- /navigation end -->
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-12  col-xs-12">
					<div class="hero-caption pinside50">
						<h1 class="hero-title">운동 팁 게시판</h1>
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
                    <h1 id="title"><%= exrTip.getTitle() %></h1>
                    <span><a href="#"><i class="fa fa-twitter  float-right" onclick="interest()">찜하기</i></a></span>
                    <br/>
                    <span><%= exrTip.getWriter() %> | <%= exrTip.getRegdate().substring(0, 10) + " " + exrTip.getRegdate().substring(10, exrTip.getRegdate().length()-2) %></span>
                    <span class="float-right">조회 <%= exrTip.getHit() %> | 추천 {{recommend}}</span>
                    <hr>
				</div>
			</div>
			
			
				<div class="col-sm-12">

				<div class="form-group">
					<textarea id="summernote" name="content"><%=exrTip.getContent() %></textarea>
				</div>


				<div class="form-group">
					<button type="button" class="btn btn-primary" id="bt_list">목록</button>
					<button type="button" class="btn btn-outline-success" id="bt_edit">수정</button>
					<button type="button" class="btn btn-outline-danger" id="bt_delete">삭제</button>
				</div>

				<div class="col-md-12 mt-5 mb-4 text-center">
					<button class="btn btn-default" id="bt_recommend">
						<i class="fa-solid fa-thumbs-up"></i>추천 {{recommend}}</button>
				</div>

					<hr>
				</div>
				
				<div class="col-sm-9">
					<label class="control-label" for="textarea">Comments</label>
					<form id="form1">
						<input type="hidden" name="recommend" value="<%=exrTip.getRecommend()%>">
						<input type="hidden" name="exr_tip_idx" value="<%=exrTip.getExr_tip_idx()%>">
						<textarea class="form-control" name="content"rows="6" placeholder="댓글 입력 창"></textarea>
						<input type="text" class="form-control" name="writer" placeholder="작성자"/>
						<button id="bt_comment" class="btn btn-default" type="button">등록</button>
					</form>
				</div>

					<!-- ./템플릿 -->


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
	
	
	// 추천하기
	function recommend(){
		$.ajax({
			url:"/rest/exr/tip/recommend/"+$("input[name='exr_tip_idx']").val(),
			type:"GET",
			success:function(result, status, xhr){
				
				app1.recommend=result;
			},
			error:function(xhr, status, err){
				console.log(xhr.responseText);
			}
		});
	}
	
	
	// 작성자 본인에게만 수정삭제 버튼 권한
	function showHide(){
		if('<sec:authentication property="principal.member.member_idx"/>' != <%= exrTip.getMember().getMember_idx() %>){
			$("#bt_edit").hide();
			$("#bt_delete").hide();
			
		}else{
			$("#bt_edit").show();
			$("#bt_delete").show();
		}
	}
	

	function interest(){
		let data = {
			idx: $("input[name='exr_tip_idx']").val(),
			member_idx: $("input[name='member.member_idx']").val(),
			table_name: "운동팁",
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
				recommend:$("input[name='recommend']").val()
			}
		});
	}
	
	
	
	/***onLoad***/
	$(function() {
		init();
		
		
		// 목록 페이지 이동
		$("#bt_list").click(function() {
			location.href = "/exr/tip_list";
		});
		

		// 수정
		$("#bt_edit").click(function() {
			if (confirm("수정하시겠습니까?")) {
				location.href = "/exr/tip/edit/"+$("input[name='exr_tip_idx']").val();
			}
		});
		
		
		// 삭제
		$("#bt_delete").click(function() {
			if (confirm("삭제하시겠습니까?")) {
				//alert($("input[name='exr_tip_idx']").val());
				location.href = "/exr/tip/delete?exr_tip_idx="+$("input[name='exr_tip_idx']").val();
			}
		});

		
		// 추천
		$("#bt_recommend").click(function() {
			recommend();
		});
		
		
		showHide();
		
		
		// 써머 노트 적용
		$('#summernote').summernote({
			height : 400
		});

	});
</script>
</html>
