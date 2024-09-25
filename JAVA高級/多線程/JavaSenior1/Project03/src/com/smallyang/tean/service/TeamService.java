package com.smallyang.tean.service;

import com.smallyang.tean.domain.Architect;
import com.smallyang.tean.domain.Designer;
import com.smallyang.tean.domain.Employee;
import com.smallyang.tean.domain.Programmer;

/**
 * 
 * <p>
 * Title: TeamService
 * </p>
 * 
 * <p>
 * Description: 關於開發團隊成員的添加、刪除、管理等
 * </p>
 * 
 * @author USER 2023年7月25日
 */
public class TeamService {
	private static int counter = 1;// 給memberId賦值用
	private final int MAX_MEMBER = 5;// 限制開發團隊人數
	private Programmer[] team = new Programmer[MAX_MEMBER];// 保存開發團隊成員
	private int total;// 紀錄開發團隊中實際人數

	public TeamService() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @Description: 獲取開發團隊中所有成員
	 * @author USER
	 * @date 2023年7月25日
	 * @param
	 * @return
	 */
	public Programmer[] getTeam() {
		Programmer[] team = new Programmer[total];
		for (int i = 0; i < team.length; i++) {
			team[i] = this.team[i];
		}
		return team;

	}

	/**
	 * 
	 * @Description: 將指定員工添加到開發團隊中
	 * @author USER
	 * @date 2023年7月27日
	 * @param
	 * @return
	 * @throws TeamException
	 */
	public void addMember(Employee e) throws TeamException {
		if (total >= MAX_MEMBER) {
			throw new TeamException("成員已滿，無法添加");
		}
		if (!(e instanceof Programmer)) {
			throw new TeamException("該成員不是開發人員，無法添加");
		}
		if (isExist(e)) {
			throw new TeamException("該成員已在開發團隊中，無法添加");
		}

		Programmer p = (Programmer) e;// 一定不會出現ClassNotFoundException，上面已經驗證過
//		if ("BUSY".equalsIgnoreCase(p.getStatus().getNAME())) {// if(p.getStatus().getNAME().equals("BUSY")) {
//			throw new TeamException("該員工已是本團隊成員");
//		} else if ("VOCATION".equalsIgnoreCase(p.getStatus().getNAME())) {
//			throw new TeamException("該員工在休假無法添加");
//		}
		Status busy = Status.BUSY;
		System.out.println(busy.name());
		switch (p.getStatus()){
			case BUSY:
				throw new TeamException("該員工已是本團隊成員");
			case VOCATION:
				throw new TeamException("該員工在休假無法添加");
		}


		// 獲取team已有成員中架構施、設計師、程序員的人數
		int numOfArch = 0, numOfDes = 0, numOfPro = 0;
		for (int i = 0; i < total; i++) {
			if (team[i] instanceof Architect) {
				numOfArch++;
			} else if (team[i] instanceof Designer) {
				numOfDes++;
			} else if (team[i] instanceof Programmer) {
				numOfPro++;
			}
		}

		// 都先從架構師 因為範圍比較小
		if (p instanceof Architect) {
			if (numOfArch >= 1) {
				throw new TeamException("團隊中至多只能有1名架構師");
			}
		} else if (p instanceof Designer) {
			if (numOfDes >= 2) {
				throw new TeamException("團隊中至多只能有2名設計師");
			}
		} else if (p instanceof Programmer) {
			if (numOfPro >= 3) {
				throw new TeamException("團隊中至多只能有3名設計師");
			}
		}

		// 將p(或e)添加到現有的team中
		team[total++] = p;
		// p的屬性賦值
		p.setStatus(Status.BUSY);
		p.setMemberId(counter++);
	}

	/**
	 * 
	 * @Description:判斷指定的員工是否已存在開發團隊中
	 * @author USER
	 * @date 2023年7月27日
	 * @param
	 * @return
	 */
	private boolean isExist(Employee e) {
		// 也可以用memberId，但e要強轉成Programmer
		for (int i = 0; i < total; i++) {
			if (team[i].getId() == e.getId()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @Description: 從團隊中刪除成員
	 * @author USER
	 * @date 2023年7月29日
	 * @param
	 * @return
	 * @throws TeamException
	 */
	public void removeMember(int memberId) throws TeamException {
		int i = 0;
		// 整個循環走完後才知道有沒有找到
		for (; i < total; i++) {
			if (team[i].getMemberId() == memberId) {
				team[i].setStatus(Status.FREE);
				break;
			}
		}

//			表示沒找到指定的memberId的情況
		if (i == total) {
			throw new TeamException("找不到指定memberId");
		}

		// 後一個把前一個覆蓋的意思
		for (int j = i + 1; j < total; j++) {
			team[j - 1] = team[j];
		}

		// 寫法一
//			team[total-1] =null;
//			total--;
		// or
		// 寫法二
		team[--total] = null;

	}
}
