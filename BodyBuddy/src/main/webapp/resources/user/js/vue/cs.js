const List = {
    template: `
		<div class="container">
			<div class="row">
				<div class="col">
					<h1>{{data.title}}</h1>
					<hr>
				</div>
			</div>
			<div class="row">
				<div class="col table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>No</th>
								<th>제목</th>
								<th>등록일</th>
								<th>상태</th>
							</tr>
						</thead>
						<tbody>
						    <tr v-for="(post, i) in list" :key="i">
						        <td>{{paging.no-i}}</td>
						        <td @click="showDetail(post)">{{post.title}}</td>
						        <td>{{post.regdate.substring(0,16)}}</td>
						        <td>{{post.status}}</td>
                            </tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row">
				<div class="col text-right">
					<button type="button" class="btn btn-default" @click="write(data.type)">글쓰기</button>
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
        write: (type) => {
            showForm()
            if (type == "ask") {
                askApp.formdata = {type: "ask", title: "1:1 문의하기", url: "/mypage/ask", method:"post"}
            } else if (type == "report") {
                askApp.formdata = {type: "report", title: "신고하기", url: "/mypage/report", method:"post"}
            }
        },
        showDetail(post){
            askApp.detail_data.type=this.data.type;
            askApp.detail_data.dto=post;
            showDetail();
        },
        getList(page){
            console.log(page);
            const vm = this;
            $.ajax({
                type: "get",
                url: "/mypage/" + vm.data.type + "/list/"+ page,
                success: function(data){
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
        //this.$refs.li[0].className="active";
        console.log(this.$refs.li);
    },
    props: ['data'],
    data(){
    	return{
            list: [],
            paging: [],
    	}
    }
}

const Writeform = {
    template: `
        <div class="container">
			<div class="row">
				<div class="col">
                    <h1>{{data.title}}</a></h1>
                    <hr>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<form id="form1">
						<div class="form-group">
							<input v-if="data.method=='put'" type="text" class="form-control" name="title" :value="data.dto.title" required>
							<input v-if="data.method=='post'" type="text" class="form-control" name="title" placeholder="제목 입력" required>
						</div>
						<div class="form-group">
							<textarea v-if="data.method=='put'" id="summernote" name="content" rows="10">{{data.dto.content}}</textarea>
							<textarea v-if="data.method=='post'" id="summernote" name="content" rows="10" placeholder="내용 입력"></textarea>
						</div>
						<div class="form-group">
							<button type="button" class="btn btn-default pull-right" @click="regist(data)">등록</button>
							<button type="button" class="btn btn-dark pull-right" @click="cancel">취소</button>
						</div>
						<input v-if="data.method=='put'" type="hidden" name="_method" value="PUT"/>
						<input v-if="data.method=='put'" type="hidden" name="ask_idx" :value="data.dto.ask_idx"/>
						<input v-if="data.method=='put'" type="hidden" name="report_idx" :value="data.dto.report_idx"/>
					</form>
				</div>
			</div>
		</div>
    `,
    props: ["data"],
    methods: {
        cancel: () => {
            showList();
        },
        regist: (data) => {
            let formData = $("#form1").serialize();
            $.ajax({
                type: "post",
                url: data.url,
                data: formData,
                success: () => {
                    //getList(data.type, 1);
                    alert("등록 완료");
                    showList();
                },
                error:(e)=>{
                    alert(e.responseJSON.msg);
                }
            })
        }
    },
    mounted() {
        $('#summernote').summernote({
            minHeight: 400,
            maximumImageFileSize: 5 * 1024 * 1024, //5MB 제한
            placeholder: "내용을 입력하세요",
            callbacks: {
                onImageUploadError: function (msg) {
                    alert("한번에 올릴 수 있는 최대 파일 크기는 5MB 입니다");
                }
            }
        });
    }
}

const Detail = {
    template: `
    <div class="container">
        <h1>문의 / 신고</h1>
        <hr>
        <div class="row">
            <div class="col">
                <h3>{{data.dto.title}}</h3><br/>
                <span>{{data.dto.member.nickname}} | {{data.dto.regdate.substring(0,16)}}</span>
                <hr>
            </div>
        </div>
        <!-- end of row -->
        <br/>
        <div class="row mb-5">
            <div class="col-md-12 mb-4">
                 <span v-html="data.dto.content"></span>
            </div>
        </div>
        <hr>
        <!-- end of row -->
        <button class="btn btn-primary" @click="toList">목록</button>
        <button type="button" class="btn btn-danger pull-right writer-check" @click="del">삭제</button>
        <button type="button" class="btn btn-default pull-right writer-check" style="margin-right: 10px" @click="edit(data.type)">수정</button>
        <hr>
        <br/>
    </div>
    `,
    props: ['data'],
    methods: {
        del(){
            if(!confirm("정말 삭제할까요?")) return;
            $.ajax({
                type: "delete",
                url: "/mypage/"+this.data.type,
                contentType:"application/json;charset=utf-8",
                processData:false,
                data: JSON.stringify(this.data.dto),
                success: (result)=>{
                    showList();
                },
                error: (e)=>{
                	console.log(e);
                    alert(e.responseJSON.msg);
                }
            })
        },
        edit(type){
            showForm();
            if (type == "ask") {
                askApp.formdata = {type: "ask", title: "문의글 수정", url: "/mypage/ask", dto: this.data.dto, method: "put"}
            } else if (type == "report") {
                askApp.formdata = {type: "report", title: "신고글 수정", url: "/mypage/report", dto: this.data.dto, method: "put"}
            }
        },
        toList(){
            showList();
        }
    }
}

const showList=()=>{
    askApp.showList = true;
    askApp.showForm = false;
    askApp.showDetail = false;
}
const showForm=()=>{
    askApp.showList = false;
    askApp.showForm = true;
    askApp.showDetail = false;
}
const showDetail=()=>{
    askApp.showList = false;
    askApp.showForm = false;
    askApp.showDetail = true;
}
