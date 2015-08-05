package com.callgraph;

import java.util.UUID;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println("├──");
        System.out.println( "Hello World!" );
    }

    public String generateUniqueKey(){
        String id = UUID.randomUUID().toString();
        return id;
    }
}
