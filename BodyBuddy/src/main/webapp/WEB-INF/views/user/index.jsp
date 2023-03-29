<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="./inc/header_link.jsp" %>
<%@include file="./inc/adminlte.jsp" %>
</head>

<body class="animsition">
	<!-- top-bar start-->
	<%@include file="./inc/topbar.jsp" %>
    <!-- /top-bar end-->

    <!-- hero section start -->
    <div class="hero-section">
		<!-- navigation-->
	   	<%@include file="./inc/header_navi.jsp" %>
	    <!-- /navigation end -->
        <div class="container">
          <div class="row">
              <div class="col-lg-6 col-md-6 col-sm-12  col-xs-12">
                  <div class="hero-caption pinside50">
                      <h1 class="hero-title">BodyBuddy</h1>
                      <p class="hero-text">환영합니다 헬스 커뮤니티 BodyBuddy 입니다</p>
                  </div>
              </div>
          </div>
      	</div>
    </div>
     <!-- ./hero section end -->
     
    <!-- content start -->
    
    <div class="space-medium"  id="app1">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                	<template>
                        <rank :ranklist="rankList"/>
                	</template>
                </div>
            </div>
        </div>
    </div>
    <!-- /content end -->
    
	<!-- black footer_space -->
    <%@include file="./inc/footer_space.jsp" %>
    
    
    
    <!-- tiny footer -->
    <%@include file="./inc/footer_tiny.jsp" %>
    
    <%@include file="./inc/footer_link.jsp" %>
    
</body>
<script type="text/javascript">
	
	let app1;
	
	$(()=>{
		init();
		
		getRank("daily");
	});
	
	const rank = {
			template:`
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title">달리기 랭킹</h3>
                    <div class="card-tools">
                        <ul class="pagination pagination-sm float-right">
                            <li class="page-item"><a class="page-link" href="#">일일</a></li>
                            <li class="page-item"><a class="page-link" href="#">주간</a></li>
                            <li class="page-item"><a class="page-link" href="#">월간</a></li>
                        </ul>
                    </div>
                </div>
            
                <div class="card-body p-0">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>랭킹</th>
                                <th>닉네임</th>
                                <th>1위와의 차이</th>
                                <th>거리</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<template v-for="(rank, i) in ranklist">
	                            <tr>
	                                <td>{{(i+1) + " "}}th</td>
	                                <td>{{rank.member.nickname}}</td>
	                                <td>
	                                    <div class="progress-group">
	                                    	<span :class="'badge bg-'+colorInPer(rank)">{{getPerFromTop(rank)}}%</span>
	                                        <span class="float-right"><b>{{getDistanceFromTop(rank)}} 차이</b></span>
	                                        <div class="progress progress-xs">
	                                            <div :class="'progress-bar bg-'+colorInPer(rank)" :style="'width: '+getPerFromTop(rank)+'%'"></div>
	                                        </div>
	                                    </div>
	                                </td>
	                                <td>{{}}</td>
	                            </tr>
                            </template>
                        </tbody>
                    </table>
                </div>
            
            </div>
			`,
			props:["ranklist"],
			methods:{
				colorInPer:function(rank){
					//80, 60, 40, 20
					//파, 초, 주, 빨
					let per;
					let color;
					
					per = this.getPerFromTop(rank);
					
					console.log("per : ", per);
					
					switch(per){
						case per>=80: ;break;
						case per>=60: color="success";break;
						case per>=40: color="warning";break;
						case per>=20: color="danger";break;
					}
					if(per >80){
						color="indigo";
					}else if(per > 60){
						color="primary";
					}else if(per > 40){
						color="success";
					}else if(per > 20){
						color="warning";
					}else{
						color="danger";
					}
					
					console.log("color : ", color);
					
					return color;
				},
				getPerFromTop:function(rank){
					return rank.distance / this.ranklist[0].distance * 100;
				},
				getDistanceFromTop:function(rank){
					let distance;
					distance = this.ranklist[0].distance-rank.distance;
					
					if(distance >= 1000){
						distance = (distance / 1000).toFixed(2);
						distance += "Km";
					}else{
						distance += "M";
					}
					
					return distance;
				}
			}
	};
	
	function init() {
		app1 = new Vue({
			el:"#app1",
			data:{
				rankList:[]
			},
			components:{
				rank
			}
		});
	}
	
	function getRank(period) {
		$.ajax({
			url:"/rest/ranking/walk/"+period,
			type:"GET",
			success:(result, status, xhr)=>{
				console.log(result);
				app1.rankList = result;
			},
			error:(xhr, status, err)=>{
				console.log(xhr);
			}
		});
	}
</script>
</html>
