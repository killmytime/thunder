<?php
//注册页面脚本
include 'connect.php';
session_start();
$_SESSION['username'] = '';
$username = $_GET['email'];
$password = $_GET['pass'];
$response = 'Register failed. Please try again.';
$registered = true;

//检查输入的邮箱是否已经被注册
$result = $mysqli->query('SELECT UserName FROM TravelUser');
while ($row = $result->fetch_array(MYSQLI_NUM)) {
    if ($username == $row[0]) {
        $response = 'The email has been registered. Please use another.';
        $registered = false;
        break;
    }
}

//未被注册则进行注册，对密码哈希并加盐后存入数据库
if ($registered) {
    $password_hashed = password_hash($password, PASSWORD_DEFAULT);
    $query = 'INSERT INTO TravelUser SET UserName="' . $username . '", Pass="'
        . $password_hashed . '", State=1, DateJoined=NOW(), DateLastModified=NOW()';
    $mysqli->query($query);
    if ($mysqli->insert_id > 0) {
        $response = 'true';
        $uidResult = $mysqli->query('SELECT UID FROM traveluser WHERE UserName="' . $username . '"');
        $row = $uidResult->fetch_array(MYSQLI_NUM);
        $_SESSION['UID'] = $row[0];
        $_SESSION['username'] = $username;
    }
}
echo $response;
?>