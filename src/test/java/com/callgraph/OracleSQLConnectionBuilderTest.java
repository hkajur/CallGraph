package com.callgraph;

import java.io.IOException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Test;
import org.junit.AfterClass;

/**
 * Unit test for OracleSQLConnectionBuilderTest Class.
 */
public class OracleSQLConnectionBuilderTest
{

    @Test
    public void testConnection(){

        Connection conn = new OracleSQLConnectionBuilder()
                .setDriver      (   "oracle.jdbc.driver.OracleDriver"   )
                .setHost        (           "localhost"                 )
                .setDBName      (             "orcl"                    )
                .setUsername    (              "hr"                     )
                .setPassword    (            "oracle"                   )
                .build();

        assertNotNull(conn);

        try {
            conn.close();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

}
