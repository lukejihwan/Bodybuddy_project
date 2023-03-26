<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<!-- content 부분만 비워둔 기본 템플릿 -->
<!-- hero섹션이 포함되어있음 -->
<html lang="en">
<head>
<%@include file="../inc/header_link.jsp"%>
<%@include file="../inc/adminlte.jsp"%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/admin-lte@3.2/dist/css/adminlte.min.css">
<script src="https://cdn.jsdelivr.net/npm/admin-lte@3.2/dist/js/adminlte.min.js"></script>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=0x0lvn9t3z"></script>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=0x0lvn9t3z&submodules=geocoder"></script>
</head>
<style>
	.card-primary:not(.card-outline)>.card-header{
		background-color: #c5f016;
	}
	.btn-default {
	    color: #1e1e1f;
	    background-color: #c5f016;
	}
	.btn-default:hover {
	    color: #2f2e2c;
	    background-color: #b9e118;
	    border-color: #b9e118;
	}
</style>

<body class="animsition">
	<!-- top-bar start-->
	<%@include file="../inc/topbar.jsp"%>
	<!-- /top-bar end-->


	<!-- hero section start -->
	<div class="hero-section">
		<!-- navigation-->
		<%@include file="../inc/header_navi.jsp"%>
		<!-- /navigation end -->
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-12  col-xs-12">
					<div class="hero-caption pinside50">
						<h1 class="hero-title">내 주변보기</h1>
						<p class="small-caps mb30 text-white"></p>
						<p class="hero-text">주변의 헬스장, 공원을 안내해드립니다</p>
						<!-- <a href="classes-list.html" class="btn btn-default">링크 필요하면
							사용할 버튼</a> -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- ./hero section end -->

	<!-- content start -->
	<div class="space-medium">
		<div class="container">
			<div class="row">
				<div class="col">
					<h1>내 주변 보기</h1>
					<hr>
				</div>
			</div>
			<!-- end of row -->
			<div class="row">
				<div class="col-md-6"></div>
				<div class="col-md-6"></div>
			</div>
			<!-- end of row -->
		</div>
		<!-- end of container -->

		<div class="container" id="app1">
			<div class="row">
				<div class="col">
					<div class="card card-primary card-tabs" id="main">
						<div class="card-header p-0 pt-1">
							<ul class="nav nav-tabs" role="tablist">
								<template v-for="(place, i) in places">
									<myheader :key="i" :place="place" :index="i"/>
								</template>
							</ul>
						</div>
						<div class="card-body">
							<div class="tab-content">
								<template>
									<mycontent :place="selected" :coords="coords" :itemlist="itemList"/>
								</template>
						</div>
						<!-- /.card -->
					</div>
				</div>
			</div>
		</div>

	</div>
	<!-- end of space-medium -->
	<!-- /content end -->

	<!-- black footer_space -->
	<%@include file="../inc/footer_space.jsp"%>



	<!-- tiny footer -->
	<%@include file="../inc/footer_tiny.jsp"%>

	<%@include file="../inc/footer_link.jsp"%>

