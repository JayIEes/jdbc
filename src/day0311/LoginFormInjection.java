package day0311;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class LoginFormInjection extends JFrame implements ActionListener {

	private JTextField jtfId;
	private JPasswordField jpfPass;
	private JLabel jlblOutput;
	
	public LoginFormInjection() {
		super("로그인 인증");
		
		jtfId=new JTextField();
		jpfPass=new JPasswordField();
		jlblOutput=new JLabel("인증결과");
		
		jtfId.setBorder(new TitledBorder("아이디"));
		jpfPass.setBorder(new TitledBorder("비밀번호"));
		jlblOutput.setBorder(new TitledBorder("인증결과"));
		
		setLayout(new GridLayout(3,1));
		
		jtfId.addActionListener(this);
		jpfPass.addActionListener(this);
		
		add(jtfId);
		add(jpfPass);
		add(jlblOutput);
		
		setBounds(100, 100, 300, 250);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//LoginFormInjection
	
	private void chkNull() {
		
		String id=jtfId.getText().trim();
		
				
		if("".equals(id)){//nullPointerException발생시키지 않는다.
			JOptionPane.showMessageDialog(this, "아이디 필수 입력");
			jtfId.requestFocus();
		}//end if
		
		
		
		String pass=new String(jpfPass.getPassword()).trim();
		
		if("".equals(pass)){//nullPointerException 발생시키지 않는다.
			JOptionPane.showMessageDialog(this, "비밀번호 필수 입력");
			jpfPass.requestFocus();
			return;
		}//end if
		
		
		LoginDAO lDAO=new LoginDAO();
		
		//입력값을 저장할 VO생성
		LoginVO lVO=new LoginVO();
//		lVO.setId(lDAO.blockInjection(id));
//		lVO.setPassword(lDAO.blockInjection(pass));
//		lVO.setId(id);
//		lVO.setPassword(pass);
		lVO.setId(lDAO.blockInjection(id));
		lVO.setPassword(lDAO.blockInjection(pass));
		try {
			
//			String name=lDAO.useStatement(lVO);//로그인 작업 수행
//			String name=lDAO.usePreparedStatement(lVO);//로그인 작업 수행
			String name=lDAO.useProcedure(lVO);
			
			jtfId.setText("");//아이디 초기화
			jpfPass.setText("");//비번 초기화
			jtfId.requestFocus();
			
			//조회 결과 이름이 없다면
			if("".equals(name)) {
				JOptionPane.showMessageDialog(this, "id 또는 비밀번호를 확인하세요");
				jlblOutput.setText("인증결과");
				return;
			}
			jlblOutput.setText(name+"님 안녕하세요.");
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
	}//chkNull;
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		chkNull();
	}//actionPerformed

	public static void main(String[] args) {
		new LoginFormInjection();
	}//main

}//clas
