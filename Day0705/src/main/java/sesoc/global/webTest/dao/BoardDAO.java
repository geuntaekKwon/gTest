package sesoc.global.webTest.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import sesoc.global.webTest.vo.Board;

public interface BoardDAO {
	public List<Board> select(Map<String, String> search, RowBounds rb);
	public int insert(Board board);
	public int delete(int boardnum);
	public int update(Board board);
	public Board selectOne(int boardnum);
	public int getBoardCount(Map<String, String> map); // 전체 글 갯수
	public int incrementCount(int boardnum);
}
