package com.timebusker.model;

import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description: UserAuhorToken
 * @Author: Administrator
 * @Date: 2020/1/9 22:38
 **/
//@Data
//@Entity
//@Table(name = "tb_user_author_token")
public class UserAuthorToken implements Serializable {

    @Id
    private String loginName;
    private String series;
    private String token;
    private LocalDateTime lastUsedTime;

}
