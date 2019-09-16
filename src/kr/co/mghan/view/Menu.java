package kr.co.mghan.view;

import kr.co.mghan.domain.EmpBean;
import kr.co.mghan.domain.EmpData;
import kr.co.mghan.ex.CodeValueNotFoundException;
import kr.co.mghan.ex.RunException;

//KITRI Human Resource Management System에 오신것을 환영합니다.
//다음 수행하고자 하는 메뉴 번호를 누르세요.
//	1. 사원정보조회
//	2. 사원추가
//	3. 사원수정
//	4. 사원삭제
//	5. 부서정보조회
//	6. 종료

public class Menu extends CommonMethod
{
	public void main_menu(int auth) throws CodeValueNotFoundException
	{
		// 메뉴 반복 코드 값 추가
		boolean repeat = true;

		while (repeat)
		{
			System.out.println("-- KITRI Human Resource Management System에 오신것을 환영합니다.");
			System.out.println("-- 다음 수행하고자 하는 메뉴 번호를 누르세요.");
			System.out.println("- 1. 사원정보조회");
			if (auth != 3)
			{
				System.out.println("- 2. 사원추가");
				System.out.println("- 3. 사원수정");
			}
			if (auth < 3)
			{
				System.out.println("- 4. 사원삭제");
			}
			if (auth < 2)
			{
				System.out.println("- 5. 부서정보조회");
			}
			System.out.println("- 6. 종료");
			System.out.print("값을 입력하세요. => ");
			// 입력값 받을 수 있는 기능 수행
			String menu = super.input_msg();
			System.out.println("입력하신 값은 " + menu + " 입니다.");
			if (menu.equals("6"))
			{ // OR Integer.parseInt
				System.out.println("------------종료-----------");
				repeat = false;
			} // if 6 종료 end

			// 1번 메뉴 발생 시킬 수 있도록 구성하기
			else if (menu.equals("1"))
			{
				// 1번 세부 메뉴 실행 시킬 수 있도록 구성하기
				first_menu();
			} // else if first end

			else if (menu.equals("2") && auth != 4)
			{
				// 2번 메뉴 (사원 추가 실행시킬 수 있도록 구성하기)
				second_menu();
			} // else if second end
			else if (menu.equals("3") && auth != 4)
			{
				third_menu();
			}
			else if (menu.equals("4") && auth < 3)
			{
				// 4번 메뉴 ( 사원 삭제)
				fourth_menu();
			}
			else if (menu.equals("5") && auth < 2)
			{
				fifth_menu(auth);
			}
			else
			{
				try
				{
					new RunException().runException();
				}
				catch (CodeValueNotFoundException cvnf)
				{
					System.out.println("입력 값이 잘 못 되었습니다.");
				}
			}
		} // while end
	}// main end

	// 1번 메뉴 메소드 생성
	public void first_menu()
	{

		for (;;)
		{
			System.out.println("------------------------------");
			System.out.println("----------사원정보조회 메뉴---------");
			System.out.println("------------------------------");
			System.out.println("@ 수행하고자 하는 메뉴 번호를 누르세요.");
			System.out.println("a. 사원정보조회");
			System.out.println("b. 특정사원조회");
			System.out.println("c. 사원정보엑셀로 추출");
			System.out.println("exit. 종료");
			System.out.print("값을 입력하세요. => ");
			// 입력값 받을 수 있는 기능 수행
			String menu = input_msg();

			if (menu.equals("exit")) // 사원조회 나가기
			{
				System.out.println("------------1번 메뉴 종료-----------");
				break;
			} // if exit end , exit시 종료
			else if (menu.equals("a"))
			{ // 전체사원조회
				// SearchHR shr = new SearchHR();
				SearchHR shr = SearchHR.getInstance();
				shr.AllView();
			} // else if 'a' END
			else if (menu.equals("b"))
			{ // 특정인물 조회
				boolean repeat = true;
				boolean isEmp = false;
				EmpData ed = new EmpData();
				while (repeat) // 실패시 다시 돌게끔
				{
					System.out.println("사원번호를 입력하세요.");
					String i_empno = input_msg();
					isEmp = ed.isEmp(i_empno);
					if (isEmp)
					{
						EmpBean eb = null;
						eb = (EmpBean) new EmpData().getEmp(Integer.parseInt(i_empno));
						SearchHR.getInstance().selView(eb);
						break;
					}
					else {
						System.out.println("해당 사원번호는 없습니다.");
					}
					repeat = repeatCheck(repeat);

				} // while 특정사원찾기 END

			} // else if 'b' END
			else if (menu.equals("c"))
			{
				ExcelPrint xp = new ExcelPrint();
				
				xp.setXls();
				
				System.out.println("실행되었습니다.");
			}
		} // while 사원조회 메뉴 END
	} // first_menu end

