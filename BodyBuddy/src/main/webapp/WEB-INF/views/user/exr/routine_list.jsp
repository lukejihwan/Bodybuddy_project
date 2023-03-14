<%@page import="com.edu.bodybuddy.domain.exr.ExrNotice"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%

%>
<!DOCTYPE html>
<html lang="en">
<head profile="http://www.w3.org/2005/10/profile">

<%@include file="../inc/header_link.jsp" %>
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
                      <h1 class="hero-title">루틴 공유 게시판</h1>
                      <p class="small-caps mb30 text-white">BodyBuddy Excercise Routine Share Here.</p>
                      <p class="hero-text">자신만의 운동 루틴을 기록해보세요!</p>
                      <a href="/exr/routine/regist" class="btn btn-default">지금 기록하기</a>
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

				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">루틴 공유 게시판</h3>

							<div class="card-tools">
								<div class="input-group input-group-sm" style="width: 250px;">
									<input type="text" name="table_search"
										class="form-control float-right" style="text-color:white" placeholder="Search">

									<div class="input-group-append">
										<button type="submit" class="btn btn-default">
											<i class="fas fa-search"></i>
										</button>
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
										<th>카테고리</th>
										<th>제목</th>
										<th>작성자</th>
										<th>등록일</th>
										<th>조회수</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>183</td>
										<td>John Doe</td>
										<td>11-7-2014</td>
										<td><span class="tag tag-success">Approved</span></td>
										<td>Bacon ipsum dolorr.</td>
										<td>0</td>
									</tr>

								</tbody>
							</table>
						</div>
						<!-- /.card-body -->
					</div>
					<!-- /.card -->
					<!-- <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center"> -->
						<div>
						
						<div class="st-pagination">
							<!--st-pagination-->
							<ul class="pagination">
								<li><a href="#" aria-label="previous"><span aria-hidden="true">previous</span></a></li>
								<li class="active"><a href="#">1</a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#" aria-label="Next"><span
										aria-hidden="true">next</span></a></li>
							</ul>
						</div>
						<!--/.st-pagination-->
					
						</div>
					<div class="text-lg-end">
						<a href="/exr/routine/registform" class="btn btn-default">글쓰기</a>
					</div>


				</div>
				
				

			</div>
        </div>
    </div>



    <!-- /content end -->
    
	<!-- black footer_space -->
    <%@include file="../inc/footer_space.jsp" %>
    

    
    <!-- tiny footer -->
    <%@include file="../inc/footer_tiny.jsp" %>
    
    <%@include file="../inc/footer_link.jsp" %>
    
</body>

</html>
