package com.callgraph;

import java.util.UUID;
import java.sql.*;

/**
 * Hello world!
 *
 */
public class App
{

    private static final String DRIVER      = "com.mysql.jdbc.Driver";
    private static final String URL         = "jdbc:mysql://localhost:3306/InventoryManagement";
    private static final String USERNAME    = "root";
    private static final String PASSWORD    = "welcome";

    private static final String USERS_QUERY = "SELECT * FROM users";

    private static final boolean debug = false;

    public static void handleEx(Exception ex){
        if(debug)
            ex.printStackTrace();
        else
            System.err.println(ex);
    }

    public static void main( String[] args )
    {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

        Class.forName(DRIVER).newInstance();

    //  System.out.println("├──");
    //  System.out.println( "Hello World!" );

        conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        pstmt = conn.prepareStatement(USERS_QUERY);
        rs = pstmt.executeQuery();

        while(rs.next()){

            System.out.printf(
                    "%-8s\t%-25s\t%-12s\t%-12s\t%3d\n",
                    rs.getString( "username"  ),
                    rs.getString(  "email"    ),
                    rs.getString( "firstname" ),
                    rs.getString( "lastname"  ),
                    rs.getInt   (    "id"     )
            );
        }

        } catch(Exception ex){
            handleEx(ex);
        } finally {
            try {  if(rs    != null){ rs   .close();    rs = null; } } catch(Exception ex){  handleEx(ex);  }
            try {  if(pstmt != null){ pstmt.close(); pstmt = null; } } catch(Exception ex){  handleEx(ex);  }
            try {  if(conn  != null){ conn .close();  conn = null; } } catch(Exception ex){  handleEx(ex);  }
        }

    }

    public String generateUniqueKey(){
        String id = UUID.randomUUID().toString();
        return id;
    }
}
