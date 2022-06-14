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
		super("���̺� ����");
		
		JLabel jlbl=new JLabel("���̺�");
		jcb=new JComboBox<String>();
		jta=new JTextArea();
		JScrollPane jsp=new JScrollPane(jta);
		
		setLayout(null);
		
		add(jlbl);
		add(jcb);
		add(jsp);
		
		Font font=new Font("�������", Font.PLAIN, 15);
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
	 * �޺��ڽ��� ������ �ֱ�
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
			//DB�۾� 
			List<SelectTableVO> list= tiDAO.tableInfo(tName);;
			
			StringBuilder output=new StringBuilder();
			
			output.append("�÷���\t��������\tũ��\n");
			
			SelectTableVO stVO=null;
			for(int i=0;i<list.size();i++) {
				stVO=list.get(i);
				output
				.append(stVO.getColumn_name()).append("\t")
				.append(stVO.getData_type()).append("\t")
				.append(stVO.getData_length()).append("\n");
			}//end for
			
			if(list.isEmpty()) {
				output.append("���̺��� ������ ��ȸ�� �� �����ϴ�.\n");
			}//end if
			//�ʱ�ȭ�� ������ �Է�
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
