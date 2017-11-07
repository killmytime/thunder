window.addEventListener('load', registerPage, true);

function registerPage() {

    document.getElementById('home').className="";
    document.getElementById('browse').className='';
    document.getElementById('search').className='';

    let registerBT = document.getElementById('registerBT');
    registerBT.addEventListener('click', register, true);
    function register() {
        let email = document.getElementById('inputRegisterEmail').value;
        let passwordFirst = document.getElementById('inputRegisterPasswordFirst').value;
        let passwordSecond = document.getElementById('inputRegisterPasswordSecond').value;
        let emailRegExp = /^[a-z0-9]+@([a-z0-9]+\.)+[a-z]{2,}$/;

        if (email == '' || passwordFirst == '' || passwordSecond == '')
            alert('Please enter your email or password.');
        else if (passwordFirst != passwordSecond)
            alert('Please make sure two passwords are the same.');
        else if (!emailRegExp.test(email))
            alert('please check your email format.');
        //各种字符匹配感觉有点麻烦，网上大多是判断带下划线的密码，我也就这么干了。。。
        else if (/[^a-zA-Z0-9_]/.test(passwordFirst))
            alert('Illegal character in password!Please only use number, letter and underline.');
        else if (!checkStrong(passwordFirst))
            alert('Make your password stronger.Your password must contain ' +
                'at least two of number, letter and underline and no less than 5 characters.');
        else {
            let xmlhttp = new XMLHttpRequest();
            xmlhttp.open('GET', 'PHP/registerConfirmation.php?email=' + email + '&pass=' + passwordFirst, true);
            xmlhttp.send();
            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    if (xmlhttp.responseText == "true") {
                        window.location.href = 'home.php';
                        alert('Register successfully!');
                    }
                    else
                        alert(xmlhttp.responseText);
                }
            }
        }

    }

    function checkStrong(password) {
        if (password.length < 5) return false;
        let modes = 0;
        if (/[0-9]/.test(password)) modes++; //数字
        if (/[a-z]/.test(password)) modes++; //小写
        if (/[A-Z]/.test(password)) modes++; //大写 
        if (/{}/.test(password)) modes++;

        switch (modes) {
            case 0:
            case 1:
                return false;
            case 2:
            case 3:
            case 4:
                return true;
        }
    }
}