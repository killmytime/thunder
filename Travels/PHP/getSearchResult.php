<?php
include 'connect.php';

//$filterWay = 'title';
//$searchKey = 'the';
//返回搜索结果
if (isset($_GET['filterWay'])) {
    $filterWay = $_GET['filterWay'];
    $searchKey = $_GET['searchKey'];
    $titleQuery = 'SELECT ImageID,Title,Description,PATH FROM travelimage WHERE Title REGEXP "' . $searchKey . '"';
    $descriptionQuery = 'SELECT ImageID,Title,Description,PATH FROM travelimage WHERE Description REGEXP "'
        . $searchKey . '"';
    $query = ($filterWay == 'title') ? $titleQuery : $descriptionQuery;
    //$query = 'select ImageID,Title,Description,PATH from travelimage WHERE Title regexp "a"';
    $result = $mysqli->query($query);
    $imgArray = array();
    $imgNum = 0;
    while ($row = $result->fetch_array(MYSQLI_ASSOC)) {
        $imgArray[$imgNum]['imageID'] = $row['ImageID'];
        $imgArray[$imgNum]['title'] = utf8_encode($row['Title']);
        $imgArray[$imgNum]['description'] = utf8_encode($row['Description']);
        $imgArray[$imgNum]['path'] = $row['PATH'];
        $imgNum++;
    }

    /*echo $titleQuery;
    echo "\n";
    echo $descriptionQuery;*/
    echo json_encode($imgArray);
    /*echo '<pre>';
    print_r($imgArray);
    echo '</pre>';*/

}