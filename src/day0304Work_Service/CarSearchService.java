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
		// JFrame 디자인
		super("차량 정보");
		csDAO = new CarSearchDAO();

		JLabel jlbl = new JLabel("모델명 :");
		jbtnSearch = new JButton("검색");
		jbtnList = new JButton("차량목록");
		jtf = new JTextField();
		jta = new JTextArea();
		JScrollPane jsp = new JScrollPane(jta);

		setLayout(null);

		add(jlbl);
		add(jbtnSearch);
		add(jbtnList);
		add(jtf);
		add(jsp);

		// 이벤트 등록
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
				JOptionPane.showMessageDialog(null, "종료되었습니다.");
			}

		});

	}// Work_0304

	/**
	 * 차량 모델명 유효성 검증
	 */
	private void getJtfName() {

		String car_Name = jtf.getText().trim();
		StringBuilder outputData = new StringBuilder();

		if (car_Name.equals("")) {
			JOptionPane.showMessageDialog(this, "검색할 차량 모델을 입력해주세요.");
			return;
		} // end if

		// 유효성 검증이 되었다면 DB작업을 수행
		try {
			List<CarModelSelectVO> carList = csDAO.car_search(car_Name);


			if (carList.isEmpty()) {
				outputData.append("조회된 차량 모델이 없습니다.");
				return;
			} // end if

			outputData.append(car_Name).append("의 정보입니다.\n년식\t가격\t배기량\t옵션\t\n");

			for (CarModelSelectVO cmsVO : carList) {

				outputData
				.append(cmsVO.getCar_year()).append("\t")
				.append(cmsVO.getPrice()).append("\t")
				.append(cmsVO.getCc()).append("\t")
				.append(cmsVO.getCar_option()).append("\t\n");

			} // end for
//			jta.setText(outputData.toString());
//
//			// 입력을 편하게하기 위해서 초기화
//			jtf.setText("");

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(this, "차량 모델 검색 중 문제가 생겼습니다.");
			e.printStackTrace();

		} finally{
			jta.setText(outputData.toString());

			// 입력을 편하게하기 위해서 초기화
			jtf.setText("");
		}//end finally

	}// getJtfName

	
	private void searchAllCarMaker() {
		//모든 레코드를 조회한다.
		try {
			
			List<CarMakerListVO> carList=csDAO.carMaker();
			
			StringBuilder outputData=new StringBuilder();
			outputData.append("maker | ").append("model\n");
			
			//조회된 결과가 없을 때 보여줄 내용.
			if(carList.isEmpty()){
				outputData.append("조회된 정보가 없습니다.");
			}//end if
			
			
			for(CarMakerListVO cmlVO : carList) {
				//날짜처리
				
				outputData
				.append(cmlVO.getMaker()).append(" | ")
				.append(cmlVO.getModel()).append("\n");
			}//end for
			
			JOptionPane.showMessageDialog(null,outputData.toString());
			
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "차량 조회 중 문제 발생");
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
