package com.yg.servlet;

import com.mysql.cj.util.StringUtils;
import com.yg.pojo.User;
import com.yg.service.UserServiceImpl;
import com.yg.util.SessionName;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class editPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserServiceImpl userService = new UserServiceImpl();
        User session_user = (User) req.getSession().getAttribute(SessionName.session_name);
        int id = session_user.getId();
        if (id != 0) {
            User user = userService.queryUserByid(id);
            req.setAttribute("userId", user.getId());
            req.getRequestDispatcher("/jsp/pwdmodify.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "未知错误，请重新登录");
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取传过来的参数
        User session_user = (User) req.getSession().getAttribute(SessionName.session_name);
        int id = session_user.getId();
        String old_password = req.getParameter("oldpassword");
        String password = req.getParameter("newpassword");


        if (!old_password.equals(password) || !StringUtils.isNullOrEmpty(password)) {
            //进行修改操作
            UserServiceImpl userService = new UserServiceImpl();
            int i = userService.editPassword(id, password);
            if (i > 0) {
                //修改成功
                req.getSession().removeAttribute(SessionName.session_name);
                req.setAttribute("message", "密码修改成功，请重新退出登录");
            } else {
                //修改失败
                req.setAttribute("message", "修改失败，请重新登录修改尝试");

            }
        } else {
            req.setAttribute("message", "新密码不能跟旧密码重复，请重新输入");

        }
        req.getRequestDispatcher("/jsp/pwdmodify.jsp").forward(req, resp);

    }
}
