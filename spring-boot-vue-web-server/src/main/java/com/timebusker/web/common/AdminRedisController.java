package com.timebusker.web.common;

import com.timebusker.common.redis.RedisService;
import com.timebusker.common.web.ResultVo;
import com.timebusker.web.AbstractBaseController;
import com.timebusker.model.vo.RedisVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @DESC:AdminRedisController:redis缓存管理
 * @author:timebusker
 * @date:2019/4/30
 */
@RestController
@RequestMapping("/redis")
public class AdminRedisController extends AbstractBaseController {

    @Autowired
    private RedisService redisService;

    @RequestMapping("/list")
    public ResultVo getRedis(String key) {
        List<RedisVo> list = new ArrayList<>();
        Set<String> keys = redisService.keys(key);
        for (String per : keys) {
            RedisVo vo = new RedisVo(per, redisService);
        }
        return ResultVo.ok().put("list", list);
    }

    @RequestMapping("/create")
    public ResultVo create() {
        return ResultVo.ok();
    }

    @RequestMapping("/update")
    public ResultVo update() {
        return ResultVo.ok();
    }

    @RequestMapping("/delete")
    public ResultVo delete() {
        return ResultVo.ok();
    }

    @RequestMapping("/flushdb")
    public ResultVo deleteAll() {
        return ResultVo.ok();
    }

}
