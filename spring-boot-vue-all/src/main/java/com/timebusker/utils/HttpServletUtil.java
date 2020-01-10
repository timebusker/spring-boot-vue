package com.timebusker.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.timebusker.common.web.ResultVO;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description: HttpServletUtil
 * @Author: Administrator
 * @Date: 2020/1/10 11:13
 **/
public class HttpServletUtil {

    /**
     * 判断请求方式并做响应处理
     *
     * @param response
     * @param resultVO
     */
    public static void writeJson(HttpServletRequest request, HttpServletResponse response, ResultVO resultVO) {
        try {
            if (isAjaxRequest(request)) {
                writeJson(response, resultVO);
            } else {
                // 控制页面跳转
                request.getRequestDispatcher("/index").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 输出JSON数据到前端
     *
     * @param response
     * @param resultVO
     */
    public static void writeJson(HttpServletResponse response, ResultVO resultVO) {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(resultVO));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        if (!StringUtils.isBlank(request.getHeader("x-requested-with")) && request.getHeader("x-requested-with").equals("XMLHttpRequest"))
            return true;
        return false;
    }
}
