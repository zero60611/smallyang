package com.smallyang.tean.service;

import com.smallyang.tean.domain.Architect;
import com.smallyang.tean.domain.Designer;
import com.smallyang.tean.domain.Employee;
import com.smallyang.tean.domain.Equipment;
import com.smallyang.tean.domain.NoteBook;
import com.smallyang.tean.domain.PC;
import com.smallyang.tean.domain.Printer;
import com.smallyang.tean.domain.Programmer;

import static com.smallyang.tean.service.Data.*;

/**
 * 
* <p>Title: NameListService</p>  

* <p>Description: </p> 
 * @author USER
 * 2023年7月18日
 */
public class NameListService {
	private Employee[] employees;
	/**
	 * 給employees及數組進行初始化
	 */
	public NameListService() {
		//	根據項目提供的DATA類構建相應大小的employee
		//  再根據DATA類中的數據構建不同的對象，包括employee、programmer、designer、architect
		//	將對象存於數據中
		//	引入DATA項下的靜態結構
		employees = new Employee[EMPLOYEES.length];
		for(int i = 0; i < employees.length; i++) {
			//	獲取員工的類型
			int type = Integer.parseInt(EMPLOYEES[i][0]);
			
			// 獲取employee的4個基本信息
			int id = Integer.parseInt(EMPLOYEES[i][1]);
			String name = EMPLOYEES[i][2];
			int age = Integer.parseInt(EMPLOYEES[i][3]);
			double salary = Double.parseDouble(EMPLOYEES[i][4]);
			int stock;
			Equipment equipment;
			double bonus;
			
			switch(type) {
				case EMPLOYEE:
					employees[i] = new Employee(id, name, age, salary);
					break;
				case PROGRAMMER:
					equipment = createEquipment(i);
					employees[i] = new Programmer(id, name, age, salary, equipment);
					break;
				case DESIGNER:
					equipment = createEquipment(i);
					bonus = Double.parseDouble(EMPLOYEES[i][5]);
					employees[i] = new Designer(id, name, age, salary, equipment, bonus);
					break;
				case ARCHITECT:
					equipment = createEquipment(i);
					bonus = Double.parseDouble(EMPLOYEES[i][5]);
					stock = Integer.parseInt(EMPLOYEES[i][6]);
					employees[i] = new Architect(id, name, age, salary, equipment, bonus, stock);
					break;
			}
		
		}
	}

	/**
	 * 
	 * @Description: 獲取指定index上的員工的設備
	 * @author USER
	 * @date 2023年7月22日
	 * @param 
	 * @return
	 */
	private Equipment createEquipment(int index) {
		int key = Integer.parseInt(EQUIPMENTS[index][0]);
		String modelOrName = EQUIPMENTS[index][1];
		
		switch(key) {
		case PC://21
			String display = EQUIPMENTS[index][2];
			return new PC(modelOrName, display);
		case NOTEBOOK://22
			double price = Double.parseDouble(EQUIPMENTS[index][2]);
			return new NoteBook(modelOrName, price);
		case PRINTER://23
			String type = EQUIPMENTS[index][2];
			return new Printer(modelOrName, type);
		}
		
		return null;
	}

	/**
	 * 
	 * @Description: 獲取當前所有員工
	 * @author USER
	 * @date 2023年7月22日
	 * @param 
	 * @return
	 */
	public Employee[] getAllEmployees() {
		return employees;
	}

	/**
	 * 
	 * @Description: 獲取指定ID的員工
	 * @author USER
	 * @date 2023年7月22日
	 * @param 
	 * @return
	 * @throws TeamException 
	 */
	public Employee getEmployee(Integer id) throws TeamException {
		for(int i = 0; i < employees.length; i++) {
			if(employees[i].getId() == id) {	
			return employees[i];
		}
	}
		throw new TeamException("找不到指定員工");
	}
	
}
