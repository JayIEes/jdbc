package day0310;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.concurrent.Callable;

import kr.co.sist.dao.DbConnection;

/**
 * insert쿼리를 가지고 있는 procedure 사용
 * @author user
 */
public class UseInsertProcedure {

	
	public ResultVO useInsertProcedure(ProcedureVO pVO) throws SQLException {
		ResultVO rVO=null;
		
		Connection con=null;
		CallableStatement cstmt=null;
		
		DbConnection dc=DbConnection.getInstance();
		try {
		//1.드라이버 로딩
		//2.커넥션 얻기
			con=dc.getConn();
		//3.쿼리문 생성 객체 얻기
			cstmt=con.prepareCall("{call insert_test_proc(?,?,?,?,?,?) }");
		//4.바인드 변수에 값 설정
			//in parameter
			cstmt.setInt(1, pVO.getNum());
			cstmt.setString(2, pVO.getName());
			cstmt.setString(3, pVO.getEmail());
			cstmt.setInt(4, pVO.getAge());
			//out parameter
			cstmt.registerOutParameter(5, Types.NUMERIC);
			cstmt.registerOutParameter(6, Types.VARCHAR);
		//5.쿼리 실행
			cstmt.execute();
		//6.out parameter 값 받기
			rVO=new ResultVO();
			rVO.setRowCnt(cstmt.getInt(5));
			rVO.setErrMsg(cstmt.getString(6));
			
		}finally{
			//7.연결 끊기
			dc.close(null, cstmt, con);
		}//end finally
		
		
		
		return rVO;
	}//useInsertProcedure
	
	
	public static void main(String[] args) {

		UseInsertProcedure uip=new UseInsertProcedure();
		
		ProcedureVO pVO=new ProcedureVO();
		pVO.setNum(6);
		pVO.setName("김진영");
		pVO.setEmail("kim@test.com");
		pVO.setAge(25);
		
		try {
			ResultVO rVO = uip.useInsertProcedure(pVO);
			switch(rVO.getRowCnt()) {
			case 1: System.out.println(pVO.getName()+"추가되었습니다."); break;
			default : System.out.println(rVO.getErrMsg());
			
			}//end switch
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}//main

}//class
