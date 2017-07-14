package sesoc.global.webTest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sesoc.global.webTest.dao.CustomerRepository;
import sesoc.global.webTest.vo.Customer;

@Controller
public class CustomerController {
	@Autowired
	CustomerRepository repo;
	
	String message = "";
	
	@RequestMapping(value="/joinForm", method=RequestMethod.GET)
	public String join(){
		return "customer/joinForm";
	}//join
	
	@RequestMapping(value="/joinForm", method=RequestMethod.POST)
	public String join(Customer customer){
		repo.createCustomer(customer);
		return "index";
	}//join
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login(){
		
		return "customer/loginForm";
	}//login
	
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(String custid, String password, HttpSession session, Model model){
		Customer customer = repo.findCustomer(custid, password);
		
		if(customer == null){
			message = "로그인 실패";
			session.setAttribute("message", message);
			return "customer/message";
		}else{
			session.setAttribute("loginId", customer.getCustid());
			session.setAttribute("password", password);
			session.setAttribute("name", customer.getName());
			session.setAttribute("message", message);
			return "redirect:/";
		}
		
	}//login
	
	@RequestMapping(value="logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/";
	}//login
	
	@RequestMapping(value="update")
	public String update(HttpSession session, Model model){
		String id = (String) session.getAttribute("loginId");
		String password = (String) session.getAttribute("password");
		Customer customer = repo.findCustomer(id, password);
		
		model.addAttribute("customer", customer);
		return "customer/updateForm";
	}//update
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String update(HttpSession session, String address, String email, String idno){
		String id = (String) session.getAttribute("loginId"); /// 여기에서 받아보자 매개변수로 말고, session으로
		String password = (String) session.getAttribute("password");
		Customer customer = repo.findCustomer(id, password);
		customer.setAddress(address);
		customer.setEmail(email);
		customer.setIdno(idno);
		
		repo.updateCustomer(customer);
		
		return "redirect:/";
	}//update
	
}//class
