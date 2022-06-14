package day0307Work_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import day0307Work_VO.SelectTableVO;
import kr.co.sist.dao.DbConnection;
import kr.co.sist.prepared.vo.CpEmp5VO;

public class TableInfoDAO {

	private static TableInfoDAO tiDAO;
	
	
	private TableInfoDAO() {
	}//TableInfoDAO
	
	
	
	public static TableInfoDAO getInstace() {
		if(tiDAO==null) {
			tiDAO=new TableInfoDAO();
		}
		return tiDAO;
	}//getInstace
	
	
	
	/**
	 * JComboBox에 들어갈 모든 테이블명들을 조회하는 쿼리문을 처리하는 메소드.
	 * @return
	 * @throws SQLException
	 */
	public List<String> allTableName() throws SQLException{
		
		List<String> list=new ArrayList<String>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConnection dc=DbConnection.getInstance();
		
		try{
		//1.드라이버 로딩
		//2.커넥션 얻기
			con=dc.getConn();
		
		//3.쿼리문 생성객체 얻기
			String selectTableName="select tname from tab";
			
			pstmt=con.prepareStatement(selectTableName);
		
		//4.쿼리 수행 후 결과 얻기
			rs=pstmt.executeQuery();
			
			String tableName=null;
			while(rs.next()) {//조회결과 있음.
				
				tableName=rs.getString("tname");
				
				//리스트에 추가
				list.add(tableName);
			}//end while
			
			
		}finally {
		//6.연결 끊기
			dc.close(rs, pstmt, con);
		}//end finally
		
		return list;
	}//tableName
	
	
	
	
	/**
	 * JTextArea에 출력하는 테이블의 정보를 조회하는 쿼리문 
	 * @return
	 * @throws SQLException 
	 */
	public List<SelectTableVO> tableInfo(String tName) throws SQLException{
		
		List<SelectTableVO> list = new ArrayList<SelectTableVO>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConnection dc=DbConnection.getInstance();
		
		try{
		//1.드라이버 로딩
		//2.커넥션 얻기
			con=dc.getConn();
		
		//3.쿼리문 생성객체 얻기
			StringBuilder selectTableInfo=new StringBuilder();
			
			selectTableInfo
			.append("	select column_name,data_type, nvl(data_precision,data_length) data_length")
			.append("	from user_tab_cols")
			.append("	where table_name='")
			.append(tName).append("'");
			
			pstmt=con.prepareStatement(selectTableInfo.toString());
		
			
		//5.쿼리 수행 후 결과 얻기
			rs=pstmt.executeQuery();
			
			SelectTableVO stVO=null;
			while(rs.next()) {//조회결과 있음.
				//조회된 현재 커서가 위치하는 레코드의 컬럼값을 VO에 설정하고 
				stVO=new SelectTableVO(rs.getString("column_name"),rs.getString("data_type"),
						rs.getInt("data_length"));
				
				//리스트에 추가
				list.add(stVO);
			}//end while
			
			
		}finally {
		//6.연결 끊기
			dc.close(rs, pstmt, con);
		}//end finally
		
		
		return list;
		
	}
	
	
	
	
}//class
