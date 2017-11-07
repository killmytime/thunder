<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <?php
    include 'part/quotes.php';
    ?>
    <script src="js/search.js"></script>
    <link rel="stylesheet" type="text/css" href="css/search.css">
</head>
<body>
<header>
    <?php
    include 'part/nav.php';
    ?>
</header>
<section>
    <div class="panel panel-primary search-panel">
        <div class="panel-heading"><h4>Search</h4></div>
        <div class="panel-body">
            <div class="input-descrip"><p>Filter By Title</p></div>
            <div class="row">
                <div class="col-md-9">
                    <div class="input-group">
                        <span class="input-group-addon">
                            <input type="radio" name="filterWay" value="title" checked="true">
                        </span>
                        <input id="titleInput" type="text" class="form-control">
                    </div>
                </div>
            </div>
            <div class="input-descrip"><p>Filter By Description</p></div>
            <div class="row">
                <div class="col-md-9">
                    <div class="input-group">
                        <span class="input-group-addon">
                            <input type="radio" name="filterWay" value="description">
                        </span>
                        <input id="descriptionInput" type="text" class="form-control">
                    </div>
                </div>
            </div>
        </div>
        <div class="panel-footer">
            <div class="btn-group" role="group">
                <button id="filterBT" type="button" class="btn btn-primary">Filter</button>
            </div>
        </div>
    </div>
    <div class="panel panel-primary search-panel">
        <div class="panel-heading"><h4>Search Result</h4></div>
        <div id="img-div" class="panel-body"></div>
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
