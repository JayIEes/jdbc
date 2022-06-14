package kr.co.sist.statement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.statement.vo.CpEmp4InsertVO;
import kr.co.sist.statement.vo.CpEmp4SelectAllVO;
import kr.co.sist.statement.vo.CpEmp4SelectOneVO;
import kr.co.sist.statement.vo.CpEmp4UpdateVO;

/**
 * @author user
 * DAO(Data Access Object) : DB�۾� ����
 * method���� ����ϴ� �������� �־ �����Ѵ�.
 */
public class StatementDAO {

	
	public StatementDAO() {
		
	}//StatementDAO
	
	
	/**
	 * DBMS�� ������ ��ü�� ��ȯ�ϴ� ��
	 * @return Connection
	 * @throws SQLException
	 */
	private Connection getDbConnection() throws SQLException{
		
		Connection con=null;
		
		//1.����̹� �ε�
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		
		//2.Connection ���
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String id="scott";
		String pass="tiger";
		
		con=DriverManager.getConnection(url, id, pass);
		
		return con;
	}//getDbConnection
	
	
	
	
	public void insertCpEmp4(CpEmp4InsertVO ceiVO) throws SQLException {
		
		Connection con= null;
		Statement stmt=null;
		
		try {
		//1.����̹� �ε�
			
		//2.Ŀ�ؼ� ���
			con=getDbConnection();
		
		//3.������ ������ü ���
			stmt=con.createStatement();
		
		//4.���� ���� �� ��� ���'
//			String insertCpEmp4="insert cp_emp4(empno,ename,job,sal,comm) values("
//					+ ceiVO.getEmpno()+",'"+ceiVO.getEname()+"','"+ceiVO.getJob()
//					+"',"+ceiVO.getSal()+","+ceiVO.getComm()+")";
			
			StringBuilder insertCpEmp= new StringBuilder();
			insertCpEmp
			.append("insert into cp_emp4(empno,ename,job,sal,comm) values(")
			.append(ceiVO.getEmpno()).append(",'")
			.append(ceiVO.getEname()).append("','")
			.append(ceiVO.getJob()).append("',")
			.append(ceiVO.getSal()).append(",")
			.append(ceiVO.getComm()).append(")");
			
//			int cnt=stmt.executeUpdate(insertCpEmp.toString());
//			System.out.println(cnt+"�� �Է¼���");
			//insert�� 1�� �ƴϸ� �����̹Ƿ� ���� �� ���� ������ �ʿ䰡 ����.
			stmt.executeUpdate( insertCpEmp.toString() );	
			
			
//			System.out.println(insertCpEmp4);
			System.out.println(insertCpEmp);
		}finally {
			
		//5.�������
		if(stmt!=null) {stmt.close();}//end if
		if(con!=null) {con.close();}//end if
		
		}//end finally
		
	}//insertCpEmp4
	
	
	
	/**
	 * ������� ����<br>
	 * �����ȣ�� �´� ����� ����, ����, ���ʽ��� �����ϴ� ��.
	 * @param ceuVO ������ ��������� ���� ��ü
	 * @return ����� ���� ��
	 * @throws SQLException
	 */
	public int updateCpEmp4(CpEmp4UpdateVO ceuVO) throws SQLException  {
		
		int rowCnt=0;
		
		Connection con= null;
		Statement stmt=null;
		try {
			//1.����̹� �ε�
				
			//2.Ŀ�ؼ� ���
				con=getDbConnection();
			
			//3.������ ������ü ���
				stmt=con.createStatement();
			
			//4.���� ���� �� ��� ���
				StringBuilder updateCpEmp= new StringBuilder();
				updateCpEmp
				.append("   update cp_emp4    ")
				.append("  set  job='").append(ceuVO.getJob()).append("',")
				.append("  sal=").append(ceuVO.getSal()).append(",")
				.append("  comm=").append(ceuVO.getComm())
				.append("  where empno=").append(ceuVO.getEmpno());
				System.out.println(updateCpEmp);
				
				rowCnt=stmt.executeUpdate(updateCpEmp.toString());
				
			}finally {
				
			//5.�������
			if(stmt!=null) {stmt.close();}//end if
			if(con!=null) {con.close();}//end if
			
			}//end finally
		
		
		return rowCnt;
	}//updateCpEmp4
	
	
	
