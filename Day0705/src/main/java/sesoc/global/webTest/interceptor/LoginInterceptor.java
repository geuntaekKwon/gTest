package sesoc.global.webTest.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// session 정보 얻어오기
		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("loginId");
		
		String ctx = request.getContextPath();
		
		// 로그인을 하지 않은 경우
		if(loginId == null){
			// 로그인 화면으로 redirect;
			response.sendRedirect("login");
			return false;
		}//if
		
		
		return super.preHandle(request, response, handler); // it returns 'true' value;
	}//preHandle
	
}//class
