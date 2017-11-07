<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <?php
    include 'part/quotes.php';
    ?>
    <link rel="stylesheet" type="text/css" href="css/upload.css">
    <script src="js/upload.js"></script>
</head>
<body>
<header>
    <?php
    include 'part/nav.php';
    ?>
</header>
<section>
    <div class="panel panel-default">
        <form id="inputForm" action="PHP/uploadImage.php" enctype="multipart/form-data" method="post">
            <div class="panel-body input-group">
                <ul class="list-group">
                    <li class="list-group-item">
                        <div><img id="pre-img"></div>
                        <div class="input-group ">
                            <input id="imgupload-input" name="uploadFile" type="file" class="form-control"
                                   placeholder="Username">
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="input-group ">
                            <span class="input-group-addon press" id="basic-addon1">Name</span>
                            <input id="name-input" name="imageName" type="text" class="form-control"
                                   placeholder="Photo Name">
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="input-group">
                            <span class="input-group-addon press" id="basic-addon1">Description</span>
                            <textarea id="description-input" name="imageDescription" class="form-control"
                                      placeholder="Photo Description"></textarea>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="input-group">
                            <span class="input-group-addon press" id="basic-addon1">City</span>
                            <input id="city-input" name="city" type="text" class="form-control" placeholder="City">
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="input-group">
                            <span class="input-group-addon press" id="basic-addon1">Country</span>
                            <input id="country-input" name="country" type="text" class="form-control"
                                   placeholder="Country">
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="input-group">
                            <span class="input-group-addon press" id="basic-addon1">Continent</span>
                            <input id="continent-input" name="continent" type="text" class="form-control"
                                   placeholder="Continent">
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="input-group">
                            <span class="input-group-addon press" id="basic-addon1">Latitude</span>
                            <input id="latitude-input" name="latitude" type="number" class="form-control"
                                   placeholder="Latitude">
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="input-group">
                            <span class="input-group-addon press" id="basic-addon1">Longitude</span>
                            <input id="longitude-input" name="longitude" type="number" class="form-control"
                                   placeholder="Longitude">
                        </div>
                    </li>
                </ul>
            </div>
            <div class="panel-footer">
                <input id="submitBT" type="submit" name="upload" value="Upload" class="btn btn-primary">
            </div>
        </form>
    </div>
</section>
<footer>
    <?php
    include 'part/footer.php';
    ?>
</footer>
</body>
</html>
