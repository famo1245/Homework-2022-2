import java.sql.*;

public class MySQLJDBC {

	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{

			//Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/mydb?useUnicode=true&useJDBCCompliantTimezoneShift" +
							"=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");

			//Execute a query
			stmt = conn.createStatement();

			// Insert value
//			stmt.executeUpdate("delete from instructor where name = 'Kim' and dept_name = 'Physics'");

//			rs = stmt.executeQuery("SELECT * FROM instructor");
//
//			//Print results
//            while(rs.next()) {
//            	System.out.println(rs.getString("dept_name") +
//						"|" + rs.getString(2) + "|" +
//						rs.getString(3) + "|" + rs.getDouble("salary"));
////				System.out.println(rs.getString(1) +
////						"|" + rs.getString(2) + "|" +
////						rs.getDouble(3));	//columnIndex는 1부터 시작함
//            }

			// 실습 #1
			rs = stmt.executeQuery("select name from instructor" +
					" where dept_name = 'Comp. Sci.' and salary > 70000");

			while(rs.next()) {
				System.out.println(rs.getString("name"));
			}

			rs = stmt.executeQuery("select name, course_id from instructor, teaches" +
					" where instructor.ID = teaches.ID and instructor.dept_name = 'Biology'");

			while(rs.next()) {
				System.out.println(rs.getString("name") + "|" +
						rs.getString("course_id"));
			}

			// 실습 #2
			stmt.executeUpdate("update instructor set salary = salary * 1.05");

			rs = stmt.executeQuery("SELECT * FROM instructor");

			//Print results
            while(rs.next()) {
            	System.out.println(rs.getString("dept_name") +
						"|" + rs.getString(2) + "|" +
						rs.getString(3) + "|" + rs.getDouble("salary"));
//				System.out.println(rs.getString(1) +
//						"|" + rs.getString(2) + "|" +
//						rs.getDouble(3));	//columnIndex는 1부터 시작함
            }

            // close a connection
            stmt.close();
            conn.close();

		}catch (SQLException ex) {
			//Handle errors for JDBC
			ex.printStackTrace();
		} catch (Exception e){
		    //Handle errors for Class.forName
			e.printStackTrace();
		}
	}
}