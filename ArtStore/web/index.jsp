<%@ page import="entity.Artwork" %><%--
  Created by IntelliJ IDEA.
  User: leiwe
  Date: 2017/7/6
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="./part/quotes.txt"/>
    <link rel="stylesheet" href="./css/home.css">
    <%--<script>--%>
        <%--$(document).ready(function () {--%>
            <%--$("#searchNav").click(function () {--%>
                <%--$.get("./searchData",{"search":$("#searchInputNav").val()});--%>
                <%--window.location.href="./search.jsp";--%>
            <%--})--%>
        <%--})--%>
    <%--</script>--%>
</head>
<body>
<jsp:useBean id="artworkService"  class="service.impl.ArtworkServiceImpl" scope="application"/>
<%--
./是相对路径，指当前目录，防止项目迁移时发生不必要的错误
--%>
<%String success=(String)session.getAttribute("success");%>
<header class="marginBottom">
    <jsp:include page="./part/nav.jsp" flush="true"/>
</header>
<%--轮播，三张图片按照点击量逆序排列，By the way，点击量只做了简单的页面details的区分，由于用户量太小和偷懒没有做关联到customer的处理--%>
<div id="mycarousel" class="carousel slide marginBottom" data-ride="carousel"
     style="width: 60vw;margin-left: auto;margin-right: auto">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#mycarousel" data-slide-to="0" class="active"></li>
        <li data-target="#mycarousel" data-slide-to="1"></li>
        <li data-target="#mycarousel" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <a href="./details.jsp?ArtworkID=<%=artworkService.carouselImg().get(0).getArtWorkId()%>">
                <img src="./art-images/works/large/<%=artworkService.carouselImg().get(0).getImageFileName() %>.jpg" alt="...">
            </a>
                <div class="container">
                <div class="carousel-caption">
                    <h1><%=artworkService.carouselImg().get(0).getTitle() %></h1>
                    <p><%=artworkService.carouselImg().get(0).getDescription() %></p>
                    <p><a class="btn btn-lg btn-primary" href="./details.jsp?ArtworkID=<%=artworkService.carouselImg().get(0).getArtWorkId()%>">View More</a></p>
                </div>
            </div>
        </div>
        <div class="item">
            <a href="./details.jsp?ArtworkID=<%=artworkService.carouselImg().get(1).getArtWorkId()%>">
                <img src="./art-images/works/large/<%=artworkService.carouselImg().get(1).getImageFileName() %>.jpg" alt="...">
            </a>
                <div class="container">
                <div class="carousel-caption">
                    <h1><%=artworkService.carouselImg().get(1).getTitle() %></h1>
                    <p><%=artworkService.carouselImg().get(1).getDescription() %></p>
                    <p><a class="btn btn-lg btn-primary" href="./details.jsp?ArtworkID=<%=artworkService.carouselImg().get(1).getArtWorkId()%>">View More</a></p>
                </div>
            </div>
        </div>
        <div class="item">
            <a href="./details.jsp?ArtworkID=<%=artworkService.carouselImg().get(2).getArtWorkId()%>">
            <img src="./art-images/works/large/<%=artworkService.carouselImg().get(2).getImageFileName() %>.jpg" alt="...">
            </a>
                <div class="carousel-caption">
                <h1><%=artworkService.carouselImg().get(2).getTitle() %></h1>
                <p><%=artworkService.carouselImg().get(2).getDescription() %></p>
                <p><a class="btn btn-lg btn-primary" href="./details.jsp?ArtworkID=<%=artworkService.carouselImg().get(2).getArtWorkId()%>">View More</a></p>
            </div>
        </div>
    </div>

    <!-- Controls -->
    <a class="left carousel-control" href="#mycarousel"  data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#mycarousel" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right"></span>
        <span class="sr-only">Next</span>
    </a>
</div>
<%--三张最新图片，按照artworkID逆序排列--%>
<section id="threeWorks">
    <div class="Container container-fluid" id="hotImageContainerSection" >
        <% for (Artwork artwork : artworkService.newImg()) { %>
        <div class="image-container">
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail" style="height: 80vh">
                    <a href="./details.jsp?ArtworkID=<%=artwork.getArtWorkId()%>">
                        <img class="img-circle" style="height:200px;width: 200px" src="./art-images/works/small/<%=artwork.getImageFileName() %>.jpg" alt="<%=artwork.getTitle() %>">
                    </a>
                    <div class="caption">
                        <h3><%=artwork.getTitle() %></h3>
                        <p><%=artwork.getDescription() %></p>
                    </div>
                    <p><a href="./details.jsp?ArtworkID=<%=artwork.getArtWorkId()%>" class="btn btn-primary" >View details &raquo;</a></p>
                </div>
            </div>
        </div>
        <% } %>
    </div>
</section>
<section id="allWorks" >
    <div class=" container-fluid">
    <% int i=1; %>
    <% for (Artwork artwork : artworkService.getAll()) {  %>
        <div class="beautifulBoard">
        <p><%=i+"."+artwork.getTitle()+artwork.getImageFileName()%></p>
        <div class="row">
            <div class="col-xs-6 col-md-4"><a href="./details.jsp?ArtworkID=<%=artwork.getArtWorkId()%>"><img src="./art-images/works/large/<%=artwork.getImageFileName() %>.jpg" style="width: 30vw;height: 40vh"></a></div>
            <div class="col-xs-12 col-md-8">
                <p><%=artwork.getDescription()%></p>
                <li>Google Link: <a href="<%=artwork.getGoogleLink() %>"><%=artwork.getGoogleLink() %></a></li>
                <li>Artwork Link:<a href="<%=artwork.getArtWorkLink() %>"> <%=artwork.getArtWorkLink()%></a></li>
            </div>
            <p><a class="btn btn-lg btn-primary" href="./details.jsp?ArtworkID=<%=artwork.getArtWorkId()%>">View More</a></p>
        </div>
        </div>
    <% i++; } %>
    </div>
</section>
<footer>
    <jsp:include page="./part/footer.jsp" flush="true"/>
</footer>
<%--懒得改了，就直接在servlet返回错误代码（js形式），回调刷新一下login页面，出现弹窗--%>
<%
    if(success != null) {
        out.print(success);
        session.removeAttribute("success");
    }%>
</body>
</html>