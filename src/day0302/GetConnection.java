package day0302;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author user
 *	JDBC를 사용한 Connection 얻기
 */
public class GetConnection {

	public GetConnection() throws SQLException {
		//1.드라이버 로딩 (e:/dev/driver/ojdbc8.jar)
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("드라이버 로딩 성공");
		} catch (ClassNotFoundException e) {//Compile exception (Checked Exception)- 개발자가 반드시 확인해야함
			//Runtime Exception(Unchecked Exception) 
			e.printStackTrace();
		}//end catch
		
		//2. 로딩된 드라이버 사용하여 Connection 얻기
//		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String url="jdbc:oracle:thin:@127.0.0.1:1521:orcl"; //localhost, 127.0.0.1
		String id="scott";
		String pass="tiger";
		
		Connection con=null;
		Statement stmt=null;
		try {
			con=DriverManager.getConnection(url, id, pass);
			System.out.println("DB연결 성공 : "+con);
			
			//3.쿼리문 생성객체 얻기
			stmt=con.createStatement();
			
			//4.쿼리문 실행 후 결과 얻기
			String dname="개발부";
			String insertQuery="insert into cp_dept(deptno,dname,loc) values(99,'"+dname+"','서울')";
			//홀따옴표 잘 붙여서! //자바에서 쿼리문 쓸 때에는 ;를 절대 넣지 말아야한다.
			int cnt=stmt.executeUpdate(insertQuery);
			System.out.println(insertQuery+"로 " +cnt+"건 추가 되었습니다.");
			
		}finally{
			//5. 연결끊기
			if(stmt!=null) {stmt.close();}//end if
			if(con!=null) {con.close();}//end if
		}//end finally
		
		
		
	}//GetConnection
	
	public static void main(String[] args) {
		try {
			new GetConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}
	
	
}