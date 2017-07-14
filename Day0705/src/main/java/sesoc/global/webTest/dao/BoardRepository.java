package sesoc.global.webTest.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sesoc.global.webTest.util.PageNavigator;
import sesoc.global.webTest.vo.Board;

@Repository
public class BoardRepository {

	@Autowired
	SqlSession sqlSession;
	
	public int getBoardCount(String searchtype, String searchword){
		int result = 0;
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchtype", searchtype);
		map.put("searchword", searchword);
		
		result = dao.getBoardCount(map);
		
		return result;
	}//getBoardCount
	
	public List<Board> findAll(String searchtype, String searchword, int startRecord, int countPerPage){
		List<Board> result = null;
		
		//
		RowBounds rb = new RowBounds(startRecord, countPerPage); // 동적 SQL 포인터 역할
		//
		
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		//
		Map<String, String> search = new HashMap<String, String>();
		//
		
		search.put("searchtype", searchtype);
		search.put("searchword", searchword);
		
		result = dao.select(search, rb); //rb는 세팅만 해주고 넘겨주기만 하면 알아서 작동한다.
		
		return result;
	}//select
	
	public Board findBoard(int boardnum){
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		Board result = dao.selectOne(boardnum);
		
		return result;
	}//findBoard
	
	public int insertBoard(Board board){
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		int result = dao.insert(board);
		return result;
	}//insert
	
	public int updateBoard(Board board){
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		int result = dao.update(board);
		return result;
	}//updateBoard
	
	public int delete(int boardnum){
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		int result = dao.delete(boardnum);
		
		return result;
	}//findBoard
	
	public int incrementCount(int boardnum){
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		int result = dao.incrementCount(boardnum);
		
		return result;
	}//findBoard
	
}//class
