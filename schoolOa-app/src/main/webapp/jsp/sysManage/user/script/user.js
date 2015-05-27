/**
 * 用户管理
 * 
 */

/** *参数定义******** */
app.user = {
	f : {},
	v : {}
};
app.user.v.url = 'user/resource';
app.user.v.userGrid = {};
app.user.v.userWindow = {};
app.user.v.userForm = {};
app.user.v.userFormRole = {};

/**
 * 初始化参数
 */
app.user.f.initV = function(){
  app.user.v.userGrid = mini.get('gridUser');
  app.user.v.userWindow = mini.get("userInfoDiv");
  app.user.v.userForm = new mini.Form("#userInfo");
  app.user.v.userFormRole = mini.get('role_edit');
};

/**
 * 初始化用户表格
 */
app.user.f.dataInit = function(){
  app.user.v.userGrid.setUrl(app.user.v.url);
  app.user.v.userGrid.on('drawcell',function(e){app.user.f.drawcell(e);});
};

/**
 * 画数据
 */
app.user.f.drawcell = function (e){
	var column = e.column;
	switch (column.name) {
	case 'sex':
		if (e.value === 1)
			e.cellHtml =  '女';
		else if (e.value === 0)
			e.cellHtml =  '男';
		break;
	case 'title':
		e.cellHtml = "";
		if (e.value === 0)
			e.cellHtml =  '教授';
		else if (e.value === 1)
			e.cellHtml =  '副教授';
		else if (e.value === 2)
			e.cellHtml =  '讲师';
		else if (e.value === 3)
			e.cellHtml =  '助教';
		else if (e.value === 4)
			e.cellHtml =  '教员';
		else if (e.value === 5)
			e.cellHtml =  '研究员';
		break;
	case 'eduLev':
		if (e.value === 0)
			e.cellHtml =  '博士';
		else if (e.value === 1)
			e.cellHtml =  '硕士';
		else if (e.value === 2)
			e.cellHtml =  '研究生';
		else if (e.value === 3)
			e.cellHtml =  '双学士';
		else if (e.value === 4)
			e.cellHtml =  '大学本科';
		break;
	case 'polStatus':
		if (e.value === 0)
			e.cellHtml =  '群众';
		else if (e.value === 1)
			e.cellHtml =  '团员';
		else if (e.value === 2)
			e.cellHtml =  '党员';
		else if (e.value === 3)
			e.cellHtml =  '其他';
		break;
	default:
		break;
	}
};

/**
 * 初始化实现
 */
$(function(){
  mini.parse();
  app.user.f.initV();
  app.user.f.dataInit();
  app.user.v.userGrid.load();
  $('#closeWin').click(function() {
    app.user.v.userWindow.hide();
  });
  $('#btnSubmit').click(app.user.f.btnSubmit);
});

/**
 * 新增修改对话框打开
 */
app.user.show = function (flag) {
  app.user.v.userForm.reset();
  mini.get('id').setValue('');
  app.user.v.userFormRole.setUrl('/role/resource/RoleAll');/////查询角色，待完善
  if (flag) {
    app.user.v.userWindow.setTitle('新增用户');
    app.user.v.userWindow.show();
  } else {
    app.user.v.userWindow.setTitle('修改用户');
    var row = app.user.v.userGrid.getSelected();
    if (row) {
      app.user.v.userForm.setData(row, false);
      mini.get('id').setValue(row.id);
      //角色待处理
      app.user.v.userWindow.show();
    } else {
      sys.ui.oaMsgShow({state : 'warning', msg : '请选择一条数据！'});
    }
  }
};

/**
 * 新增修改提交
 */
app.user.f.btnSubmit = function () {
  if (app.user.v.userForm.isValid() == false) return;
  var obj = app.user.v.userForm.getData(false,false);
  var id = mini.get('id').getValue();
//  var roleIds = app.user.v.userFormRole.getValue();角色待处理
  if (id) {// 修改=---没问题
	obj.id = id;
    $.ajax({
        url : app.user.v.url,
        type: 'PUT',
        data : obj,
        dataType : 'json',
        async : false,
        success : function(result) {
        	app.user.f.closeWin();
        	sys.ui.oaMsgShow({
				state : 'success',
				msg : '修改成功'
			});
            app.user.v.userGrid.reload(); 
        }
      });
  } else {// 新增
	  $.post(app.user.v.url, obj, function(data) {
		  app.user.f.closeWin();
	      sys.ui.oaMsgShow({
				state : 'success',
				msg : '新增成功'
		  });
	      app.user.v.userGrid.reload(); 
	   });
  }
};

/**
 * 删除
 */
app.user.del = function () {
  var row = app.user.v.userGrid.getSelected();
  if (row) {
    mini.confirm("确定删除记录？", "确定？", function(action) {
      if (action == "ok") {
	       $.ajax({
			      url : app.user.v.url + '/' + row.id,
			      type: 'DELETE',
			      dataType : 'json',
			      async : false,
			      success : function(result) {
			        sys.ui.oaMsgShow({
						state : 'success',
						msg : '删除成功'
					});
			        app.user.v.userGrid.reload(); 
			      }
			    });
			}
		});
   
  } else {
	  sys.ui.oaMsgShow({state : 'warning', msg : '请选择一条数据！'});
  }
};

/**
 * 查询
 */
app.user.f.search = function() {
	var keyword = mini.get('userKey').getValue();
	var params = {
		'keyWord' : keyword,
	};
	app.user.v.userGrid.load(params);
};


function comboboxChanged(e){
	var id = e.id,value = e.value;
	$("input[name='"+id+"']").val(value);
}

/**
 * 关闭对话框
 */
app.user.f.closeWin = function() {
  app.user.v.userWindow.hide();
  app.user.v.userGrid.reload();
};