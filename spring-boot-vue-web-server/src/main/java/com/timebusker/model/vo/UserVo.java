package com.timebusker.model.vo;

/**
 * @DESC:UserVo
 * @author:timebusker
 * @date:2019/3/21
 */
public class UserVo {

    private String userName;
    private String password;
    private String checkNum;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(String checkNum) {
        this.checkNum = checkNum;
    }
}
