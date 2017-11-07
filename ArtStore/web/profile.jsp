<%@ page import="entity.Customers" %>
<%@ page import="entity.CustomerLogon" %>
<%@ page import="dao.DaoFactory.DaoFactory" %>
<%@ page import="dao.CustomerDao" %>
<%--
  Created by IntelliJ IDEA.
  User: leiwe
  Date: 2017/7/17
  Time: 0:31
  To change this template use File | Settings | File Templates.
--%>
<%--个人信息页面--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="./part/quotes.txt"/>
</head>
<body>
<% if (session.getAttribute("user") != null) {%>
<header class="marginBottom">
    <jsp:include page="./part/nav.jsp" flush="true"/>
</header>
    <jsp:include page="./part/navSide.jsp"/>
    <div class="col-xs-12 col-md-8">
            <table style="margin-top: 30px;margin-left: 100px" class="table">
                <%CustomerLogon customerTmp = ((Customers) session.getAttribute("user")).getCustomerLogon();%>
                <%CustomerDao customer = DaoFactory.getCustomerDaoInstance();%>
                <tr>
                    <td>Name</td>
                    <td><%=customer.getOne(customerTmp.getCustomerId()).getFirstName() + "   " + customer.getOne(customerTmp.getCustomerId()).getLastName()%>
                    </td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td><%=customer.getOne(customerTmp.getCustomerId()).getAddress()%>
                    </td>
                </tr>
                <tr>
                    <td>City</td>
                    <td><%=customer.getOne(customerTmp.getCustomerId()).getCity()%>
                    </td>
                </tr>
                <tr>
                    <td>Region</td>
                    <td><%=customer.getOne(customerTmp.getCustomerId()).getRegion()%>
                    </td>
                </tr>
                <tr>
                    <td>Country</td>
                    <td><%=customer.getOne(customerTmp.getCustomerId()).getCountry()%>
                    </td>
                </tr>
                <tr>
                    <td>Postal</td>
                    <td><%=customer.getOne(customerTmp.getCustomerId()).getPostal()%>
                    </td>
                </tr>
                <tr>
                    <td>Phone</td>
                    <td><%=customer.getOne(customerTmp.getCustomerId()).getPhone()%>
                    </td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><%=customer.getOne(customerTmp.getCustomerId()).getEmail()%>
                    </td>
                </tr>
            </table>
            <a href="./modify.jsp"><input type="button" placeholder="Modify" value="modify" class="btn btn-primary" style="margin-left: 27vw;"></a>
    </div>

<footer>
    <jsp:include page="./part/footer.jsp" flush="true"/>
</footer>
<%}else{ response.sendRedirect("./login.jsp");}%>
</body>
</html>

