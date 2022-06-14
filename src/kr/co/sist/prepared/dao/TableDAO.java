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
 * ������ ���̺��� ������ ��ȸ
 * @author user
 */
public class TableDAO {
	
	private static TableDAO tDAO;
	
	
	private TableDAO() {//Ŭ���� �ܺο��� ��üȭ �Ұ���
		
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
	 * ���̺���� �޾Ƽ� ���̺� ���� ��� ��� ������ ��ȸ
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
		//1.����̹� �ε�
		//2.Ŀ�ؼ� ���
			con=dc.getConn();
		
		//3.������ ������ü ���
			StringBuilder selectTable=new StringBuilder();
//			selectTable
//			.append("	select empno, ename, sal, comm, deptno	")
//			.append("	from ?	");//���̺���̳� �÷����� ���ε� ������ ó������ ���Ѵ�.
//			pstmt=con.prepareStatement(selectTable.toString());
			
			
			//PreparedStatement�� ���̺���̳� �÷����� �������� ó���� ������ 
			//bind ������ ������� �ʰ� �������� �����־ ó���Ѵ�.
			selectTable
			.append("	select empno, ename, sal, comm, deptno	")
			.append("	from 	").append(table);
			pstmt=con.prepareStatement(selectTable.toString());
		
//			//4.���ε� ������ �� �Ҵ�
//			pstmt.setString(1,table); //�ʿ������
			
			
		//5.���� ���� �� ��� ���
			rs=pstmt.executeQuery();
			
			CpEmp5VO ceVO=null;
			while(rs.next()) {//��ȸ��� ����.
				//��ȸ�� ���� Ŀ���� ��ġ�ϴ� ���ڵ��� �÷����� VO�� �����ϰ� 
				ceVO=new CpEmp5VO(rs.getInt("empno"),rs.getInt("sal"),
						rs.getInt("comm"),rs.getInt("deptno"),rs.getString("ename"));
				
				//����Ʈ�� �߰�
				list.add(ceVO);
			}//end while
			
			
		}finally {
		//6.���� ����
			dc.close(rs, pstmt, con);
		}//end finally
		
		
		return list;
	}
	
	
}//class
