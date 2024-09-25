package com.smallyang.tean.junit;

import com.smallyang.tean.domain.Employee;
import com.smallyang.tean.service.NameListService;
import com.smallyang.tean.service.TeamException;
import org.junit.jupiter.api.Test;

/**
 * 
* <p>Title: NameListServiceTest</p>  

* <p>Description: NameListService的測試</p> 
 * @author USER
 * 2023年7月24日
 */
public class NameListServiceTest {

	@Test
	public void testGetAllEmployees() {
		NameListService service = new NameListService();
		Employee[] employees = service.getAllEmployees();
		for(int i = 0; i < employees.length; i++) {
			System.out.println(employees[i]);
		}
	}
	
	
	@Test
	public void testGetEmployees() {
		NameListService service = new NameListService();
		int id = 1;
		id = 10;
		try {
			Employee employee = service.getEmployee(id);
			System.out.println(employee);

		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}

	}
}
