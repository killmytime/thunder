<?php

include 'PHP/connect.php';

session_start();
if (isset($_GET['imageID'])) {
    $imageID = $_GET['imageID'];

    $query = 'SELECT * FROM travelimage WHERE ImageID=' . $imageID;
    $result = $mysqli->query($query);
    $imageInfoAssoc = $result->fetch_array(MYSQLI_ASSOC);//存储图片信息的关联数组

    if ($secondResult = $mysqli->query('SELECT AsciiName FROM geocities WHERE GeoNameID=' . $imageInfoAssoc['CityCode'])
    ) {
        $cityNameArray = $secondResult->fetch_array(MYSQLI_NUM);
        $imageInfoAssoc['CityName'] = $cityNameArray[0];
    } else
        $imageInfoAssoc['CityName'] = 'Unknown';

    $thirdResult = $mysqli->query('SELECT CountryName FROM geocountries WHERE ISO="' . $imageInfoAssoc['CountryCodeISO'] . '"');
    $countryNameArray = $thirdResult->fetch_array(MYSQLI_NUM);
    $imageInfoAssoc['CountryName'] = $countryNameArray[0];
    $result = $mysqli->query('SELECT ImageID,COUNT(ImageID) AS favoritingNum  FROM travelimagefavor GROUP BY ImageID ');
    $favorite= array();
    while ($row=mysqli_fetch_assoc($result)){
        $imageIDFav=$row['ImageID'];
        $favorite[$imageIDFav]=$row['favoritingNum'];
    }
    $likeNumber=$favorite[$imageID]==null?0:$favorite[$imageID];
    $isFavorited = false;
    //用户的情况
    if (isset($_SESSION['UID'])) {
            $resultFourth = $mysqli->query('SELECT imageID FROM travelimagefavor WHERE UID=' . $_SESSION['UID']);
            $favorArray = array();
            while ($row = mysqli_fetch_assoc($resultFourth)) {
                $imageIDFavor = $row['imageID'];
                $favorArray[$imageIDFavor] = 1;
            }
        foreach ($favorArray as $imageIDFavor) {
            if (@$favorArray[$imageID]==1)
                $isFavorited = true;
        }
    }
}
?>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <?php
    include 'part/quotes.php';
    ?>
    <link rel="stylesheet" type="text/css" href="css/imageDetail.css">
    <script src="js/imageDetail.js"></script>
</head>
<body>
<header>
    <?php
    include 'part/nav.php';
    ?>
</header>
<section>
    <div class="container">
        <div><h3><?php echo $imageInfoAssoc['Title']; ?>
                <span id="uid-container" class="<?php echo isset($_SESSION['UID']) ? $_SESSION['UID'] : ''; ?>"></span>
                <span id="imageid-container" class="<?php echo $_GET['imageID']; ?>"></span>
            </h3></div>
        <div class="row information">
            <div class="col-md-8">
                <a href="imageDetail.php?imageID=<?php echo $_GET['imageID']; ?>">
                    <img src="travel-images/medium/<?php echo $imageInfoAssoc['PATH']; ?>"></a>
            </div>
            <div class="col-md-4">
                <div class="like-bt-container">
                    <hr/>
                    <p class="<?php echo $isFavorited ? '' : 'hide' ?>">
                        <span id="unfavor" class="glyphicon glyphicon-heart like-bt" aria-hidden="true"></span>
                        <span> Favorited</span></p>
                    <p class="<?php echo $isFavorited ? 'hide' : '' ?>">
                        <span id="favor" class="glyphicon glyphicon-heart-empty like-bt" aria-hidden="true"></span>
                        <span> Favorite</span></p>
                    <hr/>
                </div>
                <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div class="panel-heading">Like Number</div>
                    <div class="panel-body like-number-div">
                        <p><?php echo $likeNumber; ?></p>
                    </div>
                </div>
                <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div class="panel-heading">Image Details</div>
                    <!-- List group -->
                    <ul class="list-group">
                        <li class="list-group-item">Country: <?php echo $imageInfoAssoc['CountryName']; ?></li>
                        <li class="list-group-item">City: <?php echo $imageInfoAssoc['CityName']; ?></li>
                        <li class="list-group-item">Latitude: <?php echo $imageInfoAssoc['Latitude']; ?></li>
                        <li class="list-group-item">Longtitude: <?php echo $imageInfoAssoc['Longitude']; ?></li>
                    </ul>
                </div>
            </div>
        </div>
        <hr/>
        <div>
            <p>
                <?php echo $imageInfoAssoc['Description']; ?>.
            </p>
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

