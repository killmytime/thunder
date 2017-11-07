<?php
include 'connect.php';
session_start();

//上传脚本
if(isset($_POST['upload'])) {
    $lastImageResult = $mysqli->query('SELECT MAX(ImageID) FROM travelimage');
    $row = $lastImageResult->fetch_array(MYSQLI_NUM);
    $imageNum = $row[0];

    $cityCode = '';
    $countryCode = '';
    $cityCodeResult = $mysqli->query('SELECT GeoNameID FROM geocities WHERE AsciiName="' . $_POST['city'] . '"');
    if (!empty($cityCodeResult)) {
        $row = $cityCodeResult->fetch_array(MYSQLI_ASSOC);
        $cityCode = $row['GeoNameID'];
    }
    $countryCodeResult = $mysqli->query('SELECT ISO FROM geocountries WHERE CountryName="' . $_POST['country'] . '"');
    if (!empty($countryCodeResult)) {
        $row = $countryCodeResult->fetch_array(MYSQLI_ASSOC);
        $countryCode = $row['ISO'];
    }

    $query = 'INSERT INTO travelimage SET ImageID=' . ($imageNum + 1) . ', Title="' . $_POST['imageName'] .
        '",Description="' . $_POST['imageDescription'] . '", CityCode="' . $cityCode . '", CountryCodeISO="' .
        $countryCode . '", UID=' . $_SESSION['UID'] . ', Latitude=' . $_POST['latitude'] . ', Longitude=' .
        $_POST['longitude'] . ', PATH="' . $_FILES['uploadFile']['name'] . '"';
    //echo $query;
    if ($mysqli->query($query)) {
        //print_r($_FILES);
        if ($_FILES['uploadFile']['error'] > 0) {
            $mysqli->query('DELETE FROM travelimage WHERE ImageID=' . ($imageNum + 1));
            echo 'Error: ' . $_FILES['uploadFile']['error'];
        } else if ($_FILES['uploadFile']['type'] == 'image/jpeg' || $_FILES['uploadFile']['type'] == 'image/pjpeg') {
            if (file_exists('../travel-images/large/' . $_FILES['uploadFile']['name'])) {
                $mysqli->query('DELETE FROM travelimage WHERE ImageID=' . ($imageNum + 1));
                echo $_FILES['uploadFile']['name'] . ' already exists.';
            } else {
                //裁剪图片分类放
                $largeImageFilePath = '../travel-images/large/' . $_FILES['uploadFile']['name'];
                $mediumImageFilePath = '../travel-images/medium/' . $_FILES['uploadFile']['name'];
                $smallImageFilePath = '../travel-images/small/' . $_FILES['uploadFile']['name'];
                $squareMediumImageFilePath = '../travel-images/square-medium/' . $_FILES['uploadFile']['name'];
                $squareSmallImageFilePath = '../travel-images/square-small/' . $_FILES['uploadFile']['name'];

                list($width, $height) = getimagesize($_FILES['uploadFile']['tmp_name']);
                $uploadImage = imagecreatefromjpeg($_FILES['uploadFile']['tmp_name']);
                $largeImage = imagecreatetruecolor(1680, 960);
                $mediumImage = imagecreatetruecolor(640, 480);
                $smallImage = imagecreatetruecolor(320, 240);
                $squareMediumImage = imagecreatetruecolor(250, 250);
                $squareSmallImage = imagecreatetruecolor(75, 75);

                imagecopyresized($largeImage, $uploadImage, 0, 0, 0, 0, 1680, 960, $width, $height);
                imagecopyresized($mediumImage, $uploadImage, 0, 0, 0, 0, 640, 480, $width, $height);
                imagecopyresized($smallImage, $uploadImage, 0, 0, 0, 0, 320, 240, $width, $height);
                imagecopyresized($squareMediumImage, $uploadImage, 0, 0, 0, 0, 250, 250, $width, $height);
                imagecopyresized($squareSmallImage, $uploadImage, 0, 0, 0, 0, 75, 75, $width, $height);
                $largeRe = imagejpeg($largeImage, $largeImageFilePath);
                $mediumRe = imagejpeg($mediumImage, $mediumImageFilePath);
                $smallRe = imagejpeg($smallImage, $smallImageFilePath);
                $squareMediumRe = imagejpeg($squareMediumImage, $squareMediumImageFilePath);
                $squareSmallRe = imagejpeg($squareSmallImage, $squareSmallImageFilePath);

                imagedestroy($uploadImage);
                imagedestroy($largeImage);
                imagedestroy($mediumImage);
                imagedestroy($smallImage);
                imagedestroy($squareSmallImage);
                imagedestroy($squareMediumImage);

                if ($largeRe && $smallRe && $squareSmallRe && $squareMediumRe && $mediumRe)
                    echo 'Success!';
                else {
                    $mysqli->query('DELETE FROM travelimage WHERE ImageID=' . ($imageNum + 1));
                    echo 'Upload failed. Please try again.';
                }
            }
        } else
            echo 'Please upload a JPG image.';
    } else {
        $mysqli->query('DELETE FROM travelimage WHERE ImageID=' . ($imageNum + 1));
        echo 'Uploading failed. Please try again.';
    }
}else{
    echo 'Invalid operation';
}