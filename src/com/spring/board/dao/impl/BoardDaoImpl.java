package com.spring.board.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dao.BoardDao;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.PageVo;
import com.spring.board.vo.SearchCriteria;
import com.spring.board.vo.UserVo;

@Repository
public class BoardDaoImpl implements BoardDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public String selectTest() throws Exception {
		// TODO Auto-generated method stub
		
		String a = sqlSession.selectOne("board.boardList");
		
		return a;
	}
	/**
	 * 
	 * */
	@Override
	public List<BoardVo> selectBoardList(SearchCriteria scri) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("board.boardList",scri);
	}
	
	@Override
	public int selectBoardCnt(SearchCriteria scri) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.boardTotal", scri);
	}
	
	@Override
	public BoardVo selectBoard(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.boardView", boardVo);
	}
	
	@Override
	public int boardInsert(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("board.boardInsert", boardVo);
	}
	
	// boardDelete
	@Override
	public int boardDelete(BoardVo boardVo) throws Exception {
		
		return sqlSession.delete("board.boardDelete", boardVo);
	}
	
	// boardUpdate
	@Override
	public int boardUpdate(BoardVo boardVo) throws Exception {
		
		return sqlSession.update("board.boardUpdate", boardVo);
	}
	
	// codeSelectMenu
	@Override
	public List<CodeVo> codeSelectMenu() throws Exception {
		
		return sqlSession.selectList("board.codeSelectMenu");
	}
	
	@Override
	public List<BoardVo> typeSearch(Map<String, Object> searchParam) {
		
		
		return sqlSession.selectList("board.searchType", searchParam);
	}
	@Override
	public List<CodeVo> codeSelectPhone() throws Exception {
		
		
		return sqlSession.selectList("board.codeSelectPhone");
	}
	@Override
	public int signup(UserVo userVo) throws Exception {
		
		return sqlSession.insert("board.signup", userVo);
	}
	
	// idChk
	@Override
	public int idChk(UserVo userVo) throws Exception {
		return sqlSession.selectOne("board.idChk", userVo);
	}
	
	// login
	@Override
	public UserVo login(UserVo userVo) throws Exception {
		
		return sqlSession.selectOne("board.login", userVo);
	}
	
	
}
