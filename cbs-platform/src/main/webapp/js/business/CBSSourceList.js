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

    /*var ac = new CBSAutocomplete2({
		target : $('#autocomplete-ajax'),
		url : '/admin/customer/queryCustList',
		method : 'get',
	    valueField : 'customerId',
	    textField : 'fullName',
	    appendTo : '.search-content',
	    onChange : function(row){
	    	if(row != null) {
	    		$('.sender-column[data-column="customerName"]').val(row.fullName);
				$.get(global.server + '/admin/agreement/getAgreements', {custId:row.customerId}, function(msg){
					if (msg && msg.status && msg.status.statusCode == global.status.success) {
						var data = msg.data;
						$('.agreement-select').empty();
						var el = $('<option data-id="" value="">请选择</option>');
		                $('.agreement-select').append(el);
						$.each(data, function(i, j){
							var el = $('<option data-id="'+ j.agreementId +'" value="' + j.agreementId + '">' + j.startCodeP +'</option>');
		                	$('.agreement-select').append(el);
		                });
						$('.agreement-select').multiselect('refresh');
					}
				});
	    	}
	    }
	});*/
    
//	new CBSPCSelect($("#startCodeP"),$('#startCodeC'),'.search-content');
//	new CBSPCSelect($("#endCodeP"),$('#endCodeC'),'.search-content');
	
	//客户全称&客户合同下拉框
	/*$('select.baseInfo-column').each(function(i, j){
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
	
	/*$('.agreement-select').multiselect({
    	header : false,
		multiple : false,
		selectedList : 1,
		appendTo : '.search-content',
		position:{
			my: 'left top',
		    at: 'left bottom'
		},
		noneSelectedText: '请选择'
    });*/
	
	/*var editOrder = function(orderId){
		var url = global.getContextPath() + '/addBusSourceOrder.html?orderId='+orderId;
    	var body = document.getElementsByTagName("body")[0];
    	var el = document.createElement("a");
    	body.appendChild(el);
    	el.href = url;
    	el.target = '_blank';
    	el.click();
    	body.removeChild(el);
    }*/
	
    var table = new CBSTable({
        page : $('.handle-page'),
        content : $('.handle-content > table > tbody'),
        url : '/admin/order/pageOrders',
        method  : 'GET',
        pageNumber : 0,
        pageSize : 10,
        rowRender : function(index, row){
            var el=$('<tr>\
			<td><span class="check-box check"></span></td>\
			<td class="orderNo to-info"></td>\
			<td class="customerName to-info"></td>\
			<td class="line to-info"></td>\
			<td class="lineAttribute to-info"></td>\
			<td class="statusCn to-info"></td>\
			<td class="retStatusCn to-info"></td>\
			<td class="text-gray createdTime to-info"></td>\
			</tr>');
            
            var createdTime=row.createdTime;
            if(createdTime) {
            	createdTime=moment(createdTime, "YYYY-MM-DD HH:mm:ss").format("YYYY-MM-DD HH:mm");
            }
            el.find(".orderNo").html(row.orderNo);
            el.find(".customerName").html(row.customerName);
            el.find(".statusCn").html(row.statusCn);
            el.find(".createdTime").html(createdTime);
            el.find(".lineAttribute").html(row.lineAttribute);
            el.find(".line").html(row.startCodePCn+"-"+row.endCodePCn);
            el.find(".check-box").val(row.orderNo).attr("status",row.status).attr("orderId",row.orderId);
            el.find(".retStatusCn").html(row.retStatusCn);
            el.find(".to-info").click(function(){
                openCustom(row.orderId);
            });
            el.find('.check').click(function(){
                var flag=$(this).hasClass('checked');
                console.log(flag)
                if(flag) {
                    $(this).removeClass('checked');
                } else {
                    $(this).addClass('checked');
                }
            });
            var toDoList=row.toDoList;
            if(toDoList!=null) {
            	/*$.each(toDoList,function(i,j){
                	if(j.code=='02') {
                		el.find('span').removeClass('.edit-nobg').addClass('edit').addClass('to-info');
                		el.find('.edit').click(function(){
                			editOrder(row.orderId);
                		});
                	}
                });*/
            }
            return el;
        },
        onBeforeLoad : function(param){
        	$('.all-check').removeClass('checked');
            return param.queryType != null;
        }
    });
    var openCustom = function(orderId){
        var url = global.getContextPath() + '/info/business/orderInfo?orderId='+orderId;
        var body = document.getElementsByTagName("body")[0];
        var el = document.createElement("a");
        body.appendChild(el);
        el.href = url;
        el.target = '_blank';
        el.click();
        body.removeChild(el);
    }
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
    
    var cancelOrder = function(){
    	var orderIds = [];
    	$('td > .checked').each(function () {
            var orderId = $(this).attr("orderId");
            orderIds.push(orderId);
        });
    	var orderId = orderIds.join(",");
    	$.ajax({
            url: global.getServer() + '/admin/order/cancelBatch',
            type: "POST",
            data: {orderIds: orderId},
            success: function (msg) {
                if (msg && msg.status && msg.status.statusCode == global.status.success) {
                	alert("订单已取消");
                    table.load({
                        queryType: 0
                    });
                } else {
                	alert("有订单不允许取消");
                }
            }
        });
    	
    }
    
    $('.handle > .handle-btn > .cancel-order').click(function() {
        var selected = $('td > .checked');
        if (selected.length > 0) {
        	/*var options = {
    			data : {},
    			text : '是否需要取消订单',
    			callback:function(data){
    				cancelOrder();
    			}
    		}
    		new CBSConfirm(options);*/
        } else {
            alert("请选择要取消的的订单");
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
    });*/

    /*$('.sea-close').click(function(event) {
        advSearch.hide();
    });*/
    /*$('#btn_adv_search').click(function(){
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
    
    var sort='01';
    var orderStatus='';
    var retStatus='';
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
            orderStatus:orderStatus,
            retStatus:retStatus,
            sort:sort
        });
    });

    table.load({
        queryType:0
    });
    
    //状态筛选
    /*var order_status=$('.order_status li');
	$.each(order_status,function(){
		$(this).click(function(){
			table.load({
	    		queryType:0,
	    		keyWord:$('.list-search').val(),
	    		sort:sort,
	    		retStatus:retStatus,
	    		orderStatus:$(this).attr('value')
	    	});
			orderStatus=$(this).attr('value');
		});
	});
	
	var return_status=$('.return_status li');
	$.each(return_status,function(){
		$(this).click(function(){
			table.load({
	    		queryType:0,
	    		keyWord:$('.list-search').val(),
	    		sort:sort,
	    		orderStatus:orderStatus,
	    		retStatus:$(this).attr('value')
	    	});
			retStatus=$(this).attr('value');
		});
	});*/

});


