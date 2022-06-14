package day0311;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import day0310.ProcedureVO;
import kr.co.sist.dao.DbConnection;
import oracle.jdbc.internal.OracleTypes;

public class UseSelectProcedure {

	public List<ProcedureVO> selectAll() throws SQLException{
		List<ProcedureVO> list= new ArrayList<ProcedureVO>();
		
		Connection con=null;
		CallableStatement cstmt=null;
		
		DbConnection dc=DbConnection.getInstance();
		
		try {
		//1.
		//2.
			con=dc.getConn();
		//3.쿼리문 생성 객체 얻기
			cstmt=con.prepareCall("{ call select_all_proc(?) }");
		//4.바인드 변수 값 할당
			//in parameter
			//out parameter
//			cstmt.registerOutParameter(1, Types.REF_CURSOR);
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
		//5.쿼리문 실행
			cstmt.execute();
//			ResultSet rs=cstmt.executeQuery();//주의!!부모에 존재하는 method는 out parameter와는 
			//관련이 없기 때문에 코드 상에서는 에러를 발생시키지 않으나 사용할 수 없다.(자동완선되는 코드 주의)
			ResultSet rs=(ResultSet) cstmt.getObject(1);
			
			ProcedureVO pVO=null;
			while(rs.next()) {//레코드가 존재하면
				pVO=new ProcedureVO();//컬럼값을 저장할 VO를 생성하고 
				pVO.setNum(rs.getInt("num"));
				pVO.setName(rs.getString("name"));
				pVO.setEmail(rs.getString("email"));
				pVO.setAge(rs.getInt("age"));
				pVO.setInput_date(rs.getString("input_date"));
				
				//vo를 list에 추가
				//
				list.add(pVO);
			}//while
		//6.out parameter값얻기
		}finally{
			//7.
			dc.close(null, cstmt, con);
		}
		
		return list;
	}//selectAll
	
	
	
	public void printProcedure(List<ProcedureVO> list) {
		
			if(list.isEmpty()) {
				System.out.println("레코드가 존재하지 않습니다.");
			}//end if
			
			for(ProcedureVO pVO :list) {
				System.out.printf("%d\t %s\t %s\t %d\t %s\n",
						pVO.getNum(),pVO.getName(),pVO.getEmail(),
						pVO.getAge(),pVO.getInput_date());
			}
		
	}//useSelectAll
	
	
	
	public List<ProcedureVO> selectNum(int num) throws SQLException{
		List<ProcedureVO> list=new ArrayList<ProcedureVO>();
		
		Connection con=null;
		CallableStatement cstmt=null;
		
		DbConnection dc=DbConnection.getInstance();
		
		try {
		//1.
		//2.
			con=dc.getConn();
		//3.쿼리문 생성객체 얻기
			cstmt=con.prepareCall("{ call select_num_proc(?,?) }");
		//4.바인드 변수 값 설정
			//in parameter
			cstmt.setInt(1, num);
			//out parameter
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
		//5.프로시저 실행
			cstmt.execute();
			//excuteUpdate도 되고 다됨
		//6.out parameter 값 받기 : OracleTypes.CURSOR를 통한 ResultSet받기
			ResultSet rs=(ResultSet) cstmt.getObject(2);
			
			ProcedureVO pVO=null;
			while(rs.next()) {
				pVO=new ProcedureVO();
				
				pVO.setNum(rs.getInt("num"));
				pVO.setName(rs.getString("name"));
				pVO.setEmail(rs.getString("email"));
				pVO.setAge(rs.getInt("age"));
				pVO.setInput_date(rs.getString("input_date"));
				
				list.add(pVO);
			}//end while
			
		}finally{
			//7.
			dc.close(null, cstmt, con);
		}//end finally
		
		return list;
	}//selectNumProc
	
	
	
	public static void main(String[] args) {
		UseSelectProcedure usp=new UseSelectProcedure();
		
		try {
			//모든 레코드 조회
//			List<ProcedureVO> list= usp.selectAll();
			//조건 조회
			List<ProcedureVO> list=usp.selectNum(6);
			usp.printProcedure(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
	}//main

}//class
