package BMS;

	import java.beans.Statement;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;

	public class DBManager { //데이터베이스 연결 DBManager 클래스 생성
		
		static Connection getConnection() {
			Connection conn = null;
			Statement stmt;
			try { //Database 연결 코드
				String url = "jdbc:mysql://localhost:3306/bms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
				conn = DriverManager.getConnection(url, "root", "tj102938");
				
			} catch (Exception e) { //연결 실패시 오류메세지 출력
				System.out.println("DB 연결 실패");
				e.printStackTrace();
			}
			return conn;
		}//Connection END
	}//end of DBManager

