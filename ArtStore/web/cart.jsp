<%@ page import="entity.Cart" %>
<%@ page import="entity.Customers" %><%--
  Created by IntelliJ IDEA.
  User: leiwe
  Date: 2017/7/19
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="./part/quotes.txt"/>
    <script src="./js/cart.js"></script>
</head>
<body>
<% if (session.getAttribute("user") != null) {%>
<%int customerID=((Customers)(request.getSession().getAttribute("user"))).getCustomerId();%>
<jsp:useBean id="cartService" class="service.impl.CartServiceImpl"/>
<jsp:useBean id="artworkService" class="service.impl.ArtworkServiceImpl"/>
<header class="marginBottom">
    <jsp:include page="./part/nav.jsp" flush="true"/>
</header>
<jsp:include page="./part/navSide.jsp"/>
<div class="col-xs-12 col-md-8">
    <h2>My Cart</h2>
    <form>
    <table class="table">
        <tr>
            <td>Select</td><td>CartID</td><td>DateCreated</td><td>Artwork</td>
        </tr>
        <% for (Cart cart : cartService.getByCustomer(customerID)) { %>
        <tr>
            <td><input type="checkbox" name="checkbox" value="<%=cart.getCartId()%>"></td>
            <td><%=cart.getCartId()%></td>
            <td><%=cart.getDateCreated()%></td>
            <td><a href="./details.jsp?ArtworkID=<%=artworkService.getOne(cart.getArtworkId()).getArtWorkId()%>"><%=artworkService.getOne(cart.getArtworkId()).getTitle()%></a></td>
        </tr>
        <% } %>
    </table>
        <button id="pay" class="btn btn-lg btn-primary btn-block" type="button">Pay</button>
        <button id="delete" class="btn btn-lg btn-danger btn-block" type="button" >Delete</button>
    </form>
</div>
<footer>
    <jsp:include page="./part/footer.jsp" flush="true"/>
</footer>
<%}else{ response.sendRedirect("./login.jsp");}%>
</body>
</html>
