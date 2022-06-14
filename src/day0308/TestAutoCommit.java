package day0308;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.sist.dao.DbConnection;
import kr.co.sist.prepared.vo.ClobVO;

/**
 * @author user
 * auto commit�� ���� ���� ��.
 */
public class TestAutoCommit {

	
	
	/**
	 * autocommit����
	 * @param cVO
	 * @throws SQLException
	 */
	public void insertTestClob(ClobVO cVO) throws SQLException{

		Connection con=null;
		PreparedStatement pstmt=null;
		
		DbConnection dc=DbConnection.getInstance();
		
		try {
		//1.����̹� �ε�
		//2.Ŀ�ؼ� ���
			con=dc.getConn();
			
			// autocommit ����
			con.setAutoCommit(false);
			
		//3.������ ������ü ���
			String insert="insert into test_clob(subject,content) values(?,?)";

			pstmt=con.prepareStatement(insert);
		//4.
			pstmt.setString(1,cVO.getSubject());
			pstmt.setString(2,cVO.getContent());
			
		//5.
			int cnt=pstmt.executeUpdate();
			System.out.println(cnt+"�� �߰�");
		
			if(cnt==1) {//�߰��� ���� �ϳ��� commit�� ���� 
				con.commit();
			}//end if
			
		}finally {
		//6.���� ����
//			dc.close(null, pstmt, con);//close�� �Ǹ� DB�۾��� �ϵ��ũ�� ����ϰ� ������ ���´�.
		}//end finally
	}//insertTestClob
	
	
	public static void main(String[] args) {
		ClobVO cVO=new ClobVO();
		cVO.setSubject("������ ������ �Դϴ�.");
		cVO.setContent("����.");
	
		TestAutoCommit tac=new TestAutoCommit();
		try {
			tac.insertTestClob(cVO);//�߰� �۾� ��
			System.out.println("�߰�����");
		} catch (SQLException se) {
			se.printStackTrace();
		}//end catch
	}//main


}
