package job;

import me.aipa.spam.service.ItemInfoService;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 商品自动抓取类 实现Job类
 * 
 * @author lil
 * 
 */
public class GetItemJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		JobDataMap map = context.getMergedJobDataMap();
		// 获取需要调用的service
		ItemInfoService service = (ItemInfoService) map.get("service");
		String itemId = (String) map.get("itemId");
		String regprice = (String) map.get("regprice");
		String regsell = (String) map.get("regsell");

		service.handleInfo(itemId, regprice, regsell);

	}

}
