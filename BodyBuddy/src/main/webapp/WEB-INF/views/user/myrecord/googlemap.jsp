<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../inc/header_link.jsp" %>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
  #map {
    height: 100%;
  }
  .top{
    height: 80%;
  }
  html, body {
    height: 100%;
    margin: 0;
    padding: 0;
  }
</style>
</head>
<body>
구글맵 출력 예정
		<div class="row mt-2 top">
			<div class="col-md-9 border top">
				<div id="map">
			
				</div>
			</div>
		</div>
<script type="text/javascript">
	//let map=new google.maps.Map(document.getElementById("map"), mapProp);
	
	// 1) 맵 초기 콜백 함수 
	function initMap(){
		let map = new google.maps.Map(document.getElementById('map'), {
			  center: new google.maps.LatLng(37.556436, 126.945207),
			  zoom: 14
			});
		
		
			let test=[
				{lat : 37.56288275392123, lng : 126.94683778297095},
				{lat : 37.55906185816793, lng : 126.94410917188688},
				{lat : 37.55872803568523, lng : 126.93776684847374}
				];
			
			// 이대 주변에서 크게 뜀! 경로1 - geocoding으로 
			let test2=[
				{lat : 37.56288275392123, lng : 126.94683778297095},
				{lat : 37.55906185816793, lng : 126.94410917188688},
				{lat : 37.55872803568523, lng : 126.93776684847374},
				{lat : 37.5428234, lng : 126.9325981},
				{lat : 37.5498977, lng : 126.9427769},
				{lat : 37.5561674, lng : 126.9392701},
				{lat : 37.5559517, lng : 126.9325981}
				
				//{lat : 37.5637561, lng : 126.9084211},
				//{lat : 37.566535, lng : 126.7645827},
				//{lat : 37.566535, lng : 126.9779692}
				//{lat : 35.907757, lng : 127.766922}
			];
				
		 const flightPath = new google.maps.Polyline({
			    path: test2,
			    geodesic: true,
			    strokeColor: "	#FF4500",
			    strokeOpacity: 1.0,
			    strokeWeight: 4,
			  });
			  flightPath.setMap(map);
	}		
	
	// 위도 경도 가져오기
	function getLocation(){
		// 이 경로를 rest로 받아야 겠다!
		location.href="https://maps.googleapis.com/maps/api/geocode/json?latlng=37.556436,126.945207&key=AIzaSyCygGXycbdibyxeRnH6YKo6mZSeKV1Nfn0";
	}
	
	
	$(function(){
		initMap();
		
		$("#bt_geo").click(function(){
			getLocation();
		});
	});
</script>
</body>
</html>
<!-- 구글 맵 API & 드로잉 레이어 라이브러리(API) -->
<script async src="https://maps.googleapis.com/maps/api/js?&libraries=drawing&libraries=drawing&libraries=drawing&key=AIzaSyABFfH85xw6pNOdcGrmBAMGKOJhVhsQL6Q&callback=initMap"></script>
