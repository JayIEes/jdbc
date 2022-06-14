package day0308;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import kr.co.sist.dao.DbConnection;

public class UseResultSetMetaData {

	public String useResultSetMetaData(String tname) throws SQLException {
		
		StringBuilder output=new StringBuilder();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ResultSetMetaData rsmd=null; // close하는 객체가 아니다.
		
		DbConnection dc=DbConnection.getInstance();
		
		try {
		//1.드라이버 로딩
		//2.커넥션 얻기
			con=dc.getConn();
		//3.쿼리문 생성객체 얻기
			StringBuilder selectQuery=new StringBuilder();
			selectQuery.append("	select * from ").append(tname); //테이블명 바인드 변수로 안됨
		
			pstmt=con.prepareStatement(selectQuery.toString());
		//4.
		//5.
			rs=pstmt.executeQuery();
			//실행된 select query문에 컬럼의 정보를 얻기 위해 ResultSetMetaData 사용
			rsmd=rs.getMetaData();
			//컬럼의 정보 얻기
			System.out.println(tname+"테이블");
//			System.out.println("컬럼의 개수 : "+rsmd.getColumnCount());
//			System.out.println("처음 컬럼명 : "+rsmd.getColumnLabel(1));
//			System.out.println("처음 컬럼의 데이터형명 : "+rsmd.getColumnTypeName(1));
//			System.out.println("처음 컬럼의 크기 : "+rsmd.getPrecision(1));
			
			for(int i=1;i<rsmd.getColumnCount()+1;i++) {
				output
				.append(rsmd.getColumnLabel(i)).append("\t")
				.append(rsmd.getColumnTypeName(i)).append("\t")
				.append(rsmd.getPrecision(i)).append("\t")
				.append(rsmd.isNullable(i)==0?"not null":"").append("\t").append("\n");
			}//end for
			
		}finally {
		//6.연결 끊기
			dc.close(rs, pstmt, con);
		}//end finally
		
		return output.toString();
	}//useResultSetMetaData
	
	
	
	public static void main(String[] args) {
		UseResultSetMetaData ursmd= new UseResultSetMetaData();
		String tname="EMP";
		try {
			System.out.println(ursmd.useResultSetMetaData(tname));
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//main

}//class
