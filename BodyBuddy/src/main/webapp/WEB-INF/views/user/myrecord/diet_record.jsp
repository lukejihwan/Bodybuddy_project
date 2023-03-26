<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../inc/header_link.jsp" %>
</head>
<style>
.wrapper{
	margin: 15px;
	padding: 10px;
	font-size: 20px;
}
.sec_cal {
    width: 360px;
    margin: 0 auto;
    font-family: "NotoSansR";
}

.sec_cal .cal_nav {
    display: flex;
    justify-content: center;
    align-items: center;
    font-weight: 700;
    font-size: 48px;
    line-height: 78px;
}

.sec_cal .cal_nav .year-month {
    width: 300px;
    text-align: center;
    line-height: 1;
}

.sec_cal .cal_nav .nav {
    display: flex;
    border: 1px solid #333333;
    border-radius: 5px;
}

.sec_cal .cal_nav .go-prev,
.sec_cal .cal_nav .go-next {
    display: block;
    width: 50px;
    height: 78px;
    font-size: 0;
    display: flex;
    justify-content: center;
    align-items: center;
}

.sec_cal .cal_nav .go-prev::before,
.sec_cal .cal_nav .go-next::before {
    content: "";
    display: block;
    width: 20px;
    height: 20px;
    border: 3px solid #000;
    border-width: 3px 3px 0 0;
    transition: border 0.1s;
}

.sec_cal .cal_nav .go-prev:hover::before,
.sec_cal .cal_nav .go-next:hover::before {
    border-color: #ed2a61;
}

.sec_cal .cal_nav .go-prev::before {
    transform: rotate(-135deg);
}

.sec_cal .cal_nav .go-next::before {
    transform: rotate(45deg);
}

.sec_cal .cal_wrap {
    padding-top: 40px;
    position: relative;
    margin: 0 auto;
}

.sec_cal .cal_wrap .days {
    display: flex;
    margin-bottom: 20px;
    padding-bottom: 20px;
    border-bottom: 1px solid #ddd;
}

.sec_cal .cal_wrap::after {
    top: 368px;
}

.sec_cal .cal_wrap .day {
    display:flex;
    align-items: center;
    justify-content: center;
    width: calc(100% / 7);
    text-align: left;
    color: #999;
    font-size: 16px;
    text-align: center;
    border-radius:5px
}

.current.today {
	background: rgb(129, 169, 235);
}

.sec_cal .cal_wrap .dates {
    display: flex;
    flex-flow: wrap;
    height: 290px;
}

.sec_cal .cal_wrap .day:nth-child(7n) {
    color: #3c6ffa;
}

.sec_cal .cal_wrap .day:nth-child(7n-6) {
    color: #ed2a61;
}

.sec_cal .cal_wrap .day.disable {
    color: #ddd;
}
#div_ExrCalendar{
	border-radius: 5px;
	border: 2px solid #ff9933;
}
.current:hover{
	transform:scale(1.2);
	cursor: pointer;
}
</style>
<script type="text/javascript">
let currentYear;
let currentMonth;
let nextDate;
let today;
//vue 컨트롤객체
let app1;
// 현재 위치에 포커스 맞추기
let map;


