<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <?php
    include 'part/quotes.php';
    ?>
    <link rel="stylesheet" type="text/css" href="css/browse.css">
    <script src="js/browse.js"></script>
</head>
<body>
<header>
    <?php
    include 'part/nav.php';
    ?>
</header>
<section>
    //bootstrap面板
    <div class="panel panel-default">
        <div class="panel-heading">Filter</div>
        <div class="panel-body">
            <div id="filter-row" class="row">
                <div class="col-md-4">
                    <select id="continent-select" class="geo-select"></select>
                </div>
                <div class="col-md-4">
                    <select id="country-select" class="geo-select"></select>
                </div>
                <div class="col-md-4">
                    <select id="city-select" class="geo-select"></select>
                </div>
            </div>
        </div>
        <hr/>
        //图片容器
        <div id="img-div" class="panel-body"></div>
        //分页实现的容器
        <center id="pageBT-div" class="panel-footer"></center>
    </div>
</section>
<footer>
    <?php
    include 'part/footer.php';
    ?>
</footer>
</body>
</html>
