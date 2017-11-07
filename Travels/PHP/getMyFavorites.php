<?php
include 'connect.php';
session_start();
//找到当前用户的收藏图片，json形式返回
if (isset($_SESSION['UID'])) {
    $queryFirst = 'SELECT ImageID FROM travelimagefavor WHERE UID=' . $_SESSION['UID'];
    $resultFirst = $mysqli->query($queryFirst);
    if ($resultFirst->num_rows > 0) {
        $imageInfoArray = array();
        $imageNum = 0;
        while ($row = $resultFirst->fetch_array(MYSQLI_NUM)) {
            $querySecond = 'SELECT Title,Description,PATH FROM travelimage WHERE ImageID=' . $row[0];
            $resultSecond = $mysqli->query($querySecond);
            $imageInfoRow = $resultSecond->fetch_array(MYSQLI_ASSOC);
            $imageInfoArray[$imageNum]['title'] = $imageInfoRow['Title'];
            $imageInfoArray[$imageNum]['description'] = isset($imageInfoRow['Description']) ?
                $imageInfoRow['Description'] : '';
            $imageInfoArray[$imageNum]['path'] = $imageInfoRow['PATH'];
            $imageInfoArray[$imageNum]['imageID'] = $row[0];
            $imageNum++;
        }
        echo json_encode($imageInfoArray);
    } else
        echo 'nofavorite';
} else
    echo 'nosign';