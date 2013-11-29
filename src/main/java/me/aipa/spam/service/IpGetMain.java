package me.aipa.spam.service;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.MimeMultipart;

import me.aipa.spam.Util;
import me.aipa.spam.bean.IpGetDO;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

public class IpGetMain {

	/**
	 * @param args
	 * @throws SchedulerException 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void main(String[] args) throws SchedulerException, ClientProtocolException, IOException {
		
		/*********************http请求获取页面信息**********************/
//		Header[] headers = new BasicHeader[1];
//		Header header = new BasicHeader("referer","http://weixin.qq.com/?version=620757816&uin=1141426960");///http://detail.tmall.com/item.htm
//		headers[0] = header;
		
//		DefaultHttpClient client = new DefaultHttpClient();
//		HttpPost post = new HttpPost("http://wx.qlogo.cn/mmhead/Q3auHgzwzM5hEK1B3ibYaj9p9hiaxszot8E4NUZoJrlPbwnhjFEDGtfQ/96"); //http://mdskip.taobao.com/core/initItemDetail.htm?itemId=17250184058
//		post.setHeaders(headers); 
//		
//		 HttpResponse response = client.execute(post);         
//		 HttpEntity  entity = response.getEntity(); 
//		 String html=EntityUtils.toString(entity);
//		 System.out.println(html);

//			String resultStr = "";
//
//			Pattern pt = Pattern.compile("sellCount\":(.*)},\"spe");
//			Matcher matcher = pt.matcher(html);
//			while (matcher.find()) {
//				String str = matcher.group(1); // 只取第一组
//				if (!Util.isEmpty(str)) {
//					resultStr = str;
//				}
//			}
//			System.out.println(resultStr);
		
		/****************************自动任务xml配置启动******************************/        
//        ApplicationContext context =	new FileSystemXmlApplicationContext("src/main/webapp/META-INF/autoconf/mail.xml");  
		//FileSystemXmlApplicationContext   ClassPathXmlApplicationContext
//		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler(); 
//		System.out.println(scheduler.getTriggerGroupNames());
//		TriggerKey triggerKey = new TriggerKey("jobTrigger",scheduler.DEFAULT_GROUP);
//		CronTriggerFactoryBean trigger = (CronTriggerFactoryBean)scheduler.getTrigger(triggerKey);
//		String cronExpression = "";
//		trigger.setCronExpression(cronExpression);
//		scheduler.isShutdown();//判断作业调度内是否有作业
//		scheduler.deleteJob(null);//删除作业
		/************************邮件添加附件***********************/
//	     创建一个Email附件
//
//	       EmailAttachment emailattachment = new EmailAttachment();
//
//	       emailattachment.setPath("src/main/webapp/assets/img/menu.jpg");
//	       
////		     emailattachment.setURL(new URL("http://img04.taobaocdn.com/bao/uploaded/i4/10506027962591313/T1xF1yFjJgXXXXXXXX_!!"));
//	       
//	       emailattachment.setDisposition(EmailAttachment.ATTACHMENT);
//
//	       emailattachment.setDescription("This is Smile picture");
//
//	       emailattachment.setName("bulktree.jpg");
		
//		System.getProperties().setProperty("http.proxyHost", "60.190.138.151");
//
//		System.getProperties().setProperty("http.proxyPort", "80");
		/***************************邮件发送测试***************************/
//		   HtmlEmail email = new HtmlEmail();
//		   try {
//			    // 这里是SMTP发送服务器的名字：，163的如下：
//			    email.setHostName("smtp.qq.com");
//			    // 字符编码集的设置
//			    email.setCharset("gbk");
//			    // 收件人的邮箱
//			    email.addTo("mailSendJust@163.com","xx");//2784671042@qq.com
//			    // 发送人的邮箱
//			    email.setFrom("1048355451@qq.com", "小陈");
//			    // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
//			    email.setAuthentication("1048355451@qq.com", "520zel08061314");
//			    email.setSubject("ip代理发送邮件测试");
//			    // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
//			    email.setMsg("看到请回复，ip代理是否成功，O(∩_∩)O谢谢");
////			    email.setSSLOnConnect(true);
////			    email.attach(emailattachment);
//			    // 发送
//			    
//			    email.send();
//			   
//			    System.out.println ( "邮件发送成功!" );
//		   } catch (EmailException e) {
//		    e.printStackTrace();
//		    System.out.println ( "邮件发送失败!" );
//		   }
		
		/**************************ip代理测试******************************/
//		System.getProperties().setProperty("http.proxyHost", "221.11.67.115");
//
//		System.getProperties().setProperty("http.proxyPort", "9999");
//
//		System.out.println(getHtml("http://iframe.ip138.com/ic.asp")); //http://www.ip138.com/ip2city.asp

	}
	
	private static String getHtml(String address){

		StringBuffer html = new StringBuffer();

		String result = null;

		try{

		 URL url = new URL(address);

		 URLConnection conn = url.openConnection();

		conn.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; GTB5; .NET CLR 2.0.50727; CIBA)");

		BufferedInputStream in = new BufferedInputStream(conn.getInputStream());

		try {

			String inputLine;
	
			byte[] buf = new byte[4096];
	
			int bytesRead = 0;
	
			while (bytesRead >= 0) {
	
				inputLine = new String(buf, 0, bytesRead, "ISO-8859-1");
		
				html.append(inputLine);
		
				bytesRead = in.read(buf);
		
				inputLine = null;
	
			}
	
			buf = null;
	
			} finally {
	
				in.close();
		
				conn = null;
		
				url = null;
	
			}
	
			result = new String(html.toString().trim().getBytes("ISO-8859-1"), "gb2312").toLowerCase();

		}catch(Exception e){

		e.printStackTrace();

		return null;

		}

		html = null;

		return result;

		}

}
