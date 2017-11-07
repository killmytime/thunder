window.addEventListener('load', load, true);

function load() {
    let unfavorBT = document.getElementById('unfavor');
    let favorBT = document.getElementById('favor');
    if (document.getElementById('accountName')) {
        let uid = document.getElementById('uid-container').className;
        let imageid = document.getElementById('imageid-container').className;

        unfavorBT.addEventListener('click', function () {
            favorImg(false);
        }, true);
        favorBT.addEventListener('click', function () {
            favorImg(true);
        }, true);

        function favorImg(isfavor) {
            let xmlhttp = new XMLHttpRequest();
            xmlhttp.open('GET', 'PHP/favor.php?isfavor=' + isfavor + '&UID=' + uid + '&imageID=' + imageid, true);
            xmlhttp.send();
            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
                    window.location.reload();
            }
        }
    }
    else {
        unfavorBT.addEventListener('click', function () {
            alert('Please login firstly.');
        }, true);
        favorBT.addEventListener('click', function () {
            alert('Please login firstly.');
        }, true);
    }
}