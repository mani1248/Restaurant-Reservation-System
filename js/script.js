/**
 * Created by Manish on 11/18/2015.
 */


$(document).ready(function() {
    $("#ownerLogin").click(function (e) {
        e.preventDefault();
        window.location.href = "ownerHome.html";
    });
});


$(document).ready(function () {
    $(".clearFields").keyup(function () {
        $(this).next().toggle(Boolean($(this).val()));
    });
    $(".clearFields").toggle(Boolean($(".clearFields").val()));
    $(".clearFields").click(function () {
        $(this).prev().val('').focus();
        $(this).hide();
    });
});