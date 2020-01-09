package com.timebusker.web;

import com.timebusker.common.web.ResultVO;
import com.timebusker.model.SystemConfigEntity;
import com.timebusker.service.SystemConfigService;
import com.timebusker.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @Description: SystemConfigController
 * @Author: Administrator
 * @Date: 2020/1/6 20:21
 **/
@RestController
@RequestMapping("/config")
public class SystemConfigController extends AbstractBaseController {

    @Autowired
    private SystemConfigService systemConfigService;

    @RequestMapping("/list")
    public ResultVO list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        Page<SystemConfigEntity> page = systemConfigService.queryWithPage(query);
        return ResultVO.ok().put("data", page.getContent())
                .put("totalSize", page.getTotalElements())
                .put("totalPage", page.getTotalPages())
                .put("currentPage", query.getCurrentPage())
                .put("pageSize", query.getPageSize());
    }

    @RequestMapping("/save")
    public ResultVO save(SystemConfigEntity config) {
        config.setUpdateTime(LocalDateTime.now());
        systemConfigService.save(config);
        return ResultVO.ok();
    }

    @RequestMapping("/delete")
    public ResultVO delete(SystemConfigEntity config) {
        systemConfigService.delete(config);
        return ResultVO.ok();
    }
}
