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
	
	// Ÿ�԰˻�
	@Override
	public List<BoardVo> typeSearch(Map<String, Object> searchParam) throws Exception {
		
		return boardDao.typeSearch(searchParam);
	}
	
	// ȸ������
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
		
//		���ϰ��� 0�̸� ���̵� �������� ����
//		���ϰ��� 1�̸� ��й�ȣ�� �ٸ�
//		���ϰ��� 3�̸� ���̵�� ��й�ȣ�� ��ġ��
		
		int idChkResult = boardDao.idChk(userVo);
//		int pwChkResult = boardDao.idChk(userVo);
		int loginChk = 0;
		if (idChkResult == 0) {	// ���̵� ��ġ ���� �ʾ�����
			return loginChk = 0;
		} else {
			if (boardDao.login(userVo) == null) {	// ��й�ȣ�� ��ġ���� ����
				return loginChk = 1;
			}// ���̵� ��ġ���� ����
		return loginChk = 3;
		}
	}
}
