package day0314;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.DbConnection;

public class ZipcodeDAO {

	private static ZipcodeDAO zDAO;
		
	private ZipcodeDAO() {
		
	}
	
	public static ZipcodeDAO getInstance() {
		
		if(zDAO==null) {
			zDAO=new ZipcodeDAO();
		}//end if
		
		return zDAO;
	}//getInstance
	
	public List<ZipcodeVO> selectStatement(String dong)throws SQLException{
		List<ZipcodeVO> list= new  ArrayList<ZipcodeVO>();
		
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		DbConnection dc=DbConnection.getInstance();
		try {
		//1.
		//2.
			con=dc.getConn();
		//3.
			stmt=con.createStatement();
		//4.
			StringBuilder selectZipcode=new StringBuilder();
			selectZipcode
			.append("	select zipcode,sido,gugun,dong,nvl(bunji,' ') bunji	")
			.append("	from zipcode	")
//			.append("	where dong like '").append(dong).append("%'");
			.append("	where dong like '").append(blockInjection(dong)).append("%'");
		
			rs=stmt.executeQuery(selectZipcode.toString());
			
			ZipcodeVO zVO=null;
			
			while(rs.next()) {//��ȸ�� ����� �����ϸ�
				//�÷����� VO�� �����ϰ�
				zVO=new ZipcodeVO();
				zVO.setZipcode(rs.getString("zipcode"));
				zVO.setSido(rs.getString("sido"));
				zVO.setGugun(rs.getString("gugun"));
				zVO.setDong(rs.getString("dong"));
				zVO.setBunji(rs.getString("bunji"));
				
				//���� �̸��� ��ü ���� ���� ������� �ʵ��� �����ϱ� ���� list�� �ִ´�.
				list.add(zVO);
			}
			
		}finally{
			//5.
			dc.close(rs, stmt, con);
		}
		
		return list;
	}//selectStatement

	
	
	
	
	public String blockInjection(String sql) {
		
		if(sql!=null && !"".equals(sql)) {
			//injection�� ���õ� ���ڿ��� ġȯ
			sql=sql.replaceAll("select", "").replaceAll("union", "")
					.replaceAll(" ", "").replaceAll("'", "").replaceAll("--", "");
		}//end if
		
		return sql;
	}//blockInjection
	
	
	
	
	public List<ZipcodeVO> selectPreparedStatement(String dong)throws SQLException{
		List<ZipcodeVO> list= new  ArrayList<ZipcodeVO>();

		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConnection dc=DbConnection.getInstance();
		try {
		//1.
		//2.
			con=dc.getConn();
		//3.
			StringBuilder selectZipcode=new StringBuilder();
			selectZipcode
			.append("	select zipcode,sido,gugun,dong,nvl(bunji,' ') bunji	")
			.append("	from zipcode	")
			.append("	where dong like ?||'%'");
			
			pstmt=con.prepareStatement(selectZipcode.toString());
		//4.
			pstmt.setString(1, dong);
			
		//5.
			rs=pstmt.executeQuery();
			
			ZipcodeVO zVO=null;
			
			while(rs.next()) {//��ȸ�� ����� �����ϸ�
				//�÷����� VO�� �����ϰ�
				zVO=new ZipcodeVO();
				zVO.setZipcode(rs.getString("zipcode"));
				zVO.setSido(rs.getString("sido"));
				zVO.setGugun(rs.getString("gugun"));
				zVO.setDong(rs.getString("dong"));
				zVO.setBunji(rs.getString("bunji"));
				
				//���� �̸��� ��ü ���� ���� ������� �ʵ��� �����ϱ� ���� list�� �ִ´�.
				list.add(zVO);
			}
		}finally{
			//6.
			dc.close(rs, pstmt, con);
		}//end finally
		
		return list;
	}
	
	
	
}//clas
