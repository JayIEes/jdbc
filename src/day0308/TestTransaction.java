package day0308;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.sist.dao.DbConnection;
import kr.co.sist.prepared.vo.ClobVO;
import kr.co.sist.prepared.vo.CpEmp5VO;

public class TestTransaction {

	private Connection con;
	
	
	
	public int transaction(CpEmp5VO ceVO) throws SQLException{
		int rowCnt=0;
		
		DbConnection dc=DbConnection.getInstance();
		
		//1.����̹� �ε�
		//2.Ŀ�ؼ� ���
			con=dc.getConn();
			con.setAutoCommit(false);//auto commit����
			PreparedStatement pstmt=null;
			PreparedStatement pstmt2=null;
		
			//3.������ ������ü ���
			String insertTransaction1="insert into test_transaction1(num,name) values(?,?)";
		
			pstmt=con.prepareStatement(insertTransaction1);
		//4.
			pstmt.setInt(1, ceVO.getEmpno());
			pstmt.setString(2, ceVO.getEname());
			
		///////////////////////////////////////////////////////////2�� ����
			
			//3.������ ������ü ���
			String insertTransaction2="	insert into test_transaction2(num,name) values(?,?)";
		
			pstmt2=con.prepareStatement(insertTransaction2);
		//4.
			pstmt2.setInt(1, ceVO.getEmpno());
			pstmt2.setString(2, ceVO.getEname());
			
			
			//5.
			int cnt1=pstmt.executeUpdate();
			int cnt2=pstmt2.executeUpdate();
			
			rowCnt=cnt1+cnt2;
			
			return rowCnt;
	}//transaction
	
	
	public void useTransaction() throws SQLException{
		//�� �߻�
		CpEmp5VO ceVO=new CpEmp5VO();
		ceVO.setEmpno(2);
		ceVO.setEname("��������������");
		
		try {
			int totalCnt=transaction(ceVO);
			if(totalCnt==2) {//insert�� �ι� ����
				System.out.println("�߰� �Ǿ����ϴ�.");
				con.commit();//test_transaction1�� test_transaction2 ��� �߰�
			}
		} catch (SQLException e) {
			System.out.println("��� �Ǿ����ϴ�.");
			con.rollback();//test_transaction1�� test_transaction2 ��� ����
			e.printStackTrace();
		}finally {
			if(con!=null) {con.close();}//end if
		}//end finally
		
		
	}
	
	
	public static void main(String[] args) {
		TestTransaction tt=new TestTransaction();
		try {
			tt.useTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			 
	}//main

}//class
