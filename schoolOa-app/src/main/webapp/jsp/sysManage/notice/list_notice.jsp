<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <jsp:include page="/common/style.jsp" />
    <link href="script/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />
    <jsp:include page="/common/script.jsp" />
    <script type="text/javascript" src="jsp/sysManage/notice/script/notice_oa.js"></script>
</head>
<body>

    <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
      <table style="width:100%;padding:0px;">
        <tr>
          <td style="width:100%;">
            <a class="mini-button" iconCls="icon-add" onclick="app.notice.f.add();">增加</a>
            <a class="mini-button" iconCls="icon-edit" onclick="app.notice.f.edit();">编辑</a>
            <a class="mini-button" iconCls="icon-remove" onclick="app.notice.del();">删除</a>       
          </td>
          <td style="white-space:nowrap;">
            <input id="keyWords" name="keyWords" class="mini-buttonedit delete" onButtonClick="javascript:this.setText(null);this.setValue(null);" 
               style="width: 180px;" emptyText="请输入关键字" onenter="app.notice.f.search();"/>   
            <a class="mini-button" onclick="app.notice.f.search()">查询</a>
          </td>
        </tr>
      </table>           
    </div>
  
<div class="mini-fit">
  <div  id="gridNotice" class="mini-datagrid" style="width:100%;height:100%;" sizeList="[5,10,20,50]"
        pageSize="10" multiSelect="false" onlyCheckSelection="true" ajaxOptions="{type:'get'}">
    <div property="columns">
      <div type="checkcolumn" ></div>        
      <div field="title" headerAlign="center" width="60%" allowSort="true">公告标题</div>    
      <div field="createDate" dateFormat="yyyy-MM-dd hh:mm:ss" width="15%" headerAlign="center" allowSort="true">发布日期</div> 
      <div field="creater" width="15%" headerAlign="center" allowSort="false">发布人</div> 
      <div name="action" width="10%" headerAlign="center" align="center"  renderer="app.notice.f.view"  allowSort="false">操作</div> 
    </div>
  </div>
</div>
</body>
</html>