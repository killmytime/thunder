package servlet;

import service.CartService;
import service.impl.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CartDeleteServlet",value = "/cartDelete")
public class CartDeleteServlet extends HttpServlet {
    private CartService cartService=new CartServiceImpl();
    private void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] cartList=request.getParameterValues("cartList");
        for (String CartID:cartList) {
            int cartID = Integer.parseInt(CartID);
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
