<?php
include 'connect.php';

//收藏图片和取消收藏
$isfavor = $_GET['isfavor'];
$uid = $_GET['UID'];
$imageID = $_GET['imageID'];

$insertQuery = 'INSERT INTO travelimagefavor SET UID=' . $uid . ', ImageID=' . $imageID;
$deleteQuery = 'DELETE FROM travelimagefavor WHERE UID=' . $uid . ' AND ImageID=' . $imageID;

$query = ($isfavor == 'true') ? $insertQuery : $deleteQuery;
$mysqli->query($query);
?>