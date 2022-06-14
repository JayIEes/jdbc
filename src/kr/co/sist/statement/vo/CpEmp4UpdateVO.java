package kr.co.sist.statement.vo;

import java.sql.Date;

public class CpEmp4UpdateVO {

	private int empno, sal;
	private String job;
	private double comm;
	
	public CpEmp4UpdateVO() {
		
	}//CpEmp4UpdateVO

	public CpEmp4UpdateVO(int empno, int sal, String job, double comm) {
		super();
		this.empno = empno;
		this.sal = sal;
		this.job = job;
		this.comm = comm;
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
	
	

}//class
