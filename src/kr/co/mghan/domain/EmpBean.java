package kr.co.mghan.domain;

public class EmpBean
{
	private int empno;
	private String ename;
	private int mgr;
	private String job;
	private String hiredate;
	private double sal;
	private double comm;
	private int deptno;

	// getter , setter
	public int getEmpno()
	{
		return empno;
	}

	public void setEmpno(int empno)
	{
		this.empno = empno;
	}

	public String getEname()
	{
		return ename;
	}

	public void setEname(String ename)
	{
		this.ename = ename;
	}

	public int getMgr()
	{
		return mgr;
	}

	public void setMgr(int mgr)
	{
		this.mgr = mgr;
	}

	public String getJob()
	{
		return job;
	}

	public void setJob(String job)
	{
		this.job = job;
	}

	public String getHiredate()
	{
		return hiredate;
	}

	public void setHiredate(String hiredate)
	{
		this.hiredate = hiredate;
	}

	public double getSal()
	{
		return sal;
	}

	public void setSal(double sal)	
	{		
		this.sal = sal;
	}

	public double getComm()
	{
		return comm;
	}

	public void setComm(double comm)
	{
		comm = getSal() * 0.1;
		this.comm = comm;
	}

	public int getDeptno()
	{
		return deptno;
	}

	public void setDeptno(int deptno)
	{
		this.deptno = deptno;
	}

}
