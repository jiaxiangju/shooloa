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
    <script>
		var editor = null;
		var editorId = "ke";
		setTimeout(function () {
    	editor = KindEditor.create('#' + editorId, {
       		resizeType: 1,
        	uploadJson: 'script/kindeditor/jsp/upload_json.jsp',
        	fileManagerJson: 'script/kindeditor/jsp/file_manager_json.jsp',
        	allowPreviewEmoticons: false,
        	allowImageUpload: true,
        	items: [
	    	'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
	    	'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
	    	'insertunorderedlist', '|', 'emoticons', 'image', 'link']
    		});
		}, 1);
	</script>
</head>
<body>    
    <input id="id" name="id" class="mini-hidden" value="">
    <form id="noticeInfo" method="post">
      <table class="form_table">
        <tbody>
          <tr style="height:30px;">
            <th>公告标题：</th>
            <td>
              <input id="title" name="title" class="mini-textbox" required="true" style="width:180px;" />
            </td>
            <th>发布人：</th>
            <td>
              <input id="creater" name="creater" class="mini-textbox" required="true" value="${user.userName}" allowInput="false" style="width:180px;" />
            </td>
          </tr>
          <tr>
			<th style="text-align:left">内容：</th>
		  </tr>
		  <tr>
			<td colspan="4">
			  <!--  <input name="content" class="mini-hidden"/>-->
			  <textarea id="ke" name="content" style="width:100%;height:250px;visibility:hidden;">
              </textarea>
			</td>
		  </tr>
      </tbody>
      <tfoot>
        <tr>
          <td colspan="4" style=" text-align: center;">
            <input type="button" onclick="onOk();" value="保存" >
            <input type="button"  onclick="onCancel();" value="取消">
          </td>
        </tr>
      </tfoot>
    </table>
  </form>
    <script type="text/javascript">
        mini.parse();
        var form = new mini.Form("noticeInfo");
        function SaveData() {
        	var _id = mini.get('id').getValue();
            form.validate();
            if (form.isValid() == false) return;
            var o = form.getData();
            o.content = editor.html();
            if(_id){//修改
            	 $.ajax({
                     url: "resource/announcement?id="+_id,
                     data:o,
                     type: 'put',
                     success: function (text) {
                    	 CloseWindow("save");
                     }
                 });
            }else{//新增
            	$.post('resource/announcement', o, function(data) {
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
                    url: "resource/announcement/" + data.id,
                    type: 'get',
                    cache: false,
                    success: function (text) {
                        var o = mini.decode(text);
                        form.setData(o);
                        form.setChanged(false);
                        editor.html(o.content);
                        mini.get("id").setValue(data.id);
                        //mini.get("ke").setValue(o.content);
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
            CloseWindow("close");
        }
    </script>
</body>
</html>
