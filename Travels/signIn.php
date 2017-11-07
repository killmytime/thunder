<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <?php
    include 'part/quotes.php';
    ?>
    <script src="js/login.js"></script>
    <link rel="stylesheet" type="text/css" href="css/correction.css">
</head>
<body>
<header>
    <?php
    include 'part/nav.php';
    ?>
</header>
<section class="signInContainer">
    <div class="container">
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <form class="form-signin">
                    <h2 class="form-signin-heading">Log in</h2>
                    <label for="inputEmail" class="sr-only">Email address</label>
                    <input type="email" id="inputEmail" class="form-control input-form" placeholder="Email address..."
                           required autofocus>
                    <label for="inputPassword" class="sr-only">Password</label>
                    <input type="password" id="inputPassword" class="form-control input-form" placeholder="Password..."
                           required>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" value="remember-me"> Remember me
                        </label>
                    </div>
                    <button id="signInPageBT" class="btn btn-lg btn-primary btn-block input-form" type="button">
                        Log in
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

