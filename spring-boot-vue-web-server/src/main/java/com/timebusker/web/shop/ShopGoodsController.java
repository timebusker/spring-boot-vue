package com.timebusker.web.shop;

import com.timebusker.common.web.ResultVo;
import com.timebusker.model.shop.ShopGoods;
import com.timebusker.service.shop.ShopGoodsService;
import com.timebusker.web.AbstractBaseController;
import com.timebusker.web.vo.QueryParamsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @DESC:GoodsController:商品控制器
 * @author:timebusker
 * @date:2019/4/17
 */
@RestController
@RequestMapping("/shop/goods")
public class ShopGoodsController extends AbstractBaseController {

    @Autowired
    private ShopGoodsService shopGoodsService;

    @RequestMapping(value = "/save", method = RequestMethod.PUT)
    public ResultVo saveGoods(@RequestBody ShopGoods goods) {
        shopGoodsService.save(goods);
        return ResultVo.ok().put("goods", goods);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResultVo list(@RequestBody QueryParamsVo params) {
        List<ShopGoods> list = shopGoodsService.queryGoods(params);
        return ResultVo.ok().put("list", list).put("params", params);
    }
}
