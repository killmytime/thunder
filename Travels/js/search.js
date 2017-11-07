window.addEventListener('load', function () {
    document.getElementById('home').className="";
    document.getElementById('browse').className='';
    document.getElementById('search').className='active';

    let imgArray = new Array;
    let filterBT = document.getElementById('filterBT');

    loadImg();
    filterBT.addEventListener('click', search, true);

    function search() {
        let radios = document.getElementsByName('filterWay');
        let filterWay;
        let searchKey;
        for (let i = 0; i < radios.length; i++) {
            if (radios[i].checked)
                filterWay = radios[i].value;
        }
        if (filterWay == 'title')
            searchKey = document.getElementById('titleInput').value;
        else if (filterWay == 'description')
            searchKey = document.getElementById('descriptionInput').value;

        let xmlhttp = new XMLHttpRequest();
        xmlhttp.open('GET', 'PHP/getSearchResult.php?filterWay=' + filterWay + '&searchKey=' + searchKey, true);
        xmlhttp.send();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                imgArray = JSON.parse(xmlhttp.responseText);
                loadImg();
                document.getElementById('titleInput').value = '';
                searchKey = document.getElementById('descriptionInput').value = '';
            }
        }
    }
    
    function loadImg() {
        let pageNum;
        pageNum = (('' + (imgArray.length / 6)).length == 1) ? (imgArray.length / 6)
            : (Math.floor(imgArray.length / 6) + 1);
        let currentImg = 0;
        let pageBTDiv = document.getElementById('pageBT-div');
        let imgDiv = document.getElementById('img-div');

        pageBTDiv.innerHTML = outputPageBT(pageNum);
        if (pageNum > 1) {
            imgDiv.innerHTML = outputImgFullPage(1, true);
            for (let i = 1; i < pageNum - 1; i++)
                imgDiv.innerHTML += outputImgFullPage(i + 1, false);
            imgDiv.innerHTML += outputLastPage(pageNum, imgArray.length % 6, false);
        }
        else
            imgDiv.innerHTML = outputLastPage(pageNum, imgArray.length % 6, true);
        $('#pageBT-ul li').each(function () {$(this).click(function () {changePage();})});
        
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
            imgFullRow += '<a href="imageDetail.php?imageID=' + imgArray[currentImg].imageID + '">';
            imgFullRow += '<div class="col-md-8">';
            imgFullRow += '<h3>' + imgArray[currentImg].title + '</h3>';
            imgFullRow += '<p>' + imgArray[currentImg].description + '</p>';
            imgFullRow += '</div></a></div>';
            currentImg++;
            return imgFullRow;
        }
        function outputLastPage(pageIndex, imgNumLeft, isDisplay) {
            let className = isDisplay ? '' : 'hide';
            let lastPage = '<div id="page' + pageIndex + '" class="' + className + '">';
            for (let i = 0; i < imgNumLeft; i++)
                lastPage += outputImgFullRow();
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