	public int deleteCpEmp4(int empno)throws SQLException  {
		int rowCnt=0;
		
		Connection con= null;
		Statement stmt=null;
		try {
			//1.����̹� �ε�
				
			//2.Ŀ�ؼ� ���
				con=getDbConnection();
			
			//3.������ ������ü ���
				stmt=con.createStatement();
			
			//4.���� ���� �� ��� ���
				StringBuilder deleteCpEmp= new StringBuilder();
				deleteCpEmp.append("delete from cp_emp4 where empno=").append(empno);
				
				rowCnt=stmt.executeUpdate(deleteCpEmp.toString());
				
				
			}finally {
				
			//5.�������
			if(stmt!=null) {stmt.close();}//end if
			if(con!=null) {con.close();}//end if
			
			}//end finally
		
		
		
		return rowCnt;
	}//deleteCpEmp4
	
	
	
	
	public CpEmp4SelectOneVO selectCpEmp4(int empno) throws SQLException{
		
		CpEmp4SelectOneVO cesoVO= null;
		
		Connection con= null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			//1.����̹� �ε�
			//2.Ŀ�ؼ� ���
				con=getDbConnection();
			
			//3.������ ������ü ���
				stmt=con.createStatement();
			
			//4.���� ���� �� ��� ���
				StringBuilder selectCpEmp= new StringBuilder();
				
				selectCpEmp
				.append("select ename,job,sal,comm   ")
				.append("from cp_emp4                ")
				.append("where empno=").append(empno);
				
				
				rs=stmt.executeQuery(selectCpEmp.toString()); //rs���� cursor�� ����� 
				
				if(rs.next()) { //�����ȣ�� �˻��� ���ڵ尡 ����.
					
					//VO�� ���� �Ҵ�
					cesoVO=new CpEmp4SelectOneVO();

					//cursor�� �����ϴ� ���ڵ忡 ����÷��� ���� �����ͼ� 
					cesoVO.setEname(rs.getString("ename")); //varchar2->String
					cesoVO.setComm(rs.getDouble("comm"));//number->double
					cesoVO.setSal(rs.getInt("sal"));//number->int
					cesoVO.setJob(rs.getString("job"));//varchar2->String
					
				}//end if
				
			}finally {
				
			//5.�������
			if(rs!=null) {rs.close();}//end if
			if(stmt!=null) {stmt.close();}//end if
			if(con!=null) {con.close();}//end if
			
			}//end finally
		
		
		return cesoVO;
	}//selectCpEmp4

	
	public List<CpEmp4SelectAllVO> selectCpEmp4() throws SQLException{
		 
		List<CpEmp4SelectAllVO> list= new ArrayList<CpEmp4SelectAllVO>();
		

		Connection con= null;
		Statement stmt=null;
		
		ResultSet rs=null;
		
		try {
			//1.����̹� �ε�
			//2.Ŀ�ؼ� ���
				con=getDbConnection();
			
			//3.������ ������ü ���
				stmt=con.createStatement();
			
			//4.���� ���� �� ��� ���
				StringBuilder selectCpEmp= new StringBuilder();
				
				selectCpEmp
				.append("   select empno,ename,job,sal,comm,hiredate, to_char(hiredate,'yyyy-mm-dd') hiredate2")
				.append("   from cp_emp4")
				.append("  	order by empno");
				
				
				rs=stmt.executeQuery(selectCpEmp.toString()); //rs���� cursor�� ����� 
				
				CpEmp4SelectAllVO cesaVO=null;
				
				while(rs.next()) { //���ڵ尡 �����ϸ� 
					
					
					//�÷����� �ϳ��� VO�� �Ҵ�
					cesaVO=new CpEmp4SelectAllVO();
					
					cesaVO.setEmpno(rs.getInt("empno"));//number->int
					cesaVO.setEname(rs.getString("ename")); //varchar2->String
					cesaVO.setJob(rs.getString("job"));//varchar2->String
					cesaVO.setSal(rs.getInt("sal"));//number->int
					cesaVO.setComm(rs.getDouble("comm"));//number->double
					cesaVO.setHiredate(rs.getDate("hiredate"));//date->java.sql.Date
					cesaVO.setHiredate2(rs.getString("hiredate2"));//date-> to_char()-> String
					
					
					//���ڵ� �ϳ��� ���� VO�� List�� �߰�
					list.add(cesaVO);
					
					
				}//end if
				
			}finally {
				
			//5.�������
			if(rs!=null) {rs.close();}//end if
			if(stmt!=null) {stmt.close();}//end if
			if(con!=null) {con.close();}//end if
			
			}//end finally
		
		return list;
	}//selectCpEmp4
	
	
	//Debug�� method
//	public static void main(String[] args) throws SQLException {
//		
//		//���� Test

//		
//		sd.selectCpEmp4();
		
//	}

}//class
