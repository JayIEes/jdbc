package kr.co.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author user
 * Singleton pattern을 도입한 클래스
 * connection 얻기, 끊기
 */
public class DbConnection {

	private static DbConnection dc;
	
	/**
	 * 클래스 외부에서 객체화하는 것을 막는다.
	 */
	private DbConnection() { 
		
	}//DbConnection
	
	
	/**
	 * DbConnection 객체를 반환하는 일
	 * @return
	 */
	public static DbConnection getInstance() {
		
		if(dc==null) {
			dc=new DbConnection();
		}//end if
		
		return dc;
	}//getInstance
	
	
	
	
	public Connection getConn() throws SQLException{
		
		Connection con=null;
		//1.드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		
		//2.커넥션 얻기
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String id="scott";
		String pass="tiger";
		
		con=DriverManager.getConnection(url, id, pass);
		
		return con;
		
	}//getConn
	
	
	
	public Statement getStatement() throws SQLException {
		Statement stmt= null;
		stmt=getConn().createStatement();
		return stmt;
	}//getStatement
	
	
	
	/**
	 * DBMS 연결 종료
	 * @param rs
	 * @param stmt
	 * @param con
	 * @throws SQLException
	 */
	public void close(ResultSet rs, Statement stmt, Connection con) throws SQLException{
		//연결을 잘못끊으셨습니다.
		if(rs!=null) {rs.close();} //end if 
		if(stmt!=null) {stmt.close();} //end if
		if(con!=null) {con.close();} //end if
	}//close
	
	
}//class
