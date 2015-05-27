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
     
    <input name="id" class="mini-hidden" id="_id"/>
    <form id="wfForm" method="post">
        <div style="padding-left:11px;padding-bottom:5px;">
            <table style="table-layout:fixed;">
                <tr align="left">
                    <td style="width:70px;">流程编号：</td>
                    <td style="width:150px;">    
                        <input name="flowCode" class="mini-textbox" required="true"  emptyText="请输入流程编号"/>
                    </td>
                </tr>
                 <tr>
                    <td style="width:70px;">流程名称：</td>
                    <td style="width:150px;">    
                        <input name="flowName"  class="mini-combobox" style="width:150px;" textField="flowName" valueField="id" 
    url="jsp/data/flowType.txt" showNullItem="true" required="true" allowInput="true"
    onvalidation="onComboValidation" />
                    </td>
                </tr>
                <tr>
                    <td style="width:70px;">流程类型：</td>
                    <td style="width:150px;">    
                        <input name="type" class="mini-textbox" required="true"  emptyText="请输入流程类型"/>
                    </td>
                </tr>
                 <tr>
                    <td style="width:70px;">备注：</td>
                    <td style="width:150px;">    
                        <input name="desc" class="mini-textbox"  emptyText="请输入备注"/>
                    </td>
                </tr>
            </table>
        </div>
        <div style="text-align:left;padding:10px;">               
            <a class="mini-button" onclick="onOk" style="width:60px;margin-right:20px;">确定</a>       
            <a class="mini-button" onclick="onCancel" style="width:60px;">取消</a>       
        </div>        
    </form>
    <script type="text/javascript">
        mini.parse();
        var form = new mini.Form("wfForm");
        function SaveData() {
        	var _id = mini.get('_id').getValue();
            form.validate();
            if (form.isValid() == false) return;
            var o = form.getData(); 
            if(_id){//修改
            	 $.ajax({
                     url: "resource/workFlow?id="+_id,
                     data:o,
                     type: 'put',
                     success: function (text) {
                    	 CloseWindow("save");
                     }
                 });
            }else{//新增
            	$.post('resource/workFlow', o, function(data) {
               	 CloseWindow("save");
       			});
            }
        }

        ////////////////////
        //标准方法接口定义
        function SetData(data) {
            if (data.action == "edit") {
                //跨页面传递的数据对象，克隆后才可以安全使用
                data = mini.clone(data);
                $.ajax({
                    url: "resource/workFlow/" + data.id,
                    type: 'get',
                    cache: false,
                    success: function (text) {
                        var o = mini.decode(text);
                        form.setData(o);
                        form.setChanged(false);
                        
                        mini.get("_id").setValue(data.id);
                    }
                });
            }
        }

        function GetData() {
            var o = form.getData();
            return o;
        }
        function CloseWindow(action) {            
            if (action == "close" && form.isChanged()) {
                if (confirm("数据被修改了，是否先保存？")) {
                    return false;
                }
            }
            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();            
        }
        function onOk(e) {
            SaveData();
        }
        function onCancel(e) {
            CloseWindow("cancel");
        }
    </script>
</body>
</html>
