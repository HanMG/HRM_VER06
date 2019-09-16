package kr.co.mghan.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;

public class EmpData
{
//	// 샘플 데이터 세팅
//	public List<EmpBean> def_data()
//	{
//		// Oracle DB에서 값 가져오기
//		// List에 추가하기
//		// Connection 생성
//		Connection con = dbCon();
//
//		// System.out.println("Con 객체 : " + con);
//
//		// Query문을 실행시킬 수 있도록 구조 만들기
//		Statement stmt = null;
//		// ResultSet (DB 결과값 저장)를 선언하여 가져오기
//		ResultSet rs = null;
//		// 집어넣을 리스트 생성
//		List<EmpBean> list = new ArrayList<EmpBean>();
//
//		try
//		{
//			stmt = con.createStatement();
//			// select 실행시
//			String sql = "SELECT empno, ename, mgr, job, hiredate, sal, comm, deptno FROM emp";
//			rs = stmt.executeQuery(sql);
//
//			// insert, update, delete
//
//			// stmt.executeUpdate("");
//
//			// ResutSet 내에서 부터 값을 조회해보기
//			// Iterator 방식
//			while (rs.next())
//			{
//				EmpBean eb = new EmpBean();
//				eb.setEmpno(rs.getInt(1));
//				eb.setEname(rs.getString(2));
//				eb.setMgr(rs.getInt(3));
//				eb.setJob(rs.getString(4));
//				eb.setHiredate(rs.getString(5));
//				eb.setSal(rs.getDouble(6));
//				eb.setComm(rs.getDouble(7));
//				eb.setDeptno(rs.getInt(8));
//				list.add(eb);
//				// rs.getInt("empno");
//			}
//		}
//		catch (SQLException e1)
//		{
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		finally
//		{
//			try
//			{
//				// 순서 맞춰서
//				if (rs != null)
//				{
//					rs.close();
//				}
//				if (stmt != null)
//				{
//					stmt.close();
//				}
//				if (con != null)
//				{
//					con.close();
//				}
//			}
//			catch (SQLException e)
//			{
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//		return list;
//	} // def_data end
	

	// 특정 사원 정보 조회 후 값 세팅하기 (def_data() 에 갖고 있는 초기값)
	public EmpBean getEmp(int empno)
	{
		Connection con = dbCon();

		// System.out.println("Con 객체 : " + con);

		// Query문을 실행시킬 수 있도록 구조 만들기
		Statement stmt = null;
		// ResultSet (DB 결과값 저장)를 선언하여 가져오기
		ResultSet rs = null;
		// 집어넣을 리스트 생성

		String sql = "SELECT empno, ename, mgr, job, hiredate, sal, comm, deptno FROM emp  WHERE empno = "
				+ empno;
		EmpBean eb = new EmpBean();
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
				eb.setEmpno(rs.getInt(1));
				eb.setEname(rs.getString(2));
				eb.setMgr(rs.getInt(3));
				eb.setJob(rs.getString(4));
				eb.setHiredate(rs.getString(5));
				eb.setSal(rs.getDouble(6));
				eb.setComm(rs.getDouble(7));
				eb.setDeptno(rs.getInt(8));
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
		return eb;
	}

	// 사원 추가 시키기 (Menu -> empno, ename ) 받아서 사용
	public int ins_emp(int empno, String ename, int deptno, int mgr, double sal)
	{
		
		Connection con = dbCon();
		// Query문을 실행시킬 수 있도록 구조 만들기
		PreparedStatement pstmt = null;
		
		int cnt = 0;		
		
		String sql = "INSERT INTO emp(empno,ename,deptno,mgr,sal) ";
		String sql2 = "VALUES(?,?,?,?,?)";
		try
		{
			pstmt = con.prepareStatement(sql+sql2);
			pstmt.setInt(1, empno);
			pstmt.setString(2, ename);
			pstmt.setInt(3, deptno);
			pstmt.setInt(4, mgr);
			pstmt.setDouble(5, sal);
			cnt = pstmt.executeUpdate();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		finally {
			try
			{
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();
				}				
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return cnt;
	}

	// 객체배열과 인덱스 번호를 받아 삭제 진행
	public int del_emp(String empno)
	{
		Connection con = dbCon();
		PreparedStatement pstmt = null;
		// 트렌젝션 관리 설정
		// Autocommit 해제	
		String sql = "DELETE FROM emp WHERE empno = ?";
		int cnt = 0;
		try
		{
			con.setAutoCommit(false);
			System.out.println("AutoCommit Setting false");
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(empno));
			cnt = pstmt.executeUpdate();
			if(cnt == 1) {
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
		finally {
			try
			{
				System.out.println("AutoCommit Setting True");
				con.setAutoCommit(true);
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();
				}								
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
			
		}
		

		return cnt;

	}// del_emp2 END

	public int mod_emp(String m_empno, String m_ename, String empno)
	{
		Connection con = dbCon();
		PreparedStatement pstmt = null;
		String sql = "UPDATE emp SET empno = ?, ename = ? WHERE empno = ?";
		int cnt = 0;
		try
		{
			con.setAutoCommit(false);
			System.out.println("AutoCommit Setting false");
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(m_empno));
			pstmt.setString(2, m_ename);
			pstmt.setInt(3, Integer.parseInt(empno));
			cnt = pstmt.executeUpdate();
			if(cnt == 1) {
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
		finally {
			try
			{
				System.out.println("AutoCommit Setting True");
				con.setAutoCommit(true);
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();
				}								
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}					
		}	
		return cnt;
	}
	
	
	public boolean isEmp(String i_empno) {
		boolean exist = false;
		
		Connection con = dbCon();
		
		Statement stmt = null;
		
		ResultSet rs = null;
		
		String sql = "SELECT empno FROM emp WHERE empno =" +i_empno;		
		
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

	// Connection 생성 메소드 구성

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
