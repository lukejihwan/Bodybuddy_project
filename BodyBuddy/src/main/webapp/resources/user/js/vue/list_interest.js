const List = {
    template: `
		<div class="container">
			<div class="row">
				<div class="col">
					<h1>내가 찜한 목록</h1>
					<hr>
				</div>
			</div>
			<div class="row">
				<div class="col table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>정보</th>
								<th>제목</th>
								<th>찜한 날짜</th>
							</tr>
						</thead>
						<tbody>
						    <tr v-for="(post, i) in list" :key="i" @click="showDetail(post)">
						        <td style="width: 15%">{{post.table_name}}</td>
						        <td style="width: 55%">{{post.title}}</td>
						        <td style="width: 20%">{{post.regdate.substring(0,16)}}</td>
                            </tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row mt-3">
				<div class="col text-center d-flex justify-content-center">
					<div class="st-pagination">
						  <ul class="pagination">
							<li v-if="paging.hasPrev"><a href="#" aria-label="이전" @click.prevent="getList(paging.firstPage-1)"><span aria-hidden="false">이전</span></a></li>
							<li v-for="i in paging.forNum" ref="li"><a href="#" @click.prevent="changeBt($event)">{{paging.firstPage+i-1}}</a></li>
							<li v-if="paging.hasNext"> <a href="#" aria-label="다음" @click.prevent="getList(paging.lastPage+1)"><span aria-hidden="true">다음</span></a></li>
						  </ul>
					</div>
				</div>
			</div>
		</div>
			`,
    methods: {
        showDetail(post){
            if(post.table_name == "운동루틴"){
                location.href="/exr/routine_detail/"+post.idx;
            } else if(post.table_name == "운동팁"){
                location.href="/exr/tip_detail//"+post.idx;
            } else if(post.table_name == "식단공유"){
                location.href="/diet/share_detail/"+post.idx;
            } else if(post.table_name == "식단팁"){
                location.href="/diet/tip_detail/"+post.idx;
            }
        },
        getList(page){
            const vm = this;
            $.ajax({
                type: "get",
                url: "/mypage/interest/"+page,
                success: function(data){
                    console.log(data);
                    vm.list = data.list;
                    vm.paging = data.paging;
                },
                error: (e)=>{
                    alert(e.responseJSON.msg);
                }
            })
        },
        changeBt(e){
            let buttons = this.$refs.li;
            for (let i=0; i<buttons.length; i++){
                buttons[i].className="";
            }
            e.target.parentElement.className="active"
            this.getList(e.target.innerText);
        }
    },
    created() {
        this.getList(1);
    },
    data(){
        return{
            list: [],
            paging: [],
        }
    }
}