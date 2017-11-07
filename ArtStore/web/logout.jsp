<%--
  Created by IntelliJ IDEA.
  User: leiwe
  Date: 2017/7/12
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" pageEncoding="UTF-8"%>  
  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
<head>  
    <title>跳转中。。。</title>  
    <meta http-equiv="pragma" content="no-cache">  
    <meta http-equiv="cache-control" content="no-cache">  
    <meta http-equiv="expires" content="0">  
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
    <meta http-equiv="description" content="This is my page">  
    <script type="text/javascript">  
                var time = 4;  
      
        function returnUrlByTime() {  
      
            window.setTimeout('returnUrlByTime()', 1000);  
      
            time = time - 1;  
      
            document.getElementById("layer").innerHTML =time;  
        }  
    </script>
    <link rel="icon" href="logos/favicon.ico">
    </head>

    <body  onload="returnUrlByTime()">
    <% session.invalidate(); %>
        <h3>登出中</h3>
    <b><span id="layer">3</span>秒后，跳转到主页</b>

    <%
        //转向语句  
        response.setHeader("Refresh","3;URL=../index.jsp");
    %>
    </body>
    </html>  






