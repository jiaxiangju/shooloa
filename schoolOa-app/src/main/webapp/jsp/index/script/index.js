var app = {
  f : {},
  v : {}
};

/**
 * 
 * 点击左侧 触发事件
 */
app.f.onItemSelect = function(e){
	mini.parse();
	var item = e.item;
	var tabs = mini.get("mainTabs");
	
	var id = "tab$" + item.id;
    var tab = tabs.getTab(id);
    if (!tab) {
        tab = {};
        tab.name = id;
        tab.title = item.text;
        tab.showCloseButton = true;
        tab.url = item.url;

        tabs.addTab(tab);
    }
    tabs.activeTab(tab);
//    tabs.activeTab(tab);
//	tab.title = item.name;
//    tabs.loadTab(item.url,tab);
};


/**
 * 发布公告
 */
app.f.addNotice = function () {
	mini.open({
        url: "jsp/sysManage/notice/noticeInfo.jsp",
        title: "发布公告", width: 540, height: 395,
        onload: function () {
            var iframe = this.getIFrameEl();
            var data = { action: "new"};
            iframe.contentWindow.SetData(data);
        },
        ondestroy: function (action) {
//        	app.notice.v.noticeGrid.reload(); 
        }
    });
};

$(function () {
	mini.parse();
});