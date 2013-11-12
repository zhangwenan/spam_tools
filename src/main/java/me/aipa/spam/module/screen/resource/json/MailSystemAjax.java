package me.aipa.spam.module.screen.resource.json;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.citrus.turbine.dataresolver.Params;
import com.alibaba.fastjson.JSONObject;
import me.aipa.spam.bean.MailSendFromDO;
import me.aipa.spam.bean.MailSendToDO;
import me.aipa.spam.module.screen.json.AbstractJsonScreen;
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
public class MailSystemAjax extends AbstractJsonScreen {
    @Autowired
    private MailService mailService;
    @Autowired
    private HttpServletResponse httpServletResponse;
    public void doMailSendToAdd(Context context,@Params MailSendToDO mailSendToDO) throws Exception{
        if(mailSendToDO.getAddress()!=""){
            int result =mailService.insertMailSendTo(mailSendToDO.getAddress());
            if(result==0){
                httpServletResponse.getWriter().write("{\"success\":false,\"error\":\"parameter repeat\"}");
            }else{
                httpServletResponse.getWriter().write("{\"success\":true,\"error\":\"\"}");
            }
        }else{
            httpServletResponse.getWriter().write("{\"success\":false,\"error\":\"address_empty\"}");
        }
    }
    public void doMailSendFromAdd(@Params MailSendFromDO mailSendFromDO) throws Exception{
          if(mailSendFromDO.getAddress()==null || mailSendFromDO.getUserName()==null || mailSendFromDO.getPass()==null || mailSendFromDO.getHost()==null){
              httpServletResponse.getWriter().write("{\"success\":false,\"error\":\"parameter missing\"}");
          }else{
              int result = mailService.insertMailSendFrom(mailSendFromDO);
              if(result==1){
                  httpServletResponse.getWriter().write("{\"success\":true,\"error\":\"\"}");
              }else{
                  httpServletResponse.getWriter().write("{\"success\":false,\"error\":\"parameter repeat\"}");
              }
          }
    }

    public void doMailSendFromListByPage(@Param("pageIndex") int page,@Param("limit") int num) throws Exception{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("results",mailService.getMailSendFromCount());
        jsonObject.put("rows",mailService.getMailSendFromList(page*num,num));
        httpServletResponse.getWriter().write(JSONObject.toJSONString(jsonObject));
    }

    public void doMailSendFromUpdate(@Params MailSendFromDO mailSendFromDO) throws Exception{
        if(mailSendFromDO.getAddress()==null || mailSendFromDO.getUserName()==null || mailSendFromDO.getPass()==null || mailSendFromDO.getHost()==null){
            httpServletResponse.getWriter().write("{\"success\":false,\"error\":\"parameter missing\"}");
        }else{
             int result = mailService.updateMailSendFromById(mailSendFromDO);
            if(result==1){
                httpServletResponse.getWriter().write("{\"success\":true,\"error\":\"\"}");
            }else{
                httpServletResponse.getWriter().write("{\"success\":false,\"error\":\"Sql error\"}");
            }
        }
    }

   public void doMailSendFromDelByAddress(@Param("ids") String ids) throws Exception{
        String[] args = ids.split(",");
       if(args.length==0){
            httpServletResponse.getWriter().write("{\"success\":false,\"error\":\"empty arr[]\"}");
        }else{
            for(int i=0;i<args.length;i++){
                mailService.delMailSendFromById(Integer.valueOf(args[i]));
            }
            httpServletResponse.getWriter().write("{\"success\":true,\"error\":\"\"}");
        }
    }

    public void doMailSendToListByPage(@Param("pageIndex") int page,@Param("limit") int num) throws Exception{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("results",mailService.getMailSendToCount());
        jsonObject.put("rows",mailService.getMailSendToList(page*num,num));
        httpServletResponse.getWriter().write(JSONObject.toJSONString(jsonObject));
    }

    public void doMailSendToDelByAddress(@Param("ids") String ids) throws Exception{
        String[] args = ids.split(",");
        if(args.length==0){
            httpServletResponse.getWriter().write("{\"success\":false,\"error\":\"empty arr[]\"}");
        }else{
            for(int i=0;i<args.length;i++){
               mailService.delMailSendToById(Integer.valueOf(args[i]));
            }
            httpServletResponse.getWriter().write("{\"success\":true,\"error\":\"\"}");
        }
    }

    public void doMailSendToUpdate(@Params MailSendToDO mailSendToDO)throws Exception{
         if(mailSendToDO.getAddress()=="" || mailSendToDO.getId()<=0){
             httpServletResponse.getWriter().write("{\"success\":false,\"error\":\"parameter missing\"}");
         }else{
             int result = mailService.updateMailSendToById(mailSendToDO);
             if(result==1){
                 httpServletResponse.getWriter().write("{\"success\":true,\"error\":\"\"}");
             }else{
                 httpServletResponse.getWriter().write("{\"success\":false,\"error\":\"Sql error\"}");
             }
         }
    }
}
