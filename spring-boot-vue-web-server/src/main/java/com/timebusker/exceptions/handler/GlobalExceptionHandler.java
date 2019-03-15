package com.timebusker.exceptions.handler;

import com.timebusker.exceptions.CommonException;
import com.timebusker.web.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @DESC:GlobalExceptionHandler:统一异常处理类
 * @author:timebusker
 * @date:2019/3/13
 */
public class GlobalExceptionHandler {

    private static final String ERROR_PAGE_40X = "40X";

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 自定义异常
     */
    @ExceptionHandler(CommonException.class)
    @ResponseBody
    public ResponseBean handleRRException(CommonException e) {
        ResponseBean rb = new ResponseBean();
        rb.put("code", e.getCode());
        rb.put("msg", e.getMessage());
        return rb;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(ERROR_PAGE_40X);
        return mv;
    }
}
