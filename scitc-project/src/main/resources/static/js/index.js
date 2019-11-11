function getColor() {
    let color=["0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"];
    let newColor="#";
    let len=color.length;
    let i=0;
    (function(){
        newColor+=color[parseInt(Math.random()*len)];
        return i++ === 5 ? null : arguments.callee();
    }());
    return newColor;
}
/***头部交互开始***/
$(function() {

    let  pc= window.matchMedia('(min-width:1200px)');
    let  ml= window.matchMedia('(min-width:992px)');
    let  phone= window.matchMedia('(min-width:768px)');
    let contaner=$(".container");
    let leftNav=$(".left-nav");
    let pageContent=$(".page-content");
        randomColor();
    function randomColor() {
        setInterval(function() {
            $(".lineColor").css({background:`linear-gradient(to right,${getColor()},
            ${getColor()},
            ${getColor()},
            ${getColor()}, 
            ${getColor()},
             ${getColor()},
            ${getColor()})`});
        },5000)
    }

});
/***头部交互结束***/
function selfClose() {
   $(".self-layui").addClass("layui-show");
   return false;
}  /***事件函数 方便于移除**/

$(".layui-tab-title").on("mouseover",'.layui-this',function() {
    $(this).find(".layui-tab-close").on("click",selfClose);
});

$(".layui-tab-title").on("mouseout",'.layui-this',function() {
    $(this).find(".layui-tab-close").unbind("click",selfClose);
});

$(".layui-tab-title").on("mouseout",'.layui-this',function() {
    $(this).find(".layui-tab-close").on("click",function() {
        console.log(123123123);
        return false;
    })
});
