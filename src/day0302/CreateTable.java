package day0302;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author user
 * Statement.execute();�� ����� �������� ����
 */
public class CreateTable {

		public CreateTable() throws SQLException {
			//1.����̹� �ε�
			try {
				Class.forName("oracle.jdbc.OracleDriver");//classpath �Ǵ� build path�� �ʿ�
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}//end catch
			
			Connection con=null;
			Statement stmt=null;
			
			try {
			
			//2.Connection ���
			String url="jdbc:oracle:thin:@localhost:1521:orcl";
			String id="scott";
			String pass="tiger";
			con=DriverManager.getConnection(url, id, pass);
			
			//3.������ ���� ��ü ���
			stmt=con.createStatement();
			
			//4.���� ���� �� ��� ���
//			String createQuery
//			="create table test(num number, name varchar2(30),input_date date default sysdate)";
			String createQuery="drop table test";
			stmt.execute(createQuery); //������������ ���� �� ������ true, ���� �� ������ false�� ����
			//������ ������ �������̶�� ��ȸ�� ����� ���� false�� ����, ������ ������ �����ϸ� ���ܰ� ���ϵȴ�.
//			if(flag) {
//				System.out.println(flag);
				System.out.println("���̺� ���� ���� : "+stmt.getQueryTimeout());
//			}
//			
			}finally {
			//5.�������
				if(stmt!=null) {stmt.close();}//end if
				if(con!=null) {con.close();}//end if
			}//end finally
		}
	
	public static void main(String[] args) {
		try {
			new CreateTable();
		} catch (SQLException se) {
			System.err.println("���̺� ���� ����");
			se.printStackTrace();
		}
	}

}