package com.timebusker.service.shop;

import com.timebusker.model.shop.ShopGoods;
import com.timebusker.model.vo.QueryParamsVo;

import java.util.List;

/**
 * @DESC:GoodsService
 * @author:timebusker
 * @date:2019/4/17
 */
public interface ShopGoodsService {

    ShopGoods save(ShopGoods goods);

    ShopGoods update(ShopGoods goods);

    ShopGoods delete(ShopGoods goods);

    List<ShopGoods> queryGoods(QueryParamsVo params);
}
