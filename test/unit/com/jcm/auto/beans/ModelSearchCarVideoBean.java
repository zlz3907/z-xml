/*
 * Copyright (c) 1996, 2011, JCM and/or its affiliates. All rights reserved.
 */
package com.jcm.auto.beans;


/**
 * Describe class <code>ModelSearchCarVideoBean</code> here.
 *
 * @author zhaotengfei
 * @create 2014-3-12
 *
 */
public class ModelSearchCarVideoBean {

  /**
   * Get the <code>videoUri</code> value.
   *
   * @return the <code>videoUri</code> value of the <code>String</code>.
   */
  public String getVideoUri() {
    return videoUri;
  }

  /**
   * Set the <code>videoUri</code> value.
   *
   * @param videoUri the videoUri to set.
   */
  public void setVideoUri(String videoUri) {
    this.videoUri = videoUri;
  }

  /**
   * Get the <code>videoSurface</code> value.
   *
   * @return the <code>videoSurface</code> value of the <code>String</code>.
   */
  public String getVideoSurface() {
    return videoSurface;
  }

  /**
   * Set the <code>videoSurface</code> value.
   *
   * @param videoSurface the videoSurface to set.
   */
  public void setVideoSurface(String videoSurface) {
    this.videoSurface = videoSurface;
  }

  /**
   * Get the <code>title</code> value.
   *
   * @return the <code>title</code> value of the <code>String</code>.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Set the <code>title</code> value.
   *
   * @param title the title to set.
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Get the <code>docid</code> value.
   *
   * @return the <code>docid</code> value of the <code>String</code>.
   */
  public String getDocid() {
    return docid;
  }

  /**
   * Set the <code>docid</code> value.
   *
   * @param docid the docid to set.
   */
  public void setDocid(String docid) {
    this.docid = docid;
  }

  private String docid;
  //视频地址
  private String videoUri;
  //视频封面图
  private String videoSurface;
  //视频标题
  private String title;
  
  @Override
  public int hashCode(){
    return videoUri.hashCode();
  }
}
