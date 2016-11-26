var CBSPermissionInfo = function(options){
	this.opt = $.extend({}, options);
	this.columns = $('.column');
	this.menuColumn=$('.menu-column');
	this.departColumn=$('.depart-column');
    var _this = this;
    
    this.init = function (options) {
    	var _this = this;
        if (options && options.staffId) {
            console.log('init permission data with id: ' + options.staffId);
            var url = "/admin/staff/getPermissionInfo?staffId=" + options.staffId;
            var _this = this;
            $.get(global.server + url, function (msg) {
                if (msg && msg.status && msg.status.statusCode == global.status.success) {
                    var data = msg.data;
                    _this.fillContentFromJsonData(data);
                } else {
                	alert("页面参数错误，或数据已不存在。");
                	window.close();
                }
            });
        } else {
        	alert("页面参数错误");
        	window.close();
        }
    }
	this.init(options);
	
};

CBSPermissionInfo.prototype.fillContentFromJsonData = function (data) {
    if (!data) return;
    this.data = data;
    var _this = this;
    this.columns.each(function (i, j) {
    	var _this = $(this);
    	var pName = _this.data("column");
    	var pValue = data[pName];
    	_this.text(pValue);
    });
    var departInfo=data.departInfo;
    var departStr="";
    $.each(departInfo,function(index,row){
    	departStr+=row.departName+";";
    });
    _this.departColumn.text(departStr);
    var menus=data.menuInfo;
    if(menus.length>0) {
    	var menuStr=getMenu(menus, 0);
    	_this.menuColumn.html(menuStr);
    } else {
    	_this.menuColumn.text('');
    }
    
    
    function getMenu(obj, level) {
    	var str='';
    	$.each(obj,function(index,row) {
    		var menuName=row.menuName;
    		var subMenus=row.subMenu;
    		if(subMenus.length>0){
    			str+=menuName;
    		} else {
    			if((index+1)<obj.length){
    				str+=menuName+"、";
    			} else {
    				str+=menuName;
    			}
    		}
    		if(subMenus.length>0) {
    			if(level>0){
    				str += "["+getMenu(subMenus, (level+1))+"]</br>";
    			} else {
    				str += "("+getMenu(subMenus, (level+1))+")</br>";
    			}
    		}
    	});
    	console.log(str)
    	return str;
    }
};