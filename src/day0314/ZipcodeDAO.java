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
			
			while(rs.next()) {//조회된 결과가 존재하면
				//컬럼값을 VO에 저장하고
				zVO=new ZipcodeVO();
				zVO.setZipcode(rs.getString("zipcode"));
				zVO.setSido(rs.getString("sido"));
				zVO.setGugun(rs.getString("gugun"));
				zVO.setDong(rs.getString("dong"));
				zVO.setBunji(rs.getString("bunji"));
				
				//같은 이름의 객체 여러 개를 사라지지 않도록 관리하기 위해 list에 넣는다.
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
			//injection에 관련된 문자열을 치환
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
			
			while(rs.next()) {//조회된 결과가 존재하면
				//컬럼값을 VO에 저장하고
				zVO=new ZipcodeVO();
				zVO.setZipcode(rs.getString("zipcode"));
				zVO.setSido(rs.getString("sido"));
				zVO.setGugun(rs.getString("gugun"));
				zVO.setDong(rs.getString("dong"));
				zVO.setBunji(rs.getString("bunji"));
				
				//같은 이름의 객체 여러 개를 사라지지 않도록 관리하기 위해 list에 넣는다.
				list.add(zVO);
			}
		}finally{
			//6.
			dc.close(rs, pstmt, con);
		}//end finally
		
		return list;
	}
	
	
	
}//clas
