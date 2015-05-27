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
      <table style="width:100%;padding:0px;">
        <tr>
          <td style="width:100%;">
            <a class="mini-button" iconCls="icon-add" onclick="app.role.show('add');">增加</a>
            <a class="mini-button" iconCls="icon-edit" onclick="app.role.show();">编辑</a>
            <a class="mini-button" iconCls="icon-remove" onclick="app.role.del();">删除</a>       
          </td>
          <td style="white-space:nowrap;">
            <input id="roleKeyWords" name="keyWords" class="mini-buttonedit delete" onButtonClick="javascript:this.setText(null);this.setValue(null);" 
               style="width: 180px;" emptyText="请输入关键字" onenter="app.role.f.search();"/>   
            <a class="mini-button" onclick="app.role.f.search()">查询</a>
          </td>
        </tr>
      </table>           
    </div>

<div class="mini-fit">  
  <div  id="gridRole" class="mini-datagrid" style="width:100%;height:100%;" sizeList="[5,10,20,50]"
        pageSize="10" multiSelect="false" onlyCheckSelection="true" ajaxOptions="{type:'get'}">
    <div property="columns">
      <div type="checkcolumn" ></div>        
      <div field="roleCode" headerAlign="center" allowSort="true">角色编码</div>    
      <div field="roleName" headerAlign="center" allowSort="true">角色名称</div> 
      <div field="desc" headerAlign="center" allowSort="false">备注</div> 
      <div name="action" headerAlign="center" align="center"  renderer="app.role.roleGridFormatter"  allowSort="false">操作</div> 
    </div>
  </div>
</div>
  
  <!-- 新增,修改对话框 -->
  <div id="roleInfoDiv" class="mini-window" style="width: 507px;height: 248px" showMaxButton="false"
       showShadow="true" showModal="true" allowResize="true" allowDrag="true" title="角色管理 ">
    <input id="id" name="id" class="mini-hidden" value="">
    <form id="roleInfo">
      <table class="form_table">
        <tbody>
          <tr style="height:30px;">
            <th>角色编码：</th>
            <td>
              <input id="roleCode" name="roleCode" class="mini-textbox" required="true" style="width:180px;" />
            </td>
          </tr>
          <tr style="height:30px;">
            <th>角色名称：</th>
            <td>
              <input id="roleName" name="roleName" class="mini-textbox" required="true" style="width:180px;" />
            </td>
          </tr>
          <tr>
			<td class="role_cell">备注</td>
			<td>
			  <input name="desc" class="mini-hidden"/>
			  <textarea name="desc" class="mini-textarea" rows="3" cols="19" text-wrap="virtual" style="width:180px;"></textarea>
			</td>
		  </tr>
      </tbody>
      <tfoot>
        <tr>
          <td colspan="4" style="text-align: center;">
            <input type="button" value="保存" id="btnSubmit">
            <input id="closeWin" type="button" value="取消">
          </td>
        </tr>
      </tfoot>
    </table>
  </form>
</div>

</body>
<script type="text/javascript" src="jsp/sysManage/role/script/role_oa.js"></script>
</html>