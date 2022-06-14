package kr.co.sist.prepared.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import kr.co.sist.prepared.dao.TableInfoDAO;
import kr.co.sist.prepared.vo.ColumnVO;


public class TableInfoView extends JFrame implements ActionListener{
	
	private DefaultComboBoxModel<String> dcbmTable;
	private JComboBox<String> jcbTable;
	private JTextArea jtaOutput;
	
	
	public TableInfoView() {
		super("테이블 정보 조회");
		
		dcbmTable=new DefaultComboBoxModel<String>();
		jcbTable=new JComboBox<String>(dcbmTable);
		jtaOutput=new JTextArea();
		
		JPanel jpanel=new JPanel();
		jpanel.add(new JLabel("테이블명"));
		jpanel.add(jcbTable);
		
		add("North",jpanel);
		add("Center",jtaOutput);
		
		jcbTable.addActionListener(this);
		
		setBounds(100,100,500,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}//TableInfoView

	
	
	private void searchTableData() {
		TableInfoDAO tiDAO=TableInfoDAO.getInstace();
		try {
			List<String> tableList=tiDAO.selectAllTable();//테이블을 조회하여
			for(String tname:tableList) {
				dcbmTable.addElement(tname);//JComboBox에 채운다.
			}//end for
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//searchTableData
	
	
	private void searchTableColumn(String tname) {
		TableInfoDAO tiDAO=TableInfoDAO.getInstace();
		try {
			List<ColumnVO> list=tiDAO.selectTableColumn(tname);
			
			StringBuilder output=new StringBuilder();
			output.append("컬럼명\t데이터형\t크기\n");
			
			for(ColumnVO cVO : list) {
				output
				.append(cVO.getColumnName()).append("\t")
				.append(cVO.getDataType()).append("\t")
				.append(cVO.getDataLength()).append("\n");
			}//end for
			
			jtaOutput.setText(output.toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//searchTableColumn
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String tname=dcbmTable.getElementAt(jcbTable.getSelectedIndex());
		
		switch(JOptionPane.showConfirmDialog(this,tname+"테이블의 내용을 검색하시겠습니까?")){
		case JOptionPane.OK_OPTION :
			searchTableColumn(tname);
		}//end switch
	}
	
	
	public static void main(String[] args) {
		new TableInfoView().searchTableData();
	}//main

}//class
