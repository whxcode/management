let userKey=false;
let passKey=false;
let btn=$$("input[type='password']");
let logo=$$(".login-login h1 img");
$$(function() {
    logo.click(function() {
        window.location.href="http://www.scitc.com.cn/";
    })
});



/***底部第三方功能***/
$$(function() {
    $$("footer section ul li").click(function() {
        $$('<div></div>').css({
            "position":"fixed",
            "width":"100%",
            "height":"100%",
            "top":0,
            "left":0,
            "textAlign":"center",
            "background":"rgba(0,0,0,.1)",
            'fontSize':"3rem",
            "padding":"20rem 1rem"
        }).text("此功能开发中...请耐心等待...").appendTo(document.body,function(){
            $$(".login").css({transition:"all .4s",filter:"blur(1rem)"});
        }).click(function() {
            $$(this).remove();
            $$(".login").css({transition:"all .4s",filter:"blur(0)"});
        });
    });
});