package day0307Work_Service;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import day0307Work_DAO.TableInfoDAO;
import day0307Work_VO.SelectTableVO;
import kr.co.sist.prepared.dao.TableDAO;
import kr.co.sist.prepared.vo.CpEmp5VO;

public class TableInfo extends JFrame implements ActionListener {

	private JComboBox<String> jcb;
	private JTextArea jta;
	private TableInfoDAO tiDAO;
	
	
	public TableInfo() throws SQLException {
		super("테이블 정보");
		
		JLabel jlbl=new JLabel("테이블");
		jcb=new JComboBox<String>();
		jta=new JTextArea();
		JScrollPane jsp=new JScrollPane(jta);
		
		setLayout(null);
		
		add(jlbl);
		add(jcb);
		add(jsp);
		
		Font font=new Font("맑은고딕", Font.PLAIN, 15);
		jlbl.setFont(font);
		
		jlbl.setBounds(40,20,60,25);
		jcb.setBounds(110,20,200,25);
		jsp.setBounds(25,60,305,180);
		
		jcb.addActionListener(this);
		setJComboBox();
		
		setSize(370, 300);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}//TableInfo
	
	
	/**
	 * 콤보박스에 아이템 넣기
	 * @throws SQLException
	 */
	private void setJComboBox() throws SQLException {
		
		tiDAO= TableInfoDAO.getInstace();
		List<String> list = tiDAO.allTableName();
		
		for(int i=0;i<list.size();i++) {
			jcb.addItem(tiDAO.allTableName().get(i));
		}
	}//setJComboBox
	
	
	
	
	
	private void searchTableInfo(String tName) {
		
		try {
			//DB작업 
			List<SelectTableVO> list= tiDAO.tableInfo(tName);;
			
			StringBuilder output=new StringBuilder();
			
			output.append("컬럼명\t데이터형\t크기\n");
			
			SelectTableVO stVO=null;
			for(int i=0;i<list.size();i++) {
				stVO=list.get(i);
				output
				.append(stVO.getColumn_name()).append("\t")
				.append(stVO.getData_type()).append("\t")
				.append(stVO.getData_length()).append("\n");
			}//end for
			
			if(list.isEmpty()) {
				output.append("테이블의 정보를 조회할 수 없습니다.\n");
			}//end if
			//초기화된 데이터 입력
			jta.setText(output.toString());
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
	}//searchTableInfo
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		searchTableInfo(jcb.getSelectedItem().toString());
		
	}//actionPerformed

	
	
	public static void main(String[] args) {
		try {
			new TableInfo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//main

}//class