</body>
<script type="text/javascript">

	$(()=>{
		authCheck();
		init();
	});
	
	//네이버맵 변수
	let map;
	let mapOptions = {
	    //center: new naver.maps.LatLng(lat, lon),
	    zoom: 13,
	};
	
	// vue 변수
	let app1;
	const myrow={
		template:`
			<tr data-widget="expandable-table" aria-expanded="false" @click="handleClick(htmlDecode(item.title))">
				<td>{{htmlDecode(item.title)}}</td>
				<td>{{getDistance()+' M'}}</td>
			</tr>
		`,
		props:["item"],
		methods:{
			htmlDecode:window.htmlDecode,
			getDistance:function(){
				let destination = window.naver.maps.Point(this.item.mapx, this.item.mapy);
				//console.log("dest with tm128 : ", destination);
				destination = naver.maps.TransCoord.fromTM128ToLatLng(destination);
				//console.log("dest with latlon : ", destination);
				
				let myLocation = new naver.maps.LatLng(app1.coords.lat, app1.coords.lon);
				//console.log("myloc : ", destination);
				
				let converted = map.getProjection().getDistance(myLocation, destination);
				//console.log("converted : ", converted);
				return parseInt(converted);
			},
			handleClick:function(title){
				window.hideAll();
				window.itemSelected(title);
			}
		}
	};
	const myrowbody={
		template:`
			<tr class="expandable-body d-none">
				<td colspan="7">
					<p style="display: none;">
						<span>{{item.address}}</span><br/>
						<span>{{item.roadAddress}}</span><br/>
						<span><a :href="item.link">{{item.link}}</a></span>
					</p>
				</td>
			</tr>
		`,
		props:["item"],
	};
	const reviews = {
			template:`
				<div class="row">
					<div class="col-md-12">
						<p class="no-result">검색 결과가 존재하지 않습니다..</p>
					</div>
					<!-- end of col-md-12 -->
				</div>
				<!-- end of row -->
			`,
			data(){
				return {
					reviewList:[]
				};
			}
	}
	
	
	const myheader = {
			template:`
				<li class="nav-item"><a :class="index==0?'nav-link active':'nav-link'"
					:id="place+'-tab'" data-toggle="pill"
					href="#" role="tab"
					@click="setContent"
					aria-controls="place+'-content'" :aria-selected="index==0?'true':'false'">{{doc[place]}}</a></li>
			`,
			props:["place", "index"],
			data(){
				return {
					doc:{
						gym:"헬스장",
						park:"공원"
					}
				};
			},
			methods:{
				setContent:function(){
					app1.selected=this.place;
					this.hideAll();					
				},
				hideAll:window.hideAll
			}
	};
	const mycontent = {
			template:`
				<div id="content">
					<br/>
					<div class="row">
						<div class="col">
							<h3>주변 {{doc[place]}} 추천</h3>
						</div>
					</div>
					<!-- end of row -->
					<br/>
					<div class="row">
						<div class="col-md-12">
							<div class="card">
								<div class="card-body">
									
									<div class="row">
										<div class="col-md-6">
											<div id="map" style="width:100%;height:425px;"></div>
										</div>
										<!-- end of col-md-6 -->
										<div class="col-md-6 table-responsive">
											<table class="table table-bordered table-hover">
												<thead>
													<tr>
													</tr>
												</thead>
												<tbody>
													<template v-for="(item, i) in itemlist">
														<myrow :item="item"/>
														<myrowbody :item="item"/>
													</template>
												</tbody>
											</table>
										</div>
										<!-- end of col-md-6 -->
									</div>
									<!-- end of row -->
								</div>
								<!-- end of body -->
							</div>
							<!-- end of card -->
						</div>
						<!-- end of col-md-12 -->
				</div>
				<!-- end of row -->
				<div class="row">
				<div class="col-md-12">
				<div class="card card-primary">
					<div class="card-header">
						<h3 class="card-title">블로그 리뷰</h3>
					</div>
					<div class="card-body">
						<reviews/>
					</div>
					<!-- end of body -->
				</div>
				<!-- end of card -->
			</div>
			<!-- end of col-md-12 -->
				</div>
				<!-- end of row -->
				</div>
			`,
			props:["place", "coords", "itemlist"],
			data(){
				return {
					doc:{
						gym:"헬스장",
						park:"공원"
					},
					infowindow:new naver.maps.InfoWindow(),
					marker:"",
					listener:""
				};
			},
			methods:{
				initMap:function(){
					let center = new naver.maps.LatLng(this.coords.lat, this.coords.lon);
					map.setCenter(center);
					map.setZoom(13);
					
					if(this.marker!=""){
						console.log("내위치 marker 리스너 삭제됨");
						new naver.maps.Event.removeListener(this.listener);
						this.marker.setMap(null);
					}
					
					this.marker = new naver.maps.Marker({
					    position: new naver.maps.LatLng(this.coords.lat, this.coords.lon),
					    map: map,
					})
					
					let infowindow = this.infowindow;
					infowindow.setContent('<div>내 위치</div>');
					this.listener = naver.maps.Event.addListener(this.marker, 'click', function(e) {
					    infowindow.open(map, this.marker);
					    map.setCenter(e.coord);
					    map.setZoom(15);
					    console.log("내위치 e", e);
					})
					
					
					infowindow.open(map, this.marker);
					console.log(this.infowindow);
					
					getPlaceListByCoords(this.coords.lat, this.coords.lon, this.place);
				}
			},
			watch:{
				coords:function(cd){
					console.log("cd : ", cd);
					this.initMap();
				},
				place:function(){
					console.log("pc");
					this.initMap();
				},
			},

			mounted(){
				map = new naver.maps.Map("map", mapOptions);
				console.log("mounted:",this.coords.lat);
			},
			components:{
				myrow,
				myrowbody,
				reviews
			}
	}
	
	
	
	//vue
	
	function init() {
		app1 = new Vue({
			el:"#app1",
			data:{
				places:[
					"gym",
					"park"
				],
				selected:"gym",
				coords:{
					lat:1,
					lon:1
				},
				start:1,
				itemList:[],
				markerList:[],
				itemFlag:false,
				itemFlag2:false,
				myaddr:"",
			},
			components:{
				myheader,
				mycontent
			},
			watch:{
				itemList:function(){
					removeEventToMarker();
					setMarkerList();
					addEventToMarker();
				}
			}
			
		});
	}
	
	function itemSelected(e){
		console.log("itemSelected 눌림", e);
		//console.log("객체?", typeof(e)=='object');
		console.log("엘리먼트?", e);
		
		let trList = $("tr[data-widget='expandable-table']");
		
		console.log("itemFlag", app1.itemFlag);
		if(app1.itemFlag==false){
			if(typeof(e)=='object'){
				for(let i =0;i<trList.length;i++){
					//console.log("tr.val", htmlDecode(trList[i].__vue__.item.title));
					if(e.title==htmlDecode(trList[i].__vue__.item.title)){
						app1.itemFlag=true;
						$(trList[i]).trigger("click");
					}
				}
			}else{
				for(let i =0;i<app1.markerList.length;i++){
					//console.log("app1.markerList[i].title", app1.markerList[i].title);
					if(e==app1.markerList[i].title){
						app1.itemFlag=true;
						$(app1.markerList[i].marker.getElement()).trigger("click");
					}
				}
			}
			
		}else{
			console.log("끊김");
			
			app1.itemFlag=false;
			
			if(app1.itemFlag2==false){
				app1.itemFlag2=true;
			}else{
				app1.itemFlag2=false;
				return;
			}
			
			console.log("블로그 검색을 위한 타이틀 요청");
		}
	}
	
	function hideAll() {
		$("tr[aria-expanded='true']").trigger("click")
	}
	
	//상활별 GPS 처리
	function authCheck() {
		<sec:authorize access="isAuthenticated()">
			if('<sec:authentication property="principal.member.address"/>'!='null'){
				askMemberAddress();
			}else{
				getGPS();
			}
		</sec:authorize>
		<sec:authorize access="isAnonymous()">
			getGPS();
		</sec:authorize>
	}
	
	function askMemberAddress() {
		Swal.fire({
			  title: '회원님이 기입하신 주소('+htmlDecode('<sec:authorize access="isAuthenticated()"><sec:authentication property="principal.member.address.member_address"/></sec:authorize>')+')로 검색하시겠습니까?',
			  text:"아니오를 누르실 경우 gps로 현재 위치를 기반으로 알려드립니다",
			  showCancelButton: true,
			  confirmButtonText: '네',
			  cancelButtonText: '아니오',
			  icon:"question",
			  confirmButtonColor: '#c5f016',
			  cancelButtonColor: '#d33',
			}).then((result) => {
			  if (result.isConfirmed) {
				  app1.myaddr = htmlDecode('<sec:authorize access="isAuthenticated()"><sec:authentication property="principal.member.address.member_address"/></sec:authorize>');
				  getGPSByAddr();
			  }else{
				  getGPS();
			  }
			})
	}
	function getGPSByAddr() {
		$.ajax({
			url:"/rest/aroundme/coords/addr/"+app1.myaddr,
			type:"GET",
			success:(result, status, xhr)=>{
				console.log(result);
				app1.coords = result;
			},
			error:(xhr, status ,err)=>{
				console.log(err);
			}
		});
	}
	
	function getGPS() {
		navigator.geolocation.getCurrentPosition(
			(pos)=>{
				alert("허락 누름");
				console.log(pos);
				lat = pos.coords.latitude;
				lon = pos.coords.longitude;
				//getPlaceListByCoords(lat, lon, "gym");
				let a = {
						lat,
						lon
				}
				//console.log("before : ", app1.coords);
				app1.coords = a;
				getAddrByCoords(lat, lon);
					
				//console.log("after : ", app1.coords);
			}, 
			()=>{
				alert("거절 누름");
			}
		);
	}
	
	function setMarkerList() {
		for(let i =0;i<app1.markerList.length;i++){
			app1.markerList[i].marker.setMap(null);
		}
		app1.markerList.length=0;
		
		let markerList = [];
		let trList = $("tr[data-widget='expandable-table']");
		for(let i =0;i<app1.itemList.length;i++){
			let dest = naver.maps.TransCoord.fromTM128ToLatLng(naver.maps.Point(app1.itemList[i].mapx, app1.itemList[i].mapy));
			//console.log("dest in setMarkerList : ", dest);
			let marker = {
					marker:new naver.maps.Marker({
					    position: dest,
					    map: map,
					}),
					title:htmlDecode(app1.itemList[i].title),
			}
			markerList.push(marker);
		}
		app1.markerList=markerList;
		console.log(markerList);
	}
	
	function addEventToMarker() {
		app1.markerList.forEach(marker=>{
			marker.listener=naver.maps.Event.addDOMListener(marker.marker.getElement(), 'click', function(e) {
				let infowindow = new naver.maps.InfoWindow({
				    content: "<div style=\"padding:20px;\">"+marker.title+"</div><center><button type=\"button\" onClick=\"window.open('http://map.naver.com/index.nhn?slng="+app1.coords.lon+"&slat="+app1.coords.lat+"&stext="+app1.myaddr+"&elng="+marker.marker.getPosition().x+"&elat="+marker.marker.getPosition().y+"&pathType=1&showMap=true&etext="+marker.title+"&menu=route')\" class=\"btn btn-default\" style=\"margin-bottom:10px\">길찾기</button></center>"
				});
			    infowindow.open(map, marker.marker);
			    map.setCenter(marker.marker.getPosition());
			    map.setZoom(15);
			    
			    itemSelected(marker);
			})
		});
	}
	
	function removeEventToMarker() {
		console.log("마커리스트 리스너 삭제됨");
		app1.markerList.forEach(marker=>{
			new naver.maps.Event.removeDOMListener(marker.listener);
		});
	}
	
	//네이버
	function getPlaceListByCoords(lat, lon, place) {
		setLoadingOverlay($("#main"));
		$.ajax({
			url:"/rest/aroundme/place/coords/"+place+"/"+lat+","+lon,
			type:"GET",
			success:(result, status, xhr)=>{
				console.log(result);
				app1.itemList = result.items;
				removeLoadingOverlay($("#main"));
			},
			error:(xhr, status ,err)=>{
				console.log(err);
			}
		});
	}
	function getAddrByCoords(lat, lon){
		setLoadingOverlay($("#main"));
		$.ajax({
			url:"/rest/aroundme/addr/coords/"+lat+","+lon,
			type:"GET",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success:(result, status, xhr)=>{
				console.log(result);
				app1.myaddr=result.msg;
			},
			error:(xhr, status ,err)=>{
				console.log(err);
			}
		});
	}
	
	function getBlogByTitle(title){
		
		$.ajax({
			url:"/rest/aroundme/blog/"+title,
			type:"GET",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success:(result, status, xhr)=>{
				console.log(result);
			},
			error:(xhr, status ,err)=>{
				console.log(err);
			}
		});
	}
	
	
	
	//기타 함수
	function htmlDecode(input) {
	  var doc = new DOMParser().parseFromString(input, "text/html");
	  return doc.documentElement.textContent;
	}
	
	function setLoadingOverlay(el){
		if($(el).children(".overlay").length>0) return;
		
		let overlay = `
			<div class="overlay dark">
			  <i class="fas fa-2x fa-sync-alt fa-spin"></i>
			</div>
		`;
		$(el).append(overlay);
	}
	function removeLoadingOverlay(el){
		$(el).children(".overlay").remove();
	}
	
	
</script>
</html>
