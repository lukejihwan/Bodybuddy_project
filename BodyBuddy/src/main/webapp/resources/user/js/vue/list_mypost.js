const List = {
    template: `
		<div class="container">
			<div class="row">
				<div class="col">
					<h1>내가 쓴 글 목록</h1>
					<hr>
				</div>
			</div>
			<div class="row">
				<div class="col table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>분류</th>
								<th>제목</th>
								<th>등록일</th>
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
            if(post.table_name == "자유게시판"){
                location.href="/board/free_detail_view/"+post.idx;
            } else if(post.table_name == "QnA"){
                location.href="/board/qna_detail_view/"+post.idx;
            } else if(post.table_name == "익명게시판"){
                location.href="/board/counselling_detail_view/"+post.idx;
            }
        },
        getList(page){
            const vm = this;
            $.ajax({
                type: "get",
                url: "/mypage/mypost/list/"+page,
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
    props: ['list1'],
    data(){
        return{
            list: [],
            paging: [],
        }
    }
}