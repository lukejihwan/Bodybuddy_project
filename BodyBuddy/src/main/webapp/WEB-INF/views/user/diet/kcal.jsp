<%@page import="com.edu.bodybuddy.domain.diet.Food"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@page import="java.util.List"%>
<%@page import="com.edu.bodybuddy.util.PageManager"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%

%>


<!-- content 부분만 비워둔 기본 템플릿 -->
<!-- hero섹션이 포함되어있음 -->
<html lang="en">
<head>
<%@include file="../inc/header_link.jsp"%>
<%@include file="../inc/list_css.jsp"%>
</head>
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
						<h1 class="hero-title">칼로리계산기</h1>
						<p class="small-caps mb30 text-white"></p>
						<p class="hero-text">칼로리를 계산해볼 수 있는 게시판</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- ./hero section end -->
	
	<!-- content start -->
	<div class="content" id="app1">
		<div class="container">
			<div class="row">
				<div class="col">
                    <h1>칼로리 계산기</h1>
                    <hr>
           <!-- 계산기 -->       
            <div class="row">
            	<div class="col" style="background:#c5f016; width:500px; height:200px; margin-left:110px" >
            	
            	</div>
            	<div class="col" style="font-size:40px; text-align:center; margin-top:70px; margin-left:70px">
            		<div class="row">
            			총 24444 kcal
            		</div>
            	</div>
            </div>  
            <br/>
            <!-- 계산기 끝 -->              
            
            <!-- 검색처리 -->
			<div class="row">
				<div class="col-lg-offset-4 col-lg-10" style="margin-left:100px">
					<div class="widget widget-search mb10">
						<form>
							<div class="input-group">
								<input type="text" class="form-control" placeholder="검색어를 입력하세요" name="keyword">
								<button class="btn btn-default" type="button"><i class="fa fa-search" id="bt_search"></i></button>
							</div>
						</form>
					</div>
				</div>				
			</div>
		
			<!-- 검색처리 끝 -->
			
      
			</div>
			</div>
			<!-- end of row -->
			<form id="form">
			
			<div class="row">
				<div class="col table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>음식명</th>
								<th>1회 제공량(g)</th>
								<th>칼로리</th>
								<th>탄수화물</th>
								<th>단백질</th>
								<th>지방</th>
								<th><input type="checkbox"></th>
							</tr>
						</thead>
						<tbody>
						<template v-for="food in searchList">
							<row :obj="food" >
						</template>
						</tbody>
					</table>
				</div>
				<!-- end of col -->
			</div>
			</form>
		</div>
	</div>
	<!-- /content end -->
		

    <!-- black footer_space -->
    <%@include file="../inc/footer_space.jsp" %>
    
    <!-- tiny footer -->
    <%@include file="../inc/footer_tiny.jsp" %>
    
	<%@include file="../inc/footer_link.jsp" %>

</body>
<script type="text/javascript">
	let app1;
	
	const row={
		template:`
			<tr>
				<th>{{food.name}}</th>
				<th>{{food.wt}}</th>
				<th>{{food.kcal}}</th>
				<th>{{food.car}}</th>
				<th>{{food.tien}}</th>
				<th>{{food.vince}}</th>
				<th><input type="checkbox"></th>
			</tr>	
		`,
		props:["obj"],
		data:function(){
			return{
				food:this.obj,
			}
		}
	}
	
	//API 불러오기
	function getFoodList(){
		$.ajax({
			url:"/rest/diet/kcal/list",
			type:"post",
			contentType:"application/json",
			success:function(result, status, xhr){
				let jsonList=[];
				for(let i=0; i<result.length; i++){
					let obj=result[i];
					//console.log("???????",obj);
					
					let json={};
					json['name']=obj.desc_KOR;
					json['wt']=obj.serving_WT;
					json['kcal']=obj.nutr_CONT1;
					json['car']=obj.nutr_CONT2;
					json['tien']=obj.nutr_CONT3;
					json['vince']=obj.nutr_CONT4;
					
					jsonList.push(json);
					//console.log("???????",json);
					
				}
				app1.foodList=jsonList;
			}
		});
	}
	
	//API 검색기능
	function getSearchAPI(foodName){
		let json={};
		let keyword=$("input[name='keyword']").val();
		//console.log(keyword);
		
		json['foodName']=keyword;
		
		$.ajax({
			url:"/rest/diet/kcal/search",
			type:"post",
			contentType:"application/json",
			data: JSON.stringify(json),
			success:function(result, status, xhr){
				//console.log(result);
				
				let jsonList=[];
				for(let i=0; i<result.length; i++){
					let obj=result[i];
					//console.log("???????",obj);
					
					let json={};
					json['name']=obj.DESC_KOR;
					json['wt']=obj.SERVING_WT;
					json['kcal']=obj.NUTR_CONT1;
					json['car']=obj.NUTR_CONT2;
					json['tien']=obj.NUTR_CONT3;
					json['vince']=obj.NUTR_CONT4;
					
					jsonList.push(json);
				}
				app1.searchList=jsonList;
			}
		});
	}


	function init(){
		app1=new Vue({
			el:"#app1",
			data:{
				foodList:[], //전체배열
				searchList:[] //검색어 보여질 배열
			},
			components:{
				row
			}
		});
	}

	$(function(){
		init();
		//getFoodList();	
		
		//검색버튼
		$("#bt_search").click(function(){
			getSearchAPI();
		});
	});

</script>
</html>
