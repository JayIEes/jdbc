package day0311;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import day0310.ResultVO;
import kr.co.sist.dao.DbConnection;

public class UseDeleteProcedure {

	public ResultVO useDeleteProcedure(int num)throws SQLException{
		ResultVO rVO=null;
		
		Connection con=null;
		CallableStatement cstmt=null;
		
		DbConnection dc=DbConnection.getInstance();
		
		try {
		//1.
		//2.
			con=dc.getConn();
		//3.������ ������ü ���
			String procedure="{ call delete_test_proc(?,?,?) }";
			
			cstmt=con.prepareCall(procedure);
		//4.���ε� ������ �� ����
			//in parameter
			cstmt.setInt(1, num);
			//out parameter
			cstmt.registerOutParameter(2, Types.NUMERIC);
			cstmt.registerOutParameter(3, Types.VARCHAR);
		//5.���ν��� ����
			cstmt.execute();
		//6.out parameter�� ������ �� ���
			rVO=new ResultVO();
			rVO.setRowCnt(cstmt.getInt(2));
			rVO.setErrMsg(cstmt.getString(3));
			
		}finally{
			//7.
			dc.close(null, cstmt, con);
		
		}
		
		return rVO;
	}//useDeleteProcedure
	
	
	public static void main(String[] args) {
		UseDeleteProcedure udp= new UseDeleteProcedure();
		int num=6;
		try {
			ResultVO rVO= udp.useDeleteProcedure(num);
			System.out.println(rVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}//main

}
