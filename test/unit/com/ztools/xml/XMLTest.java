package com.ztools.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.lang.reflect.Array;

import org.junit.Assert;
import org.junit.Test;

import com.ztools.xml.XMLReader;
import com.ztools.xml.XMLWriter;
import com.ztools.xml.ZHandler;

import com.ztools.xml.bean.*;

public final class XMLTest {

  public static Object creatTestObject() {
    Person p = new Person("Name" + 3, 3 + "", "f");

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("aKey", "zlz");
    Map<String, String> subMap = new HashMap<String, String>();
    subMap.put("sub1", "v1");
    subMap.put("sub2", "v2");
    map.put("bKey", subMap);

    Object[] arr = {1, "2", 1, map};
    p.setArr(arr);

    List<String> c = new ArrayList<String>();
    c.add("&#1232;&amp;and;");
    c.add("xxx");
    p.setChildren(c);
    List<Integer> t = new ArrayList<Integer>();
    t.add(1);
    t.add(333);
    List<Integer> tt = new ArrayList<Integer>();
    tt.add(11);
    tt.add(333);
    p.setThr(tt);

    List<Object> objList = new ArrayList<Object>();
    objList.add(1); // int
    objList.add(1123132123123L); // long
    objList.add(1.00001D); // double
    objList.add(9.99f); // float
    objList.add((short) 5); // short
    objList.add((byte) 120); // byte
    objList.add(true);

    objList.add('c'); // char
    objList.add("hello<>&';:\"<![CDATA[abc]]> xml");
    objList.add(new Person("aaa", "12", "f"));
    objList.add(p);
    p.setObjlist(objList);

    List<House> houses = new ArrayList<House>();
    House h = new House("hourse1", "beijing", 100);
    List<Room> rooms = new ArrayList<Room>();
    rooms.add(new Room(1));
    rooms.add(new Room(2));
    rooms.add(new Room(3));
    h.setRooms(rooms);
    houses.add(h);
    houses.add(new House("hourse2", "wuhan", 200));
    houses.add(new House("hourse3", "wuhan", 120));

    p.setHouses(houses);
    // List<Person> list = new ArrayList<Person>();
    // list.add(p);
    return p;
  }

  @Test
  public void testReadAndWriteXmlFile() {
    try {
      File file = new File("person.xml");
      //      System.out.println("file: " + file.getAbsolutePath());
      Object objA = creatTestObject();
      XMLWriter.writeObjectToXmlFile(objA, file.getAbsolutePath());
      //      System.out.println("write file: " + file.getAbsolutePath());
      Object objB = 
        XMLReader.xmlStreamToObject(new FileInputStream(file), 
                                    new ZHandler());

      String strA = XMLWriter.objectToXmlString(objA);
      String strB = XMLWriter.objectToXmlString(objB);
      System.out.println("testReadAndWriteXmlFile is \t"
                         + strA.replaceAll(" hashcode=\"-?\\d+\"", "")
                         .equals(strB.replaceAll(" hashcode=\"-?\\d+\"", "")));
      assert(objA.equals(objB));
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testObjectToXmlString() {

    Object objA = creatTestObject();
    String xml1 = XMLWriter.objectToXmlString(objA);
    Assert.assertNotNull(xml1);
    // System.out.println(xml1);
    // System.out.println(xml1.replaceAll(" hashcode=\"-?\\d+\"", ""));
    // System.out.println("--------------------");
    Object objB = XMLReader.xmlStringToObject(xml1);
    String xml2 = XMLWriter.objectToXmlString(objB);
    // System.out.println(xml2 + "\n-----------------");
    // System.out.println(xml2.replaceAll(" hashcode=\"-?\\d+\"", ""));
    Assert.assertEquals(xml1.replaceAll(" hashcode=\"-?\\d+\"", ""),
                        xml2.replaceAll(" hashcode=\"-?\\d+\"", ""));
    System.out.println("testObjectToXmlString is \t" 
                       + xml1.replaceAll(" hashcode=\"-?\\d+\"", "")
                       .equals(xml2.replaceAll(" hashcode=\"-?\\d+\"", "")));
  }

  @Test
  public void testNullValueInMap() {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("keyone", "hello");
    map.put("keytwo", new byte[]{1,2,3});
    map.put(null, "three");
    map.put("keyFour", null);

    String xmlStr = XMLWriter.objectToXmlString(map);
    // System.out.println(xmlStr);
    Object obj = XMLReader.xmlStringToObject(xmlStr);
    String xmlStr2 = XMLWriter.objectToXmlString(obj);
    // System.out.println();
    // System.out.println(xmlStr2);
    boolean flag = true;
    if (obj instanceof Map<?, ?>) {
      Map<?, ?> m = (Map<?, ?>) obj;
      
      for (Object key : map.keySet()) {
        assert(m.containsKey(key));
        assert(m.containsValue(map.get(key)));
        if (!m.containsKey(key) || !m.containsValue(map.get(key))) {
          flag = false;
          Object v1 = map.get(key);
          Object v2 = m.get(key);
          if (null != v1 && null != v2 && v1.getClass().isArray()
              && v2.getClass().isArray()) {
            int length1 = Array.getLength(v1);
            int length2 = Array.getLength(v2);
            Object[] arr1 = new Object[length1];
            Object[] arr2 = new Object[length2];
            for (int i = 0; i < length1; i++) {
              arr1[i] = Array.get(v1, i);
            }

            for (int j = 0; j < length2; j++) {
              arr2[j] = Array.get(v2, j);
            }
            if (Arrays.equals(arr1, arr2)) {
              flag = true;
            }
          }
          if (!flag)
          System.out.println("no match " + key + " a=" + m.get(key) 
                             + " b=" + map.get(key));
          break;
        }
        
      }

      //      Assert.assertEquals(m., map);
    }
    System.out.println("testNullValueInMap is \t" + flag);

  }

  @Test
  public void testMapInMap() {
    Map<String, String> map = new HashMap<String, String>();
    map.put("keyone", "hello");
    map.put("keytwo", null);
    map.put(null, "three");
    
    Map<String, Object> tmap = new HashMap<String, Object>();
    tmap.put("key1", "abcabcabc\nabc");
    tmap.put("tsub", map);

    Map<String, Object> pMap = new HashMap<String, Object>();
    pMap.put("subMap", tmap);
    pMap.put("one", 1);
    pMap.put("integer", new Integer(129));
    pMap.put("string", "string");

    
    Object testObj = pMap;
    String xmlStr = XMLWriter.objectToXmlString(testObj);
    //System.out.println(xmlStr);
    Object obj = XMLReader.xmlStringToObject(xmlStr);
    String xmlStr2 = XMLWriter.objectToXmlString(obj);
    //System.out.println();
    System.out.println(xmlStr2);
    System.out.println("testMapInMap is \t" + obj.equals(testObj));
    Assert.assertEquals(obj, testObj);
  }
  
  @Test
  public void testReadXmlFile() {
    try {
      Object obj = 
        XMLReader.xmlStreamToObject(new FileInputStream("person.xml"), new ZHandler());
      // System.out.println(obj);
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }
  
}
