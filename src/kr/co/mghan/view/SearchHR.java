package kr.co.mghan.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kr.co.mghan.domain.EmpBean;

public class SearchHR extends Search
{
	// 사원 조회를 위한 View 객체
	// 사원의 정보를 가져다가 구성함.

	// 싱글톤
	private static SearchHR shr = new SearchHR();

	static SearchHR getInstance()
	{

		return shr;
	}

	public void all_View()
	{
		Connection con = null;

		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		String user = "scott";
		String password = "tiger";

		try
		{
			con = DriverManager.getConnection(url, user, password);
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Query문을 실행시킬 수 있도록 구조 만들기
		Statement stmt = null;
		// ResultSet (DB 결과값 저장)를 선언하여 가져오기
		ResultSet rs = null;
		
		

		String sql = "SELECT empno, ename, deptno FROM emp ORDER BY empno ASC";

		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			System.out.println("------조회된 사원 결과------");
			int cnt = 1;
			while (rs.next())
			{				
				System.out.println((cnt) + "번째 사원 정보----");
				System.out.println("사원번호 : " + rs.getInt(1));
				System.out.println("사원이름 : " + rs.getString(2));
				System.out.println("부서번호 : " + rs.getInt(3));
				System.out.println();
				cnt ++;
			}
			
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try
			{
				// 순서 맞춰서
				if (rs != null)
				{
					rs.close();
				}
				if (stmt != null)
				{
					stmt.close();
				}
				if (con != null)
				{
					con.close();
				}
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	

	}// all_View end

	// 특정사원 번호 조회를 통한 사원 정보 출력
	public void emp_View(EmpBean eb)
	{
		if (eb == null)
		{
			System.out.println("조회된 값이 없습니다.");
		}
		else
		{
			System.out.println("사원 정보는 다음과 같습니다.");
			System.out.println("사원번호 : " + eb.getEmpno());
			System.out.println("이름 : " + eb.getEname());
			System.out.println("부서 : " + eb.getDeptno());
			System.out.println();
		}
	}
	
	
	
	
	public void AllView()
	{
		// TODO Auto-generated method stub
		all_View();
	}

	@Override
	public void selView(Object ob)
	{
		// TODO Auto-generated method stub
		emp_View((EmpBean) ob);

	}
} // SearchHR end
