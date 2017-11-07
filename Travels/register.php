<?php
?>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <?php
    include 'part/quotes.php';
    ?>
    <link rel="stylesheet" type="text/css" href="css/correction.css">
    <script src="js/register.js"></script>
</head>
<body>
<header>
    <?php
    include 'part/nav.php';
    ?>
</header>
<section class="signInContainer marginTop">
    <div class="container">
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <form class="form-signin">
                    <h2 class="form-signin-heading" style="text-align: center;">Register</h2>
                    <label for="inputEmail" class="sr-only">Email address</label>
                    <input type="email" id="inputRegisterEmail" class="form-control input-form"
                           placeholder="Email address..." required autofocus>
                    <label for="inputPassword" class="sr-only">New Password</label>
                    <input type="password" id="inputRegisterPasswordFirst" class="form-control input-form"
                           placeholder="New Password..." required>
                    <label for="inputPassword" class="sr-only">Confirm Password</label>
                    <input type="password" id="inputRegisterPasswordSecond" class="form-control input-form"
                           placeholder="Confirm Password..." required>
                    <button id="registerBT" class="btn btn-lg btn-primary btn-block input-form" type="button">Register
                    </button>
                </form>
            </div>
            <div class="col-md-4"></div>
        </div>
    </div> <!-- /container -->
</section>
<footer>
    <?php
    include 'part/footer.php';
    ?>
</footer>
</body>
</html>
