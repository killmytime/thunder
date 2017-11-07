<%@ page import="entity.CustomerLogon" %>
<%@ page import="entity.Customers" %><%--
  Created by IntelliJ IDEA.
  User: leiwe
  Date: 2017/7/13
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%--导航栏--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#navbar" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" id="logo" href="#" style="color: #4cae4c"><b>ArtStore</b></a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="../index.jsp">Artworks</a></li>
                <li><a href="../artists.jsp?currentPage=1">Artists</a> </li>
                <% if (session.getAttribute("user") == null) {%>
                <li><a href="../login.jsp">Sign in</a></li>
                <li><a href="../register.jsp">Sign up</a></li>
                <% }else {
                    CustomerLogon customerTmp = ((Customers)session.getAttribute("user")).getCustomerLogon();
                %>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                       aria-haspopup="true" aria-expanded="false">
                        <%=customerTmp.getUserName()%><span class="caret"></span>
                    </a>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="dropdown-header">Basic</li>
                        <li><a href="../profile.jsp">My Profile</a></li>
                        <li><a href="../cart.jsp">My cart</a></li>
                        <li><a href="../records.jsp">My Records</a></li>
                        <li role="separator" class="divider"></li>
                        <li>Others</li>
                        <li><a href="../logout.jsp">Log out</a></li>
                    </ul>
                </li>
                <% }%>
            </ul>
            <form class="navbar-form navbar-right">
                <div class="form-group">
                    <input id="searchInputNav" class="form-control" placeholder="Search">
                </div>
                <a href="../search.jsp"><input id="searchNav" class="btn btn-success" type="button" value="Search"></a>
            </form>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
</body>
</html>
