package com.iu.base.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Pager {	
	
	//page 번호 담을 변수
	private Long page;
	
	//한 페이지에 보여줄 글의 갯수
	private Long perPage;
	
	//한 블럭당 출력할 번호 갯수
	private Long perBlock;
	
	//시작 index 번호
	private Long startRow;

	private Long totalPage;
	
	//시작 끝 page 번호
	private Long startNum;
	private Long lastNum;
	
	//이전 다음 블럭 유무
	private boolean pre; //false 이전 X, true O
	private boolean next; //false 다음 X, true O
	
	//검색 종류
	private String kind;
	//검색어
	private String search;
	
	//page 계산하는 메서드
	public void makeNum(Long totalCount) {
		//1. 전체 글의 갯수 받아옴
		
		//2. 전체 글의 갯수로 전체 페이지 갯수 구하기
		this.totalPage = totalCount / this.getPerPage();
		if(totalCount % this.getPerPage()!=0) {
			totalCount++;
		}
		
		//3. 전체 페이지로 전체 블럭의 갯수 구하기
		Long totalBlock = totalPage / this.getPerBlock();
		if(totalPage % this.getPerBlock()!=0) {
			totalBlock++;
		}
		
		//4. page 번호로 현재 블럭 번호 구하기
		Long curBlock = this.getPage() / this.getPerBlock();
		if(this.getPage() % this.getPerBlock() != 0) {
			curBlock++;
		}
		
		//5. 현재 블럭 번호로 시작 번호와 끝번호 구하기
		this.startNum = (curBlock-1)*(this.getPerBlock())+1;
		this.lastNum = this.getPerBlock()*curBlock;
		
		//6. 현재 블럭 번호가 마지막 블럭 이라면 끝번호는 전체 페이지 번호
		if(curBlock == totalBlock) {
			lastNum = totalPage;
		}
		
		//7. 이전블럭, 다음 블럭 가능한지 유무
		if(curBlock == 1) {
			this.pre=true;
		}
		
		if(curBlock != totalBlock) {
			this.next=true;
		}
	}
	
	//시작 index 번호를 계산하는 메서드
	public void makeStartRow() {
		//page = 1, startRow =0
		//page = 2, startRow = 10
		//page = 3, startRow = 20
		this.startRow= (this.getPage()-1) * this.getPerPage();
	}
	
	public Long getPage() {
		if(this.page == null || this.page==0 ) {
			this.page = 1L;
		}
		return this.page;
	}
	
	public Long getPerPage() {
		if(this.perPage == null || this.perPage==0 ) {
			this.perPage = 10L;
		}
		return this.perPage;
	}
	
	public String getSearch() {
		if(this.search == null) {
			this.search="";
		}
		return this.search; //"%"+search+"%";
	}
	
	public Long getPerBlock() {
		if(this.perBlock == null || this.perBlock<1) {
			this.perBlock=5L;
		}
		
		return perBlock;
	}
	
}
