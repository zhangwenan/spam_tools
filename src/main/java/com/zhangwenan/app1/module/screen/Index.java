package com.zhangwenan.app1.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.zhangwenan.app1.bean.ParamterMap;
import com.zhangwenan.app1.bean.SiteConfig;
import com.zhangwenan.app1.service.AdminService;
import com.zhangwenan.app1.service.KeywordService;
import com.zhangwenan.app1.service.SiteConfigService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-10-11
 * Time: 下午10:37
 * To change this template use File | Settings | File Templates.
 */
public class Index {

    @Autowired
    private AdminService adminService;

    @Autowired
    private KeywordService keywordService;

    @Autowired
    private SiteConfigService siteConfigService;

    @Autowired
    HttpServletRequest request;

    public void execute(Context context) throws Exception{

        context.put("serverName", request.getServerName());
        SiteConfig siteConfig = siteConfigService.getConfigByServerName(request.getServerName());
        context.put("siteName", siteConfig.getSiteName());
        context.put("siteDesc", siteConfig.getSiteDesc());


        ParamterMap paramterMap = new ParamterMap(siteConfig.getLastUpdatedId(), 300);
        context.put("indexKeywordList", keywordService.getKeywordList(paramterMap));

//        if(!keywordService.nextEnough(paramterMap)){
//            paramterMap.setStartId(keywordService.getFirstKeywordId());
//        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        context.put("lastUpdatedTime", formatter.format(siteConfig.getLastUpdatedTime()));

//        context.put("prevIdList", keywordService.prevEnough(paramterMap));

//        paramterMap.setStartId(300);
//        paramterMap.setLength(400);
//        context.put("nextIdList", keywordService.nextEnough(paramterMap));
    }
}
