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
		//1. 드라이버 로딩
		//2. 커넥션 얻기
		con=getDbConnection();
		
		//3. 쿼리문 생성객체 얻기
		stmt=con.createStatement();
		
		//4. 쿼리문 수행 후 결과 얻기
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
		//5. 연결 끊기
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
		//1. 드라이버 로딩
		//2. 커넥션 얻기
		con=getDbConnection();
		
		//3. 쿼리문 생성객체 얻기
		stmt=con.createStatement();
		
		//4. 쿼리문 수행 후 결과 얻기
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
		//5. 연결 끊기
			if(rs!=null) {rs.close();}//end if
			if(stmt!=null) {stmt.close();}//end if
			if(con!=null) {con.close();}//end if
		}//end finally
	
		return list;
	
	}//car_search
	
	
	
	
	

	private Connection getDbConnection() throws SQLException{
		
		Connection con=null;
		
		//1.드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		
		//2.Connection 얻기
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String id="scott";
		String pass="tiger";
		
		con=DriverManager.getConnection(url, id, pass);
		
		return con;
	}//getDbConnection
	
	
	
}//class