const rowlist={
	template:`
		<div class="card bg-dark text-white wrapper">
		<!--form id값 동적으로 할당-->
			<form :id="'form'+exr.exr_record_idx">
			<div class="row card-body">
				운동명: <input type="text" class="form-control input-sm" v-model="exr.exr_name" name="exrname">
				<input type="hidden" v-model="exr.exr_record_idx" name="exr_idx">
			</div>
			<div div class="row">
				<div class="col-md-10 card-body">
					<div class="form-group">
						<template v-for="(detail_exr,i) in exr.exrRecordDetailList">
							<div class="row">
								<div class="col-md-2">
									{{i+1}}세트
								</div>
								<div class="col-md-3">
									<input type="number" min="1" max="700" class="form-control input-sm" v-model="detail_exr.kg" name="kgs[]">
								</div>
								<div class="col-md-2">
									kg
								</div>
								<div class="col-md-3">
									<input type="number" min="1" max="700" class="form-control input-sm" v-model="detail_exr.times" name="times[]">
								</div>
								<div class="col-md-2">
									개
								</div>
							</div>
						</template>
					</div>
				</div>
					<!--
					<input type="hidden" value="{{exr.exr_record_idx}}" name="exr_idx">
					-->
					<div class="col-md-2 card-body">
						<div class="form-group">
							<button type="button" class="btn btn-warning" v-on:click="update(exr)">수정</button>
						</div>
						<div class="form-group">
							<button type="button" class="btn btn-danger" v-on:click="del(exr.exr_record_idx)">삭제</button>
						</div>
					</div>
			</div>
			</form>
		</div>
		`,
		props:['exr'],
		data(){
			return{
				exr:this.exr
			};
		},
		methods:{
			update:function(exr){
				if(confirm("수정하시겠습니까?")){
					//form 요소 선택(백틱사용)
					console.log("여기는 왔다");
					let form=document.getElementById('form'+exr.exr_record_idx);
					console.log(form);
					//form 데이터를 FormData 객체로 변환
					let formData = new FormData(form);
					
					//formData안의 내용물을 확인하는 법
					for(let pair of formData.entries()){
						console.log(pair[0]+", "+pair[1]);
					}
					// fetch요청을 보낼 url
					let url="/rest/myrecord/exrRecord"
					console.log("어디까지 왔다.");
					fetch(url,{
						method:"POST",
						body:formData
					})
					.then(response=>{
						if(response.ok){
							//요청 성공적으로 처리시
							console.log("수정 완료");
							alert("수정이 완료되었습니다");
							location.href="/myrecord/exr_record";
						}else{
							//요청 실패시
							console.log("수정 실패");
						}
					})
					.catch(error=>{
						console.log(error);
					});
				}
			},
			del:function(exr_record_idx){
				deleteExrRecord(exr_record_idx);
			}
		}
		
}

//운동기록 삭제하는 함수
function deleteExrRecord(){
	if(confirm("삭제하시겠습니까?")){
		$.ajax({
			url:"/rest/myrecord/exrRecord/"+exr_record_idx,
			type:"delete",
			success:function(result, status, xhr){
				console.log("result는",result);
				console.log("삭제성공");
				alert("삭제되었습니다");
				location.href="/myrecord/exr_record";
			},
			error:function(xhr, status, error){
				console.log("에러", error);
			}
		});
	}
}

//vue 초기화 함수
function init(){
	app1=new Vue({
		el:"#app1",
		data:{
			exrList:[],
			count:10
		},
		components:{
			rowlist
		}
	});
}


/*
    달력 렌더링 할 때 필요한 정보 목록 

    현재 월(초기값 : 현재 시간)
    금월 마지막일 날짜와 요일
    전월 마지막일 날짜와 요일
*/
function renderCalender(thisMonth) {

    // 렌더링을 위한 데이터 정리
    currentYear = thisMonth.getFullYear();
    currentMonth = thisMonth.getMonth(); //0월부터 시작
    currentDate = thisMonth.getDate();
	//console.log("current달은 : ",currentMonth);
	
    // 이전 달의 마지막 날 날짜와 요일 구하기
    let startDay = new Date(currentYear, currentMonth, 0);
    let prevDate = startDay.getDate();
    let prevDay = startDay.getDay();
    console.log(prevDay);

    // 이번 달의 마지막날 날짜와 요일 구하기
    let endDay = new Date(currentYear, currentMonth + 1, 0);
    nextDate = endDay.getDate();
    let nextDay = endDay.getDay();

    console.log(prevDate, prevDay, nextDate, nextDay);

    // 현재 월 표기
    $(".year-month").text(currentYear + "-" + (currentMonth + 1));

    // 렌더링 html 요소 생성
    calendar = document.querySelector(".dates");
    calendar.innerHTML = "";
    
    // 지난달
    for (let i = prevDate - prevDay; i <= prevDate; i++) {
        calendar.innerHTML = calendar.innerHTML + "<div class='day prev disable'>" + i + "</div>"
    }
    // 이번달
    for (let i = 1; i <= nextDate; i++) {
        calendar.innerHTML = calendar.innerHTML + "<div class='day current' onclick='showExrRecord("+i+")' data-toggle='collapse' data-target='#exrCollapse'>" + i + "</div>"
    }
    // 다음달
    for (let i = 1; i <= (7 - nextDay == 7 ? 0 : 7 - nextDay); i++) {
        calendar.innerHTML = calendar.innerHTML + "<div class='day next disable'>" + i + "</div>"
    }

    // 오늘 날짜 표기
    if (today.getMonth() == currentMonth) {
        todayDate = today.getDate();
        let currentMonthDate = document.querySelectorAll(".dates .current");
        currentMonthDate[todayDate -1].classList.add("today");
    }
}

