package me.aipa.spam.bean;

import me.aipa.spam.service.IpGetService;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-10-18
 * Time: 下午5:01
 * To change this template use File | Settings | File Templates.
 */
public class MailTask implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6733947746617695966L;
	@Autowired
	private  IpGetService ipGetService;
    
	 public void mailSend(){
	    	
//	    	System.out.println(111111111);
//	    	IpGetDO ipGetDO = ipGetService.getUrl();
//	    	System.out.println(ipGetDO.getId());
	    }
}
