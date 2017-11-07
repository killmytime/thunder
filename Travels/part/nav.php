<?php

isset($_SESSION) or session_start();
//判断session状态在页面加载时开始一个session

$_SESSION['username'] = (isset($_SESSION['username'])) ? $_SESSION['username'] : '';
//抄了一段bootstrap的导航栏样式，略作修改

$part1 = '<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
            <a class="navbar-brand" id="log" href="#">Share Your Travel</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul id="nav-ul" class="nav navbar-nav">
                <li id="home"><a href="home.php">Home</a></li>
                <li id="browse"><a href="browse.php">Browse</a></li>
                <li id="search"><a href="search.php">Search</a></li>
            </ul>';

$part3 = '</div>
    </div>
</nav>';

$part2_1 = '<form class="navbar-form navbar-right" id="signInForm">
                <div class="form-group">
                    <input type="email" class="form-control" placeholder="Email" id="username">
                    <input type="password" class="form-control" placeholder="Password" id="password">
                </div>
                <button type="button" class="btn btn-primary" id="signIn">Login</button>
                <button type="button" class="btn btn-primary" id="register">Register</button>
            </form>';

$accountName = '<div class="btn-group navbar-right" id="accountList">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" id="accountName" style="height: 50px;width 196px;background-color: lightslategrey">';
//为了显示出欢迎用户登陆的效果
$accountMenu = ' <span class="caret"></span>
                        <span class="sr-only">Toggle Dropdown</span>
                    </button>
                    <ul class="dropdown-menu" style="width: 196px;background-color: grey">
                        <li id="uploadItem"><a href="#">
                        <span class="glyphicon glyphicon-cloud-upload" aria-hidden="true"></span>  Upload</a></li>
                        <li id="myPhotosItem"><a href="#">
                        <span class="glyphicon glyphicon-picture" aria-hidden="true"></span>  My Photos</a></li>
                        <li id="favorItem"><a href="#">
                        <span class="glyphicon glyphicon-heart" aria-hidden="true"></span>  Favorites</a></li>
                        <li role="separator" class="divider"></li>
                        <li id="logoutItem"><a href="#">
                        <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>  Logout</a></li>
                    </ul>
            </div>';
//分步集成一个完整的通用导航栏（加入判断用户状态）
echo $part1;
if ($_SESSION['username'] == '') {
    echo $part2_1;
} else {
    echo $accountName . $_SESSION['username'] . $accountMenu;
}
echo $part3;
?>

