/**
 * 
 */
var CBSAddLoad = function(options){
	this.opt = $.extend({}, options);
	this.acm = {};
	
	
	this.initSelect = function(){
		var _this = this;
		$('.multiselect').each(function(i, j){
			var minWidth = $(j).data('width');
			$(j).multiselect({
				minWidth : minWidth,
				header : false,
				multiple : false,
				selectedList : 1,
				noneSelectedText: '请选择',
			})
		});
		
	}
	
	this.init = function(options){
		var _this = this;
		_this.initSelect()
		if (options && options.loadId) {
			console.log('init load data with id: ' + options.loadId);
            var url = "/admin/load/getLoadDetail?loadId=" + options.loadId;
            var _this = this;
            $.get(global.server + url, function (msg) {
                if (msg && msg.status && msg.status.statusCode == global.status.success) {
                    var data = msg.data;
                    _this.data = data;
                    _this.initBtn();
                    _this.showLoadInfo();
                    _this.showOrderInfo();
                }
            }).done(function(msg){
            	var data = msg.data;
            	var loadInfo = data.loadInfo;
            	
            	var supplyId = loadInfo.supplyId;
            	
        		$('.autocomplete.supply').each(function(i, j){
        			var self = $(j);
        			var ac = new CBSAutocomplete2({
        				target : self,
        				url : '/admin/supply/getSupplyList',
        				method : 'get',
        			    valueField : 'supplyId',
        			    textField : 'fullName',
        			    initValue : supplyId,
        			    onChange : function(row){
        			    	if(row == null){
        			    		$('select.supply-column').each(function(i, j){
            						var self = $(j);
            						$(j).multiselect('enable');
            						$(j).multiselect('setValue', '');
            						$(j).multiselect('disable');
            					});
        			    	} else {
        			    		$('select.supply-column').each(function(i, j){
            						var self = $(j);
            						$(j).multiselect('enable');
            						$(j).multiselect('setValue', row.paymentType);
            						$(j).multiselect('disable');
            					});
        			    	}
        			    }
        			})
        			var _p = self.data('column');
        			_this.acm[_p] = ac;
        		});
            	
            	var vehicleIds = loadInfo.vehicleInfoId;
            	$('.autocomplete.vehicle').each(function(i, j){
        			var self = $(j);
        			var _p = self.data('column');
        			var ac = new CBSAutocomplete2({
        				target : self,
        				url : '/admin/vehiclemanage/listVehicle',
        				method : 'get',
        			    valueField : 'vehicleInfoId',
        			    textField : 'plateNumber',
        			    param : {
        			    	vehicleIds: vehicleIds
        			    },
        			    initValue : loadInfo[_p],
        			    onChange : function(row){
        			    	if(row == null){
        			    		$('.vehicle-column').each(function(i, j){
            			    		var self = $(j);
            						self.val('');
            			    	});
        			    		$('input:radio[name=bridge]').prop('checked',false);
        			    		_this.acm.supplyId.clear();
        			    	} else {
        			    		$('.vehicle-column').each(function(i, j){
            			    		var self = $(j);
            						var _p = self.data('show-column');
            						self.val(row[_p]);
            			    	});
        			    		if(row.bridgeType){
            			    		$('input:radio[name=bridge][value="'+row.bridgeType+'"]').prop('checked',true);
            			    	}
        			    		if(row.ownerName){
        			    			_this.acm.supplyId.setValue(row.ownerName);
        			    		} else {
        			    			_this.acm.supplyId.clear();
        			    		}
        			    	}
        			    }
        			})
        			_this.acm[_p] = ac;
        		});
            	
            	var hungIds = loadInfo.loadInfoId;
        		$('.autocomplete.hung').each(function(i, j){
        			var self = $(j);
        			var _p = self.data('column');
        			var ac = new CBSAutocomplete2({
        				target : self,
        				url : '/admin/vehiclemanage/listHung',
        				method : 'get',
        			    valueField : 'vehicleInfoId',
        			    textField : 'plateNumber',
        			    param : {
        			    	vehicleIds: hungIds
        			    },
        			    initValue : loadInfo[_p]
        			})
        			
        			_this.acm[_p] = ac;
        		});
        		
        		var getDriver = function(driverInfo, type){
        			var result = {};
        			$.each(driverInfo, function(i, j){
        				if(j.isMajor == type){
        					result = j;
        					return false;
        				}
        			});
        			return result;
        		}
        		
        		var gerDriverList = function(driverInfo){
        			if(driverInfo){
        				var result = [];
        				$.each(driverInfo, function(i, j){
        					result.push(j.driverInfoId);
            			});
        				return result.join(',');
        			} else {
        				return "";
        			}
        		}
        		
        		
        		var driverIds = gerDriverList(loadInfo.driverInfo);
        		
        		$('.autocomplete.driver').each(function(i, j){
        			var self = $(j);
        			var _p = self.data('column');
        			var _v = {};
        			if(_p == 'driver1'){
        				_v = getDriver(loadInfo.driverInfo, '01');
        			} else {
        				_v = getDriver(loadInfo.driverInfo, '02');
        			}
        			var ac = new CBSAutocomplete2({
        				target : self,
        				url : '/admin/driver/getDrivers',
        				method : 'get',
        			    valueField : 'driverInfoId',
        			    textField : 'realName',
        			    param : {
        			    	driverIds: driverIds
        			    },
        			    initValue : _v.driverInfoId,
        			    onChange : function(row){
        			    	if(row == null){
        			    		$('.'+_p).each(function(i, j){
            			    		var self = $(j);
            						self.val('');
            			    	});
        			    	} else {
        			    		$('.'+_p).each(function(i, j){
            			    		var self = $(j);
            						var _p = self.data('show-column');
            						self.val(row[_p]);
            			    	});
        			    	}
        			    }
        			})
        			
        			_this.acm[_p] = ac;
        		});
        		
        		
            });
		}
	}
	
	this.initBtn = function(){
		 var _this = this;
		 var data = _this.data;
		 
		 var status = data.loadInfo.status;
		 
		 if(status == '09'){
			 $('title').text('修改配载信息');
			 $('.actions > .action_14').removeClass('disable').data('action', '14');
			 $('.actions > .action_15').removeClass('disable').data('action', '15');
		 } else {
			 var actionList = data.actionList;
			 if(actionList){
				 $.each(actionList, function(i, j){
					 $('.actions > .action_'+j.code).removeClass('disable').data('action', j.code);
				 });
			 }
		 }
		 
		 //移除其他无用按钮
		 $('.actions > .disable').remove();
		 
		 
		 $('.actions > .action_13').click(function(){
			_this.createLoad();
		 });
		 
		 $('.actions > .action_14').click(function(){
			_this.modifyLoad();
		 });
		 
		 $('.actions > .action_12').click(function(){
			 _this.showCancel();
		 });
		 
		 $('.actions > .action_15').click(function(){
			_this.showClose();
		 });
	}
	
	
	this.init(options);
	
}


