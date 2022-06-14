package day0307Work_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import day0307Work_VO.SelectTableVO;
import kr.co.sist.dao.DbConnection;
import kr.co.sist.prepared.vo.CpEmp5VO;

public class TableInfoDAO {

	private static TableInfoDAO tiDAO;
	
	
	private TableInfoDAO() {
	}//TableInfoDAO
	
	
	
	public static TableInfoDAO getInstace() {
		if(tiDAO==null) {
			tiDAO=new TableInfoDAO();
		}
		return tiDAO;
	}//getInstace
	
	
	
	/**
	 * JComboBox�� �� ��� ���̺����� ��ȸ�ϴ� �������� ó���ϴ� �޼ҵ�.
	 * @return
	 * @throws SQLException
	 */
	public List<String> allTableName() throws SQLException{
		
		List<String> list=new ArrayList<String>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConnection dc=DbConnection.getInstance();
		
		try{
		//1.����̹� �ε�
		//2.Ŀ�ؼ� ���
			con=dc.getConn();
		
		//3.������ ������ü ���
			String selectTableName="select tname from tab";
			
			pstmt=con.prepareStatement(selectTableName);
		
		//4.���� ���� �� ��� ���
			rs=pstmt.executeQuery();
			
			String tableName=null;
			while(rs.next()) {//��ȸ��� ����.
				
				tableName=rs.getString("tname");
				
				//����Ʈ�� �߰�
				list.add(tableName);
			}//end while
			
			
		}finally {
		//6.���� ����
			dc.close(rs, pstmt, con);
		}//end finally
		
		return list;
	}//tableName
	
	
	
	
	/**
	 * JTextArea�� ����ϴ� ���̺��� ������ ��ȸ�ϴ� ������ 
	 * @return
	 * @throws SQLException 
	 */
	public List<SelectTableVO> tableInfo(String tName) throws SQLException{
		
		List<SelectTableVO> list = new ArrayList<SelectTableVO>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConnection dc=DbConnection.getInstance();
		
		try{
		//1.����̹� �ε�
		//2.Ŀ�ؼ� ���
			con=dc.getConn();
		
		//3.������ ������ü ���
			StringBuilder selectTableInfo=new StringBuilder();
			
			selectTableInfo
			.append("	select column_name,data_type, nvl(data_precision,data_length) data_length")
			.append("	from user_tab_cols")
			.append("	where table_name='")
			.append(tName).append("'");
			
			pstmt=con.prepareStatement(selectTableInfo.toString());
		
			
		//5.���� ���� �� ��� ���
			rs=pstmt.executeQuery();
			
			SelectTableVO stVO=null;
			while(rs.next()) {//��ȸ��� ����.
				//��ȸ�� ���� Ŀ���� ��ġ�ϴ� ���ڵ��� �÷����� VO�� �����ϰ� 
				stVO=new SelectTableVO(rs.getString("column_name"),rs.getString("data_type"),
						rs.getInt("data_length"));
				
				//����Ʈ�� �߰�
				list.add(stVO);
			}//end while
			
			
		}finally {
		//6.���� ����
			dc.close(rs, pstmt, con);
		}//end finally
		
		
		return list;
		
	}
	
	
	
	
}//class
