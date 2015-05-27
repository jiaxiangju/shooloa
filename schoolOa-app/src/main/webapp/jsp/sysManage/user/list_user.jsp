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
            <a class="mini-button" iconCls="icon-add" onclick="app.user.show('add');">增加</a>
            <a class="mini-button" iconCls="icon-edit" onclick="app.user.show();">编辑</a>
            <a class="mini-button" iconCls="icon-remove" onclick="app.user.del();">删除</a>       
          </td>
          <td style="white-space:nowrap;">
            <input id="userKey" class="mini-buttonedit delete" onButtonClick="javascript:this.setText(null);this.setValue(null);" 
               style="width: 180px;" emptyText="请输入姓名" onenter="app.user.f.search();"/>   
            <a class="mini-button" onclick="app.user.f.search()">查询</a>
          </td>
        </tr>
      </table>           
    </div>
   
<div class="mini-fit">    
  <div  id="gridUser" class="mini-datagrid" style="width:100%;height:100%;" sizeList="[5,10,20,50]"
        pageSize="10" multiSelect="false" onlyCheckSelection="true" ajaxOptions="{type:'get'}">
    <div property="columns">
      <div type="checkcolumn" ></div>        
      <div field="loginName" width="100" headerAlign="center" allowSort="true">登录名</div>    
      <div field="userName" width="100" headerAlign="center" allowSort="true">姓名</div> 
      <div field="sex" name="sex" width="100" headerAlign="center" allowSort="true">性别</div> 
      <div field="age" width="100" headerAlign="center" allowSort="true">年龄</div>
      <div field="title" name="title" width="100" headerAlign="center" allowSort="true">职称</div>
      <div field="salary" name="salary" width="100" headerAlign="center" allowSort="true">薪资</div>
      <div field="email" width="120" headerAlign="center" allowSort="true">邮箱</div> 
      <div field="eduLev" name="eduLev" width="120" headerAlign="center" allowSort="true">学历</div>
      <div field="polStatus" name="polStatus" width="120" headerAlign="center" allowSort="true">政治面貌</div> 
      <div field="createDate" width="100" headerAlign="center" dateFormat="yyyy-MM-dd" allowSort="true">创建日期</div>       
    </div>
  </div>
</div>
  
  
  <!-- 新增,修改对话框 -->
  <div id="userInfoDiv" class="mini-window" style="width: 507px;height: 248px" showMaxButton="false"
       showShadow="true" showModal="true" allowResize="true" allowDrag="true" title="用户管理 ">
    <input id="id" name="id" class="mini-hidden" value="">
    <form id="userInfo">
      <input name="password" class="mini-hidden" id="pwd" required="true" style="width:180px;" value="123456"/>
      <table class="form_table">
        <tbody>
          <tr style="height:30px;">
            <th>登录名</th>
            <td>
              <input id="loginName" name="loginName" class="mini-textbox" required="true" style="width:180px;" />
            </td>
            <th>姓名</th>
            <td>
              <input id="userName" name="userName" class="mini-textbox" required="true" style="width:180px;" />
            </td>
          </tr>
          <tr style="height:30px;">
            <th>性别</th>
            <td>
              <input name="sex" class="mini-combobox" value="0" textField="text" valueField="id" style="width:180px;"
                   data="[{'id':'0', 'text': '男'},{'id':'1', 'text': '女'}]" onValuechanged="comboboxChanged(this)">
            </td>
            <th>年龄</th>
            <td>
              <input id="age" name="age" class="mini-textbox" required="false" style="width:180px;" />
            </td>
          </tr>
          <tr style="height:30px;">
            <th>职称</th>
            <td>
              <input class="mini-combobox" name="title" value="0" textField="text" valueField="id"
                  data="[{ 'id': '0', 'text': '教授'},
                         { 'id': '1', 'text': '副教授'},
                         { 'id': '2', 'text': '讲师'},
                         { 'id': '3', 'text': '助教'},
                         { 'id': '4', 'text': '教员'},
                         { 'id': '5', 'text': '研究员'}]"
                  allowInput="true" valueFromSelect="true" style="width:180px;" onValuechanged="comboboxChanged(this)">
            </td>
            <th>薪资</th>
            <td>
              <input id="salary" name="salary" class="mini-textbox" required="false" style="width:180px;" />
            </td>
          </tr>
          <tr style="height:30px;">
            <th>学历</th>
            <td>
              <input class="mini-combobox" name="eduLev" value="0" textField="text" valueField="id"
                  data="[{ 'id': '0', 'text': '博士'},
                         { 'id': '1', 'text': '硕士'},
                         { 'id': '2', 'text': '研究生'},
                         { 'id': '3', 'text': '双学士'},
                         { 'id': '4', 'text': '大学本科'}]"
                  allowInput="true" valueFromSelect="true" style="width:180px;" onValuechanged="comboboxChanged(this)">
            </td>
            <th>政治面貌</th>
            <td>
              <input class="mini-combobox" name="polStatus" value="0" textField="text" valueField="id"
                  data="[{ 'id': '0', 'text': '群众'},
                         { 'id': '1', 'text': '团员'},
                         { 'id': '2', 'text': '党员'},
                         { 'id': '3', 'text': '其他'}]"
                  allowInput="true" valueFromSelect="true" style="width:180px;" onValuechanged="comboboxChanged(this)">
            </td>
          </tr>
          <tr style="height:30px;">
            <th>邮箱</th>
            <td>
              <input id="email" name="email" class="mini-textbox" required="false" style="width:180px;" />
            </td>
            <th>角色</th>
            <td>
              <input id="role_edit" name="roleName" class="mini-combobox" required="true" style="width:180px;" 
                   style="width:125px;" valueField="id" textField="roleName" ajaxOptions="{type:'get'}"
                   multiSelect="true" />
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
<script type="text/javascript" src="jsp/sysManage/user/script/user.js"></script>
</html>