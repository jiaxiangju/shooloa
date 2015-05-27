<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
	<title>计算机系自动化办公系统</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
 	<jsp:include page="../../common/style.jsp" />
    <jsp:include page="../../common/script.jsp" />
    <link href="script/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />
    
    <style type="text/css">
    body{
        margin:0;padding:0;border:0;width:100%;height:100%;overflow:hidden;
    }    
    .header
    {
        background:url(images/header.jpg) repeat-x 0 -1px;
        height:60px;
    }

    </style>   
</head>
<!--Layout-->
<div id="layout1" class="mini-layout" style="width:100%;height:100%;">

    <div class="header" region="north" height="70" showSplit="false" showHeader="false">
        <h1 style="margin:0;padding:15px;cursor:default;font-family:微软雅黑,黑体,宋体;">计算机系自动化办公系统</h1>
        <div style="position:absolute;top:18px;right:10px;">
            <a class="mini-button mini-button-iconTop" iconCls="icon-add" onclick="onQuickClick" plain="true">申请</a>    
            <a class="mini-button mini-button-iconTop" iconCls="icon-edit" onclick="app.f.addNotice();"  plain="true" >发布公告</a>
            <span style="color:#000;margin-left:70px">欢迎：</span><span style="color:#0e2d5f">${user.userName}</span> 
            <a href="logout.do" style="text-decoration:none">&nbsp;[退出]</a>       
        </div>
        
    </div>
    <div title="south" region="south" showSplit="false" showHeader="false" height="30" >
        <div style="line-height:28px;text-align:center;cursor:default"> © 忻州师范学院计算机系版权所有 </div>
    </div>
    
    <div title="center" region="center" style="border:0;" bodyStyle="overflow:hidden;">
        <!--Splitter-->
        <div class="mini-splitter" style="width:100%;height:100%;" borderStyle="border:0;">
            <div size="250" maxSize="250" minSize="250" showCollapseButton="true" style="border:0;">
              <!--OutlookMenu-->
        <div id="leftTree" class="mini-outlookmenu" url="resource/indexMenu" onitemselect="app.f.onItemSelect"
            idField="id" parentField="pid" textField="text" borderStyle="border:0" ajaxOptions="{type:'get'}"
        >
        </div>
                
            </div>
            <div showCollapseButton="false" style="border:0;">
                <!--Tabs-->
                <div id="mainTabs" class="mini-tabs" activeIndex="0" style="width:100%;height:100%;"      
                     plain="false">
                    <div title="首页" url="jsp/index/main.jsp"></div>
                </div>
            </div>        
        </div>
    </div>
</div>
</body>
</html>