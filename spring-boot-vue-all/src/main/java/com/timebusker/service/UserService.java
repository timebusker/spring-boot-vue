package com.timebusker.service;

import com.timebusker.model.UserEntity;
import com.timebusker.repository.UserRepository;
import com.timebusker.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: UserServcie
 * @Author: Administrator
 * @Date: 2019/12/20 14:13
 **/
@Service
public class UserService extends AbstractBaseServiceImpl<UserEntity, UserRepository> {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void instance() {
        this.setRepository(userRepository);
    }

    @Override
    public boolean save(UserEntity userEntity) {
        if (userEntity.getId() == null) {
            String password = userEntity.getPassword();
            userEntity.setPassword(password);
        }
        return super.save(userEntity);
    }
}