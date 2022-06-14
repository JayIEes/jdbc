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
 * DAO(Data Access Object) : DB작업 정의
 * method명은 사용하는 쿼리문을 넣어서 저장한다.
 */
public class StatementDAO {

	
	public StatementDAO() {
		
	}//StatementDAO
	
	
	/**
	 * DBMS와 연결한 객체를 반환하는 일
	 * @return Connection
	 * @throws SQLException
	 */
	private Connection getDbConnection() throws SQLException{
		
		Connection con=null;
		
		//1.드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		
		//2.Connection 얻기
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
		//1.드라이버 로딩
			
		//2.커넥션 얻기
			con=getDbConnection();
		
		//3.쿼리문 생성객체 얻기
			stmt=con.createStatement();
		
		//4.쿼리 수행 후 결과 얻기'
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
//			System.out.println(cnt+"건 입력성공");
			//insert는 1건 아니면 예외이므로 수행 행 수를 저장할 필요가 없다.
			stmt.executeUpdate( insertCpEmp.toString() );	
			
			
//			System.out.println(insertCpEmp4);
			System.out.println(insertCpEmp);
		}finally {
			
		//5.연결끊기
		if(stmt!=null) {stmt.close();}//end if
		if(con!=null) {con.close();}//end if
		
		}//end finally
		
	}//insertCpEmp4
	
	
	
	/**
	 * 사원정보 변경<br>
	 * 사원번호와 맞는 사원의 직무, 연봉, 보너스를 변경하는 일.
	 * @param ceuVO 변경할 사원정보를 가진 객체
	 * @return 변경된 행의 수
	 * @throws SQLException
	 */
	public int updateCpEmp4(CpEmp4UpdateVO ceuVO) throws SQLException  {
		
		int rowCnt=0;
		
		Connection con= null;
		Statement stmt=null;
		try {
			//1.드라이버 로딩
				
			//2.커넥션 얻기
				con=getDbConnection();
			
			//3.쿼리문 생성객체 얻기
				stmt=con.createStatement();
			
			//4.쿼리 수행 후 결과 얻기
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
				
			//5.연결끊기
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
			//1.드라이버 로딩
				
			//2.커넥션 얻기
				con=getDbConnection();
			
			//3.쿼리문 생성객체 얻기
				stmt=con.createStatement();
			
			//4.쿼리 수행 후 결과 얻기
				StringBuilder deleteCpEmp= new StringBuilder();
				deleteCpEmp.append("delete from cp_emp4 where empno=").append(empno);
				
				rowCnt=stmt.executeUpdate(deleteCpEmp.toString());
				
				
			}finally {
				
			//5.연결끊기
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
			//1.드라이버 로딩
			//2.커넥션 얻기
				con=getDbConnection();
			
			//3.쿼리문 생성객체 얻기
				stmt=con.createStatement();
			
			//4.쿼리 수행 후 결과 얻기
				StringBuilder selectCpEmp= new StringBuilder();
				
				selectCpEmp
				.append("select ename,job,sal,comm   ")
				.append("from cp_emp4                ")
				.append("where empno=").append(empno);
				
				
				rs=stmt.executeQuery(selectCpEmp.toString()); //rs에는 cursor의 제어권 
				
				if(rs.next()) { //사원번호로 검색된 레코드가 존재.
					
					//VO에 값을 할당
					cesoVO=new CpEmp4SelectOneVO();

					//cursor가 존재하는 레코드에 사원컬럼의 값을 가져와서 
					cesoVO.setEname(rs.getString("ename")); //varchar2->String
					cesoVO.setComm(rs.getDouble("comm"));//number->double
					cesoVO.setSal(rs.getInt("sal"));//number->int
					cesoVO.setJob(rs.getString("job"));//varchar2->String
					
				}//end if
				
			}finally {
				
			//5.연결끊기
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
			//1.드라이버 로딩
			//2.커넥션 얻기
				con=getDbConnection();
			
			//3.쿼리문 생성객체 얻기
				stmt=con.createStatement();
			
			//4.쿼리 수행 후 결과 얻기
				StringBuilder selectCpEmp= new StringBuilder();
				
				selectCpEmp
				.append("   select empno,ename,job,sal,comm,hiredate, to_char(hiredate,'yyyy-mm-dd') hiredate2")
				.append("   from cp_emp4")
				.append("  	order by empno");
				
				
				rs=stmt.executeQuery(selectCpEmp.toString()); //rs에는 cursor의 제어권 
				
				CpEmp4SelectAllVO cesaVO=null;
				
				while(rs.next()) { //레코드가 존재하면 
					
					
					//컬럼값을 하나의 VO에 할당
					cesaVO=new CpEmp4SelectAllVO();
					
					cesaVO.setEmpno(rs.getInt("empno"));//number->int
					cesaVO.setEname(rs.getString("ename")); //varchar2->String
					cesaVO.setJob(rs.getString("job"));//varchar2->String
					cesaVO.setSal(rs.getInt("sal"));//number->int
					cesaVO.setComm(rs.getDouble("comm"));//number->double
					cesaVO.setHiredate(rs.getDate("hiredate"));//date->java.sql.Date
					cesaVO.setHiredate2(rs.getString("hiredate2"));//date-> to_char()-> String
					
					
					//레코드 하나를 가진 VO를 List에 추가
					list.add(cesaVO);
					
					
				}//end if
				
			}finally {
				
			//5.연결끊기
			if(rs!=null) {rs.close();}//end if
			if(stmt!=null) {stmt.close();}//end if
			if(con!=null) {con.close();}//end if
			
			}//end finally
		
		return list;
	}//selectCpEmp4
	
	
	//Debug용 method
//	public static void main(String[] args) throws SQLException {
//		
//		//단위 Test

//		
//		sd.selectCpEmp4();
		
//	}

}//class
