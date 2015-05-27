app.role = {
	f : {},
	v : {}
};
app.role.v.url = '/role/resource';
app.role.v.roleGrid = {};
app.role.v.roleWindow = {};
app.role.v.roleForm = {};

/**
 * 初始化参数
 */
app.role.f.initV = function(){
  app.role.v.roleGrid = mini.get('gridRole');
  app.role.v.roleWindow = mini.get("roleInfoDiv");
  app.role.v.roleForm = new mini.Form("#roleInfo");
};

/**
 * 初始化用户表格
 */
app.role.f.dataInit = function(){
  app.role.v.roleGrid.setUrl(app.role.v.url);
};

/**
 * 初始化实现
 */
$(function(){
  mini.parse();
  app.role.f.initV();
  app.role.f.dataInit();
  app.role.v.roleGrid.load();
  $('#closeWin').click(function() {
    app.role.v.roleWindow.hide();
  });
  $('#btnSubmit').click(app.role.f.btnSubmit);
});

/**
 * 最后一列操作按钮
 */
app.role.roleGridFormatter = function (e){
	var rowObject = e.record;
	return '<label class="label">'
	        + '<a href="javascript:;" onclick="app.role.setRole(\''+rowObject.id+'\',\''+rowObject.roleName+'\')">'
	        + '配置权限</a></label';
};

/**
 * 新增修改对话框打开
 */
app.role.show = function (flag) {
  app.role.v.roleForm.reset();
  mini.get('id').setValue('');
  if (flag) {
    app.role.v.roleWindow.setTitle('新增角色');
    app.role.v.roleWindow.show();
  } else {
    app.role.v.roleWindow.setTitle('修改角色');
    var row = app.role.v.roleGrid.getSelected();
    if (row) {
      app.role.v.roleForm.setData(row, false);
      mini.get('id').setValue(row.id);
      app.role.v.roleWindow.show();
    } else {
      sys.ui.oaMsgShow({state : 'warning', msg : '请选择一条数据！'});
    }
  }
};

/**
 * 新增修改提交
 */
app.role.f.btnSubmit = function () {
  if (app.role.v.roleForm.isValid() == false) return;
  var obj = app.role.v.roleForm.getData(false,false);
  var id = mini.get('id').getValue();
  console.log(obj);
  if (id) {// 修改=---没问题
	obj.id = id;
    $.ajax({
        url : app.role.v.url,
        type: 'PUT',
        data : obj,
        dataType : 'json',
        async : false,
        success : function(result) {
        	app.role.f.closeWin();
        	sys.ui.oaMsgShow({
				state : 'success',
				msg : '修改成功'
			});
            app.role.v.roleGrid.reload(); 
        }
      });
  } else {// 新增
    $.post(app.role.v.url, obj, function(data) {
      if (data) {
        app.role.f.closeWin();
        sys.ui.oaMsgShow({
			state : 'success',
			msg : '新增成功'
  	  	});
      }
  		app.role.v.roleGrid.reload(); 
    });
  }
};

/**
 * 删除
 */
app.role.del = function () {
  var row = app.role.v.roleGrid.getSelected();
  if (row) {
    mini.confirm("确定删除记录？", "确定？", function(action) {
    if (action == "ok") {
      $.ajax({
        url : app.role.v.url + '/' + row.id,
        type: 'DELETE',
        dataType : 'json',
        async : false,
        success : function(result) {
          sys.ui.oaMsgShow({
		    state : 'success',
		    msg : '删除成功'
		   });
           app.role.v.roleGrid.reload(); 
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
app.role.f.search = function() {
	var keyword = mini.get('roleKeyWords').getValue();
	var params = {
		'keyWord' : keyword,
	};
	app.role.v.roleGrid.load(params);
};

/**
 * 关闭对话框
 */
app.role.f.closeWin = function() {
  app.role.v.roleWindow.hide();
  app.role.v.roleGrid.reload();
};


app.role.setRole = function (id, name) {
	console.log('配置权限');
};