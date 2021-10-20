package SCD2_2021_Exam.facade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 

public class Cache {

        public Cache(){
            this.createNewDatabase();
        }
        //[1]
        private void createNewDatabase() {
    
            String url = "jdbc:sqlite:test.db";
            String sql = "CREATE TABLE IF NOT EXISTS holidays (\n"
                + "	date text NOT NULL,\n"
                + "	name text NOT NULL,\n"
                + "	name_local text,\n"
                + "	language text,\n"
                + "	description text,\n"
                + "	country text,\n"
                + "	location text,\n"
                + "	type text,\n"
                + "	year text,\n"
                + "	month text,\n"
                + "	day text,\n"
                + "	week_day text,\n"
                + "	number text\n"
                + ");";
    
            try (Connection conn = DriverManager.getConnection(url)) {
                if (conn != null) {
                    DatabaseMetaData meta = conn.getMetaData();
                    Statement stmt = conn.createStatement();
                    stmt.execute(sql);
                }
    
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        //[1]
        private Connection connect() {
            // SQLite connection string
            String url = "jdbc:sqlite:test.db";
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return conn;
        }

        /**
        * Insert all the given data into the database cache <br><br>
        */
        //[1]
        public void insert(String name, String name_local, String language,String description,String country, String location,String type, String date, String date_year, String date_month, String date_day,String week_day,String number) {
            
            String sql = "INSERT INTO holidays(date,name,name_local,language,description,country,location,type,year,month,day,week_day,number) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try (Connection conn = this.connect();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, date);
                pstmt.setString(2, name);
                pstmt.setString(3, name_local);
                pstmt.setString(4, language);
                pstmt.setString(5, description);
                pstmt.setString(6, country);
                pstmt.setString(7, location);
                pstmt.setString(8, type);
                pstmt.setString(9, date_year);
                pstmt.setString(10, date_month);
                pstmt.setString(11, date_day);
                pstmt.setString(12, week_day);
                pstmt.setString(13, number);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        /**
        * Checks and returns if the date exists in the database or not
        * @param date Check whether there is data for the given date
        * @return if the date is there in the database or not
        */
        //[1]
        public boolean dateExists(String date){  
            String sql = "SELECT * FROM holidays WHERE date = ? " ;  
              
            try {  
                Connection conn = this.connect();  
                PreparedStatement pstmt  = conn.prepareStatement(sql);
                pstmt.setString(1,date);
                ResultSet rs  = pstmt.executeQuery();
                // loop through the result set  
                while (rs.next()) { 
                    rs.close();
                    return true; 
                }  
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
            return false;
        }  

        /**
        * Delets the record from the database
        * @param date date of the record to delete
        */
        public void delete(String date) {
            String sql = "DELETE FROM holidays WHERE date = ?";
    
            try (Connection conn = this.connect();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
                // set the corresponding param
                pstmt.setString(1, date);
                // execute the delete statement
                pstmt.executeUpdate();
    
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        /**
        * Returns the name of the holiday associated with the given date
        * @param date The date of which data to retrieve
        * @return the name of the holiday
        */
        //[1]
        public String dateHoliday(String date){  
            String sql = "SELECT * FROM holidays WHERE date = ? " ;  
              
            try {  
                Connection conn = this.connect();  
                PreparedStatement pstmt  = conn.prepareStatement(sql);
                pstmt.setString(1,date);
                ResultSet rs  = pstmt.executeQuery();
                // loop through the result set  
                while (rs.next()) { 
                    String name = rs.getString("name"); 
                    rs.close();
                    return name; 
                }  
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
            return null;
        }  

        /**
        * Returns the number of holidays associated with the given date
        * @param date The date of which data to retrieve
        * @return the number of holidays on that date
        */
        //[1]
        public String dateNumber(String date){  
            String sql = "SELECT * FROM holidays WHERE date = ?" ;  
              
            try {  
                Connection conn = this.connect();  
                PreparedStatement pstmt  = conn.prepareStatement(sql);
                pstmt.setString(1,date);
                ResultSet rs  = pstmt.executeQuery();
                // loop through the result set  
                while (rs.next()) { 
                    String number = rs.getString("number"); 
                    rs.close();
                    return number; 
                }  
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
            return null;
        } 
    
}