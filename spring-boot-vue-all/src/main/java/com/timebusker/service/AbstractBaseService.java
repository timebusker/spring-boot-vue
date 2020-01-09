package com.timebusker.service;

import com.timebusker.utils.Query;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

/**
 * @Description: AbstractBaseService
 * @Author: Administrator
 * @Date: 2019/12/20 13:26
 **/
public interface AbstractBaseService<DTO> {

    boolean save(Collection<DTO> set);

    boolean save(DTO dto);

    boolean update(DTO dto);

    boolean updateByParams(Query params);

    boolean delete(DTO dto);

    boolean delete(String idx);

    DTO query(String idx);

    Page<DTO> queryWithPage(Query params);
}
