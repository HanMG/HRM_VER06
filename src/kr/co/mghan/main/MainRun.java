package kr.co.mghan.main;

import kr.co.mghan.domain.EmpBean;
import kr.co.mghan.domain.EmpData;
import kr.co.mghan.ex.CodeValueNotFoundException;
import kr.co.mghan.view.CommonMethod;
import kr.co.mghan.view.Login;


public class MainRun extends CommonMethod
{
	public static void main(String[] args) throws CodeValueNotFoundException
	{

		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		// oracle driver등록
		boolean repeat = true;
		while (repeat)
		{
			Login login = new Login();
			EmpBean eb = login.login();
			EmpData ed = new EmpData();				
			repeat = ed.loginEmp(eb.getEmpno(), eb.getEname()); 
		}
	}
}
