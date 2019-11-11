
    /**1 2 3 4 ***/
    let leftNavLi=parent.$("#nav>li>ul>li");

    let selfNavli=$(".footer ul li");




    let inputPass=$("#newPass");

    let summitOn=$(".inputGroup .submit");

    let passKey=false;


    var Pass = document.getElementById('Pass');
    var newPass = document.getElementById('newPass');



    for(let i=0;i<selfNavli.length;i++) {
        $(selfNavli[i]).attr("index",i+1);
    }



    summitOn.click(()=>{
        if(Pass.value !== newPass.value) {
            window.alert("两次输入不同")
          return false
        }
    })





    selfNavli.on("mousedown",function() {
       $(this).css({transform:"scale(1.4)"});
    }).on("mouseup",function() {
        $(this).css({transform:"scale(1)"});
    });

    selfNavli.click(function() {
       let index=parseInt($(this).attr("index"));
        leftNavLi.eq(index).click();
    });





    /***submit***按钮跳转***/


