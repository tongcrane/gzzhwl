/*var CBSPCSelect = function(p, c, append){
	this.init = function(p, c, append){
		var _this = this;
		var _ptarget = p;
		var _ctarget = c;
		
		this.pSelect = new CBSPCSelectRow(_ptarget, {showEmpty:true});
		this.cSelect = new CBSPCSelectRow(_ctarget, {showEmpty:true});
		
		var pw = p.data('width');
		
		_ptarget.multiselect({
			header : false,
			multiple : false,
			selectedList : 1,
			minWidth : pw,
			appendTo : append,
			position:{
				my: 'left bottom',
			    at: 'left top'
			},
			noneSelectedText: '请选择',
			click: function(event, ui){
				_this.cSelect.refresh(ui.id);
		    }
		});
		var cw = p.data('width');
		_ctarget.multiselect({
			header : false,
			multiple : false,
			selectedList : 1,
			minWidth : pw,
			appendTo : append,
			position:{
				my: 'left bottom',
			    at: 'left top'
			},
			noneSelectedText: '请选择'
		});
		
		_this.showDefault();
	};
	
	
	this.showDefault = function(code){
		var _this = this;
		_this.pSelect.refresh(1);
	};
	
	this.selectValue = function(pV, cV){
		var _this = this;
		if(pV){
			if(cV){
				_this.cSelect.refreshByCode(pV, cV);
				_this.pSelect.refreshByCode('000000',pV);
			} else {
				_this.pSelect.refreshByCode('000000',pV);
			}
		}
	};
	
	this.init(p, c, append);
}*/

/*var CBSPCSelectRow = function(container, options){
	this.container = container;
	this.opt = $.extend({}, options);
	
	this.refresh = function(parentId, initId){
		var _this = this;
		_this.getData(parentId, _this.showData, initId);
	};
	
	this.refreshByCode = function(parentCode, initCode){
		var _this = this;
		_this.getDataByCode(parentCode, _this.showDataByCode, initCode);
	}
	
	this.showDataByCode = function(data, initCode){
		var _this = this;
		container.empty();
		if(options.showEmpty){
			container.append('<option data-id="" value="">请选择</option>');
		}
		$.each(data, function(i, j){
			if(initCode){
				if(j.regionCode == initCode){
					container.append('<option selected data-id="'+ j.regionId +'" value="' + j.regionCode + '">' + j.regionName +'</option>');
				} else {
					container.append('<option data-id="'+ j.regionId +'" value="' + j.regionCode + '">' + j.regionName +'</option>');
				}
			}else{
				container.append('<option data-id="'+ j.regionId +'" value="' + j.regionCode + '">' + j.regionName +'</option>');
			}
		});
		container.multiselect("refresh");
		if(initCode){
			//console.log("init..."+initId);
		} else {
			//console.log("refresh");
			container.multiselect("widget").find("label.ui-corner-all:first").each(function(){
				this.click();
			});
		}
	};
	

	this.showData = function(data, initId){
		var _this = this;
		container.empty();
		if(options.showEmpty){
			container.append('<option data-id="" value="">请选择</option>');
		}
		$.each(data, function(i, j){
			if(initId){
				if(j.regionId == initId){
					container.append('<option selected data-id="'+ j.regionId +'" value="' + j.regionCode + '">' + j.regionName +'</option>');
				} else {
					container.append('<option data-id="'+ j.regionId +'" value="' + j.regionCode + '">' + j.regionName +'</option>');
				}
			}else{
				container.append('<option data-id="'+ j.regionId +'" value="' + j.regionCode + '">' + j.regionName +'</option>');
			}
			
		});
		container.multiselect("refresh");
		if(initId){
			//console.log("init..."+initId);
		} else {
			//console.log("refresh");
			container.multiselect("widget").find("label.ui-corner-all:first").each(function(){
				this.click();
			});
		}
	};
	
	this.getValue = function(){
		return container.val();
	}
	
	
	this.getData = function(parentId, callback, initId){
		var url = '/api/region/findById';
		if(parentId){
			$.ajax({
		        url: global.server + url,
		        type: "get",
		        data: {"parentId":parentId},
		        success: function (msg) {
		            if (msg && msg.status && msg.status.statusCode == global.status.success) {
		            	var data = msg.data;
		            	callback(data, initId);
		            }
		        }
		    });
		} else {
			callback([], initId);
		}
	};
	
	this.getDataByCode = function(parentCode, callback, initCode){
		var url = '/api/region/findByCode';
		if(parentCode){
			$.ajax({
				url: global.server + url,
				type: "get",
				data: {"regionCode":parentCode},
				success: function (msg) {
					if (msg && msg.status && msg.status.statusCode == global.status.success) {
						var data = msg.data;
						callback(data, initCode);
					}
				}
			});
		} else {
			callback([], initCode);
		}
	};
	
};*/

