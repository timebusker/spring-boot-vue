package com.timebusker.web;

import com.timebusker.common.web.ResultVO;
import com.timebusker.model.SystemEntity;
import com.timebusker.service.SystemService;
import com.timebusker.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: SystemController
 * @Author: Administrator
 * @Date: 2019/12/26 17:27
 **/
@RestController
@RequestMapping("/system")
public class SystemController extends AbstractBaseController {

    @Autowired
    private SystemService systemService;

    @RequestMapping("/list")
    public ResultVO getSystemList() {
        List<SystemEntity> list = new ArrayList<>();
        list = systemService.query(new Query());
        return ResultVO.ok().put("data", list);
    }
}
