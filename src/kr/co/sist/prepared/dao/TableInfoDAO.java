package kr.co.sist.prepared.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import day0307Work_VO.SelectTableVO;
import kr.co.sist.dao.DbConnection;
import kr.co.sist.prepared.vo.ColumnVO;

/**
 * @author user
 * ���Ӱ����� ���̺� ������ ��ȸ, �Էµ� ���̺��� �÷� ������ ��ȸ
 */
public class TableInfoDAO {

	private static TableInfoDAO tDAO;
	
	public TableInfoDAO() {
		
	}//TableInfoDAO

	
	
	public static TableInfoDAO getInstace() {
		if(tDAO==null) {
			tDAO=new TableInfoDAO();
		}//end if
		return tDAO;
	}//getInstace
	
	
	
	public List<String> selectAllTable() throws SQLException{
		List<String> listTab=new ArrayList<String>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConnection dc=DbConnection.getInstance();
		
		try{
		//1.����̹� �ε�
		//2.Ŀ�ؼ� ���
			con=dc.getConn();
		
		//3.������ ������ü ���
			String selectTab="select tname from tab";
			
			pstmt=con.prepareStatement(selectTab);
		
		//4.���� ���� �� ��� ���
			rs=pstmt.executeQuery();
			
			while(rs.next()) {//��ȸ��� ����.
				listTab.add(rs.getString("tname"));
			}//end while
			
		}finally {
		//6.���� ����
			dc.close(rs, pstmt, con);
		}//end finally
		
		return listTab;
	}//selectAllTable
	
	
	
	public List<ColumnVO> selectTableColumn(String tName) throws SQLException{
		
		List<ColumnVO> list=new ArrayList<ColumnVO>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConnection dc=DbConnection.getInstance();
		
		try{
		//1.����̹� �ε�
		//2.Ŀ�ؼ� ���
			con=dc.getConn();
		
		//3.������ ������ü ���
			StringBuilder selectColumn=new StringBuilder();
			
			selectColumn
			.append("	select column_name,data_type, nvl(data_precision,data_length) data_length	")
			.append("	from user_tab_cols															")
			.append("	where table_name=?															");
			
			pstmt=con.prepareStatement(selectColumn.toString());
		//4.
			pstmt.setString(1, tName);
			
		//5.���� ���� �� ��� ���
			rs=pstmt.executeQuery();
			
			//��ȸ�� ���ڵ带 VO�� ����(�÷���� VO�ν��Ͻ����� �ƹ��� ����� ����.)
			ColumnVO cVO=null;
			while(rs.next()) {//��ȸ��� ����.
				
				cVO=new ColumnVO();
				cVO.setColumnName(rs.getString("column_name"));
				cVO.setDataType(rs.getString("data_type"));
				cVO.setDataLength(rs.getInt("data_length"));
				//����Ʈ�� �߰�
				list.add(cVO);
			}//end while
			
			
		}finally {
		//6.���� ����
			dc.close(rs, pstmt, con);
		}//end finally
		
		
		return list;
	}
	
	
	
}
