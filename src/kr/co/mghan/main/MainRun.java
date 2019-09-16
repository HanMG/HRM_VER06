package kr.co.mghan.main;

import java.util.Hashtable;
import java.util.Map;

import kr.co.mghan.ex.CodeValueNotFoundException;
import kr.co.mghan.view.CommonMethod;
import kr.co.mghan.view.Menu;

public class MainRun extends CommonMethod
{
	public static void main(String[] args) throws CodeValueNotFoundException
	{
		Map<String, String> map = new Hashtable<String, String>();

		try {
			Class.forName("oracle.jdbc.OracleDriver");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		//oracle driver등록
		
		map.put("admin", "adminpw");
		map.put("hr_head", "user1");
		map.put("it_mgr", "user2");
		map.put("cs_emp", "user3");
		Menu mn = new Menu();
		while (true)
		{
			System.out.println("아이디와 비밀번호를 입력해주세요.");
			System.out.print("아이디: ");
			String id = input_msg();

			System.out.print("비밀번호: ");
			String pw = input_msg();
			// Menu 객체를 통해 아까 만든 메뉴 실행 시키기

			if (map.containsKey(id))
			{
				if (map.get(id).equals(pw))
				{
					System.out.println("로그인 되었습니다.");
					if (id.equals("admin"))
					{						
						mn.main_menu(0);
						break;
					}
					else if(id.equals("hr_head")) {						
						mn.main_menu(1);
						break;
					}
					else if(id.equals("it_mgr")) {
						mn.main_menu(2);
						break;
					}
					else if(id.equals("cs_emp")) {
						mn.main_menu(3);
						break;
					}
				}
				else
				{
					System.out.println("비밀번호가 일치하지 않습니다.");
				}
			}
			else
			{
				System.out.println("입력하신 아이디가 존재하지 않습니다.");
			}
		}

	}
}
