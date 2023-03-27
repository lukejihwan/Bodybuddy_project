<%@page import="com.edu.bodybuddy.domain.exr.ExrToday"%>
<%@page import="com.edu.bodybuddy.domain.exr.ExrCategory"%>
<%@page import="com.edu.bodybuddy.util.PageManager"%>
<%@page import="com.edu.bodybuddy.domain.exr.ExrRoutine"%>
<%@page import="com.edu.bodybuddy.domain.exr.ExrNotice"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	List<ExrToday> exrTodayList=(List<ExrToday>)request.getAttribute("exrTodayList");
	System.out.println("뷰에서 확인 "+exrTodayList);
%>
<!DOCTYPE html>
<html lang="en">
<head profile="http://www.w3.org/2005/10/profile">

<%@include file="../inc/header_link.jsp" %>
<style type="text/css">
	.hero-section{
		background-image: url("/resources/user/images/exr/todaybg.jpg");
	}
	.comment-count{
		margin-left:10px;
		color:red;
		font-weight: 10px;
	}
</style>
</head>

<body class="animsition">
	<!-- top-bar start-->
	<%@include file="../inc/topbar.jsp" %>
    <!-- /top-bar end-->

    <!-- hero section start -->
    <div class="hero-section">
		<!-- navigation-->
	   	<%@include file="../inc/header_navi.jsp" %>
	    <!-- /navigation end -->
        <div class="container">
          <div class="row">
              <div class="col-lg-6 col-md-6 col-sm-12  col-xs-12">
                  <div class="hero-caption pinside50">
                      <h1 class="hero-title">오운완 게시판</h1>
                      <p class="small-caps mb30 text-white">BodyBuddy Excercise Routine Share Here.</p>
                      <p class="hero-text">하루의 러닝 기록을 기록하는 공간입니다</p>
                      <a href="/exr/today/registform" class="btn btn-default">지금 기록하기</a>
                  </div>
              </div>
          </div>
      	</div>
    </div>
     <!-- ./hero section end -->
     
    <!-- content start -->
    <div class="space-medium">
        <div class="container" id="app1">

			<div class="card-header">
				<div class="row">

					<div class="col-7">
						<h3 class="card-title">오운완 게시판</h3>

						<div class="card-tools">
							<form action="form1">


								<input type="text" name="keyword" placeholder="제목 검색"
									style="width: 80%">
								<button class="btn btn-default" type="button" id="bt_search">
									<i class="fa fa-search"></i>
								</button>
							</form>
						</div>

					</div>
				</div>

			</div>
			<!-- /.card-header -->

			<div class="card-body table-responsive p-0">
				<table class="table table-hover text-nowrap">
					<thead>
						<tr>
							<th>No</th>
							<th>제목</th>
							<th>작성자</th>
							<th>등록일</th>
							<th>추천</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
						<%for(int i=0; i<exrTodayList.size(); i++){ %>
						<%ExrToday exrToday=exrTodayList.get(i); %>
						<tr>
							<td><%=exrToday.getExr_today_idx() %></td>
							<td><a href="/exr/today/detail?exr_today_idx=<%=exrToday.getExr_today_idx() %>"><%=exrToday.getTitle() %>
								<span class="comment-count">[&nbsp <%=exrToday.getCommentList().size() %> &nbsp]</span>
							</a></td>
							<td><%=exrToday.getWriter() %></td>
							<td><%=exrToday.getRegdate() %></td>
							<td><%=exrToday.getRecommend() %></td>
							<td><%=exrToday.getHit() %></td>
						</tr>
						<%} %>
					</tbody>
				</table>
			</div>
			<!-- /.card-body -->


			<!-- /.card -->
			<!-- <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center"> -->


			<div class="row">
				<div class="col text-center">
					<div class="st-pagination">
						<ul class="pagination">



						</ul>
					</div>
				</div>
			</div>


			<div class="text-lg-end">
				<a href="/exr/today/registform" class="btn btn-default">글쓰기</a>
			</div>

		</div>
    </div>


    <!-- /content end -->
    
	<!-- black footer_space -->
    <%@include file="../inc/footer_space.jsp" %>

    <!-- tiny footer -->
    <%@include file="../inc/footer_tiny.jsp" %>
    
    <%@include file="../inc/footer_link.jsp" %>
<script type="text/javascript">

	
	$(function(){
	});

</script>
</body>
</html>
