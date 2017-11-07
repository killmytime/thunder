<%@ page import="entity.Artist" %>
<%@ page import="entity.Artwork" %>
<%@ page import="util.PageBean" %><%--
  Created by IntelliJ IDEA.
  User: leiwe
  Date: 2017/7/17
  Time: 0:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="./part/quotes.txt" flush="true"/>
    <script src="./js/artists.js"></script>
</head>
<body>
<%StringBuilder url=new StringBuilder(request.getServerName()+":"+request.getLocalPort()+request.getRequestURI());
    if (request.getQueryString()==null||"".equals(request.getQueryString())){
//        url.append("?currentPage=1");
        response.sendRedirect("./artists.jsp?currentPage=1");
    }
    else {
%>
<jsp:useBean id="artistService" scope="application" class="service.impl.ArtistServiceImpl"/>
<jsp:useBean id="artworkService" scope="application" class="service.impl.ArtworkServiceImpl"/>
<%String currPage=request.getParameter("currentPage");
int currentPage=1;
if (currPage.matches("^[0-9]+$")){
    currentPage=Integer.parseInt(currPage);
}
%>
<header class="marginBottom">
    <jsp:include page="./part/nav.jsp" flush="true"/>
</header>
<h1>Artists</h1>
<section id="allArtists">
    <div class=" container-fluid">
        <%PageBean<Artist> pageBean=new PageBean<>();
        pageBean.setCurrentPage(currentPage);
        artistService.getAll(pageBean);
        %>
    <% for (Artist artist : pageBean.getPageData()) {  %>

        <div class="row beautifulBoard">
            <div class="col-xs-6 col-md-4"><img src="./art-images/artists/medium/<%=artist.getArtistId()%>.jpg" style="width: 20vw;height: 100vh" class="marginBottom"></div>
            <div class=".col-xs-12 .col-md-8"><%=artist.getFirstName()+" "+artist.getLastName()%>
            <p>Artist Link: <a href="<%=artist.getArtistLink()%>"><%=artist.getArtistLink()%></a></p>
            <br/>
            <p><%=artist.getDetails()%></p>
            <br/>
            <p>ArtworkLink</p>
            <%for (Artwork artwork:artworkService.getByArtist(artist.getArtistId())){ %>
            <li><a href="./details.jsp?ArtworkID=<%=artwork.getArtWorkId()%>"><%=artwork.getTitle()%></a> </li>
            <% } %>
                <div></div>
        </div>
    </div>

    <%  } %>
        <center><button id="before" value="before">Back</button><button id="current" value="<%=pageBean.getCurrentPage()%>"><%=pageBean.getCurrentPage()%></button><button id="next" value="next">Next</button> </center>
    </div>
</section>
<footer>
    <jsp:include page="./part/footer.jsp" flush="true"/>
</footer>
<%}%>
</body>
</html>
