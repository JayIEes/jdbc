package kr.co.sist.statement.vo;

import java.sql.Date;

public class CpEmp4SelectAllVO {

	private int empno, sal ;
	private String ename,job, hiredate2;
	private double comm;
	private Date hiredate;
	
	public CpEmp4SelectAllVO() {
		
	}

	public CpEmp4SelectAllVO(int empno, int sal, String ename, String job, String hiredate2, double comm,
			Date hiredate) {
		super();
		this.empno = empno;
		this.sal = sal;
		this.ename = ename;
		this.job = job;
		this.hiredate2 = hiredate2;
		this.comm = comm;
		this.hiredate = hiredate;
	}

	/**
	 * @return the empno
	 */
	public int getEmpno() {
		return empno;
	}

	/**
	 * @param empno the empno to set
	 */
	public void setEmpno(int empno) {
		this.empno = empno;
	}

	/**
	 * @return the sal
	 */
	public int getSal() {
		return sal;
	}

	/**
	 * @param sal the sal to set
	 */
	public void setSal(int sal) {
		this.sal = sal;
	}

	/**
	 * @return the ename
	 */
	public String getEname() {
		return ename;
	}

	/**
	 * @param ename the ename to set
	 */
	public void setEname(String ename) {
		this.ename = ename;
	}

	/**
	 * @return the job
	 */
	public String getJob() {
		return job;
	}

	/**
	 * @param job the job to set
	 */
	public void setJob(String job) {
		this.job = job;
	}

	/**
	 * @return the hiredate2
	 */
	public String getHiredate2() {
		return hiredate2;
	}

	/**
	 * @param hiredate2 the hiredate2 to set
	 */
	public void setHiredate2(String hiredate2) {
		this.hiredate2 = hiredate2;
	}

	/**
	 * @return the comm
	 */
	public double getComm() {
		return comm;
	}

	/**
	 * @param comm the comm to set
	 */
	public void setComm(double comm) {
		this.comm = comm;
	}

	/**
	 * @return the hiredate
	 */
	public Date getHiredate() {
		return hiredate;
	}

	/**
	 * @param hiredate the hiredate to set
	 */
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}


	

	
	
}
