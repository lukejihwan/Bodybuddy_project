<%@page import="com.edu.bodybuddy.domain.diet.DietCategory"%>
<%@page import="com.edu.bodybuddy.domain.diet.DietShare"%>
<%@page import="com.edu.bodybuddy.domain.diet.DietTip"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%
	List<DietCategory> dietCategoryList=(List)request.getAttribute("dietCategoryList");
	DietShare dietShare=(DietShare)request.getAttribute("dietShare");
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
							<input type="hidden" class="form-control" name="diet_share_idx"value="<%=dietShare.getDiet_share_idx()%>">
							<div class="form-group">
							<select class="form-control" name="category_idx">
								<option value="0">카테고리 선택</option>
								<% for(DietCategory dietCategory:dietCategoryList){ %>
									<option value="<%=dietCategory.getDiet_category_idx()%>"><%=dietCategory.getDiet_category_name() %></option>
								<% } %>
							</select>
							</div>
							<input type="text" class="form-control" name="title" placeholder="제목을 입력하세요" style="height:100px; font-size:30px" value="<%=dietShare.getTitle()%>">
						</div>
						<div class="form-group">
							<!-- 작성자... -->
						</div>
						
						<div class="form-group">
							<textarea id="summernote" name="content"><%=dietShare.getContent() %></textarea>
							
							<div class="form-group">
								<input type="hidden" name="preview" value="<%=dietShare.getPreview()%>">
							</div>
							
							<div class="form-group row">
								<input type="number" name="kcal" class="form-control" placeholder="열량을 입력하세요" value="<%=dietShare.getKcal()%>">
							</div>
							<div class="form-group row">
								<input type="number" name="carbohydrate" class="form-control" placeholder="탄수화물을 입력하세요" value="<%=dietShare.getCarbohydrate()%>">
							</div>
							<div class="form-group row">
								<input type="number" name="protein" class="form-control" placeholder="단백질을 입력하세요" value="<%=dietShare.getProtein()%>">
							</div>
							<div class="form-group row">
								<input type="number" name="province" class="form-control" placeholder="지방을 입력하세요" value="<%=dietShare.getProvince()%>">
							</div>
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
		getPreview();
		
		let json={};
		json['diet_share_idx']=$("#form input[name='diet_share_idx']").val();
		json['category_idx']=$("#form select[name='category_idx']").val();
		json['title']=$("#form input[name='title']").val();
		json['content']=$("#form textarea[name='content']").val();
		json['preview']=$("#form input[name='preview']").val();
		json['kcal']=$("#form input[name='kcal']").val();
		json['carbohydrate']=$("#form input[name='carbohydrate']").val();
		json['protein']=$("#form input[name='protein']").val();
		json['province']=$("#form input[name='province']").val();
	
		$.ajax({
			url:"/rest/diet/share_edit",
			type:"put",
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify(json), 
			processData:false,
			success:function(result, status, xhr){
				alert("글 수정 완료");
				location.href="/diet/share_detail/<%=dietShare.getDiet_share_idx()%>";
			}		
		});
	}
	
	//프리뷰 구하기
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
