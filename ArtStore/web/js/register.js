$(document).ready(function(){
    $("#registerSubmitBtn").click(function(){
        //发送 post 请求
        $.post("./register", // path
            $("#registerForm").serialize(), //data
            function (data) { // callback function
                data = JSON.parse(data); // get js object
                if (data.result === "OK") {
                    window.location.href = "./index.jsp"; //回到首页
                    //javascript:history.go(-1)返回上一页
                }
                else {
                    alert(data.error);
                }
            }
        );

        // $.get, $.ajax
    });
    //Email
    //Regex for email:/^[a-z0-9]+@([a-z0-9]+\.)+[a-z]{2,}$/
    let emailElement = document.getElementById('email');
    let emailDiv = document.getElementById('email-div');
    let emailHelp = document.getElementById('email-help');
    emailElement.addEventListener('input', function (event) {
        let email = emailElement.value;
        if (email.match(/^[a-z0-9]+@([a-z0-9]+\.)+[a-z]{2,}$/)) {
            emailDiv.classList.add('has-success');
            emailDiv.classList.remove('has-error');
            emailHelp.innerHTML = "";
        } else {
            emailHelp.innerHTML = 'Invalid Email. A valid email should be "A@B.C", where each of A, B and C is a string made up of character a-z, A-Z and 0-9. C should be at least 2 characters long.';
            emailDiv.classList.add('has-error');
        }
    });

    // Username:
    // Regex for username: /^[^ ].{2,}[^ ]$/
    let usernameElement = document.getElementById('username');
    let usernameDiv = document.getElementById('username-div');
    let usernameHelp = document.getElementById(('username-help'));
    usernameElement.addEventListener('input', function (event) {
        let username = usernameElement.value;
        if (username.match(/^[^ ].{2,}[^ ]$/)) {
            usernameDiv.classList.add('has-success');
            usernameDiv.classList.remove('has-error');
            usernameHelp.innerHTML = "";
        } else {
            usernameHelp.innerHTML = 'Invalid username. Username should contain at least 4 characters, and should not leave blank.'
        }
    });

    // Password:
    // Regex for password: /^(?=.*\d)(?=.*[A-Za-z]).{6,}$/
    let passwordElement = document.getElementById('password');
    let passwordDiv = document.getElementById('password-div');
    let passwordHelp = document.getElementById('password-help');
    passwordElement.addEventListener('input', function (event) {
        let password = passwordElement.value;
        if (password.match(/^(?=.*\d)(?=.*[A-Za-z]).{6,}$/)) {
            //感觉蛮奇怪的，[^ ]不能匹配空格，[^]可以匹配空格。。。,不作限制的话也可以匹配空格
            // 不过这里反正不能让用户填空格
            //必须包含大小写字母和数字的组合，不能使用特殊字符，长度不小于6
            passwordDiv.classList.add('has-success');
            passwordDiv.classList.remove('has-error');
            passwordHelp.innerHTML = "";
        } else {
            passwordHelp.innerHTML = "Invalid password.The length of password should be longer than 4 and the password must contain capital or small letters and numbers but no special element.."
        }
    })

    //PasswordConfirm
    let passwordConfirmElement=document.getElementById("passwordConfirm");
    let passwordConfirmDiv=document.getElementById("passwordConfirm-div");
    let passwordConfirmHelp=document.getElementById("passwordConfirm-help");
    passwordConfirmElement.addEventListener('input',function (event) {
        let passwordConfirm=passwordElement.value;
        if (passwordConfirm.isEqual(passwordElement.value)){
            passwordConfirmDiv.classList.add('has-success');
            passwordConfirmDiv.classList.remove('has-error');
            passwordConfirmHelp.innerHTML="";
        }else {
            passwordConfirmHelp.innerHTML="Make sure confirm password is the same with your password."
        }
    })
});