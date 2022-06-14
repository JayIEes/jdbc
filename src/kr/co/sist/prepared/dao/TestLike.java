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
 * 바인드 변수에 % 기호를 사용
 */
public class TestLike {

	public List<ZipcodeVO> selectZipcode(String dong) throws SQLException {
		
		List<ZipcodeVO> list=new ArrayList<ZipcodeVO>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConnection dc=DbConnection.getInstance();
		
		try{
		//1.드라이버 로딩
		//2.커넥션 얻기
			con=dc.getConn();
		
		//3.쿼리문 생성객체 얻기
			StringBuilder selectZipcode=new StringBuilder();
			selectZipcode
			.append("	select zipcode,sido,gugun,dong,bunji	")
			.append("	from zipcode							")
			.append("	where dong like ?||'%'					");
			
			pstmt=con.prepareStatement(selectZipcode.toString());
		
		//4.쿼리 수행 후 결과 얻기
			pstmt.setString(1, dong);//인식불가 ?%
		
		//5.
			rs=pstmt.executeQuery();
			
			ZipcodeVO zVO=null;
			while(rs.next()) {//조회된 우편번호 있음
				zVO=new ZipcodeVO();
				zVO.setZipcode(rs.getString("zipcode"));
				zVO.setSido(rs.getString("sido"));
				zVO.setGugun(rs.getString("gugun"));
				zVO.setDong(rs.getString("dong"));
				zVO.setBunji(rs.getString("bunji"));
				
				//리스트 추가
				list.add(zVO);
			}//end while
			
		}finally {
		//6.연결 끊기
			dc.close(rs, pstmt, con);
		}//end finally
		return list;
	}//selectZipcode
	
	
	public static void main(String[] args) {
		TestLike tl=new TestLike();
		try {
			System.out.println(tl.selectZipcode("향남"));
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//main

}//class
