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
		JLabel jlbl=new JLabel("이름 :");
		JButton jbtn=new JButton("입력");
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
		
		//1.드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		
		//2.Connection 얻기
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
					
					//4.쿼리 수행 후 결과 얻기
					String insertQuery="insert into work1 values('"+tempData+"')";
					
					stmt.executeUpdate(insertQuery);
					JOptionPane.showMessageDialog(null, tempData+"이(가) 정상적으로 추가되었습니다.");
					
					
				}catch(SQLException se) {
				
					JOptionPane.showMessageDialog(null, "죄송합니다. 추가 작업에 문제가 발생하였습니다.");
					se.printStackTrace();
					
					//다양한 예외 상황 처리
					int errCode=se.getErrorCode();
					String sqlErrMsg="";
					
					System.out.println(se.getErrorCode());
					switch(errCode) {
						case 925 : //ORA-00925
							sqlErrMsg="쿼리문이 잘못되었습니다.";
							break;
						
						case 12899 : //ORA-12899- 문자열 값이 정해진 크기보다 큰 경우
							sqlErrMsg="이름은 영어 15자 한글 5자 이내이어야 합니다.";
							break;
							
						case 1400 : //ORA-01400- null 
							sqlErrMsg= "값을 입력해주세요.";
							break;
					}//end switch
					
					JOptionPane.showMessageDialog(null, sqlErrMsg);
					
				}finally {
					//5.연결끊기
					if(stmt!=null) {stmt.close();}//end if
					if(con!=null) {con.close();}//end if
					
					jtf.setText("");
				}//end finally
			}else{
				JOptionPane.showMessageDialog(jtf, "숫자는 입력 불가능합니다.");
			
			}
		}else {
			JOptionPane.showMessageDialog(jtf, "값을 입력해주세요.");
			}
	}//insertWork
	
	
	
	
	public static void main(String[] args) {
		new day0303_work();
	}







}
