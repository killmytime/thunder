package servlet;

import entity.Customers;
import service.CustomerService;
import service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Created by leiwe on 2017/7/10.
 */
@WebServlet(value = "/login", name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    private CustomerService customerService = new CustomerServiceImpl();

    private void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Customers customers = customerService.login(username, password);
        if (customers != null) {
        request.getSession().setAttribute("user", customers);
            request.getSession().setAttribute("success","<script>\n" +
                    "    alert(\"Login successfully.\");\n" +
                    "</script>");
        response.sendRedirect("./index.jsp");
        //javascript:history.go(-1)跳转上一页
    }
        else {
        //response.getWriter().println("username or password is wrong.");
        request.getSession().setAttribute("error","<script>\n" +
                "    alert(\"username or password is wrong.\");\n" +
                "</script>");
            response.sendRedirect("./login.jsp");
    }
}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doRequest(request, response);
    }
}

