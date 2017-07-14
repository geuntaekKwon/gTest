package sesoc.global.webTest.exception;


import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // 컨트롤러에서 에러를 잡겠다는 듰
public class WebExceptionHandler {

	
	@ExceptionHandler(Exception.class)
	public String errorHandler(Model model, Exception e){
		model.addAttribute("errormsg", e.getMessage());
		return "/error";
	}//errorHandler
	
	
}//class
