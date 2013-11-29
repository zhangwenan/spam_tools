package me.aipa.spam.service;

import java.util.Date;
import java.util.List;

import me.aipa.spam.bean.InfoDO;
import me.aipa.spam.bean.ItemDO;

/**
 * Created with IntelliJ IDEA. User: wenlie Date: 13-11-5 Time: 下午4:56 To change
 * this template use File | Settings | File Templates.
 */
public interface ItemInfoService {
		
		/**
		 * 抓取并添加商品信息
		 * @param infoDO
		 */
		void handleInfo(String itemId,String regprice, String regsell);
		
		/**
		 * 删除商品信息
		 * @param itemId
		 * @param timeStart
		 * @param timeEnd
		 */
		void deleteInfo(String itemId,Date timeStart,Date timeEnd);
		
		/**
		 * 商品信息列表
		 * @param page
		 * @param limit
		 * @param itemId
		 * @param timeStart
		 * @param timeEnd
		 * @param title
		 * @return
		 */
		List<InfoDO> getInfoList(int page,int limit,String itemId,Date timeStart,Date timeEnd,String title);
		
		/**
		 * 商品信息列表count
		 * @param page
		 * @param limit
		 * @param itemId
		 * @param timeStart
		 * @param timeEnd
		 * @param title
		 * @return
		 */
		Integer infoCount(int page,int limit,String itemId,Date timeStart,Date timeEnd,String title);
    
	
		/**
		 * 抓取商品信息任务 开始
		 * 
		 * @param itemId
		 */
		public void getItemInfoStart(String itemId);
	
		/**
		 * 结束抓取商品信息
		 */
		void shutDown(String itemId);

	
	/**
	 * 删除商品任务信息
	 * 
	 * @param itemId
	 * @param timeStart
	 * @param timeEnd
	 */
	ItemDO selectDOByItemId(String itemId);


	/**
	 * 测试
	 */
	boolean getItemTask(String itemId, String regprice, String regsell);

	/**
	 * 添加商品任务信息
	 * 
	 * @param itemDO
	 */
	void saveItemTask(ItemDO itemDO);

	/**
	 * 删除商品任务信息
	 * 
	 * @param itemId
	 * @param timeStart
	 * @param timeEnd
	 */
	boolean deleteItemTask(String itemId);

	/**
	 * 查询商品任务信息
	 * 
	 * @param itemId
	 * @param page
	 * @param limit
	 * @param title
	 * @return
	 */
	List<ItemDO> getItemTaskList(int page, int limit, String itemId, String title);

	/**
	 * 获取商品任务信息数量
	 * 
	 * @param itemId
	 * @param page
	 * @param limit
	 * @param title
	 * @return
	 */
	int countItemTask(int page, int limit, String itemId, String title);

	/**
	 * 更改商品任务信息
	 * 
	 * @param itemId
	 * @param page
	 * @param limit
	 * @param title
	 * @return
	 */
	boolean updateItemTask(ItemDO itemDO);

}
