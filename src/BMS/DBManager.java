package BMS;

	import java.beans.Statement;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;

	public class DBManager { //�����ͺ��̽� ���� DBManager Ŭ���� ����
		
		static Connection getConnection() {
			Connection conn = null;
			Statement stmt;
			try { //Database ���� �ڵ�
				String url = "jdbc:mysql://localhost:3306/bms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
				conn = DriverManager.getConnection(url, "root", "tj102938");
				
			} catch (Exception e) { //���� ���н� �����޼��� ���
				System.out.println("DB ���� ����");
				e.printStackTrace();
			}
			return conn;
		}//Connection END
	}//end of DBManager

