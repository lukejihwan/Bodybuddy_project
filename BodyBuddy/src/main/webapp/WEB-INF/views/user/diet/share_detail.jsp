<%@page import="com.edu.bodybuddy.domain.diet.DietShare"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	List<DietShare> dietShareList=(List)request.getAttribute("dietShareList");
	DietShare dietShare=(DietShare)request.getAttribute("dietShare");
%>
<!DOCTYPE html>
<!-- content 부분만 비워둔 기본 템플릿 -->
<!-- hero섹션이 포함되어있음 -->
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
                    <h1>식단 공유 게시판</h1>
                    <hr>
                    <input type="hidden" class="form-control" name="diet_share_idx"value="<%=dietShare.getDiet_share_idx()%>">
                    <h3><%=dietShare.getTitle() %></h3>
                    <span><%=dietShare.getDietCategory().getDiet_category_name() %></span>
                    <br/>
                    <span>작성자 | <%=dietShare.getRegdate().substring(0,10) %></span>
                    <span class="float-right">조회 <%=dietShare.getHit() %> | 추천 {{recommend}}</span>
                    <hr>
				</div>
			</div>
			<!-- end of row -->
			<br/>
			<div class="row">
				<div class="col-md-12">
					<%=	dietShare.getContent() %>	
								
					<!-- 그래프 시작 -->
					<div class="col-md-12">
						<div class="progress-group">
							<span class="progress-text" style="color:#4374D9"><b>열량</b></span>
							<span class="progress-number" style="margin-left:980px"><b>160</b>/200</span>
							<div class="progress sm">
		  						<div class="progress-bar" style="width:60%"></div>
							</div>
						</div>
						<div class="progress-group">
							<span class="progress-text" style="color:#3c8dbc"><b>탄수화물</b></span>
							<span class="progress-number" style="margin-left:953px"><b>160</b>/200</span>
							<div class="progress sm">
		  						<div class="progress-bar bg-info" style="width:20%"></div>
							</div>
						</div>
						<div class="progress-group">
							<span class="progress-text" style="color:#CC3D3D"><b>단백질</b></span>
							<span class="progress-number" style="margin-left:967px"><b>160</b>/200</span>
							<div class="progress sm">
		  						<div class="progress-bar bg-danger" style="width:20%"></div>
							</div>
						</div>
						<div class="progress-group">
							<span class="progress-text" style="color:#f39c12"><b>지방</b></span>
							<span class="progress-number" style="margin-left:980px"><b>160</b>/200</span>
							<div class="progress sm">
		  						<div class="progress-bar bg-warning" style="width:20%"></div>
							</div>
						</div>
					</div>
					<!-- 그래프 끝 -->
				</div>	
				<div class="col-md-12 mt-5 mb-4 text-center">
					<button class="btn btn-default" id="bt_recommend"><i class="fa-solid fa-thumbs-up"></i>{{recommend}}</button>
				</div>	
			</div>
			<hr>
			<!-- end of row -->
			<button class="btn btn-primary " id="bt_list">목록</button>
			<button type="button" class="btn btn-default pull-right" id="bt_del">삭제</button>
			<button type="button" class="btn btn-default pull-right" id="bt_edit">수정</button>
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
	//글 삭제 
	function del(){
		if(!confirm("삭제하시겠습니까?")){
			return;
		}	
		
		$.ajax({
			url:"/rest/diet/share_del/"+$("input[name='diet_share_idx']").val(),
			type:"delete",
			success:function(result, status, xhr){
				alert("글 삭제 완료");
				location.href="/diet/share_list/1";
			}
		});		
	}
	
	//디테일페이지 불러오기(추천수)
	function getDetail(){
		$.ajax({
			url:"/rest/diet/share_detail/"+<%= dietShare.getDiet_share_idx() %>,
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
		json["diet_share_idx"] = "<%= dietShare.getDiet_share_idx()%>";
		
		$.ajax({
			url:"/rest/diet/share/recommend",
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
	
	function init(){
		app1=new Vue({
			el:"#app1",
			components:{
				
			},
			data:{
				recommend:[]
			}
		});
	}

	

	$(function(){
		init();
		getDetail();
		
		//목록버튼 
		$("#bt_list").click(function(){
			location.href="/diet/share_list/1"; 
		});
		
		//수정페이지 버튼 
		$("#bt_edit").click(function(){
			location.href="/diet/share_edit/"+<%= dietShare.getDiet_share_idx()%>
		});
		
		//삭제버튼 
		$("#bt_del").click(function(){
			del();
		});
		
		//추천버튼
		$("#bt_recommend").click(function(){
			recommend();
			
		});
	});
</script>
</html>
