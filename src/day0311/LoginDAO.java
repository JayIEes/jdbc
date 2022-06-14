package day0311;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kr.co.sist.dao.DbConnection;
import oracle.jdbc.internal.OracleTypes;

public class LoginDAO {

	
	
	public String useStatement(LoginVO lVO) throws SQLException{ //injection은 statement에서 발생
		
		String name="";
		
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		DbConnection dc=DbConnection.getInstance();
		try{
		//1.
		//2.
			con=dc.getConn();
		//3.
			stmt=con.createStatement();
		//4.
			StringBuilder selectLogin=new StringBuilder();
			selectLogin
			.append("	select name ")
			.append("	from test_login ")
			.append("	where id='").append(lVO.getId())
			.append("' and password='").append(lVO.getPassword()).append("'");
			
			rs=stmt.executeQuery(selectLogin.toString());
			
			System.out.println(selectLogin);
			if(rs.next()) {
				name=rs.getString("name");
			}//end if
			
			//5.
		//6.
		}finally {
		//7.
			dc.close(rs, stmt, con);
			
		}//end finally
		return name;
	}//useStatement
	
	
	
	
	public String blockInjection(String sql) {
		
		if(sql!=null && !"".equals(sql)) {
			//injection에 관련된 문자열을 치환
			sql=sql.replaceAll("select", "").replaceAll("union", "")
					.replaceAll(" ", "").replaceAll("'", "").replaceAll("--", "");
		}//end if
		
		return sql;
	}//blockInjection
	
	
	
	public String usePreparedStatement(LoginVO lVO) throws SQLException{
		String name="";
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConnection dc=DbConnection.getInstance();
		try{
		//1.
		//2.
			con=dc.getConn();
		//3.
			String selectLogin="select name from test_login where id=? and password=?";
			pstmt=con.prepareStatement(selectLogin);
		//4.
			pstmt.setString(1, lVO.getId());
			pstmt.setString(2, lVO.getPassword());
			
			//5.
			rs=pstmt.executeQuery();
			
			System.out.println(selectLogin);
			System.out.println(lVO.getId()+ lVO.getPassword());
			
			if(rs.next()) {
				name=rs.getString("name");
			}//end if
			
		}finally {
			//6.
			dc.close(rs, pstmt, con);
		}//end finally
		
		return name;
	}//usePreparedStatement
	
	
	
	
	/**
	 * procedure를 사용하여 로그인 처리를 수행하는 메소드
	 * @param lVO
	 * @return
	 */
	public String useProcedure(LoginVO lVO) throws SQLException {
		String name = "";
		
		Connection con=null;
		CallableStatement cstmt=null;
		
		DbConnection dc=DbConnection.getInstance();
		
		try {
		//1.
		//2.
			con=dc.getConn();
		//3.쿼리문 생성 객체 얻기
			cstmt=con.prepareCall("{ call login_procedure(?,?,?) }");
		//4.
			//in parameter
			cstmt.setString(1,lVO.getId());
			cstmt.setString(2,lVO.getPassword());
			//out parameter
			cstmt.registerOutParameter(3, OracleTypes.CURSOR);
		//5.쿼리문 실행
			cstmt.execute();
			ResultSet rs=(ResultSet) cstmt.getObject(3);
			
			while(rs.next()) {
				name=rs.getString("name");
			}
			
			//6.
		}finally{
			//7.
			dc.close(null, cstmt, con);
		}
		
		return name;
	}//useProcedure
	
	
}//class
