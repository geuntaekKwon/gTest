package sesoc.global.webTest.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sesoc.global.webTest.vo.Customer;

@Repository //데이터베이스에 접속하는 애
public class CustomerRepository { //CustomerDAOImpl 이 하던 일을 여기서 함

	@Autowired
	SqlSession sqlSession;
	
	public int createCustomer(Customer customer){
		CustomerDAO dao = sqlSession.getMapper(CustomerDAO.class);
		int result = dao.insert(customer);
		return result;
	}//createCustomer
	
	public int updateCustomer(Customer customer){
		CustomerDAO dao = sqlSession.getMapper(CustomerDAO.class);
		int result = dao.update(customer);
		return result;
	}//createCustomer
	
	public int deleteCustomer(String custid){
		return 0;
	}//createCustomer
	
	public Customer findCustomer(String custid){
		CustomerDAO dao = sqlSession.getMapper(CustomerDAO.class);
		
		return null;
	}//createCustomer
	
	public List<Customer> findAll(){
		return null;
	}//createCustomer

	public Customer findCustomer(String custid, String password) {
		CustomerDAO dao = sqlSession.getMapper(CustomerDAO.class);
		Map<String, String> map = new HashMap<String, String>();

		map.put("custid", custid);
		map.put("password", password);
		Customer customer = dao.searchOne(map);
			
		return customer;
	}
	
}//class
