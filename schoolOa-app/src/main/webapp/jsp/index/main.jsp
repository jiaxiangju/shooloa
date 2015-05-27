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
    <script type="text/javascript" src="jsp/index/script/main.js"></script>
</head>
<body>
  <div class="mini-fit">
    <div id="win1" class="mini-window" title="个人中心" style="width:50%;height:50%;padding:5px;" 
		iconCls="icon-user" showCollapseButton="false" showShadow="true" showCloseButton="false"
   		showToolbar="false" showFooter="true" showModal="false" allowResize="false" allowDrag="false">
      <div property="footer" style="text-align:right;height:17px;padding:5px;padding-right:15px;">
      </div>
        1<br />
    </div>
    <div id="win2" class="mini-window" title="工作日记看板" style="width:50%;height:50%;padding:5px;" 
		iconCls="icon-collapse" showCollapseButton="false" showShadow="true" showCloseButton="false"
   		showToolbar="false" showFooter="true" showModal="false" allowResize="false" allowDrag="false">
      <div property="footer" style="text-align:right;height:17px;padding:5px;padding-right:15px;">
      </div>
        1<br />
    </div>
    
    
    <div id="win3" class="mini-window" title="公告/通知" style="width:50%;height:50%;padding:5px;" 
		iconCls="icon-tip" showCollapseButton="false" showShadow="true" showCloseButton="false"
   		showToolbar="false" showFooter="true" showModal="false" allowResize="false" allowDrag="false">
      <div property="footer" style="text-align:right;height:17px;padding:5px;padding-right:15px;">
      </div>
      <div id="gridNotice" class="mini-datagrid" style="width:100%;height:100%;" sizeList="[5,10,20,50]"
           pageSize="10" url="resource/announcement" multiSelect="false" onlyCheckSelection="true" ajaxOptions="{type:'get'}">
    	<div property="columns">
          <div field="title" headerAlign="center" width="60%" allowSort="true">公告标题</div>    
          <div field="createDate" dateFormat="yyyy-MM-dd hh:mm:ss" width="15%" headerAlign="center" allowSort="true">发布日期</div> 
          <div field="creater" width="15%" headerAlign="center" allowSort="false">发布人</div> 
          <div name="action" width="10%" headerAlign="center" align="center"  renderer="app.main.f.view"  allowSort="false">操作</div> 
        </div>
      </div>
    </div>
    
    
    <div id="win4" class="mini-window" title="友情链接" style="width:50%;height:50%;padding:5px;" 
		iconCls="icon-goto" showCollapseButton="false" showShadow="true" showCloseButton="false"
   		showToolbar="false" showFooter="true" showModal="false" allowResize="false" allowDrag="false">
      <div property="footer" style="text-align:right;height:17px;padding:5px;padding-right:15px;">
      </div>
        1<br />
    </div>
  </div>
</body>
</html>