<%@ page import="entity.Order" %>
<%@ page import="entity.Customers" %>
<%--
  Created by IntelliJ IDEA.
  User: leiwe
  Date: 2017/7/17
  Time: 0:30
  To change this template use File | Settings | File Templates.
--%>
<%--订单记录页面--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="./part/quotes.txt"/>
</head>
<body>
<% if (session.getAttribute("user") != null) {%>
<jsp:useBean id="orderService" class="service.impl.OrderServiceImpl"/>
<jsp:useBean id="cartService" class="service.impl.CartServiceImpl"/>
<jsp:useBean id="artworkService" class="service.impl.ArtworkServiceImpl"/>
<header class="marginBottom">
    <jsp:include page="./part/nav.jsp" flush="true"/>
</header>
<jsp:include page="./part/navSide.jsp"/>
<div class="col-xs-12 col-md-8">
    <h2>Records</h2>
    <table class="table">
        <tr>
            <td>OrderID</td><td>DateCompleted</td><td>Artwork</td>
        </tr>
        <% int customerID=((Customers)session.getAttribute("user")).getCustomerId(); %>
        <% for (Order order : orderService.getByCustomer(customerID)) { %>
        <tr>
            <td><%=order.getOrderId()%></td>
            <td><%=order.getDateCreated()%></td>
            <td><a href="./details.jsp?ArtworkID=<%=order.getArtworkID()%>" ><%=artworkService.getOne(order.getArtworkID()).getTitle()%></a></td>
        </tr>
        <% }%>
    </table>
</div>
<footer>
    <jsp:include page="./part/footer.jsp" flush="true"/>
</footer>
<%}else{ response.sendRedirect("./login.jsp");}%>
</body>
</html>
