package kr.co.mghan.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kr.co.mghan.domain.DeptBean;
import kr.co.mghan.domain.DeptData;

public class DeptMenu extends CommonMethod
{
	Pattern p_number = Pattern.compile("(^[0-9]*$)"); // 숫자 패턴
	
	private static DeptMenu dm = new DeptMenu();
	
	static DeptMenu getInstance()
	{
		return dm;
	}

	public void dept_search()
	{
		for (;;)
		{
			System.out.println("@ 수행하고자 하는 메뉴 번호를 입력하세요.");
			System.out.println("a. 전체 부서 조회");
			System.out.println("b. 특정 부서 조회");
			System.out.println("exit. 종료");
			String d_menu = input_msg();
			if (d_menu.equals("a"))
			{
				new DeptMethod().dept_All_View();
			}
			else if (d_menu.equals("b"))
			{
				boolean repeat = true; // 반복자
				boolean isDept = false; // 부서 존재 확인
				DeptMethod dmt = new DeptMethod();

				while (repeat)
				{
					System.out.println("부서번호를 입력하세요.");
					String i_deptno = input_msg();
					Matcher m = p_number.matcher(i_deptno);
					isDept = dmt.isDept(i_deptno);
					if (isDept)
					{
						if (m.find())
						{
							DeptBean db = null;
							db = new DeptData().getDept(Integer.parseInt(i_deptno));
							new DeptMethod().dept_View(db);
							break;
						}
						else
						{
							System.out.println("부서번호만 입력해주세요.");
						}
					}
					else {
						System.out.println("해당 부서번호는 없습니다.");
					}
					repeat = repeatCheck(repeat);
				}
			}
			else if (d_menu.equals("exit"))
			{
				break;
			}
		}

	}

	public void dept_add()
	{
		int a_deptno; // 추가할 부서번호
		String a_deptname; // 추가할 부서명
		String a_loc; // 추가할 부서의 위치

		System.out.println("추가할 부서번호를 입력하세요.");
		a_deptno = Integer.parseInt(input_msg());
		System.out.println("추가할 부서명를 입력하세요.");
		a_deptname = input_msg();
		System.out.println("추가할 부서의 위치를 입력하세요.");
		a_loc = input_msg();

		DeptMethod dmt = new DeptMethod();
		dmt.ins_dept(a_deptno, a_deptname, a_loc);

	} // dept_add END

	public void dept_mod()
	{
		String i_deptno; // 수정할 부서번호
		int m_deptno; // 수정될 부서번호
		String m_deptname; // 수정될 부서명
		String m_loc; // 수정될 부서위치

		boolean repeat = true; // 반복자
		boolean isDept = false;
		DeptMethod dmt = new DeptMethod();

		while (repeat)
		{
			System.out.println("수정하실 부서번호를 입력하세요.");
			i_deptno = input_msg();
			isDept = dmt.isDept(i_deptno);
			if (isDept)
			{
				Matcher m = p_number.matcher(i_deptno); // 입력받은 값을 Matcher를 통해 체크
				if (m.find())
				{
					System.out.println("부서번호 수정");
					m_deptno = Integer.parseInt(input_msg());
					System.out.println("부서명 수정");
					m_deptname = input_msg();
					System.out.println("부서위치 수정");
					m_loc = input_msg();

					dmt.mod_dept(m_deptno, m_deptname, m_loc, i_deptno);
					break;
				}
				else
				{
					System.out.println("부서번호만 입력해주세요.");
				}
			}
			else
			{
				System.out.println("부서번호가 존재하지 않습니다.");
			}

			repeat = repeatCheck(repeat);
		} // While END
	} // dept_mod END

	// 부서 삭제
	public void dept_del()
	{
		String i_deptno; // 입력받을 부서번호
		boolean isDept = false; // 부서번호가 있는지
		boolean repeat = true; // 반복자
		DeptMethod dmt = new DeptMethod();

		while (repeat)
		{
			System.out.println("삭제하실 부서번호를 입력하세요.");
			i_deptno = input_msg();
			isDept = dmt.isDept(i_deptno);

			if (isDept)
			{
				Matcher m = p_number.matcher(i_deptno); // 입력받은 값을 Matcher를 통해 체크

				if (m.find()) // find() 패턴이 일치할 경우 true, 아닐경우 false를 반환
				{

					dmt.del_dept(i_deptno);
					break;
				}
				else
				{
					System.out.println("부서번호만 입력해주세요.");
				}
			}
			else
			{
				System.out.println("부서번호가 존재하지 않습니다.");
			}

			repeat = repeatCheck(repeat);
		}
	}// dept_del END
}
