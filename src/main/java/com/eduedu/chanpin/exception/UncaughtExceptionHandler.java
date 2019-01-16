package com.eduedu.chanpin.exception;

/**
 * Created by Administrator on 2016/6/23.
 */

import com.alibaba.fastjson.JSON;
import com.eduedu.chanpin.domain.dto.Result;
import com.eduedu.chanpin.utils.NetUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UncaughtExceptionHandler implements HandlerExceptionResolver {
    private static final Logger logger = LoggerFactory.getLogger(UncaughtExceptionHandler.class.getName());

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        //获取异常信息
        String exceptionMessage = e.getMessage();
        String exceptionStack = ExceptionUtils.getStackTrace(e);

        //日志记录异常信息
        logger.error("Spring-Handled UncaughtException, URL: " + httpServletRequest.getRequestURL().toString() + ", Message: " +exceptionMessage);
        logger.error("Details: " + exceptionStack);

        //回复客户端请求
        return NetUtil.isAjax(httpServletRequest) ? handleNonSyncException(httpServletResponse, exceptionMessage, exceptionStack)
                                                    : handleSyncException(e, exceptionMessage, exceptionStack) ;
    }

    /**
     * 处理异步请求的未捕获异常
     * @param httpServletResponse：响应
     * @param exceptionMessage：异常消息
     * @param exceptionStack：异常栈
     * @return 无
     */
    private ModelAndView handleNonSyncException(HttpServletResponse httpServletResponse, String exceptionMessage, String exceptionStack) {
        String result = JSON.toJSONString(Result.fail(exceptionMessage));

        NetUtil.responseAjaxJson(httpServletResponse, result);
        return null;
    }

    /**
     * 处理同步请求的未捕获异常
     * @param e：异常
     * @param exceptionMessage：异常消息
     * @param exceptionStack：异常栈
     * @return 视图文件名称和数据
     */
    private ModelAndView handleSyncException(Exception e, String exceptionMessage, String exceptionStack) {
        String promptViewName = "prompt.htm";
        ModelMap model = new ModelMap();

        if (e instanceof UncaughtException) {
            UncaughtException uncaughtException = (UncaughtException) e;
            promptViewName = (uncaughtException.getViewName() == null) ? promptViewName : uncaughtException.getViewName();
            model = uncaughtException.getModelMap();
        }

        model.addAttribute("prompt", "后台异常，详情请咨询技术人员");
        model.addAttribute("error", exceptionMessage);
        model.addAttribute("detail", exceptionStack);
        return new ModelAndView(promptViewName, model);
    }
}
