package me.aipa.spam.module.screen;

import com.alibaba.citrus.turbine.Context;
import me.aipa.spam.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-5
 * Time: 下午7:01
 * To change this template use File | Settings | File Templates.
 */
public class MailSystem {

    @Autowired
    private MailService mailService;
    public void execute(Context context)throws Exception{
        context.put("mailSendToCount",mailService.getMailSendToCount());    //获取接收邮箱全部数量
        context.put("mailSendFromCount",mailService.getMailSendFromCount());    //获取发送邮箱全部数量
       /* SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        context.put("thetime",df.format(new Date()));*/
        context.put("pageId","code");
    }


}
