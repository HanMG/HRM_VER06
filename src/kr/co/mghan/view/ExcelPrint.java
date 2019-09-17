package kr.co.mghan.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class ExcelPrint
{
	public void setXls() {
		
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
		
		HSSFWorkbook wb = new HSSFWorkbook(); // 워크북 생성
		HSSFSheet hs = wb.createSheet(); // 워크 시트 생성
		
		HSSFRow hr = hs.createRow(0); // 행 생성
		HSSFCell hc; // 셀 생성
		
		Date time = new Date(); 
        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        String now = format1.format(time);
		
		// 헤더 정보 구성
        hc = hr.createCell(0);
        hc.setCellValue("사원번호");        
        hc = hr.createCell(1);
        hc.setCellValue("사원명");        
        hc = hr.createCell(2);
        hc.setCellValue("상급자 사원번호");        
        hc = hr.createCell(3);
        hc.setCellValue("직책");        
        hc = hr.createCell(4);
        hc.setCellValue("고용일자");
        hc = hr.createCell(5);
        hc.setCellValue("월급");
        hc = hr.createCell(6);
        hc.setCellValue("보너스");
        hc = hr.createCell(7);
        hc.setCellValue("부서번호");     
        
        int rowIdx=0;
        String sql = "SELECT empno, ename, mgr, job, hiredate, sal, comm, deptno FROM emp";
        try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			System.out.println("------조회된 사원 결과------");			
			while (rs.next())
			{				
				hr = hs.createRow(rowIdx+1);
				hc = hr.createCell(0); // 사원번호 컬럼에
	            hc.setCellValue(rs.getInt(1)); // 사원번호 값을 가져와 전달
	            
	            hc = hr.createCell(1); // 사원명 컬럼에
	            hc.setCellValue(rs.getString(2)); // 사원명 값을 가져와 전달
	            
	            hc = hr.createCell(2); // 상급자 사원번호 컬럼에
	            hc.setCellValue(rs.getInt(3)); // 상급자 사원번호 값을 가져와 전달
	            
	            hc = hr.createCell(3); // 직책 컬럼에
	            hc.setCellValue(rs.getString(4)); // 직책 값을 가져와 전달
	            
	            hc = hr.createCell(4);
	            hc.setCellValue(rs.getString(5));
	            
	            hc = hr.createCell(5);
	            hc.setCellValue(rs.getDouble(6));
	            
	            hc = hr.createCell(6);
	            hc.setCellValue(rs.getDouble(7));
	            
	            hc = hr.createCell(7);
	            hc.setCellValue(rs.getInt(8));  
				
				rowIdx++;
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
        		
        
        // 마지막 행 다다음 행 8번째 셀에 생성된 시각을 기입
        hr = hs.createRow(rowIdx+1); 
        hc = hr.createCell(8); 
        hc.setCellValue(now); 
        
        
		
		// 특정위치에 파일생성
		File file = new File("EmpSheet.xls");
		
		if(file.exists()) {
			try
			{
				file.createNewFile();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		FileOutputStream fos = null;
		try
		{
			fos = new FileOutputStream(file);
			wb.write(fos);
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try
			{
				wb.close();
				fos.close();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
				
	}
}
