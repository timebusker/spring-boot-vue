package com.timebusker.common.exception;

import com.baomidou.kaptcha.exception.KaptchaException;
import com.baomidou.kaptcha.exception.KaptchaIncorrectException;
import com.baomidou.kaptcha.exception.KaptchaNotFoundException;
import com.baomidou.kaptcha.exception.KaptchaTimeoutException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: GlobalExceptionHandler：全局异常信息处理类
 * @Author: Administrator
 * @Date: 2019/12/19 23:17
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 在@ControllerAdvice类中，根据抛出的具体Exception类型匹配@ExceptionHandler中配置的异常类型来匹配错误映射和处理
     */
    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = KaptchaException.class)
    public ErrorMassage<Object> kaptchaExceptionHandler(KaptchaException kaptchaException) {
        ErrorMassage<Object> error = new ErrorMassage<>();
        error.setCode(404);
        if (kaptchaException instanceof KaptchaIncorrectException) {
            error.setMessage("验证码不正确");
            return error;
        } else if (kaptchaException instanceof KaptchaNotFoundException) {
            error.setMessage("验证码未找到");
            return error;
        } else if (kaptchaException instanceof KaptchaTimeoutException) {
            error.setMessage("验证码过期");
            return error;
        } else {
            error.setMessage("验证码渲染失败");
            return error;
        }
    }

    /**
     * 捕获自定义异常，返回json信息
     */
    @ExceptionHandler(value = VueException.class)
    public ErrorMassage<Object> ErrorHandler(HttpServletRequest request, VueException e) throws Exception {
        ErrorMassage<Object> error = new ErrorMassage<>();
        error.setCode(404);
        error.setMessage("Not Found Exception......");
        error.setUrl(request.getRequestURL().toString());
        error.setData("");
        return error;
    }

    @ExceptionHandler(value = ArithmeticException.class)
    public ErrorMassage<Object> ErrorHandler(HttpServletRequest req, ArithmeticException e) throws Exception {
        ErrorMassage<Object> error = new ErrorMassage<>();
        error.setCode(405);
        error.setMessage("java.lang.ArithmeticException: / by zero");
        error.setUrl(req.getRequestURL().toString());
        error.setData(e);
        return error;
    }

    /**
     * @ExceptionHandler用来定义函数针对的异常类型，最后将Exception对象和请求URL映射到error.html中
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView ErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        ErrorMassage<Exception> error = new ErrorMassage<Exception>();
        error.setCode(400);
        error.setMessage("系统异常");
        error.setUrl(request.getRequestURI());
        error.setData(e);
        mav.addObject(error);
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
}
