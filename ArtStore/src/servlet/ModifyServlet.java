package servlet;

import entity.Customers;
import service.CustomerService;
import service.impl.CustomerServiceImpl;
import util.Transfer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/modify", name = "ModifyServlet")
public class ModifyServlet extends HttpServlet {
    private CustomerService customerService = new CustomerServiceImpl();

    private void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Customers customers = (Customers) request.getSession().getAttribute("user");
        String firstName = request.getParameter("firstName");
        Transfer transfer=new Transfer();

        customers.setFirstName(transfer.transfer(firstName));
        System.out.println(firstName);
        String lastName = request.getParameter("lastName");
        customers.setLastName(lastName);
        String address = request.getParameter("address");
        customers.setAddress(address);
        String city = request.getParameter("city");
        customers.setCity(city);
        String region = request.getParameter("region");
        customers.setRegion(region);
        String country = request.getParameter("country");
        customers.setCountry(country);
        String postal = request.getParameter("postal");
        customers.setPostal(postal);
        String phone = request.getParameter("phone");
        customers.setPhone(phone);
        String email = request.getParameter("email");
        customers.setEmail(email);
        customerService.update(customers);
        request.getSession().setAttribute("user", customers);
        request.getSession().setAttribute("user", customers);
        response.sendRedirect("\"./index.jsp\"");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doRequest(request, response);
    }
}
