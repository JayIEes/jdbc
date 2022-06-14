package kr.co.sist.statement.work;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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

public class Work extends JFrame implements ActionListener {

	private JTextField jtfName;
	
	
	public Work() {
		super("이름저장");
	
		jtfName=new JTextField(10);
		JButton jbtnInput= new JButton("입력");
		
		setLayout(new FlowLayout());
		
		add(new JLabel("이름"));
		add(jtfName);
		add(jbtnInput);
		
		//2. 이벤트 등록
		jtfName.addActionListener(this);
		jbtnInput.addActionListener(this);
		
		
		setBounds(100,100,400,150);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				dispose();
			}
		});
		
	
	}//Work
	
	private void getJtfName() {
		//이름 가져와서 유효성 검증.
		
		String name=jtfName.getText().trim();
		
		if(name.equals("")) {
			JOptionPane.showMessageDialog(this, "추가할 이름을 입력해주세요.");
			return;
		}//end if
		
		//유효성 검증이 되었다면 DB작업을 수행
		try {
			//DB작업 수행
			insertName(name);
			//결과 사용자에게 보여주고
			JOptionPane.showMessageDialog(this, name+"님의 정보를 추가하셨습니다.");
			//입력을 편하게하기 위해서 초기화
			jtfName.setText("");
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(this, "이름 추가 작업 중에 문제가 발생하였습니다.");
			e.printStackTrace();
			
		}//end catch
		
		
	}//getJtfName
	
	
	private void insertName(String name) throws SQLException {
		Connection con=null;
		Statement stmt=null;
		
		try {
		//1. 드라이버 로딩
		//2. 커넥션 얻기
		con=getDbConnection();
		
		//3. 쿼리문 생성객체 얻기
		stmt=con.createStatement();
		//4. 쿼리문 수행 후 결과 얻기
		StringBuilder insertName=new StringBuilder();
		insertName.append("insert into work_statement(name) values('")
		.append(name).append("')");
		
		stmt.executeUpdate(insertName.toString()); //DBMS에 이름이 추가된다.
		
		
		}finally {
		//5. 연결 끊기
			if(stmt!=null) {stmt.close();}//end if
			if(con!=null) {con.close();}//end if
		}//end finally
		
	}//insertName
	
	
	
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
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		getJtfName();
	}//actionPerformed

	
	
	
	public static void main(String[] args) {
		new Work();
	}//main

}
