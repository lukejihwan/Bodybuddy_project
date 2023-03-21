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
				
			</div>
		</div>
	</div>
	<!-- ./hero section end -->

	<!-- content start -->
	<div class="space-medium">
		<div class="container">
			<h1>식단 팁 게시판</h1>
			<hr>
			<div class="row">
				<div class="col-sm-12">
					<form id="form">
						<div class="form-group">
							<input type="hidden" class="form-control" name="diet_tip_idx"value="<%=dietTip.getDiet_tip_idx()%>">
							<input type="text" class="form-control" name="title" placeholder="제목을 입력하세요" style="height:100px; font-size:30px" value="<%=dietTip.getTitle()%>">
						</div>
						<div class="form-group">
							<!-- 작성자... -->
						</div>
						
						<div class="form-group">
							<textarea id="summernote" name="content"><%=dietTip.getContent() %></textarea>
						</div>
						
						<div class="form-group">
							<button type="button" class="btn btn-primary" id="bt_cancel">취소</button>
							<button type="button" class="btn btn-default pull-right" id="bt_edit">수정</button>
						</div>
					</form>
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
	//글 수정 
	function edit(){
		if(!confirm("수정하시겠습니까?")){
			return;
		}
		
		let json={};
		json['diet_tip_idx']=$("#form input[name='diet_tip_idx']").val();
		json['title']=$("#form input[name='title']").val();
		json['content']=$("#form textarea[name='content']").val();
	
		$.ajax({
			url:"/rest/diet/tip_edit",
			type:"put",
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify(json), 
			processData:false,
			success:function(result, status, xhr){
				alert("글 수정 완료");
				location.href="/diet/tip_detail/<%=dietTip.getDiet_tip_idx()%>";
			}
			
		});
	}


	$(function(){
		// 써머 노트 적용
		$('#summernote').summernote({
			height:800
		});
		
		//취소버튼 
		$("#bt_cancel").click(function(){
			history.back();
		});
		
		//수정버튼 
		$("#bt_edit").click(function(){
			edit();
		});
	});

</script>
</html>
