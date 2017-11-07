function signIn() {
    if (document.getElementById('signIn')) {
        let signInBT = document.getElementById('signIn');
        let register = document.getElementById('register');
        signInBT.addEventListener('click', function () {
            loginConfirmation(document.getElementById('username').value,
                document.getElementById('password').value)
        }, true);
        register.addEventListener('click', function () {
            window.location.href = 'register.php';
        }, true);
    }

    if (document.getElementById('signInPageBT')) {
        let signInPageBT = document.getElementById('signInPageBT');
        signInPageBT.addEventListener('click', function () {
            loginConfirmation(document.getElementById('inputEmail').value,
                document.getElementById('inputPassword').value)
        }, true);
    }
    if (document.getElementById('uploadItem')) {
        let uploadItem = document.getElementById('uploadItem');
        let myPhotosItem = document.getElementById('myPhotosItem');
        let favorItem = document.getElementById('favorItem');
        let logoutItem = document.getElementById('logoutItem');

        uploadItem.addEventListener('click', function () {
            window.location.href = 'upload.php';
        }, true);
        myPhotosItem.addEventListener('click', function () {
            window.location.href = 'myPhoto.php';
        }, true);
        favorItem.addEventListener('click', function () {
            window.location.href = 'favorites.php';
        }, true);
        logoutItem.addEventListener('click', function () {
            let xmlhttpLogout = new XMLHttpRequest();
            xmlhttpLogout.open('GET', 'PHP/logout.php', true);
            xmlhttpLogout.send();
            xmlhttpLogout.onreadystatechange = function () {
                if (xmlhttpLogout.readyState == 4 && xmlhttpLogout.status == 200) {
                    if (xmlhttpLogout.responseText == 'logout')
                        window.location.href = 'home.php';
                }
            }
        }, true);
    }

    function loginConfirmation(username, password) {
        let usernameRegExp = /^[a-z0-9]+@([a-z0-9]+\.)+[a-z]{2,}$/;

        if (username == "" || password == "")
            window.location.href = 'signIn.php';
        else if (usernameRegExp.test(username)) {
            let xmlhttp = new XMLHttpRequest();
            xmlhttp.open('GET', 'PHP/loginConfirmation.php' + '?username=' + username + '&password=' + password, true);
            xmlhttp.send();
            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    if (xmlhttp.responseText + "" == "true")
                        window.location.href = 'home.php';
                    else
                        alert("Your Email or Password is Wrong.");
                }
            }
        }
        else
            alert("Please Enter A Right Email.");
    }
}


window.addEventListener('load', signIn, true);
