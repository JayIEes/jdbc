package kr.co.sist.statement.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.print.attribute.standard.OutputDeviceAssigned;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import kr.co.sist.statement.dao.StatementDAO;
import kr.co.sist.statement.vo.CpEmp4InsertVO;
import kr.co.sist.statement.vo.CpEmp4SelectAllVO;
import kr.co.sist.statement.vo.CpEmp4SelectOneVO;
import kr.co.sist.statement.vo.CpEmp4UpdateVO;

/**
 * 업무로직을 처리하면서 필요에 따라 DB작업을 사용하는 클래스<br>
 * XxxService 클래스의 method명은 업무의 용어로 작성한다.(절대 쿼리를 method명으로 설정하지 않는다.)
 * @author user
 */
public class UseStatementDAOService {

	public static final String ADD="1";
	public static final String MODIFY="2";
	public static final String REMOVE="3";
	public static final String SEARCH_ONE="4";
	public static final String SEARCH_ALL="5";
	public static final String EXIT="6";
	
	private StatementDAO stmtDAO;
	
	
	public UseStatementDAOService() {
		stmtDAO=new StatementDAO();
		
	}//UseStatementDAOService
	
	
	public void addCpEmp4() {
		String tempData=JOptionPane.showInputDialog
				("추가할 사원정보를\",\" 로 구분 입력\n 사원번호,사원명,직무,연봉,보너스");
		String[] data=tempData.split(",");
		
		if(data.length!=5) {//비정상적인 입력상황
			JOptionPane.showMessageDialog(null, "입력데이터의 개수가 맞지 않습니다.");
			return;//early return;
		}//end if
		
		
		//위 if 의 조건에 맞지 않을 때 상황코드 (else에 대한 코드)
		//정상적인 입력상황
		//1.값에 대한 가공. 문자열=>변환
		try {
			int empno=Integer.parseInt(data[0]);
			String ename=data[1];
			String job=data[2];
			int sal = Integer.parseInt(data[3]);
			double comm=Double.parseDouble(data[4]);
			
			//2.VO에 값을 넣어  (하나의 객체에 모아서 처리)
			CpEmp4InsertVO ceiVO=new CpEmp4InsertVO(empno,sal,ename,job,comm);
//			System.out.println(ceiVO);
			
			//3.DAO에 전달.
			stmtDAO.insertCpEmp4(ceiVO);
			JOptionPane.showMessageDialog(null,ceiVO.getEmpno()+"번 사원정보를 추가하였습니다.");
		
		}catch(SQLException se) {
			JOptionPane.showMessageDialog(null, "죄송합니다. 사원추가 작업에 문제가 발생하였습니다.");
			se.printStackTrace();
			
			//다양한 예외 상황 처리
			int errCode=se.getErrorCode();
			String sqlErrMsg="";
			
			switch(errCode) {
			
			case 1 : //ORA-0001
				sqlErrMsg="같은 사원 번호가 이미 존재합니다.";
				break;
			
			case 925 : //ORA-00925
				sqlErrMsg="쿼리문이 잘못되었습니다.";
				break;
				
			case 1438 : //ORA-01438- 숫자값이 정해진 크기보다 큰 경우
				sqlErrMsg="사원번호는 숫자 4자리까지, 연봉은 5자리, 보너스 5자리 입니다.";
				break;
				
			case 12899 : //ORA-12899- 문자열 값이 정해진 크기보다 큰 경우
				sqlErrMsg="사원명은 영어 10자 한글 3자 이내이어야하고, \n직무는 영어 9자와 한글 3자 이내어야 합니다.";
				break;
				
			}//end switch
			
			JOptionPane.showMessageDialog(null, sqlErrMsg);
			
			
		}catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog( null, "사원번호, 연봉, 보너스는 숫자여야합니다.");
		}
		
		
	}//addCpEmp4
	
	
	
	public void modifyCpEmp4() {
		
		String tempData=JOptionPane.showInputDialog
				("수정할 사원정보를\",\" 로 구분 입력\n 사원번호,직무,연봉"
						+ ",보너스\n사원번호에 일치하는 사원의 직무,연봉,보너스를 변경합니다.");
		String[] data=tempData.split(",");
		
		if(data.length!=4) {//비정상적인 입력상황
			JOptionPane.showMessageDialog(null, "입력데이터의 개수가 맞지 않습니다.");
			return;//early return;
		}//end if
		
		
		//정상적인 입력상황
		try {
			//1.데이터의 가공.
			int empno=Integer.parseInt(data[0]);
			String job=data[1];
			int sal = Integer.parseInt(data[2]);
			double comm=Double.parseDouble(data[3]);
			
			//2.VO에 값 설정
			CpEmp4UpdateVO ceuVO=new CpEmp4UpdateVO(empno,sal,job,comm);
			
			//3.DBMS에 전달
			int cnt=stmtDAO.updateCpEmp4(ceuVO);
			
			String msg=ceuVO.getEmpno()+"번 사원은 존재하지 않습니다.";
			if(cnt==1) {
				msg=ceuVO.getEmpno()+"번 사원정보가 변경되었습니다.";
			}//end if
			
			JOptionPane.showMessageDialog(null, msg);
			
			
		}catch(SQLException se) {
			se.printStackTrace();
			
			//다양한 예외 상황 처리
			int errCode=se.getErrorCode();
			String sqlErrMsg="";
			
			switch(errCode) {
			case 925 : //ORA-00925
				sqlErrMsg="쿼리문이 잘못되었습니다.";break;
				
			case 1438 : //ORA-01438- 숫자값이 정해진 크기보다 큰 경우
				sqlErrMsg="연봉은 5자리, 보너스 5자리 입니다.";break;
				
			case 12899 : //ORA-12899- 문자열 값이 정해진 크기보다 큰 경우
				sqlErrMsg="직무는 영어 9자와 한글 3자 이내어야 합니다.";
				break;
			}//end switch
			
			JOptionPane.showMessageDialog(null, sqlErrMsg);
		}catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null,"사원번호, 연봉, 보너스는 숫자여야합니다.");
		}//end catch
		
	
		
		
	}//modifyCpEmp4
	
	
	
	public void removeCpEmp4() {
		
		String tempData=JOptionPane.showInputDialog("삭제할 사원정보를 입력해주세요.");
		
		try {
			//입력값 처리
			int empno=Integer.parseInt(tempData);
		
			//DB작업 수행
			int cnt=stmtDAO.deleteCpEmp4(empno);
			
			String msg=empno+"번 사원은 존재하지 않습니다.";
			if(cnt==1) {
				msg=empno+"번 사원정보가 삭제되었습니다.";
			}//end if
			
			JOptionPane.showMessageDialog(null, msg);
			
		}catch(SQLException se) {
			se.printStackTrace();
			
			//다양한 예외 상황 처리
			int errCode=se.getErrorCode();
			String sqlErrMsg="";
			
			switch(errCode) {
			case 925 : //ORA-00925
				sqlErrMsg="쿼리문이 잘못되었습니다."; break;
			}//end switch
			
			JOptionPane.showMessageDialog(null, sqlErrMsg);
			
			
		}catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null,"사원번호는 숫자로만 구성됩니다.");
		}
		
	}//removeCpEmp4
	
	
	
	
	public void searchOneCpEmp4() {
		
		String tempData=JOptionPane.showInputDialog("조회할 사원정보를 입력해주세요.");
		
		try {
			//입력값 처리
			int empno=Integer.parseInt(tempData);
			
			//DB작업
			CpEmp4SelectOneVO cesoVO= stmtDAO.selectCpEmp4(empno);
			
			//출력데이터 만들기
			StringBuilder empOutputData=new StringBuilder();
			empOutputData.append(empno).append("번 사원정보 조회 결과\n");
			
			if(cesoVO==null) {//사원번호로  조회한 결과가 없음
				empOutputData.append("조회된 사원이 없습니다. 사원번호를 확인해주세요.");
				
			}else {
				empOutputData.append("사원명: ").append(cesoVO.getEname()).append("\n");
				empOutputData.append("연봉: ").append(cesoVO.getSal()).append("\n");
				empOutputData.append("보너스: ").append(cesoVO.getComm()).append("\n");
				empOutputData.append("직무: ").append(cesoVO.getJob());
				
			}//end else
			
			JOptionPane.showMessageDialog(null, new JTextArea(empOutputData.toString(),10,50));

		}catch(SQLException se) {
			JOptionPane.showMessageDialog(null, "사원정보를 조회할 수 없습니다.");
			se.printStackTrace();
		}catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "사원번호는 숫자이어야 합니다.");
		}//end catch
		
	}//searchOneCpEmp4
	
	
	
	
	
	public void searchAllCpEmp4() {
		//모든 레코드를 조회한다.
		try {
			List<CpEmp4SelectAllVO> empList=stmtDAO.selectCpEmp4();
			
			StringBuilder outputData=new StringBuilder();
			outputData.append("전체사원 조회\n");
			outputData.append("사원번호\t사원명\t직무\t연봉\t보너스\t입사일\t입사일\n");
			
			//조회된 경과가 없을 때 보여줄 내용.
			if(empList.isEmpty()){
				outputData.append("조회된 사원정보가 없습니다.");
			}//end if
			
			//리스트를 반복시켜 모든 정보를 출력한다.
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			for(CpEmp4SelectAllVO cesaVO : empList) {
				//날짜처리
				
				
				outputData
				.append(cesaVO.getEmpno()).append("\t")
				.append(cesaVO.getEname()).append("\t")
				.append(cesaVO.getJob()).append("\t")
				.append(cesaVO.getSal()).append("\t")
				.append(cesaVO.getComm()).append("\t")
				.append(cesaVO.getHiredate2()).append("\t")
				.append(sdf.format(cesaVO.getHiredate())).append("\n");
			}//end for
			outputData.append(empList.size()).append("명 조회");
			
			
			JTextArea jta=new JTextArea(outputData.toString(),15,60);
			JScrollPane jsp=new JScrollPane(jta);
			jsp.setBorder(new TitledBorder("전체사원 정보 조회"));
			
			JOptionPane.showMessageDialog(null, jsp);
			
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "전체 사원 조회 중 문제 발생");
		}//end catch
		
		
	}//searchAllCpEmp4
	
	
	
	
	
	public void menu() {
		
		boolean exitFlag=false;
		String inputMenu="";
		
		try {
			do {
				inputMenu=JOptionPane.showInputDialog
						("메뉴 선택\n1.사원추가 2.사원변경 3.사원삭제 4.사원조회 5.모든사원조회 6.종료\n 번호를 입력하세요.");
			
				switch(inputMenu) {
				case ADD : addCpEmp4(); break; 
				case MODIFY : modifyCpEmp4(); break; 
				case REMOVE : removeCpEmp4(); break; 
				case SEARCH_ONE : searchOneCpEmp4(); break; 
				case SEARCH_ALL : searchAllCpEmp4(); break; 
				case EXIT : exitFlag=true; break; 
					
				}//end switch
			
			
			}while(!exitFlag);
		}catch(NullPointerException npe) {
			//사용자가 취소나, x를 누른 경우
		}finally {
			JOptionPane.showMessageDialog(null, "프로그램이 종료되었습니다.");
		}//end catch
		
	}//menu
	
	
	public static void main(String[] args) {
		new UseStatementDAOService().menu();
	}//main

}//class
