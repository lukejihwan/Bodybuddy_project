

/*변수 선언*/

let curNickname = $("#curNickname").val();
let curPhone = $("#curPhone").val();
let nickname = $("input[name='nickname']")[0];
let pw1 = $('#pswd1')[0];
let pwMsg = $('#alertTxt')[0];

let pwImg1 = $('#pswd1_img1')[0];
let pw2 = $('#pswd2')[0];
let pwImg2 = $('#pswd2_img1')[0];
let pwMsgArea = $('.int_pass')[0];
let error = $('.error_next_box');

const NICKNAME = 0;
const PHONE = 1;
const PASS = 2;
const PASSC = 3;

const NAMES = ["nickname", "phone", "pass", "passc"];

let isNicknameChecked = true;
let isPhoneValid = false;
let isPhoneChecked = true;
let isAllChecked = true;


/*이벤트 핸들러 연결*/

pw1.addEventListener("focusout", checkPw);
pw2.addEventListener("focusout", comparePw);
phone.addEventListener("focusout", checkPhoneNum);
nickname.addEventListener("focusout", checkNickname);


/*콜백 함수*/

function checkNickname() {
    let namePattern = /^[a-zA-Z가-힣0-9]{2,8}$/;
    if(nickname.value === curNickname){
        isNicknameChecked = true;
        error[PHONE].style.display = "none";
        return;
    } else if(nickname.value === "") {
        error[NICKNAME].innerHTML = "필수 정보입니다.";
        error[NICKNAME].style.display = "block";
        error[NICKNAME].style.color = "red";
        isNicknameChecked = false;
    } else if(!namePattern.test(nickname.value) || nickname.value.indexOf(" ") > -1) {
        error[NICKNAME].innerHTML = "2~10자 한글과 영문 대 소문자를 사용하세요. (특수기호, 공백 사용 불가)";
        error[NICKNAME].style.display = "block";
        error[NICKNAME].style.color = "red";
        isNicknameChecked = false;
    } else {
        let data = {nickname: $("#nickname").val()};
        $.ajax({
            type: "post",
            url: "/auth/member/check/nickname",
            data: data,
            success: function(result) {
                error[NICKNAME].innerHTML = result.msg;
                error[NICKNAME].style.display = "block";
                error[NICKNAME].style.color = "#03c75a";
                isNicknameChecked = true;
            },
            error: function (e) {
                error[NICKNAME].innerHTML = e.responseJSON.msg;
                error[NICKNAME].style.display = "block";
                error[NICKNAME].style.color = "red";
                isNicknameChecked = false;
            }
        })
    }
}

