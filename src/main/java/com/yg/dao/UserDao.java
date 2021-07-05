package com.yg.dao;

import com.yg.pojo.Role;
import com.yg.pojo.User;

import java.sql.Connection;
import java.util.List;

public interface UserDao {
    //获取用户登录信息
    public User getUserLogin(Connection connection, String userCode, String userPassword);

    //用id查询用户信息
    public User queryUserById(Connection connection, int id);

    //修改密码
    public int editPassword(Connection connection, int id, String userPassword);

    //获取用户数量（根据条件获取)
    public int UserCountByTj(Connection connection, int role_id, String userName);

    //获取用列表(根据条件查询 分页查询)
    public List<User> getUserList(Connection connection, String user_name, int role_id, int cuurenPageNo, int pageSize);

    //获取用户权限列表
    public List<Role> getRoleList(Connection connection, int role_id, String role_name);
}
