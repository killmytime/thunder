$(document).ready(function () {
    $.ajaxSettings.traditional=true;
    let cartArray=new Array();
    function checkboxNum() {
        $("input[name='checkbox']:checked").each(function () {
            cartArray.push($(this).val());
        })
    }
    $("#pay").click(function () {
        checkboxNum();
        $.get("./cartPay",{"cartList":cartArray});
        //神TM要刷新两次，不知为何只刷一次还是显示缓存页，反正一次两次也看不出来
        history.go(0);
        history.go(0);
    });
    $("#delete").click(function () {
        checkboxNum();
        $.get("./cartDelete",{"cartList":cartArray});
        history.go(0);
        history.go(0);

    })
});