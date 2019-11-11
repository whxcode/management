function getXhr() {
    return new XMLHttpRequest();
}

function myAddEvent(obj,event,fun) {
    if(obj.attachEvent) {
        obj.attachEvent("on"+event,function() {
            fun();
            return false;
        }.bind(obj));
    }else {
        obj.addEventListener(event,function() {
            fun.call(obj);
        }.bind(obj),false);
    }
}

function create(v) {
    let reg=/<[a-z]+\b/;
    let reg1=/[a-z]+/i;
    let str1=v.match(v)[0];
    let ele=str1.match(reg1);
    return document.createElement(ele[0]);
}

function getByClass(parent,Class) {
    let aEle=parent.getElementsByTagName("*");
    let ret=[];
    for(let i=0;i<aEle.length;i++) {
        if(aEle[i].className==Class) {
            ret.push(aEle[i]);
        }
    }
    return ret;
}

function getElements(parent,str) {
    return parent.querySelectorAll(str);
}

function $$(v) {
    return new M(v);
}

function M(v) {
    this.elements=[];
    switch(typeof v) {
        case "function":
            myAddEvent(window,"load",v);
            break;
        case "string":
                switch (v.charAt(0)) {
                    case "<":
                        this.elements.push(create(v));
                        break;
                    default:
                        this.elements=document.querySelectorAll(v);
                        break;
                }
           break;
        default:
            this.elements.push(v);
            break;
    }
    return this;
}

M.prototype.forEach=function(fun) {
    for(let i=0;i<this.elements.length;i++) {
        fun&&fun.call(this,this.elements[i]);
    }
    return this;
};

M.prototype.appendTo=function(parent,fun) {
        this.forEach((e)=>{
            parent.appendChild(e);
        });
    fun&&fun();
    return this;
};

M.prototype.hover=function(funOver,funOut) {
    let info=null;
        funOver&&funOut?this.forEach((e)=>{
            myAddEvent(e,"mouseover",funOver);
            myAddEvent(e,"mouseout",funOut);
        }):info="参数不正确";
    return info?info:this;
};

M.prototype.toggle=function() {
    let arg_=arguments;
    if(arg_.length>0) {
        this.forEach((e)=>{
            myAddEvent(e,"click",(function(count){
                return  function() {
                    arg_[count++%arg_.length].call(e);
                }
            }(0)));
        })
    }else {

    }
};

M.prototype.remove=function() {
        let parent=this.elements[0].parentNode;
        this.forEach((e)=>{
            parent.removeChild(e);
        });
    };

M.prototype.getParent=function() {
    let parent=this.elements[0].parentNode;
    console.log(parent);
    return $$(parent);

};

function animate(ele,json,fun) {
    clearInterval(ele.timeId);
    ele.timeId=setInterval(function(){   //设置原属id
        var key=true;
        for(var attr in json){
            if(attr==="zIndex"){ele.style[attr]=json[attr];
            }else if(attr==="opacity"){
                var current=((function(ele,attr){
                    return window.getComputedStyle(ele,null)[attr];
                }(ele,attr)))*100;
                console.log(current);
                var target=json[attr]*100;
                var speed=(target-current)/10;
                speed=speed>0?Math.ceil(speed):Math.floor(speed);
                current+=speed;
                ele.style[attr]=current/100;
            }else {
                var current=(function(ele,attr){return parseInt(window.getComputedStyle(ele,null)[attr]);
                }(ele,attr));
                var target=json[attr];
                var speed=(target-current)/10;
                speed=speed>0?Math.ceil(speed):Math.floor(speed);
                current+=speed;
                ele.style[attr]=current+"px";
            }
            if(current!=target){
                key=false;
            }
        }
        if(key){
            clearInterval(ele.timeId);
            if(fun){
                fun();
            }
        }
    },20);
}

function forIn() {


}

function Time(v) {
    let reg=/[0-9]{0,}.[0-9]+s/;
    return v.match(reg)[0];
}

M.prototype.css=function(json) {
    for(let key in json) {
        this.forEach((e) => {
           e.style[key]=json[key];
        });
    }
    return this;
};

M.prototype.text=function(v) {
  this.forEach((e)=>{
      e.innerText=v;
  })  ;
  return this;
};

M.prototype.getThis=function() {
    return this.elements;
};

M.prototype.get=function(v) {
    return this.elements[v];
};

M.prototype.click=function(fun) {
    this.forEach((e)=>{
        myAddEvent(e,"click",fun);
    });
    return this;
};

M.prototype.getClass=function() {
    let c=null;
    this.forEach((e)=>{
       c=e.getAttribute("class");
    });
    return c;
};
M.prototype.getAttr=function(attr) {
    let c=null;
    this.forEach((e)=>{
        c=window.getComputedStyle(e)[attr];
    });
    return c;
};

M.prototype.removeClassAll=function() {
    this.forEach((e)=>{
        e.setAttribute("class","");
    });
    return this;
};

get=(e)=>{
  return e.getAttribute("class");
};

set=(e,v)=>{
    e.setAttribute("class",v);
};

/****设置class***/
M.prototype.setClass=function(v) {
    let getClass=[];
    let setClass=v.split(" ");
    let arrEle=[];
    let count=0;
    let i=0;
    this.forEach((e)=>{
        getClass.push(get(e).split(" "));
        arrEle.push(e);
        count++;
    });
    count-=1;
    (function(){
        let set=new Set(setClass.concat(getClass[i]));
        let setArr=[...set];
        console.log(setArr);
        arrEle[i].setAttribute("class",setArr.join(" "));
        return i++ === count?null:arguments.callee();
    }());
    return this;
};



M.prototype.setAttr=function(k,v) {
    this.forEach((e)=>{
        e.setAttribute(k,v);
    });
    return this;
};

M.prototype.removeAttr=function(v) {
    this.forEach((e)=>{
        e.removeAttribute(v);
    });
    return this;
};

function getIndex(one) {
    let parent=one.elements[0].parentNode;
    let child=parent.children;
    let index=-1;
    for(let i=0;i<child.length;i++) {
        if(one.elements[0]==child[i]) {
            index=i;
            break;
        }
    }
    return index;
}

M.prototype.next=function() {
    let self=this;
    let parent=this.elements[0].parentNode;
    let child=parent.children;
    let index=getIndex(this);
   return $$(child[++index]);
};

M.prototype.visib=function() {
    let parent=this.elements[0].parentNode;
    let child=parent.children;
    let index=getIndex(this);
    return $$(child[--index]);
};

M.prototype.getChild=function() {
  let child=this.elements[0].children;
  let newObj=$$();
  newObj.elements=[];
    for(let i=0;i<child.length;i++) {
       newObj.elements.push(child[i]);
    }
 return newObj;
};

M.prototype.on=function(event,fun) {
  this.forEach((e)=>{
      myAddEvent(e,event,fun);
  });
};
