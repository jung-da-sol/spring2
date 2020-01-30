package board.spring.mybatis;

import java.util.ArrayList;
import java.util.List;

public interface BoardService {
	
	public List<BoardVO> getAllBoard(int[] param);

	/* public List<BoardVO> Paging(int[] param); */
	
	public BoardVO getBoardDetail(int seq);
	
	public int insertBoard(BoardVO vo);
	
	public void update(BoardVO vo);
	
	public void delete(int seq);
	
	public void pluscnt(int seq);
	
	public int namecheck(BoardVO vo);

	
}
