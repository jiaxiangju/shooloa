<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
	<title>计算机系自动化办公系统-登录</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <link href="jsp/login/css/demo.css" rel="stylesheet" type="text/css" />
    <link href="jsp/login/css/login.css" rel="stylesheet" type="text/css" />
    <link href="script/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
    body
    {
        width:100%;height:100%;margin:0;overflow:hidden;
    }
    </style>
</head>
<script src="script/boot.js" type="text/javascript"></script>
<body >
  <div id="login_content">
    <div class="login_div_text">
      <form action="login.do" method="post" id="loginForm">
        <ul>
          <li>
            <b>用户名：</b>
            <input id="username" name="username" class="mini-textbox" required="true" maxlength="14"/>
          </li>
          <li>
            <b>密　码：</b>
            <input id="password" onvalidation="onPwdValidation" name="password" class="mini-password" required="true" maxlength="14"/>
          </li>
          <li>
            <input id="remPwd"  name="remPwd" type="checkbox" class="login_checkbox"/>记住用户名
           </li>
          <li>
            <b>　　　　</b>
            <input class="login_btn" type="button" value="登 录" onclick="app.f.sub()" onMouseOver="this.className='login_btn_mouseover'" onmouseout="this.className='login_btn'"/>
            <input class="login_btn" type="button" value="重 置" onclick="app.f.reset()"  onMouseOver="this.className='login_btn_mouseover'"onmouseout="this.className='login_btn'"/>
          </li>
        </ul>
      </form>
    <label id="errMsg" style="display:none">${errMsg}</label>
  </div>
</div>
</body>
<script type="text/javascript" src="script/jquery.min.js"></script>
<script type="text/javascript" src="jsp/login/script/login.js"></script>
</html>
