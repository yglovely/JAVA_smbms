package com.yg.servlet;

import com.yg.pojo.Role;
import com.yg.pojo.User;
import com.yg.service.UserServiceImpl;
import com.yg.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class userList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        query(req, resp);

    }

    //查询信息
    public static void query(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("queryname");
        String role_id = req.getParameter("queryUserRole");
        String index_in = req.getParameter("currentPageNo");
        username = username != null ? username : null;
        int roleid = role_id == null ? 0 : Integer.parseInt(role_id);
        int index = index_in == null ? 1 : Integer.parseInt(index_in);
        //实例化page
        PageUtil pageUtil1 = new PageUtil();
        pageUtil1.setIndex(index);
        UserServiceImpl uimp = new UserServiceImpl();
        //获取用户总数量
        int count = uimp.selectUserCount(roleid, username);
        pageUtil1.setIndex(index);
        pageUtil1.setSize(pageUtil1.getSize());
        pageUtil1.setTotal(count);
        //获取用户列表
        List<User> userList = uimp.getUserList(username, roleid, pageUtil1.getIndex(), pageUtil1.getSize());

        Object[] users = userList.toArray();
        req.setAttribute("userlist", users);
        //获取权限列表
        List<Role> userRole = uimp.getUserRole(0, null);
        HashMap<String, Object> pages = new HashMap<String, Object>();
        pages.put("list", users);
        pages.put("count", pageUtil1.getTotal());
        pages.put("index", index);
        pages.put("total", pageUtil1.getTotalpage());
        req.setAttribute("roleList", userRole.toArray());
        req.setAttribute("page", pages);
        try {
            req.getRequestDispatcher("/jsp/userlist.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("queryname");
        String role_id = req.getParameter("queryUserRole");
        String index_in = req.getParameter("currentPageNo");
        username = username != null ? username : null;
        int roleid = role_id == null ? 0 : Integer.parseInt(role_id);
        int index = index_in == null ? 1 : Integer.parseInt(index_in);
        //实例化page
        PageUtil pageUtil1 = new PageUtil();
        pageUtil1.setIndex(index);
        UserServiceImpl uimp = new UserServiceImpl();
        //获取用户总数量
        int count = uimp.selectUserCount(roleid, username);
        pageUtil1.setIndex(index);
        pageUtil1.setSize(pageUtil1.getSize());
        pageUtil1.setTotal(count);
        //获取用户列表
        List<User> userList = uimp.getUserList(username, roleid, pageUtil1.getIndex(), pageUtil1.getSize());

        Object[] users = userList.toArray();
        req.setAttribute("userlist", users);
        //获取权限列表
        List<Role> userRole = uimp.getUserRole(0, null);
        HashMap<String, Object> pages = new HashMap<String, Object>();
        pages.put("list", users);
        pages.put("count", pageUtil1.getTotal());
        pages.put("index", pageUtil1.getIndex());
        pages.put("total", pageUtil1.getTotalpage());
        req.setAttribute("roleList", userRole.toArray());
        req.setAttribute("page", pages);

    }
}
