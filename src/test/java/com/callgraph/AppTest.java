package com.callgraph;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    @Test
    public void testLength(){
        App obj = new App();
        Assert.assertEquals(36, obj.generateUniqueKey().length());
    }

}
