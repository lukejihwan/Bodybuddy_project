class Pager{
	//멤버변수를  반드시 constructor() 안에 두어야 함
	constructor(){
		this.totalRecord=0; //총 레코드 수 
		this.pageSize=20;//페이지당 보여질 레코드 수
		this.totalPage=0; //총페이지 수
		this.blockSize=10;//블럭당 보여질 페이지 수 
		this.currentPage=1;//현재 페이지
		this.firstPage=0; //블럭당  반복문의 시작 페이지
		this.lastPage=0;	 //블럭당 반복문의 마지막 페이지 
		this.curPos=0; //페이지당 시작 인덱스(리스트 or  배열내)
		this.num=0; //페이지당 시작 번호
	}
	init(list, currentPage){
		this.totalRecord=list.length;
		this.totalPage = Math.ceil(this.totalRecord/this.pageSize);
		this.currentPage=currentPage;
		this.firstPage= this.currentPage - (this.currentPage-1)%this.blockSize;
		this.lastPage = this.firstPage + (this.blockSize-1);
		this.curPos = (this.currentPage-1)*this.pageSize;
		this.num=this.totalRecord - this.curPos;		
	}	
}