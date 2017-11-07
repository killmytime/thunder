package servlet;

import entity.Cart;
import entity.Customers;
import service.CartService;
import service.OrderService;
import service.impl.CartServiceImpl;
import service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CartServlet",value = "/cart")
public class CartServlet extends HttpServlet {
    private CartService cartService=new CartServiceImpl();
    private void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("1");
        String artworkId = request.getSession().getAttribute("artworkID").toString();
//        System.out.println(artworkId);
        int customerID=((Customers)(request.getSession().getAttribute("user"))).getCustomerId();
        int artworkID=Integer.parseInt(artworkId);
        Cart cart=cartService.add(customerID,artworkID);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doRequest(request,response);
    }
}
