package com.hong610.controller.error;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hong610.common.exception.AppException;
import com.hong610.common.exception.Error;
import com.hong610.common.exception.error.SystemError;
import com.hong610.controller.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 错误相关
 * Created by Hong on 2016/12/2.
 */
@Controller
public class ErrorController extends BaseController implements HandlerExceptionResolver,EmbeddedServletContainerCustomizer {

    private static Logger log = LoggerFactory.getLogger(ErrorController.class);

    /**
     * 没有权限
     */
    @RequestMapping(value = "/access_denied.html")
    public String accessDenied(){
        if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
            throw new AppException(SystemError.PAGE_404);
            //throw new AppException(SystemError.USER_NOTRESOURCES_ERROR);
        }
        return "error/not_found";//"error/accessDenied";
    }

    /**
     * Session超时进入的页面
     */
    @RequestMapping(value = "/time_out.html")
    public String timeOut(){
        if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
            throw new AppException(SystemError.USER_INVALID_SESSION_ERROR);
        }
        return "error/time_out";
    }

    /**
     * 404
     */
    @RequestMapping(value = "/not_found.html")
    public String notFound(){
        if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
            throw new AppException(SystemError.PAGE_404);
        }
        return "error/not_found";
    }

    /**
     * 405
     */
    @RequestMapping(value = "/method_not_allowed.html")
    public String methodNotAllowed(){
        if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
            throw new AppException(SystemError.PAGE_405);
        }
        return "error/method_not_allowed";
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
        configurableEmbeddedServletContainer.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND , "/not_found.html"), new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED , "/method_not_allowed.html"));
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(e.toString()+"\n");
        StackTraceElement[] arrayOfStackTraceElement = e.getStackTrace();//得到错误堆栈信息
        for (Object localObject2 : arrayOfStackTraceElement)//组装信息
            buffer.append("\tat " + localObject2+"\n");
        System.err.println(buffer.toString());
        log.error(buffer.toString());

        ModelAndView mav = new ModelAndView("error/error");
        if(e instanceof AppException){
            AppException appException = (AppException) e;
            mav.addObject("err", JSON.toJSONString(new Error(appException.getCode(), appException.getMessage())));
        }else if(e instanceof DataAccessException){
            mav.addObject("err", JSON.toJSONString(SystemError.ERROE_DATABASE));
        } else if(e instanceof IllegalArgumentException ){
            mav.addObject("err", JSON.toJSONString(SystemError.ERROR_PARAMETER));
        } else if(e instanceof NullPointerException){
            mav.addObject("err", JSON.toJSONString(SystemError.ERROR_NULLPOINT));
        } else if(e instanceof AccessDeniedException) {
            mav.setViewName("/access_denied.html");
        } else {
            JSONObject json = JSON.parseObject(JSON.toJSONString(SystemError.ERROE_EXCEPTION));
            json.remove("message");
            json.put("message", e.toString());
            mav.addObject("err", json.toJSONString());
        }
        return mav;
    }
}
