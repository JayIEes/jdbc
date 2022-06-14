package day0308;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import kr.co.sist.prepared.dao.SearchCarDAO;
import kr.co.sist.prepared.vo.CarVO;

public class SearchCarService {

	private JTextArea jta;
	
	public SearchCarService() throws SQLException {
		
		jta=new JTextArea();
		
		SearchCarDAO scDAO=SearchCarDAO.getInstance();
		
		String carMaker=JOptionPane.showInputDialog(null,"���� ������");
		List<CarVO> list= scDAO.selectCarInfo(carMaker);
		
		StringBuilder output=new StringBuilder();
		
		output.append("������\t������\t�𵨸�\t����\t����\t�ɼ�\n");
		
		for(CarVO cVO:list) {
			output
			.append(cVO.getCarCountry()).append("\t")
			.append(cVO.getCarMaker()).append("\t")
			.append(cVO.getCarModel()).append("\t")
			.append(cVO.getCarYear()).append("\t")
			.append(cVO.getPrice()).append("\t")
			.append(cVO.getCarOption()).append("\n");
		}//end for
		
		if(list.isEmpty()) {
			output.append("��ȸ�� ����� �����ϴ�.");
			jta.setText(output.toString());
			JOptionPane.showMessageDialog(null, jta);
			return;
		}
		
		
		jta.setText(output.toString());
		JOptionPane.showMessageDialog(null, jta);
	}//SearchCarService

	
	public static void main(String[] args) {
		try {
			new SearchCarService();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "������ ������ϴ�.");
		}
	}//main

}//class