CBSAddLoad.prototype.showLoadInfo = function(){
	var _this = this;
	var loadInfo = _this.data.loadInfo;
	var status = loadInfo.status;
	if(status != '07') {
		$('.load-column').each(function(i, j){
			var self = $(j);
			var _p = self.data('show-column');
			self.val(loadInfo[_p]);
		});
		
		if(loadInfo.isPredict){
			$('input:radio[name=isPredict][value="'+loadInfo.isPredict+'"]').prop('checked',true);
		}
		
		if(loadInfo.bridgeType){
			$('input:radio[name=bridge][value="'+loadInfo.bridgeType+'"]').prop('checked',true);
		}
		
		var supplyInfo = loadInfo.supplyInfo;
		$('.supply-column').each(function(i, j){
			var self = $(j);
			var _p = self.data('show-column');
			self.val(supplyInfo[_p]);
		});
		$('select.supply-column').each(function(i, j){
			var self = $(j);
			var _p = self.data('show-column');
			$(j).multiselect('enable');
			$(j).multiselect('setValue', supplyInfo[_p]);
			$(j).multiselect('disable');
		});
		
		var driverInfo = loadInfo.driverInfo;
		$.each(driverInfo, function(i, j){
			var isMajor = j.isMajor;
			$('.driver-column-'+isMajor).each(function(k, l){
				var self = $(l);
				var _p = self.data('show-column');
				self.val(j[_p]);
			})
		});
	}
}

CBSAddLoad.prototype.getFullAddress = function(region){
	if($.isArray(region)){
		var pcd = [];
		$.each(region, function(i, j){
			pcd.push(j.regionName);
		});
		return pcd.reverse().join(' ');
	} else {
		return "";
	}
};

