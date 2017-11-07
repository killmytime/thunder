window.addEventListener('load', function () {
    let imgArray = new Array;
    let pageBTDiv = document.getElementById('pageBT-div');
    let imgDiv = document.getElementById('img-div');

    if (document.getElementById('accountName')) {
        let xmlhttp = new XMLHttpRequest();
        xmlhttp.open('GET', 'PHP/getMyFavorites.php', true);
        xmlhttp.send();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                if (xmlhttp.responseText == 'nofavorite')
                    imgDiv.innerHTML = '<h3 class="nosignin">You have no favorite photos.</h3>';
                else if (xmlhttp.responseText == 'nosign')
                    imgDiv.innerHTML = '<h3 class="nosignin">Please login firstly.</h3>';
                else {
                    imgArray = JSON.parse(xmlhttp.responseText);
                    loadImg();
                }
            }
        }
    }
    else
        imgDiv.innerHTML = '<h3 class="nosignin">Please login firstly.</h3>';

    function loadImg() {
        let pageNum;
        pageNum = (('' + (imgArray.length / 6)).length == 1) ? (imgArray.length / 6)
            : (Math.floor(imgArray.length / 6) + 1);
        let currentImg = 0;

        pageBTDiv.innerHTML = outputPageBT(pageNum);
        if (pageNum > 1) {
            imgDiv.innerHTML = outputImgFullPage(1, true);
            for (let i = 1; i < pageNum - 1; i++)
                imgDiv.innerHTML += outputImgFullPage(i + 1, false);
            imgDiv.innerHTML += outputLastPage(pageNum, imgArray.length % 6, false);
        }
        else
            imgDiv.innerHTML = outputLastPage(pageNum, imgArray.length % 6, true);
        $('#pageBT-ul li').each(function () {
            $(this).click(function () {
                changePage();
            })
        });
        $('.delete-btn').each(function () {
            $(this).click(function () {
                deletefavor();
            })
        });

        function deletefavor() {
            let eventBT = event.currentTarget;
            let imageID = eventBT.title;
            let parentRow = eventBT.parentNode.parentNode.parentNode;
            let phpurl = 'PHP/delete.php?deleteType=deleteFavor&imageID=' + imageID;
            let xmlhttpDelete = new XMLHttpRequest();
            xmlhttpDelete.open('GET', phpurl, true);
            xmlhttpDelete.send();
            xmlhttpDelete.onreadystatechange = function () {
                if (xmlhttpDelete.readyState == 4 && xmlhttpDelete.status == 200) {
                    if (xmlhttpDelete.responseText == 'success')
                        parentRow.parentNode.removeChild(parentRow);
                }
            }
        }

        function outputImgFullPage(index, isDisplay) {
            let className = isDisplay ? '' : 'hide';
            let imgFullPage = '<div id="page' + index + '" class="' + className + '">';
            for (let i = 0; i < 6; i++)
                imgFullPage += outputImgFullRow();
            imgFullPage += '</div>';
            return imgFullPage;
        }

        function outputImgFullRow() {
            let imgFullRow = '<div class="row img-row">';
            imgFullRow += '<div class="col-md-4">';
            imgFullRow += '<a href="imageDetail.php?imageID=' + imgArray[currentImg].imageID + '">';
            imgFullRow += '<img src="travel-images/square-medium/' + imgArray[currentImg].path + '">';
            imgFullRow += '</a></div>';
            imgFullRow += '<div class="col-md-6">';
            imgFullRow += '<a href="imageDetail.php?imageID=' + imgArray[currentImg].imageID + '">';
            imgFullRow += '<div><h3>' + imgArray[currentImg].title + '</h3>';
            imgFullRow += '<p>' + imgArray[currentImg].description + '</p></div></a></div>';
            imgFullRow += '<div class="col-md-2"><p>';
            imgFullRow += '<a class="btn btn-lg btn-danger delete-btn" title="' + imgArray[currentImg].imageID +
                '">Delete</a></p></div></div>';
            currentImg++;
            return imgFullRow;
        }

        function outputLastPage(pageIndex, imgNumLeft, isDisplay) {
            let className = isDisplay ? '' : 'hide';
            let lastPage = '<div id="page' + pageIndex + '" class="' + className + '">';
            if (imgNumLeft == 0) {
                for (let i = 0; i < 6; i++)
                    lastPage += outputImgFullRow();
            }
            else {
                for (let i = 0; i < imgNumLeft; i++)
                    lastPage += outputImgFullRow();
            }
            lastPage += '</div>';
            return lastPage;
        }

        function outputPageBT(pageNum) {
            let pageBT = '<nav aria-label="Page navigation">';
            pageBT += '<ul id="pageBT-ul" class="pagination">';
            pageBT += '<li class="page-BT"><a href="#" aria-label="Previous">';
            pageBT += '<span aria-hidden="true">&laquo;</span></a></li>';
            pageBT += '<li class="page-BT active"><a>' + 1 + '</a></li>';
            for (let i = 1; i < pageNum; i++)
                pageBT += '<li class="page-BT"><a href="#">' + (i + 1) + '</a></li>';
            pageBT += '<li class="page-BT"><a href="#" aria-label="Next">';
            pageBT += '<span aria-hidden="true">&raquo;</span></a></li></ul></nav>';
            return pageBT;
        }

        function changePage() {
            let clickIndex;
            let currentIndex;
            let pageBTArray = document.getElementById('pageBT-ul').getElementsByTagName('li');
            let pageArray = new Array(pageBTArray.length - 2);
            for (let index = 0; index < pageArray.length; index++)
                pageArray[index] = document.getElementById('page' + (index + 1));
            for (let i = 0; i < pageBTArray.length; i++) {
                if (pageBTArray[i].className == 'page-BT active')
                    currentIndex = i;
                if (pageBTArray[i] == event.currentTarget)
                    clickIndex = i;
            }
            if (clickIndex == 0) {
                if (currentIndex > 1) {
                    for (let j = 0; j < pageBTArray.length; j++)
                        pageBTArray[j].className = 'page-BT';
                    for (let k = 0; k < pageArray.length; k++)
                        pageArray[k].className = 'hide';
                    pageBTArray[currentIndex - 1].className = 'page-BT active';
                    pageArray[currentIndex - 2].className = '';
                }
            }
            else if (clickIndex == pageBTArray.length - 1) {
                if (currentIndex < pageBTArray.length - 2) {
                    for (let j = 0; j < pageBTArray.length; j++)
                        pageBTArray[j].className = 'page-BT';
                    for (let k = 0; k < pageArray.length; k++)
                        pageArray[k].className = 'hide';
                    pageBTArray[currentIndex + 1].className = 'page-BT active';
                    pageArray[currentIndex].className = '';
                }
            }
            else {
                for (let j = 0; j < pageBTArray.length; j++)
                    pageBTArray[j].className = 'page-BT';
                for (let k = 0; k < pageArray.length; k++)
                    pageArray[k].className = 'hide';
                pageBTArray[clickIndex].className = 'page-BT active';
                pageArray[clickIndex - 1].className = '';
            }
        }
    }
}, true);