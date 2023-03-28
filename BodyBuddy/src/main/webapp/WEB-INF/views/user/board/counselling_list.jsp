<%@page import="com.edu.bodybuddy.util.PageManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.edu.bodybuddy.domain.board.CounsellingBoard"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
List boardList = (List) request.getAttribute("counsellingBoardList");
PageManager pageManager = (PageManager) request.getAttribute("pageManager");
String listURI = "/board/counselling_list/"; // href 이동 주소 이것만 변경하면 됨. 뒤에 / 붙일 것 ex. /board/counselling_list/
String detailURI = "/board/counselling_detail_view/";
String registformURI="/board/counselling_registform";
String listSearchURI = listURI + "search/";

if(request.getRequestURI().indexOf(listURI+"search")!=-1){
	listURI = listURI + "search/";
}

if (boardList == null)
	out.print("<script>location.href='" + listURI + "1'</script>");

if (boardList == null) {
	boardList = new ArrayList();
	pageManager = new PageManager();
	pageManager.init(boardList.size(), 0);
} ;
%>
<!DOCTYPE html>
<!-- content 부분만 비워둔 기본 템플릿 -->
<!-- hero섹션이 포함되어있음 -->
<html lang="en">
<head>
<%@include file="../inc/header_link.jsp"%>
<%@include file="../inc/list_css.jsp"%>
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
						<h1 class="hero-title">고민상담게시판</h1>
						<p class="small-caps mb30 text-white"></p>
						<p class="hero-text">익명으로 고민을 상담할 수 있는 게시판입니다</p>
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
				<div class="col">
                    <h1><a href="<%= listURI+1 %>">고민상담게시판</a></h1>
                    <hr>
				</div>
			</div>
			<!-- end of row -->
			<div class="row">
				<div class="col text-center">
					<div class="input-group search">
						<input type="text" class="form-control" id="t_search" placeholder="제목을 검색하세요..">
						<span class="input-group-btn">
							<button class="btn btn-default" type="button" id="bt_search"><i class="fa fa-search"></i></button>
						</span> 
                    </div>
				</div>
			</div>
			<!-- end of row -->	
			</br>
			<div class="row">
				<div class="col table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>No</th>
								<th>제목</th>
								<th></th> <!-- 썸네일 나올 곳 -->
								<th>작성자</th>
								<th>등록일</th>
								<th>조회수</th>
							</tr>
						</thead>
						<tbody>
							<%
							for (int i = 0; i < boardList.size(); i++) {
							%>
							<%
							CounsellingBoard board = (CounsellingBoard) boardList.get(i);
							%>
							<tr onclick="getDetail(<%=board.getCounselling_board_idx()%>)">
								<td><%=board.getCounselling_board_idx()%></td>
								<td><%=board.getTitle()%><span class="comment-count"><%= board.getCommentList().size()>0?"&nbsp&nbsp["+board.getCommentList().size()+"]":"" %></span></td>
								<td><img src="<%=board.getThumbnail()%>" style="width:50px;height:50px;" onerror="this.style.visibility='hidden';"/></td>
								<td><%=board.getWriter()%></td>
								<td><%=board.getRegdate().substring(0, 10)%></td>
								<td><%=board.getHit()%></td>
							</tr>
							<%
							}
							%>
						</tbody>
					</table>
				</div>
				<!-- end of col -->
			</div>
			<!-- end of row -->
			<div class="row">
				<div class="col text-right">
					<button type="button" class="btn btn-default" id="bt_regist">글쓰기</button>
				</div>
			</div>
			<!-- end of row -->
			<div class="row">
				<div class="col text-center">
					<div class="st-pagination">
						<!--st-pagination-->
						<ul class="pagination">
							<% if(pageManager.getFirstPage()!=1){ %>
							<li><a href="<%= listURI %><%= pageManager.getFirstPage()-1  %>" aria-label="previous"><span aria-hidden="false">이전</span></a></li>
							<% } %>
							
							<% for(int i =pageManager.getFirstPage();i<=pageManager.getLastPage();i++){ %>
							<% if(i>pageManager.getTotalPage()) break; %>
							<li <% if(pageManager.getCurrentPage()==i) out.print("class=\"active\""); %>> <a href="<%= listURI %><%= i %>"><%= i %></a></li>
							<% } %>
							
							<% if(pageManager.getLastPage()<pageManager.getTotalPage()){ %>
							<li><a href="<%= listURI %><%= pageManager.getLastPage()+1 %>" aria-label="Next"><span aria-hidden="true">다음</span></a></li>
							<% } %>
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
<script>
	$(()=>{	
		$("#bt_regist").click(()=>{
			regist();
		});
		$("#bt_search").click(()=>{
			search();
		});
	});
	
	function regist() {
		<sec:authorize access="isAnonymous()">
			Swal.fire({
				title:"로그인해야 사용할 수 있는 기능입니다",
				icon:"warning",
				confirmButtonText:"확인",
				confirmButtonColor: '#c5f016'
			});
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			location.href = "<%= registformURI %>";
		</sec:authorize>
	}
	function search() {
		//console.log($("#t_search").val());
		
		if($("#t_search").val()==""){
			Swal.fire({
				title:"검색어를 입력해주세요",
				icon:"warning",
				confirmButtonText:"확인",
				confirmButtonColor: '#c5f016'
			});
			return;
		}
		
		location.href="<%= listSearchURI %>"+$("#t_search").val()+"/"+1;
	}
	
	function getDetail(idx) {
		location.href = "<%=detailURI%>" + idx;
	}
</script>
</html>
