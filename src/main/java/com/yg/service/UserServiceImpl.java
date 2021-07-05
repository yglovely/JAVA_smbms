package com.yg.service;


import com.yg.dao.BaseDao;
import com.yg.dao.UserDao;
import com.yg.dao.UserDaoImp;
import com.yg.pojo.Role;
import com.yg.pojo.User;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserDao userDao;

    static {
        userDao = new UserDaoImp();
        BaseDao baseDao = new BaseDao();
    }

    public User login(String UserCode, String password) {
        Connection connection = BaseDao.getConnection();
        User user = null;
        try {
            user = userDao.getUserLogin(connection, UserCode, password);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.close(connection, null, null);
        }
        return user;
    }

    public User queryUserByid(int id) {
        Connection connection = BaseDao.getConnection();
        UserDaoImp userDaoImp = new UserDaoImp();
        User user = userDaoImp.queryUserById(connection, id);
        BaseDao.close(connection, null, null);
        return user;
    }

    public int editPassword(int id, String userpassword) {
        Connection connection = BaseDao.getConnection();
        UserDaoImp userDaoImp = new UserDaoImp();
        int i = userDaoImp.editPassword(connection, id, userpassword);
        BaseDao.close(connection, null, null);
        return i;
    }

    public int selectUserCount(int role_id, String user_name) {
        UserDaoImp userDaoImp = new UserDaoImp();
        Connection connection = BaseDao.getConnection();
        int count = userDaoImp.UserCountByTj(connection, role_id, user_name);
        BaseDao.close(connection, null, null);
        return count;
    }

    //获取用户列表

    public List<User> getUserList(String user_name, int role_id, int cuurenPageNo, int pageSize) {
        Connection connection = BaseDao.getConnection();

        UserDaoImp userDaoImp = new UserDaoImp();
        List<User> userList = userDaoImp.getUserList(connection, user_name, role_id, cuurenPageNo, pageSize);
        BaseDao.close(connection, null, null);

        return userList;
    }

    public List<Role> getUserRole(int role_id, String role_name) {
        Connection connection = BaseDao.getConnection();
        UserDaoImp userDaoImp = new UserDaoImp();
        List<Role> roleList = userDaoImp.getRoleList(connection, role_id, role_name);
        BaseDao.close(connection, null, null);
        return roleList;
    }

    @Test
    public void text() {


    }

}
