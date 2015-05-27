var app = {f:{},v:{}};
  
/**
 * ready方法
 */
$(function(){
  mini.parse();
  if(!($('#errMsg').text() === '' || $('#errMsg').text() === null)) {
	  mini.alert($('#errMsg').text());
  }
  $("input[name='username']").attr('value', app.f.getCookie('username'));
  $("input[name='password']").attr('value', app.f.getCookie('password'));
  if($("input[name='username']").val() != '') {
    $('#remPwd').attr('checked','true');
  }
});

/**
 * 回车事件
 */
$(document).keypress(function(e) {
  if(e.which == 13) {  
    app.f.sub();
  }
}); 
	
/**
 * get cookie
 */
app.f.getCookie = function(attr) {
  //读cookie属性，这将返回文档的所有cookie
  var allcookies = document.cookie.split('; ');
  // 查找名为attr的cookie,找到则返回它的值，找不到则返回空字符串
  if(attr === 'password') {
    return '';
  }else {
    for (var i = 0; i < allcookies.length; i++) {
      var temp = allcookies[i].split('=');
      if (temp[0] === attr) {
        return unescape(temp[1]);
      }
    }
  }
  return '';
};
	
/**
 * set cookie
 */
app.f.setCookie = function(iname,ivalue,hours,path) {
  var name = escape(iname);
  var value = escape(ivalue);
  var expires = new Date();
  expires.setTime(expires.getTime() + hours*3600000);
  path = path === "" ? "" : "" + path;
  var _expires = (typeof hours) == "string" ? "" : ";expires=" + expires.toUTCString();
  document.cookie = name + "=" + value + _expires + path;
};

/**
 * 点击登录时验证
 */
app.f.rememberClick = function() {
  if(document.getElementById('remPwd').checked) {
    app.f.setCookie("username", $("input[name='username']").val(), 24, "/");
    app.f.setCookie("password", $("input[name='password']").val(), 24, "/");
  }else {
    app.f.setCookie("username", $("input[name='username']").val(), 0, "/");
    app.f.setCookie("password", $("input[name='password']").val(), 0, "/");
  }
};
	
/**
 * 提交登录
 */
app.f.sub = function() {
  var form = new  mini.Form("#loginForm");
  form.validate();
  if (form.isValid() == false) return;
  app.f.rememberClick();
  document.getElementById('loginForm').submit();
};

/**
 * 登录
 * @param e
 */
//function onLoginClick(e) {
//    var form = new mini.Form("#loginWindow");
//
//    form.validate();
//    if (form.isValid() == false) return;
//
//    loginWindow.hide();
//    mini.loading("登录成功，马上转到系统...", "登录成功");
//    setTimeout(function () {
//        window.location = "../outlooktree/outlooktree.html";
//    }, 1500);
//}

/**
 * 重置
 */
app.f.reset = function() {
  $("input[name='username']").val('');
  $("input[name='password']").val('');
};


/**
 * 密码验证
 * @param e
 */
function onPwdValidation(e) {
    if (e.isValid) {
        if (e.value.length < 6) {
            e.errorText = "密码不能少于6个字符";
            e.isValid = false;
        }
    }
}
