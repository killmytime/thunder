<?php

include 'connect.php';
//从城市逆向推得国家大洲，发现了一个问题，如果城市为空的话还要再考虑国家是不是空，嫌麻烦然后改了一下数据库。。。
//包含所有图片信息的数组
$allImageInfo = array();
$allImageNum = 0;
$imageInfoResult = $mysqli->query('SELECT ImageID,CityCode,CountryCodeISO,PATH FROM travelimage');
while ($row = $imageInfoResult->fetch_array(MYSQLI_ASSOC)) {
    $allImageInfo[$allImageNum] = $row;
    $allImageNum++;
}

//首先得到所有图片，由图片中的城市信息返回去得到城市数组
$cityArray = array();
for ($index = 0; $index < $allImageNum; $index++) {
    if (isset($allImageInfo[$index]['CityCode']) && $allImageInfo[$index]['CityCode'] != '0') {
        $query = 'SELECT AsciiName FROM geocities WHERE GeoNameID=' . $allImageInfo[$index]['CityCode'];
        $cityNameResult = $mysqli->query($query);
        $row = $cityNameResult->fetch_array(MYSQLI_NUM);
        $cityName = $row[0];
    } else
        $cityName = 'Unknown';
    $allImageInfo[$index]['CityName'] = $cityName;
    $cityArray[$cityName]['CityName'] = $cityName;
    $cityArray[$cityName]['CityCode'] = isset($allImageInfo[$index]['CityCode']) ? $allImageInfo[$index]['CityCode'] : '';
}
foreach ($cityArray as $cityName => $item) {
    $i = 0;
    for ($index = 0; $index < $allImageNum; $index++) {
        if ($allImageInfo[$index]['CityName'] == $cityName) {
            $cityArray[$cityName]['img'][$i] = $allImageInfo[$index];
            $i++;
        }
    }
}

//由城市数组得到国家数组（需要升级）
$countryArray = array();
foreach ($cityArray as $cityName => $item) {
    if ($cityArray[$cityName]['CityCode'] == '' || $cityArray[$cityName]['CityCode'] == '0') {
        $cityArray[$cityName]['CountryCodeISO'] = '';
        $cityArray[$cityName]['CountryName'] = 'Unknown';
    } else {
        $queryFirst = 'SELECT CountryCodeISO FROM geocities WHERE GeoNameID=' . $cityArray[$cityName]['CityCode'];
        $resultFisrt = $mysqli->query($queryFirst);
        $rowFirst = $resultFisrt->fetch_array(MYSQLI_NUM);
        $cityArray[$cityName]['CountryCodeISO'] = $rowFirst[0];
        $querySecond = 'SELECT CountryName FROM geocountries WHERE ISO="' . $cityArray[$cityName]['CountryCodeISO'] . '"';
        $resultSecond = $mysqli->query($querySecond);
        $rowSecond = $resultSecond->fetch_array(MYSQLI_NUM);
        $cityArray[$cityName]['CountryName'] = $rowSecond[0];
    }
    $countryName = $cityArray[$cityName]['CountryName'];
    $countryArray[$countryName]['CountryName'] = $countryName;
    $countryArray[$countryName]['CountryCodeISO'] = ($cityArray[$cityName]['CityCode'] == ''
        || $cityArray[$cityName]['CityCode'] == '0') ? '' : $rowFirst[0];
}

//由国家数组得到大洲数组
$continentArray = array();
foreach ($countryArray as $countryName => $country) {
    $i = 0;
    foreach ($cityArray as $cityName => $city) {
        if ($cityArray[$cityName]['CountryName'] == $countryName) {
            $countryArray[$countryName]['City'][$i] = $cityArray[$cityName];
            $i++;
        }
    }

    if ($countryArray[$countryName]['CountryCodeISO'] == '') {
        $countryArray[$countryName]['ContinentName'] = 'Unknown';
        $countryArray[$countryName]['ContinentCode'] = '';
        $continentArray['Unknown']['ContinentName'] = 'Unknown';
    } else {
        $queryFirst = 'SELECT Continent FROM geocountries WHERE ISO="' . $countryArray[$countryName]['CountryCodeISO'] . '"';
        $resultFisrt = $mysqli->query($queryFirst);
        $rowFirst = $resultFisrt->fetch_array(MYSQLI_NUM);
        $countryArray[$countryName]['ContinentCode'] = $rowFirst[0];
        $querySecond = 'SELECT ContinentName FROM geocontinents WHERE ContinentCode="' . $rowFirst[0] . '"';
        $resultSecond = $mysqli->query($querySecond);
        $rowSecond = $resultSecond->fetch_array(MYSQLI_NUM);
        $countryArray[$countryName]['ContinentName'] = $rowSecond[0];
        $continentArray[$rowSecond[0]]['ContinentName'] = $rowSecond[0];
    }
}
foreach ($continentArray as $continentName => $continent) {
    $i = 0;
    foreach ($countryArray as $countryName => $country) {
        if ($countryArray[$countryName]['ContinentName'] == $continentName) {
            $continentArray[$continentName]['Country'][$i] = $countryArray[$countryName];
            $i++;
        }
    }
}

//最终制作出一个返回的数组
$i = 0;
$responseArray = array();
foreach ($continentArray as $continentName => $continent) {
    $responseArray['Continent'][$i] = $continentArray[$continentName];
    $i++;
}

//利用json返回
echo json_encode($responseArray);
?>