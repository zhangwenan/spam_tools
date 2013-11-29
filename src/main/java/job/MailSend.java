package job;

import me.aipa.spam.service.MailService;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 邮件发送自动执行类 实现Job类
 * 
 * @author ptt
 * 
 */
public class MailSend implements Job {

	@Autowired
	private MailService mailService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		JobDataMap map = context.getMergedJobDataMap();
		// 获取需要调用的service
		MailService service = (MailService) map.get("service");
		try {
			service.sendMails();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
