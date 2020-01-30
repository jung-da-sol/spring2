package board.spring.mybatis;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardMybatisController {

	@Autowired
	BoardService service;
	
	@RequestMapping("/boardlist")  //url
	public ModelAndView getAllBoard(@RequestParam(required=false) String pagenum, 
									@RequestParam(required=false) String result) {
		ModelAndView mav = new ModelAndView();
		
		if(pagenum == null) {
			pagenum = "1";
		}
		int pagenum2 = Integer.parseInt(pagenum);
		int[] param = new int[2];
		param[0] = (pagenum2 - 1) * 5 + 1;
		param[1] = pagenum2 * 5;
		
		List<BoardVO> list = service.getAllBoard(param);
		
		mav.addObject("boardlist", list );  //jsp로 가져갈 것들
		mav.setViewName("boardlist");  //jsp
		
		return mav;
	}
	
	@RequestMapping(value="/boardwrite", method=RequestMethod.GET)
	public ModelAndView BoardWrite() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("writeform");
		return mav;
		
	}
	
	@RequestMapping(value="/boardwrite", method=RequestMethod.POST)
	public ModelAndView BoardWrite(BoardVO vo) throws IOException {
		ModelAndView mav = new ModelAndView();
		int result = service.namecheck(vo);
		if(result != 0) {
			int a = service.insertBoard(vo);
			if(a == 1) {
				mav.addObject("result", "게시물 작성 완료");
				mav.setViewName("boardwritesuccess");
			}
		} else {
			mav.setViewName("memberinsert");
		}
		
		return mav;
		
	}
	
	@RequestMapping("/boarddetail")
	public ModelAndView getBoardDetail(int seq) {
		ModelAndView mav = new ModelAndView();
		
		service.pluscnt(seq);
		BoardVO vo = service.getBoardDetail(seq);
		
		mav.addObject("detail", vo);
		mav.setViewName("boarddetail");
		return mav;
	}
	
	@RequestMapping(value="/update" , method=RequestMethod.GET)
	public ModelAndView updateview(int seq,BoardVO vo) {
		ModelAndView mav = new ModelAndView();

		BoardVO board =service.getBoardDetail(seq);
		
		mav.addObject("vo", board);
		mav.setViewName("update");
		return mav;
		
	}
	
	@RequestMapping(value="/update" , method=RequestMethod.POST)
	public ModelAndView update(BoardVO vo) {
		ModelAndView mav = new ModelAndView();
		
		service.update(vo);
		
		mav.setViewName("redirect:/boardlist");
		
		return mav;
	}
	
	@RequestMapping(value="/deleteboard")
	public String delete(int seq) {
		service.delete(seq);
		
		return "redirect:/boardlist";
	}

	
	
}
