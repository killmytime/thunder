<?php
include 'connect.php';
session_start();
if (isset($_POST['modify'])) {
    //备份该图片修改前的信息
    $backUpCopy = $mysqli->query('SELECT * FROM travelimage WHERE ImageID=' . $_POST['imageID']);
    $backUpCopyArray = $backUpCopy->fetch_array(MYSQLI_ASSOC);
    $backUpQuery = 'UPDATE travelimage SET Title="' . $backUpCopyArray['Title'] . '", Description="' .
        $backUpCopyArray['Description'] . '", Latitude=' . $backUpCopyArray['Latitude'] . ', Longitude=' .
        $backUpCopyArray['Longitude'] . ', CityCode="' . $backUpCopyArray['CityCode'] . '", CountryCodeISO="' .
        $backUpCopyArray['CountryCodeISO'] . '" WHERE ImageID=' . $_POST['imageID'];

    //根据用户输入，查询cityCode，countryCode
    $cityCode = '';
    $countryCode = '';
    $cityResult = $mysqli->query('SELECT GeoNameID,CountryCodeISO FROM geocities WHERE AsciiName="' . $_POST['city'] . '"');
    if (!empty($cityResult)) {
        $row = $cityResult->fetch_array(MYSQLI_ASSOC);
        $cityCode = $row['GeoNameID'];
        $countryCode = $row['CountryCodeISO'];
    }

    //生成SQL语句指令
    $query = 'UPDATE travelimage SET Title="' . $_POST['imageName'] . '", Description="' . $_POST['imageDescription'] .
        '", Latitude=' . $_POST['latitude'] . ', Longitude=' . $_POST['longitude'] . ', CityCode="' . $cityCode .
        '", CountryCodeISO="' . $countryCode . '" WHERE ImageID=' . $_POST['imageID'];
    if ($mysqli->query($query)) {
        //如果修改了上传图片，进入下面的if
        if (isset($_FILES['modifyFile'])) {
            if ($_FILES['modifyFile']['type'] == 'image/jpeg' || $_FILES['modifyFile']['type'] == 'image/pjpeg') {
                if (file_exists('../travel-images/large/' . $_FILES['modifyFile']['name'])) {
                    $mysqli->query($backUpQuery);
                    echo $_FILES['modifyFile']['name'] . ' already exists.';
                } else {
                    //切图，调整上传图片大小，存储至各个文件夹
                    //每张图片要放至五个文件夹下，设置路径
                    $largeImageFilePath = '../travel-images/large/' . $_FILES['modifyFile']['name'];
                    $mediumImageFilePath = '../travel-images/medium/' . $_FILES['modifyFile']['name'];
                    $smallImageFilePath = '../travel-images/small/' . $_FILES['modifyFile']['name'];
                    $squareMediumImageFilePath = '../travel-images/square-medium/' . $_FILES['modifyFile']['name'];
                    $squareSmallImageFilePath = '../travel-images/square-small/' . $_FILES['modifyFile']['name'];

                    //设置各个文件夹下图片的大小
                    list($width, $height) = getimagesize($_FILES['modifyFile']['tmp_name']);
                    $uploadImage = imagecreatefromjpeg($_FILES['modifyFile']['tmp_name']);
                    $largeImage = imagecreatetruecolor(1680, 960);
                    $mediumImage = imagecreatetruecolor(640, 480);
                    $smallImage = imagecreatetruecolor(320, 240);
                    $squareMediumImage = imagecreatetruecolor(250, 250);
                    $squareSmallImage = imagecreatetruecolor(75, 75);

                    //改变图片大小
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
                        $mysqli->query($backUpQuery);
                        echo 'Upload failed. Please try again.';
                    }
                }
            } else {
                $mysqli->query($backUpQuery);
                echo 'Please upload a JPG image.';
            }
        } //未修改上传图片，只修改信息，返回下面的字符串
        else
            echo 'Success!';
    } else
        echo 'Update failed.';
}