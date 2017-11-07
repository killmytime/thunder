$(document).ready(function () {
    $("#save").click(function () {
        $.post("./modify", // path
            $("#modifyForm").serialize()//data
         );
        alert("Success!");
        window.location.href = "./profile.jsp";
    })
})