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
		//3.������ ���� ��ü ���
			cstmt=con.prepareCall("{ call select_all_proc(?) }");
		//4.���ε� ���� �� �Ҵ�
			//in parameter
			//out parameter
//			cstmt.registerOutParameter(1, Types.REF_CURSOR);
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
		//5.������ ����
			cstmt.execute();
//			ResultSet rs=cstmt.executeQuery();//����!!�θ� �����ϴ� method�� out parameter�ʹ� 
			//������ ���� ������ �ڵ� �󿡼��� ������ �߻���Ű�� ������ ����� �� ����.(�ڵ��ϼ��Ǵ� �ڵ� ����)
			ResultSet rs=(ResultSet) cstmt.getObject(1);
			
			ProcedureVO pVO=null;
			while(rs.next()) {//���ڵ尡 �����ϸ�
				pVO=new ProcedureVO();//�÷����� ������ VO�� �����ϰ� 
				pVO.setNum(rs.getInt("num"));
				pVO.setName(rs.getString("name"));
				pVO.setEmail(rs.getString("email"));
				pVO.setAge(rs.getInt("age"));
				pVO.setInput_date(rs.getString("input_date"));
				
				//vo�� list�� �߰�
				//
				list.add(pVO);
			}//while
		//6.out parameter�����
		}finally{
			//7.
			dc.close(null, cstmt, con);
		}
		
		return list;
	}//selectAll
	
	
	
	public void printProcedure(List<ProcedureVO> list) {
		
			if(list.isEmpty()) {
				System.out.println("���ڵ尡 �������� �ʽ��ϴ�.");
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
		//3.������ ������ü ���
			cstmt=con.prepareCall("{ call select_num_proc(?,?) }");
		//4.���ε� ���� �� ����
			//in parameter
			cstmt.setInt(1, num);
			//out parameter
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
		//5.���ν��� ����
			cstmt.execute();
			//excuteUpdate�� �ǰ� �ٵ�
		//6.out parameter �� �ޱ� : OracleTypes.CURSOR�� ���� ResultSet�ޱ�
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
			//��� ���ڵ� ��ȸ
//			List<ProcedureVO> list= usp.selectAll();
			//���� ��ȸ
			List<ProcedureVO> list=usp.selectNum(6);
			usp.printProcedure(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
	}//main

}//class
