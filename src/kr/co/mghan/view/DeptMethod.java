package kr.co.mghan.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kr.co.mghan.domain.DeptBean;

// 부서메뉴 관련 함수 모음 
public class DeptMethod extends CommonMethod
{
	private static DeptMethod dmt = new DeptMethod();
	static DeptMethod getInstance() {
		return dmt;
	}

	// 모든 부서 보기
	public void dept_All_View()
	{
		Connection con = dbCon();

		// Query문을 실행시킬 수 있도록 구조 만들기
		Statement stmt = null;
		// ResultSet (DB 결과값 저장)를 선언하여 가져오기
		ResultSet rs = null;

		String sql = "SELECT deptno, dname, loc FROM dept ORDER BY deptno ASC";

		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			System.out.println("------조회된 부서 결과------");
			int cnt = 1;
			while (rs.next())
			{
				System.out.println((cnt) + "번째 부서 정보----");
				System.out.println("부서번호 : " + rs.getInt(1));
				System.out.println("부서이름 : " + rs.getString(2));
				System.out.println("부서위치 : " + rs.getString(3));
				System.out.println();
				cnt++;
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

	}// dept_all_View end

	// 특정부서 보기
	public void dept_View(DeptBean db)
	{

		System.out.println("부서 정보는 다음과 같습니다.");
		System.out.println("부서번호 : " + db.getDeptno());
		System.out.println("부서명 : " + db.getDeptname());
		System.out.println("위치 : " + db.getLoc());
		System.out.println();

	}

	public void ins_dept(int deptno, String deptname, String loc)
	{
		Connection con = dbCon();
		PreparedStatement pstmt = null;

		String sql = "INSERT INTO dept(deptno,dname,loc) VALUES(?,?,?)";

		try
		{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, deptno);
			pstmt.setString(2, deptname);
			pstmt.setString(3, loc);
			pstmt.executeUpdate();
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
				if (pstmt != null)
				{
					pstmt.close();
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
	}

	public void mod_dept(int m_deptno, String m_deptname, String m_loc, String i_deptno)
	{
		// 수정 진행
		Connection con = dbCon();
		PreparedStatement pstmt = null;

		String sql = "UPDATE dept SET deptno = ?, dname = ?, loc = ? WHERE deptno = ?";

		try
		{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, m_deptno);
			pstmt.setString(2, m_deptname);
			pstmt.setString(3, m_loc);
			pstmt.setInt(4, Integer.parseInt(i_deptno));
			pstmt.executeUpdate();
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
				if (pstmt != null)
				{
					pstmt.close();
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
	} // mod_dept END

	public void del_dept(String i_deptno)
	{
		Connection con = dbCon();
		PreparedStatement pstmt = null;
		// 트렌젝션 관리 설정
		// Autocommit 해제
		String sql = "DELETE FROM dept WHERE deptno = ?";
		int cnt = 0;
		try
		{
			con.setAutoCommit(false);
			System.out.println("AutoCommit Setting false");
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(i_deptno));
			cnt = pstmt.executeUpdate();
			if (cnt == 1)
			{
				System.out.println("정상 처리 : Commit 실행 ");
				con.commit();
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			try
			{
				System.out.println("비정상 종료 : RollBack 실행");
				con.rollback();
			}
			catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		finally
		{
			try
			{
				System.out.println("AutoCommit Setting True");
				con.setAutoCommit(true);
				if (pstmt != null)
				{
					pstmt.close();
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
	} // del_dept END

	public boolean isDept(String i_deptno)
	{
		boolean exist = false;

		Connection con = dbCon();

		Statement stmt = null;

		ResultSet rs = null;

		String sql = "SELECT deptno FROM dept WHERE deptno =" + i_deptno;

		try
		{
			stmt = con.createStatement();
			// select 실행시

			rs = stmt.executeQuery(sql);

			if (rs.next()) // 값이 검색이 되었을 시
			{
				exist = true;
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

		return exist;
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
