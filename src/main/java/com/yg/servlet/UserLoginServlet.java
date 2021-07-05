package com.yg.servlet;

import com.yg.pojo.User;
import com.yg.service.UserServiceImpl;
import com.yg.util.SessionName;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_code = req.getParameter("userCode");
        String user_password = req.getParameter("userPassword");
        if (user_code != null && user_password != null) {
//不为空 执行下面代码  查询数据库 是否匹配
            UserServiceImpl userService = new UserServiceImpl();
            User user = userService.login(user_code, user_password);
            if (user.getUserName() == null) {
                req.setAttribute("error", "用户名或密码错误");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            } else {
                //添加session信息
                req.getSession().setAttribute(SessionName.session_name, user);
                resp.sendRedirect(this.getServletContext().getContextPath() + "/jsp/frame.jsp");
            }

        } else {
            //跳转到登录页面
            req.setAttribute("error", "用户名或密码错误");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
