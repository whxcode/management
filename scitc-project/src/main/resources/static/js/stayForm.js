$(()=> {
    layui.use('form', function(){
    });
});

$(".submit").click(function(ev) {
    let value=$(".group>input");
    let objValue={};
    let temp=[];
    let str="请把以下选项填写完整:";
    let nameArr=[];
    for(let i=0;i<value.length;i++) {
        if(value[i].id=="remark") {
            continue;
        }
        objValue[value[i].placeholder]=value.eq(i).val();
    }
    for(let key in objValue) {
        if(objValue[key]==="") {
            temp.push(key);
        }
    }
    if(temp.length===0) {
    }else {
        $(".alertInfo").find('p').remove();
        $(".alertInfo").find('button').remove();
        for(let i=0;i<temp.length;i++) {
            nameArr.push($('<p></p>').text(temp[i]));
        }
        for(let i=0;i<temp.length;i++) {
            nameArr[i].appendTo($(".alertInfo"));
        }
        $('<button>').text(`确 认`).appendTo($(".alertInfo"));
        $(".alertInfo").find('h3').css({"display":""});
        $(".alertInfo").animate({"width":'100%','opacity':'1'}).css("display","block");
        $(".infoText").css({"filter":"blur(6px)"});
        ev.preventDefault();
    }
});

$(".alertInfo").click(function() {
    $(".alertInfo").find('p').remove();
    $(".alertInfo").find('button').remove();
    $(".alertInfo").find('h3').css({"display":"none"});
    $(this).animate({"width":'0'},function() {

        $(this).css({"display":"none"});
    });
    $(".infoText").css({"filter":"blur(0)"});
});