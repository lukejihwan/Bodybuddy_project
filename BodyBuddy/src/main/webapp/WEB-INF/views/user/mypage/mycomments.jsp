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
                    <h1 class="page-title">내가 쓴 댓글</h1>
                    <p>내가 쓴 댓글을 모아볼 수 있는 곳입니다</p>
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
                    <img src="/resources/user/images/under-construction.jpg" alt="">
                    <!-- /문의게시판 끝 -->
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
<script src="/resources/user/js/vue/list_mypost.js"></script>
<script>
    let askApp;

    function init() {
        $("ul#sidenav li").removeClass();
        $($("ul#sidenav li")[MYCOMMENTS]).addClass('active');

        askApp = new Vue({
            el: "#askApp",
            data(){
                return{
                    postList: []
                }
            },
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
