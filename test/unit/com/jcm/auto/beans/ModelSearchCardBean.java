/*
 * Copyright (c) 1996, 2011, JCM and/or its affiliates. All rights reserved.
 */
package com.jcm.auto.beans;
import java.util.List;

/**
 * Describe class <code>CarSearchCardBean</code> here.
 *
 * @author zhaotengfei
 * @create 2014-3-12
 *
 */
public class ModelSearchCardBean {
  
  //车型ID
  private Integer  modelId;
  //车型名
  private String modelName;
  //最低价和最高价，prices[0]为最低价
  private Float[] prices;
  //生产方式
  private String productType;
  //类型级别
  private String level;
  //保养周期
  private String warrant;
  //排量
  private List<Float> displments;
  //变速箱
  private String[] gearboxs;
  //头图
  private String picture;
  
  //相关图片
  private List<ModelSearchCarImgBean> imgs;
  
  //相关视频
  private List<ModelSearchCarVideoBean> videos;
  /**
   * Get the <code>modelId</code> value.
   *
   * @return the <code>modelId</code> value of the <code>String</code>.
   */
  public Integer getModelId() {
    return modelId;
  }
  /**
   * Set the <code>modelId</code> value.
   *
   * @param modelId the modelId to set.
   */
  public void setModelId(Integer modelId) {
    this.modelId = modelId;
  }
  /**
   * Get the <code>modelName</code> value.
   *
   * @return the <code>modelName</code> value of the <code>String</code>.
   */
  public String getModelName() {
    return modelName;
  }
  /**
   * Set the <code>modelName</code> value.
   *
   * @param modelName the modelName to set.
   */
  public void setModelName(String modelName) {
    this.modelName = modelName;
  }
  /**
   * Get the <code>prices</code> value.
   *
   * @return the <code>prices</code> value of the <code>Float[]</code>.
   */
  public Float[] getPrices() {
    return prices;
  }
  /**
   * Set the <code>prices</code> value.
   *
   * @param prices the prices to set.
   */
  public void setPrices(Float[] prices) {
    this.prices = prices;
  }
  /**
   * Get the <code>productType</code> value.
   *
   * @return the <code>productType</code> value of the <code>String</code>.
   */
  public String getProductType() {
    return productType;
  }
  /**
   * Set the <code>productType</code> value.
   *
   * @param productType the productType to set.
   */
  public void setProductType(String productType) {
    this.productType = productType;
  }
  /**
   * Get the <code>level</code> value.
   *
   * @return the <code>level</code> value of the <code>String</code>.
   */
  public String getLevel() {
    return level;
  }
  /**
   * Set the <code>level</code> value.
   *
   * @param level the level to set.
   */
  public void setLevel(String level) {
    this.level = level;
  }
  /**
   * Get the <code>warrant</code> value.
   *
   * @return the <code>warrant</code> value of the <code>String</code>.
   */
  public String getWarrant() {
    return warrant;
  }
  /**
   * Set the <code>warrant</code> value.
   *
   * @param warrant the warrant to set.
   */
  public void setWarrant(String warrant) {
    this.warrant = warrant;
  }
  /**
   * Get the <code>displments</code> value.
   *
   * @return the <code>displments</code> value of the <code>Float[]</code>.
   */
  public List<Float> getDisplments() {
    return displments;
  }
  /**
   * Set the <code>displments</code> value.
   *
   * @param displments the displments to set.
   */
  public void setDisplments(List<Float> displments) {
    this.displments = displments;
  }
  
  /**
   * Get the <code>gearboxs</code> value.
   *
   * @return the <code>gearboxs</code> value of the <code>String[]</code>.
   */
  public String[] getGearboxs() {
    return gearboxs;
  }
  /**
   * Set the <code>gearboxs</code> value.
   *
   * @param gearboxs the gearboxs to set.
   */
  public void setGearboxs(String[] gearboxs) {
    this.gearboxs = gearboxs;
  }
  /**
   * Get the <code>picture</code> value.
   *
   * @return the <code>picture</code> value of the <code>String</code>.
   */
  public String getPicture() {
    return picture;
  }
  /**
   * Set the <code>picture</code> value.
   *
   * @param picture the picture to set.
   */
  public void setPicture(String picture) {
    this.picture = picture;
  }
  /**
   * Get the <code>imgs</code> value.
   *
   * @return the <code>imgs</code> value of the <code>List<ModelSearchCarImgBean></code>.
   */
  public List<ModelSearchCarImgBean> getImgs() {
    return imgs;
  }
  /**
   * Set the <code>imgs</code> value.
   *
   * @param imgs the imgs to set.
   */
  public void setImgs(List<ModelSearchCarImgBean> imgs) {
    this.imgs = imgs;
  }
  /**
   * Get the <code>videos</code> value.
   *
   * @return the <code>videos</code> value of the <code>List<ModelSearchCarVideoBean></code>.
   */
  public List<ModelSearchCarVideoBean> getVideos() {
    return videos;
  }
  /**
   * Set the <code>videos</code> value.
   *
   * @param videos the videos to set.
   */
  public void setVideos(List<ModelSearchCarVideoBean> videos) {
    this.videos = videos;
  }
  @Override
  public int hashCode(){
    return modelId.hashCode();
  }
  
}
