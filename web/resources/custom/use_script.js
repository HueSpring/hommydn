//$(document).ready(function () {
//    $(".dropdown").click(
//            function () {
//                $('.dropdown-menu', this).stop(true, true).slideDown("fast");
//                $(this).toggleClass('open');
//            },
//            function () {
//                $('.dropdown-menu', this).stop(true, true).slideUp("fast");
//                $(this).toggleClass('open');
//            }
//    );
//});
$(document).ready(function () {
    $('[data-toggle="tooltip"]').tooltip();
});
$(document).ready(function(){
    $(".dropdown-toggle").dropdown();
});
//$(document).ready(function () {
//    $("#avatar").click(function () {
//        $("#avatarModal").modal();
//    });
//});
$(document).ready(function () {
    $('[data-toggle="popover"]').popover();
});
$(function () {
    $('.navbar-toggle-sidebar').click(function () {
        $('.navbar-nav').toggleClass('slide-in');
        $('.side-body').toggleClass('body-slide-in');
        $('#search').removeClass('in').addClass('collapse').slideUp(200);
    });

    $('#search-trigger').click(function () {
        $('.navbar-nav').removeClass('slide-in');
        $('.side-body').removeClass('body-slide-in');
        $('.search-input').focus();
    });
});


