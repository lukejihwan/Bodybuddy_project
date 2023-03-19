<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../inc/header_link.jsp" %>
</head>
<style>
/* section calendar */

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
    font-size: 12px;
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
</style>
<script type="text/javascript">
let currentYear;
let currentMonth;
let today;
/*
    달력 렌더링 할 때 필요한 정보 목록 

    현재 월(초기값 : 현재 시간)
    금월 마지막일 날짜와 요일
    전월 마지막일 날짜와 요일
*/
function renderCalender(thisMonth) {

    // 렌더링을 위한 데이터 정리
    currentYear = thisMonth.getFullYear();
    currentMonth = thisMonth.getMonth();
    currentDate = thisMonth.getDate();
	//console.log("currentDate",currentDate);
	
    // 이전 달의 마지막 날 날짜와 요일 구하기
    let startDay = new Date(currentYear, currentMonth, 0);
    let prevDate = startDay.getDate();
    let prevDay = startDay.getDay();
    console.log(prevDay);

    // 이번 달의 마지막날 날짜와 요일 구하기
    let endDay = new Date(currentYear, currentMonth + 1, 0);
    let nextDate = endDay.getDate();
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
        calendar.innerHTML = calendar.innerHTML + "<div class='day current' data-toggle='collapse' data-target='#demo'>" + i + "</div>"
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
    });

    // 다음달로 이동
    $(".go-next").on("click", function() {
        thisMonth = new Date(currentYear, currentMonth + 1, 1);
        renderCalender(thisMonth); 
    });
}

//시작할 때 로드될 메서드
$(document).ready(function() {
    calendarInit();
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
            	<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
					<div class="sec_cal">
					<button type="button" id="bt_strengthExr" class="btn btn-success btn-sm">근력운동</button>
            		<button type="button" id="bt_running" class="btn btn-success btn-sm">러닝</button>
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
			
			<!-- 하단 상세정보 보여질 애니메이션 창 -->
			<div class="row">
				
				<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
				</div>
				
				<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
					<div id="demo" class="collapse show">
					 	2023-03-20 운동목록 나올 곳
					</div>
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
