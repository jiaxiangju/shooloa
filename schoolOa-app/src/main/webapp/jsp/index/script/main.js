app.main = {
	f : {},
	v : {}
};
/**
 * 初始化
 */
$(function(){
    mini.parse();
   	var win1 = mini.get('win1');
   	win1.showAtPos('left','top');
   	var win2 = mini.get('win2');
   	win2.showAtPos('right','top');
   	var win3 = mini.get('win3');
   	win3.showAtPos('left','bottom');
   	var gridNotice = mini.get('gridNotice');
   	gridNotice.load();
   	var win4 = mini.get('win4');
   	win4.showAtPos('right','bottom');
});

/**
 * 通知公告查看
 */
app.main.f.view = function (e) {
	var rowObject = e.record;
	return '<label class="label">'
	        + '<a href="javascript:;" onclick="app.main.f.open('+rowObject.id+');">'
	        + '查看</a></label';
};