$(function(){
	
	/*var onBodyDown = function(event){
    	if(($(event.target).parents(".search-content").length>0)){
    		
    	} else {
    		advSearch.hide();
    	}
    };
    
    var advSearch = {
        show : function() {
            $('.search-content').show(10, function(){
            	$("body").bind("mousedown", onBodyDown);
            });
        },
        hide : function() {
        	$("body").unbind("mousedown", onBodyDown);
            $('.search-content').hide();
        }
    }*/
	
    /*$('.use-date').each(function(i, j){
		var time = $(this).data('time');
		if(time){
			$(this).datetimepicker({
        		timepicker:true,
        		format:'Y-m-d H:i',
        		autoclose:true,
        		todayHighlight:true,
        		keyboardNavigation:false
    		});
		} else {
			$(this).datetimepicker({
        		timepicker:false,
        		format:'Y-m-d',
        		autoclose:true,
        		todayHighlight:true,
        		keyboardNavigation:false
    		});
		}
    });*/
    
    /*$('select').each(function(i, j){
		var minWidth = $(j).data('width');
		$(j).multiselect({
			minWidth : minWidth,
			header : false,
			multiple : false,
			selectedList : 1,
			appendTo : '.search-content',
			position:{
				my: 'left top',
			    at: 'left bottom'
			},
			noneSelectedText: '请选择',
		})
	});*/
    
    /*var ac = new CBSAutocomplete2({
		target : $('#autocomplete-ajax'),
		url : '/admin/customer/queryCustList',
		method : 'get',
	    valueField : 'customerId',
	    textField : 'fullName',
	    appendTo : '.search-content'
	});*/
    
//	new CBSPCSelect($("#startCodeP"),$('#startCodeC'),'.search-content');
//	new CBSPCSelect($("#endCodeP"),$('#endCodeC'),'.search-content');
    
    var table = new CBSTable({
        page : $('.handle-page'),
        content : $('.handle-content > table > tbody'),
        url : '/admin/arrived/pageReceiptList',
        method  : 'GET',
        pageNumber : 0,
        pageSize : 10,
        rowRender : function(index, row){
        	var el=$('<tr>\
        			<td class="orderNo to-info"></td>\
        			<td class="loadNo to-info"></td>\
        			<td class="plateNumber to-info"></td>\
        			<td class="customerName to-info"></td>\
        			<td class="line to-info"></td>\
        			<td class="text-gray elecTime to-info"></td>\
        			<td class="text-gray printTime to-info"></td>\
        		</tr>');
        	
            var elecTime=row.elecTime;
            if(elecTime) {
            	elecTime=moment(elecTime).format("YYYY-MM-DD HH:mm");
            }
            var printTime=row.printTime;
            if(printTime) {
            	printTime=moment(printTime).format("YYYY-MM-DD HH:mm");
            }
            el.find(".orderNo").html(row.orderNo);
            el.find(".loadNo").html(row.loadNo);
            el.find(".plateNumber").html(row.plateNumber);
            el.find(".customerName").html(row.customerName);
            el.find(".line").html(row.startCodeCCn+"-"+row.endCodeCCn);
            el.find(".elecTime").html(elecTime);
            el.find(".printTime").html(printTime);
            /*el.find(".to-info").click(function(){
            	openReceipt(row.loadId);
            });*/
            return el;
        },
        onBeforeLoad : function(param){
            return param.queryType != null;;
        }
    });
    //详情
    /*var openReceipt = function(loadId){
        var url = global.getContextPath() + '/checkReceiptList.html?loadId='+loadId;
        var body = document.getElementsByTagName("body")[0];
        var el = document.createElement("a");
        body.appendChild(el);
        el.href = url;
        el.target = '_blank';
        el.click();
        body.removeChild(el);
    }*/
    
    //搜索
    $('#btn_search').click(function(){
        table.load({
            queryType:0,
            keyWord:$('.list-search').val()
        });
    });
    //高级搜索
    /*$('.advance-sea').click(function(event) {
        advSearch.show();
    });

    $('.sea-close').click(function(event) {
        advSearch.hide();
    });
    $('#btn_adv_search').click(function(){
        var columns=$('.column');
        var data = {};
        columns.each(function(i, j){
            var _this = $(this);
            var pName = _this.data("column");
            var pValue = _this.val();
            data[pName] = pValue;
        });
        data.customerId=ac.getValue();
        data.queryType=1;
        table.load(data);
        advSearch.hide();
    });*/
    
    var elec_time_sort='01';
    var print_time_sort="01";
    $('.elec_time_sort').click(function(){
    	if(elec_time_sort=='01') {
    		elec_time_sort='00';
    	} else {
    		elec_time_sort='01';
    	}
    	table.load({
            queryType:0,
            keyWord:$('.list-search').val(),
            sortField:'elecTime',
            sort:elec_time_sort
        });
    });
    
    $('.print_time_sort').click(function(){
    	if(print_time_sort=='01') {
    		print_time_sort='00';
    	} else {
    		print_time_sort='01';
    	}
    	table.load({
            queryType:0,
            keyWord:$('.list-search').val(),
            sortField:'printTime',
            sort:print_time_sort
        });
    });

    table.load({
        queryType:0
    });
    
});