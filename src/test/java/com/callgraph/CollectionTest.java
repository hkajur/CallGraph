package com.callgraph;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.AfterClass;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ArrayList;

public class CollectionTest {


    @Test
    public void testAddToList(){

        Map<Integer, String> list = new HashMap<Integer, String>();

        list.put(0, "Zero");
        list.put(2, "Harish");
        list.put(2, "Har");
        list.put(2, "Harish");

        assertEquals(2, list.size());
        assertEquals("Harish", list.get(2));
        assertEquals(2, list.size());
    }

    @Test
    public void testTreeSet(){

        Set<Integer> tree = new TreeSet<Integer>();

        List<Integer> expected = new ArrayList<Integer>();

        expected.add(1);
        expected.add(30);
        expected.add(39);
        expected.add(100);

        tree.add(new Integer(   39  ));
        tree.add(new Integer(  100  ));
        tree.add(new Integer(  100  ));
        tree.add(new Integer(  100  ));
        tree.add(new Integer(  100  ));
        tree.add(new Integer(    1  ));
        tree.add(new Integer(  100  ));
        tree.add(new Integer(   30  ));

        List<Integer> result = new ArrayList<Integer>();
        result.addAll(tree);

        System.out.println("Testing Tree Set");
        System.out.println(tree);

        assertEquals(expected, result);
    }

    @Test
    public void testHashSet(){

        Set<Integer> tree = new HashSet<Integer>();

        tree.add(new Integer(   39  ));
        tree.add(new Integer(  100  ));
        tree.add(new Integer(  100  ));
        tree.add(new Integer(  100  ));
        tree.add(new Integer(  100  ));
        tree.add(new Integer(    1  ));
        tree.add(new Integer(  100  ));
        tree.add(new Integer(   30  ));

        System.out.println("Testing Hash Set");
        System.out.println(tree);
    }

    @Test
    public void testLinkedHashSet(){

        Set<Integer> tree = new LinkedHashSet<Integer>();

        tree.add(new Integer(   39  ));
        tree.add(new Integer(  100  ));
        tree.add(new Integer(  100  ));
        tree.add(new Integer(  100  ));
        tree.add(new Integer(  100  ));
        tree.add(new Integer(    1  ));
        tree.add(new Integer(  100  ));
        tree.add(new Integer(   30  ));

        System.out.println("Testing Linked Hash Set");
        System.out.println(tree);
    }
}
