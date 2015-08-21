package com.callgraph;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ConnectionBuilder {

    protected String driver;

    protected String host;

    protected String username;
    protected String password;

    protected String dbname;

    public ConnectionBuilder(){}

    public ConnectionBuilder setDriver(String driver){
        this.driver = driver;
        return this;
    }

    public ConnectionBuilder setHost(String host){
        this.host = host;
        return this;
    }

    public ConnectionBuilder setUsername(String username){
        this.username = username;
        return this;
    }

    public ConnectionBuilder setPassword(String password){
        this.password = password;
        return this;
    }

    public ConnectionBuilder setDBName(String dbname){
        this.dbname = dbname;
        return this;
    }

    public abstract Connection build();
}
