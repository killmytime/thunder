<%--
  Created by IntelliJ IDEA.
  User: leiwe
  Date: 2017/7/10
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%--注册页面--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <jsp:include page="./part/quotes.txt" flush="true"/>
</head>
<body>
<header class="marginBottom">
    <jsp:include page="./part/nav.jsp" flush="true"/>
</header>
<div class="container margin-top-80">
    <%--js提交demo: ajax--%>
    <form id="registerForm" class="max-width-330 layout-center">
        <h2>Please sign up</h2>
        <div class="form-group" id="email-div">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" name="email" placeholder="Email@example.com" required>
            <span id="email-help" class="help-block"></span>
        </div>
        <div class="form-group" id="username-div">
            <label for="username">Username:</label>
            <input type="text" class="form-control" id="username" name="username" placeholder="Username" required>
            <span id="username-help" class="help-block"></span>
        </div>
        <div class="form-group" id="password-div">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
            <span id="password-help" class="help-block"></span>
        </div>
        <div class="form-group" id="passwordConfirm-div">
            <label for="passwordConfirm">Confirm Password</label>
            <input type="password" class="form-control" id="passwordConfirm" name="passwordConfirm" placeholder="Confirm Password" required>
            <span id="passwordConfirm-help" class="help-block"></span>
        </div>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>

        <button id="registerSubmitBtn" class="btn btn-lg btn-primary btn-block" type="button">Sign up</button>
        <a href="./login.jsp">Sign in</a>
    </form>
</div>
<footer>
    <jsp:include page="./part/footer.jsp" flush="true"/>
</footer>
<script src="./js/register.js"></script>
</body>
</html>
