package me.aipa.spam.module.screen.json;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.citrus.turbine.dataresolver.Params;
import com.alibaba.fastjson.JSONObject;
import me.aipa.spam.bean.MailSendFromDO;
import me.aipa.spam.bean.MailSendToDO;
import me.aipa.spam.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-7
 * Time: 下午4:11
 * To change this template use File | Settings | File Templates.
 */
public class MailSystemAjax extends AbstractJsonScreen{
    @Autowired
    private MailService mailService;
    @Autowired
    private HttpServletResponse httpServletResponse;
    public void doMailSendToAdd(Context context,@Params MailSendToDO mailSendToDO) throws Exception{
        if(mailSendToDO.getAddress()!=""){
            int result =mailService.insertMailSendTo(mailSendToDO.getAddress());
            if(result==0){
                responseJson("{'success':'false','error':'parameter repeat'}");
            }else{
                responseJson("{'success':'true'},'error':''");
            }
        }else{
            responseJson("{'success':'false','error':'address_empty'}");
        }
    }
    public void doMailSendFromAdd(@Params MailSendFromDO mailSendFromDO) throws Exception{
          if(mailSendFromDO.getAddress()==null || mailSendFromDO.getUserName()==null || mailSendFromDO.getPass()==null || mailSendFromDO.getHost()==null){
              responseJson("{'success':'false','error':'parameter missing'}");
          }else{
              int result = mailService.insertMailSendFrom(mailSendFromDO);
              if(result==1){
                  responseJson("{'success':'true','error':''}");
              }else{
                  responseJson("{'success':'false','error':'parameter repeat'}");
              }
          }
    }

    public void doMailSendFromListByPage(@Param("pageIndex") int page,@Param("limit") int num) throws Exception{
        page=page<1?1:page;
        num=num==0?30:num;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("results",mailService.getMailSendFromCount());
        jsonObject.put("rows",mailService.getMailSendFromList((page-1)*num,num));
        httpServletResponse.getWriter().write(JSONObject.toJSONString(jsonObject));
    }


}
