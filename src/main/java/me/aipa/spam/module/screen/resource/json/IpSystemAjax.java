package me.aipa.spam.module.screen.resource.json;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import me.aipa.spam.bean.IpGetDO;
import me.aipa.spam.service.IpGetService;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.fastjson.JSONObject;


public class IpSystemAjax {
	@Autowired
	private  IpGetService ipGetService;
	
	@Autowired
    private HttpServletResponse httpServletResponse;
	
	/**
	 * 开始获取ip
	 * @throws Exception
	 */
	public void doIpGetByUrl() throws Exception{
		ipGetService.checkTimeTisk();
		
		 httpServletResponse.getWriter().write("{\"success\":true,\"error\":\"\"}");
    }
	
	/**
	 * 停止获取ip
	 * @throws Exception
	 */
	public void doIpGetDownByUrl() throws Exception{
		ipGetService.getIpDown();
		
		 httpServletResponse.getWriter().write("{\"success\":true,\"error\":\"\"}");
    }
	
	/**
	 * 删除网站
	 * @param urlNumber
	 * @throws Exception
	 */
	public void doDeleteUrl(@Param("urlNumber") String urlNumber)throws Exception{
		 String[] urlArr = urlNumber.split(",");
	        if(urlArr.length>0){
	            for(int i=0; i<urlArr.length; i++){
	            	ipGetService.deleteUrl(urlArr[i]);
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
	
	/**
	 * 添加或编辑一个网站url
	 * @param isNew
	 * @param url
	 * @param detailRegular
	 * @param ipRegular
	 * @param domain
	 */
	public void doEditUrl(@Param("isNew") String isNew,@Param("id") String id,
            @Param("url") String url,@Param("detailRegular") String detailRegular,
            @Param("ipRegular") String ipRegular, @Param("domainName") String domainName){
			try{
					if(isNew.equals("true")){
					   IpGetDO ipGetDO = new IpGetDO();
					   ipGetDO.setDetailRegular(detailRegular);
					   ipGetDO.setIpRegular(ipRegular);
					   ipGetDO.setUrl(url);
					   ipGetDO.setDomainName(domainName);
					   
					   ipGetService.insertUrl(ipGetDO);
					   httpServletResponse.getWriter().write("{\"success\":true}");
					}else if(isNew.equals("false")){
						 IpGetDO ipGetDO = new IpGetDO();
						 ipGetDO.setDetailRegular(detailRegular);
						 ipGetDO.setIpRegular(ipRegular);
						 ipGetDO.setUrl(url);
						 ipGetDO.setDomainName(domainName);
						 ipGetDO.setId(Integer.valueOf(id));  
						   
						 ipGetService.insertUrl(ipGetDO);
						 httpServletResponse.getWriter().write("{\"success\":true}");
					}
			}
			catch (IOException e){
				e.printStackTrace();
			}
		}
	
	/**
	 * 网站页面列表获取
	 * @param pageIndex
	 * @param limit
	 * @param id
	 * @param domainName
	 */
	public void doGetUrlList(@Param("pageIndex") int pageIndex,
            @Param("limit") int limit,
            @Param("id") int id,
            @Param("domainName") String domainName){
		int page = pageIndex + 1;
		httpServletResponse.setContentType("application/json");
		
		List<IpGetDO> urlList = ipGetService.getUrlList(page, limit, id, domainName);
		try{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("rows", urlList);
			jsonObject.put("results", ipGetService.getUrlCount(id, domainName));
			httpServletResponse.getWriter().write(JSONObject.toJSONString(jsonObject));
		}
		catch (IOException e){
			//
		}
		finally {
			try{
			    httpServletResponse.getWriter().close();
			}
			catch (IOException e){
				//
			}
		}
		
	}
	
}
