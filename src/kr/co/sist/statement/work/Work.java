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
		super("�̸�����");
	
		jtfName=new JTextField(10);
		JButton jbtnInput= new JButton("�Է�");
		
		setLayout(new FlowLayout());
		
		add(new JLabel("�̸�"));
		add(jtfName);
		add(jbtnInput);
		
		//2. �̺�Ʈ ���
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
		//�̸� �����ͼ� ��ȿ�� ����.
		
		String name=jtfName.getText().trim();
		
		if(name.equals("")) {
			JOptionPane.showMessageDialog(this, "�߰��� �̸��� �Է����ּ���.");
			return;
		}//end if
		
		//��ȿ�� ������ �Ǿ��ٸ� DB�۾��� ����
		try {
			//DB�۾� ����
			insertName(name);
			//��� ����ڿ��� �����ְ�
			JOptionPane.showMessageDialog(this, name+"���� ������ �߰��ϼ̽��ϴ�.");
			//�Է��� ���ϰ��ϱ� ���ؼ� �ʱ�ȭ
			jtfName.setText("");
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(this, "�̸� �߰� �۾� �߿� ������ �߻��Ͽ����ϴ�.");
			e.printStackTrace();
			
		}//end catch
		
		
	}//getJtfName
	
	
	private void insertName(String name) throws SQLException {
		Connection con=null;
		Statement stmt=null;
		
		try {
		//1. ����̹� �ε�
		//2. Ŀ�ؼ� ���
		con=getDbConnection();
		
		//3. ������ ������ü ���
		stmt=con.createStatement();
		//4. ������ ���� �� ��� ���
		StringBuilder insertName=new StringBuilder();
		insertName.append("insert into work_statement(name) values('")
		.append(name).append("')");
		
		stmt.executeUpdate(insertName.toString()); //DBMS�� �̸��� �߰��ȴ�.
		
		
		}finally {
		//5. ���� ����
			if(stmt!=null) {stmt.close();}//end if
			if(con!=null) {con.close();}//end if
		}//end finally
		
	}//insertName
	
	
	
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
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		getJtfName();
	}//actionPerformed

	
	
	
	public static void main(String[] args) {
		new Work();
	}//main

}
