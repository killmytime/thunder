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
    let modifyData = new FormData;

    imgUploadInput.addEventListener('change', imgPreview, true);
    submitBT.addEventListener('click', modify, true);
    function imgPreview() {
        let img = document.getElementById('pre-img');
        img.style.width = '300px';
        img.style.height = '200px';
        img.src = window.URL.createObjectURL(imgUploadInput.files[0]);
        modifyData.append('modifyFile', imgUploadInput.files[0]);
    }
    function modify() {
        if (inputForm.className == '')
            alert('You can only modify your own image.');
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
            modifyData.append('modify', 1);
            modifyData.append('imageID', inputForm.className);
            modifyData.append('imageName', nameInput.value);
            modifyData.append('imageDescription', descriptionInput.value);
            modifyData.append('city', cityInput.value);
            modifyData.append('country', countryInput.value);
            modifyData.append('continent', continentInput.value);
            modifyData.append('latitude', latitudeInput.value);
            modifyData.append('longitude', longitudeInput.value);

            let xmlhttpModify = new XMLHttpRequest();
            xmlhttpModify.open('POST', 'PHP/modifyImage.php', true);
            xmlhttpModify.send(modifyData);
            xmlhttpModify.onreadystatechange = function () {
                if (xmlhttpModify.readyState == 4 && xmlhttpModify.status == 200) {
                    alert(xmlhttpModify.responseText);
                    if (xmlhttpModify.responseText == 'Success!')
                        window.location.href = 'myPhoto.php';
                }
            }
        }
    }
}, true);