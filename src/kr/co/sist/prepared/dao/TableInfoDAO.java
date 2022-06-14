package kr.co.sist.prepared.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import day0307Work_VO.SelectTableVO;
import kr.co.sist.dao.DbConnection;
import kr.co.sist.prepared.vo.ColumnVO;

/**
 * @author user
 * 접속계전의 테이블 정보를 조회, 입력된 테이블의 컬럼 정보를 조회
 */
public class TableInfoDAO {

	private static TableInfoDAO tDAO;
	
	public TableInfoDAO() {
		
	}//TableInfoDAO

	
	
	public static TableInfoDAO getInstace() {
		if(tDAO==null) {
			tDAO=new TableInfoDAO();
		}//end if
		return tDAO;
	}//getInstace
	
	
	
	public List<String> selectAllTable() throws SQLException{
		List<String> listTab=new ArrayList<String>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConnection dc=DbConnection.getInstance();
		
		try{
		//1.드라이버 로딩
		//2.커넥션 얻기
			con=dc.getConn();
		
		//3.쿼리문 생성객체 얻기
			String selectTab="select tname from tab";
			
			pstmt=con.prepareStatement(selectTab);
		
		//4.쿼리 수행 후 결과 얻기
			rs=pstmt.executeQuery();
			
			while(rs.next()) {//조회결과 있음.
				listTab.add(rs.getString("tname"));
			}//end while
			
		}finally {
		//6.연결 끊기
			dc.close(rs, pstmt, con);
		}//end finally
		
		return listTab;
	}//selectAllTable
	
	
	
	public List<ColumnVO> selectTableColumn(String tName) throws SQLException{
		
		List<ColumnVO> list=new ArrayList<ColumnVO>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConnection dc=DbConnection.getInstance();
		
		try{
		//1.드라이버 로딩
		//2.커넥션 얻기
			con=dc.getConn();
		
		//3.쿼리문 생성객체 얻기
			StringBuilder selectColumn=new StringBuilder();
			
			selectColumn
			.append("	select column_name,data_type, nvl(data_precision,data_length) data_length	")
			.append("	from user_tab_cols															")
			.append("	where table_name=?															");
			
			pstmt=con.prepareStatement(selectColumn.toString());
		//4.
			pstmt.setString(1, tName);
			
		//5.쿼리 수행 후 결과 얻기
			rs=pstmt.executeQuery();
			
			//조회된 레코드를 VO를 저장(컬럼명과 VO인스턴스명은 아무런 상관이 없다.)
			ColumnVO cVO=null;
			while(rs.next()) {//조회결과 있음.
				
				cVO=new ColumnVO();
				cVO.setColumnName(rs.getString("column_name"));
				cVO.setDataType(rs.getString("data_type"));
				cVO.setDataLength(rs.getInt("data_length"));
				//리스트에 추가
				list.add(cVO);
			}//end while
			
			
		}finally {
		//6.연결 끊기
			dc.close(rs, pstmt, con);
		}//end finally
		
		
		return list;
	}
	
	
	
}
