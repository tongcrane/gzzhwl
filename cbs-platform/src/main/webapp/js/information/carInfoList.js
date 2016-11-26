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

$(function() {

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
	
	// 点击高级搜索，搜索框显示
	/*$('.advance-sea').click(function(event) {
		advSearch.show();
	});

	$('.sea-close').click(function(event) {
		advSearch.hide();
	});*/

	 /*$('.use-date').datetimepicker({
	 	timepicker:false,
		format:'Y-m-d',
		autoclose:true,
		todayHighlight:true,
		keyboardNavigation:false
	 });*/

// 	new CBSPCSelect($("#startCodeP"),$('#startCodeC'),'.search-content');
//	new CBSPCSelect($("#endCodeP"),$('#endCodeC'),'.search-content');
	
	/*var openCar = function(vehicleInfoId){
    	var url = global.getContextPath() + '/addCarInfoOne.html?vehicleInfoId='+vehicleInfoId;
    	var body = document.getElementsByTagName("body")[0];
    	var el = document.createElement("a");
    	body.appendChild(el);
    	el.href = url;
    	el.target = '_blank';
    	el.click();
    	body.removeChild(el);
    } */
	
	/*var openCarInfo = function(vehicleInfoId){
		var url = global.getContextPath() + '/checkCarInfomation.html?vehicleInfoId='+vehicleInfoId;
		var body = document.getElementsByTagName("body")[0];
		var el = document.createElement("a");
		body.appendChild(el);
		el.href = url;
		el.target = '_blank';
		el.click();
		body.removeChild(el);
	}*/
	
	/*var getPcd = function(data){
		var p = "";
		var c = "";
		var d = "";
		$.each(data,function(i, j){
			if(j.regionLevel == 1){
				p = j.regionName;
			} else if(j.regionLevel == 2){
				c = j.regionName;
			} else if(j.regionLevel == 3){
				d = j.regionName;
			}
		});
		var result = [];
		if(p){
			//result.push(p);
		}
		if(c){
			result.push(c);
		}
		if(d){
			result.push(d);
		}
		return result.join("-");
	}*/
	
	/*$('.autocomplete').each(function(){
    	var self = $(this);
    	var ac = new CBSAutocomplete2({
			target : self,
			url : '/admin/staff/getDepartList',
			method : 'get',
			appendTo : '.search-content',
		    valueField : 'departId',
		    textField : 'name'
		});
    });*/

	var table = new CBSTable({
		page : $('.handle-page'),
		content : $('.handle-content > table > tbody'),
		url : '/admin/vehiclemanage/queryVehicleInfoList',
		method  : 'POST',
		pageNumber : 0,
		pageSize : 10,
		rowRender : function(index, row){
			var el = $('<tr>\
					<td><span class="check-box check"></span></td>\
					<td class="plateNumber to-info"></td>\
					<td class="length to-info"></td>\
					<td class="loadWeight to-info"></td>\
					<td class="ownerNameCn to-info"></td>\
					<td class="ownerPhone to-info"></td>\
					<td class="attributes to-info"></td>\
					<td class="useStatusCn to-info"></td>\
					<td class="belongDepartName to-info"></td>\
				</tr>');
			
			/*el.find('.car-num').html(global.defaultIfBlack(row.plateNumber,''));
			var pcd_start = getPcd(row.departure);
			var pcd_end = getPcd(row.destination);
			var pcd_result =[];
			if(pcd_start){
				pcd_result.push(pcd_start);
			}
			if(pcd_end){
				pcd_result.push(pcd_end);
			}
			var pcd_str = pcd_result.join(" - ");
			if(pcd_str){
				pcd_str=" - 线路："+pcd_str+"；";
			}
			var length = global.defaultIfBlack(row.length,'')?row.length:'';
			if(length) {
				length=(length=='其他')?" 车长 :"+length:" 车长: "+length+'米';
			}
			var loadWeight = global.defaultIfBlack(row.loadWeight,'')?" 载重:"+global.defaultIfBlack(row.loadWeight,'')+'kg':"";
			el.find('.car-list').html(global.defaultIfBlack(row.useStatusCn,'')+length+loadWeight+" "+global.defaultIfBlack(row.attributes,'')+" "+global.defaultIfBlack(row.ownerNameCn,'')+ " " + global.defaultIfBlack(row.ownerPhone,'')+pcd_str);
			el.find('.belong').html("归属："+global.defaultIfBlack(row.belongDepartName,''));
			el.find('.b1').html(global.defaultIfBlack(row.licenseValidDate,''));
			el.find('.b2').html(global.defaultIfBlack(row.operatingCertValidDate,''));
			el.find('.b3').html(global.defaultIfBlack(row.ciValidDate,''));
			el.find('.b4').html(global.defaultIfBlack(row.viValidDate,''));
			el.find('.edit').click(function(){
				openCar(row.vehicleInfoId);
			});*/
			
			el.find('.plateNumber').html(global.defaultIfBlack(row.plateNumber,''));
			el.find('.length').html(global.defaultIfBlack(row.length,''));
			el.find('.loadWeight').html(global.defaultIfBlack(row.loadWeight,''));
			el.find('.ownerNameCn').html(global.defaultIfBlack(row.ownerNameCn,''));
			el.find('.ownerPhone').html(global.defaultIfBlack(row.ownerPhone,''));
			el.find('.attributes').html(global.defaultIfBlack(row.attributes,''));
			el.find('.useStatusCn').html(global.defaultIfBlack(row.useStatusCn,''));
			el.find('.belongDepartName').html(global.defaultIfBlack(row.belongDepartName,''));
			/*el.find('.to-info').click(function(){
				openCarInfo(row.vehicleInfoId);
			});*/
			el.find('.check-box').data(row).val(row.vehicleInfoId);
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
			return param.queryType != null;
		}
	});
	
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
	
	//搜索
	$('#btn_search').click(function(){
		table.load({
			queryType : 0,
			queryContent : $('.list-search').val()
		});
	});
	
	//高级搜索
	/*$('#btn_adv_search').click(function(){
		var columns=$('.column');
		var data = {};
		columns.each(function(i, j){
			var _this = $(this);
			var pName = _this.data("column");
			var pValue = _this.val();
			if(null!=pValue&&""!=pValue) {
				data[pName] = pValue;
			}
		});
		data.queryType=1;
		table.load(data);
		advSearch.hide();
	});*/
	
	$('.handle > .handle-btn > .del-car').click(function(){
		var selected=$('td > .checked');
		if(selected.length>0) {
			if(confirm('确认删除？')){
				selected.each(function() {  
			    	var vehicleInfoId=$(this).val();
			    	$.ajax({
				        url: global.getServer() + '/admin/vehiclemanage/remove',
				        type: "GET",
				        data: {vehicleInfoId:vehicleInfoId},
				        success: function (msg) {
				            if (msg && msg.status && msg.status.statusCode == global.status.success) {
				            	table.reload();
				            }
				        }
				    });
			    });
			}
		} else {
			alert("请选择要删除的记录");
		}
	});
	

	table.load({
		queryType : 0,
		queryContent : $('.list-search').val()
	});

});