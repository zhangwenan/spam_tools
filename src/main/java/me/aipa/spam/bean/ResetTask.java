package me.aipa.spam.bean;

import me.aipa.spam.service.IpGetService;
import me.aipa.spam.service.KeywordService;
import me.aipa.spam.service.SiteConfigService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wenlie
 * Date: 13-10-18
 * Time: 下午5:01
 * To change this template use File | Settings | File Templates.
 */
public class ResetTask {

//    @Autowired
//    SiteConfigService siteConfigService;
//
//    @Autowired
//    KeywordService keywordService;

    public void resetIndexStatus(){

//        SiteConfig siteConfig = siteConfigService.getConfigByServerName("spam.aipa.me");
//        siteConfig.setLastUpdatedTime(new Date());
//        int indexKeywordsLength = 300;
//        ParamterMap paramterMap = new ParamterMap(siteConfig.getLastUpdatedId(), indexKeywordsLength);
//        if(keywordService.nextEnough(paramterMap)){
//            siteConfig.setLastUpdatedId(siteConfig.getLastUpdatedId() + indexKeywordsLength);
//        }
//        else{
//            siteConfig.setLastUpdatedId(1);
//        }
//        siteConfigService.updateConfig(siteConfig);
    }
}
