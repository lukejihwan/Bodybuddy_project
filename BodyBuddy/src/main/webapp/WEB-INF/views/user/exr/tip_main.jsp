<%@page import="com.edu.bodybuddy.domain.exr.ExrCategory"%>
<%@page import="com.edu.bodybuddy.util.PageManager"%>
<%@page import="com.edu.bodybuddy.domain.exr.ExrRoutine"%>
<%@page import="com.edu.bodybuddy.domain.exr.ExrNotice"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%

	
//	System.out.println("뷰 페이지에서 카테고리 리스트 확인 : "+exrCategoryList);
	
%>
<!DOCTYPE html>
<html lang="en">
<head profile="http://www.w3.org/2005/10/profile">

<%@include file="../inc/header_link.jsp" %>
	<style type="text/css">
	
	#ytplayer{
		width:720;
		height:405;
	}
	 .section{
	 	margin-top:90px;
	 }
</style>
</head>

<body class="animsition">
	<!-- top-bar start-->
	<%@include file="../inc/topbar.jsp" %>
    <!-- /top-bar end-->


    <!-- hero section start -->
    <div class="hero-section" style="background:#E57373">
		<!-- navigation-->
	   	<%@include file="../inc/header_navi.jsp" %>
	    <!-- /navigation end -->
        <div class="container">
          <div class="row">
              <div class="col-lg-6 col-md-6 col-sm-12  col-xs-12">
                  <div class="hero-caption pinside50">
                      <h1 class="hero-title">운동팁 게시판</h1>
                      <p class="small-caps mb30 text-white">BodyBuddy Excercise Routine Share Here.</p>
                      <p class="hero-text">자신만의 운동 루틴을 기록해보세요!</p>
                      <a href="/exr/routine/regist" class="btn btn-default">지금 기록하기</a>
                  </div>
              </div>
          </div>
      	</div>
    </div>
     <!-- ./hero section end -->
     

	<!-- 컨텐트 시작! -->
	<div class="content m-5" id="app1">
		<div class="row">
		
			<div class="col-lg-7">
				<div class="widget widget-search mb40">
					<img src="/resources/user/images/exr/youtube.jpg" style="width:200px">
					<h2 class="widget-title">원하는 운동 키워드를 검색해보세요!</h2>
					<div class="input-group">
							<input type="text" class="form-control" placeholder="Seacrh Here" name="keyword">
							<span class="input-group-btn" style="color:red">
								<button class="btn btn-default" type="button" id="bt_search" style="background:red">
									<i class="fa fa-search" ></i>
								</button>
							</span>
					</div>
				</div>

					
				<div>
					<center>
						<div id="youtube"></div>
					</center>
				</div>

					
			</div>

		
			<div class="col-lg-5">
				<div class="section">
			
					<a href="/exr/tip/registfrom" class="btn btn-default float-right">Scrap</a>
	
					<div class="card-body table-responsive p-0">
						<table class="table table-hover text-nowrap">
							<thead>
								<tr>
									<th>No</th>
									<th>카테고리</th>
									<th>제목</th>
									<th>등록일</th>
								</tr>
							</thead>
							<tbody>
							
								<template v-for="tip in exrTipList">
									<row :dto="tip" :key="tip.exr_tip_idx"/>
								</template>
								
							</tbody>
						</table>
					</div>
			
				</div><!-- ./section -->
			</div>
				
				
			<!-- ./row -->
			</div>
		</div>


    
	<!-- black footer_space -->
    <%@include file="../inc/footer_space.jsp" %>

    <!-- tiny footer -->
    <%@include file="../inc/footer_tiny.jsp" %>
    
    <%@include file="../inc/footer_link.jsp" %>
<script type="text/javascript">
	// 나의 api키
	let APIKey="AIzaSyABFfH85xw6pNOdcGrmBAMGKOJhVhsQL6Q";
	
	
	//----------------------------------------------------------------------------------//
	function searchVideo(){
		// 검색어 입력된 키워드!
		let keyword = $("input[name='keyword']").val();
		console.log("검색 키워드 :",keyword);
		
		$.ajax({
			url:"https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=10&q="+keyword+"&type=video&key="+APIKey,
			type:"GET",
			data:{
				part: "snippet", 
				key: APIKey, 
				type: "video", 
				q: keyword
			},
			success: function (data) {
			   data.items.forEach(function(element, index) {
                    $('#youtube').append('<iframe width="360" height="215" src="https://www.youtube.com/embed/'+element.id.videoId+'" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>');
                });
			  }
		});
	}
	
	
	function getExrTipList(){
		$.ajax({
			url:"/rest/exr/tip_list",
			type:"GET",
			success:function(result, status, xhr){
				app1.exrTipList=result;
				console.log(app1.exrTipList);
			},
			error:function(xhr, status, err){
				console.log(xhr.responseText);
			}
		});
	}
	
	
	const row={
			template:`
 				<tr @click="getDetail(exrTip)">
				    <td>{{exrTip.exr_tip_idx}}</td>
				    <td>{{exrTip.exrCategory.exr_category_name}}</td>
				    <td>{{exrTip.title}}</td>
				    <td>{{exrTip.regdate.substr(0, 10)}}</td>
				</tr> 
			`,
			props:["dto"],
			data(){
				return{
					exrTip:this.dto,
				}
			},
			methods:{
				getDetail:function(exrTip){
					loginCheck();
					
					//alert(exrTip.exr_tip_idx);
					location.href="/exr/tip_detail/"+exrTip.exr_tip_idx;
				}
			}
	}
	
	
	function loginCheck() {
		<sec:authorize access="isAnonymous()">
			Swal.fire({
				title:"로그인해야 사용할 수 있는 기능입니다",
				icon:"warning",
				confirmButtonText:"확인",
				confirmButtonColor: '#c5f016'
			}).then(()=>{
				location.href="/exr/tip_list";
			});
		</sec:authorize>
	}
	
	
	
	function init(){
		app1=new Vue({
			el:"#app1",
			data:{
				exrTipList:[],
			},
			components:{
				row
			},
		});
	}
	
	
	/*** onLoad ***/
	$(function(){
		init();
		getExrTipList();
		
		
		// 동영상 크롤링
		$("#bt_search").click(function(){
			searchVideo();
		});
		
		
	});
</script>
</body>
</html>
