package com.callgraph;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Test;
import org.junit.AfterClass;

/**
 * Unit test for MySQLConnectionBuilderTest Class.
 */
public class MySQLConnectionBuilderTest
{

    @Test
    public void testConnection(){

        Connection conn = new MySQLConnectionBuilder("com.mysql.jdbc.Driver")
                        .setHost("localhost")
                        .setDBName("Ericsson")
                        .setUsername("root")
                        .setPassword("welcome")
                        .build();

        assertNotNull(conn);

        try {
            conn.close();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

}
