package EXPENSE_TRACKER;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
       private static final String driver ="com.mysql.cj.jdbc.Driver";
       private static final String url ="jdbc:mysql://localhost:3306/expense_db2";
       private static final String user = "root";
       private static final String pass ="Junimar123";

        // ✅ Make connection static and shared
       private static  Connection conn = null;

       public static Connection getConnection(){
           try {
               // Wala pang koneksyon, kaya gagawa tayo ng bago
               if(conn == null){
                   Class.forName(driver);
                   conn = DriverManager.getConnection(url,user,pass);
                 //  System.out.println("Database Connected!");
               }

           } catch (Exception e) {
               e.printStackTrace();
           }

           return conn;
       }


}
