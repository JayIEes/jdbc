package kr.co.sist.prepared.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.DbConnection;
import kr.co.sist.prepared.vo.CarVO;

public class SearchCarDAO {

	
	private static SearchCarDAO scDAO;

	public SearchCarDAO() {
		
	}
	
	
	public static SearchCarDAO getInstance() {
		
		if(scDAO==null) {
			scDAO=new SearchCarDAO();
		}//end if
		
		return scDAO;
	}//getInstance
	
	
	
	public List<CarVO> selectCarInfo(String carMaker) throws SQLException{
		
		List<CarVO> list=new ArrayList<CarVO>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConnection dc=DbConnection.getInstance();
		
		try {
			//1.
			//2.Ä¿³Ø¼Ç
			con=dc.getConn();
			
			//3.
			StringBuilder selectCarModel= new StringBuilder();
			
			selectCarModel
			.append("	select cc.country, cc.maker,cma.model,car_year,price,car_option")
			.append("	from car_country cc, car_maker cma, car_model cmo")
			.append("	where (cma.maker=cc.maker) and ")
			.append("	(cmo.model(+)=cma.model) and ")
			.append("	cc.maker='")
			.append(carMaker.toUpperCase())
			.append("'");
			
			pstmt=con.prepareStatement(selectCarModel.toString());
			
			//5.
			rs=pstmt.executeQuery();
			
			CarVO cVO=null;
			while(rs.next()) {
				
				cVO=new CarVO();
				cVO.setCarCountry(rs.getString("country"));
				cVO.setCarMaker(rs.getString("maker"));
				cVO.setCarModel(rs.getString("model"));
				cVO.setCarYear(rs.getString("car_year"));
				cVO.setPrice(rs.getInt("price"));
				cVO.setCarOption(rs.getString("car_option"));
				
				list.add(cVO);
			}//end while
		
		}finally {
			dc.close(rs, pstmt, con);
		}
		
		return list;
	}//selectCarInfo
	
	
	
}//class
