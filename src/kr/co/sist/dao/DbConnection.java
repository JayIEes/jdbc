package kr.co.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author user
 * Singleton pattern�� ������ Ŭ����
 * connection ���, ����
 */
public class DbConnection {

	private static DbConnection dc;
	
	/**
	 * Ŭ���� �ܺο��� ��üȭ�ϴ� ���� ���´�.
	 */
	private DbConnection() { 
		
	}//DbConnection
	
	
	/**
	 * DbConnection ��ü�� ��ȯ�ϴ� ��
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
		//1.����̹� �ε�
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		
		//2.Ŀ�ؼ� ���
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
	 * DBMS ���� ����
	 * @param rs
	 * @param stmt
	 * @param con
	 * @throws SQLException
	 */
	public void close(ResultSet rs, Statement stmt, Connection con) throws SQLException{
		//������ �߸������̽��ϴ�.
		if(rs!=null) {rs.close();} //end if 
		if(stmt!=null) {stmt.close();} //end if
		if(con!=null) {con.close();} //end if
	}//close
	
	
}//class
