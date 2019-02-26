package com.landmine.code;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * author: dulei
 * date: 18-11-6
 * desc:
 */
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        PrintWriter out =resp.getWriter();
        out.println("<html>");
        out.println("<head><title>Servlet展示页</title></head>");
        out.println("<body>name:"  + "<br/> sex: "  +"</body>");
        out.println("</html>");
    }
}
