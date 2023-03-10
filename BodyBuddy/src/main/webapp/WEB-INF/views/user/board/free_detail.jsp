<%@page import="com.edu.bodybuddy.domain.board.FreeBoard"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	FreeBoard board = (FreeBoard)request.getAttribute("board");
	String listURI = "/board/free_list/";
%>
<!DOCTYPE html>
<!-- content 부분만 비워둔 기본 템플릿 -->
<!-- hero섹션이 포함되어있음 -->
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
	<div class="space-medium">
		<div class="container">
			<div class="row">
				<form id="form1">
					<input type="hidden" name="free_board_idx" class="for-send" value="<%= board.getFree_board_idx() %>">
					<div class="form-group">
						<input type="text" class="form-control for-send" name="title" placeholder="제목..." value="<%= board.getTitle() %>">
					</div>
					<div class="form-group">
						<input type="text" class="form-control for-send" name="writer" placeholder="작성자..." value="<%= board.getWriter() %>">
					</div>
					<div class="form-group">
						<textarea id="summernote" name="content" class="for-send"><%= board.getContent() %></textarea>
					</div>
					<div class="form-group">
						<button type="button" class="btn btn-primary" id="bt_list">목록</button>
						<button type="button" class="btn btn-danger pull-right" id="bt_del">삭제</button>
						<button type="button" class="btn btn-default pull-right" style="margin-right: 10px" id="bt_edit">수정</button>
					</div>
				</form>
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
	$(()=>{
		$('#summernote').summernote({
			minHeight:200
		});
		
		$("#bt_edit").click(()=>{
			edit();
		});
		$("#bt_del").click(()=>{
			del();
		});
		$("#bt_list").click(()=>{
			location.href="<%= listURI %>" + 1;
		});
	});
	
	function edit() {
		if(!confirm("수정하시겠습니까?")) return; 
		
		
		let json = {};
		$.each($(".for-send"), (i, item)=>{
		    json[item.name] = item.value;
		});
		
		
		
		//writer 언젠가 세션에 사용자 id로 넣어야 함
		$.ajax({
			url:"/rest/board/free_board",
			type:"PUT",
			contentType:"application/json;charset=utf-8",
			processData:false,
			data:JSON.stringify(json),
			success:(result, status, xhr)=>{
				console.log(result);
			}
		});
	}
	
	function del() {
		if(!confirm("수정하시겠습니까?")) return; 
		
		$("#form1").attr({
			action:"/board/free_delete?free_board_idx="+$("input[type='hidden']"),
			method:"DELETE"
		});
		
		$("#form1").submit();
	}
	

</script>
</html>
