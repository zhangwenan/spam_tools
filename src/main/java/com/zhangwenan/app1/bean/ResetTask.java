package com.zhangwenan.app1.bean;

import com.zhangwenan.app1.service.KeywordService;
import com.zhangwenan.app1.service.SiteConfigService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-10-18
 * Time: 下午5:01
 * To change this template use File | Settings | File Templates.
 */
public class ResetTask {

    @Autowired
    SiteConfigService siteConfigService;

    @Autowired
    KeywordService keywordService;

    public void resetIndexStatus(){
        SiteConfig siteConfig = siteConfigService.getConfigByServerName("utao.me");
        siteConfig.setLastUpdatedTime(new Date());
        int indexKeywordsLength = 300;
        ParamterMap paramterMap = new ParamterMap(siteConfig.getLastUpdatedId(), indexKeywordsLength);
        if(keywordService.nextEnough(paramterMap)){
            siteConfig.setLastUpdatedId(siteConfig.getLastUpdatedId() + indexKeywordsLength);
        }
        else{
            siteConfig.setLastUpdatedId(1);
        }
        siteConfigService.updateConfig(siteConfig);
    }
}
