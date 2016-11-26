var CBSPCSelect = function(p, c, append){
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
}

var CBSPCSelectRow = function(container, options){
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
	
};

$(function() {
//	$('.search-content').bind('click', function(e){
//		return false;
//	});
//	var advSearch = {
//		show : function() {
//			$('.search-content').show(10, function(){
//				$('body').one('click', function(){
//					advSearch.hide();
//				});
//			});
//		},
//		hide : function() {
//			$('.search-content').hide();
//			$('body').unbind();
//		}
//	}
	var onBodyDown = function(event){
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
     }

	// 点击高级搜索，搜索框显示
	$('.advance-sea').click(function(event) {
		advSearch.show();
	});

	$('.sea-close').click(function(event) {
		advSearch.hide();
	});

	 $('.use-date').datetimepicker({
	 	timepicker:false,
		format:'Y-m-d',
		autoclose:true,
		todayHighlight:true,
		keyboardNavigation:false
	 });
	
	new CBSPCSelect($("#startCodeP"),$('#startCodeC'),'.search-content');
	new CBSPCSelect($("#endCodeP"),$('#endCodeC'),'.search-content');
	
	$('.autocomplete').each(function(){
    	var self = $(this);
//    	var _p = self.data('column');
    	var ac = new CBSAutocomplete2({
			target : self,
			url : '/admin/staff/getDepartList',
			method : 'get',
			appendTo : '.search-content',
		    valueField : 'departId',
		    textField : 'name'
//		    initValue : data[_p]
		});
//    	_this.acm[_p] = ac;
    });

	var openLose = function(vehicleInfoId){
		var url = global.getContextPath() + '/addLoseCarInfoOne.html?vehicleInfoId='+vehicleInfoId;
    	var body = document.getElementsByTagName("body")[0];
    	var el = document.createElement("a");
    	body.appendChild(el);
    	el.href = url;
    	el.target = '_blank';
    	el.click();
    	body.removeChild(el);
    }
	var openLoseInfo = function(vehicleInfoId){
		var url = global.getContextPath() + '/checkLoseCarInfo.html?vehicleInfoId='+vehicleInfoId;
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
		url : '/admin/vehiclemanage/queryHungInfoList',
		method  : 'POST',
		pageNumber : 0,
		pageSize : 10,
		rowRender : function(index, row){
			var el = $('<tr> \
						<td> \
							<div class="f-width"> \
								<input class="check-box" type="checkbox"/> \
							</div> \
						</td> \
						<td> \
							<a href="javascript:void(0);" class="edit"></a> \
						</td> \
						<td> \
							<div class="car-num to-info"></div> \
						</td> \
						<td> \
							<div class="car-list to-info"></div> \
						</td> \
						<td> \
							<div class="owe-to"> \
					    		<p class="belong to-info"></p> \
					    		<span class="owe-span"> \
					    			<ul class="deadline"> \
					    				<li> \
					    					<p>行驶证有效期</p> \
					    					<b class="b1"></b> \
					    				</li> \
					    				<li> \
					    					<p>营运证有效期</p> \
					    					<b class="b2"></b> \
					    				</li> \
					    			</ul> \
					    		</span> \
					    	</div> \
						</td> \
					</tr>');
			el.find('.car-num').html(global.defaultIfBlack(row.plateNumber,''));
			var length = global.defaultIfBlack(row.length,'')?row.length:'';
			if(length) {
				length=(length=='其他')?" 车长:"+length:" 车长:"+length+'米';
			}
			var loadWeight = global.defaultIfBlack(row.loadWeight,'')?" 载重:"+global.defaultIfBlack(row.loadWeight,'')+'kg':"";
			el.find('.car-list').html(global.defaultIfBlack(row.useStatusCn,'')+length+loadWeight);
			el.find('.belong').html("归属："+global.defaultIfBlack(row.belongDepartName,''));
			el.find('.b1').html(global.defaultIfBlack(row.licenseValidDate,''));
			el.find('.b2').html(global.defaultIfBlack(row.operatingCertValidDate,''));
			el.find('.edit').click(function(){
				openLose(row.vehicleInfoId);
			});
			el.find('.to-info').click(function(){
				openLoseInfo(row.vehicleInfoId);
			});
			el.find('.f-width > .check-box').data(row).val(row.vehicleInfoId);
			el.find('.f-width > .check-box').click(function(){
				var flag=$(this).attr('checked');
				if(flag===undefined) {
					$(this).attr('checked','checked');
					$("tr:eq("+index+")").css('background-color','#FBEDC1');
				} else {
					$(this).removeAttr('checked');
					$("tr:eq("+index+")").removeAttr("style");
				}
			});
			return el;
		},
		onBeforeLoad : function(param){
			return param.queryType != null;
		}
	});
	
	$('#btn_search').click(function(){
		table.load({
			queryType:0,
			queryContent:$('.list-search').val()
		});
	});
	
	$('#btn_adv_search').click(function(){
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
	});
	
	$('.handle-data>.handle-delete').click(function(){
		var selected=$('.f-width > .check-box:checked');
		if(selected.length>0) {
			if(confirm('确认删除？')){
				$('.f-width > .check-box:checked').each(function() {  
			    	var vehicleInfoId=$(this).val();
			    	$.ajax({
				        url: global.server + '/admin/vehiclemanage/remove',
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
		queryType:0,
		queryContent:$('.list-search').val()
	});
	
});