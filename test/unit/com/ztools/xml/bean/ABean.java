package com.ztools.xml.bean;

import java.lang.reflect.Method;

/**
 * @author Zhong Lizhi<mailto:zlz.3907@gmail.com>
 * 
 */
public class ABean {
  
  
  private ObjectId id = new ObjectId();

  /**
   * Get the <code>id</code> value.
   * 
   * @return the <code>id</code> value of the <code>ObjectId</code>.
   */
  public final ObjectId getId() {
    return id;
  }

  /**
   * Set the <code>id</code> value.
   * 
   * @param id
   *          the id to set.
   */
  public final void setId(ObjectId id) {
    this.id = id;
  }
  
  private String name;

  /**
   * Get the <code>name</code> value.
   * 
   * @return the <code>name</code> value of the <code>String</code>.
   */
  public final String getName() {
    return name;
  }

  /**
   * Set the <code>name</code> value.
   * 
   * @param name
   *          the name to set.
   */
  public final void setName(String name) {
    this.name = name;
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof ABean) {
      ABean other = (ABean) obj;
      Method[] ms = obj.getClass().getMethods();
      if (null != ms) {
        for (int i = 0; i < ms.length; i++) {
          String fieldName = ms[i].getName();
          if (!"getClass".equals(fieldName) && !"get".equals(fieldName)
              && !"is".equals(fieldName)
              && 0 == ms[i].getParameterTypes().length
              && (fieldName.startsWith("get") || fieldName.startsWith("is"))) {
            try {
              if (!equalsField(ms[i].invoke(this), ms[i].invoke(other))) {
                return false;
              }
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        }
      }
    }
    return true;
  }

  private boolean equalsField(Object a, Object b) {
    if (null != a) {
      if (ObjectId.class.equals(a.getClass())) {
        return null != b && ObjectId.class.equals(b.getClass());
      }
      return a.equals(b);
    }
    return null == b;
  }

}
