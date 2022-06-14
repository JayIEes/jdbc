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
		
		String carMaker=JOptionPane.showInputDialog(null,"차량 제조사");
		List<CarVO> list= scDAO.selectCarInfo(carMaker);
		
		StringBuilder output=new StringBuilder();
		
		output.append("제조국\t제조사\t모델명\t연식\t가격\t옵션\n");
		
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
			output.append("조회된 결과가 없습니다.");
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
			JOptionPane.showMessageDialog(null, "문제가 생겼습니다.");
		}
	}//main

}//class
