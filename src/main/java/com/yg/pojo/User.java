package com.yg.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class User {

    private Integer id; //id
    private String userCode; //用户编码
    private String userName; //用户名称
    private String userPassword; //用户密码
    private Integer gender;  //性别
    private Date birthday;  //出生日期
    private String phone;   //电话
    private String address; //地址
    private Integer userRole;    //用户角色
    private Integer createdBy;   //创建者
    private Date creationDate; //创建时间
    private Integer modifyBy;     //更新者
    private Date modifyDate;   //更新时间

    private Integer age;//年龄

    private String userRoleName;    //用户角色名称


    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    public Integer getAge() {
        return new Date().getYear() - birthday.getYear();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public User() {
        super();
    }

    public User(Integer id, String userCode, String userName,
                String userPassword, Integer gender, Date birthday, String phone,
                String address, Integer userRole, Integer createdBy,
                Date creationDate, Integer modifyBy, Date modifyDate,
                String userRoleName) {
        super();
        this.id = id;
        this.userCode = userCode;
        this.userName = userName;
        this.userPassword = userPassword;
        this.gender = gender;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.userRole = userRole;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
        this.userRoleName = userRoleName;
    }

    public static User set(ResultSet resultSet) {
        User user = new User();
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", userCode=" + userCode + ", userName="
                + userName + ", userPassword=" + userPassword + ", gender="
                + gender + ", birthday=" + birthday + ", phone=" + phone
                + ", address=" + address + ", userRole=" + userRole
                + ", createdBy=" + createdBy + ", creationDate=" + creationDate
                + ", modifyBy=" + modifyBy + ", modifyDate=" + modifyDate
                + ", age=" + age + ", userRoleName=" + userRoleName + "]";
    }

}
