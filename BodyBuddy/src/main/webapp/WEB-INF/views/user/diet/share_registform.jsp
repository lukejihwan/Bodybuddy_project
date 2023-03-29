<%@page import="com.edu.bodybuddy.domain.diet.DietCategory"%>
<%@page import="com.edu.bodybuddy.domain.diet.DietShare"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%	
	List<DietCategory> dietCategoryList=(List)request.getAttribute("dietCategoryList");
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
	<form id="form">
	<div class="space-medium">
		<div class="container">
			<h1>식단 공유 게시판</h1>
			<hr>
			<div class="row">
				<div class="col-sm-12">
					<form id="form">
						<div class="form-group">
							<select class="form-control" name="category_idx">
								<option value="0">카테고리 선택</option>
								<% for(DietCategory dietCategory:dietCategoryList){ %>
									<option value="<%=dietCategory.getDiet_category_idx()%>"><%=dietCategory.getDiet_category_name() %></option>
								<% } %>
							</select>
						</div>
						<div class="form-group">
							<input type="hidden" name="member_idx" value="<sec:authorize access="isAuthenticated()"><sec:authentication property="principal.member.member_idx"/></sec:authorize>">
							<input type="text" class="form-control" name="title" placeholder="제목을 입력하세요" style="height:100px; font-size:30px">
						</div>
						<div class="form-group">
							<input type="hidden" class="form-control" name="writer" placeholder="작성자..." value="<sec:authorize access="isAuthenticated()"><sec:authentication property="principal.member.nickname"/></sec:authorize>">
						</div>
						
						<div class="form-group">
							<textarea id="summernote" name="content"></textarea>
						</div>
						
						<div class="form-group">
								<input type="hidden" name="preview">
						</div>
						
						<div class="form-group">
							<input type="number" class="form-control" name="kcal" placeholder="칼로리를 입력하세요" style="height:70px; font-size:15px">
							<input type="number" class="form-control" name="carbohydrate" placeholder="탄수화물을 입력하세요" style="height:70px; font-size:15px">
							<input type="number" class="form-control" name="protein" placeholder="단백질을 입력하세요" style="height:70px; font-size:15px">
							<input type="number" class="form-control" name="province" placeholder="지방을 입력하세요" style="height:70px; font-size:15px">
						</div>
						
						<div class="form-group">
							<button type="button" class="btn btn-primary" id="bt_list">목록</button>
							<button type="button" class="btn btn-default pull-right" id="bt_regist">등록</button>
						</div>
					</form>
				</div>
			
			</div>
			<!-- end of row -->
		</div>
		<!-- end of container -->
	</div>
	</form>
	<!-- end of space-medium -->
	<!-- /content end -->

	<!-- black footer_space -->
	<%@include file="../inc/footer_space.jsp"%>

	<!-- tiny footer -->
	<%@include file="../inc/footer_tiny.jsp"%>

	<%@include file="../inc/footer_link.jsp"%>

</body>
<script type="text/javascript">
	//글 등록하기
	function regist(){
		getPreview(); //프리뷰 호출
		
		let formData=new FormData();
		
		formData.append("dietCategory.diet_category_idx", $("#form select[name='category_idx']").val());
		formData.append("member.member_idx", $("#form *[name='member_idx']").val());
		formData.append("title", $("#form input[name='title']").val());
		formData.append("writer", $("#form input[name='writer']").val());
		formData.append("content", $("#form textarea[name='content']").val());
		formData.append("preview", $("#form input[name='preview']").val());
		formData.append("kcal", $("#form input[name='kcal']").val());
		formData.append("carbohydrate", $("#form input[name='carbohydrate']").val());
		formData.append("protein", $("#form input[name='protein']").val());
		formData.append("province", $("#form input[name='province']").val());
		
		$.ajax({
			url:"/rest/diet/share_regist",
			type:"post",
			contentType:false,
			processData:false,
			data:formData,
			success:function(result, status, xhr){
				alert("글 등록 완료");
				location.href="/diet/share_list"
			}
		});
	}
	
	function getPreview(){
		let domParser = new DOMParser();
		let doc = domParser.parseFromString($("#form textarea[name='content']").val(), "text/html");
		if(doc.querySelector("img") != null){
			$("#form input[name='preview']").val(doc.querySelector("img").src);
		}
	}
	

	$(function(){
		// 써머 노트 적용
		$('#summernote').summernote({
			height:400
		});
		
		// 목록 페이지 이동
		$("#bt_list").click(function(){
			location.href="/diet/share_list";
		});
		
		// 등록
		$("#bt_regist").click(function(){
			regist();
		});

	});

</script>
</html>
