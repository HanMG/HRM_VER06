package kr.co.mghan.view;

import java.util.regex.Matcher;

import kr.co.mghan.domain.EmpBean;

public class Login extends CommonMethod
{
	public EmpBean login()
	{
		EmpBean eb = new EmpBean();
		// TODO Auto-generated constructor stub
		for (;;) // 사원번호
		{
			System.out.println("사원명과 사원번호를 입력해주세요.");
			System.out.print("사원명: ");
			String ename = input_msg();

			System.out.print("사원번호: ");
			String empno = input_msg();
			Matcher m = p_number.matcher(empno); // 입력받은 값을 Matcher를 통해 체크
			if (m.find())
			{
				int i_empno = Integer.parseInt(empno);

				eb.setEname(ename.toUpperCase());
				eb.setEmpno(i_empno);

				return eb;
			}
			else
			{
				System.out.println("사원번호에는 숫자만 입력해주세요.\n");				
			}
		}
	}
}
