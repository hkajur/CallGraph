package com.callgraph;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import java.util.List;

/**
 * Unit test for ModuleRecord Class.
 */
public class ModuleRecordTest
{
    @Ignore
    @Test
    public void testNumberRows(){

        List<ModuleRecord> list = ModuleRecord.all();
        Assert.assertEquals(1, list.size());

    }

    @BeforeClass
    public static void testCreateRow(){

        final int id = 244;
        final String modName = "Admin/xbfTest.C";
        final String functionName = "waitForMod";
        final String commandName = "ls";
        final int lineNo = 3300;
        final String dbType = "Yoda";


        ModuleRecord module = new ModuleRecord()
            .setId(id)
            .setModule(modName)
            .setFunction(functionName)
            .setCommand(commandName)
            .setLine(lineNo);

        boolean isSave = module.save();

        Assert.assertEquals(true, isSave);

    }

    @Test
    public void testDeleteInvalidRow(){

        final int id = 20204000;
        boolean deletedRow = ModuleRecord.delete(id);
        Assert.assertEquals(false, deletedRow);

    }

    @AfterClass
    public static void tearDown(){

        boolean deletingRow = ModuleRecord.delete(244);

        Assert.assertEquals(true, deletingRow);
    }


}
