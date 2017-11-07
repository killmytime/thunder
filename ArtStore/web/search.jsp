<%--
  Created by IntelliJ IDEA.
  User: leiwe
  Date: 2017/7/17
  Time: 0:27
  To change this template use File | Settings | File Templates.
--%>
<%--搜索页面--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <jsp:include page="./part/quotes.txt"/>
</head>
<body>
<header class="marginBottom">
<jsp:include page="./part/nav.jsp"/>
</header>
<%String search = request.getParameter("search");
if (search==null){
    search="";
}
%>
<div class="container">
    <div class="row">
        <div class="col-md-8">
            <h3>filter</h3>
            <input  placeholder="Keywords" value="<%=search%>" class="form-control" id="searchInput" autofocus>
            <div class="table-responsive">
                <table class="table">
                    <tr>
                        <td>
                            <input type="radio" name="filter" id="filterTitle" value="title" checked>
                            <label for="filterTitle">By Title</label>
                        </td>
                        <td>
                            <input type="radio" name="filter" id="filterDescription" value="description">
                            <label for="filterDescription">By Description</label>
                        </td>
                        <td>
                            <input type="radio" name="filter" id="filterArtist" value="artist">
                            <label for="filterArtist">By Artist's Name</label>
                        </td>
                    </tr>
                    <tr>
                        <td>Sort By</td>
                        <td>
                        <input type="radio" name="sort" id="sortedByPrice" value="price">
                        <label for="sortedByPrice">By Price</label>
                    </td>
                        <td>
                        <input type="radio" name="sort" id="sortedByDate" value="date">
                        <label for="sortedByDate">By Date</label>
                        </td>
                    </tr>
                </table>
                <button type="button" class="btn btn-danger" id="filter">filter</button>
            </div>
        </div>
    </div>
    <div id="searchResult" class="container-fluid">
    </div>
</div>
<% ; %>
<script src="./js/search.js"></script>
<footer>
<jsp:include page="./part/footer.jsp"/>
</footer>
</body>
</html>
