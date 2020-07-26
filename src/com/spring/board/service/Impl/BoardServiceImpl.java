package com.spring.board.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.dao.BoardDao;
import com.spring.board.service.BoardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.PageVo;
import com.spring.board.vo.SearchCriteria;
import com.spring.board.vo.UserVo;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	BoardDao boardDao;
	
	@Override
	public String selectTest() throws Exception {
		// TODO Auto-generated method stub
		return boardDao.selectTest();
	}
	
	@Override
	public List<BoardVo> SelectBoardList(SearchCriteria scri) throws Exception {
		// TODO Auto-generated method stub
		
		return boardDao.selectBoardList(scri);
	}
	
	@Override
	public int selectBoardCnt(SearchCriteria scri) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.selectBoardCnt(scri);
	}
	
	@Override
	public BoardVo selectBoard(String boardType, int boardNum) throws Exception {
		// TODO Auto-generated method stub
		BoardVo boardVo = new BoardVo();
		
		boardVo.setBoardType(boardType);
		boardVo.setBoardNum(boardNum);
		
		return boardDao.selectBoard(boardVo);
	}
	
	// insert
	@Override
	public int boardInsert(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("service boardVo.getCreator() : "+boardVo.getCreator());
		String[] codeIndex = boardVo.getBoardType().split(",");
		String[] arrayTitle = boardVo.getBoardTitle().split(",");
		String[] arrayComment = boardVo.getBoardComment().split(",");
//		String[] arrayWriter = boardVo.getCreator().split(",");
		int result = 0;
		for (int i = 0; i < codeIndex.length; i++) {
			BoardVo splitBoardVo = new BoardVo();
			System.out.println("arrayTitle[i] : "+arrayTitle.length);
			splitBoardVo.setBoardTitle(arrayTitle[i]); 
			splitBoardVo.setBoardComment(arrayComment[i]);
			splitBoardVo.setCreator(boardVo.getCreator());
			splitBoardVo.setBoardType(codeIndex[i]);
			
			result = boardDao.boardInsert(splitBoardVo);
			
			
		}
		return result;
	}
	
	// boardDelete
	@Override
	public int boardDelete(BoardVo boardVo) throws Exception {
		
		return boardDao.boardDelete(boardVo);
	}
	
	// boardUpdate
	@Override
	public int boardUpdate(BoardVo boardVo) throws Exception {
		
		return boardDao.boardUpdate(boardVo);
	}
	
	// codeSelectMenu
	@Override
	public List<CodeVo> codeSelectMenu() throws Exception {
		
		
		return boardDao.codeSelectMenu();
	}
	
	// codeSelectPhone
	@Override
	public List<CodeVo> codeSelectPhone() throws Exception {
		
		
		return boardDao.codeSelectPhone();
	}	
	
	// 타입검색
	@Override
	public List<BoardVo> typeSearch(Map<String, Object> searchParam) throws Exception {
		
		return boardDao.typeSearch(searchParam);
	}
	
	// 회원가입
	@Override
	public int signup(UserVo userVo) throws Exception {
		
		String userPw = userVo.getUserPw();
		userVo.setUserPw(userPw.split(",")[0]);
		
		
		return boardDao.signup(userVo);
	}
	
	// idChk
	@Override
	public int idChk(UserVo userVo) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.idChk(userVo);
	}
	
	// login
	@Override
	public UserVo login(UserVo userVo) throws Exception {
		
		return boardDao.login(userVo);
	}

	@Override
	public int loginChk(UserVo userVo) throws Exception {
		
//		리턴값이 0이면 아이디가 존재하지 않음
//		리턴값이 1이면 비밀번호가 다름
//		리턴값이 3이면 아이디와 비밀번호가 일치함
		
		int idChkResult = boardDao.idChk(userVo);
//		int pwChkResult = boardDao.idChk(userVo);
		int loginChk = 0;
		if (idChkResult == 0) {	// 아이디가 일치 하지 않았을때
			return loginChk = 0;
		} else {
			if (boardDao.login(userVo) == null) {	// 비밀번호가 일치하지 않음
				return loginChk = 1;
			}// 아이디가 일치하지 않음
		return loginChk = 3;
		}
	}
}
