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
		ResultSetMetaData rsmd=null; // close�ϴ� ��ü�� �ƴϴ�.
		
		DbConnection dc=DbConnection.getInstance();
		
		try {
		//1.����̹� �ε�
		//2.Ŀ�ؼ� ���
			con=dc.getConn();
		//3.������ ������ü ���
			StringBuilder selectQuery=new StringBuilder();
			selectQuery.append("	select * from ").append(tname); //���̺�� ���ε� ������ �ȵ�
		
			pstmt=con.prepareStatement(selectQuery.toString());
		//4.
		//5.
			rs=pstmt.executeQuery();
			//����� select query���� �÷��� ������ ��� ���� ResultSetMetaData ���
			rsmd=rs.getMetaData();
			//�÷��� ���� ���
			System.out.println(tname+"���̺�");
//			System.out.println("�÷��� ���� : "+rsmd.getColumnCount());
//			System.out.println("ó�� �÷��� : "+rsmd.getColumnLabel(1));
//			System.out.println("ó�� �÷��� ���������� : "+rsmd.getColumnTypeName(1));
//			System.out.println("ó�� �÷��� ũ�� : "+rsmd.getPrecision(1));
			
			for(int i=1;i<rsmd.getColumnCount()+1;i++) {
				output
				.append(rsmd.getColumnLabel(i)).append("\t")
				.append(rsmd.getColumnTypeName(i)).append("\t")
				.append(rsmd.getPrecision(i)).append("\t")
				.append(rsmd.isNullable(i)==0?"not null":"").append("\t").append("\n");
			}//end for
			
		}finally {
		//6.���� ����
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
