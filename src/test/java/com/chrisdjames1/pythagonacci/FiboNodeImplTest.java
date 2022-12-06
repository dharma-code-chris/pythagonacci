package com.chrisdjames1.pythagonacci;

import org.junit.Test;

public class FiboNodeImplTest {

    @Test
    public void testDepth1() {
        String fiboNodeString = new FiboNodeImpl(1).toStringRecursive();
        System.out.println(fiboNodeString);
    }

    @Test
    public void testDepth2() {
        String fiboNodeString = new FiboNodeImpl(2).toStringRecursive();
        System.out.println(fiboNodeString);
    }

    @Test
    public void testDepth3() {
        String fiboNodeString = new FiboNodeImpl(3).toStringRecursive();
        System.out.println(fiboNodeString);
    }

    @Test
    public void testDepth4() {
        String fiboNodeString = new FiboNodeImpl(4).toStringRecursive();
        System.out.println(fiboNodeString);
    }

    @Test
    public void testDepthDefault() {
        String fiboNodeString = new FiboNodeImpl().toStringRecursive();
        System.out.println(fiboNodeString);
    }

}