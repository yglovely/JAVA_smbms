package com.yg.service;

import com.yg.pojo.Role;
import com.yg.pojo.User;

import java.util.List;

public interface UserService {
    public User login(String UserCode, String password);

    //获取用户byid
    public User queryUserByid(int id);

    //修改密码
    public int editPassword(int id, String userpassword);

    //获取用户数量（根据条件）
    public int selectUserCount(int role_id, String user_name);

    //获取用户列表
    public List<User> getUserList(String user_name, int role_id, int cuurenPageNo, int pageSize);

    //获取权限组列表
    public List<Role> getUserRole(int role_id, String role_name);
}
