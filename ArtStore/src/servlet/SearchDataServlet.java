/**
 * 神TM无语，不知道问题出在哪里，感觉代码完全没错，值也可以传递，就是不管在servlet跳转页面还是回调去跳转页面都不可以。。心态炸了。。。
 */

//package servlet;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet(name = "SearchDataServlet",value = "/searchData")
//public class SearchDataServlet extends HttpServlet {
//
//    private void doRequest (HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String search = request.getParameter("search");
//        System.out.println(search);
////        response.sendRedirect("./search.jsp?search="+search+"");
//        response.sendRedirect("./index.jsp");
//    }
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        doRequest(request,response);
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        doRequest(request,response);
//    }
//}
//