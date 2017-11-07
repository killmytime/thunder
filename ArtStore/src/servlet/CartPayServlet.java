package servlet;

import entity.*;
import service.ArtworkService;
import service.CartService;
import service.OrderService;
import service.impl.ArtworkServiceImpl;
import service.impl.CartServiceImpl;
import service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(name = "CartPayServlet",value = "/cartPay")
public class CartPayServlet extends HttpServlet {
    private CartService cartService=new CartServiceImpl();
    private OrderService orderService=new OrderServiceImpl();
    private ArtworkService artworkService=new ArtworkServiceImpl();
    private void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] cartList=request.getParameterValues("cartList");
        int customerID=((Customers)(request.getSession().getAttribute("user"))).getCustomerId();
        for (String CartID:cartList) {
            int cartID = Integer.parseInt(CartID);
            Cart cart=cartService.getOne(cartID);
            Order order=new Order();
            order.setCustomerId(customerID);
            order.setDateCreated(cart.getDateCreated());
            order.setDateCompleted(new Timestamp(System.currentTimeMillis()));
            order.setArtworkID(cart.getArtworkId());
            orderService.add(order);
            Artwork artwork=artworkService.getOne(cart.getArtworkId());
            artwork.setStatus(1);
            artworkService.update(artwork);
            cartService.delete(cartID);
            response.sendRedirect("./cart.jsp");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doRequest(request, response);
    }
}
