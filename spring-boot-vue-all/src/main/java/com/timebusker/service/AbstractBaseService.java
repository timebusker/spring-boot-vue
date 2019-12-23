package com.timebusker.service;

import com.timebusker.utils.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

/**
 * @Description: AbstractBaseService
 * @Author: Administrator
 * @Date: 2019/12/20 13:26
 **/
public interface AbstractBaseService<DTO> {

    boolean save(Set<DTO> set);

    boolean save(DTO dto);

    boolean update(DTO dto);

    boolean updateByParams(Query params);

    boolean delete(DTO dto);

    boolean delete(String idx);

    DTO query(String idx);

    DTO queryByParams(Query params);

    List<DTO> query(Query params);

    Page<DTO> query(Query params, Pageable pageable);

}
