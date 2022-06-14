package day0310;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import kr.co.sist.dao.DbConnection;

public class UseTestCProc {
	
	
	
	public UseTestCProc() throws SQLException{
		
		Connection con=null;
		CallableStatement cstmt=null;
		
		DbConnection dc=DbConnection.getInstance();
		
		try {
		//1.����̹� �ε�
		//2.Ŀ�ؼ� ���
			con=dc.getConn();
		//3.������ ������ü ���
			cstmt=con.prepareCall("{ call test_proc(?,?,?,?,?) }");
		//4.���ε� ���� �� ����
			//in parameter (1-�̸�,2-����,3-�̸���) : procedure�� �ִ� ��
			cstmt.setString(1, "����");
			cstmt.setInt(2, 27);
			cstmt.setString(3, "kang@naver.com");
			
			//out parameter (4-�¾ ��,5-������) : procedure���� ��� ��.
			cstmt.registerOutParameter(4, Types.NUMERIC);
			cstmt.registerOutParameter(5, Types.VARCHAR);
		//5.������ ����
			cstmt.execute();
		//6.out parameter�� ����� �� ���
			int birth=cstmt.getInt(4);
			String domain=cstmt.getString(5);
			
			System.out.println("�¾ �� :"+birth);
			System.out.println("������ :"+domain);
		}finally {
		//7.�������
			dc.close(null, cstmt, con);
		}
		
	}//UseTestCProc
	
	
	

	public static void main(String[] args) {
		try {
			new UseTestCProc();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//main

}//class
