package board.spring.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {

	@Autowired
	SqlSession session;
	
	public List<BoardVO> getAllBoard(int[] param) {
	List<BoardVO> list = session.selectList("board.allboard", param);
	return list;
	}
	
	public List<BoardVO> Paging(int[] param){
		List<BoardVO> list = session.selectList("board.paging");
		return list;
	}
	
	public int insertBoard(BoardVO vo) {
		return session.insert("board.insertboard", vo);
	}
	
	public BoardVO getBoardDetail(int seq) {
		return session.selectOne("board.detail", seq);
		
	}

	public void update(BoardVO vo) {
		session.selectOne("board.update", vo);
	}
	
	public void delete(int seq) {
		session.selectOne("board.delete", seq);
	}
	
	public void pluscnt(int seq) { 
		session.selectOne("board.pluscnt", seq); 
	}
	
	public int namecheck(BoardVO vo) {
		return session.selectOne("board.namecheck", vo);
	}
	 
}
