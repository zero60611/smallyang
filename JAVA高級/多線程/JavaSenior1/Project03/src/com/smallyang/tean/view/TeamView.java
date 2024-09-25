package com.smallyang.tean.view;

import com.smallyang.tean.domain.Employee;
import com.smallyang.tean.domain.Programmer;
import com.smallyang.tean.service.NameListService;
import com.smallyang.tean.service.Status;
import com.smallyang.tean.service.TeamException;
import com.smallyang.tean.service.TeamService;

public class TeamView {
	private NameListService listSvc = new NameListService();
	private TeamService teamSvc = new TeamService();

	public void enterMainMenu() {
		boolean mainFlag = true;
		char menuSelection = 0;

		while (mainFlag) {
			if (menuSelection != '1') {
				listAllEmployee();
			}
			System.out.println("1團隊列表 2添加團隊成員 3刪除團隊成員 4退出 請選擇1~4: ");
			menuSelection = TSUtility.readMenuSelection();
			switch (menuSelection) {
			case '1':
				getTeam();
				break;
			case '2':
				addMember();
				break;
			case '3':
				deleteMember();
				break;
			case '4':
				System.out.println("確認是否退出(Y/N):");
				char isExit = TSUtility.readConfirmSelection();
				if (isExit == 'Y') {
					mainFlag = false;
					System.out.println("離開團隊了");
				}
			}

			// todo 可能是別的方法
//			Employee[] emps = TSUtility.getAllEmployees();
			Employee[] emps = null;

			if (emps == null || emps.length == 0) {
				System.out.println("沒有客戶紀錄");
			} else {
				System.out.println("ID\t姓名\t年齡\t工資\t職位\t狀態\t獎金\t股票\t領用設備");
				for (Employee e : emps) {
					System.out.println(" " + e);
				}
			}

			System.out.println("-------------------------------------------------------------------------");
		}
	}

	/**
	 * 
	 * @Description:顯示所有員工訊息
	 * @author USER
	 * @date 2023年7月29日
	 * @param
	 * @return
	 */
	private void listAllEmployee() {
		System.out.println("--------------------開發團隊調度軟件--------------------");
		Employee[] allEmployees = listSvc.getAllEmployees();
		if (allEmployees == null || allEmployees.length == 0) {
			System.out.println("公司中沒有任何員工信息");
		} else {
			System.out.println("ID\t姓名\t年龄\t工资\t职位\t状态\t奖金\t股票\t领用设备");
			for (int i = 0; i < allEmployees.length; i++) {
				System.out.println(allEmployees[i]);
			}
		}
		System.out.println("----------------------------------------");
	}

	private void getTeam() {
		System.out.println("--------------------團隊成員列表--------------------");
		Programmer[] team = teamSvc.getTeam();
		if (team == null || team.length == 0) {
			System.out.println("開發團隊目前沒有成員");
		} else {
			System.out.println("TID/ID\t姓名\t年龄\t工资\t职位\t奖金\t股票");
			for (int i = 0; i < team.length; i++) {
				System.out.println(team[i].getDetailsForTeam());
			}
		}
		System.out.println("----------------------------------------");

	}

	private void addMember() {
		System.out.println("\n--------------------添加成員--------------------");
		System.out.print("請輸入要添加的員工ID：");
		int id = TSUtility.readInt();

		Employee employee;
		try {
			employee = listSvc.getEmployee(id);
			teamSvc.addMember(employee);
			System.out.println("添加成功");

		} catch (TeamException e) {
			// TODO Auto-generated catch block
			System.out.println("添加失敗，原因:" + e.getMessage());
		}
		// 迴車建繼續...
		TSUtility.readReturn();
	}

	private void deleteMember() {
		System.out.println("\n--------------------刪除成員--------------------");
		System.out.print("請輸入要刪除員工的TID: ");
		int id = TSUtility.readInt();
		System.out.println("確認是否刪除?Y/N");

		char yn = TSUtility.readConfirmSelection();

		if (yn == 'N') {
			return;
		}
		try {
			teamSvc.removeMember(id);
			System.out.println("刪除成功");
		} catch (TeamException e) {
			// TODO: handle exception
			System.out.println("刪除失敗 原因:" + e.getMessage());
		}
		// enter繼續
		TSUtility.readReturn();
	}

	public static void main(String[] args) {
		TeamView view = new TeamView();
		view.enterMainMenu();
	}
}
