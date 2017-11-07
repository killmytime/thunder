<%@ page import="entity.Customers" %><%--
  Created by IntelliJ IDEA.
  User: leiwe
  Date: 2017/7/17
  Time: 0:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="./part/quotes.txt"/>
    <link rel="stylesheet" href="./css/details.css">
</head>
<body>
<%StringBuilder url=new StringBuilder(request.getServerName()+":"+request.getLocalPort()+request.getRequestURI());
    if (request.getQueryString()==null||"".equals(request.getQueryString())){
//        url.append("?ArtworkID=1");
//        response.sendRedirect(url.toString());
        //重定向，本来打算直接在原来url上改，不知道为啥迷之不能读取，转为string之后还是不行，干脆手写了
        //然而还是只是简单的重定向，逻辑上还是有问题
        response.sendRedirect("./details.jsp?ArtworkID=1");
    }
    else {
%>
<%--获取图片及相关信息，调用service--%>
<jsp:useBean id="artworkService" class="service.impl.ArtworkServiceImpl"/>
<jsp:useBean id="genreService" class="service.impl.GenreServiceImpl"/>
<jsp:useBean id="artistService" class="service.impl.ArtistServiceImpl"/>
<jsp:useBean id="subjectService" class="service.impl.SubjectServiceImpl"/>
<jsp:useBean id="cartService" class="service.impl.CartServiceImpl"/>
<jsp:useBean id="orderService" class="service.impl.OrderServiceImpl"/>
<%
    String ArtworkID = request.getParameter("ArtworkID");//用request得到  
    if (ArtworkID.matches("[0-9]+")) {
        int artworkId = Integer.parseInt(ArtworkID);
%>

<header class="marginBottom">
    <jsp:include page="./part/nav.jsp" flush="true"/>
</header>
<!--重定向还没做后期需要加-->
<%session.setAttribute("artworkID", artworkId);%>
<section>
    <div class="detailFrame">
        <div><h3><%=artworkService.getOne(artworkId).getTitle()%>
        </h3></div>
        <div class="row information">
            <div class="col-xs-12 col-md-8">
                <img src="./art-images/works/large/<%=artworkService.getOne(artworkId).getImageFileName()%>.jpg"
                     style="max-width: 800px;max-height: 600px;">
            </div>
            <div class="col-xs-6 col-md-4">
                <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div class="panel-heading">Product Details</div>
                    <!-- List group -->
                    <ul class="list-group">
                        <li class="list-group-item">Date:<%=artworkService.getOne(artworkId).getYearOfWork()%>
                        </li>
                        <li class="list-group-item">Medium:<%=artworkService.getOne(artworkId).getMedium()%>
                        </li>
                        <li class="list-group-item">Width:<%=artworkService.getOne(artworkId).getWidth()%>
                        </li>
                        <li class="list-group-item">Height:<%=artworkService.getOne(artworkId).getHeight()%>
                        </li>
                        <li class="list-group-item">Genres:<%=genreService.getOne(artworkId).getGenreName()%>
                        </li>
                        <li class="list-group-item">
                            Subjects:<%=subjectService.getByArtwork(artworkId).getSubjectName()%>
                        </li>
                        <li class="list-group-item">Costs:<%=artworkService.getOne(artworkId).getCost()%>
                        </li>
                        <li class="list-group-item">MSRP:<%=artworkService.getOne(artworkId).getMsrp()%>
                        </li>
                        <li class="list-group-item">
                            Artist:<%=artistService.getByArtwork(artworkId).getFirstName() + "  " + artistService.getByArtwork(artworkId).getLastName()%>
                        </li>
                        <li class="list-group-item">Link:<a
                                href="<%=artworkService.getOne(artworkId).getArtWorkLink()%>"><%=artworkService.getOne(artworkId).getArtWorkLink()%>
                        </a></li>
                        <li class="list-group-item">Google Link:<a
                                href="<%=artworkService.getOne(artworkId).getGoogleLink()%>"><%=artworkService.getOne(artworkId).getGoogleLink()%>
                        </a></li>
                        <%
                            if (session.getAttribute("user") != null) {
                                int customerID = ((Customers) (session.getAttribute("user"))).getCustomerId();
                        %>
                        <%if (!orderService.sold(artworkId) && cartService.artworkCanBeAdded(customerID, artworkId)) {%>
                        <div class="Container">
                            <button id="add" name="add" class="btn btn-success" style="float: left">+ Add to cart
                            </button>
                        </div>
                        <%} else if (!cartService.artworkCanBeAdded(customerID, artworkId)) {%>
                        <div class="Container">
                            <button id="added" name="adder" class="btn btn-primary" style="float: left">Added</button>
                        </div>
                        <% } else if (orderService.sold(artworkId)) {%>
                        <div class="Container">
                            <button id="bought" name="add" class="btn btn-danger" style="float: left">Sold Out</button>
                        </div>
                        <%
                            }
                        } else {
                        %>
                        <div class="Container">
                            <button id="addNone" name="add" class="btn btn-success" style="float: left">+ Add to cart
                            </button>
                        </div>
                        <% } %>
                    </ul>
                </div>
            </div>
        </div>
        <hr/>
        <div>
            <p>
                Description:
            </p>
            <br/>
            <p>
                <%=artworkService.getOne(artworkId).getDescription()%>
            </p>
            <hr/>
            <p>Share to:</p>
            <%--调用了一个网上人家已经写好的东西（用于分享网页（虽然没部署的网页分享了好像也没什么价值。。））--%>
            <div class="bshare-custom icon-medium-plus">
                <a title="分享到QQ空间" class="bshare-qzone"></a>
                <a title="分享到新浪微博" class="bshare-sinaminiblog"></a>
                <a title="分享到人人网" class="bshare-renren"></a>
                <a title="分享到腾讯微博" class="bshare-qqmb"></a>
                <a title="分享到网易微博" class="bshare-neteasemb"></a>
                <a title="更多平台" class="bshare-more bshare-more-icon more-style-addthis"></a>
                <span class="BSHARE_COUNT bshare-share-count">0</span>
            </div>

        </div>
    </div> <!-- /container -->
</section>
<% } else {
    response.setHeader("Refresh", "3;URL=../index.jsp");%>
<h3> 请正常访问</h3>
<b><span id="layer">3</span>秒后，跳转到主页</b>
<% }%>
<footer>
    <jsp:include page="./part/footer.jsp" flush="true"/>
</footer>
<%}%>
<script src="http://static.bshare.cn/b/buttonLite.js#style=-1&amp;uuid=&amp;pophcol=2&amp;lang=zh" type="text/javascript" charset="utf-8"></script>
<script src="http://static.bshare.cn/b/bshareC0.js" type="text/javascript" charset="utf-8"></script>
<script src="./js/details.js"></script>
</body>
</html>
