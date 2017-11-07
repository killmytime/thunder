<%--
  Created by IntelliJ IDEA.
  User: leiwe
  Date: 2017/7/10
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <jsp:include page="./part/quotes.txt" flush="true"/>

</head>
<body>
<%String error=(String)session.getAttribute("error");%>
<header class="marginBottom">
    <jsp:include page="./part/nav.jsp" flush="true"/>
</header>
<div class="container margin-top-80">
    <form action="./login" method="post" class="max-width-330 layout-center">
        <h2>Please sign in</h2>
        <label for="username" class="sr-only">Username</label>
        <input type="text" id="username" name="username" class="form-control" placeholder="Username" required>

        <label for="inputPassword1" class="sr-only">Password</label>
        <input type="password" id="inputPassword1" name="password" class="form-control" placeholder="Password" required>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <a href="./register.jsp">Sign up</a>
    </form>
</div>
<footer>
    <jsp:include page="./part/footer.jsp" flush="true"/>
</footer>
<%--懒得改了，就直接在servlet返回错误代码（js形式），回调刷新一下login页面，出现弹窗--%>
<%
    if(error != null) {
        out.print(error);

        session.removeAttribute("error");
    }%>
</body>
</html>
