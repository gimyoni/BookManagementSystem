package BMS;

	import java.sql.Connection; //MySQL 연결
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.Statement;
	import java.util.Properties;
	 
	public class DBLogin { //DBLogin 클래스 생성
		
	    String id = null;
	    String pw = null;
	    //Database 연결 코드
	    Statement stmt = null;
	    ResultSet rs = null;
	    String url = "jdbc:mysql://localhost:3306/bms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	    String sql = null;
	    Properties info = null;
	    Connection cnn = null;
	 
	    int checkIDPW(String id, String pw) { //아이디와 비밀번호값을 전달받아 로그인 
	        this.id = id;
	        this.pw = pw;
	        int result = 1;
	 
	        try { 
	            Class.forName("org.gjt.mm.mysql.Driver"); 
	            info = new Properties();
	            info.setProperty("user", "root");
	            info.setProperty("password", "tj102938");
	            cnn = DriverManager.getConnection(url, info); // 연결할 정보를 가지고있는 드라이버매니저를 던진다
	            stmt = cnn.createStatement();
	 
	            sql = "select * from users where id=''" + id + "'"; //user에서 값을 가져옴
	            rs = stmt.executeQuery(sql); 
	 
	            if (rs.next() == false || (id.isEmpty()) == true) { // id가 존재하지 않으면
	                result = 1; //false값 대입
	            } else {
	                sql = "select * from (select * from users where id=''" + id + "')"; //user에서 값을 가져옴
	                rs = stmt.executeQuery(sql);
	                while (rs.next() == true) {         // 다음값의
	                    if (rs.getString(2).equals(pw)) { // 비밀번호와 같은지 비교
	                        result = 0;         // 비밀번호가 일치하면로그인 성공 (true 값 대입)
	                    } else {                // 아이디는 같고 비밀번호가 다른경우
	                        result = 1; //false값 대입
	                    }
	                }
	            }
	        } catch (Exception ee) { //실행오류시
	            System.out.println("문제있음");
	            ee.printStackTrace();
	        }
	        return result;
	    }//end of checkIDPW
	
}//end of DBLogIn