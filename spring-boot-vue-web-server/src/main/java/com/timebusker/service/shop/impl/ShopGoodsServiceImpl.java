package com.timebusker.service.shop.impl;

import com.github.pagehelper.PageHelper;
import com.timebusker.mapper.shop.ShopGoodsMapper;
import com.timebusker.model.shop.ShopGoods;
import com.timebusker.service.AbstractBaseService;
import com.timebusker.service.shop.ShopGoodsService;
import com.timebusker.web.vo.QueryParamsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @DESC:GoodsServiceImpl
 * @author:timebusker
 * @date:2019/4/17
 */
@Service
public class ShopGoodsServiceImpl extends AbstractBaseService implements ShopGoodsService {

    @Autowired
    private ShopGoodsMapper shopGoodsMapper;


    @Override
    public ShopGoods save(ShopGoods goods) {
        goods.setId(sequenceId.nextId());
        shopGoodsMapper.insert(goods);
        return goods;
    }

    @Override
    public ShopGoods update(ShopGoods goods) {
        return null;
    }

    @Override
    public ShopGoods delete(ShopGoods goods) {
        return null;
    }

    @Override
    public List<ShopGoods> queryGoods(QueryParamsVo params) {
        List<ShopGoods> list = new ArrayList<>();
        try {
            PageHelper.startPage(params.getCurrPage(), params.getPageSize());
            list = shopGoodsMapper.selectAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
}
