<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../inc/header_link.jsp"%>
</head>

<body class="animsition">
	<%@include file="../inc/topbar.jsp" %>
	<div class="page-header">
	<%@include file="../inc/header_navi.jsp" %>
		<div class="container">
	        <div class="row">
	            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
	                <div class="page-caption pinside40">
	                    <h1 class="page-title">1:1문의 / 신고내역</h1>
	                    <p>사이트 이용에 관한 문의 또는 불량게시물을 신고해주세요</p>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
	
	<!-- content start -->
	<div class="content" id="askApp">
		<div class="container mb-5">
			<div class="row">
				<div class="col-2 mt100">
					<%@include file="./inc/side-navi.jsp"%>
				</div>
				<div class="col-10">
					<div class="space-medium">
						<!-- 문의게시판 시작 -->
						<list v-if="showList" :data="ask_data"></list>
						<!-- /문의게시판 끝 -->

						<div class="row mt100"></div>

						<!-- 신고게시판 시작 -->
						<list v-if="showList" :data="report_data"></list>
						<!-- /신고게시판 끝-->
						
						<!-- 상세보기 -->
						<detail v-if="showDetail" :data="detail_data"></detail>
						<!-- /상세보기-->

						<!-- 글쓰기 폼-->
						<writeform v-if="showForm" :data="formdata"></writeform>
						<!-- /글쓰기 폼-->
					</div>
					<!-- end of space-medium -->
				</div>
			</div>
		</div>
	</div>
	
	<!-- /content end -->
	
	<!-- black footer_space -->
	<%@include file="../inc/footer_space.jsp"%>
	
	<!-- tiny footer -->
	<%@include file="../inc/footer_tiny.jsp"%>
	
	<%@include file="../inc/footer_link.jsp"%>
	<script src="/resources/user/js/vue/cs.js"></script>
	<script>
		let askApp;
		
		function init() {
			$("ul#sidenav li").removeClass();
			$($("ul#sidenav li")[ASK]).addClass('active');

			askApp = new Vue({
				el: "#askApp",
				data(){
					return{
						showList: true,
						showForm: false,
						showDetail: false,
						ask_data : {type:"ask", title: "1:1 문의 내역", list:[], form_title:"1:1 문의하기"},
						report_data : {type:"report", title: "신고 내역", list:[], form_title:"신고하기"},
						detail_data: {type:"", dto:{}},
						formdata:{}
					}
				},
				components: {
					list: List,
					writeform: Writeform,
					detail: Detail
				},
			})
		}

		$(function() {
			init();
		})
	</script>
</body>

</html>
