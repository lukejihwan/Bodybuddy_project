<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../inc/header_link.jsp" %>
</head>
<!-- collapse 적용하기 위한 CDN필요함. 없으면 깨짐 -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<style>
/* section calendar */
/* fixed로 잡아버리면 사이트가 줄어들때, contents와 충돌남
.btn-group-vertical {
	position: fixed;
}
*/
#app1{
	margin: 10px;
}
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
	border: 2px solid #28a745;
}
.current:hover{
	transform:scale(1.2);
	cursor: pointer;
}

<!-- 구글맵과 관련된 스타일 -->
.space-medium{
    width: 100%;
    height: 100%;
    margin: auto;
}


  #myMap {
    height: 100%;
  }
  .top{
    height: 500px;
  }
  html, body {
    height: 100%;
    margin: 0;
    padding: 0;
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
			<div class="row card-body">
				운동명: {{exer.exr_name}}
			</div>
			<div div class="row">
				<div class="col-md-8 card-body">
					<div class="form-group">
					</div>
				</div>
				<div class="col-md-4 card-body">
					<div class="form-group">
						<button type="button" class="btn btn-warning" v-on:click="update(exer.exr_name)">수정</button>
					</div>
					<div class="form-group">
						<button type="button" class="btn btn-danger">삭제</button>
					</div>
				</div>
			</div>
		</div>
		`,
		props:['exr', 'key_idx'],
		data:function(){
			return{
				exer:this.exr
			};
		},
		methods:{
			update:function(exrname){
				alert(exrname+" 수정할래요?");
			}
		}
}

function init(){
	app1=new Vue({
		el:"#app1",
		data:{
			exrList:[]
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
    console.log(utc);
    console.log(today);
  
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
			showExrRecordsOnCollapse(result);
		},
		error:function(xhr, status, error){
			console.log("error",error);
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
	$($(".current")[getDay-1]).css("background-color", "#49469c");
}

//근력운동에 관한 달력 불러오는 곳
function getStrengthExrCalendar(){
	document.getElementById("div_ExrCalendar").style.border="2px solid #28a745";
}

//러닝에 관한 달력 불러오는 곳
function getRunningCalendar(){
	document.getElementById("div_ExrCalendar").style.border="2px solid #dc3545";
}

//운동기록된 내용과 날짜 등을 불러올 메서드
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
			alert("성공적으로 불러옴");
		},
		error:function(xhr, status, error){
			console.log(error, "기록불러오던 중 에러발생");
		}
	});
}



	/*------------------------------------------------------------------------------
			구글맵과 관련된 영역
		-----------------------------------------------------------------------------*/
	// 1) 맵 초기 콜백 함수 
	function initMap() {
		let mapProp= {
		  center:new google.maps.LatLng(37.556436, 126.945207),
		  zoom:16,
		};
		map = new google.maps.Map(document.getElementById("myMap"),mapProp);

		console.log("잘 호출 되는 거지? ", map);
		
	}
	
	
	// db에 저장된 위치 데이터 불러오는 함수
	function getGpsData(){
		$.ajax({
			url:"/rest/myrecord/today/gps",
			typr:"GET",
			success:function(result, status, xhr){
				createPolyline(result);
				
				console.log("결과안의 개수 ", result.length);
				
				let jsonList=[];
				for(let i=0; i<result.length; i++){
					let dto=result[i];
					
					let json={};
					json['lat']=dto.lati;
					json['lng']=dto.longi;
					
					jsonList.push(json);
					
				}
				//console.log("가공된 제이슨 리스트는? ",jsonList);
				createPolyline(jsonList);
				
			}
		});
	}
	
	
	// 라인그리기
	function createPolyline(jsonList){
		//console.log("그림 그릴 제이슨리스트의 모습은? ", jsonList);

		 const flightPath = new google.maps.Polyline({
			    path: jsonList,
			    geodesic: true,
			    strokeColor: "	#FF4500",
			    strokeOpacity: 1.0,
			    strokeWeight: 6,
			  });
			  flightPath.setMap(map);
	}
	
	/*------------------------------------------------------------------------------*/
	
	
/*** 시작할 때 로드될 메서드 ***/
$(document).ready(function() {
    //초기화
    init();
    //달력초기화
	/*------------------*/
	getGpsData();
	initMap();
	/*------------------*/
	
	//달력초기화
	calendarInit();
    
    //처음 보여주는 달력의 등록된 운동기록 보여주기
    getExrRecordForMonth();
    
    $("#bt_strengthExr").click(function(){
    	getStrengthExrCalendar();
    });

    $("#bt_running").click(function(){
    	getRunningCalendar();
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
    <div class="space-medium" id="app1">
        <div class="container">
            <div class="row">
            	<!-- 왼쪽에 나의 기록 목록 나오는 영역 -->
            	<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
				    <div class = "btn-group-vertical">
				        <button type = "button" class = "btn btn-primary">기록 추가</button>
				        <button type = "button" class = "btn btn-primary">신체기록</button>
				        <button type = "button" class = "btn btn-default">운동기록</button>
				        <button type = "button" class = "btn btn-primary">식단기록</button>
				    </div>
            	</div>
            	
            	<!-- 달력 나올 영역 -->
            	<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
					<div class="sec_cal">
					<button type="button" id="bt_strengthExr" class="btn btn-success btn-sm">근력운동</button>
            		<button type="button" id="bt_running" class="btn btn-danger btn-sm">러닝</button>
						
						<!-- 버튼을 제외한 달력이 보이고 사라지는 것을 제어하기 위한 div -->
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


					</div>
				</div>


				<!-- 구글맵 나올 영역 -->
				<div class="col-lg-4 top">
					<div class="mapArea top" style="background:red">
						<div id="myMap">
						</div>
					</div>
				</div>



			</div>
			<!-- ./row -->
			
			<!-- 하단 상세정보 보여질 애니메이션 창 -->
			<div class="row">
				
				<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
				</div>
  				
  				<!-- 운동기록 상세보기가 나올 창 -->
				<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
				
				<template v-for="exr in exrList">
					<rowlist :key_idx="exr.exr_record_idx" :exr="exr"/>
				</template>
				
					<!-- 
					<div id="exrCollapse" class="collapse">
				 		<div class="card bg-success text-white">
				 			<div class="card-body">
				 				2023-03-20 운동목록 나올 곳	
				 			</div>
				 		</div>
					</div>
					 -->
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
<!-- 구글 맵 API -->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyABFfH85xw6pNOdcGrmBAMGKOJhVhsQL6Q&callback=initMap"></script>