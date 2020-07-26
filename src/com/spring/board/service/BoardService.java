package com.spring.board.service;

import java.util.List;
import java.util.Map;

import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.PageVo;
import com.spring.board.vo.SearchCriteria;
import com.spring.board.vo.UserVo;

public interface BoardService {

	public String selectTest() throws Exception;

	public List<BoardVo> SelectBoardList(SearchCriteria scri) throws Exception;

	public BoardVo selectBoard(String boardType, int boardNum) throws Exception;
	
	// totalCount
	public int selectBoardCnt(SearchCriteria scri) throws Exception;

	public int boardInsert(BoardVo boardVo) throws Exception;
	
	// boardDelete
	public int boardDelete(BoardVo boardVo) throws Exception;
	
	// boardUpdate
	public int boardUpdate(BoardVo boardVo) throws Exception;
	
	// codeSelectMenu
	public List<CodeVo> codeSelectMenu() throws Exception;

	// codeSelectPhone
	public List<CodeVo> codeSelectPhone() throws Exception;
	
	// type °Ë»ö
	public List<BoardVo> typeSearch(Map<String, Object> searchParam) throws Exception;
	
	// singup
	public int signup(UserVo userVo) throws Exception;
	
	// idChk
	public int idChk(UserVo userVo) throws Exception;
	
	// login
	public UserVo login(UserVo userVo) throws Exception;
	
	// loginChk
	public int loginChk(UserVo userVo) throws Exception;
}
