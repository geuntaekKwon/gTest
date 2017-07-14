package sesoc.global.webTest.dao;

import java.util.List;
import java.util.Map;

import sesoc.global.webTest.vo.Customer;

public interface CustomerDAO {
	//CRUD
	public int insert(Customer customer);
	
	public Customer searchOne(Map<String, String> map);
	
	public int update(Customer customer);
	
	public int delete(String custid);
	
	public List<Customer> selectAll();
	
}//class