CBSAddLoad.prototype.fillData = function(data, selector){
	var _this = this;
    $(selector).each(function(i, j){
    	var properties = $(j).data('column');
    	var pList = properties.split(',');
    	var $self = $(j);
    	if(data!=null){
    		var value = [];
    		$.each(pList,function(i, j){
    			var _v = data[j];
    			if(_v){
    				if (j=='needLength'){
            			if(_v!='其他'){
            				value.push(_v+" 米");
            			} else {
                			value.push(_v);
                		}
            		} else if(j=='sendRegion'){
            			value.push(_this.getFullAddress(_v));
            		} else if(j=='receiveRegion'){
            			value.push(_this.getFullAddress(_v));
            		} else if(j=='needStartTime'||j=='needArriveTime'||j=='pickUpTime'){
            			_v = moment(_v, "YYYY-MM-DD HH:mm:ss").format("YYYY-MM-DD HH:mm");
            			value.push(_v);
            		} else {
            			value.push(_v);
            		}
    			}
        	});
    		if(j.tagName =='INPUT'){
    			$self.val(value.join(' '));
    		} else {
    			$self.text(value.join(' '));
    		}
    	} else {
    		if(j.tagName =='INPUT'){
    			$self.val("");
    		} else {
    			$self.text("");
    		}
    	}
    });
}

CBSAddLoad.prototype.showOrderInfo = function(){
	var _this = this;
	var orderInfo = _this.data.orderInfo;
	_this.fillData(orderInfo, '.order-column');
}



CBSAddLoad.prototype.getJsonFromContent = function(){
	var _this = this;
	var data = {};
	data.loadId = _this.opt.loadId;
	
	$('.column').each(function(i, j){
		var self = $(j);
		var _p = self.data('column');
		var _v = self.val();
		data[_p] = _v;
	});
	
	$('.radio').each(function(i, j){
		var self = $(j);
		var target = self.find('input:radio:checked');
		var _p = self.data('column');
		var _v = "";
		if(target.length == 1){
			_v = target.val();
		}
		data[_p] = _v;
	});
	
	$.each(_this.acm, function(i, j) {
		var _p = i;
		var _v = j.getValue();
		data[_p] = _v;
	});
	
	
	var driver1 = data.driver1;
	var driver2 = data.driver2;
	var driverList = [];
	
	if(driver1 != null){
		var d1 = {};
		d1.driverInfoId = driver1;
		d1.isMajor = '01';
		driverList.push(d1);
	}
	
	if(driver2 != null){
		var d2 = {};
		d2.driverInfoId = driver2;
		d2.isMajor = '02';
		driverList.push(d2);
	}
	
	delete data.driver1;
	delete data.driver2;
	
	data.driverList = driverList;
	return data;
}

CBSAddLoad.prototype.hasRepeat = function(arr){
	var hash = {};
	for(var i in arr) {
	if(hash[arr[i]])
		return true;
		hash[arr[i]] = true;
	}
	return false;
}

CBSAddLoad.prototype.validDriver = function(driverList){
	var _this = this;
	var majorCount = 0;
	var idList = [];
	$.each(driverList, function(i, j){
		if(j.driverInfoId != null){
			if(j.isMajor == '01'){
				majorCount++;
			}
			idList.push(j.driverInfoId);
		}
	});
	var hasRepeat = _this.hasRepeat(idList);
	if(hasRepeat){
		alert('不能选择相同的司机');
		return false;
	}
	if(majorCount != 1){
		alert('请选择司机1');
		return false;
	}
	return true;
}

