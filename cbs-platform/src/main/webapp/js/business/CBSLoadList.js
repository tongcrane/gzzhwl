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
	
	$('select').each(function(i, j){
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
	});
	
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
    };*/
    
    /*var advSearch = {
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
    
	
//	new CBSPCSelect($("#startCodeP"),$('#startCodeC'),'.search-content');
//	new CBSPCSelect($("#endCodeP"),$('#endCodeC'),'.search-content');
	
    //修改
	var editLoad = function(loadId){
		var url = global.getContextPath() + '/info/business/loadAdd?loadId='+loadId;
    	var body = document.getElementsByTagName("body")[0];
    	var el = document.createElement("a");
    	body.appendChild(el);
    	el.href = url;
    	el.target = '_blank';
    	el.click();
    	body.removeChild(el);
    }
	
    var table = new CBSTable({
        page : $('.handle-page'),
        content : $('.handle-content > table > tbody'),
        url : '/admin/load/pageLoads',
        method  : 'GET',
        pageNumber : 0,
        pageSize : 10,
        rowRender : function(index, row){           
            var el=$('<tr>\
			<td><span class="check-box check"></span></td>\
			<td class="loadNo to-info"></td>\
			<td class="plateNumber to-info"></td>\
			<td class="orderNo to-info"></td>\
			<td class="line to-info"></td>\
			<td class="freightPrice to-info"></td>\
			<td class="statusCn to-info"></td>\
			<td class="text-gray createdTime to-info"></td>\
            </tr>');
            
            var createdTime=row.createdTime;
            if(createdTime) {
            	createdTime=moment(createdTime, "YYYY-MM-DD HH:mm:ss").format("YYYY-MM-DD HH:mm");
            }
            var freightPrice=row.freightPrice;
			if(freightPrice) {
				if(!isNaN(freightPrice)) {
					freightPrice=Number(freightPrice).toFixed(2);
				}
			}
            el.find(".loadNo").html(row.loadNo);
            el.find(".plateNumber").html(row.plateNumber);
            el.find(".line").html(row.startCodePCn+"-"+row.endCodePCn);
            el.find(".freightPrice").html(freightPrice);
            el.find(".orderNo").html(row.orderNo);
            el.find(".statusCn").html(row.statusCn);
            el.find(".createdTime").html(createdTime);
            el.find(".cancel").val(row.loadNo).attr("status",row.status).attr("loadId",row.loadId);
            el.find(".to-info").click(function(){
            	if(row.status == '09'){//已配载
            		openLoad(row.loadId);
            	} else if(row.status == '07'){//待配载
            		editLoad(row.loadId);
            	}
            });
            /*if(row.status =='09'){
            	el.find('.edit').click(function(){
                	editLoad(row.loadId);
                });
            } else {
            	el.find('.edit').addClass('edit-nobg').removeClass('edit');
            }*/
            
            el.find('.check').click(function(){
                var flag=$(this).hasClass('checked');
                console.log(flag)
                if(flag) {
                    $(this).removeClass('checked');
                } else {
                    $(this).addClass('checked');
                }
            });
            return el;
        },
        onBeforeLoad : function(param){
        	$('.all-check').removeClass('checked');
            return param.queryType != null;;
        }
    });
    //详情
    /*var openLoad = function(loadId){
        var url = global.getContextPath() + '/checkBusVehicles.html?loadId='+loadId;
        var body = document.getElementsByTagName("body")[0];
        var el = document.createElement("a");
        body.appendChild(el);
        el.href = url;
        el.target = '_blank';
        el.click();
        body.removeChild(el);
    }*/
    //全选
    $('.all-check').click(function(){
    	var check_box=$('.check');
    	var flag=$(this).hasClass('checked');
    	if(flag){
    		$(this).removeClass('checked');
    		$.each(check_box,function(i,j) {
    			$(j).removeClass('checked');
    		});
    	} else {
    		$(this).addClass('checked');
    		$.each(check_box,function(i,j) {
    			$(j).addClass('checked');
    		});
    	}
    });
    
    //发车
    var doTrip = function(){
    	var loadIds = [];
    	$('td > .checked').each(function () {
            var loadId = $(this).attr("loadId");
            loadIds.push(loadId);
        });
    	var loadId = loadIds.join(",");
    	$.ajax({
            url: global.getServer() + '/admin/load/tripBatch',
            type: "POST",
            data: {loadIds: loadId},
            success: function (msg) {
                if (msg && msg.status && msg.status.statusCode == global.status.success) {
                	alert("已发车");
                    table.load({
                        queryType: 0
                    });
                } else {
                	alert("有提货单不允许发车");
                }
            }
        });
    	
    }
    
    $('.handle > .handle-btn > .do-trip').click(function() {
        var selected = $('td > .checked');
        if (selected.length > 0) {
        	console.log('adsadasd');
        	/*var options = {
    			data : {},
    			text : '是否确认发车',
    			callback:function(data){
    				doTrip();
    			}
    		}
    		new CBSConfirm(options);*/
        } else {
            alert("请选择要发车的提货单");
        }
    });
    
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
        data.queryType=1;
        table.load(data);
        advSearch.hide();
    });*/
    
    var sort='01';
    var loadStatus='';
    $('.sortBtn').click(function(){
    	if(sort=='01') {
    		sort='00';
    		$(this).css('background-image','url('+global.getContextPath()+'/img/icon/time_up.png)');
    	} else {
    		sort='01';
    		$(this).css('background-image','url('+global.getContextPath()+'/img/icon/time_down.png)');
    	}
    	table.load({
            queryType:0,
            keyWord:$('.list-search').val(),
            status:loadStatus,
            sort:sort
        });
    });

    table.load({
        queryType:0
    });
    
    //状态筛选
    /*var load_status=$('.load_status li');
	$.each(load_status,function(){
		$(this).click(function(){
			table.load({
	    		queryType:0,
	    		keyWord:$('.list-search').val(),
	    		sort:sort,
	    		status:$(this).attr('value')
	    	});
			loadStatus=$(this).attr('value');
		});
	});*/

});