<?php
include 'PHP/image.php';
?>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <?php
    include 'part/quotes.php';
    ?>
    <link rel="stylesheet" type="text/css" href="css/correction.css">
    <script src="js/home.js"></script>
</head>
<body>
<header>
    <?php
    include 'part/nav.php';
    ?>
</header>
<section class="marginTop">
    <!-- Carousel
   ================================================== -->
    <div id="myCarousel" class="carousel slide" data-ride="carousel"
         style="width: 60vw;margin-left: auto;margin-right: auto">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner marginTop">
            <div class="item active">
                <a href="imageDetail.php?imageID=<?php echo $hotImageInfo[0]['ImageID']; ?>">
                    <img class="first-slide" src="travel-images/large/<?php echo $hotImageInfo[0]['path']; ?>"
                         alt="First slide">
                </a>
                <div class="container">
                    <div class="carousel-caption">
                        <h1><?php echo $hotImageInfo[0]['title']; ?></h1>
                        <p><?php echo $hotImageInfo[0]['description']; ?></p>
                        <p><a class="btn btn-lg btn-primary"
                              href="imageDetail.php?imageID=<?php echo $hotImageInfo[0]['ImageID']; ?>" role="button">View
                                More</a></p>
                    </div>
                </div>
            </div>
            <div class="item">
                <a href="imageDetail.php?imageID=<?php echo $hotImageInfo[1]['ImageID']; ?>">
                    <img class="second-slide" src="travel-images/large/<?php echo $hotImageInfo[1]['path']; ?>"
                         alt="Second slide">
                </a>
                <div class="container">
                    <div class="carousel-caption">
                        <h1><?php echo $hotImageInfo[1]['title']; ?></h1>
                        <p><?php echo $hotImageInfo[1]['description']; ?></p>
                        <p><a class="btn btn-lg btn-primary"
                              href="imageDetail.php?imageID=<?php echo $hotImageInfo[1]['ImageID']; ?>" role="button">View
                                More</a></p>
                    </div>
                </div>
            </div>
            <div class="item">
                <a href="imageDetail.php?imageID=<?php echo $hotImageInfo[2]['ImageID']; ?>">
                    <img class="third-slide" src="travel-images/large/<?php echo $hotImageInfo[2]['path']; ?>"
                         alt="Third slide">
                </a>
                <div class="container">
                    <div class="carousel-caption">
                        <h1><?php echo $hotImageInfo[2]['title']; ?></h1>
                        <p><?php echo $hotImageInfo[2]['description']; ?></p>
                        <p><a class="btn btn-lg btn-primary"
                              href="imageDetail.php?imageID=<?php echo $hotImageInfo[2]['ImageID']; ?>" role="button">View
                                More</a></p>
                    </div>
                </div>
            </div>
        </div>
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div><!-- /.carousel -->
</section>
<section>
    <hr/>
    <p class="textCenter">Other Hot Recommend <span id="otherHot" class="glyphicon glyphicon-refresh"
                                                    aria-hidden="true"></span></p>
    <hr/>
    <div class="bt-container">

    </div>
    <div class="Container container-fluid" id="hotImageContainerSection">

        <div id="otherHot1" class="image-container-active">
            <?php
            outputHotImageRow($allImageInfoArray, $allImageInfoArrayNum);
            ?>
        </div>
        <div id="otherHot2" class="image-container">
            <?php
            outputHotImageRow($allImageInfoArray, $allImageInfoArrayNum);
            ?>
        </div>
        <div id="otherHot3" class="image-container">
            <?php
            outputHotImageRow($allImageInfoArray, $allImageInfoArrayNum);
            ?>
        </div>
    </div>
</section>
<section class="Container">
    <div class="bt-container">
        <hr/>
        <p class="textCenter">New Upload <span id="otherNewUpload" class="glyphicon glyphicon-refresh"
                                               aria-hidden="true"></span></p>
        <hr/>
    </div>
    <div id="newImgContainerDiv1" class="image-container-active">
        <?php
        outputRandomImageRow($allImageInfoArray, $allImageInfoArrayNum);
        outputRandomImageRow($allImageInfoArray, $allImageInfoArrayNum);
        ?>
    </div>
    <div id="newImgContainerDiv2" class="image-container">
        <?php
        outputRandomImageRow($allImageInfoArray, $allImageInfoArrayNum);
        outputRandomImageRow($allImageInfoArray, $allImageInfoArrayNum);
        ?>
    </div>
    <div id="newImgContainerDiv3" class="image-container">
        <?php
        outputRandomImageRow($allImageInfoArray, $allImageInfoArrayNum);
        outputRandomImageRow($allImageInfoArray, $allImageInfoArrayNum);
        ?>
    </div>
</section>
<footer>
    <?php
    include 'part/footer.php';
    ?>
</footer>
</body>
</html>