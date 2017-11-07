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

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    private CustomerService customerService = new CustomerServiceImpl();

    private void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("passwordConfirm");
        if (password.equals(passwordConfirm)) {
        Customers customers = customerService.register(username, password,email);
        if (customers != null) {
            customerService.update(customers);
            request.getSession().setAttribute("user", customers);
            // json 表达式
            response.getWriter().print("{\"result\": \"OK\"}");
        } else {
            // json 表达式
            response.getWriter().print("{\"result\": \"ERROR\", \"error\": \"userName is already existed.\"}");
        }
    }else {
        // json 表达式
        response.getWriter().print("{\"result\": \"ERROR\", \"error\": \"Confirm password must be the same with your password.\"}");
    }
}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doRequest(request, response);
    }
}
