package com.callgraph;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;

public abstract class DBUtils {

    protected String table;

    protected String [] visible;
    protected String [] fillable;

    protected Map<String, Object> values;

    protected static Connection conn;

    // Cache the Prepared Statements to avoid unnecessary overhead in creating them
    protected static Map<String, PreparedStatement> cachedStatements = new HashMap<String, PreparedStatement>();

    protected static final boolean debug = false;

    protected DBUtils(){
        conn = getInstance();
        values = new HashMap<String, Object>();
    }

    public static Connection getInstance(){

        if(conn == null){

            conn = new OracleSQLConnectionBuilder()
                .setDriver      (   "oracle.jdbc.driver.OracleDriver"   )
                .setHost        (           "localhost"                 )
                .setDBName      (             "orcl"                    )
                .setUsername    (              "hr"                     )
                .setPassword    (            "oracle"                   )
                .build();

            if(debug)
                System.out.println("Connection Established");

        }

        return conn;
    }

    public String getColumns(){

        int size = this.visible.length;

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < size; i++){
            if(i == (size - 1))
                sb.append(this.visible[i]);
            else
                sb.append(this.visible[i]).append(",");
        }

        return sb.toString();
    }

    public String getQuery(){

        return String.format("SELECT %s FROM %s",
                this.getColumns(),
                this.table);

    }

    public static void handleException(Exception ex){

        if(debug)
            ex.printStackTrace();
        else
            System.err.println(ex);

    }

    public void print(){

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            pstmt = conn.prepareStatement(getQuery());
            rs = pstmt.executeQuery();

            while(rs.next()){

                for(String str : visible){
                    System.out.printf("%s\t", rs.getString(str));
                }

                System.out.println();
            }

        } catch(Exception ex){
            handleException(ex);
        } finally {
            try { if(rs     != null){     rs.close();  } } catch(Exception ex){ handleException(ex); }
            try { if(pstmt  != null){  pstmt.close();  } } catch(Exception ex){ handleException(ex); }
        }
    }

    public boolean save(){

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            StringBuilder columnNames  = new StringBuilder();
            StringBuilder columnValues = new StringBuilder();

            for(int index = 0; index < fillable.length; index++){
                if(index == (fillable.length - 1)){
                    columnNames.append(fillable[index]);
                    columnValues.append("?");
                } else {
                    columnNames.append(fillable[index]).append(",");
                    columnValues.append("?,");
                }
            }

            String insertQuery = String.format("INSERT INTO %s (%s) VALUES (%s)",
                                    table,
                                    columnNames.toString(),
                                    columnValues.toString());

            pstmt = conn.prepareStatement(insertQuery);

            for(int index = 0; index < fillable.length; index++){

                Object obj = values.get(fillable[index]);

                if(obj instanceof String){
                    pstmt.setString(index + 1, obj.toString());
                } else if(obj instanceof Integer){
                    pstmt.setInt(index + 1, Integer.parseInt(obj.toString()));
                }
            }

            int rows = pstmt.executeUpdate();

            if(rows != 0)
                return true;

        } catch(Exception ex){
            handleException(ex);
        } finally {
            try { if(rs     != null){     rs.close();  } } catch(Exception ex){ handleException(ex); }
            try { if(pstmt  != null){  pstmt.close();  } } catch(Exception ex){ handleException(ex); }
        }

        return false;

    }

    public static void close() {

        try {

            if(conn != null){
                if(debug)
                    System.out.println("Connection Closed");
                conn.close();
                conn = null;
            }

        } catch(Exception ex){
            handleException(ex);
        }
    }

}
