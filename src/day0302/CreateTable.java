package day0302;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author user
 * Statement.execute();를 사용한 쿼리문의 실행
 */
public class CreateTable {

		public CreateTable() throws SQLException {
			//1.드라이버 로딩
			try {
				Class.forName("oracle.jdbc.OracleDriver");//classpath 또는 build path가 필요
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}//end catch
			
			Connection con=null;
			Statement stmt=null;
			
			try {
			
			//2.Connection 얻기
			String url="jdbc:oracle:thin:@localhost:1521:orcl";
			String id="scott";
			String pass="tiger";
			con=DriverManager.getConnection(url, id, pass);
			
			//3.쿼리문 생성 객체 얻기
			stmt=con.createStatement();
			
			//4.쿼리 수행 후 결과 얻기
//			String createQuery
//			="create table test(num number, name varchar2(30),input_date date default sysdate)";
			String createQuery="drop table test";
			stmt.execute(createQuery); //쿼리실행결과를 받을 수 있으면 true, 받을 수 없으면 false가 리턴
			//쿼리의 실행이 정상적이라면 조회된 결과가 없어 false가 리턴, 쿼리의 실행이 실패하면 예외가 리턴된다.
//			if(flag) {
//				System.out.println(flag);
				System.out.println("테이블 생성 성공 : "+stmt.getQueryTimeout());
//			}
//			
			}finally {
			//5.연결끊기
				if(stmt!=null) {stmt.close();}//end if
				if(con!=null) {con.close();}//end if
			}//end finally
		}
	
	public static void main(String[] args) {
		try {
			new CreateTable();
		} catch (SQLException se) {
			System.err.println("테이블 생성 실패");
			se.printStackTrace();
		}
	}

}
