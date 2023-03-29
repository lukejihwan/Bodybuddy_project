<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="./inc/header_link.jsp" %>
<%@include file="./inc/adminlte.jsp" %>
<style type="text/css">
	.card-primary:not(.card-outline)>.card-header{
		background-color: #c5f016;
	}
	.card-primary:not(.card-outline)>.card-header, .card-primary:not(.card-outline)>.card-header a {
	    color: #383838;
	}
	.clickable{
		cursor: pointer;
	}
</style>
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
            <!-- end of row -->
            <div class="row">
                <div class="col-md-6">
                	<template>
                		<board_rank id="exr_routine"/>
                	</template>
                	<template>
                		<board_rank id="exr_tip"/>
                	</template>
                	<template>
                		<board_rank id="exr_today"/>
                	</template>
                </div>
                <div class="col-md-6">
                	<template>
                		<board_rank id="diet_share"/>
                	</template>
                	<template>
                		<board_rank id="diet_tip"/>
                	</template>
                	<template>
                		<board_rank id="free_board"/>
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
		
		getWalkRank("daily");
	});
	
	const rank = {
			template:`
	            <div class="card card-primary">
	                <div class="card-header">
	                    <h3 class="card-title">달리기 {{titleMap[title]}} 랭킹</h3>
	                    <div class="card-tools">
	                        <ul class="pagination pagination-sm float-right">
	                            <li class="page-item"><a class="page-link clickable" @click="getWalkRank('daily')">일일</a></li>
	                            <li class="page-item"><a class="page-link clickable" @click="getWalkRank('weekly')">주간</a></li>
	                            <li class="page-item"><a class="page-link clickable" @click="getWalkRank('monthly')">월간</a></li>
	                        </ul>
	                    </div>
	                </div>
	            
	                <div class="card-body p-0">
	                    <table class="table">
	                        <thead>
	                            <tr>
	                                <th style="width: 10%;">랭킹</th>
	                                <th style="width: 20%;">닉네임</th>
	                                <th style="width: 60%;">1위와의 차이</th>
	                                <th style="width: 10%;">거리</th>
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
		                                <td><b>{{handleDistance(rank.distance)}}</b></td>
		                            </tr>
	                            </template>
	                        </tbody>
	                    </table>
	                </div>
	            
	            </div>
			`,
			props:["ranklist"],
			data(){
				return {
					title:"daily",
					titleMap:{
						"daily":"일일",
						"weekly":"주간",
						"monthly":"월간"
					}
				};
			},
			methods:{
				colorInPer:function(rank){
					//80, 60, 40, 20
					//파, 초, 주, 빨
					let per;
					let color;
					
					per = this.getPerFromTop(rank);
					
					//console.log("per : ", per);
					
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
					
					//console.log("color : ", color);
					
					return color;
				},
				getPerFromTop:function(rank){
					return (rank.distance / this.ranklist[0].distance * 100).toFixed(0);
				},
				getDistanceFromTop:function(rank){
					let distance;
					distance = (this.ranklist[0].distance-rank.distance);
					
					distance = this.handleDistance(distance);
					
					return distance;
				},
				handleDistance:function(distance){
					if(distance >= 1000){
						distance = (distance / 1000).toFixed(2);
						distance += "Km";
					}else{
						distance += "M";
					}
					return distance;
				},
				getWalkRank:function(period){
					this.title=period;
					console.log("period", period);
					console.log("title", this.title);
					window.getWalkRank(period);
				},
				
			}
	};
	
	const board_rank = {
			template:`
				<div class="card card-primary" :id="id">
			        <div class="card-header">
			            <h3 class="card-title">{{boardMap[id].title}}게시판 주간 추천 랭킹</h3>
			        </div>
			    
			        <div class="card-body p-0">
			            <table class="table table-hover">
			                <thead>
			                    <tr>
			                        <th>No</th>
			                        <th>제목</th>
			                        <th>작성자</th>
			                        <th>조회수</th>
			                        <th>추천수</th>
			                    </tr>
			                </thead>
			                <tbody>
			                	<template v-for="(item, i) in boardlist">
				                    <tr @click="getDetail(boardMap[id], item.board_idx)">
				                        <td>{{item.board_idx}}</td>
				                        <td>{{item.title}}<span class="comment-count">{{(item.commentCount == 0?"":"  ["+item.commentCount+"]")}}</span></td>
				                        <td>{{item.writer}}</td>
				                        <td>{{item.hit}}</td>
				                        <td>{{item.recommend}}</td>
				                    </tr>
			                    </template>
			                </tbody>
			            </table>
			        </div>
			    
			    </div>
			`,
			props:["id"],
			data(){
				return {
					boardMap:{
						"exr_routine":{
							title:"운동 루틴 공유",
							boardName:"exr_routine",
							uri:"/exr/routine_detail",
							dataType:"uri"
						},
						"exr_tip":{
							title:"운동 팁 공유",
							boardName:"exr_tip",
							uri:"/exr/tip_detail",
							dataType:"uri"
						},
						"exr_today":{
							title:"오운완",
							boardName:"exr_today",
							uri:"exr/today_detail",
							dataType:"query"
						},
						"diet_share":{
							title:"식단 공유",
							boardName:"diet_share",
							uri:"/diet/share_detail",
							dataType:"uri"
						},
						"diet_tip":{
							title:"식단 팁",
							boardName:"diet_tip",
							uri:"/diet/tip_detail",
							dataType:"uri"
						},
						"free_board":{
							title:"자유",
							boardName:"free_board",
							uri:"/board/free_detail_view",
							dataType:"uri"
						},
					},
					boardlist:[]
				};
			},
			mounted(){
				console.log("id : ", this.id);
				this.getBoardRank("weekly", this.id);
			},
			methods:{
				getBoardRank:window.getBoardRank,
				getDetail:window.getDetail,
			}
			
	};
	
	function init() {
		app1 = new Vue({
			el:"#app1",
			data:{
				rankList:[],
				
			},
			components:{
				rank,
				board_rank
			}
		});
	}
	
	function getWalkRank(period) {
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

	function getBoardRank(period, boardName) {
		$.ajax({
			url:"/rest/ranking/board/"+period+"/"+boardName,
			type:"GET",
			success:(result, status, xhr)=>{
				console.log("가져온 테이블 : ", boardName);
				$("#"+boardName)[0].__vue__.boardlist = result;
				console.log(result);
				console.log($("#"+boardName)[0].__vue__.boardlist);
			},
			error:(xhr, status, err)=>{
				console.log(xhr);
			}
		});
	}
	
	function getDetail(board, board_idx) {
		if(board.dataType=="uri"){
			location.href = board.uri + "/" + board_idx;
		}else{
			location.href = board.uri + "?" + board.boardName + "_idx=" + board_idx;
		}
	}
</script>
</html>
