$(document).ready(function(){
    $(".owl-carousel").owlCarousel({
        "items":1,
        'nav':false,
        'loop':true,
        'autoplay':true,
        'autoplayTimeout':5000,
        'autoplayHoverPause':true,
    });
});


let clickLi=$(".main li");
let logo=$('.main header img');
clickLi.click(function() {
    let href=$(this).attr("_href");
    window.open(href);
});
logo.click(function() {
    let href=$(this).attr("_href");
    window.open(href);
});