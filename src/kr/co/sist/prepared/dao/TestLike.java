package kr.co.sist.prepared.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.DbConnection;
import kr.co.sist.prepared.vo.ZipcodeVO;

/**
 * @author user
 * ���ε� ������ % ��ȣ�� ���
 */
public class TestLike {

	public List<ZipcodeVO> selectZipcode(String dong) throws SQLException {
		
		List<ZipcodeVO> list=new ArrayList<ZipcodeVO>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConnection dc=DbConnection.getInstance();
		
		try{
		//1.����̹� �ε�
		//2.Ŀ�ؼ� ���
			con=dc.getConn();
		
		//3.������ ������ü ���
			StringBuilder selectZipcode=new StringBuilder();
			selectZipcode
			.append("	select zipcode,sido,gugun,dong,bunji	")
			.append("	from zipcode							")
			.append("	where dong like ?||'%'					");
			
			pstmt=con.prepareStatement(selectZipcode.toString());
		
		//4.���� ���� �� ��� ���
			pstmt.setString(1, dong);//�νĺҰ� ?%
		
		//5.
			rs=pstmt.executeQuery();
			
			ZipcodeVO zVO=null;
			while(rs.next()) {//��ȸ�� �����ȣ ����
				zVO=new ZipcodeVO();
				zVO.setZipcode(rs.getString("zipcode"));
				zVO.setSido(rs.getString("sido"));
				zVO.setGugun(rs.getString("gugun"));
				zVO.setDong(rs.getString("dong"));
				zVO.setBunji(rs.getString("bunji"));
				
				//����Ʈ �߰�
				list.add(zVO);
			}//end while
			
		}finally {
		//6.���� ����
			dc.close(rs, pstmt, con);
		}//end finally
		return list;
	}//selectZipcode
	
	
	public static void main(String[] args) {
		TestLike tl=new TestLike();
		try {
			System.out.println(tl.selectZipcode("�Ⳳ"));
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//main

}//class
