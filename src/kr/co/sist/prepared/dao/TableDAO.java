package kr.co.sist.prepared.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.DbConnection;
import kr.co.sist.prepared.vo.CpEmp5VO;
import oracle.jdbc.proxy.annotation.Pre;

/**
 * 선택한 테이블의 동적인 조회
 * @author user
 */
public class TableDAO {
	
	private static TableDAO tDAO;
	
	
	private TableDAO() {//클래스 외부에서 객체화 불가능
		
	}//TableDAO
	
	
	public static TableDAO getInstance() {
		if(tDAO==null) {
			tDAO=new TableDAO();
		}
		
		return tDAO;
	}//getInstance
	
	
//	public TableDAO() {
//	}//TableDAO
	
	/**
	 * 테이블명을 받아서 테이블에 대한 모든 사원 정보를 조회
	 * @param table
	 * @return
	 * @throws SQLException
	 */
	public List<CpEmp5VO> selectTable(String table) throws SQLException{
		List<CpEmp5VO> list =new ArrayList<CpEmp5VO>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConnection dc=DbConnection.getInstance();
		
		try{
		//1.드라이버 로딩
		//2.커넥션 얻기
			con=dc.getConn();
		
		//3.쿼리문 생성객체 얻기
			StringBuilder selectTable=new StringBuilder();
//			selectTable
//			.append("	select empno, ename, sal, comm, deptno	")
//			.append("	from ?	");//테이블명이나 컬럼명은 바인드 변수로 처리되지 못한다.
//			pstmt=con.prepareStatement(selectTable.toString());
			
			
			//PreparedStatement로 테이블명이나 컬럼명을 동적으로 처리할 때에는 
			//bind 변수를 사용하지 않고 쿼리문에 직접넣어서 처리한다.
			selectTable
			.append("	select empno, ename, sal, comm, deptno	")
			.append("	from 	").append(table);
			pstmt=con.prepareStatement(selectTable.toString());
		
//			//4.바인드 변수에 값 할당
//			pstmt.setString(1,table); //필요없어짐
			
			
		//5.쿼리 수행 후 결과 얻기
			rs=pstmt.executeQuery();
			
			CpEmp5VO ceVO=null;
			while(rs.next()) {//조회결과 있음.
				//조회된 현재 커서가 위치하는 레코드의 컬럼값을 VO에 설정하고 
				ceVO=new CpEmp5VO(rs.getInt("empno"),rs.getInt("sal"),
						rs.getInt("comm"),rs.getInt("deptno"),rs.getString("ename"));
				
				//리스트에 추가
				list.add(ceVO);
			}//end while
			
			
		}finally {
		//6.연결 끊기
			dc.close(rs, pstmt, con);
		}//end finally
		
		
		return list;
	}
	
	
}//class
