<?php

include 'connect.php';

//登陆脚本
session_start();
if (isset($_GET['username']) && isset($_GET['password'])) {
    $username = $_GET['username'];
    $password = $_GET['password'];
    $result = $mysqli->query('SELECT UserName, Pass, UID FROM TravelUser WHERE UserName="' . $username . '"');
    $signInResponse = 'false';
    if (!empty($result)) {
        $userInfo = $result->fetch_array(MYSQLI_ASSOC);
        //和哈希加密算法配合使用qvq
        if (password_verify($password, $userInfo['Pass'])) {
            $signInResponse = 'true';
            $_SESSION['username'] = $username;
            $_SESSION['UID'] = $userInfo['UID'];
            $query='UPDATE traveluser SET DateLastModified=NOW() WHERE UserName='.$username;
            $mysqli->query($query);
        }
    }
    echo $signInResponse;
}
?>