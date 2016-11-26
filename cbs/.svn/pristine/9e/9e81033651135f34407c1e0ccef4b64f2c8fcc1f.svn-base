var CBSMenuTree = function(options){
	this.opt = $.extend({}, options);
    this.hasDisplay = false;
    this.template = {
		container: function (options) {
			var el = $('<div class="menu_tree_content"> \
					<ul id="menu_tree" class="ztree"></ul> \
			   </div>');
			el.hide();
			return el;
		}
	};
    
    var _this = this;
    
    this._init = function (options) {
    	var _this = this;
        _this.container = _this.template.container();
        $('body').append(_this.container);
        var initValue = $.extend([],options.initValue);
//        console.log(initValue);
    	_this._getData(initValue);
    };
    
    this._bindEvent = function(){
    	var _this = this;
    	_this.opt.target.click(function(){
    		if(!_this.hasDisplay){
    			_this._show();
    		} else {
    			_this._hide();
    		}
    	});
    };
    
    this.getData = function(){
    	var _this = this;
    	var _result = [];
    	var nodes = _this.treeObj.getCheckedNodes(true);
    	$.each(nodes, function(i, j){
    		if(j.isLeaf == '1'){
    			_result.push(j.menuId);
    		}
    	});
    	return _result;
    };
    
    this._onCheck = function(event, treeId, treeNode) {
    	var treeObj = $.fn.zTree.getZTreeObj(treeId);
    	var nodes = treeObj.getCheckedNodes(true);
    	var nameList = [];
    	$.each(nodes, function(i, j){
    		if(j.isLeaf == '1'){
    			nameList.push(j.menuName);
        	}
    	})
    	_this.opt.display.val(nameList.join(";"));
    };
    
    this._initTree = function(data, initValue){
    	var _this = this;
    	var setting = {
			callback: {
				onCheck: _this._onCheck
			},
			check: {
				autoCheckTrigger: true,
				enable: true,
				chkboxType: { "Y": "ps", "N": "ps" }
			},
			view: {
				dblClickExpand: true
			},
			data: {
				key: {
					name: "menuName",
					children: "subMenu",
					title:"description"
				},
				simpleData: {
					enable: false
				}
			}
		};
    	var target = _this.container.find('#menu_tree');
    	_this.treeObj = $.fn.zTree.init(target, setting, data);
    	var treeObj = _this.treeObj;
    	$.each(initValue, function(i, j){
    		var node = treeObj.getNodeByParam("menuId", j, null);
    		treeObj.checkNode(node, true, true, true);
    	})
    	_this._bindEvent();
    };
    
    this._hide = function(){
    	var _this = this;
    	$("body").unbind("mousedown", _this._onBodyDown);
    	_this.hasDisplay = false;
    	_this.container.hide();
    };
    
    this._show = function(){
    	var _this = this;
    	
    	var cityObj = _this.opt.target;
		var cityOffset = _this.opt.target.offset();
		_this.container.css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
		
    	$("body").bind("mousedown", _this._onBodyDown);
    	_this.hasDisplay = true;
    	_this.container.show();
    };
    
    this._onBodyDown = function(event){
    	if($(event.target).get(0) === _this.opt.target.get(0) || ($(event.target).parents(".menu_tree_content").length>0)){
    		
    	} else {
    		_this._hide();
    	}
    };
    
    
    this._getData = function(initValue){
    	var _this = this;
    	var url = '/admin/auth/menu';
    	$.get(global.server + url, function (msg) {
            if (msg && msg.status && msg.status.statusCode == global.status.success) {
                var data = msg.data;
                _this._initTree(data, initValue);
            }
        })
    }
    
    
	this._init(options);
	
};