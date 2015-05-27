app.v.wf = {};
app.f.wf = {};

mini.parse();
app.v.wf.grid = mini.get("wfData");
app.v.wf.grid.load();


app.f.wf.add = function() {
    mini.open({
        url: "jsp/sysManage/workFlow/workFlowInfo.jsp",
        title: "新增流程", width: 600, height: 360,
        onload: function () {
            var iframe = this.getIFrameEl();
            var data = { action: "new"};
            iframe.contentWindow.SetData(data);
        },
        ondestroy: function (action) {

            app.v.wf.grid.reload();
        }
    });
}
app.f.wf.edit = function() {
    var row = app.v.wf.grid.getSelected();
    if (row) {
        mini.open({
            url: "jsp/sysManage/workFlow/workFlowInfo.jsp",
            title: "编辑流程", width: 600, height: 360,
            onload: function () {
                var iframe = this.getIFrameEl();
                var data = { action: "edit", id: row.id };
                iframe.contentWindow.SetData(data);
                
            },
            ondestroy: function (action) {
                app.v.wf.grid.reload();
            }
        });
        
    } else {
        alert("请选中一条记录");
    }
    
}
app.f.wf.remove = function() {
    
    var rows = app.v.wf.grid.getSelecteds();
    if (rows.length > 0) {
        if (confirm("确定删除选中记录？")) {
            var ids = [];
            for (var i = 0, l = rows.length; i < l; i++) {
                var r = rows[i];
                ids.push(r.id);
            }
            var id = ids.join(',');
            app.v.wf.grid.loading("操作中，请稍后......");
            $.ajax({
                url: "resource/workFlow/" +ids,
                type:"delete",
                success: function (text) {
                    app.v.wf.grid.reload();
                },
                error: function () {
                }
            });
        }
    } else {
        alert("请选中一条记录");
    }
}

app.f.wf.setFstep = function(id){
	 mini.open({
         url: "jsp/sysManage/workFlow/wfStep.jsp",
         title: "设置流程步骤", width: 600, height: 360,
         onload: function () {
             var iframe = this.getIFrameEl();
             var data = { action: "setStep", id: id };
             iframe.contentWindow.SetData(data);
             
         },
         ondestroy: function (action) {
             app.v.wf.grid.reload();
         }
     });
}

app.f.wf.search = function() {
    var key = mini.get("key").getValue();
    app.v.wf.grid.load({ key: key });
}
function onKeyEnter(e) {
    search();
}
/////////////////////////////////////////////////

app.f.wf.onActionRenderer = function(e) {
    var grid = e.sender;
    var record = e.record;
    var uid = record._uid;
    var rowIndex = e.rowIndex;
    //<a href="javascript:app.ArchivesGrid.checkFile(\'' + record._id + '\',\'view\',\'' + grid.id + '\')"><img src="images/button/gnbtn_view.png">查看</a>',
    var s =  '<a class="mini-button" iconCls="icon-filter" href="javascript:app.f.wf.setFstep(\''+record.id+'\')">设置流程步骤 </a>';
    return s;
}
