package kr.co.sist.statement.vo;

public class CpEmp4SelectOneVO {

	private int sal;
	private String ename, job;
	private double comm;
	
	public CpEmp4SelectOneVO() {
		
	}

	public CpEmp4SelectOneVO(int sal, String ename, String job, double comm) {
		super();
		this.sal = sal;
		this.ename = ename;
		this.job = job;
		this.comm = comm;
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
	
}
