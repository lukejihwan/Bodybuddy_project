const Askview = {
    template:`
				<!-- 문의게시판 시작 -->
				<div class="container">
					<div class="row">
						<div class="col">
							<h1>1:1 문의내역</h1> - 궁금한 점을 물어보세요
							<hr>
						</div>
					</div>
					<!-- end of row -->
					<div class="row">
						<div class="col table-responsive">
							<table class="table table-hover">
								<thead>
									<tr>
										<th>No</th>
										<th>제목</th>
										<th>등록일</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
						<!-- end of col -->
					</div>
					<!-- end of row -->
					<div class="row">
						<div class="col text-right">
							<button type="button" class="btn btn-default" id="bt_ask">문의하기</button>
						</div>
					</div>
					<!-- end of row -->
					<div class="row mt-3">
						<div class="col text-center d-flex justify-content-center">
							<div class="st-pagination">
								<!--st-pagination-->
								  <ul class="pagination">
									<li> <a href="#" aria-label="previous"><span aria-hidden="true">previous</span></a></li>
									<li class="active"><a href="#">1</a></li>
									<li><a href="#">2</a></li>
									<li><a href="#">3</a></li>
									<li> <a href="#" aria-label="Next"><span aria-hidden="true">next</span></a></li>
								  </ul>
								<!--/st-pagination-->
							</div>
						</div>
					</div>
					<!-- end of row -->
				</div>
				<!-- /문의게시판 끝 -->
			`
}

