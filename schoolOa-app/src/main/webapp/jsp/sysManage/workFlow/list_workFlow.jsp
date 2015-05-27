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
</head>
<body>
    
    <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td style="width:100%;">
                        <a class="mini-button" iconCls="icon-add" onclick="app.f.wf.add()">增加</a>
                        <a class="mini-button" iconCls="icon-edit" onclick="app.f.wf.edit()">编辑</a>
                        <a class="mini-button" iconCls="icon-remove" onclick="app.f.wf.remove()">删除</a>       
                    </td>
                    <td style="white-space:nowrap;">
                        <input id="key" class="mini-textbox" emptyText="请输入流程名称 流程编码" style="width:150px;" onenter="onKeyEnter"/>   
                        <a class="mini-button" onclick="app.f.wf.search()">查询</a>
                    </td>
                </tr>
            </table>           
        </div>
    <div class="mini-fit">
	    <div id="wfData" class="mini-datagrid" style="width:100%;height:100%;" allowResize="true"
	        url="resource/workFlow" onlyCheckSelection="true"  idField="id" multiSelect="true" ajaxOptions="{type:'get'}" >
	        <div property="columns">
	           <div type="indexcolumn"></div>
	            <div type="checkcolumn" ></div>        
	            <div field="flowCode" width="120" headerAlign="center" >流程编码</div> 
	            <div field="flowName" width="120" headerAlign="center" >流程名称</div>    
	            <div field="type" width="120" headerAlign="center" >流程类型</div> 
	            <div field="desc" width="120" headerAlign="center" >备注</div>  
	            <div name="action" width="120" headerAlign="center" align="center" renderer="app.f.wf.onActionRenderer">操作</div>  
	        </div>
	    </div>
    </div>
    <script type="text/javascript" src="jsp/sysManage/workFlow/script/workFlow.js"></script>   
</body>
</html>