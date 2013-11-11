package com.example;

import org.junit.Test;

public class TestHello {

  @Test
  public void testSayHello() {
    System.out.println("Hello world!");
    Object[] objs = new Object[] { new boolean[] { true },
        new byte[] { (byte) 1 }, new char[] { 'a' }, new short[] { 1 },
        new int[] { 2 }, new long[] { 3 }, new double[] { 4 },
        new String[] { "z" } };
    for (int i = 0; i < objs.length; i++) {
      System.out.println("class: " + objs[i].getClass().getName() );
    }
  }

}
