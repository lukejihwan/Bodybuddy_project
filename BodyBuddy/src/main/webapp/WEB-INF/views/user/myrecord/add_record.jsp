<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../inc/header_link.jsp" %>
</head>
<style>
.dateHead div {
  background: #e31b20;
  color: #fff;
  text-align: center;
  border-radius: 5px;
}
.grid {
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  grid-gap: 5px;
}
.grid div {
  padding: .6rem;
  font-size: .9rem;
  cursor: pointer;
}
.dateBoard div {
  color: #222;
  font-weight: bold;
  min-height: 6rem;
  padding: .6rem .8rem;
  border-radius: 5px;
  border: 1px solid #eee;
}
.noColor {
  background: #eee;
}
.header {
  display: flex;
  justify-content: space-between;
  padding: 1rem 2rem;
}
/* 좌우 버튼 */
.btns {
 display: block;
 width: 20px;
 height: 20px;
 border: 3px solid #000;
 border-width: 3px 3px 0 0;
 cursor: pointer;
}
.prevDay {
  transform: rotate(-135deg);
}
.nextDay {
  transform: rotate(45deg);
}
/* ---- */
* {
  margin: 0;
  padding: 0;
  list-style: none;
  box-sizing: border-box;  
  font-family: Pretendard;
}
.rap {
  max-width: 820px;
  padding: 0 1.4rem;
  margin-top: 1.4rem;
}
.dateHead {
  margin: .4rem 0;
}
.btn-group-vertical{
	position: fixed;
}
</style>
<script type="text/javascript">
let currentYear;
let currentMonth;
function getDate(){
	let date = new Date();
	let makeCalendar = (date) => {
  	currentYear = new Date(date).getFullYear();
  	currentMonth = new Date(date).getMonth() + 1;
  	let firstDay = new Date(date.setDate(1)).getDay();
  	let lastDay = new Date(currentYear, currentMonth, 0).getDate();
  	let limitDay = firstDay + lastDay;
  	let nextDay = Math.ceil(limitDay / 7) * 7;
  	var htmlDummy ='';
  	for (let i = 0; i < firstDay; i++) {
    	htmlDummy += "<div class='noColor'></div>";
  	}
  	for (let i = 1; i <= lastDay; i++) {
    	htmlDummy += "<div onclick='popups("+i+")'>"+i+"</div>";
  	}
  	for (let i = limitDay; i < nextDay; i++) {
    	htmlDummy += "<div class='noColor'></div>";
  	}
  		document.querySelector('.dateBoard').innerHTML = htmlDummy;
  		document.querySelector('.dateTitle').innerText = currentYear+"년 "+currentMonth+"월";
	}
	
	makeCalendar(date);
	// 이전달 이동
	document.querySelector('.prevDay').onclick = () => {
		makeCalendar(new Date(date.setMonth(date.getMonth() - 1)));
	}
	
	// 다음달 이동
	document.querySelector('.nextDay').onclick = () => {
		makeCalendar(new Date(date.setMonth(date.getMonth() + 1)));
	}
}
function popups(index){
	console.log(currentYear, currentMonth);
	alert(index);
}
$(function(){
	getDate();
	
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
				        <button type = "button" class = "btn btn-default">기록 추가</button>
				        <button type = "button" class = "btn btn-primary">신체기록</button>
				        <button type = "button" class = "btn btn-primary">운동기록</button>
				        <button type = "button" class = "btn btn-primary">식단기록</button>
				    </div>
            	</div>
            	
            	<!-- 달력 나올 영역 -->
                <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
					<div class='rap'>
						<div class="header">
							<div class="btns prevDay"></div>
							<h2 class="dateTitle"></h2>
							<div class="btns nextDay"></div>
						</div>

						<div class="grid dateHead">
							<div>일</div>
							<div>월</div>
							<div>화</div>
							<div>수</div>
							<div>목</div>
							<div>금</div>
							<div onclick="popups(1)">토</div>
						</div>

						<div class="grid dateBoard"></div>
					</div>

					<!-- 
                    <div class="service-block pdt60 mb30">
                        <h1 class="default-title mb30">기록 추가 페이지</h1>
                        <p class="mb40">기록 추가하는 페이지입니다</p>
                        <a href="classes-list.html" class="btn btn-default">View ALL Classes</a>
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
