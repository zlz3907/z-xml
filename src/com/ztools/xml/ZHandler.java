package com.ztools.xml;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class ZHandler extends AbsHandler {

  private static final long serialVersionUID = 1L;

  private boolean isDebug = false;

  private int currLineNum = 0;

  private StringBuilder characters = new StringBuilder();

  private String rootName;

  private List<Object> tempItemObject = new ArrayList<Object>();
  private String tempItemName = null;
  private Object currObject = null;
  private boolean isBaseType = false;

  private boolean isEndElement = false;

  private Object bean;

  private String defaultClassName = null;

  private String charset;

  private boolean isRoot = true;
  private int depth;

  private Map<String, Object> objMap = new HashMap<String, Object>();

  private Map<Integer, Boolean> isMapEntry = new HashMap<Integer, Boolean>();
  private Map<Integer, Object> currKey = new HashMap<Integer, Object>();
  private Map<Integer, Object> currValue = new HashMap<Integer, Object>();
  private String tempHashcode = null;

  public String getCharset() {
    return charset;
  }

  public void setCharset(String charset) {
    this.charset = charset;
  }

  public ZHandler() {
    super();
  }
  
  private void debug(String flag, String qName, Object attributes) {
	if (isDebug) {
        System.out.println(flag + ": " + currLineNum++ + "\t" + space[depth]
                           + qName + "\t" + depth + "\t" 
                           + (tempItemObject.size()>0 ? 
                        		   tempItemObject.get(tempItemObject.size()-1).getClass().getName()
                        		   :null)
                           + "\t" + currObject
                           + "\t" + attributes);
    }
  }

  @Override
  public void startDocument() throws SAXException {
    if (isDebug) {
      System.out.println("Line\tQName\tdepth\ttempItemName\ttempItemObject"
      		+ "\tcurrObject\tattributes");
    }
  }

  private Field[] getFields(Class<?> c) {
    Field[] ret = null;
    if (null != c) {
      Field[] temp;
      do {
        Field[] cfs = c.getDeclaredFields();
        if (null == ret) {
          ret = cfs;
        } else {
          temp = new Field[ret.length + cfs.length];
          System.arraycopy(ret, 0, temp, 0, ret.length);
          System.arraycopy(cfs, 0, temp, ret.length, cfs.length);
          ret = temp;
        }
        c = c.getSuperclass();
      } while (null != c);
    }
    return ret;
  }

  private Object encapsulateObject(Attributes attributes, Class<?> c) {
    if (null != c && null != attributes) {
      Field[] fs = getFields(c);
      if (null == fs) {
        return null;
      }
      try {

        if (Timestamp.class.equals(c)) {
          return new Timestamp(System.currentTimeMillis());
        }
        if (c.isEnum()) {
          // System.out.println("FIXME: Enum " + c);
          String name = attributes.getValue("name");
          return Enum.valueOf(c.asSubclass(Enum.class), name);
        }
        Object obj = c.newInstance();
        for (int i = 0; i < fs.length; i++) {
          String value = attributes.getValue(fs[i].getName());
          if (null != value && !"".equals(value)) {
            String methodName = getMethodName(fs[i].getName());
            if ("Boolean".equalsIgnoreCase(fs[i].getType().getSimpleName())) {
              if (0 <= fs[i].getName().toLowerCase().indexOf("is")) {
                methodName = getMethodName(fs[i].getName().substring(2));
              }
            }
            Method m = null;// c.getMethod(methodName, fs[i].getType());
            // TODO:
            do {
              m = c.getMethod(methodName, fs[i].getType());
              if (null != m) {
                break;
              }
              c = c.getSuperclass();
            } while (null != c);

            Object p = null;
            Class<?> type = fs[i].getType();
            p = parseValue(value, type);
            if (null != p) {
              m.invoke(obj, p);
            }
          }
        }
        return obj;
      } catch (InstantiationException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (SecurityException e) {
        e.printStackTrace();
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  @Override
  public void startElement(String uri, String localName, String qName,
      Attributes attributes) throws SAXException {
    try {
      characters.delete(0, characters.length());

      if (isRoot) {
        rootName = qName;
        isRoot = false;
        // iXmlProcess = XMLProcessFactory.getXmlProcess(rootName);
        defaultClassName = attributes.getValue("class");
      }
      // is root
      if (this.rootName.equals(qName)) {
        this.tempItemName = qName;
        depth++;
        return;
      }

      this.tempItemName = qName;
      tempHashcode = attributes.getValue("hashcode");
      try {
        String className = attributes.getValue("class");
        if (null == className || "".equals(className)) {
          // set default class name
          className = Object.class.getName();// this.bean.getClass().getName();
          // //
          // this.bean.getBeanClass().getName();
        }

        if (null == className) {
          className = defaultClassName;
        }
        Class<?> c = Class.forName(className);
        currObject = parseValue("0", c);

        // if ("true".equals(attributes.getValue("isMapEntry"))) {
        // isMapEntry = true;
        // }

        if (c.isArray()) {
          currObject = Array.newInstance(c.getComponentType(), 0);
        } else if (null == currObject) {
          isBaseType = false;
          currObject = encapsulateObject(attributes, c);
          String hashcode = attributes.getValue("hashcode");
          if (null != hashcode && !"".equals(hashcode)) {
            Object obj = objMap.get(hashcode);
            if (null != obj) {
              currObject = obj;
            } else {
              objMap.put(hashcode, currObject);
            }
          }
        } else {
          if ("0".equals(currObject)) {
            currObject = "";
          }
          if ("0".equals(tempHashcode)) {
            currObject = parseValue(null, c);
          } else {
            Object obj = objMap.get(tempHashcode);
            if (null != obj)
              currObject = obj;
          }
          isBaseType = true;
        }

      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }

      int tempItemIndex = depth - 1;
      if (tempItemIndex < this.tempItemObject.size()) {
        this.tempItemObject.set(tempItemIndex, currObject);
      } else {
        this.tempItemObject.add(tempItemIndex, currObject);
      }

      if ("true".equals(attributes.getValue("isMapEntry"))) {
        isMapEntry.put(tempItemIndex, true);
      }

      isEndElement = false;
      depth++;
    } finally {
      debug("Start", qName, attributes);
    };

  }

  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    // processCharacters(ch, start, length);
    characters.append(ch, start, length);
  }

  private void processCharacters(StringBuilder characters) {
    if (0 < characters.length() && isBaseType) {
      String s = characters.toString();// new String(ch).substring(start, start
                                       // + length);
      currObject = parseValue(s, currObject.getClass());
      if (null != tempHashcode && !"".equals(tempHashcode)) {
        objMap.put(tempHashcode, currObject);
      }
      int tempItemIndex = depth - 1 - 1;
      tempItemObject.set(tempItemIndex, currObject);
      // System.out.println("char: ------\n" + s);
    } else if (null != currObject && currObject instanceof Timestamp) {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
      try {
        String s = characters.toString();// new String(ch).substring(start,
                                         // start + length);
        currObject = new Timestamp(sdf.parse(s).getTime());
        int tempItemIndex = depth - 1 - 1;
        tempItemObject.set(tempItemIndex, currObject);
      } catch (ParseException e) {
        // currObject = new
        // Timestamp(Calendar.getInstance().getTimeInMillis());
        e.printStackTrace();
      }

    }
    debug("Character", null, characters);
  }

  private Object parseValue(String value, Class<?> type) {
    Object p = null;
    try {
      // value = iXmlProcess.read(value);
      if (type.equals(String.class)) {
        p = null != value ? value : "";
      } else if (type.equals(Double.class)
          || "double".equals(type.getSimpleName())) {
        p = null != value ? Double.parseDouble(value) : 0.00d;
      } else if (type.equals(Float.class)
          || "float".equals(type.getSimpleName())) {
        p = null != value ? Float.parseFloat(value) : 0.0f;
      } else if (type.equals(Long.class) || "long".equals(type.getSimpleName())) {
        p = null != value ? Long.parseLong(value) : 0l;
      } else if (type.equals(Integer.class)
          || "int".equals(type.getSimpleName())) {
        p = null != value ? Integer.parseInt(value) : 0;
      } else if (type.equals(Short.class)
          || "short".equals(type.getSimpleName())) {
        p = null != value ? Short.parseShort(value) : 0;
      } else if (type.equals(Boolean.class)
          || "boolean".equals(type.getSimpleName())) {
        p = null != value ? Boolean.parseBoolean(value) : false;
      } else if (type.equals(Date.class)) {
        p = DateFormat.getDateInstance(0, Locale.getDefault()).parse(value);
      } else if (type.equals(Character.class)
          || "char".equals(type.getSimpleName())) {
        if (null != value && value.length() > 0) {
          p = value.charAt(0);
        } else {
          p = new Character('0');
        }
      } else if (type.equals(Byte.class) || "byte".equals(type.getSimpleName())) {
        p = null != value ? Byte.parseByte(value) : 0;
      }
    } catch (ParseException e) {
      p = new Date();
    } catch (NumberFormatException e) {
      p = 0;
    }
    return p;
  }

  private String getMethodName(String fieldName) {
    String methodName = fieldName;
    methodName = "set".concat(methodName.substring(0, 1).toUpperCase()).concat(
        methodName.substring(1));
    return methodName;
  }

  String[] space = {"", " ", "  ", "   ", "    ", "     ", "      ", "       ", "        ", "          "};
  @Override
  public void endElement(String uri, String localName, String qName)
      throws SAXException {
    try {
      processCharacters(characters);
      if (rootName.equals(qName)) {
        return;
      }
      depth--;

      isBaseType = false;
      if (!qName.equals(tempItemName) && isEndElement) {
        // this.oldDepth--;
        tempItemName = qName;
      }

      int tempItemIndex = depth - 1;

      //    System.out.println( space[depth] + qName + " " +  this.tempItemObject.get(tempItemIndex).getClass().getName());
      if (this.tempItemObject.size() > tempItemIndex && tempItemIndex > 0) {
        Object parent = this.tempItemObject.get(tempItemIndex - 1);
        Object curr = this.tempItemObject.get(tempItemIndex);
        if (parent.getClass().isArray()) {
          int length = Array.getLength(parent);
          Object arr = Array.newInstance(parent.getClass().getComponentType(),
                                         length + 1);
          for (int i = 0; i < length; i++) {
            Array.set(arr, i, Array.get(parent, i));
          }
          Array.set(arr, length, curr);
          this.tempItemObject.set(tempItemIndex - 1, arr);
        } else if (parent instanceof Map<?, ?>) {
          Map<Object, Object> map = (Map<Object, Object>) parent;

          Boolean isME = isMapEntry.get(tempItemIndex);
          if (null != isME && isME) {
            isMapEntry.put(tempItemIndex, false);
            map.put(currKey.get(tempItemIndex + 1),
                    currValue.get(tempItemIndex + 1));
            currKey.put(tempItemIndex + 1, null);
            currValue.put(tempItemIndex + 1, null);
          } else {
            map.put(qName, curr);
          }

        } else if (parent instanceof Collection<?>) {
          Collection<Object> c = (Collection<Object>) parent;
          c.add(curr);
          this.tempItemObject.set(tempItemIndex - 1, c);
        } else if (null != parent) {
          // String methodName = getMethodName(qName);
          Boolean isME = isMapEntry.get(tempItemIndex);
          if (null != isME && isME
              && ("key".equals(qName) || "value".equals(qName))) {

          } else {
            Method m = null;
            try {
              Method[] ms = extractMethods(parent); // obj.getClass().getMethods();
              for (int i = 0; i < ms.length; i++) {
                if (ms[i].getName().toLowerCase()
                    .equals("set" + qName.toLowerCase())) {
                  m = ms[i];
                  break;
                }
              }
              // m = obj.getClass().getMethod(methodName,
              // curr.getClass());
              if (null != m) {
                m.invoke(parent, new Object[] { curr });
                this.tempItemObject.set(tempItemIndex - 1, parent);
              }
            } catch (SecurityException e) {
              e.printStackTrace();
              // } catch (NoSuchMethodException e) {
              // e.printStackTrace();
            } catch (IllegalArgumentException e) {
              System.out.println("error: " + m + " " + curr.getClass().getName());
              e.printStackTrace();
              debug("Error", qName, parent.getClass().getName());
              System.exit(1);
            } catch (IllegalAccessException e) {
              e.printStackTrace();
            } catch (InvocationTargetException e) {
              e.printStackTrace();
            }
          }
        }

        if ("key".equals(qName)) {
          currKey.put(tempItemIndex, curr);
        }
        if ("value".equals(qName)) {
          currValue.put(tempItemIndex, curr);
        }

        if ("entry".equalsIgnoreCase(qName)) {
          // currKey.put(tempItemIndex, null);
          // currValue.put(tempItemIndex, null);
        }

      }
      // isStartElement = false;
      // depth--;
    } finally {
      isEndElement = true;
      debug("End", qName, tempItemObject.get(depth - 1).getClass().getName());
      currObject = null;
    }
  }

  public Object getBean() {
    return bean;
  }

  public void setBean(final Object bean) {
    this.bean = bean;
  }

  @Override
  public void endDocument() throws SAXException {
    int tempItemIndex = depth - 1;
    Object obj = (this.tempItemObject.get(tempItemIndex));
    if (null != obj) {
      this.setBean(obj);
    }
    if (isDebug)
      System.out.println(this.tempItemObject);
  }

  @Override
  public Object getXmlObject() {
    return getBean();
  }

  private static Method[] extractMethods(Object obj) {
    Class<?> c = obj.getClass();
    Method[] ms = null;// = obj.getClass().getDeclaredMethods();
    Method[] temp;
    do {
      Method[] cms = c.getDeclaredMethods();
      if (null == ms) {
        ms = cms;
      } else {
        temp = new Method[ms.length + cms.length];
        System.arraycopy(ms, 0, temp, 0, ms.length);
        System.arraycopy(cms, 0, temp, ms.length, cms.length);
        ms = temp;
      }
      c = c.getSuperclass();
    } while (null != c);

    return ms;
  }
}
