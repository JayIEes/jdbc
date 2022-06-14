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
		super("�α��� ����");
		
		jtfId=new JTextField();
		jpfPass=new JPasswordField();
		jlblOutput=new JLabel("�������");
		
		jtfId.setBorder(new TitledBorder("���̵�"));
		jpfPass.setBorder(new TitledBorder("��й�ȣ"));
		jlblOutput.setBorder(new TitledBorder("�������"));
		
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
		
				
		if("".equals(id)){//nullPointerException�߻���Ű�� �ʴ´�.
			JOptionPane.showMessageDialog(this, "���̵� �ʼ� �Է�");
			jtfId.requestFocus();
		}//end if
		
		
		
		String pass=new String(jpfPass.getPassword()).trim();
		
		if("".equals(pass)){//nullPointerException �߻���Ű�� �ʴ´�.
			JOptionPane.showMessageDialog(this, "��й�ȣ �ʼ� �Է�");
			jpfPass.requestFocus();
			return;
		}//end if
		
		
		LoginDAO lDAO=new LoginDAO();
		
		//�Է°��� ������ VO����
		LoginVO lVO=new LoginVO();
//		lVO.setId(lDAO.blockInjection(id));
//		lVO.setPassword(lDAO.blockInjection(pass));
//		lVO.setId(id);
//		lVO.setPassword(pass);
		lVO.setId(lDAO.blockInjection(id));
		lVO.setPassword(lDAO.blockInjection(pass));
		try {
			
//			String name=lDAO.useStatement(lVO);//�α��� �۾� ����
//			String name=lDAO.usePreparedStatement(lVO);//�α��� �۾� ����
			String name=lDAO.useProcedure(lVO);
			
			jtfId.setText("");//���̵� �ʱ�ȭ
			jpfPass.setText("");//��� �ʱ�ȭ
			jtfId.requestFocus();
			
			//��ȸ ��� �̸��� ���ٸ�
			if("".equals(name)) {
				JOptionPane.showMessageDialog(this, "id �Ǵ� ��й�ȣ�� Ȯ���ϼ���");
				jlblOutput.setText("�������");
				return;
			}
			jlblOutput.setText(name+"�� �ȳ��ϼ���.");
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