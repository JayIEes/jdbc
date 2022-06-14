package day0304Work_DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import day0304Work_VO.CarMakerListVO;
import day0304Work_VO.CarModelSelectVO;



public class CarSearchDAO {


	
	public List<CarModelSelectVO> car_search(String name) throws SQLException  {
		
		List<CarModelSelectVO> list=new ArrayList<CarModelSelectVO>();
		
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try {
		//1. ����̹� �ε�
		//2. Ŀ�ؼ� ���
		con=getDbConnection();
		
		//3. ������ ������ü ���
		stmt=con.createStatement();
		
		//4. ������ ���� �� ��� ���
		StringBuilder selectCarInfo=new StringBuilder();
		
		selectCarInfo
		.append("		select car_year,price,cc,car_option")
		.append("		from car_model")
		.append("		where model='")
		.append(name.toUpperCase())
		.append("'");
		
		rs=stmt.executeQuery(selectCarInfo.toString()); 
		
		CarModelSelectVO cmsVO=null;
		
		while(rs.next()) {
			cmsVO=new CarModelSelectVO();
			
			cmsVO.setCar_year(rs.getString("car_year"));
			cmsVO.setPrice(rs.getInt("price"));
			cmsVO.setCc(rs.getInt("cc"));
			cmsVO.setCar_option(rs.getString("car_option"));
			
			list.add(cmsVO);
			
		}//end while
		
		
		}finally {
		//5. ���� ����
			if(rs!=null) {rs.close();}//end if
			if(stmt!=null) {stmt.close();}//end if
			if(con!=null) {con.close();}//end if
		}//end finally
	
		return list;
	
	}//car_search
	
	public List<CarMakerListVO> carMaker() throws SQLException  {
		
		List<CarMakerListVO> list=new ArrayList<CarMakerListVO>();
		
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try {
		//1. ����̹� �ε�
		//2. Ŀ�ؼ� ���
		con=getDbConnection();
		
		//3. ������ ������ü ���
		stmt=con.createStatement();
		
		//4. ������ ���� �� ��� ���
		StringBuilder selectCarList=new StringBuilder();
		
		selectCarList
		.append("	select maker,model")
		.append("	from car_maker");
		
		rs=stmt.executeQuery(selectCarList.toString()); 
		
		CarMakerListVO cmlVO=null;
		
		while(rs.next()) {
			cmlVO=new CarMakerListVO();
			
			cmlVO.setMaker(rs.getString("maker"));
			cmlVO.setModel(rs.getString("model"));
			
			list.add(cmlVO);
		}//end while
		
		
		}finally {
		//5. ���� ����
			if(rs!=null) {rs.close();}//end if
			if(stmt!=null) {stmt.close();}//end if
			if(con!=null) {con.close();}//end if
		}//end finally
	
		return list;
	
	}//car_search
	
	
	
	
	

	private Connection getDbConnection() throws SQLException{
		
		Connection con=null;
		
		//1.����̹� �ε�
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		
		//2.Connection ���
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String id="scott";
		String pass="tiger";
		
		con=DriverManager.getConnection(url, id, pass);
		
		return con;
	}//getDbConnection
	
	
	
}//class
