package com.callgraph;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class MySQLConnectionBuilder extends ConnectionBuilder {

    private final static Logger logger = Logger.getLogger(MySQLConnectionBuilder.class);

    private static final String CONN_URL = "jdbc:mysql://%s:3306/%s";
    private static final String EMPTY = "";

    public MySQLConnectionBuilder(){

        this(   EMPTY   );
        if(logger.isDebugEnabled())
            logger.debug("Default constructor invoked");

    }

    public MySQLConnectionBuilder(final String driver){

        if(logger.isDebugEnabled())
            logger.debug("Overloaded constructor invoked");
        setDriver   (   driver  );
        setDBName   (   EMPTY   );
        setHost     (   EMPTY   );
        setUsername (   EMPTY   );
        setUsername (   EMPTY   );

    }

    public Connection build(){

        final String url = String.format(CONN_URL, host, dbname);

        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
        } catch(ClassNotFoundException ex){
            ex.printStackTrace();
        } catch(SQLException ex){
            ex.printStackTrace();
        }

        return conn;
    }
}
