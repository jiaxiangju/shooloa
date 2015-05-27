app.notice = {
	f : {},
	v : {}
};
app.notice.v.url = 'resource/announcement';
app.notice.v.noticeGrid = {};
//app.notice.v.noticeWindow = {};
//app.notice.v.noticeForm = {};

/**
 * 初始化参数
 */
app.notice.f.initV = function(){
  app.notice.v.noticeGrid = mini.get('gridNotice');
//  app.notice.v.noticeWindow = mini.get("noticeInfoDiv");
//  app.notice.v.noticeForm = new mini.Form("#noticeInfo");
};

/**
 * 初始化用户表格
 */
app.notice.f.dataInit = function(){
  app.notice.v.noticeGrid.setUrl(app.notice.v.url);
};

/**
 * 初始化实现
 */
$(function(){
  mini.parse();
  app.notice.f.initV();
  app.notice.f.dataInit();
  app.notice.v.noticeGrid.load();
});

/**
 * 最后一列操作按钮
 */
app.notice.f.view = function (e){
	var rowObject = e.record;
	return '<label class="label">'
	        + '<a href="javascript:;" onclick="app.notice.f.open('+rowObject.id+');" iconCls="icon-search">查看</a></label';
};

/**
 * 查看公告
 */
app.notice.f.open = function (obj) {
	console.log(obj);
	console.log(app.v.tabs);
	mini.parse();
	var mainTabs = document.getElementById('mainTabs');
	console.log(mainTabs);
	 //add tab
    var tab = {title: "通知/公告"};
    tab = mainTabs.addTab(tab);            

    //tab body
    var el = mainTabs.getTabBodyEl(tab);
    el.innerHTML = 'a new tab!';

    //active tab
    mainTabs.activeTab(tab);
};

/**
 * 新增对话框打开
 */
app.notice.f.add = function() {
    mini.open({
        url: "jsp/sysManage/notice/noticeInfo.jsp",
        title: "发布公告", width: 540, height: 395,
        onload: function () {
            var iframe = this.getIFrameEl();
            var data = { action: "new"};
            iframe.contentWindow.SetData(data);
        },
        ondestroy: function (action) {
        	app.notice.v.noticeGrid.reload(); 
        }
    });
};

/**
 * 修改对话框打开
 */
app.notice.f.edit = function() {
    var row = app.notice.v.noticeGrid.getSelected();
    if (row) {
        mini.open({
            url: "jsp/sysManage/notice/noticeInfo.jsp",
            title: "修改公告", width: 540, height: 395,
            onload: function () {
                var iframe = this.getIFrameEl();
                var data = { action: "edit", id: row.id };
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
            	app.notice.v.noticeGrid.reload();
            }
        });
        
    } else {
    	sys.ui.oaMsgShow({state : 'warning', msg : '请选择一条数据！'});
    }
    
};

/**
 * 删除
 */
app.notice.del = function () {
  var row = app.notice.v.noticeGrid.getSelected();
  if (row) {
	  mini.confirm("确定删除记录？", "确定？", function(action) {
	      if (action == "ok") {
	    	  $.ajax({
	      	      url : app.notice.v.url + '/' + row.id,
	      	      type: 'DELETE',
	      	      dataType : 'json',
	      	      async : false,
	      	      success : function(result) {
	      	        sys.ui.oaMsgShow({
	      				state : 'success',
	      				msg : '删除成功'
	      			});
	      	        app.notice.v.noticeGrid.reload(); 
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
app.notice.f.search = function() {
	var keyword = mini.get('keyWords').getValue();
	var params = {
		'keyWord' : keyword,
	};
	app.notice.v.noticeGrid.load(params);
};

