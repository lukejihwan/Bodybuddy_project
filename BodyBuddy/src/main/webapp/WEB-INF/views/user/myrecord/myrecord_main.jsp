<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../inc/header_link.jsp" %>
</head>
<style>
.dropdown-menu{

}
#title_exrMain{
	background-color: #c5f016;
	padding: 10px;
	margin: 10px;
	border-radius: 6px;
	text-align: center;
}
#t_exr{
	margin-top: 10px;
	font-size: 40px;
}
.under_box{
	height: 100px;
}
#temperature_area{
	padding: 30px;
	height: 150px;
	text-align:center;
	font-size: 40px;
	border-bottom: 1px solid rgba(173, 184, 173);
}
#gps_icon{
	width: 20px;
}
#temperature_icon{
	width: 90px;
}
#local_weather{
	text-align: right;
}

/*오늘의 운동지수 맨 아래 영역*/
#under_rowgroup{
	margin-top: 30px;
	text-align: center;
}
#humidity_icon{
	width: 40px;
}
#wind_icon{
	width: 40px;
}
#precipitation_icon{
	width: 40px;
}
</style>
<script type="text/javascript">
//왼쪽영역의 주소를 바꿔주는 함수
function changeAddress(rawnx,rawny){
	console.log("dkdk",rawnx,rawny);
	switch(rawnx,rawny){
		case (60,127):$("#t_address").text("서울특별시 중구");break;
		case (60,120):$("#t_address").text("경기도 수원");break;
		case (73,134):$("#t_address").text("강원도 춘천");break;
		case (91,77):$("#t_address").text("경상남도 창원");break;
		case (89,91):$("#t_address").text("경상북도");break;
		case (52,38):$("#t_address").text("제주특별자치도");break;
		case (127,127):$("#t_address").text("울릉군");break;
		case (63,89):$("#t_address").text("전라북도 전주");break;
		case (51,67):$("#t_address").text("전라남도 목포");break;
		case (68,100):$("#t_address").text("충청남도 천안");break;
		case (69,107):$("#t_address").text("충청북도 청주");break;
	}
}

function getWeather(rawnx, rawny){
	changeAddress(rawnx,rawny);
	
	
	console.log(rawnx, rawny);
	console.log(typeof rawnx);
	$.ajax({
		url:"/rest/myrecord/weatherAPI/"+rawnx+"/"+rawny,
		type:"GET",
		success:function(result, status, xhr){
			console.log("날씨 api 결과는", result);
		},
		error:function(xhr, status, error){
			console.log("error", error);
		}
	});
}

$(document).ready(function(){
	//운동기록 메인 페이지로 들어 올 때 API호출
	getWeather(60,127);
	
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
            	<!-- 
            	<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
				    <div class = "btn-group-vertical">
				        <button type = "button" class = "btn btn-primary">기록 추가</button>
				        <button type = "button" class = "btn btn-default">신체기록</button>
				        <button type = "button" class = "btn btn-primary">운동기록</button>
				        <button type = "button" class = "btn btn-primary">식단기록</button>
				    </div>
            	</div>
            	 -->
            	
            	<!-- 오늘의 날씨 정보 나오는 곳 -->
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div>
                    	<div id="title_exrMain">
                    		<h2 id="t_exr">오늘의 운동지수</h2>
                    	</div>
                    	<div>
                    		<div class="row">
	                    		<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
	                    			<img id="gps_icon" alt="이미지 불러오는 중" src="/resources/user/images/gps_icon.png">
	                    			<h5 style="display: inline" id="t_address">서울특별시 중구</h5>
	                    		</div>
	                    		<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
	                    			<div class="dropdown dropright">
		                    			<button type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown">
		                    				지역별 날씨
		                    			</button>
		                    			<div class="dropdown-menu">
		                    				<a class="dropdown-item" href="javascript:getWeather(60,27)">서울</a>
		                    				<a class="dropdown-item" href="javascript:getWeather(60,120)">경기도</a>
		                    				<a class="dropdown-item" href="javascript:getWeather(73,134)">강원도</a>
		                    				<a class="dropdown-item" href="javascript:getWeather(89,91)">경상북도</a>
		                    				<a class="dropdown-item" href="javascript:getWeather(91,77)">경상남도</a>
		                    				<a class="dropdown-item" href="javascript:getWeather(63,89)">전라북도</a>
		                    				<a class="dropdown-item" href="javascript:getWeather(51,67)">전라남도</a>
		                    				<a class="dropdown-item" href="javascript:getWeather(69,107)">충청북도</a>
		                    				<a class="dropdown-item" href="javascript:getWeather(68,100)">충청남도</a>
		                    				<a class="dropdown-item" href="javascript:getWeather(52,38)">제주특별자치도</a>
		                    				<a class="dropdown-item" href="javascript:getWeather(127,127)">울릉도</a>
		                    			</div>
	                    			</div>
	                    		</div>
                    		</div>
                    	</div>
                    	<div>
                    		<div id="temperature_area">
                    			오늘의 평균 기온
								20.4&#8451
								<img id="temperature_icon" alt="이미지 불러오는 중" src="/resources/user/images/temperature_icon.png">
                    		</div>
                    	</div>
                    	<div>
                    		<div class="row" id="under_rowgroup">
	                    		<div class="col-sm-4">
	                    			<div class="under_box">
	                    				<img id="humidity_icon" alt="이미지 불러오는 중" src="/resources/user/images/humidity_icon.png">
	                    				<br>
	                    				30%
	                    			</div>
	                    		</div>
	                    		<div class="col-sm-4">
	                    			<div class="under_box">
	                    				<img id="wind_icon" alt="이미지 불러오는 중" src="/resources/user/images/wind_icon.png">
	                    				<br>
	                    				북서풍 12m/s
	                    			</div>
	                    		</div>
	                    		<div class="col-sm-4">
	                    			<div class="under_box">
	                    				<img id="precipitation_icon" alt="이미지 불러오는 중" src="/resources/user/images/rain_icon.png">
	                    				<br>
	                    				0
	                    			</div>
	                    		</div>
                    		</div>
                    	</div>
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
