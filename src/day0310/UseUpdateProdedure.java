package day0310;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import kr.co.sist.dao.DbConnection;

public class UseUpdateProdedure {

	public ResultVO updateProcr(ProcedureVO pVO) throws SQLException{
		ResultVO rVO =null;
		
		
		Connection con=null;
		CallableStatement cstmt=null;
		
		DbConnection dc=DbConnection.getInstance();
		try {
		//1.
		//2.
			con=dc.getConn();
		//3.������ ���� ��ü ���
			cstmt=con.prepareCall("{call update_test_proc(?,?,?,?,?)}");
		//4.���ε� ������ �� ����
			//in parameter
			cstmt.setInt(1, pVO.getNum());
			cstmt.setInt(2, pVO.getAge());
			cstmt.setString(3, pVO.getEmail());
			//out parameter
			cstmt.registerOutParameter(4, Types.NUMERIC);//PL/SQL ���ε� ����(var ������ ��������)
			cstmt.registerOutParameter(5, Types.VARCHAR);
		//5.���� ����
			cstmt.execute();
		//6.out parameter�� �ޱ�
			rVO=new ResultVO();
			rVO.setRowCnt(cstmt.getInt(4));
			rVO.setErrMsg(cstmt.getString(5));
		}finally{
			//7.
			dc.close(null, cstmt, con);
		}
		
		return rVO;
	}
	
	
	public static void main(String[] args) {
		UseUpdateProdedure uup=new UseUpdateProdedure();
		
		ProcedureVO pVO=new ProcedureVO();
		pVO.setNum(60);
		pVO.setAge(26);
		pVO.setEmail("change@change.com");
		
		
		try {
			ResultVO rVO=uup.updateProcr(pVO);
			System.out.println(rVO);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
	}//main

}//class
