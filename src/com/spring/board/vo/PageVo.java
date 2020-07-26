package com.spring.board.vo;

public class PageVo {
	
	private int pageNo;	// 현재 페이지 번호
	private int perPageNum;	// 한 페이지당 출력되는 게시글의 수
	private int rowStart;
	private int rowEnd;
	
	// 페이지 기본값 초기화 생성자
	public PageVo() {
		this.pageNo = 1;
		this.perPageNum = 10;
	}

	public int getPageNo() {
		return pageNo;
	}
	// 현재 페이지가 0보다 작으면 1로 페이지값을 설정
	public void setPageNo(int pageNo) {	
		if (pageNo <= 0) {
			this.pageNo = 1;
			return;
		}
		this.pageNo = pageNo;
	}

	public int getPerPageNum() {
		return perPageNum;
	}
	
	// 보여줄 게시글리스트의 수가 0보다 작거나 100을 넘기면 
	// 보여줄 게시글 리스트의 수를 10개로 설정. 
	// 게시글 리스트의 한계를 설정
	public void setPerPageNum(int perPageNum) {
		if (perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
		}
		this.perPageNum = perPageNum;
	}
	
	// 현재 페이지 게시글의 시작위치를 계산하는 메소드
	public int getPageStart() {
		return (this.pageNo - 1) * perPageNum;
	} 
	
	// 페이지에서 시작되는 게시글의 처음을 구하는 메소드
	public int getRowStart() {
		rowStart = ((pageNo -1 ) * perPageNum) +1; 
		return rowStart;
	}
	
	// 페이지에서 게시글의 마지막을 구하는 메소드
	public int getRowEnd() {
		rowEnd = rowStart + perPageNum -1;
		return rowEnd;
	}

	@Override
	public String toString() {
		return "PageVo [pageNo=" + pageNo + ", perPageNum=" + perPageNum + ", rowStart=" + rowStart + ", rowEnd="
				+ rowEnd + "]";
	}

	
	
	
}
