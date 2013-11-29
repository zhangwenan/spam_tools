package me.aipa.spam.module.screen.resource.json;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import me.aipa.spam.bean.MailContentDO;
import me.aipa.spam.service.MailService;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.fastjson.JSONObject;

/**
 * Created with IntelliJ IDEA. User: wenlie Date: 13-11-8 Time: 下午2:36 To change
 * this template use File | Settings | File Templates.
 */
public class mailContent {

	@Autowired
	private HttpServletResponse httpServletResponse;

	@Autowired
	private MailService mailService;

	/**
	 * 根据页码，每页条数来获取邮件模板
	 * 
	 * @param pageIndex
	 * @param limit
	 * @param id
	 *            为额外的搜索条件
	 * @param title
	 *            为额外的搜索条件
	 */
	public void doGetMailContent(@Param("pageIndex") int pageIndex, @Param("limit") int limit, @Param("id") int id,
			@Param("title") String title) {
		int page = pageIndex + 1;
		httpServletResponse.setContentType("application/json");

		List<MailContentDO> mailContentList = mailService.getMailContentList(page, limit, id, title);
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("rows", mailContentList);
			jsonObject.put("results", mailService.countMailContent(id, title));
			httpServletResponse.getWriter().write(JSONObject.toJSONString(jsonObject));

		} catch (IOException e) {

		} finally {
			try {
				httpServletResponse.getWriter().close();
			} catch (IOException e) {

			}
		}
	}

	/**
	 * 添加或编辑邮件模板
	 * 
	 * @param isNew
	 * @param title
	 * @param content
	 */
	public void doSaveMailContent(@Param("isNew") String isNew, @Param("title") String title,
			@Param("content") String content, @Param("id") int id) {
		try {
			int result = 0;
			MailContentDO mailContentDO = new MailContentDO();
			mailContentDO.setTitle(title);
			mailContentDO.setContent(content);
			if ("true".equals(isNew)) {
				mailContentDO.setAddTime(new Date());
				mailContentDO.setDel(0);
				result = mailService.insertMailContent(mailContentDO);
			} else {
				mailContentDO.setId(id);
				result = mailService.updateMailContentById(mailContentDO);
			}
			if (result == 1) {
				httpServletResponse.getWriter().write("{\"success\":true,\"error\":\"\"}");
			} else {
				httpServletResponse.getWriter().write("{\"success\":false,\"error\":\"Sql error\"}");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
	}

	/**
	 * 删除邮件模板
	 * 
	 * @param ids
	 */
	public void doDelMailContent(@Param("ids") String ids) {
		String[] args = ids.split(",");
		if (args.length == 0) {
			try {
				httpServletResponse.getWriter().write("{\"success\":false,\"error\":\"empty arr[]\"}");
			} catch (IOException e) {
				e.printStackTrace(); // To change body of catch statement use
										// File | Settings | File Templates.
			}
		} else {
			for (int i = 0; i < args.length; i++) {
				try {
					mailService.deleteMailContentById(Integer.valueOf(args[i]));
				} catch (Exception e) {
					e.printStackTrace(); // To change body of catch statement
											// use File | Settings | File
											// Templates.
				}
			}
			try {
				httpServletResponse.getWriter().write("{\"success\":true,\"error\":\"\"}");
			} catch (IOException e) {
				e.printStackTrace(); // To change body of catch statement use
										// File | Settings | File Templates.
			}
		}
	}

}
