package day0310;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.concurrent.Callable;

import kr.co.sist.dao.DbConnection;

/**
 * insert������ ������ �ִ� procedure ���
 * @author user
 */
public class UseInsertProcedure {

	
	public ResultVO useInsertProcedure(ProcedureVO pVO) throws SQLException {
		ResultVO rVO=null;
		
		Connection con=null;
		CallableStatement cstmt=null;
		
		DbConnection dc=DbConnection.getInstance();
		try {
		//1.����̹� �ε�
		//2.Ŀ�ؼ� ���
			con=dc.getConn();
		//3.������ ���� ��ü ���
			cstmt=con.prepareCall("{call insert_test_proc(?,?,?,?,?,?) }");
		//4.���ε� ������ �� ����
			//in parameter
			cstmt.setInt(1, pVO.getNum());
			cstmt.setString(2, pVO.getName());
			cstmt.setString(3, pVO.getEmail());
			cstmt.setInt(4, pVO.getAge());
			//out parameter
			cstmt.registerOutParameter(5, Types.NUMERIC);
			cstmt.registerOutParameter(6, Types.VARCHAR);
		//5.���� ����
			cstmt.execute();
		//6.out parameter �� �ޱ�
			rVO=new ResultVO();
			rVO.setRowCnt(cstmt.getInt(5));
			rVO.setErrMsg(cstmt.getString(6));
			
		}finally{
			//7.���� ����
			dc.close(null, cstmt, con);
		}//end finally
		
		
		
		return rVO;
	}//useInsertProcedure
	
	
	public static void main(String[] args) {

		UseInsertProcedure uip=new UseInsertProcedure();
		
		ProcedureVO pVO=new ProcedureVO();
		pVO.setNum(6);
		pVO.setName("������");
		pVO.setEmail("kim@test.com");
		pVO.setAge(25);
		
		try {
			ResultVO rVO = uip.useInsertProcedure(pVO);
			switch(rVO.getRowCnt()) {
			case 1: System.out.println(pVO.getName()+"�߰��Ǿ����ϴ�."); break;
			default : System.out.println(rVO.getErrMsg());
			
			}//end switch
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}//main

}//class
