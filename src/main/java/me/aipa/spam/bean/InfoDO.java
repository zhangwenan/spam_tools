package me.aipa.spam.bean;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-10-12
 * Time: 下午2:43
 * To change this template use File | Settings | File Templates.
 */
public class InfoDO extends BaseDO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4904422464641657611L;
	
	private String itemId;							//	商品id
	private String sellNum;								//	商品销量
	private Double price;								//	商品价格
	private Date getTime;							//	抓取时间
	
	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getSellNum() {
		return sellNum;
	}
	public void setSellnum(String sellNum) {
		this.sellNum = sellNum;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getGetTime() {
		return getTime;
	}
	public void setGetTime(Date getTime) {
		this.getTime = getTime;
	}
	


}
