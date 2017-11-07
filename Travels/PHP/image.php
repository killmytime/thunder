<?php
include 'connect.php';
//供图片查询

//将被收藏的图片ID存入数组$favorNumArray

$hotImageID = array();
$result = $mysqli->query('SELECT ImageID FROM travelimagefavor GROUP BY ImageID ');
$i = 0;
while ($row = mysqli_fetch_assoc($result)) {
    $hotImageID[$i] = $row['ImageID'];
    $i++;
}
$hotImageInfoNum = 0;
//统计所有热门图片，但是目前热门图片只有几个，qvq，所以其他热门里干脆也设置成随机了
foreach ($hotImageID as $value) {
    //echo getImagePath($id);
    $hotImageInfo[$hotImageInfoNum]['path'] = getImagePath($value);
    $hotImageInfo[$hotImageInfoNum]['title'] = getImageTitle($value);
    $hotImageInfo[$hotImageInfoNum]['description'] = getImageDescription($value);
    $hotImageInfo[$hotImageInfoNum]['ImageID'] = $value;
    $hotImageInfoNum++;
}

$allImageInfoArray = array();//包含所有所有图片的数组
$allImageInfoArrayNum = 0;
$allImageResult = $mysqli->query('SELECT PATH,Title,Description,ImageID FROM travelimage');

while ($row = $allImageResult->fetch_array(MYSQLI_ASSOC)) {
    $allImageInfoArray[$allImageInfoArrayNum] = $row;
    $allImageInfoArrayNum++;
}


//获取图片路径（文件名字）
function getImagePath($imageID)
{
    //由于不是全局变量的原因，在函数中没办法直接用到$mysql,所以new了一个对象
    $mysqli = new mysqli('127.0.0.1', 'root', '998326abc', 'travel');
    $imageQuery = 'SELECT PATH FROM travelimage WHERE ImageID=' . $imageID;
    $result = $mysqli->query($imageQuery);
    $row = $result->fetch_array(MYSQLI_NUM);
    return $row[0];
}
//获取图片名字
function getImageTitle($imageID)
{
    $mysqli = new mysqli('127.0.0.1', 'root', '998326abc', 'travel');
    $imageQuery = 'SELECT Title FROM travelimage WHERE ImageID=' . $imageID;
    $result = $mysqli->query($imageQuery);
    $row = $result->fetch_array(MYSQLI_NUM);
    return utf8_encode($row[0]);
}
//获取图片介绍
function getImageDescription($imageID)
{
    $mysqli = new mysqli('127.0.0.1', 'root', '998326abc', 'travel');
    $imageQuery = 'SELECT Description FROM travelimage WHERE ImageID=' . $imageID;
    $result = $mysqli->query($imageQuery);
    $row = $result->fetch_array(MYSQLI_NUM);
    return utf8_encode($row[0]);
}

//输出其他热门到网页(随机)
function outputHotImageRow($imageArrayArg, $imageArrayNumArg)
{
    echo '<div class="row">';
    for ($x = 0; $x < 3; $x++) {
        $index = rand(0, $imageArrayNumArg - 1);
            echo '<div class="col-sm-6 col-md-4">
                   <div class="thumbnail">';
            echo '<a href="imageDetail.php?imageID=' . $imageArrayArg[$index]['ImageID'] . '">';
            echo '<img class="hot-img" style="height:200px" src="travel-images/small/' . $imageArrayArg[$index]['PATH']
                . '" alt="' . utf8_encode($imageArrayArg[$index]['Description']) . '"></a>';
            echo '<div class="caption">';
            echo '<h3>' . utf8_encode($imageArrayArg[$index]['Title']) . '</h3>';
            echo '<p>' . utf8_encode($imageArrayArg[$index]['Description']) . '</p></div>';
            echo '<p><a href="imageDetail.php?imageID=' . $imageArrayArg[$index]['ImageID'];
            echo '" class="btn btn-primary" role="button">View details &raquo;</a></p>';
            echo '</div></div>';
        }
    echo '</div>';
}
//输出随机图片到最新上传
function outputRandomImageRow($imageArrayArg, $imageArrayNumArg)
{
    echo '<div class="row">';
    for ($x = 0; $x < 12; $x++) {
        $index = rand(0, $imageArrayNumArg - 1);

        echo '<div class="col-sm-2 col-md-1 newimg-div">';
        echo '<a href="imageDetail.php?imageID=' . $imageArrayArg[$index]['ImageID'] . '">';
        echo '<img src="travel-images/square-small/' . $imageArrayArg[$index]['PATH'] . '"></a>';
        echo '</div>';
    }
    echo '</div>';
}

?>