package com.zhangwenan.app1.bean;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-10-12
 * Time: 下午2:43
 * To change this template use File | Settings | File Templates.
 */
public class Item {

    private int id;


    // 商品标题
    private String itemTitle;


    // 商品价格
    private Float originalPrice;


    // 促销价
    private Float promotionPrice;


    // 售出件数
    private int soldCount;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public Float getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Float originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Float getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(Float promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public int getSoldCount() {
        return soldCount;
    }

    public void setSoldCount(int soldCount) {
        this.soldCount = soldCount;
    }
}
