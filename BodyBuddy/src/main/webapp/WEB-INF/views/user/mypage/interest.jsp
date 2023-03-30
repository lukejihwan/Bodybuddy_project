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
<div class="content" id="App1">
	<div class="container mb-5">
		<div class="row">
			<div class="col-2 mt100">
				<%@include file="./inc/side-navi.jsp"%>
			</div>
			<div class="col-10">
				<div class="space-medium">
					<!-- 찜목록 시작 -->
					<list></list>
					<!-- 찜목록 -->
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
<script src="/resources/user/js/vue/list_interest.js"></script>
<script>
	let App1;

	function init() {
		$("ul#sidenav li").removeClass();
		$($("ul#sidenav li")[INTEREST]).addClass('active');

		App1 = new Vue({
			el: "#App1",
			components: {
				list: List,
			},
		})
	}

	$(function() {
		init();
	})
</script>
</body>

</html>
