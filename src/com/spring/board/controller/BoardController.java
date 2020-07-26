package com.spring.board.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.board.HomeController;
import com.spring.board.service.BoardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.PageMaker;
import com.spring.board.vo.PageVo;
import com.spring.board.vo.SearchCriteria;
import com.spring.board.vo.UserVo;
import com.spring.common.CommonUtil;

import net.sf.json.JSONArray;

@Controller
public class BoardController {
	
	@Autowired 
	BoardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	// boardList
	@RequestMapping(value = "/board/boardList.do", method = RequestMethod.GET)
	public String boardList(Locale locale, Model model
			,@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo
			,@RequestParam(value = "searchKeys", required = false) String[] searchKeys
			,PageVo pageVo
			,@ModelAttribute("searchKeys") SearchCriteria scri
				) throws Exception{
		
		System.out.println("pageVo.toString() : "+pageVo.toString());
		
	
		List<BoardVo> boardList = new ArrayList<BoardVo>();
//		SearchCriteria scri = new SearchCriteria();
		scri.setSearchKeys(searchKeys);
		scri.setPageNo(pageNo);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setPageVo(scri);
		pageMaker.setTotalCount(boardService.selectBoardCnt(scri));
		
		System.out.println("scri.getPageNo() : "+scri.getPageNo());
//		System.out.println("scri.getPageNo() : "+scri.getSearchKeys().toString());
		int totalCnt = 0;
		
		List<CodeVo> codeVoList = new ArrayList<CodeVo>();
		codeVoList = boardService.codeSelectMenu();
		boardList = boardService.SelectBoardList(scri);
		totalCnt = boardService.selectBoardCnt(scri);
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("pageNo", scri.getPageNo());
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("codeList", codeVoList);
		model.addAttribute("selectChk", JSONArray.fromObject(scri.getSearchKeys()));
		return "board/boardList";
	}
	
	// 글쓰기 상세보기 이동
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardView.do", method = RequestMethod.GET)
	public String boardView(Locale locale, Model model
			,@PathVariable("boardType")String boardType
			,@PathVariable("boardNum")int boardNum
			,PageVo pageVo
//			,@RequestParam("pageNo") int pageNo
				) throws Exception{
		
		logger.info("boardView chk");
//		int page = pageNo;
//		int totalCnt = 0;
		
//		if(pageVo.getPageNo() == 0){
//			pageVo.setPageNo(page);;
//		}
		
		BoardVo boardVo = new BoardVo();
		List<CodeVo> codeVoList = new ArrayList<CodeVo>();
		
		
		boardVo = boardService.selectBoard(boardType,boardNum);
		codeVoList = boardService.codeSelectMenu();
		
		for (CodeVo codeVo : codeVoList) {
			System.out.println(codeVo.toString());
		}
		
		model.addAttribute("boardType", boardType);
		model.addAttribute("boardNum", boardNum);
		model.addAttribute("board", boardVo);
		model.addAttribute("pageNo", pageVo.getPageNo());
		model.addAttribute("codeList", codeVoList);
		return "board/boardView";
	}
	
	// 수정 페이지 이동
	@RequestMapping(value = "/board/boardModify.do", method = RequestMethod.GET)
	public String boardModify(Locale locale, Model model
			, @RequestParam("boardType")String boardType
			, @RequestParam("boardNum")int boardNum
			, @RequestParam("pageNo") int pageNo) throws Exception{
		logger.info("boardModify.do");
		
		
		
		BoardVo boardVo = new BoardVo();
		boardVo = boardService.selectBoard(boardType,boardNum);
		
		model.addAttribute("boardType", boardVo.getBoardType());
		model.addAttribute("boardNum", boardVo.getBoardNum());
		model.addAttribute("board", boardVo);
		model.addAttribute("pageNo", pageNo);
		
		return "board/boardModify";
	}	
	
	// 글쓰기 페이지 이동
	@RequestMapping(value = "/board/boardWrite.do", method = RequestMethod.GET)
	public String boardWrite(Locale locale, Model model
//			,@RequestParam("pageNo") int pageNo
			,PageVo pageVo		
			) throws Exception{
//		System.out.println(pageNo);
		
		logger.info("board/boardWrite chk");
		
		List<CodeVo> codeVoList = new ArrayList<CodeVo>();
		
		
		codeVoList = boardService.codeSelectMenu();
		
		for (CodeVo codeVo : codeVoList) {
			System.out.println(codeVo.toString());
		}
		
		model.addAttribute("pageNo", pageVo.getPageNo());
		model.addAttribute("codeList", codeVoList);
		model.addAttribute("codeListJson", JSONArray.fromObject(codeVoList));
//		model.addAttribute(codeVoList);
		return "board/boardWrite";
	}
	

		
	
	// 글 작성 액션
	@RequestMapping(value = "/board/boardWriteAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardWriteAction(Locale locale
			,BoardVo boardVo
			,CodeVo codeVo
			) throws Exception{
		
		System.out.println("boardVo.toString() : "+boardVo.toString());
		System.out.println("boardVo.toString() : "+codeVo.toString());
		boardVo.setBoardType(codeVo.getCodeId());
		
		HashMap<String, String> result = new HashMap<String, String>();
//		CommonUtil commonUtil = new CommonUtil();
		
		int resultCnt = boardService.boardInsert(boardVo);
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		String callbackMsg = CommonUtil.getJsonCallBackString(result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
	}
	
	// 글 수정 액션
	@RequestMapping(value = "/board/boardModifyAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardModifyAction(Locale locale, BoardVo boardVo) throws Exception{
		logger.info("boardModify.do");
		
		System.out.println("boardVo.toString() : "+boardVo.toString());
		HashMap<String, String> result = new HashMap<String, String>();
		

		int resultCnt = boardService.boardUpdate(boardVo);
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		String callbackMsg = CommonUtil.getJsonCallBackString(result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
	}	
	
	
	// 글 삭제 액션
	@ResponseBody
	@RequestMapping(value = "/board/boardDeleteAction.do", method = RequestMethod.POST)
	public String boardDelete(Locale locale, BoardVo BoardVo, PageVo pageVo) throws Exception {
		
		logger.info("boardDeleteAction.do");
		
		HashMap<String, String> result = new HashMap<String, String>();
		
		int resultCnt=  boardService.boardDelete(BoardVo);
		
		int page = 1;
//		int totalCnt = 0;
		
		if(pageVo.getPageNo() == 0){
			pageVo.setPageNo(page);;
		}
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		String callbackMsg = CommonUtil.getJsonCallBackString(result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
	}
	
	/*
	 * // type별 검색 액션
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "/board/boardSearchAction.do", method =
	 * RequestMethod.GET) public List<BoardVo> boardSearch(SearchCriteria scri)
	 * throws Exception { System.out.println("searchKeys : "+
	 * scri.getSearchKeys().length);
	 * 
	 * // Map<String, Object> searchParam = new HashMap<String, Object>();
	 * List<BoardVo> boardList = new ArrayList<BoardVo>();
	 * 
	 * // searchParam.put("searchKeys", scri.getSearchKeys()); boardList =
	 * boardService.SelectBoardList(scri);
	 * 
	 * for (int i = 0; i < scri.getSearchKeys().length; i++) {
	 * System.out.println(scri.getSearchKeys()[i]); }
	 * 
	 * return boardList; }
	 */
	
}