	// 2번 메뉴 메소드 생성
	public void second_menu()
	{ // 사원추가
		System.out.println("추가하고자 하는 사원의 번호를 입력하세요.");
		String empno = input_msg();
		System.out.println("추가하고자 하는 사원의 이름을 입력하세요.");
		String ename = input_msg();
		System.out.println("추가하고자 하는 사원의 부서번호을 입력하세요.");
		String deptno = input_msg();
		System.out.println("추가하고자 하는 사원의 매니저번호를 입력하세요.");
		String mgr = input_msg();
		System.out.println("추가하고자 하는 사원의 급여를 입력하세요.");
		String sal = input_msg();

		// 5가지 값을 전달해 추가
		EmpData ed = new EmpData();
		int cnt = ed.ins_emp(Integer.parseInt(empno), ename, Integer.parseInt(deptno),
				Integer.parseInt(mgr), Double.parseDouble(sal));
		System.out.println(cnt + "명의 사원이 입력되었습니다.");

	}

	// 3번 메뉴 메소드 생성
	public void third_menu()
	{ // 사원정보 수정 실행
		EmpData ed = new EmpData();
		boolean isEmp = false;
		boolean repeat = true;

		System.out.println("@ 수정 메뉴를 선택하셨습니다.");
		while (repeat)
		{

			System.out.println("수정하고자하는 사원 번호를 입력하시오.");
			// shr.all_View(ar_eb);
			String i_empno = input_msg();
			isEmp = ed.isEmp(i_empno);
			if (isEmp)
			{
				int cnt = 0;

				System.out.println("수정하고자 하는 사원 번호를 입력하세요.");
				String m_empno = input_msg();
				System.out.println("수정하고자 하는 사원 이름을 입력하세요.");
				String m_ename = input_msg();
				cnt = ed.mod_emp(m_empno, m_ename, i_empno);
				System.out.println(cnt + "명의 사원이 수정되었습니다.");
				break;
			}
			else
			{
				System.out.println("해당 사원번호는 없습니다.");
			}

			repeat = repeatCheck(repeat);
		}
	}

	// 4번 메뉴 메소드 생성
	public void fourth_menu()
	{
		EmpData ed = new EmpData();
		boolean isEmp = false;
		boolean repeat = true;

		// 삭제 메뉴 출력
		System.out.println("@ 삭제 메뉴를 선택하셨습니다.");
		while (repeat)
		{
			System.out.println("삭제하고자 하는 사원의 번호를 입력하세요.");
			String i_empno = input_msg();
			isEmp = ed.isEmp(i_empno);
			if (isEmp)
			{
				int cnt = 0;
				cnt = ed.del_emp(i_empno);
				System.out.println(cnt + "명의 사원이 삭제되었습니다.");
				break;
			}
			else
			{
				System.out.println("해당 사원번호는 없습니다.");
			}

			repeat = repeatCheck(repeat);
		}

	}

	// 5번 메뉴 메소드 생성
	public void fifth_menu(int auth)
	{
		// 부서정보 조회 메뉴
		boolean repeat = true;
		while (repeat)
		{
			System.out.println("------------------------------");
			System.out.println("----------부서정보조회 메뉴---------");
			System.out.println("------------------------------");
			System.out.println("@ 수행하고자 하는 메뉴 번호를 누르세요.");
			System.out.println("1. 부서 조회");
			if (auth == 0)
			{
				System.out.println("2. 부서 추가");
				System.out.println("3. 부서 수정");
				System.out.println("4. 부서 삭제");
			}
			System.out.println("exit. 종료");
			System.out.print("값을 입력하세요. => ");
			String menu = input_msg();
			DeptMenu dm = DeptMenu.getInstance();
			if (menu.equals("exit"))
			{
				repeat = false;
			}
			else if (menu.equals("1"))
			{
				dm.dept_search();
			}
			else if (menu.equals("2") && auth == 0)
			{
				dm.dept_add();
			}
			else if (menu.equals("3") && auth == 0)
			{
				dm.dept_mod();
			}
			else if (menu.equals("4") && auth == 0)
			{
				dm.dept_del();
			}
		}
	}

} // class menu end
