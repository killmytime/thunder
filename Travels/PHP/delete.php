<?php
//删除数据库记录
include 'connect.php';
session_start();
if (isset($_GET['deleteType'])) {
    switch ($_GET['deleteType']) {
        //取消收藏
        case 'deleteFavor' :
            $query = 'DELETE FROM travelimagefavor WHERE ImageID='.$_GET['imageID'].' AND UID='.$_SESSION['UID'];
            if ($mysqli->query($query))
                echo 'success';
            break;

        //删除已上传图片
        case 'deleteMyPhoto' :
            $queryFirst = 'DELETE FROM travelimage WHERE ImageID='.$_GET['imageID'];
            $querySecond = 'DELETE FROM travelimagefavor WHERE ImageID='.$_GET['imageID'];
            if ($mysqli->query($queryFirst)) {
                if ($mysqli->query($querySecond))
                    echo 'success';
            }
            break;
    }
}