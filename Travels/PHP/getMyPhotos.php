<?php
include 'connect.php';
session_start();
//查询当前用户上传的图片json返回
if(isset($_SESSION['UID'])) {
    $query = 'SELECT ImageID,Title,Description,PATH FROM travelimage WHERE UID='.$_SESSION['UID'];
    $result = $mysqli->query($query);
    $imgArray = array();
    $imgNum = 0;
    while ($row = mysqli_fetch_assoc($result)) {
        $imgArray[$imgNum]['imageID'] = $row['ImageID'];
        $imgArray[$imgNum]['title'] = $row['Title'];
        $imgArray[$imgNum]['description'] = $row['Description'];
        $imgArray[$imgNum]['path'] = $row['PATH'];
        $imgNum++;
    }

    echo json_encode($imgArray);
}