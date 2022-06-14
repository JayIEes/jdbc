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
 * ���������� ó���ϸ鼭 �ʿ信 ���� DB�۾��� ����ϴ� Ŭ����<br>
 * XxxService Ŭ������ method���� ������ ���� �ۼ��Ѵ�.(���� ������ method������ �������� �ʴ´�.)
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
				("�߰��� ���������\",\" �� ���� �Է�\n �����ȣ,�����,����,����,���ʽ�");
		String[] data=tempData.split(",");
		
		if(data.length!=5) {//���������� �Է»�Ȳ
			JOptionPane.showMessageDialog(null, "�Էµ������� ������ ���� �ʽ��ϴ�.");
			return;//early return;
		}//end if
		
		
		//�� if �� ���ǿ� ���� ���� �� ��Ȳ�ڵ� (else�� ���� �ڵ�)
		//�������� �Է»�Ȳ
		//1.���� ���� ����. ���ڿ�=>��ȯ
		try {
			int empno=Integer.parseInt(data[0]);
			String ename=data[1];
			String job=data[2];
			int sal = Integer.parseInt(data[3]);
			double comm=Double.parseDouble(data[4]);
			
			//2.VO�� ���� �־�  (�ϳ��� ��ü�� ��Ƽ� ó��)
			CpEmp4InsertVO ceiVO=new CpEmp4InsertVO(empno,sal,ename,job,comm);
//			System.out.println(ceiVO);
			
			//3.DAO�� ����.
			stmtDAO.insertCpEmp4(ceiVO);
			JOptionPane.showMessageDialog(null,ceiVO.getEmpno()+"�� ��������� �߰��Ͽ����ϴ�.");
		
		}catch(SQLException se) {
			JOptionPane.showMessageDialog(null, "�˼��մϴ�. ����߰� �۾��� ������ �߻��Ͽ����ϴ�.");
			se.printStackTrace();
			
			//�پ��� ���� ��Ȳ ó��
			int errCode=se.getErrorCode();
			String sqlErrMsg="";
			
			switch(errCode) {
			
			case 1 : //ORA-0001
				sqlErrMsg="���� ��� ��ȣ�� �̹� �����մϴ�.";
				break;
			
			case 925 : //ORA-00925
				sqlErrMsg="�������� �߸��Ǿ����ϴ�.";
				break;
				
			case 1438 : //ORA-01438- ���ڰ��� ������ ũ�⺸�� ū ���
				sqlErrMsg="�����ȣ�� ���� 4�ڸ�����, ������ 5�ڸ�, ���ʽ� 5�ڸ� �Դϴ�.";
				break;
				
			case 12899 : //ORA-12899- ���ڿ� ���� ������ ũ�⺸�� ū ���
				sqlErrMsg="������� ���� 10�� �ѱ� 3�� �̳��̾���ϰ�, \n������ ���� 9�ڿ� �ѱ� 3�� �̳���� �մϴ�.";
				break;
				
			}//end switch
			
			JOptionPane.showMessageDialog(null, sqlErrMsg);
			
			
		}catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog( null, "�����ȣ, ����, ���ʽ��� ���ڿ����մϴ�.");
		}
		
		
	}//addCpEmp4
	
	
	
	public void modifyCpEmp4() {
		
		String tempData=JOptionPane.showInputDialog
				("������ ���������\",\" �� ���� �Է�\n �����ȣ,����,����"
						+ ",���ʽ�\n�����ȣ�� ��ġ�ϴ� ����� ����,����,���ʽ��� �����մϴ�.");
		String[] data=tempData.split(",");
		
		if(data.length!=4) {//���������� �Է»�Ȳ
			JOptionPane.showMessageDialog(null, "�Էµ������� ������ ���� �ʽ��ϴ�.");
			return;//early return;
		}//end if
		
		
		//�������� �Է»�Ȳ
		try {
			//1.�������� ����.
			int empno=Integer.parseInt(data[0]);
			String job=data[1];
			int sal = Integer.parseInt(data[2]);
			double comm=Double.parseDouble(data[3]);
			
			//2.VO�� �� ����
			CpEmp4UpdateVO ceuVO=new CpEmp4UpdateVO(empno,sal,job,comm);
			
			//3.DBMS�� ����
			int cnt=stmtDAO.updateCpEmp4(ceuVO);
			
			String msg=ceuVO.getEmpno()+"�� ����� �������� �ʽ��ϴ�.";
			if(cnt==1) {
				msg=ceuVO.getEmpno()+"�� ��������� ����Ǿ����ϴ�.";
			}//end if
			
			JOptionPane.showMessageDialog(null, msg);
			
			
		}catch(SQLException se) {
			se.printStackTrace();
			
			//�پ��� ���� ��Ȳ ó��
			int errCode=se.getErrorCode();
			String sqlErrMsg="";
			
			switch(errCode) {
			case 925 : //ORA-00925
				sqlErrMsg="�������� �߸��Ǿ����ϴ�.";break;
				
			case 1438 : //ORA-01438- ���ڰ��� ������ ũ�⺸�� ū ���
				sqlErrMsg="������ 5�ڸ�, ���ʽ� 5�ڸ� �Դϴ�.";break;
				
			case 12899 : //ORA-12899- ���ڿ� ���� ������ ũ�⺸�� ū ���
				sqlErrMsg="������ ���� 9�ڿ� �ѱ� 3�� �̳���� �մϴ�.";
				break;
			}//end switch
			
			JOptionPane.showMessageDialog(null, sqlErrMsg);
		}catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null,"�����ȣ, ����, ���ʽ��� ���ڿ����մϴ�.");
		}//end catch
		
	
		
		
	}//modifyCpEmp4
	
	
	
	public void removeCpEmp4() {
		
		String tempData=JOptionPane.showInputDialog("������ ��������� �Է����ּ���.");
		
		try {
			//�Է°� ó��
			int empno=Integer.parseInt(tempData);
		
			//DB�۾� ����
			int cnt=stmtDAO.deleteCpEmp4(empno);
			
			String msg=empno+"�� ����� �������� �ʽ��ϴ�.";
			if(cnt==1) {
				msg=empno+"�� ��������� �����Ǿ����ϴ�.";
			}//end if
			
			JOptionPane.showMessageDialog(null, msg);
			
		}catch(SQLException se) {
			se.printStackTrace();
			
			//�پ��� ���� ��Ȳ ó��
			int errCode=se.getErrorCode();
			String sqlErrMsg="";
			
			switch(errCode) {
			case 925 : //ORA-00925
				sqlErrMsg="�������� �߸��Ǿ����ϴ�."; break;
			}//end switch
			
			JOptionPane.showMessageDialog(null, sqlErrMsg);
			
			
		}catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null,"�����ȣ�� ���ڷθ� �����˴ϴ�.");
		}
		
	}//removeCpEmp4
	
	
	
	
	public void searchOneCpEmp4() {
		
		String tempData=JOptionPane.showInputDialog("��ȸ�� ��������� �Է����ּ���.");
		
		try {
			//�Է°� ó��
			int empno=Integer.parseInt(tempData);
			
			//DB�۾�
			CpEmp4SelectOneVO cesoVO= stmtDAO.selectCpEmp4(empno);
			
			//��µ����� �����
			StringBuilder empOutputData=new StringBuilder();
			empOutputData.append(empno).append("�� ������� ��ȸ ���\n");
			
			if(cesoVO==null) {//�����ȣ��  ��ȸ�� ����� ����
				empOutputData.append("��ȸ�� ����� �����ϴ�. �����ȣ�� Ȯ�����ּ���.");
				
			}else {
				empOutputData.append("�����: ").append(cesoVO.getEname()).append("\n");
				empOutputData.append("����: ").append(cesoVO.getSal()).append("\n");
				empOutputData.append("���ʽ�: ").append(cesoVO.getComm()).append("\n");
				empOutputData.append("����: ").append(cesoVO.getJob());
				
			}//end else
			
			JOptionPane.showMessageDialog(null, new JTextArea(empOutputData.toString(),10,50));

		}catch(SQLException se) {
			JOptionPane.showMessageDialog(null, "��������� ��ȸ�� �� �����ϴ�.");
			se.printStackTrace();
		}catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "�����ȣ�� �����̾�� �մϴ�.");
		}//end catch
		
	}//searchOneCpEmp4
	
	
	
	
	
	public void searchAllCpEmp4() {
		//��� ���ڵ带 ��ȸ�Ѵ�.
		try {
			List<CpEmp4SelectAllVO> empList=stmtDAO.selectCpEmp4();
			
			StringBuilder outputData=new StringBuilder();
			outputData.append("��ü��� ��ȸ\n");
			outputData.append("�����ȣ\t�����\t����\t����\t���ʽ�\t�Ի���\t�Ի���\n");
			
			//��ȸ�� ����� ���� �� ������ ����.
			if(empList.isEmpty()){
				outputData.append("��ȸ�� ��������� �����ϴ�.");
			}//end if
			
			//����Ʈ�� �ݺ����� ��� ������ ����Ѵ�.
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			for(CpEmp4SelectAllVO cesaVO : empList) {
				//��¥ó��
				
				
				outputData
				.append(cesaVO.getEmpno()).append("\t")
				.append(cesaVO.getEname()).append("\t")
				.append(cesaVO.getJob()).append("\t")
				.append(cesaVO.getSal()).append("\t")
				.append(cesaVO.getComm()).append("\t")
				.append(cesaVO.getHiredate2()).append("\t")
				.append(sdf.format(cesaVO.getHiredate())).append("\n");
			}//end for
			outputData.append(empList.size()).append("�� ��ȸ");
			
			
			JTextArea jta=new JTextArea(outputData.toString(),15,60);
			JScrollPane jsp=new JScrollPane(jta);
			jsp.setBorder(new TitledBorder("��ü��� ���� ��ȸ"));
			
			JOptionPane.showMessageDialog(null, jsp);
			
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "��ü ��� ��ȸ �� ���� �߻�");
		}//end catch
		
		
	}//searchAllCpEmp4
	
	
	
	
	
	public void menu() {
		
		boolean exitFlag=false;
		String inputMenu="";
		
		try {
			do {
				inputMenu=JOptionPane.showInputDialog
						("�޴� ����\n1.����߰� 2.������� 3.������� 4.�����ȸ 5.�������ȸ 6.����\n ��ȣ�� �Է��ϼ���.");
			
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
			//����ڰ� ��ҳ�, x�� ���� ���
		}finally {
			JOptionPane.showMessageDialog(null, "���α׷��� ����Ǿ����ϴ�.");
		}//end catch
		
	}//menu
	
	
	public static void main(String[] args) {
		new UseStatementDAOService().menu();
	}//main

}//class
