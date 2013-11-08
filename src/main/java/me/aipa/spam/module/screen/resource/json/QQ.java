package me.aipa.spam.module.screen.resource.json;

import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.fastjson.JSONObject;
import me.aipa.spam.bean.QQAccount;
import me.aipa.spam.service.QQService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-11-8
 * Time: 下午2:36
 * To change this template use File | Settings | File Templates.
 */
public class QQ {


    @Autowired
    private HttpServletResponse httpServletResponse;

    @Autowired
    private QQService qqService;

    /**
     * 根据页码，每页条数来获取QQ
     * @param pageIndex
     * @param limit
     *
     * id,qq 为额外的搜索条件
     * @param id
     * @param qq
     */
    public void doGetQQ(@Param("pageIndex") int pageIndex,
                        @Param("limit") int limit,
                        @Param("id") int id,
                        @Param("qq") String qq){
        int page = pageIndex + 1;
        httpServletResponse.setContentType("application/json");

        List<QQAccount> qqAccountList = qqService.getQQList(page, limit, id, qq);
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("rows", qqAccountList);
            jsonObject.put("results", qqService.countQQ(id,qq));
            httpServletResponse.getWriter().write(JSONObject.toJSONString(jsonObject));
//            httpServletResponse.getWriter().write(jsonObject);

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


    /**
     * 添加或编辑QQ
     * @param isNew
     * @param qq
     * @param password
     */
    public void doEditQQ(@Param("isNew") String isNew,
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


    /**
     * 添加多个QQ
     * @param qqListStr
     */
    public void doBatchAddQQ(@Param("qqListStr") String qqListStr){
        try{
            String[] qqList = qqListStr.split("\n");
            for(int i=0; i<qqList.length; i++){
                String[] qqAccountStr = qqList[i].split("/");
                qqService.addQQ(new QQAccount(qqAccountStr[0], qqAccountStr[1]));
            }
            JSONObject r = new JSONObject();
            r.put("success", Boolean.TRUE);
            httpServletResponse.getWriter().write(JSONObject.toJSONString(r));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 删除QQ
     * @param qqNumber
     */
    public void doDelQQ(@Param("qqNumber") String qqNumber){
        String[] qqArr = qqNumber.split(",");
        if(qqArr.length>0){
            for(int i=0; i<qqArr.length; i++){
                qqService.delQQByQQNumber(qqArr[i]);
            }
            JSONObject r = new JSONObject();
            r.put("success", Boolean.TRUE);
            try {
                httpServletResponse.getWriter().write(JSONObject.toJSONString(r));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
