$(document).ready(function () {
    $.get("./click",{});
    $("#add").click(function () {
        let artworkID=document.getElementById("artworkID");
        $.get("./cart",{});
        history.go(0);
    });
    $("#added").click(function () {
        alert("You have already added to your cart!")
    });
    $("#bought").click(function () {
        alert("this artwork sold out.")
    })
   $("#addNone").click(function () {
       alert("Please Sign in firstly.");
       window.location.href="./login.jsp";
   });
});