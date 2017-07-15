package sesoc.global.webTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sesoc.global.webTest.dao.BoardRepository;
import sesoc.global.webTest.service.BoardService;
import sesoc.global.webTest.util.FileService;
import sesoc.global.webTest.util.PageNavigator;
import sesoc.global.webTest.vo.Board;

@Controller
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);	
	private final String uploadPath = "/boardfile"; // 파일이 저장되는 HDD 공간
	
	@Autowired
	BoardRepository repo;
	
	/**
	 * 글 목록 요청    // UPDATE !!!!!!!! 제발 성공하자!!!!!!!!!!
	 * */
	@RequestMapping("/boardList")
	// <a href="boardList?currentPage=${}">
	// index(첫 페이지)에서 요청하면 xurrentPage가 없다. 그때는 초기값으로 1 세팅
	public String boardList(
			@RequestParam(value="currentPage", defaultValue="1")int currentPage, 
			@RequestParam(value="searchword", defaultValue="") String searchword,
			@RequestParam(value="searchtype", defaultValue="title") String searchtype
			, Model model)
	{
		//전체 글 개수
		int totalRecordCount = repo.getBoardCount(searchtype, searchword);
		System.out.println("[ currentPage ] : " + currentPage);
		PageNavigator navi = new PageNavigator(currentPage, totalRecordCount);
		
		List<Board> boardList = repo.findAll(searchtype, searchword, navi.getStartRecord(), navi.getCountPerPage());
		model.addAttribute("boardList", boardList);
		model.addAttribute("navi", navi);
		model.addAttribute("searchword", searchword);
		model.addAttribute("searchtype", searchtype);
		return "board/boardList";
	}//boardList
	
	@RequestMapping("/boardDetail")
	public String boardDetail(int boardnum, Model model,
			@RequestParam(value="currentPage", defaultValue="1") int currentPage,
			@RequestParam(value="searchword", defaultValue="") String searchword,
			@RequestParam(value="searchtype", defaultValue="title") String searchtype
			)
	{
		Board board = repo.findBoard(boardnum);

		if(board.getSavedfile() != null){
			File file = new File(board.getSavedfile());
			MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
			String mimetype = mimeTypesMap.getContentType(file);
			
			String contenttype = mimetype.substring(0, mimetype.indexOf("/"));
			System.out.println("[ contentType ] : " + contenttype);
			
			model.addAttribute("contenttype", contenttype);
		}//if
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("searchword", searchword);
		model.addAttribute("searchtype", searchtype);
		model.addAttribute("board", board);
		repo.incrementCount(boardnum);
		return "board/boardDetail";
	}//boardDetail
	
	@RequestMapping("/boardUpdate")
	public String boardUpdate(int boardnum, Model model){
		Board board = repo.findBoard(boardnum);
		model.addAttribute("board", board);
		return "board/boardUpdate";
	}//boardUpdate
	
	@RequestMapping(value="/boardUpdate", method=RequestMethod.POST)
	public String boardUpdate(Board board, RedirectAttributes rttr, MultipartFile upload){
		String fullPath = "";
		//
		if(!upload.isEmpty()){
			//
			Board del_b = repo.findBoard(board.getBoardnum());
			String del_file = uploadPath + "/" + del_b.getSavedfile();
			FileService.deleteFile(del_file);
			//
			fullPath = FileService.getSavedFileName(upload, uploadPath);
			System.out.println("[ update.fullPath ] : " + fullPath);
			board.setOriginalfile(upload.getOriginalFilename());
			board.setSavedfile(fullPath);
			FileService.updateFile(upload, uploadPath, fullPath);
		}//if
		
		Board existingBoard = repo.findBoard(board.getBoardnum());
		System.out.println("[ board ] " + board);
		
		
		
		repo.updateBoard(board);
		return "redirect:boardList";
	}//boardUpdate
	
	/**
	 * 글쓰기화면 요청
	 * */
	@RequestMapping("/boardWrite")
	public String boardWrite(){
		return "board/boardWrite";
	}//boardUpdate
	/**
	 * 글쓰기 처리
	 * */
	@RequestMapping(value="/boardWrite", method=RequestMethod.POST)
	public String boardWrite(Board board, Model model, HttpSession session,
			MultipartFile upload
			)
	{
		
//		System.out.println("[ upload file ] : " + upload);
//		System.out.println("[ upload file.getsize() ] : " + upload.getSize()); // 0 이면 파일 업로드를 안 한것.
//		System.out.println("[ upload file.empty() ] : " + upload.isEmpty()); // true면 파일 업로드를 안 한것.
		
		if(!upload.isEmpty()){
			String originalFileName = upload.getOriginalFilename();
			String savedFileName = FileService.saveFile(upload, uploadPath);

			board.setOriginalfile(originalFileName);
			board.setSavedfile(savedFileName);
		}//if
			
		String id = (String) session.getAttribute("loginId");
		board.setCustid(id);
		repo.insertBoard(board);
		return "redirect:boardList";
	}//boardUpdate
	
	@RequestMapping("/download")
	public String download(int boardnum, HttpServletResponse response){
		Board board = repo.findBoard(boardnum);
		String originalFile = board.getOriginalfile();
		String savedFile = board.getSavedfile();
		
		// 이미지 등과 같이 브라우저에 의해 직접 열리는 파일의 경우
		// 열리지 않고 다운받도록 조정할 수 있다.
		// 그것이 바로 Content-Disposition
		
		try {
			response.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(originalFile, "UTF-8"));
			//URLEncoder를 하는 이유는! 한글일 경우 꺠지지 않도록!
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String fullPath = uploadPath + "/" + savedFile;
		FileInputStream fileIn = null;
		ServletOutputStream sout = null;
		try {
			fileIn = new FileInputStream(fullPath);
			sout = response.getOutputStream();
			
			FileCopyUtils.copy(fileIn, sout);
			
			fileIn.close();
			sout.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}//download
	
	@RequestMapping(value="/boardDelete", method=RequestMethod.GET)
	public String boardDelete(int boardnum){
		Board board = repo.findBoard(boardnum);
		String fullPath = uploadPath + "/" + board.getSavedfile();
		FileService.deleteFile(fullPath);
		repo.delete(boardnum);
		
		return "redirect:boardList";
	}//boardUpdate
}//class
