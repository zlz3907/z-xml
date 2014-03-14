/*
 * Copyright (c) 1996, 2011, JCM and/or its affiliates. All rights reserved.
 */
package com.jcm.auto.beans;


/**
 * Describe class <code>ModelSearchCarImgBean</code> here.
 *
 * @author zhaotengfei
 * @create 2014-3-12
 *
 */
public class ModelSearchCarImgBean {

  //图片资源地址
  private String imgUri;
  //图片ID
  private String imgId;
  //图片类型
  private String type;
  //图片来源网页
  private String srcUrl;
  /**
   * Get the <code>imgUri</code> value.
   *
   * @return the <code>imgUri</code> value of the <code>String</code>.
   */
  public String getImgUri() {
    return imgUri;
  }

  /**
   * Set the <code>imgUri</code> value.
   *
   * @param imgUri the imgUri to set.
   */
  public void setImgUri(String imgUri) {
    this.imgUri = imgUri;
  }

  /**
   * Get the <code>imgId</code> value.
   *
   * @return the <code>imgId</code> value of the <code>String</code>.
   */
  public String getImgId() {
    return imgId;
  }

  /**
   * Set the <code>imgId</code> value.
   *
   * @param imgId the imgId to set.
   */
  public void setImgId(String imgId) {
    this.imgId = imgId;
  }

  /**
   * Get the <code>type</code> value.
   *
   * @return the <code>type</code> value of the <code>String</code>.
   */
  public String getType() {
    return type;
  }

  /**
   * Set the <code>type</code> value.
   *
   * @param type the type to set.
   */
  public void setType(String type) {
    this.type = type;
  }

  
  /**
   * Get the <code>srcUrl</code> value.
   *
   * @return the <code>srcUrl</code> value of the <code>String</code>.
   */
  public String getSrcUrl() {
    return srcUrl;
  }

  /**
   * Set the <code>srcUrl</code> value.
   *
   * @param srcUrl the srcUrl to set.
   */
  public void setSrcUrl(String srcUrl) {
    this.srcUrl = srcUrl;
  }

  @Override
  public int hashCode(){
    return (type+imgId).hashCode();
  }
}
