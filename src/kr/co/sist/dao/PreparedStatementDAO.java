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
	 * 사원번호는 시퀀스를 사용한다.
	 * @param ceVO
	 * @throws SQLException
	 */
	public void insertCpEmp4(CpEmp4VO ceVO) throws SQLException {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		DbConnection dc=DbConnection.getInstance();
		try {
		//1.드라이버 로딩
		
		//2.커넥션 얻기
			conn=dc.getConn();
			
		//3.쿼리문을 넣어 쿼리문 생성객체 얻기
			String insertCpEmp
			="insert into cp_emp4(empno,ename,job,sal,comm) values(seq_cp_emp4.nextval,?,?,?,?)";
			pstmt=conn.prepareStatement(insertCpEmp);
		
		//4.바인드 변수에 값 설정
			pstmt.setString(1, ceVO.getEname());//처음 바인드 변수에 문자열로 사원번호를 넣는다.	
			pstmt.setString(2, ceVO.getJob());//둔번째 바인드 변수에 문자열로 사원번호를 넣는다.	
			pstmt.setInt(3, ceVO.getSal());//세번째 바인드 변수에 문자열로 사원번호를 넣는다.	
			pstmt.setDouble(4, ceVO.getComm());//네번째 바인드 변수에 문자열로 사원번호를 넣는다.	
		//5.쿼리 수행 후 결과 얻기
			pstmt.executeUpdate();
			
			
		}finally {
			//6.연결끊기
			dc.close(null, pstmt, conn);
		}//end finally
		
	}//insertCpEmp4
	
	
	
	public int updateCpEmp4(CpEmp4VO ceVO) throws SQLException {
		int cnt=0;
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		DbConnection dc=DbConnection.getInstance();
		
		try {
		//1.드라이버 로딩
		
		//2.커넥션 얻기
			conn=dc.getConn();
			
		//3.쿼리문을 넣어 쿼리문 실행객체 얻기
			StringBuilder updateCpEmp4=new StringBuilder();
			updateCpEmp4
			.append("	update cp_emp4")
			.append("	set job=?, sal=?, comm=?")//바인드 변수에는 문자열일지라도 홀따옴표 사용하지 않음
			.append("	where empno=?");
			
			pstmt=conn.prepareStatement(updateCpEmp4.toString());
		
			
		//4.바인드 변수에 값 할당
			pstmt.setString(1, ceVO.getJob());	
			pstmt.setInt(2, ceVO.getSal());
			pstmt.setDouble(3, ceVO.getComm());	
			pstmt.setInt(4, ceVO.getEmpno());

		//5.쿼리 수행 후 결과 얻기
			cnt=pstmt.executeUpdate();
			
			
		}finally {
			//6.연결끊기
			dc.close(null, pstmt, conn);
		}//end finally
		
		
		return cnt;
	}//insertCpEmp4
	
	
	
	
	/**
	 * 사원번호를 입력받아 입력된 사원번호에 해당하는 모든 사원을 삭제.
	 * @param empno
	 * @return
	 * @throws SQLException
	 */
	public int deleteCpEmp4(int empno) throws SQLException {
		int cnt=0;
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		DbConnection dc=DbConnection.getInstance();//싱글톤으로 객체 얻기
		
		try {
		//1.드라이버 로딩
		
		//2.커넥션 얻기
			conn=dc.getConn();
			
		//3.쿼리문을 넣어 쿼리문 실행객체 얻기
			String deleteCpEmp4="delete from cp_emp4 where empno=?" ;
			
			pstmt=conn.prepareStatement(deleteCpEmp4);
		
			
		//4.쿼리문에 있는 바인드 변수에 값 설정
			pstmt.setInt(1,empno);

		//5.쿼리 수행 후 결과 얻기
			cnt=pstmt.executeUpdate();
			
			
		}finally {
			//6.연결끊기
			dc.close(null, pstmt, conn);
		}//end finally
		
		return cnt;
	}//deleteCpEmp4
	
	
	
	public CpEmp4VO selectOneCpEmp4(int empno) throws SQLException {
		CpEmp4VO ceVO=null; 
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConnection dc=DbConnection.getInstance();//싱글톤으로 객체 얻기
		
		try {
		//1.드라이버 로딩
		
		//2.커넥션 얻기
			conn=dc.getConn();
			
		//3.쿼리문을 넣어 쿼리문 실행객체 얻기
			StringBuilder selectCpEmp4=new StringBuilder();
			selectCpEmp4
			.append("	select ename,sal,comm,job	")
			.append("	from cp_emp4				")
			.append("	where empno=?				");
					
			
			pstmt=conn.prepareStatement(selectCpEmp4.toString());
		
			
		//4.쿼리문에 있는 바인드 변수에 값 설정
			pstmt.setInt(1,empno);

		//5.쿼리 수행 후 결과 얻기
			rs=pstmt.executeQuery();
				
			if(rs.next()) {//조회된 레코드 있음
				ceVO=new CpEmp4VO();//조회컬럼 값을 저장하기 위해 VO생성
//				ceVO.setEmpno(empno);//매개변수 값을 VO설정
				//컬럼명=>가독성이 좋다
				ceVO.setEname(rs.getString("ename"));
				ceVO.setSal(rs.getInt("sal"));
				ceVO.setComm(rs.getDouble("comm")); 
				ceVO.setJob(rs.getString("job"));
				//컬럼의 인덱스를 사용. 조회쿼리 첫번째 칼럼부터 1번의 인덱스를 가진다.=>가독성이 좋지 않음.권장X
//				ceVO.setEname(rs.getString(1));
//				ceVO.setSal(rs.getInt(2));
//				ceVO.setComm(rs.getDouble(3)); 
//				ceVO.setJob(rs.getString(4));
			}//end if
			
			
		}finally {
			//6.연결끊기
			dc.close(rs, pstmt, conn);
		}//end finally
		
		
		return ceVO;
	}//selectOneCpEmp4
	
	
	
	
	public List<CpEmp4VO> selectAllCpEmp4() throws SQLException {
		
		List<CpEmp4VO> list=new ArrayList<CpEmp4VO>();
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConnection dc=DbConnection.getInstance();//싱글톤으로 객체 얻기
		
		try {
		//1.드라이버 로딩
		
		//2.커넥션 얻기
			conn=dc.getConn();
			
		//3.쿼리문을 넣어 쿼리문 실행객체 얻기
			String selectCpEmp4="select empno,ename,job,hiredate,sal,comm from cp_emp4";
			pstmt=conn.prepareStatement(selectCpEmp4);
			
		//4.바인드 변수에 값 설정

		//5.쿼리 수행 후 결과 얻기
			rs=pstmt.executeQuery();
			
			CpEmp4VO ceVO = null;
			while(rs.next()) {//조회된 레코드가 존재하면
				//컬럼의 값을 하나의 VO에 저장.
				ceVO=new CpEmp4VO();
				
				ceVO.setEmpno(rs.getInt("empno"));
				ceVO.setEname(rs.getString("ename"));
				ceVO.setJob(rs.getString("job"));
				ceVO.setHiredate(rs.getDate("hiredate"));
				ceVO.setSal(rs.getInt("sal"));
				ceVO.setComm(rs.getDouble("comm")); 
				
				//같은 이름의 VO를 여러 개 관리하기 위해 List에 추가(ceVO객체는 가장 마지막 레코드만 저장)
				list.add(ceVO);//이전 조회된 레코드의 값을 사용할 수 있도록 list에 추가한다.
				
			}//end while
			
			
		}finally {
			//6.연결끊기
			dc.close(rs, pstmt, conn);
		}//end finally
		
		return list;
	}//selectAllCpEmp4
	
	
	
	
}//class
