$(document).ready(function () {
    let currPage=$("#current").val();
    let before=parseInt(currPage)-1;
    let next=parseInt(currPage)+1;
    $("#before").click(function () {
        window.location.href="./artists.jsp?currentPage="+before;
    });
    $("#next").click(function () {
        window.location.href="./artists.jsp?currentPage="+next;
    })
})