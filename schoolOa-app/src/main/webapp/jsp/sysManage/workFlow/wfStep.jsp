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
    <jsp:include page="/common/script.jsp" />
    <link href="script/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />
</head>
<body>    

<input name="_wfid" class="mini-hidden" id="_wfid"/>
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td style="width:100%;">
                        <a class="mini-button" iconCls="icon-add" onclick="addRow()" plain="true">增加</a>
                        <a class="mini-button" iconCls="icon-remove" onclick="removeRow()" plain="true">删除</a>
                        <span class="separator"></span>
                        <a class="mini-button" iconCls="icon-save" onclick="saveData()" plain="true">保存</a>            
                    </td>
                    
                </tr>
            </table>           
        </div>
        <div class="mini-fit">
    <div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" 
         idField="id" allowResize="true" pageSize="10" 
        allowCellEdit="true" allowCellSelect="true" multiSelect="true"
        allowCellValid="true" ajaxOptions="{type:'get'}" >
        <div property="columns">
            <div type="checkcolumn"></div>
            <div field="stepName" width="120" headerAlign="center" >步骤名称
                <input property="editor" class="mini-textbox" style="width:100%;" />
            </div>                
            <div field="userIds" vtype="required" width="200" >审批人
                <input property="editor" class="mini-combobox" textField="userName" valueField="id" 
   					 url="jsp/sysManage/workFlow/data/user.txt" multiSelect="true"  showClose="true" style="width:100%;"/>
            </div>    
            <div field="desc"  width="120" headerAlign="center" allowSort="true">备注
                <input property="editor" class="mini-textbox" style="width:100%;" minHeight="80"/>
            </div>                                 
        </div>
    </div>
     </div>
    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("datagrid1");
        
        function addRow() {
            var newRow = { name: "New Row" };
            grid.addRow(newRow, 0);
            grid.validateRow(newRow);   //加入新行，马上验证新行
        }
        function removeRow() {
            var rows = grid.getSelecteds();
            if (rows.length > 0) {
                grid.removeRows(rows, true);
            }
        }
        function saveData() {
            grid.validate();
            if (grid.isValid() == false) {
                //alert("请校验输入单元格内容");
                var error = grid.getCellErrors()[0];
                grid.beginEditCell(error.record, error.column);
                return;
            }

            var data = grid.getChanges();
            var json = mini.encode(data);

            grid.loading("保存中，请稍后......");
            for(var i=0;i<data.length;i++){
            	var o = data[i];
            	var _state = o._state;//modified  or added   removed
            	if(_state === 'added'){
            		var wfId = mini.get("_wfid").getValue();
            		var obj = {stepName:o.stepName,userIds:o.userIds,desc:o.desc,workFlowId:wfId};
            		$.post('resource/workFlowStep', obj);
            	}else if(_state === 'modified'){
            		var obj = {stepName:o.stepName,userIds:o.userIds,desc:o.desc,workFlowId:o.workFlowId};
            		 $.ajax({
                         url: "resource/workFlowStep?id="+o.id,
                         data:obj,
                         type: 'put'
                     });
            	}else if(_state ==='removed'){
            		$.ajax({
                        url: "resource/workFlowStep/" +o.id,
                        type:"delete"
                    });
            	}
            	
            }
            grid.reload();
        }

        ////////////////////
        //标准方法接口定义
        function SetData(data) {
            if (data.action == "setStep") {
                //跨页面传递的数据对象，克隆后才可以安全使用
                data = mini.clone(data);
                mini.get("_wfid").setValue(data.id);
                var url = "resource/workFlowStep/" + data.id;
                grid.setUrl(url);
                grid.reload();
            }
        }

    </script>
</body>
</html>
