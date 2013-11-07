package me.aipa.spam.module.screen.resource.json;

import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import me.aipa.spam.bean.QQAccount;
import me.aipa.spam.service.QQService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-11-6
 * Time: 下午4:35
 * To change this template use File | Settings | File Templates.
 */
public class getQQ {

    private static Logger logger = LoggerFactory.getLogger(getQQ.class);


    @Autowired
    private HttpServletResponse httpServletResponse;

    @Autowired
    private QQService qqService;

    public void execute(@Param("pageIndex") int pageIndex, @Param("limit") int limit){
        int page = pageIndex + 1;
        httpServletResponse.setContentType("application/json");

        List<QQAccount> qqAccountList = qqService.getQQListByPage(page, limit);
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("rows", qqAccountList);
            jsonObject.put("results", qqService.countQQ());
            httpServletResponse.getWriter().write(JSONObject.toJSONString(jsonObject));
        }
        catch (IOException e){

        }
        finally {
            try{
                httpServletResponse.getWriter().close();
            }
            catch (IOException e){

            }
        }
    }
}
