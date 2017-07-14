package sesoc.global.webTest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sesoc.global.webTest.dao.BoardRepository;
import sesoc.global.webTest.vo.Board;

@Service  // Controller 와 Repository 사이에 위치
public class BoardService {
	
	@Autowired
	BoardRepository repo;
	
	public List<Board> boardList(String searchtype, String searchword, int startRecord, int countPerPage){
		List<Board> list = null;
		
		repo.findAll(searchtype, searchword, startRecord, countPerPage);
		
		return list;
	}//boardList
}//class
