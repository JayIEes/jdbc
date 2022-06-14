package kr.co.sist.prepared.vo;

public class CpEmp5VO {
	
	private int empno,sal,comm,deptno ;
	private String ename;
	
	
	public CpEmp5VO() {
	}

	public CpEmp5VO(int empno, int sal, int comm, int deptno, String ename) {
		super();
		this.empno = empno;
		this.sal = sal;
		this.comm = comm;
		this.deptno = deptno;
		this.ename = ename;
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
	 * @return the comm
	 */
	public int getComm() {
		return comm;
	}

	/**
	 * @param comm the comm to set
	 */
	public void setComm(int comm) {
		this.comm = comm;
	}

	/**
	 * @return the deptno
	 */
	public int getDeptno() {
		return deptno;
	}

	/**
	 * @param deptno the deptno to set
	 */
	public void setDeptno(int deptno) {
		this.deptno = deptno;
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

	@Override
	public String toString() {
		return "CpEmp5VO [empno=" + empno + ", sal=" + sal + ", comm=" + comm + ", deptno=" + deptno + ", ename="
				+ ename + "]";
	}
	
	

}//class