function checkPw() {
    let pwPattern = /[a-zA-Z0-9~!@#$%^&*()_+|<>?:{}]{8,16}/;
    if(pw1.value === "") {
        error[PASS].innerHTML = "필수 정보입니다.";
        error[PASS].style.display = "block";
    } else if(!pwPattern.test(pw1.value)) {
        error[PASS].innerHTML = "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.";
        pwMsg.innerHTML = "사용불가";
        pwMsgArea.style.paddingRight = "93px";
        error[PASS].style.display = "block";
        
        pwMsg.style.display = "block";
        pwImg1.src = "/resources/user/images/join/m_icon_not_use.png";
    } else {
        error[PASS].style.display = "none";
        pwMsg.innerHTML = "안전";
        pwMsg.style.display = "block";
        pwMsg.style.color = "#03c75a";
        pwImg1.src = "/resources/user/images/join/m_icon_safe.png";
    }
}

function comparePw() {
    if(pw2.value === pw1.value && pw2.value != "") {
        pwImg2.src = "/resources/user/images/join/m_icon_check_enable.png";
        error[PASSC].style.display = "none";
    } else if(pw2.value !== pw1.value) {
        pwImg2.src = "/resources/user/images/join/m_icon_check_disable.png";
        error[PASSC].innerHTML = "비밀번호가 일치하지 않습니다.";
        error[PASSC].style.display = "block";
    } 

    if(pw2.value === "") {
        error[PASSC].innerHTML = "필수 정보입니다.";
        error[PASSC].style.display = "block";
    }
}

function checkPhoneNum() {
    let isPhoneNum = /([01]{2})([01679]{1})([0-9]{3,4})([0-9]{4})/;
    if(phone.value === curPhone){
        error[PHONE].style.display = "none";
        isPhoneChecked = true;
        return;
    }
    if(phone.value === "") {
        error[PHONE].innerHTML = "필수 정보입니다.";
        error[PHONE].style.display = "block";
        isPhoneValid = false;
        isPhoneChecked = false;
        $("#bt_checkPhone").hide();
    } else if(!isPhoneNum.test(phone.value)) {
        error[PHONE].innerHTML = "형식에 맞지 않는 번호입니다.";
        error[PHONE].style.display = "block";
        isPhoneValid = false;
        isPhoneChecked = false;
        $("#bt_checkPhone").hide();
    } else {
        error[PHONE].style.display = "none";
        isPhoneValid = true;
        isPhoneChecked = false;
        $("#bt_checkPhone").show();
    }
    
}

function send(value){
	console.log(NAMES[value] +"인증 발송절차 실행");
    error[value].innerHTML = "처리중...";
    error[value].style.display = "block";
    error[value].style.color = "blue";
	$.ajax({
		type: "get",
		url: "/auth/member/"+NAMES[value]+"?"+NAMES[value]+"="+$("input[name='"+NAMES[value]+"']").val(),
		success: function(result) {
			error[value].innerHTML = result.msg;
            error[value].style.display = "block";
            error[value].style.color = "#03c75a";
            $($(".int_auth")[0]).css("display", "block");
		},
		error: function(e) {
			error[value].innerHTML = e.responseJSON.msg;
            error[value].style.display = "block";
            error[value].style.color = "red";
		}
	})	
}

function verify(value) {
	console.log(NAMES[value] + "인증 확인 절차 실행");
	let formData = $("#form1").serialize();
    console.log(formData);
	$.ajax({
		type: "post",
		url: "/auth/member/"+NAMES[value],
		data: formData,
		success: function(result) {
			error[value].innerHTML = result.msg;
            error[value].style.display = "block";
            error[value].style.color = "#03c75a";
            $("input[name='"+NAMES[value]+"']").attr("readonly", true);
            $($(".checkBt")[1]).hide();
            $($(".int_auth")[1]).hide();
            isPhoneChecked = true;
		},
		error: function(e) {
			console.log(e);
			alert(e.responseJSON.msg);
		}
	})	
}

function verifyAll(){
    if(isNicknameChecked && isPhoneChecked){
        isAllChecked = true;
    } else {
        isAllChecked = false;
    }
    return isAllChecked;
}

function edit() {
	let formData = $("#form1").serialize();
	$.ajax({
		type: "post",
		url: "/auth/member",
		data: formData,
		success: function(result) {
			alert(result.msg);
			location.reload();
		},
		error: function(e) {
            console.log(e.responseText);
			alert(e.responseJSON.msg);
		}
	})
}

function searchAddress() {
    new daum.Postcode({
        oncomplete: function (data) {
            $("#address").val(data.address);
            $("#address").attr("disabled", false);
        },
    }).open();
}



$(function(){

	//수정 버튼 연결
	$("#bt_edit").click(function(){
		if(verifyAll()) edit();
        else alert("인증받지 않은 항목이 있습니다");
	})

    $("#bt_checkPhone").click(function(){
		if(isPhoneValid) send(PHONE);
        else{
            error[PHONE].innerHTML = "휴대폰 번호 형식을 확인하세요";
            error[PHONE].style.display = "block";
            error[PHONE].style.color = "red";
        }
	})

    $("#bt_verifyP").click(function(){
		verify(PHONE);
	})

    $("#bt_editPass").click(function(){
        $(".f_pass").toggle(500, ()=>{
            if($(".f_pass").css("display")=="none"){
                $("input[type='password']").attr("disabled", true);
            } else {
                $("input[type='password']").attr("disabled", false);
            }
        });
	})
})