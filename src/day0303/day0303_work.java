package day0303;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import kr.co.sist.statement.vo.CpEmp4InsertVO;

public class day0303_work extends JFrame implements ActionListener{

	private JTextField jtf;
	
	public day0303_work() {
		JLabel jlbl=new JLabel("�̸� :");
		JButton jbtn=new JButton("�Է�");
		jtf=new JTextField();
		
		jbtn.addActionListener(this);
		
		add(jlbl);
		add(jtf);
		add(jbtn);
	
		setLayout(null);
		
		jlbl.setBounds(30, 30, 50, 20);
		jtf.setBounds(80, 30, 100, 20);
		jbtn.setBounds(200, 30, 70, 20);
		
		setSize(300,130);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}//day0303_work
	
	
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		
		try {
			insertWork();
		} catch (SQLException e1) {
			e1.printStackTrace();
		};
		
	}
	
	
	
	
	private Connection getDbConnection() throws SQLException{
		
		Connection con=null;
		
		//1.����̹� �ε�
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		
		//2.Connection ���
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String id="scott";
		String pass="tiger";
		
		con=DriverManager.getConnection(url, id, pass);
		
		return con;
	}//getDbConnection


	
	
	private void insertWork() throws SQLException{
			
		
		
		String tempData= jtf.getText();
		
		
		Connection con=null;
		Statement stmt=null;
		
		
		
		if(!tempData.equals("")) {
			if(!tempData.contains("1")&&!tempData.contains("2")&&!tempData.contains("3")
					&&!tempData.contains("4")&&!tempData.contains("5")&&!tempData.contains("6")
					&&!tempData.contains("7")&&!tempData.contains("8")&&!tempData.contains("9")) {
				try {
					con=getDbConnection();
					stmt=con.createStatement();
					
					//4.���� ���� �� ��� ���
					String insertQuery="insert into work1 values('"+tempData+"')";
					
					stmt.executeUpdate(insertQuery);
					JOptionPane.showMessageDialog(null, tempData+"��(��) ���������� �߰��Ǿ����ϴ�.");
					
					
				}catch(SQLException se) {
				
					JOptionPane.showMessageDialog(null, "�˼��մϴ�. �߰� �۾��� ������ �߻��Ͽ����ϴ�.");
					se.printStackTrace();
					
					//�پ��� ���� ��Ȳ ó��
					int errCode=se.getErrorCode();
					String sqlErrMsg="";
					
					System.out.println(se.getErrorCode());
					switch(errCode) {
						case 925 : //ORA-00925
							sqlErrMsg="�������� �߸��Ǿ����ϴ�.";
							break;
						
						case 12899 : //ORA-12899- ���ڿ� ���� ������ ũ�⺸�� ū ���
							sqlErrMsg="�̸��� ���� 15�� �ѱ� 5�� �̳��̾�� �մϴ�.";
							break;
							
						case 1400 : //ORA-01400- null 
							sqlErrMsg= "���� �Է����ּ���.";
							break;
					}//end switch
					
					JOptionPane.showMessageDialog(null, sqlErrMsg);
					
				}finally {
					//5.�������
					if(stmt!=null) {stmt.close();}//end if
					if(con!=null) {con.close();}//end if
					
					jtf.setText("");
				}//end finally
			}else{
				JOptionPane.showMessageDialog(jtf, "���ڴ� �Է� �Ұ����մϴ�.");
			
			}
		}else {
			JOptionPane.showMessageDialog(jtf, "���� �Է����ּ���.");
			}
	}//insertWork
	
	
	
	
	public static void main(String[] args) {
		new day0303_work();
	}







}
