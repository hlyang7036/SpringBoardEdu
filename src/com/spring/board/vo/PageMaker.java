package com.spring.board.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {
	
	private int totalCount;	// ��ü �Խù��� ��
	private int startPage;	// ���� ������ ���ؿ��� ���� ������
	private int endPage;	// ���� ������ ���ؿ��� ������ ������
	private boolean prev;	// �������� �̵��ϴ� ��ư ���� ���Ǻ���
	private boolean next;	// �������� �̵��ϴ� ��ư ���� ���Ǻ���
	private int displayPageNum = 10;	// ȭ�� �ϴܿ� �������� �������� ����
	private PageVo pageVo;	// ������ �ȿ� ����¡�� �ʿ��� �Խù����� ������ ��ų� ����ϴ� Ŭ����
	
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}
	public int getStartPage() {
		return startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
	
	public boolean isPrev() {
		return prev;
	}
	
	public boolean isNext() {
		return next;
	}
	
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	
	public PageVo getPageVo() {
		return pageVo;
	}
	
	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo; 
	}
	
	public void calcData() {
		
		// ���� �������� �������� ǥ�õǴ� �������� ������ ��ȣ����
		endPage = (int)(Math.ceil(pageVo.getPageNo() / (double)displayPageNum) * displayPageNum);
		// ����  ���� �������� 3��������� (3/10�ø�)*10���ؼ� ������ 10�� �������� ǥ�õǴ� ������ ������ ��ȣ�� �ȴ�
		
		// ���� �������� �������� ǥ�õǴ� ������ ��ȣ�� ó���� �� 
		startPage = (endPage - displayPageNum) + 1;
		// ��µǴ� ������ ������ ��ȣ�� 10�̴ϱ� (10-10)+1�� ����� 1�� ��µǴ� �������� ù ��ȣ�� �ȴ�
		 
		// ��ü �Խñ��� ���� �����ִ� �Խñ� ���� ������ ��ü �������� ���� ���ϴ� ���� 
		int tempPage = (int)(Math.ceil(totalCount / (double)pageVo.getPerPageNum()));
		 
		// ��ü �������� ���� �������� �������� ���Ͽ� ������ ���� �����Ѵ�
		if (endPage > tempPage) {
			endPage = tempPage;
		} 
		
		// startPage�� ���� 1�̸� false�� ��ȯ�Ͽ� ���� ��ư�� ��Ȱ��ȭ �ȴ�
		prev = startPage == 1 ? false : true;
		// endPage�� ���� �������� �Խù��� ���� ���Ѱ��� �� �Խù� ���� ���Ͽ� �ѰԽù� ������ �������� ���� �� ���� ��� 
		// false�� ��ȯ�Ͽ� ���� ��ư�� ��Ȱ��ȭ�ǰ� �ݴ��� true���� ��ȯ�Ͽ� ���� ��ư�� Ȱ��ȭ �ȴ�.
		next = endPage * pageVo.getPerPageNum() >= totalCount ? false : true;
	
		
	}
	
	
	public String makeQuery(int pageNo) {
		UriComponents uriComponents = 
		UriComponentsBuilder.newInstance()
							.queryParam("pageNo", pageNo)
							.queryParam("perPageNum", pageVo.getPerPageNum())
							.build();
		return uriComponents.toString();
	}
	

	
	public String makeSearch(int pageNo) {

		UriComponents uriComponents = UriComponentsBuilder
				.newInstance()
				.queryParam("pageNo", pageNo)
				.queryParam("perPageNum", pageVo.getPerPageNum())
				.queryParam("searchKeys", encoding(((SearchCriteria)pageVo).getSearchKeys()))
				// �̷��� ������� uri ������ �̵��� uri Ű����  ��Ʈ�ѷ����� �޾Ƽ� ó���ϰ� �ȴ�
				.build();
		
		return uriComponents.toUriString();
	}
	
	private String encoding(String[] searchKeys) {
		
		try {
			if (searchKeys == null || searchKeys.length == 0) {
				return "";
			}
			// URLEncoder.encode���� �迭�� ���ð�� String join���� �迭�� Ǯ����� ���ڵ��� ����� �ȴ�
			return URLEncoder.encode(String.join(",", searchKeys), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
