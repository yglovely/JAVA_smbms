package com.yg.dao;

import com.yg.pojo.Role;
import com.yg.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImp implements UserDao {
    public User getUserLogin(Connection connection, String userCode, String userPassword) {
        String sql = "select * from smbms_user where userCode = ? and userPassword = ?";
        Object[] parms = {userCode, userPassword};
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        User user = new User();
        if (connection != null) {
            try {
                resultSet = BaseDao.excute(connection, resultSet, preparedStatement, sql, parms);
                System.out.println(resultSet);
                while (resultSet.next()) {
                    user.setId(resultSet.getInt("id"));
                    user.setUserCode(resultSet.getString("userCode"));
                    user.setAddress(resultSet.getString("Address"));
                    user.setUserName(resultSet.getString("userName"));
                    user.setBirthday(resultSet.getDate("Birthday"));
                    user.setCreatedBy(resultSet.getInt("CreatedBy"));
                    user.setCreationDate(resultSet.getDate("CreationDate"));
                    user.setGender(resultSet.getInt("Gender"));
                    user.setPhone(resultSet.getString("Phone"));
                    user.setUserPassword(resultSet.getString("userPassword"));
                }
                BaseDao.close(connection, preparedStatement, resultSet);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return user;
    }

    public User queryUserById(Connection connection, int id) {
        String sql = "select * from smbms_user where id = ?";
        Object[] parms = {id};
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        User user = new User();
        try {
            ResultSet res = BaseDao.excute(connection, resultSet, preparedStatement, sql, parms);
            while (res.next()) {
                user = User.set(res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;

    }

    public int editPassword(Connection connection, int id, String userPassword) {
        String sql = "update smbms_user set userPassword = ? where id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet res = null;
        Object[] parms = {userPassword, id};
        int update = 0;
        try {
            update = BaseDao.update(connection, sql, parms);
            BaseDao.close(connection, preparedStatement, res);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return update;
    }

    public int UserCountByTj(Connection connection, int role_id, String userName) {

        String sql = "select count(*) as count from smbms_user u left join smbms_role sr on u.userRole = sr.id where 1=1";
        ArrayList<Object> list = new ArrayList<Object>();
        if (role_id > 0) {
            sql += " and  u.userRole = ?";
            list.add(role_id);
        }
        if (userName != null) {
            sql += " and  u.userName like ?";
            list.add("%" + userName + "%");
        }
        Object[] parms = list.toArray();
        ResultSet res = null;
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            ResultSet ress = BaseDao.excute(connection, res, preparedStatement, sql, parms);
            while (ress.next()) {
                count = ress.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    //获取用户列表 dao层
    public List<User> getUserList(Connection connection, String user_name, int role_id, int cuurenPageNo, int pageSize) {
        String sql = "select * from smbms_user u left join smbms_role sr on u.userRole = sr.id where 1=1 ";

        ArrayList<Object> list = new ArrayList<Object>();
        if (role_id > 0) {
            sql += " and  u.userRole = ?";
            list.add(role_id);
        }
        if (user_name != null) {
            sql += " and  u.userName like ?";
            list.add("%" + user_name + "%");
        }
        if (cuurenPageNo >= 0 && pageSize > 0) {
            sql += " limit ?,?";
            list.add((cuurenPageNo - 1) * pageSize);
            list.add(pageSize);
        }
        Object[] objects = list.toArray();

        ResultSet res = null;
        PreparedStatement preparedStatement = null;
        List<User> users = new ArrayList<User>();
        try {
            ResultSet resultSet = BaseDao.excute(connection, res, preparedStatement, sql, objects);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserCode(resultSet.getString("userCode"));
                user.setAddress(resultSet.getString("Address"));
                user.setUserName(resultSet.getString("userName"));
                user.setUserRoleName(resultSet.getString("roleName"));
                user.setBirthday(resultSet.getDate("Birthday"));
                user.setCreatedBy(resultSet.getInt("CreatedBy"));
                user.setCreationDate(resultSet.getDate("CreationDate"));
                user.setGender(resultSet.getInt("Gender"));
                user.setPhone(resultSet.getString("Phone"));
                user.setUserPassword(resultSet.getString("userPassword"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return users;
    }

    public List<Role> getRoleList(Connection connection, int role_id, String role_name) {
        String sql = "select * from smbms_role ";
        ArrayList<Object> list = new ArrayList<Object>();
        if (role_id > 0) {
            sql += " where id  = ?";
            list.add(role_id);
        }
        if (role_name != null) {
            sql += " where roleNmae = ?";
            list.add(role_name);
        }
        Object[] parms = list.toArray();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        List<Role> roles = new ArrayList<Role>();
        try {
            ResultSet res = BaseDao.excute(connection, resultSet, preparedStatement, sql, parms);
            while (res.next()) {
                Role role = new Role();
                role.setId(res.getInt("id"));
                role.setRoleName(res.getString("roleName"));
                role.setRoleCode(res.getString("roleCode"));
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }
}
