window.addEventListener('load', function () {
    let inputForm = document.getElementById('inputForm');
    let imgUploadInput = document.getElementById('imgupload-input');
    let nameInput = document.getElementById('name-input');
    let descriptionInput = document.getElementById('description-input');
    let cityInput = document.getElementById('city-input');
    let countryInput = document.getElementById('country-input');
    let continentInput = document.getElementById('continent-input');
    let latitudeInput = document.getElementById('latitude-input');
    let longitudeInput = document.getElementById('longitude-input');
    let submitBT = document.getElementById('submitBT');

    imgUploadInput.addEventListener('change', imgPreview, false);
    submitBT.addEventListener('click', upload, false);


    function imgPreview() {
        let img = document.getElementById('pre-img');
        img.style.width = '300px';
        img.style.height = '200px';
        img.src = window.URL.createObjectURL(imgUploadInput.files[0]);
    }

    function upload() {
        //如果没有图片
        if (!imgUploadInput.files[0])
            alert('Please upload a image.');
        else if (nameInput.value == '')
            alert('Please enter the photo name.');
        else if (descriptionInput.value == '')
            alert('Please enter the description.');
        else if (cityInput.value == '')
            alert('Please enter the city.');
        else if (countryInput.value == '')
            alert('Please enter the country.');
        else if (continentInput.value == '')
            alert('Please enter the continent.');
        else if (latitudeInput.value == '')
            alert('Please enter the latitude.');
        else if (longitudeInput.value == '')
            alert('Please enter the longitude.');

        else {
            let uploadData = new FormData;
            uploadData.append('upload', 1);
            uploadData.append('imageName', nameInput.value);
            uploadData.append('imageDescription', descriptionInput.value);
            uploadData.append('city', cityInput.value);
            uploadData.append('country', countryInput.value);
            uploadData.append('continent', continentInput.value);
            uploadData.append('latitude', latitudeInput.value);
            uploadData.append('longitude', longitudeInput.value);
            uploadData.append('uploadFile', imgUploadInput.files[0]);

            let xmlhttpUpload = new XMLHttpRequest();
            xmlhttpUpload.open('POST', 'PHP/uploadImage.php', true);
            xmlhttpUpload.send(uploadData);
            xmlhttpUpload.onreadystatechange = function () {
                if (xmlhttpUpload.readyState == 4 && xmlhttpUpload.status == 200) {
                    alert(xmlhttpUpload.responseText);
                    if (xmlhttpUpload.responseText == 'Success!')
                        window.location.href = 'myPhoto.php';
                }
            }
        }
    }
}, true);