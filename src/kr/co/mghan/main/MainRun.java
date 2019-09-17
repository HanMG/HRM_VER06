package kr.co.mghan.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kr.co.mghan.ex.CodeValueNotFoundException;
import kr.co.mghan.view.CommonMethod;
import kr.co.mghan.view.Menu;

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

		Statement stmt = null;
		// ResultSet (DB 결과값 저장)를 선언하여 가져오기
		ResultSet rs = null;
		// 집어넣을 리스트 생성

		while (true)
		{
			System.out.println("아이디와 비밀번호를 입력해주세요.");
			System.out.print("사원명: ");
			String ename = input_msg();			

			System.out.print("사원번호: ");
			String empno = input_msg();
			int i_pw = Integer.parseInt(empno);

			String sql = "SELECT empno, ename, job FROM emp WHERE empno = " + i_pw;
			try
			{
				stmt = con.createStatement();
				rs = stmt.executeQuery(sql);
				Menu mn = new Menu();
				if (rs.next()) // 값이 검색이 되었을 시
				{
					if (ename.toUpperCase().equals(rs.getString(2)))
					{						
						if (Integer.parseInt(empno) == rs.getInt(1))
						{
							System.out.println("로그인 되었습니다.");
							if (rs.getString(3).equals("PRESIDENT")
									|| rs.getString(3).equals("ANALYST"))
							{
								mn.main_menu(0);
								break;
							}
							else if (rs.getString(3).equals("MANAGER"))
							{
								mn.main_menu(1);
								break;
							}
							else if (rs.getString(3).equals("SALESMAN"))
							{
								mn.main_menu(2);
								break;
							}
							else if (rs.getString(3).equals("CLERK"))
							{
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
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Menu 객체를 통해 아까 만든 메뉴 실행 시키기

		}

	}
}
