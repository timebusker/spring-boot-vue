package com.timebusker.web.vo;

import lombok.*;

/**
 * @DESC:UserVo
 * @author:timebusker
 * @date:2019/3/21
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {

    private String userName;
    private String password;
    private String checkNum;
}
