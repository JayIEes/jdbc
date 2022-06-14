package day0304Work_Service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import day0304Work_DAO.CarSearchDAO;
import day0304Work_VO.CarMakerListVO;
import day0304Work_VO.CarModelSelectVO;
import kr.co.sist.statement.vo.CpEmp4SelectAllVO;

public class CarSearchService extends JFrame implements ActionListener {

	private JTextArea jta;
	private JTextField jtf;
	private JButton jbtnSearch;
	private JButton jbtnList;

	private CarSearchDAO csDAO;
	

	public CarSearchService() {
		// JFrame ������
		super("���� ����");
		csDAO = new CarSearchDAO();

		JLabel jlbl = new JLabel("�𵨸� :");
		jbtnSearch = new JButton("�˻�");
		jbtnList = new JButton("�������");
		jtf = new JTextField();
		jta = new JTextArea();
		JScrollPane jsp = new JScrollPane(jta);

		setLayout(null);

		add(jlbl);
		add(jbtnSearch);
		add(jbtnList);
		add(jtf);
		add(jsp);

		// �̺�Ʈ ���
		jtf.addActionListener(this);
		jbtnSearch.addActionListener(this);
		jbtnList.addActionListener(this);

		jlbl.setBounds(45, 30, 50, 20);
		jtf.setBounds(100, 30, 150, 20);
		jbtnSearch.setBounds(260, 30, 80, 20);
		jbtnList.setBounds(350, 30, 100, 20);
		jsp.setBounds(45, 60, 405, 230);

		setSize(510, 350);

		setVisible(true);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				dispose();
				JOptionPane.showMessageDialog(null, "����Ǿ����ϴ�.");
			}

		});

	}// Work_0304

	/**
	 * ���� �𵨸� ��ȿ�� ����
	 */
	private void getJtfName() {

		String car_Name = jtf.getText().trim();
		StringBuilder outputData = new StringBuilder();

		if (car_Name.equals("")) {
			JOptionPane.showMessageDialog(this, "�˻��� ���� ���� �Է����ּ���.");
			return;
		} // end if

		// ��ȿ�� ������ �Ǿ��ٸ� DB�۾��� ����
		try {
			List<CarModelSelectVO> carList = csDAO.car_search(car_Name);


			if (carList.isEmpty()) {
				outputData.append("��ȸ�� ���� ���� �����ϴ�.");
				return;
			} // end if

			outputData.append(car_Name).append("�� �����Դϴ�.\n���\t����\t��ⷮ\t�ɼ�\t\n");

			for (CarModelSelectVO cmsVO : carList) {

				outputData
				.append(cmsVO.getCar_year()).append("\t")
				.append(cmsVO.getPrice()).append("\t")
				.append(cmsVO.getCc()).append("\t")
				.append(cmsVO.getCar_option()).append("\t\n");

			} // end for
//			jta.setText(outputData.toString());
//
//			// �Է��� ���ϰ��ϱ� ���ؼ� �ʱ�ȭ
//			jtf.setText("");

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(this, "���� �� �˻� �� ������ ������ϴ�.");
			e.printStackTrace();

		} finally{
			jta.setText(outputData.toString());

			// �Է��� ���ϰ��ϱ� ���ؼ� �ʱ�ȭ
			jtf.setText("");
		}//end finally

	}// getJtfName

	
	private void searchAllCarMaker() {
		//��� ���ڵ带 ��ȸ�Ѵ�.
		try {
			
			List<CarMakerListVO> carList=csDAO.carMaker();
			
			StringBuilder outputData=new StringBuilder();
			outputData.append("maker | ").append("model\n");
			
			//��ȸ�� ����� ���� �� ������ ����.
			if(carList.isEmpty()){
				outputData.append("��ȸ�� ������ �����ϴ�.");
			}//end if
			
			
			for(CarMakerListVO cmlVO : carList) {
				//��¥ó��
				
				outputData
				.append(cmlVO.getMaker()).append(" | ")
				.append(cmlVO.getModel()).append("\n");
			}//end for
			
			JOptionPane.showMessageDialog(null,outputData.toString());
			
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "���� ��ȸ �� ���� �߻�");
		}//end catch
		
		
	}//
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == jbtnSearch || e.getSource() ==jtf) {
			getJtfName();
		} // end if

		if (e.getSource() == jbtnList) {
			searchAllCarMaker();
		} // end if

	}// actionPerformed

	public static void main(String[] args) {

		new CarSearchService();
	}// main

}// class