CBSAddLoad.prototype.validData = function(data){
	var _this = this;
	
	var isPrice = function(value,max, el){
		if(isNaN(value)){
			return false;
		} else {
			var _v = parseFloat(value);
			_v = _v.toFixed(2);
			if(_v<=max){
				el.val(_v);
				return true;
			} else {
				return false;
			}
		}
	}
	
	var freightPrice = data.freightPrice;
	if(freightPrice=='' || freightPrice== null){
		alert('请填写运费');
		return false;
	} else {
		var el = $('.column[data-column="freightPrice"]');
		var is = isPrice(freightPrice,99999, el);
		if(!is){
			alert('请填写正确有效的运费');
			return false;
		}
	}
	
	var prePay = data.prePay;
	if(prePay=='' || prePay== null){
		alert('请填写预付现金');
		return false;
	}else {
		var el = $('.column[data-column="prePay"]');
		var is = isPrice(prePay,99999, el);
		if(!is){
			alert('请填写正确有效的预付现金');
			return false;
		}
	}
	
	var oilPay = data.oilPay;
	if(oilPay=='' || oilPay== null){
		alert('请填写预付油卡');
		return false;
	} else {
		var el = $('.column[data-column="oilPay"]');
		var is = isPrice(oilPay,99999, el);
		if(!is){
			alert('请填写正确有效的预付油卡');
			return false;
		}
	}
	
	var oilCardNo = data.oilCardNo;
	
	var validOilCardNo1=/^9[0-9]{15}$/;
	var validOilCardNo2=/^1[0-9]{18}$/;
	
	if(oilCardNo == '' || oilCardNo == null){
		
	} else {
		var v1 = validOilCardNo1.test(oilCardNo);
		var v2 = validOilCardNo2.test(oilCardNo);
		if(!v1&&!v2){
			alert('请填写正确有效的油卡卡号');
			return false;
		}
	}
	
	var vehicleInfoId = data.vehicleInfoId;
	if(vehicleInfoId=='' || vehicleInfoId == null){
		alert('请选择车辆');
		return false;
	}
	var supplyId = data.supplyId;
	if(supplyId=='' || supplyId== null){
		alert('请选择结款对象');
		return false;
	}
	
	var headstockType = data.headstockType;
	if(headstockType == '牵引车头'){
		var loadInfoId = data.loadInfoId;
		if(loadInfoId=='' || loadInfoId== null){
			alert('请选择车挂');
			return false;
		}
	}
	
	
	var isPredict = data.isPredict;
	if(isPredict=='' || isPredict== null){
		alert('请选择运费是否预估');
		return false;
	}

	delete data.headstockType;
	
	var driverList = data.driverList;
	return _this.validDriver(driverList);
}

CBSAddLoad.prototype.createLoad = function(){
	var _this = this;
	var data = _this.getJsonFromContent();
	var valid = _this.validData(data);
	if(valid){
		var dataStringify = JSON.stringify(data);
	    $.ajax({
	        url: global.server + '/admin/load/create',
	        type: "POST",
	        contentType: "application/json; charset=utf-8",
	        data: dataStringify,
	        dataType: "json",
	        success: function (msg) {
	            if (msg && msg.status && msg.status.statusCode == global.status.success) {
	            	alert("提交成功");
	            	window.close();
	            } else {
	            	alert(msg.status.errorMsg);
	            }
	        }
	    });
	}
}

CBSAddLoad.prototype.modifyLoad = function(){
	var _this = this;
	var data = _this.getJsonFromContent();
	var valid = _this.validData(data);
	if(valid){
		var dataStringify = JSON.stringify(data);
	    $.ajax({
	        url: global.server + '/admin/load/modify',
	        type: "POST",
	        contentType: "application/json; charset=utf-8",
	        data: dataStringify,
	        dataType: "json",
	        success: function (msg) {
	            if (msg && msg.status && msg.status.statusCode == global.status.success) {
	            	alert("提交成功");
	            	window.close();
	            	window.opener.location.reload();
	            } else {
	            	alert(msg.status.errorMsg);
	            }
	        }
	    });
	}
}

CBSAddLoad.prototype.showCancel = function(){
	var _this = this;
	var options = {
		data : {},
		yes:'确认',
		no:'取消',
		text : '是否取消提货单',
		callback:function(data){
			_this.cancelLoad();
		}
	}
	new CBSConfirm(options);
}

CBSAddLoad.prototype.showClose = function(){
	var _this = this;
	var options = {
			data : {},
			yes:'确认',
			no:'取消',
			text : '是否取消修改提货单',
			callback:function(data){
				window.close();
			}
	}
	new CBSConfirm(options);
}


CBSAddLoad.prototype.cancelLoad = function(){
	var _this = this;
	var loadId = _this.opt.loadId;
    $.ajax({
        url: global.server + '/admin/load/cancel',
        type: "POST",
        data: {loadId:loadId},
        success: function (msg) {
            if (msg && msg.status && msg.status.statusCode == global.status.success) {
            	alert("提交成功");
            	window.close();
            } else {
            	alert(msg.status.errorMsg);
            }
        }
    });
}

$(function(){
	
	var loadId = global.QueryString.loadId;
	
	new CBSAddLoad({
		loadId : loadId
	});
	
})