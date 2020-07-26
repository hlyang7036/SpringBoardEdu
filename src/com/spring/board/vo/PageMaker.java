package com.spring.board.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {
	
	private int totalCount;	// 전체 게시물의 수
	private int startPage;	// 현재 페이지 기준에서 시작 페이지
	private int endPage;	// 현재 페이지 기준에서 마지막 페이지
	private boolean prev;	// 이전으로 이동하는 버튼 생성 조건변수
	private boolean next;	// 다음으로 이동하는 버튼 생성 조건변수
	private int displayPageNum = 10;	// 화면 하단에 보여지는 페이지의 갯수
	private PageVo pageVo;	// 페이지 안에 페이징에 필요한 게시물들의 정보를 담거나 계산하는 클래스
	
	
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
		
		// 현재 페이지를 기준으로 표시되는 페이지의 마지막 번호가됨
		endPage = (int)(Math.ceil(pageVo.getPageNo() / (double)displayPageNum) * displayPageNum);
		// 만약  현재 페이지가 3페이지라면 (3/10올림)*10을해서 나오는 10이 페이지에 표시되는 마지막 페이지 번호가 된다
		
		// 현재 페이지를 기준으로 표시되는 페이지 번호의 처음이 됨 
		startPage = (endPage - displayPageNum) + 1;
		// 출력되는 마지막 페이지 번호가 10이니까 (10-10)+1의 결과인 1이 출력되는 페이지의 첫 번호가 된다
		 
		// 전체 게시글의 수를 보여주는 게시글 수로 나눠서 전체 페이지의 수를 구하는 변수 
		int tempPage = (int)(Math.ceil(totalCount / (double)pageVo.getPerPageNum()));
		 
		// 전체 페이지와 현재 페이지의 마지막을 비교하여 마지막 값을 보정한다
		if (endPage > tempPage) {
			endPage = tempPage;
		} 
		
		// startPage의 값이 1이면 false를 반환하여 이전 버튼이 비활성화 된다
		prev = startPage == 1 ? false : true;
		// endPage의 값에 보여지는 게시물의 수를 곱한값과 총 게시물 수를 비교하여 총게시물 수보다 보여지는 값이 더 많을 경우 
		// false를 반환하여 다음 버튼이 비활성화되고 반대라면 true값을 반환하여 다음 버튼이 활성화 된다.
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
				// 이렇게 만들어진 uri 페이지 이동시 uri 키값을  컨트롤러에서 받아서 처리하게 된다
				.build();
		
		return uriComponents.toUriString();
	}
	
	private String encoding(String[] searchKeys) {
		
		try {
			if (searchKeys == null || searchKeys.length == 0) {
				return "";
			}
			// URLEncoder.encode에서 배열이 들어올경우 String join으로 배열을 풀어줘야 인코딩이 제대로 된다
			return URLEncoder.encode(String.join(",", searchKeys), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
