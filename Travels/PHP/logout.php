<?php
//销毁session来注销账户
session_start();
session_unset();
echo 'logout';
?>