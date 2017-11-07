package test;

//import entity.Order;
//import service.CartService;
//import service.OrderService;
//import service.impl.CartServiceImpl;
//import service.impl.OrderServiceImpl;
//
//import java.sql.Timestamp;
//
//public class orderTest {
//    public static void main(String[] args) {
//        OrderService orderService=new OrderServiceImpl();
//        CartService cartService=new CartServiceImpl();
//        Order order;
//        order=orderService.getByCustomer(60).get(0);
//        int orderID=order.getOrderId();
//        int orderCustomer=order.getCustomerId();
//        Timestamp orderCompleted=order.getDateCompleted();
//        Timestamp orderCreated=order.getDateCreated();
//        System.out.println(order.getDateCreated());
//        System.out.println(order.getOrderId());
//        System.out.println(order.getDateCompleted());
//        //System.out.println(cartService.getByArtwork(order.getArtworkID()).getDateCreated());
//        System.out.println(orderService.getByOrderID(orderID).get(0).getArtWorkId());
//
//    }
//}
