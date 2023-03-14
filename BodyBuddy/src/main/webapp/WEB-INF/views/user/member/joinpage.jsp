<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@include file="../inc/header_link.jsp" %>
</head>

<body class="animsition">
	<%@include file="../inc/topbar.jsp" %>
    <div class="page-header">
    <%@include file="../inc/header_navi.jsp" %>
 		 <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                    <div class="page-caption pinside40">
                        <h1 class="page-title">회원가입 테스트 페이지입니다</h1>
                        <p>회원이 되어 다양한 혜택을 누려보세요!</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- content start -->
    <div class="container">
       	<div class="card card-primary">
		   	 <div class="card-header">
		        <h3 class="card-title">회원가입</h3>
		     </div>
		    <!-- /.card-header -->
		    <!-- form start -->
		    <form id="registform">
		        <div class="card-body">
		            <div class="form-group">
		                <label for="id">닉네임</label>
		                <input type="text" class="form-control" name="nickname" required>
		            </div>
		            <div class="form-group">
		                <label for="pass">비밀번호</label>
		                <input type="password" class="form-control" name="password.pass" required>
		            </div>
		            <div class="form-group">
		                <label for="passc">비밀번호 확인</label>
		                <input type="password" class="form-control" id="passc" required>
		            </div>
		            <div class="form-group">
		                <label for="email">이메일</label>
		                <input type="email" class="form-control" name="email" required>
		            </div>
		            <div class="form-group">
		                <label for="phone">전화번호</label>
		                <input type="number" class="form-control" name="phone" required>
		            </div>
		            <div class="form-group">
		                <label for="address">주소(선택사항)</label>
		                <input type="text" class="form-control" name="address.member_address">
		            </div>
		        </div>
		        <!-- /.card-body -->
				<input type="hidden" name="origin" value="home">
		        <div class="card-footer">
		            <button type="button" class="btn btn-primary" id="bt_regist">회원가입</button>
		        </div>
		    </form>
		</div>
    </div>

    <!-- /content end -->
    
   	<!-- black footer_space -->
    <%@include file="../inc/footer_space.jsp" %>
    
    <!-- tiny footer -->
    <%@include file="../inc/footer_tiny.jsp" %>
    
    <!-- back to top icon -->
    <a href="#0" class="cd-top" title="Go to top">Top</a>
    
    <%@include file="../inc/footer_link.jsp" %>
    
</body>
<script type="text/javascript">

function regist() {
	let formData = $("#registform").serialize();
	$.ajax({
		type: "POST",
		url: "/auth/member",
		data: formData,
		success: function(result) {
			alert(result.msg);
			location.href="/login";
		},
		error: function(e) {
			alert(e.responseJSON.msg);
		}
	})
}

function validate(){
	let email = $("input[name='email']");
	let phone = $("input[name='phone']");
	let regEmail = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	let regPhone = /^((01[1|6|7|8|9])[1-9]+[0-9]{6,7})|(010[1-9][0-9]{7})$/;
	
	if(!email.val()){
		alert("이메일 주소를 입력해주세요");
		email.focus();
		return false;
	} else if(!regEmail.test(email.val())) {
		alert("이메일 형식이 올바르지 않습니다");
		email.focus();
		return false;
	} else if(!phone.val()){
		alert("휴대폰 번호를 입력해주세요");
		phone.focus();
		return false;
	} else if(!regPhone.test(phone.val())){
		alert("휴대폰 번호 형식이 올바르지 않습니다");
		phone.focus();
		return false;
	} else {
		return true;
	}
}

$(function(){
	//회원가입 버튼 연결
	$("#bt_regist").click(function(){
		//if(validate()) regist();
		regist();
	})
})
</script>
</html>