<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="com.edu.bodybuddy.domain.security.MemberDetail" %>
<%@ page import="com.edu.bodybuddy.domain.member.Member" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    MemberDetail memberDetail = (MemberDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Member member = memberDetail.getMember();
    String provider = member.getProvider();
    String address = null;
    if(member.getAddress()==null){
        address = "등록된 주소가 없습니다";
    } else {
        address = member.getAddress().getMember_address();
    }
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <%@include file="../inc/header_link.jsp" %>
    <link rel="stylesheet" href="/resources/user/css/join.css">
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <style>
        .f_pass{display: none;}
        .readonly{background: #e3e3e3}
    </style>
</head>

<body class="animsition">
    <%@include file="../inc/topbar.jsp" %>
    <div class="page-header">
        <%@include file="../inc/header_navi.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                    <div class="page-caption pinside40">
                        <h1 class="page-title">회원정보</h1>
                        <p>회원의 정보를 조회하고 수정할 수 있는 페이지입니다</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- content start -->
    <div class="content">
        <div class="container mb-5">
            <div class="row">
                <div class="col-2">
                    <%@include file="./inc/side-navi.jsp" %>
                </div>
                <div class="col-10">
                    <!-- wrapper -->
                    <div id="wrapper">
                        <!-- content-->
                            <form id="form1">
                                <input type="hidden" name="_method" value="PUT"/>
                                <!-- NICKNAME -->
                                <div>
                                    <h3 class="join_title"><label for="name">닉네임</label></h3>
                                    <span class="box">
                                            <input type="text" id="nickname" name="nickname" class="int" maxlength="20" value="<sec:authentication property="principal.member.nickname"/>">
                                        </span>
                                    <span class="error_next_box"></span>
                                </div>

                                <!-- EMAIL -->
                                <div>
                                    <h3 class="join_title"><label for="email">이메일 주소 - 이메일주소는 수정이 불가능합니다</label></h3>
                                    <span class="box readonly">
                                            <input type="text" class="int readonly" maxlength="100" value="<sec:authentication property="principal.member.email"/>" disabled>
                                            <button type="button" class="btn btn-default btn-sm checkBt" id="bt_check">인증번호받기</button>
                                    </span>
                                </div>

                                <!-- phone -->
                                <div>
                                    <h3 class="join_title"><label for="phone">휴대전화</label></h3>
                                    <span class="box">
                                            <input type="tel" id="phone" name="phone" class="int" maxlength="16" value="<sec:authentication property="principal.member.phone"/>">
                                            <button type="button" class="btn btn-default btn-sm checkBt" id="bt_checkPhone">인증번호받기</button>
                                        </span>
                                    <span class="error_next_box"></span>
                                    <span class="box int_auth">
                                            <input type="text" name="codeP" class="int" maxlength="6" placeholder="인증번호 입력">
                                            <button type="button" class="btn btn-default btn-sm verifyBt" id="bt_verifyP">인증</button>
                                        </span>
                                </div>

                                <!-- 주소 -->
                                <div>
                                    <h3 class="join_title"><label for="address">주소</label></h3>
                                    <span class="box readonly">
                                            <input type="text" class="int readonly" id="address" name="address.member_address" value="<%=address%>" disabled>
                                            <button type="button" class="btn btn-default btn-sm adrBt" id="bt_address" onclick="searchAddress()">주소검색</button>
                                    </span>
                                </div>

                                <!-- 비밀번호 수정 버튼 -->
                                <%if(provider.equals("home")){%>
                                <div class="form-group pt-3">
                                    <button type="button" class="btn btn-warning" id="bt_editPass">
                                        <span>비밀번호 수정</span>
                                    </button>
                                </div>
                                <%}%>

                                <!-- PW1 -->
                                <div class="f_pass">
                                    <h3 class="join_title"><label for="pswd1">비밀번호</label></h3>
                                    <span class="box int_pass">
                                            <input type="password" id="pswd1" name="password.pass" class="int" maxlength="20" placeholder="비밀번호 변경을 원하는 경우에만 입력하세요" disabled>
                                            <span id="alertTxt">사용불가</span>
                                            <img src="/resources/user/images/join/m_icon_pass.png" id="pswd1_img1" class="pswdImg">
                                        </span>
                                    <span class="error_next_box"></span>
                                </div>

                                <!-- PW2 -->
                                <div class="f_pass">
                                    <h3 class="join_title"><label for="pswd2">비밀번호 재확인</label></h3>
                                    <span class="box int_pass_check">
                                            <input type="password" id="pswd2" class="int" maxlength="20" placeholder="위와 동일한 비밀번호를 입력하세요" disabled>
                                            <img src="/resources/user/images/join/m_icon_check_disable.png" id="pswd2_img1" class="pswdImg">
                                        </span>
                                    <span class="error_next_box"></span>
                                </div>

                                <!-- 수정버튼 -->
                                <div class="form-group pt-3">
                                    <button type="button" class="btn btn-default" id="bt_edit">
                                        <span>수정하기</span>
                                    </button>
                                </div>
                            </form>
                            <input type="hidden" value="<sec:authentication property="principal.member.nickname"/>" id="curNickname">
                            <input type="hidden" value="<sec:authentication property="principal.member.phone"/>" id="curPhone">
                    </div>
                    <!-- /wrapper -->
                </div>
                <!-- end of space-medium -->
            </div>
        </div>
    </div>

    <!-- /content end -->

    <!-- black footer_space -->
    <%@include file="../inc/footer_space.jsp" %>

    <!-- tiny footer -->
    <%@include file="../inc/footer_tiny.jsp" %>

    <%@include file="../inc/footer_link.jsp" %>
    <script src="/resources/user/js/info.js"></script>
    <script>
        function init() {
            $("ul#sidenav li").removeClass();
            $($("ul#sidenav li")[INFO]).addClass('active');
        }

        $(function(){
            init();
        })
    </script>
</body>

</html>