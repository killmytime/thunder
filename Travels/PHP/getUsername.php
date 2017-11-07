<?php
session_start();
//防止出错。。获取用户名
$_SESSION['username'] = (isset($_SESSION['username'])) ? $_SESSION['username'] : '';
echo $_SESSION['username'];
?>