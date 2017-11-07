<?php
/**
 * Created by PhpStorm.
 * User: 10975
 * Date: 2017/6/4
 * Time: 23:52
 */
$quotes = ' <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>Share your travel</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="js/loginConfirmation.js"></script>
    <link rel="icon" href="logos/favicon.ico">';
//因为之前改过bootstrap代码出了bug，后来觉得还是直接引入托管在网站上的库好一点
//高亮是因为在当前目录不对应，在应用的目录是对应的（顺带自己做了个辣鸡logo）
echo $quotes;
?>