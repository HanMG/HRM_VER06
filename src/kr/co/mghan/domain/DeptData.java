package kr.co.mghan.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DeptData
{	
	
	// 특정 부서 조회
	public DeptBean getDept(int deptno)
	{
		Connection con = dbCon();

		// System.out.println("Con 객체 : " + con);

		// Query문을 실행시킬 수 있도록 구조 만들기
		Statement stmt = null;
		// ResultSet (DB 결과값 저장)를 선언하여 가져오기
		ResultSet rs = null;
		// 집어넣을 리스트 생성

		String sql = "SELECT deptno, dname, loc FROM dept WHERE deptno = "+deptno;
		DeptBean db = new DeptBean();
		try
		{
			stmt = con.createStatement();
			// select 실행시

			rs = stmt.executeQuery(sql);

			// insert, update, delete

			// stmt.executeUpdate("");

			// ResutSet 내에서 부터 값을 조회해보기
			// Iterator 방식

			if (rs.next()) // 값이 검색이 되었을 시
			{				
				db.setDeptno(rs.getInt(1));
				db.setDeptname(rs.getString(2));
				db.setLoc(rs.getString(3));
			}

		}
		catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
		return db;
	}
	
	private Connection dbCon()
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

		return con;
	}

}
