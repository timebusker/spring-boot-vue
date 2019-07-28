package com.timebusker.web;

import com.alibaba.fastjson.JSON;
import com.timebusker.common.ResultVo;
import com.timebusker.model.SystemMenu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @DESC:HomeController：首页控制层
 * @author:timebusker
 * @date:2019/7/26
 */
@Controller
@RequestMapping("/")
public class HomeController extends AbstractBaseController {

    @RequestMapping({"/", "", "/index", "/home"})
    public String index() {
        return "index";
    }

    /**
     * 输出有序菜单集合
     *
     * @return
     */
    @RequestMapping("/menus")
    @ResponseBody
    public ResultVo menus() {
        // 封装三层测试菜单
        Set<SystemMenu> set = new TreeSet<>();
        SystemMenu process = new SystemMenu("0_1", "0", "", "处理中心", "/process", "process");
        SystemMenu work = new SystemMenu("0_2", "0", "", "我的工作台", "/work", "work");
        Set<SystemMenu> workChildren = new TreeSet<>();
        SystemMenu backlog = new SystemMenu("0_2_1", "0_2", "", "待办事项", "/work/backlog", "backlog");
        SystemMenu workLog = new SystemMenu("0_2_2", "0_2", "", "工作日志", "/work/workLog", "workLog");
        SystemMenu information = new SystemMenu("0_2_3", "0_2", "", "个人信息", "/work/information", "information");
        SystemMenu manger = new SystemMenu("0_2_4", "0_2", "", "部门管理", "/work/manger", "manger");
        workChildren.add(backlog);
        workChildren.add(workLog);
        workChildren.add(information);
        workChildren.add(manger);
        work.setChildren(workChildren);
        Set<SystemMenu> mangerChildren = new TreeSet<>();
        SystemMenu approve = new SystemMenu("0_2_4_1", "0_2_4", "", "部门审批", "/work/manger/approve", "approve");
        SystemMenu budget = new SystemMenu("0_2_4_2", "0_2_4", "", "部门预算", "/work/manger/budget", "budget");
        SystemMenu report = new SystemMenu("0_2_4_3", "0_2_4", "", "部门报表", "/work/manger/report", "report");
        mangerChildren.add(approve);
        mangerChildren.add(budget);
        mangerChildren.add(report);
        manger.setChildren(mangerChildren);
        SystemMenu message = new SystemMenu("0_3", "0", "", "消息中心", "/message", "message", true);
        SystemMenu order = new SystemMenu("0_4", "0", "", "订单管理", "https://www.ele.me", "","_blank");
        set.add(process);
        set.add(work);
        set.add(message);
        set.add(order);
        logger.info(JSON.toJSONString(set));
        return ResultVo.ok().put("menus", set);
    }
}
