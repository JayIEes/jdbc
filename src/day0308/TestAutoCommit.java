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
 * auto commit에 대한 해제 예.
 */
public class TestAutoCommit {

	
	
	/**
	 * autocommit해제
	 * @param cVO
	 * @throws SQLException
	 */
	public void insertTestClob(ClobVO cVO) throws SQLException{

		Connection con=null;
		PreparedStatement pstmt=null;
		
		DbConnection dc=DbConnection.getInstance();
		
		try {
		//1.드라이버 로딩
		//2.커넥션 얻기
			con=dc.getConn();
			
			// autocommit 해제
			con.setAutoCommit(false);
			
		//3.쿼리문 생성객체 얻기
			String insert="insert into test_clob(subject,content) values(?,?)";

			pstmt=con.prepareStatement(insert);
		//4.
			pstmt.setString(1,cVO.getSubject());
			pstmt.setString(2,cVO.getContent());
			
		//5.
			int cnt=pstmt.executeUpdate();
			System.out.println(cnt+"건 추가");
		
			if(cnt==1) {//추가된 행이 하나면 commit을 수헹 
				con.commit();
			}//end if
			
		}finally {
		//6.연결 끊기
//			dc.close(null, pstmt, con);//close가 되면 DB작업을 하드디스크에 기록하고 연결을 끊는다.
		}//end finally
	}//insertTestClob
	
	
	public static void main(String[] args) {
		ClobVO cVO=new ClobVO();
		cVO.setSubject("내일은 수요일 입니다.");
		cVO.setContent("쉰다.");
	
		TestAutoCommit tac=new TestAutoCommit();
		try {
			tac.insertTestClob(cVO);//추가 작업 후
			System.out.println("추가성공");
		} catch (SQLException se) {
			se.printStackTrace();
		}//end catch
	}//main


}
