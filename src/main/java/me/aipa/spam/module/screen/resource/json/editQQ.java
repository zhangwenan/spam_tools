package me.aipa.spam.module.screen.resource.json;

import com.alibaba.citrus.turbine.dataresolver.Param;
import me.aipa.spam.bean.QQAccount;
import me.aipa.spam.service.QQService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-11-7
 * Time: 下午6:07
 * To change this template use File | Settings | File Templates.
 */
public class editQQ {

    private static Logger logger = LoggerFactory.getLogger(editQQ.class);


    @Autowired
    private HttpServletResponse httpServletResponse;

    @Autowired
    private QQService qqService;

    public void execute(@Param("isNew") String isNew,
                        @Param("qq") String qq,
                        @Param("password") String password){
        try{
            if(isNew.equals("true")){
                QQAccount qqAccount = new QQAccount(qq, password);
                qqService.addQQ(qqAccount);
                httpServletResponse.getWriter().write("{\"success\":true}");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}
