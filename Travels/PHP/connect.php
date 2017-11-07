<?php
//数据库连接
$mysqli = new mysqli('127.0.0.1', 'root', '998326abc', 'travel');
//好像是一开始设置过的缘故，用localhost不行，127.0.0.1就可以。。。但是两个不是一样的吗
//转码成utf8
$mysqli->set_charset('utf8');
