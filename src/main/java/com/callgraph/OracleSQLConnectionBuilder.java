package com.callgraph;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class OracleSQLConnectionBuilder extends ConnectionBuilder {

    private final static Logger logger = Logger.getLogger(OracleSQLConnectionBuilder.class);

    private static final String CONN_URL = "jdbc:oracle:thin:@%s:1521/%s";
    private static final String EMPTY = "";

    public OracleSQLConnectionBuilder(){

        this        (   EMPTY   );

        if(logger.isDebugEnabled())
            logger.debug("Default constructor invoked");

    }

    public OracleSQLConnectionBuilder(final String driver){

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
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch(ClassNotFoundException ex){
            ex.printStackTrace();
        } catch(SQLException ex){
            ex.printStackTrace();
        }

        return conn;
    }
}
