window.addEventListener('load', function () {
    document.getElementById('home').className="";
    document.getElementById('browse').className='active';
    document.getElementById('search').className='';

    let browserObj;

    //AJAX异步传递
    let xmlhttp = new XMLHttpRequest();
    xmlhttp.open('GET', 'PHP/filter.php', true);
    xmlhttp.send();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            browserObj = JSON.parse(xmlhttp.responseText);
            loadAll();
        }
    }

    //加载页面
    function loadAll() {
        let imgNum = 0;
        let imgArray = new Array();
        let continent = browserObj.Continent;
        let contiInner = '<option value="null">Continent</option>';
        let counInner = '<option value="null">Country</option>';
        let cityInner = '<option value="null">City</option>';
        for (let i = 0; i < continent.length; i++) {
            contiInner += '<option value="' + continent[i].ContinentName + '">' +
                continent[i].ContinentName + '</option>';
            let country = continent[i].Country;
            for (let j = 0; j < country.length; j++) {
                counInner += '<option value="' + country[j].CountryName + '">' + country[j].CountryName + '</option>';
                let city = country[j].City;
                for (let k = 0; k < city.length; k++) {
                    cityInner += '<option value="' + city[k].CityName + '">' + city[k].CityName + '</option>';
                    let img = city[k].img;
                    for (let m = 0; m < img.length; m++) {
                        imgArray[imgNum] = new Object();
                        imgArray[imgNum].imageID = img[m].ImageID;
                        imgArray[imgNum].path = img[m].PATH;
                        imgNum++;
                    }
                }
            }
        }
        let continentSelect = document.getElementById('continent-select');
        let countrySelect = document.getElementById('country-select');
        let citySelect = document.getElementById('city-select');
        continentSelect.innerHTML = contiInner;
        countrySelect.innerHTML = counInner;
        citySelect.innerHTML = cityInner;
        loadImage();

        continentSelect.addEventListener('change', continentLoad, true);
        countrySelect.addEventListener('change', countryLoad, true);
        citySelect.addEventListener('change', cityLoad, true);

        //大洲下拉框变化时的函数
        function continentLoad() {
            let continentSelectedIndex = continentSelect.selectedIndex;
            let continentSelectedValue = continentSelect.options[continentSelectedIndex].value;
            if (continentSelectedValue == 'null')
                loadAll();
            else {
                imgNum = 0;
                imgArray = new Array;
                let continentObject = getContinentObject();
                let countryArray = continentObject.Country;
                let countrySelectInner = '<option value="null">Country</option>';
                let citySelectInner = '<option value="null">City</option>';
                for (let i = 0; i < countryArray.length; i++) {
                    countrySelectInner += '<option value="' + countryArray[i].CountryName + '">'
                        + countryArray[i].CountryName + '</option>';
                    let cityArray = countryArray[i].City;
                    for (let j = 0; j < cityArray.length; j++) {
                        citySelectInner += '<option value="' + cityArray[j].CityName + '">'
                            + cityArray[j].CityName + '</option>';
                        let img = cityArray[j].img;
                        for (let k = 0; k < img.length; k++) {
                            imgArray[imgNum] = new Object();
                            imgArray[imgNum].imageID = img[k].ImageID;
                            imgArray[imgNum].path = img[k].PATH;
                            imgNum++;
                        }
                    }
                }
                countrySelect.innerHTML = countrySelectInner;
                citySelect.innerHTML = citySelectInner;
                loadImage();
            }
        }

        //国家下拉框变化时的函数
        function countryLoad() {
            let countrySelectedIndex = countrySelect.selectedIndex;
            let countrySelectedValue = countrySelect.options[countrySelectedIndex].value;
            if (countrySelectedValue == 'null')
                continentLoad();
            else {
                imgNum = 0;
                imgArray = new Array;
                let continentOptions = continentSelect.options;
                let continentObject = getCountryObject();
                let cityArray = continentObject.Country.City;
                let citySelectInner = '<option value="null">City</option>';
                for (let i = 0; i < continentOptions.length; i++) {
                    if (continentOptions[i].value == continentObject.ContinentName)
                        continentSelect.selectedIndex = i;
                }
                for (let j = 0; j < cityArray.length; j++) {
                    citySelectInner += '<option value="' + cityArray[j].CityName + '">'
                        + cityArray[j].CityName + '</option>';
                    let img = cityArray[j].img;
                    for (let k = 0; k < img.length; k++) {
                        imgArray[imgNum] = new Object();
                        imgArray[imgNum].imageID = img[k].ImageID;
                        imgArray[imgNum].path = img[k].PATH;
                        imgNum++;
                    }
                }
                citySelect.innerHTML = citySelectInner;
                loadImage();
            }
        }

        //城市下拉框变化时的函数
        function cityLoad() {
            let citySelectedIndex = citySelect.selectedIndex;
            let citySelectedValue = citySelect.options[citySelectedIndex].value;
            if (citySelectedValue == 'null')
                countryLoad();
            else {
                imgNum = 0;
                imgArray = new Array;
                let continentOptions = continentSelect.options;
                let countryOptions = countrySelect.options;
                let continentObject = getCityObject();
                let countryObject = continentObject.Country;
                let cityObject = countryObject.City;
                let img = cityObject.img;

                for (let i = 0; i < continentOptions.length; i++) {
                    if (continentOptions[i].value == continentObject.ContinentName)
                        continentSelect.selectedIndex = i;
                }
                for (let j = 0; j < countryOptions.length; j++) {
                    if (countryOptions[j].value == continentObject.Country.CountryName)
                        countrySelect.selectedIndex = j;
                }
                for (let k = 0; k < img.length; k++) {
                    imgArray[imgNum] = new Object();
                    imgArray[imgNum].imageID = img[k].ImageID;
                    imgArray[imgNum].path = img[k].PATH;
                    imgNum++;
                }
                loadImage();
            }
        }

        //获得大洲
        function getContinentObject() {
            let continentSelectedIndex = document.getElementById('continent-select').selectedIndex;
            let continentSelectedValue = document.getElementById('continent-select').options[continentSelectedIndex].value;
            if (!(continentSelectedValue == 'null')) {
                let continentObject = browserObj.Continent;
                for (let i = 0; i < continentObject.length; i++) {
                    if (continentObject[i].ContinentName == continentSelectedValue)
                        return continentObject[i];
                }
            }
        }

        //获得国家
        function getCountryObject() {
            let countrySelectedIndex = document.getElementById('country-select').selectedIndex;
            let countrySeletedValue = document.getElementById('country-select').options[countrySelectedIndex].value;
            if (!(countrySeletedValue == 'null')) {
                let continentObject = browserObj.Continent;
                let returnObject = new Object();
                for (let i = 0; i < continentObject.length; i++) {
                    let countryObject = continentObject[i].Country;
                    for (let j = 0; j < countryObject.length; j++) {
                        if (countryObject[j].CountryName == countrySeletedValue) {
                            returnObject.ContinentName = continentObject[i].ContinentName;
                            returnObject.Country = countryObject[j];
                            return returnObject;
                        }
                    }
                }
            }
        }

        //获得城市
        function getCityObject() {
            let citySelectedIndex = document.getElementById('city-select').selectedIndex;
            let citySelectedValue = document.getElementById('city-select').options[citySelectedIndex].value;
            if (!(citySelectedValue == 'null')) {
                let continentObject = browserObj.Continent;
                let returnObject = new Object();
                for (let i = 0; i < continentObject.length; i++) {
                    let countryObject = continentObject[i].Country;
                    for (let j = 0; j < countryObject.length; j++) {
                        let cityObject = countryObject[j].City;
                        for (let k = 0; k < cityObject.length; k++) {
                            if (cityObject[k].CityName == citySelectedValue) {
                                returnObject.ContinentName = continentObject[i].ContinentName;
                                returnObject.Country = new Object();
                                returnObject.Country.CountryName = countryObject[j].CountryName;
                                returnObject.Country.City = cityObject[k];
                                return returnObject;
                            }
                        }
                    }
                }
            }
        }

        //加载图片
        function loadImage() {
            let pageNum;
            pageNum = (('' + (imgArray.length / 16)).length == 1) ? (imgArray.length / 16)
                : (Math.floor(imgArray.length / 16) + 1);
            let currentImg = 0;
            let pageBTDiv = document.getElementById('pageBT-div');
            let imgDiv = document.getElementById('img-div');

            pageBTDiv.innerHTML = outputPageBT(pageNum);
            if (pageNum > 1) {
                imgDiv.innerHTML = outputImgFullPage(1, true);
                for (let i = 1; i < pageNum - 1; i++)
                    imgDiv.innerHTML += outputImgFullPage(i + 1, false);
                imgDiv.innerHTML += outputLastPage(pageNum, imgArray.length % 16, false);
            }
            else
                imgDiv.innerHTML = outputLastPage(pageNum, imgArray.length % 16, true);

            $('#pageBT-ul li').each(function () {
                $(this).click(function () {
                    changePage();
                })
            });

            function outputImgFullPage(index, isDisplay) {
                let className = isDisplay ? '' : 'hide';
                let imgFullPage = '<div id="page' + index + '" class="' + className + '">';
                for (let i = 0; i < 4; i++)
                    imgFullPage += outputImgFullRow();
                imgFullPage += '</div>';
                return imgFullPage;
            }

            function outputImgFullRow() {
                let imgFullRow = '<div class="row">';
                for (let i = 0; i < 4; i++) {
                    imgFullRow += '<div class="col-md-3">';
                    imgFullRow += '<a href="imageDetail.php?imageID=' + imgArray[currentImg].imageID + '">';
                    imgFullRow += '<img src="travel-images/square-medium/' + imgArray[currentImg].path + '">';
                    imgFullRow += '</a></div>';
                    currentImg++;
                }
                imgFullRow += '</div>';
                return imgFullRow;
            }

            function outputLastPage(pageIndex, imgNumLeft, isDisplay) {
                let rowNum;
                rowNum = (('' + (imgNumLeft / 4)).length == 1) ? (imgNumLeft / 4) : (Math.floor(imgNumLeft / 4) + 1);
                let lastRowImgNum = imgNumLeft % 4;
                let className = isDisplay ? '' : 'hide';
                let lastPage = '<div id="page' + pageIndex + '" class="' + className + '">';
                if (rowNum > 1) {
                    for (let i = 0; i < rowNum - 1; i++)
                        lastPage += outputImgFullRow();
                    if (lastRowImgNum > 0) {
                        lastPage += '<div class="row">';
                        for (let j = 0; j < lastRowImgNum; j++) {
                            lastPage += '<div class="col-md-3">';
                            lastPage += '<a href="imageDetail.php?imageID=' + imgArray[currentImg].imageID + '">';
                            lastPage += '<img src="travel-images/square-medium/' + imgArray[currentImg].path + '">';
                            lastPage += '</a></div>';
                            currentImg++;
                        }
                        for (let k = 0; k < 4 - lastRowImgNum; k++)
                            lastPage += '<div class="col-md-3"></div>';
                        lastPage += '</div>';
                    }
                    else if (lastRowImgNum == 0)
                        lastPage += outputImgFullRow();
                }
                else if (rowNum == 1) {
                    if (lastRowImgNum > 0) {
                        lastPage += '<div class="row">';
                        for (let j = 0; j < lastRowImgNum; j++) {
                            lastPage += '<div class="col-md-3">';
                            lastPage += '<a href="imageDetail.php?imageID=' + imgArray[currentImg].imageID + '">';
                            lastPage += '<img src="travel-images/square-medium/' + imgArray[currentImg].path + '">';
                            lastPage += '</a></div>';
                            currentImg++;
                        }
                        for (let k = 0; k < 4 - lastRowImgNum; k++)
                            lastPage += '<div class="col-md-3"></div>';
                        lastPage += '</div>';
                    }
                    else if (lastRowImgNum == 0)
                        lastPage += outputImgFullRow();
                }
                else if (rowNum == 0) {
                    for (let i = 0; i < 4; i++)
                        lastPage += outputImgFullRow();
                }
                lastPage += '</div>';
                return lastPage;
            }
        }
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
});