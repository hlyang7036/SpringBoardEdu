package com.spring.board.vo;

public class PageVo {
	
	private int pageNo;	// ���� ������ ��ȣ
	private int perPageNum;	// �� �������� ��µǴ� �Խñ��� ��
	private int rowStart;
	private int rowEnd;
	
	// ������ �⺻�� �ʱ�ȭ ������
	public PageVo() {
		this.pageNo = 1;
		this.perPageNum = 10;
	}

	public int getPageNo() {
		return pageNo;
	}
	// ���� �������� 0���� ������ 1�� ���������� ����
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
	
	// ������ �Խñ۸���Ʈ�� ���� 0���� �۰ų� 100�� �ѱ�� 
	// ������ �Խñ� ����Ʈ�� ���� 10���� ����. 
	// �Խñ� ����Ʈ�� �Ѱ踦 ����
	public void setPerPageNum(int perPageNum) {
		if (perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
		}
		this.perPageNum = perPageNum;
	}
	
	// ���� ������ �Խñ��� ������ġ�� ����ϴ� �޼ҵ�
	public int getPageStart() {
		return (this.pageNo - 1) * perPageNum;
	} 
	
	// ���������� ���۵Ǵ� �Խñ��� ó���� ���ϴ� �޼ҵ�
	public int getRowStart() {
		rowStart = ((pageNo -1 ) * perPageNum) +1; 
		return rowStart;
	}
	
	// ���������� �Խñ��� �������� ���ϴ� �޼ҵ�
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
