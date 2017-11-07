<%@ page import="entity.CustomerLogon" %>
<%@ page import="entity.Customers" %>
<%@ page import="dao.CustomerDao" %>
<%@ page import="dao.DaoFactory.DaoFactory" %><%--
  Created by IntelliJ IDEA.
  User: leiwe
  Date: 2017/7/21
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%--修改个人信息的页面--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="./part/quotes.txt"/>
    <script src="./js/modify.js"></script>
</head>
<body>
<% if (session.getAttribute("user") != null) {%>
<header class="marginBottom">
    <jsp:include page="./part/nav.jsp" flush="true"/>
</header>
    <jsp:include page="./part/navSide.jsp"/>
    <div class="col-xs-12 col-md-8">
        <form id="modifyForm">
        <table style="margin-top: 30px;margin-left: 100px" class="table">
            <%CustomerLogon customerTmp = ((Customers)session.getAttribute("user")).getCustomerLogon();%>
            <%CustomerDao customer= DaoFactory.getCustomerDaoInstance();%>
            <tr>
                <td>First Name</td>
                <td><input type="text" name="firstName" placeholder="<%=customer.getOne(customerTmp.getCustomerId()).getFirstName()%>"></td>
            </tr>
            <tr>
                <td>Last Name</td>
                <td><input type="text" name="lastName" placeholder="<%=customer.getOne(customerTmp.getCustomerId()).getLastName()%>"></td>
            </tr>
            <tr>
                <td>Address</td>
                <td><input type="text" name="address" placeholder="<%=customer.getOne(customerTmp.getCustomerId()).getAddress()%>"></td>
            </tr>
            <tr>
                <td>City</td>
                <td><input type="text" name="city" placeholder="<%=customer.getOne(customerTmp.getCustomerId()).getCity()%>"></td>
            </tr>
            <tr>
                <td>Region</td>
                <td><input type="text" name="region" placeholder="<%=customer.getOne(customerTmp.getCustomerId()).getRegion()%>"></td>
            </tr>
            <tr>
                <td>Country</td>
                <td><input type="text" name="country" placeholder="<%=customer.getOne(customerTmp.getCustomerId()).getCountry()%>"></td>
            </tr>
            <tr>
                <td>Postal</td>
                <td><input type="text" name="postal" placeholder="<%=customer.getOne(customerTmp.getCustomerId()).getPostal()%>"></td>
            </tr>
            <tr>
                <td>Phone</td>
                <td><input type="number" name="phone" placeholder="<%=customer.getOne(customerTmp.getCustomerId()).getPhone()%>"></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input type="email" name="email" placeholder=" <%=customer.getOne(customerTmp.getCustomerId()).getEmail()%>"></td>
            </tr>
        </table>
            <button id="save" class="btn btn-lg btn-primary btn-block" type="button">Save</button>
        </form>
    </div>

<footer>
    <jsp:include page="./part/footer.jsp" flush="true"/>
</footer>
<%}else{ response.sendRedirect("./login.jsp");}%>
</body>
</html>
