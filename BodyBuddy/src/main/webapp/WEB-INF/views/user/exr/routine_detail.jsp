<%@page import="com.edu.bodybuddy.domain.exr.ExrRoutine"%>
<%@page import="com.edu.bodybuddy.domain.exr.ExrCategory"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	List<ExrCategory> exrCategoryList=(List<ExrCategory>)request.getAttribute("exrCategoryList");
	
	ExrRoutine exrRoutine=(ExrRoutine)request.getAttribute("exrRoutine");
	System.out.println("확인"+exrRoutine);
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
			<h1>Share Your Excercise Routine !</h1>
			<hr>
			<div class="row">
				<div class="col-sm-12">
					<form id="form1">
						<div class="form-group">
							<input type="hidden" name="exr_routine_idx" value="<%=exrRoutine.getExr_routine_idx() %>">
							<input type="text" class="form-control" name="title" value="<%=exrRoutine.getTitle() %>" style="height:100px; font-size:30px">
						</div>
						<div class="form-group">
							<input type="text" class="form-control" name="writer" value="<%=exrRoutine.getWriter() %>">
						</div>
						
						<div class="form-group">
							<select class="form-control" name="exrCategory" id="exrCategory">
								<option value="0"><%=exrRoutine.getExrCategory().getExr_category_name() %></option>
								<% for(ExrCategory exrCategory:exrCategoryList){ %>
									<option value="<%=exrCategory.getExr_category_idx()%>"><%=exrCategory.getExr_category_name() %></option>
								<% } %>
							</select>
						</div>
						
						<div class="form-group">
							<textarea id="summernote" name="content"><%=exrRoutine.getContent() %></textarea>
						</div>
						
						<div class="form-group">
							<button type="button" class="btn btn-primary" id="bt_list">목록</button>
							<button type="button" class="btn btn-outline-success" id="bt_edit">수정</button>
							<button type="button" class="btn btn-outline-danger" id="bt_delete">삭제</button>
						</div>
					</form>

				<hr>
				
					<h1 class="reply-title">Leave A Comment</h1>
					<label class="control-label" for="textarea">Comments</label>
					<textarea class="form-control" id="textarea" name="textarea"
						rows="6" placeholder=" "></textarea>

					<div class="form-group">
						<label class="control-label" for="name">Name</label> <input
							id="name" name="name" type="text" class="form-control"
							placeholder=" ">
					</div>

					<div class=" widget widget-archives">
						<h2 class="widget-title">댓글</h2>
						<ul class="listnone bullet bullet-long-arrow">
							<li>September 2016</li>
							<li>September 2016</li>
							<li>September 2016</li>
							<li>September 2016</li>
						</ul>
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
	// 수정
	function edit() {
		let json = {};
		let obj = {
			exr_category_name : $("#exrCategory option:checked").text(),
			exr_category_idx : $("#form1 select[name='exrCategory']").val()
		};

		json['exrCategory'] = obj;

		json['exr_routine_idx'] = $("#form1 input[name='exr_routine_idx']")
				.val();
		json['title'] = $("#form1 input[name='title']").val();
		json['writer'] = $("#form1 input[name='writer']").val();
		json['content'] = $("#form1 textarea[name='content']").val();

		console.log(JSON.stringify(json));

		$.ajax({
			url : "/rest/exr/routine",
			type : "PUT",
			contentType : "application/json;charset=utf-8",
			processData : false,
			data : JSON.stringify(json),

			success : function(result, status, xhr) {
				alert(result.msg);
				console.log("성공시 출력 ", result);
			},

			error : function(xhr, status, err) {
				console.log("에러시 출력 ", xhr.responseText);
			}
		});
	}

	/***onLoad***/
	$(function() {

		// 목록 페이지 이동
		$("#bt_list").click(function() {
			location.href = "/exr/routine_list";
		});

		// 수정
		$("#bt_edit").click(function() {
			if (confirm("수정하시겠습니까?")) {
				//editTest();
				edit();
			}
		});

		// 삭제
		$("#bt_delete").click(
				function() {
					if (confirm("삭제하시겠습니까?")) {
						location.href = "/exr/routine/delete?exr_routine_idx="
								+ $("#form1 input[name='exr_routine_idx']")
										.val();
						alert("삭제되었습니다");
					}
				});

		// 써머 노트 적용
		$('#summernote').summernote({
			height : 400
		});
	});
</script>
</html>
