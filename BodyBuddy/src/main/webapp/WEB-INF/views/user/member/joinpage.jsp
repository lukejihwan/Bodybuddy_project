<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@include file="../inc/header_link.jsp" %>
<link rel="stylesheet" href="/resources/user/css/join.css">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>

<body class="animsition">
	<%@include file="../inc/topbar.jsp" %>
    <div class="page-header">
    <%@include file="../inc/header_navi.jsp" %>
 		 <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                    <div class="page-caption pinside40">
                        <h1 class="page-title">회원가입</h1>
                        <p>회원이 되어 다양한 혜택을 누려보세요!</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- content start -->
 	<!-- wrapper -->
    <div id="wrapper" style="padding-top: 100px">
        <!-- content-->
        <div id="content">
			<form id="form1">
				<!-- EMAIL -->
				<div>
					<h3 class="join_title"><label for="email">이메일 주소</label></h3>
					<span class="box int_email">
						<input type="text" id="email" name="email" class="int" maxlength="100" placeholder="이메일 주소 입력">
						<button type="button" class="btn btn-default btn-sm checkBt" id="bt_check">인증번호받기</button>
					</span>
					<span class="error_next_box">이메일 주소를 다시 확인해주세요.</span>
					<span class="box int_auth">
						<input type="text" name="code" class="int" maxlength="6" placeholder="인증번호 입력">
						<button type="button" class="btn btn-default btn-sm verifyBt" id="bt_verify">인증</button>
					</span>    
				</div>
				<!-- PW1 -->
				<div>
					<h3 class="join_title"><label for="pswd1">비밀번호</label></h3>
					<span class="box int_pass">
						<input type="password" id="pswd1" name="password.pass" class="int" maxlength="20">
						<span id="alertTxt">사용불가</span>
						<img src="/resources/user/images/join/m_icon_pass.png" id="pswd1_img1" class="pswdImg">
					</span>
					<span class="error_next_box"></span>
				</div>
				<!-- PW2 -->
				<div>
					<h3 class="join_title"><label for="pswd2">비밀번호 재확인</label></h3>
					<span class="box int_pass_check">
						<input type="password" id="pswd2" class="int" maxlength="20">
						<img src="/resources/user/images/join/m_icon_check_disable.png" id="pswd2_img1" class="pswdImg">
					</span>
					<span class="error_next_box"></span>
				</div>
				<!-- NICKNAME -->
				<div>
					<h3 class="join_title"><label for="name">닉네임</label></h3>
					<span class="box int_name">
						<input type="text" id="nickname" name="nickname" class="int" maxlength="20">
					</span>
					<span class="error_next_box"></span>
				</div>
				<!-- phone -->
				<div>
					<h3 class="join_title"><label for="phone">휴대전화</label></h3>
					<span class="box int_phone">
						<input type="tel" id="phone" name="phone" class="int" maxlength="16" placeholder="전화번호 입력">
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
					<h3 class="join_title"><label for="address">주소(선택사항)</label></h3>
					<span class="box readonly">
						<input type="text" class="int readonly" id="address" name="address.member_address" value="주소를 검색하세요" disabled>
						<button type="button" class="btn btn-default btn-sm adrBt" id="bt_address" onclick="searchAddress()">주소검색</button>
					</span>
				</div>

				<!-- 가입버튼 -->
				<div class="form-group pt-3">
					<button type="button" class="btn btn-default" id="bt_regist">
						<span>가입하기</span>
					</button>
				</div>
			</form>
        </div> 
        <!-- content-->
    </div> 
    <!-- wrapper -->

    <!-- /content end -->
    
   	<!-- black footer_space -->
    <%@include file="../inc/footer_space.jsp" %>
    
    <!-- tiny footer -->
    <%@include file="../inc/footer_tiny.jsp" %>
    
    <!-- back to top icon -->
    <a href="#0" class="cd-top" title="Go to top">Top</a>
    
    <%@include file="../inc/footer_link.jsp" %>
    
</body>
<script src="/resources/user/js/join.js"></script>
</html>