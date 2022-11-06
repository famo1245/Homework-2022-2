package hw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {
    private static final String DB_TABLE_NAME = "movie";
    private static final String FILE_NAME = "movie_data.txt";

    Connection connection = null;
    ResultSet rs = null;
    Statement stmt = null;

    public MovieDAO() {
        try {
            //Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //Open a connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb?useUnicode=" +
                    "true&useJDBCCompliantTimezoneShift=true&use" +
                    "LegacyDatetimeCode=false&serverTimezone=UTC", "root", "famo1245");

            //Execute a query
            stmt = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean insertData() {
        boolean flag = true;
        try {
            String fmtCreate = "CREATE TABLE IF NOT EXISTS %s (id char(3)," +
                    " title varchar (100)," +
                    " company varchar (50)," +
                    " releasedate date," +
                    " country varchar (10)," +
                    " totalscreen int," +
                    " profit numeric (15,2)," +
                    " totalnum int," +
                    " grade varchar (50)," +
                    "primary key (id))";
            String queryCreate = String.format(fmtCreate, DB_TABLE_NAME);

            stmt.executeUpdate(queryCreate);

            if(flag = tableIsEmpty()) {//check if table is empty
                // and insert data
                BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
                String line;
                while ((line = reader.readLine()) != null) {
                    ArrayList<String> values = new ArrayList<>(List.of(line.split("\\|")));

                    String fmt = "INSERT INTO %s VALUES('%s', '%s', '%s', '%s', '%s', %s, %s, %s, '%s')";
                    String query = String.format(fmt, DB_TABLE_NAME, values.get(1), values.get(2), values.get(3),
                            values.get(4), values.get(5), values.get(6), values.get(7), values.get(8), values.get(9));

                    stmt.executeUpdate(query);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public ArrayList<MovieInfo> findAll() {
        ArrayList<MovieInfo> mi = new ArrayList<>();
        try {
            String fmt = "SELECT * FROM %s ORDER BY id*1";
            String query = String.format(fmt, DB_TABLE_NAME);

            rs = stmt.executeQuery(query);
            getMovieInfo(mi);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mi;
    }

    public ArrayList<MovieInfo> findByTitle(String title) {
        ArrayList<MovieInfo> mi = new ArrayList<>();
        // search tuples contains title
        try {
            String fmt = "SELECT * FROM %s WHERE title like '%%%s%%'";
            String query = String.format(fmt, DB_TABLE_NAME, title);

            rs = stmt.executeQuery(query);
            getMovieInfo(mi);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mi;
    }

    public ArrayList<MovieInfo> findByTotalNum(int totalNum) {
        ArrayList<MovieInfo> mi = new ArrayList<>();
        // search tuples greater than totalNum
        try {
            String fmt = "SELECT * FROM %s WHERE totalnum > %d";
            String query = String.format(fmt, DB_TABLE_NAME, totalNum);

            rs = stmt.executeQuery(query);
            getMovieInfo(mi);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mi;
    }

    public ArrayList<MovieInfo> findByReleaseDate(String date1, String date2) {
        ArrayList<MovieInfo> mi = new ArrayList<>();
        //search tuples between date1 and date2
        try {
            String fmt = "SELECT * FROM %s WHERE releasedate BETWEEN '%s' AND '%s'";
            String query = String.format(fmt, DB_TABLE_NAME, date1, date2);

            rs = stmt.executeQuery(query);
            getMovieInfo(mi);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mi;
    }

    private void getMovieInfo(ArrayList<MovieInfo> mi) {
        try {
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
    }

    private boolean tableIsEmpty() {
        //check table is empty
        boolean flag = false;
        try {
            String fmt = "SELECT COUNT(*) FROM %s";
            String query = String.format(fmt, DB_TABLE_NAME);

            rs = stmt.executeQuery(query);
            rs.next();
            if(rs.getInt(1) == 0)
                flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public void terminate() {
        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
