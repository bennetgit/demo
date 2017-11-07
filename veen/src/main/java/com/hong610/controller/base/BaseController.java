package com.hong610.controller.base;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.baomidou.mybatisplus.plugins.Page;
import com.hong610.common.exception.error.SystemError;
import com.hong610.domain.User;
import com.hong610.security.entity.UserDetail;
import com.hong610.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartResolver;

import com.alibaba.fastjson.JSON;

/**
 * 控制器基类
 * Created by Hong on 2016/12/7.
 */
public class BaseController implements ServletContextAware {

	/**日志**/
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	protected HttpServletRequest request;  
    protected HttpServletResponse response;  
    protected HttpSession session;  
    protected ServletContext application;
	protected UserDetail user;

	/**文件**/
	@Resource
	protected MultipartResolver multipartResolver;

    @ModelAttribute  
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
        this.request = request;  
        this.response = response;  
        this.session = request.getSession();
		if(SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetail)
			user = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		request.setAttribute("UserDetail", user);
    }

	public void setServletContext(ServletContext arg0) {
		this.application = arg0;
	}
	
	/**
	 * 获取传递过来的参数
	 */
	public String getParameter(String key){
		return request.getParameter(key);
	}
		
	/**
	 * 转JSON并输出到网页
	 */
	public void print(Object obj){
		try{
			String result = JSON.toJSONString(obj);
			if(StringUtils.isEmpty(result))
				result = JSON.toJSONString(SystemError.ERROE_EXCEPTION);
			response.getWriter().print(result);
		}catch(Exception e){
			return;
		}
	}

	/**
	 * 分页对象
	 */
    public <T> Page<T> getPage(Class<T> clazz) {
		Page<T> page = new Page<T>();
		String pageNo = getParameter("pageNo");
		String pageSize = getParameter("pageSize");
		pageNo = pageNo==null || "".equals(pageNo) ? "1" : pageNo;
		pageSize = pageSize==null || "".equals(pageSize) ? "10" : pageSize;

		page.setSearchCount(true);
		page.setSize(Integer.valueOf(pageSize));
		page.setCurrent((Integer.valueOf(pageNo)));
        return page;
    }
}
