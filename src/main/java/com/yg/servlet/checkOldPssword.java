package com.yg.servlet;

import com.yg.pojo.User;
import com.yg.util.SessionName;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class checkOldPssword extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pw = req.getParameter("oldpassword");
        User user = (User) req.getSession().getAttribute(SessionName.session_name);
        //使用插件
        JSONObject jsonObject = new JSONObject();
       
        PrintWriter out = resp.getWriter();
        if (user.getUserPassword().equals(pw)) {
            jsonObject.put("result", "true");

        } else {
            jsonObject.put("result", "false");
        }
        resp.setContentType("application/json; charset=utf-8");
        out.write(jsonObject.toString());
    }
}
