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
 * stream을 연결하여 CLOB 데이터형 사용
 */
public class UseClob {

	
	
	public List<ClobVO> useClob()throws SQLException,IOException{
		
		List<ClobVO> list=new ArrayList<ClobVO>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		BufferedReader br= null;//CLOB데이터형을 읽어 들이기 위한 스트림
		
		DbConnection dc=DbConnection.getInstance();
		
		try {
		//1.드라이버 로딩
		//2.커넥션 얻기
			con=dc.getConn();
			
		//3.쿼리문 생성객체 얻기
			StringBuilder selectTestClob=new StringBuilder();
			selectTestClob
			.append("	select subject,content,to_char(input_date,'mm-dd-yyyy') input_date"	)
			.append("	from test_clob");
		
			pstmt=con.prepareStatement(selectTestClob.toString());
		//4.
		//5.
			rs=pstmt.executeQuery();
			
			ClobVO cVO=null;//여러 개의 컬럼을 하나로 저장하기 위해
			String temp="";
			StringBuilder content=new StringBuilder();//한줄 읽어들인 CLOB를 저장하기 위한 
			
			while(rs.next()) {
				cVO=new ClobVO();
				cVO.setSubject(rs.getString("subject"));//varchar2
//				cVO.setContent(rs.getString("content"));//clob
				cVO.setInputDate(rs.getString("input_date"));//date=>to_char를 써서 char로
				
				//clob를 읽어들이기 위해 스트림 연결
				br=new BufferedReader(rs.getClob("content").getCharacterStream());
				
				while((temp=br.readLine())!=null) {
					content.append(temp).append("\n");
				}//end while
				cVO.setContent(content.toString());
				br.close();//스트림 닫기
				
				content.delete(0, content.length());//content변수 초기화
				
				
				list.add(cVO);
			}//end while
			
			
		}finally {
		//스트림 닫기
			if(br!=null) {br.close();}//end if
		
		//6.연결 끊기
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
				System.out.printf("번호%d\n제목 : %s\n작성일 : %s\n내용 : %s\n",i+1,cVO.getSubject()
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