function calendarInit() {

    // 날짜 정보 가져오기
    const date = new Date(); // 현재 날짜(로컬 기준) 가져오기
    const utc = date.getTime() + (date.getTimezoneOffset() * 60 * 1000); // uct 표준시 도출
    const kstGap = 9 * 60 * 60 * 1000; // 한국 kst 기준시간 더하기
    today = new Date(utc + kstGap); // 한국 시간으로 date 객체 만들기(오늘)
    //console.log(utc);
    //console.log(today);
  
    let thisMonth = new Date(today.getFullYear(), today.getMonth(), today.getDate());
    // 달력에서 표기하는 날짜 객체
    
    currentYear = thisMonth.getFullYear(); // 달력에서 표기하는 연
    currentMonth = thisMonth.getMonth(); // 달력에서 표기하는 월
    let currentDate = thisMonth.getDate(); // 달력에서 표기하는 일
    
    // kst 기준 현재시간
    // console.log(thisMonth);

    // 캘린더 렌더링
    renderCalender(thisMonth);

    // 이전달로 이동
    $(".go-prev").on("click", function() {
        thisMonth = new Date(currentYear, currentMonth - 1, 1);//년, 월, 일
        renderCalender(thisMonth);
        getExrRecordForMonth();
    });

    // 다음달로 이동
    $(".go-next").on("click", function() {
        thisMonth = new Date(currentYear, currentMonth + 1, 1);
        renderCalender(thisMonth); 
        getExrRecordForMonth();
    });
}

//2023-3-3일을 2023-03-03으로 만들어주는 함수 나중에 하나의 공통된 전역 메서드로 빼줄예정
//DB에서 03 이든 3이든 상관없으므로 사용가치가 없어졌지만
//혹시모르니 일단 두겠음
function makeDayFormat(clickedDay){
	let refinedDay= (clickedDay <=9) ? "0"+clickedDay : clickedDay;
	return refinedDay;
}

//해당일의 운동기록과 세부내용을 collapse에 rendering하는 함수
function showExrRecordsOnCollapse(exrLists){
	for(let i=0; i<exrLists.length; i++){
		//console.log("운동명은",exrList[i].exr_name);
		//하나의 운동명을 아래 영역에 보여줄 함수
		
		for(let a=0; a<exrLists[i].exrRecordDetailList.length; a++){
			//console.log(exrList[i].exrRecordDetailList.length);
			console.log("번쨰 세트의 detail_idx는 ", exrLists[i].exrRecordDetailList[a].exr_record_detail_idx );
		}
	}
}

//각 날짜 클릭시 동작할 함수
function showExrRecord(clickedDay){
	let registedDate=currentYear+"-"+(currentMonth+1)+"-"+clickedDay;
	console.log("registedDate", registedDate);
	
	$.ajax({
		url:"/rest/myrecord/exrRecord/"+registedDate,
		type:"GET",
		data: registedDate,
		success:function(result, status, xhr){
			//console.log(typeof result); object형
			console.log("받아온 결과는 ", result);
			app1.exrList=result;
			console.log("exrList의 길이는 ",app1.exrList.length);
			showExrRecordsOnCollapse(result);
		},
		error:function(xhr, status, error){
			console.log("error",error);
			app1.exrList=[];
		}
	});
		
}

function renderExrRecord(registedDataForMonth){
	let divdays=document.getElementsByClassName("current");
	let selectedDays=[];
	
	//숫자 변환 작업 01을 1로 11은 11같이
	for(let i=0; i<registedDataForMonth.length; i++){
		let registedData=registedDataForMonth[i];
		let processedData=registedData.regdate.slice(8,10);
		if(processedData.substr(0,1)==0){
			let selectedDay=registedData.regdate.slice(9,10); //ex: 11 (일)div와 비교해 이미지 붙이기 위해
			selectedDays.push(selectedDay);
		}else{
			let selectedDay=registedData.regdate.slice(8,10); //ex: 11 (일)div와 비교해 이미지 붙이기 위해
			selectedDays.push(selectedDay);
		}
		//console.log(selectedDays[0]);
	}
	
	//console.log(processedData);
	for(let a=0; a<selectedDays.length; a++){
		let getDay=selectedDays[a];
		setBackground(getDay);
	}
}

