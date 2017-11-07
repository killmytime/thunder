<?php
include 'PHP/connect.php';
session_start();
$imgPath = $imgTitle = $imgDescription = $city = $country = $continent = $latitude = $longitude = $imageID = '';
if (isset($_SESSION['UID']) && isset($_GET['imageID'])) {
    //获取相关初始信息
    $query = 'SELECT Title,Description,Latitude,Longitude,CityCode,CountryCodeISO,UID,PATH FROM ' .
        'travelimage WHERE ImageID=' . $_GET['imageID'];
    //echo $query;
    $result = $mysqli->query($query);
    //echo $result;
    if (!empty($result)) {
        $resultArray = $result->fetch_array(MYSQLI_ASSOC);
        //print_r($resultArray);
        if ($_SESSION['UID'] == $resultArray['UID']) {
            $imageID = $_GET['imageID'];
            if (isset($resultArray['CityCode'])) {
                $cityResult = $mysqli->query('SELECT AsciiName FROM geocities WHERE GeoNameID=' . $resultArray['CityCode']);
                if (!empty($cityResult)) {
                    $row = $cityResult->fetch_array(MYSQLI_NUM);
                    $city = $row[0];
                }
            }
            if (isset($resultArray['CountryCodeISO'])) {
                $countryResult = $mysqli->query('SELECT CountryName,Continent FROM geocountries WHERE ISO="'
                    . $resultArray['CountryCodeISO'] . '"');
                if (!empty($countryResult)) {
                    $row = $countryResult->fetch_array(MYSQLI_ASSOC);
                    $country = $row['CountryName'];
                    $continentResult = $mysqli->query('SELECT ContinentName FROM geocontinents WHERE ContinentCode="'
                        . $row['Continent'] . '"');
                    $continentRow = $continentResult->fetch_array(MYSQLI_NUM);
                    $continent = $continentRow[0];
                }
            }
            $uid = $resultArray['UID'];
            $imgPath = 'travel-images/medium/' . $resultArray['PATH'];
            $imgTitle = $resultArray['Title'];
            $imgDescription = $resultArray['Description'];
            $latitude = $resultArray['Latitude'];
            $longitude = $resultArray['Longitude'];
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
    <link rel="stylesheet" type="text/css" href="css/upload.css">
    <script src="js/modify.js"></script>
</head>
<body>
<header>
    <?php
    include 'part/nav.php';
    ?>
</header>
<section>
    <div class="panel panel-default">
        <form id="inputForm" class="<?php echo $imageID; ?>" action="PHP/uploadImage.php"
              enctype="multipart/form-data" method="post">
            <div class="panel-body">
                <ul class="list-group">
                    <li class="list-group-item">
                        <div><img id="pre-img" src="<?php echo $imgPath; ?>"></div>
                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon1">↑</span>
                            <input id="imgupload-input" name="uploadFile" type="file" class="form-control">
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="input-group">
                            <span class="input-group-addon press" id="basic-addon1">Name</span>
                            <input id="name-input" name="imageName" type="text" value="<?php echo $imgTitle; ?>"
                                   class="form-control" placeholder="Photo Name">
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="input-group">
                            <span class="input-group-addon press" id="basic-addon1">Description</span>
                            <textarea id="description-input" name="imageDescription" class="form-control"
                                      placeholder="Photo Description"><?php echo $imgDescription ?></textarea>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="input-group">
                            <span class="input-group-addon press" id="basic-addon1">City</span>
                            <input id="city-input" name="city" type="text" value="<?php echo $city ?>"
                                   class="form-control" placeholder="City">
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="input-group">
                            <span class="input-group-addon press" id="basic-addon1">Country</span>
                            <input id="country-input" name="country" type="text" value="<?php echo $country ?>"
                                   class="form-control" placeholder="Country">
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="input-group">
                            <span class="input-group-addon press" id="basic-addon1">Continent</span>
                            <input id="continent-input" name="continent" type="text" value="<?php echo $continent ?>"
                                   class="form-control" placeholder="Continent">
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="input-group">
                            <span class="input-group-addon press" id="basic-addon1">Latitude</span>
                            <input id="latitude-input" name="latitude" type="number" value="<?php echo $latitude ?>"
                                   class="form-control" placeholder="Latitude">
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="input-group">
                            <span class="input-group-addon press" id="basic-addon1">Longitude</span>
                            <input id="longitude-input" name="longitude" type="number" value="<?php echo $longitude ?>"
                                   class="form-control" placeholder="Longitude">
                        </div>
                    </li>
                </ul>
            </div>
            <div class="panel-footer">
                <input id="submitBT" type="button" name="modify" value="Modify" class="btn btn-default">
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