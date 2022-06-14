package day0308;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.DbConnection;
import kr.co.sist.prepared.vo.ClobVO;

/**
 * @author user
 * stream�� �����Ͽ� CLOB �������� ���
 */
public class UseClob {

	
	
	public List<ClobVO> useClob()throws SQLException,IOException{
		
		List<ClobVO> list=new ArrayList<ClobVO>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		BufferedReader br= null;//CLOB���������� �о� ���̱� ���� ��Ʈ��
		
		DbConnection dc=DbConnection.getInstance();
		
		try {
		//1.����̹� �ε�
		//2.Ŀ�ؼ� ���
			con=dc.getConn();
			
		//3.������ ������ü ���
			StringBuilder selectTestClob=new StringBuilder();
			selectTestClob
			.append("	select subject,content,to_char(input_date,'mm-dd-yyyy') input_date"	)
			.append("	from test_clob");
		
			pstmt=con.prepareStatement(selectTestClob.toString());
		//4.
		//5.
			rs=pstmt.executeQuery();
			
			ClobVO cVO=null;//���� ���� �÷��� �ϳ��� �����ϱ� ����
			String temp="";
			StringBuilder content=new StringBuilder();//���� �о���� CLOB�� �����ϱ� ���� 
			
			while(rs.next()) {
				cVO=new ClobVO();
				cVO.setSubject(rs.getString("subject"));//varchar2
//				cVO.setContent(rs.getString("content"));//clob
				cVO.setInputDate(rs.getString("input_date"));//date=>to_char�� �Ἥ char��
				
				//clob�� �о���̱� ���� ��Ʈ�� ����
				br=new BufferedReader(rs.getClob("content").getCharacterStream());
				
				while((temp=br.readLine())!=null) {
					content.append(temp).append("\n");
				}//end while
				cVO.setContent(content.toString());
				br.close();//��Ʈ�� �ݱ�
				
				content.delete(0, content.length());//content���� �ʱ�ȭ
				
				
				list.add(cVO);
			}//end while
			
			
		}finally {
		//��Ʈ�� �ݱ�
			if(br!=null) {br.close();}//end if
		
		//6.���� ����
			dc.close(rs, pstmt, con);
		}//end finally
		
		return list;
	}//useClob
	
	
	public static void main(String[] args) {
		try {
//			System.out.println(new UseClob().useClob());
			UseClob uc=new UseClob();
			List<ClobVO> list= uc.useClob();
			
			ClobVO cVO=null;
			for(int i=0;i<list.size();i++) {
				cVO=list.get(i);
				System.out.printf("��ȣ%d\n���� : %s\n�ۼ��� : %s\n���� : %s\n",i+1,cVO.getSubject()
						,cVO.getInputDate(),cVO.getContent());
			}
			
//			new UseClob().useClob();
		}catch (IOException ie) {
			ie.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//main

}//class