function setBackground(getDay){
	$($(".current")[getDay-1]).css("background-color", "#ff9933");
}

//신체기록된 내용과 날짜 등을 불러올 메서드
function getExrRecordForMonth(){
	//해당달의 첫날과 마지막날을 JSON형식으로 만듬
	let json={};
	json['firstDay']=currentYear+"-"+(currentMonth+1)+"-"+1;
	json['lastDay']=currentYear+"-"+(currentMonth+1)+"-"+nextDate;
	let dateData=JSON.stringify(json);
	console.log(dateData);
	
	//비동기로 해당달의 첫날과 마지막날을 전송
	$.ajax({
		url:"/rest/myrecord/exrListForMonth",
		type:"POST",
		processData:false,
		data:dateData,
		contentType:"application/json",
		success:function(result, status, xhr){
			renderExrRecord(result);
			console.log("받아온 날짜는",result);
			//alert("성공적으로 불러옴");
		},
		error:function(xhr, status, error){
			console.log(error, "기록불러오던 중 에러발생");
		}
	});
}

$(function(){
	//초기화
    init();
	//달력초기화
	calendarInit();
	
	 //달력의 등록된 신체 기록 보여주기
    getExrRecordForMonth();
	
	//왼쪽 사이드바 페이지 이동 이벤트
    $("#bt_addRecord").click(function(){
    	location.href="/myrecord/addrecord";
    });
    $("#bt_physicalRecord").click(function(){
    	location.href="/myrecord/physical_record";
    });
    $("#bt_exrRecord").click(function(){
    	location.href="/myrecord/exr_record";
    });
    $("#bt_dietRecord").click(function(){
    	location.href="/myrecord/diet_record";
    });
});
</script>
<body class="animsition">
    <!-- top-bar start-->
	<%@include file="../inc/topbar.jsp" %>
    <!-- /top-bar end-->

    <!-- hero section start -->
    <div class="hero-section">
		<!-- navigation-->
	   	<%@include file="../inc/header_navi.jsp" %>
	    <!-- /navigation end -->
    </div>
     <!-- ./hero section end -->
     <div class="top-bar">
     	<div class="container">-</div>
     </div>

    
    <!-- content start -->
    <div class="space-medium">
        <div class="container">
        
			<!-- 1row 시작되는 곳 -->        
            <div class="row">
            	<!-- 왼쪽에 나의 기록 목록 나오는 영역 -->
            	<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
				    <div class = "btn-group-vertical">
				        <button type = "button" class = "btn btn-primary" id="bt_addRecord">기록 추가</button>
				        <button type = "button" class = "btn btn-primary" id="bt_physicalRecord">신체기록</button>
				        <button type = "button" class = "btn btn-primary" id="bt_exrRecord">운동기록</button>
				        <button type = "button" class = "btn btn-default" id="bt_dietRecord">식단기록</button>
				    </div>
            	</div>
            	
            	<!-- 달력 나올 영역 -->
				<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 calendar">
					<div class="sec_cal">
						
						<!-- 실제 달력이 나오는 영역 시작-->
						<div id="div_ExrCalendar">
							<div class="cal_nav">
								<a href="javascript:;" class="nav-btn go-prev">prev</a>
								<div class="year-month"></div>
								<a href="javascript:;" class="nav-btn go-next">next</a>
							</div>
							<div class="cal_wrap">
								<div class="days">
									<div class="day">SUN</div>
									<div class="day">MON</div>
									<div class="day">TUE</div>
									<div class="day">WED</div>
									<div class="day">THU</div>
									<div class="day">FRI</div>
									<div class="day">SAT</div>
								</div>
								<div class="dates"></div>
							</div>
						</div>
						<!-- 실제 달력 나오는 영역 끝 -->
						
					</div>
				</div>
			</div>
            <!--1row 끝나는 곳  -->
            
            <!-- 2row 시작되는 곳 -->
            <div class="row">
				
				<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
				</div>
  				
  				<!-- 운동기록 상세보기가 나올 창 -->
				<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
				
				<template v-for="exer in exrList">
					<rowlist :key_idx="exer.exr_record_idx" :exr="exer"/>
				</template>
				
				</div>
				
			</div>
            <!-- 2row 끝나는 곳 -->
            
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
