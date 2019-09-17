package kr.co.mghan.domain;

public class DeptBean
{
	private int deptno;	
	private String deptname;
	private String loc;
	
	public int getDeptno()
	{
		return deptno;
	}
	public void setDeptno(int deptno)
	{
		this.deptno = deptno;
	}
	public String getDeptname()
	{
		return deptname;
	}
	public void setDeptname(String deptname)
	{
		this.deptname = deptname;
	}
	public String getLoc()
	{
		return loc;
	}
	public void setLoc(String loc)
	{
		this.loc = loc;
	}
}
