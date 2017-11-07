window.addEventListener('load', load, true);
function load() {
    document.getElementById('home').className="active";
    document.getElementById('browse').className='';
    document.getElementById('search').className='';
    let otherHot = document.getElementById('otherHot');
    let otherNewUpload = document.getElementById('otherNewUpload');

    otherHot.addEventListener('click', changeHotImage, true);
    otherNewUpload.addEventListener('click', changeNewUploadImage, true);
//改变激活的状态达到更替的目的
    function changeHotImage() {
        let albumContainerDiv = new Array();
        let currentIndex = -1;
        let nextIndex = -1;
        for (let i = 0; i < 3; i++) {
            albumContainerDiv[i] = document.getElementById('otherHot' + (i + 1));
        }
        for (let i = 0; i < 3; i++) {
            if (albumContainerDiv[i].className == 'image-container-active')
                currentIndex = i;
            albumContainerDiv[i].className = 'image-container';
        }
        switch (currentIndex) {
            case 0:
                nextIndex = 1;
                break;
            case 1:
                nextIndex = 2;
                break;
            case 2:
                nextIndex = 0;
                break;
        }
        albumContainerDiv[nextIndex].className = 'image-container-active';
    }

    function changeNewUploadImage() {
        let newImgContainerDiv = new Array();
        let currentIndex = -1;
        let nextIndex = -1;
        for (let i = 0; i < 3; i++) {
            newImgContainerDiv[i] = document.getElementById('newImgContainerDiv' + (i + 1));
        }
        for (let i = 0; i < 3; i++) {
            if (newImgContainerDiv[i].className == 'image-container-active')
                currentIndex = i;
            newImgContainerDiv[i].className = 'image-container';
        }
        switch (currentIndex) {
            case 0:
                nextIndex = 1;
                break;
            case 1:
                nextIndex = 2;
                break;
            case 2:
                nextIndex = 0;
                break;
        }
        newImgContainerDiv[nextIndex].className = 'image-container-active';
    }
}