package kr.co.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PreparedStatementDAO {

	
	
	public PreparedStatementDAO() {
		
	}//PreparedStatementDAO
	
	
	
	/**
	 * �����ȣ�� �������� ����Ѵ�.
	 * @param ceVO
	 * @throws SQLException
	 */
	public void insertCpEmp4(CpEmp4VO ceVO) throws SQLException {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		DbConnection dc=DbConnection.getInstance();
		try {
		//1.����̹� �ε�
		
		//2.Ŀ�ؼ� ���
			conn=dc.getConn();
			
		//3.�������� �־� ������ ������ü ���
			String insertCpEmp
			="insert into cp_emp4(empno,ename,job,sal,comm) values(seq_cp_emp4.nextval,?,?,?,?)";
			pstmt=conn.prepareStatement(insertCpEmp);
		
		//4.���ε� ������ �� ����
			pstmt.setString(1, ceVO.getEname());//ó�� ���ε� ������ ���ڿ��� �����ȣ�� �ִ´�.	
			pstmt.setString(2, ceVO.getJob());//�й�° ���ε� ������ ���ڿ��� �����ȣ�� �ִ´�.	
			pstmt.setInt(3, ceVO.getSal());//����° ���ε� ������ ���ڿ��� �����ȣ�� �ִ´�.	
			pstmt.setDouble(4, ceVO.getComm());//�׹�° ���ε� ������ ���ڿ��� �����ȣ�� �ִ´�.	
		//5.���� ���� �� ��� ���
			pstmt.executeUpdate();
			
			
		}finally {
			//6.�������
			dc.close(null, pstmt, conn);
		}//end finally
		
	}//insertCpEmp4
	
	
	
	public int updateCpEmp4(CpEmp4VO ceVO) throws SQLException {
		int cnt=0;
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		DbConnection dc=DbConnection.getInstance();
		
		try {
		//1.����̹� �ε�
		
		//2.Ŀ�ؼ� ���
			conn=dc.getConn();
			
		//3.�������� �־� ������ ���ఴü ���
			StringBuilder updateCpEmp4=new StringBuilder();
			updateCpEmp4
			.append("	update cp_emp4")
			.append("	set job=?, sal=?, comm=?")//���ε� �������� ���ڿ������� Ȧ����ǥ ������� ����
			.append("	where empno=?");
			
			pstmt=conn.prepareStatement(updateCpEmp4.toString());
		
			
		//4.���ε� ������ �� �Ҵ�
			pstmt.setString(1, ceVO.getJob());	
			pstmt.setInt(2, ceVO.getSal());
			pstmt.setDouble(3, ceVO.getComm());	
			pstmt.setInt(4, ceVO.getEmpno());

		//5.���� ���� �� ��� ���
			cnt=pstmt.executeUpdate();
			
			
		}finally {
			//6.�������
			dc.close(null, pstmt, conn);
		}//end finally
		
		
		return cnt;
	}//insertCpEmp4
	
	
	
	
	/**
	 * �����ȣ�� �Է¹޾� �Էµ� �����ȣ�� �ش��ϴ� ��� ����� ����.
	 * @param empno
	 * @return
	 * @throws SQLException
	 */
	public int deleteCpEmp4(int empno) throws SQLException {
		int cnt=0;
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		DbConnection dc=DbConnection.getInstance();//�̱������� ��ü ���
		
		try {
		//1.����̹� �ε�
		
		//2.Ŀ�ؼ� ���
			conn=dc.getConn();
			
		//3.�������� �־� ������ ���ఴü ���
			String deleteCpEmp4="delete from cp_emp4 where empno=?" ;
			
			pstmt=conn.prepareStatement(deleteCpEmp4);
		
			
		//4.�������� �ִ� ���ε� ������ �� ����
			pstmt.setInt(1,empno);

		//5.���� ���� �� ��� ���
			cnt=pstmt.executeUpdate();
			
			
		}finally {
			//6.�������
			dc.close(null, pstmt, conn);
		}//end finally
		
		return cnt;
	}//deleteCpEmp4
	
	
	
	public CpEmp4VO selectOneCpEmp4(int empno) throws SQLException {
		CpEmp4VO ceVO=null; 
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConnection dc=DbConnection.getInstance();//�̱������� ��ü ���
		
		try {
		//1.����̹� �ε�
		
		//2.Ŀ�ؼ� ���
			conn=dc.getConn();
			
		//3.�������� �־� ������ ���ఴü ���
			StringBuilder selectCpEmp4=new StringBuilder();
			selectCpEmp4
			.append("	select ename,sal,comm,job	")
			.append("	from cp_emp4				")
			.append("	where empno=?				");
					
			
			pstmt=conn.prepareStatement(selectCpEmp4.toString());
		
			
		//4.�������� �ִ� ���ε� ������ �� ����
			pstmt.setInt(1,empno);

		//5.���� ���� �� ��� ���
			rs=pstmt.executeQuery();
				
			if(rs.next()) {//��ȸ�� ���ڵ� ����
				ceVO=new CpEmp4VO();//��ȸ�÷� ���� �����ϱ� ���� VO����
//				ceVO.setEmpno(empno);//�Ű����� ���� VO����
				//�÷���=>�������� ����
				ceVO.setEname(rs.getString("ename"));
				ceVO.setSal(rs.getInt("sal"));
				ceVO.setComm(rs.getDouble("comm")); 
				ceVO.setJob(rs.getString("job"));
				//�÷��� �ε����� ���. ��ȸ���� ù��° Į������ 1���� �ε����� ������.=>�������� ���� ����.����X
//				ceVO.setEname(rs.getString(1));
//				ceVO.setSal(rs.getInt(2));
//				ceVO.setComm(rs.getDouble(3)); 
//				ceVO.setJob(rs.getString(4));
			}//end if
			
			
		}finally {
			//6.�������
			dc.close(rs, pstmt, conn);
		}//end finally
		
		
		return ceVO;
	}//selectOneCpEmp4
	
	
	
	
	public List<CpEmp4VO> selectAllCpEmp4() throws SQLException {
		
		List<CpEmp4VO> list=new ArrayList<CpEmp4VO>();
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConnection dc=DbConnection.getInstance();//�̱������� ��ü ���
		
		try {
		//1.����̹� �ε�
		
		//2.Ŀ�ؼ� ���
			conn=dc.getConn();
			
		//3.�������� �־� ������ ���ఴü ���
			String selectCpEmp4="select empno,ename,job,hiredate,sal,comm from cp_emp4";
			pstmt=conn.prepareStatement(selectCpEmp4);
			
		//4.���ε� ������ �� ����

		//5.���� ���� �� ��� ���
			rs=pstmt.executeQuery();
			
			CpEmp4VO ceVO = null;
			while(rs.next()) {//��ȸ�� ���ڵ尡 �����ϸ�
				//�÷��� ���� �ϳ��� VO�� ����.
				ceVO=new CpEmp4VO();
				
				ceVO.setEmpno(rs.getInt("empno"));
				ceVO.setEname(rs.getString("ename"));
				ceVO.setJob(rs.getString("job"));
				ceVO.setHiredate(rs.getDate("hiredate"));
				ceVO.setSal(rs.getInt("sal"));
				ceVO.setComm(rs.getDouble("comm")); 
				
				//���� �̸��� VO�� ���� �� �����ϱ� ���� List�� �߰�(ceVO��ü�� ���� ������ ���ڵ常 ����)
				list.add(ceVO);//���� ��ȸ�� ���ڵ��� ���� ����� �� �ֵ��� list�� �߰��Ѵ�.
				
			}//end while
			
			
		}finally {
			//6.�������
			dc.close(rs, pstmt, conn);
		}//end finally
		
		return list;
	}//selectAllCpEmp4
	
	
	
	
}//class
