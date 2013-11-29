package me.aipa.spam.bean;

/**
 * Created with IntelliJ IDEA. User: wenlie Date: 13-10-12 Time: 下午2:43 To
 * change this template use File | Settings | File Templates.
 */
public class ItemDO {
	// 自增ID
	private int id;

	// 商品标题
	private String title;

	// 商品ID
	private String itemId;

	// 抓取间隔时间
	private int intervalTime;

	// 价格正则表达式
	private String regprice;
	// 销量正则表达
	private String regsell;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public int getIntervalTime() {
		return intervalTime;
	}

	public void setIntervalTime(int intervalTime) {
		this.intervalTime = intervalTime;
	}

	public String getRegprice() {
		return regprice;
	}

	public void setRegprice(String regprice) {
		this.regprice = regprice;
	}

	public String getRegsell() {
		return regsell;
	}

	public void setRegsell(String regsell) {
		this.regsell = regsell;
	}

}
