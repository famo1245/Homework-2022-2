package hw;

import java.sql.*;
import java.util.ArrayList;

public class DAO {
    private final String DB_TABLE_NAME = "movie";
    private final String FILE_NAME = "movie_data.txt";
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public DAO(){
        try {
            //Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/movieInfo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "famo1245");

            //Execute a query
            stmt = conn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertData() {

    }

    public ArrayList<MovieInfo> findByTitle(String title) {
        ArrayList<MovieInfo> mi = new ArrayList<>();

        try {
            String fmt = "SELECT * FROM %s WHERE title like '%%s%'";
            String query = String.format(fmt, DB_TABLE_NAME, title);
            rs = stmt.executeQuery(query);

            while(rs.next()) {
                mi.add(new MovieInfo(rs.getString("id"), rs.getString("title"),
                        rs.getString("company"), rs.getDate("releasedate"),
                        rs.getString("country"), rs.getInt("totalscreen"),
                        rs.getDouble("profit"), rs.getInt("totalnum"),
                        rs.getString("grade")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mi;
    }
    /*
    id           char(3),
    title        varchar (100),
    company      varchar (50),
    releasedate  date,
    country      varchar (10),
    totalscreen  int,
    profit       numeric (15,2),
    totalnum     int,
    grade        varchar (50),
     */

    public ArrayList<MovieInfo> findByTotalNum(int totalNum) {
        ArrayList<MovieInfo> mi = new ArrayList<>();

        try {
            String fmt = "SELECT * FROM %s WHERE totalnum > %d";
            String query = String.format(fmt, DB_TABLE_NAME, totalNum);
            rs = stmt.executeQuery(query);

            while(rs.next()) {
                mi.add(new MovieInfo(rs.getString("id"), rs.getString("title"),
                        rs.getString("company"), rs.getDate("releasedate"),
                        rs.getString("country"), rs.getInt("totalscreen"),
                        rs.getDouble("profit"), rs.getInt("totalnum"),
                        rs.getString("grade")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mi;
    }

    public ArrayList<MovieInfo> findByReleaseDate(Date date1, Date date2) {
        ArrayList<MovieInfo> mi = new ArrayList<>();

        try {
            String fmt = "SELECT * FROM %s WHERE releasedate BETWEEN '%s' AND '%s'";
            String query = String.format(fmt, DB_TABLE_NAME, date1.toString(), date2.toString());
            rs = stmt.executeQuery(query);

            while(rs.next()) {
                mi.add(new MovieInfo(rs.getString("id"), rs.getString("title"),
                        rs.getString("company"), rs.getDate("releasedate"),
                        rs.getString("country"), rs.getInt("totalscreen"),
                        rs.getDouble("profit"), rs.getInt("totalnum"),
                        rs.getString("grade")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mi;
    }

    public void terminate() {
        try {
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
