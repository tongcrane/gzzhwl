var CBSBidManageInfo = function(options){
	this.opt = $.extend({}, options);
	this.columns=$('.column');
    var _this = this;
    
    this.init = function (options) {
    	var _this = this;
    	
        if (options && options.sourceId) {
            console.log('init source data with id: ' + options.sourceId);
            var url_source = "/admin/source/querySourceDetail?sourceId=" + options.sourceId;
            var url_driver="/admin/quotedmanage/getBidVehicleInfo";
            var _this = this;
            $.get(global.server + url_source, function (msg) {
                if (msg && msg.status && msg.status.statusCode == global.status.success) {
                    var data = msg.data;
                    $('.sourse_num span').html(data.sourceNo);//货源编号
                    if(data.sourceInfo.status == '02'){
        				data.statusCn='竞标中';
        			}
                    $('.source_status').html(data.statusCn);//状态
                    if(data.statusCn=='已中标') {
                    	$('.handle-data').empty().html('中标时间：'+data.bidTime);
                    	$('.handle-page').attr('hidden','hidden');
                    	if(data.isShowVehicleInfo==false) {
                        	$('#vehicle_info').html("运输车辆信息：待完善");
                        	$('#show_card_info').addClass('active').css('cursor','default').show();
                        }
                    }
                    var createdTime=data.orderDetailInfo.createdTime;
                    if(createdTime) {
                    	createdTime=moment(createdTime, "YYYY-MM-DD HH:mm:ss").format("YYYY-MM-DD HH:mm");
                    }
                    $('.push_time span').html(createdTime);//推送时间
                    $('#quotedCount').html(data.quotedCount?msg.data.quotedCount:0);
                    _this.fillPriceContentFromJsonData(data.sourceInfo);
                } else {
                	alert("页面参数错误，或数据已不存在。");
                	//window.close();
                }
            }).done(function(msg){
            	_this.getButtons();
            });
            
            $.ajax({
                url: global.server + url_driver,
                type: "POST",
                data: {sourceId:options.sourceId},
                success: function (msg) {
                    if (msg && msg.status && msg.status.statusCode == global.status.success) {
                    	var data = msg.data;
                        var car_num=global.defaultIfBlack(data.plateNumber,'');
                        var driverInfoList='';
                        var driverList=data.driverList;
                        if(driverList.length==0) {
                        	$('#vehicle_info').html("");
                        } else {
                        	$.each(driverList,function(i,j){
                            	var realName=global.defaultIfBlack(j.realName,'')?j.realName:'';
                            	var status=global.defaultIfBlack(j.statusCn,'')?' ('+j.statusCn+')':'';
                            	var driverInfo=realName+status;
                            	driverInfoList+=driverInfo;
                            });
                            var real_name=global.defaultIfBlack(data.realName,'');
                            var status=global.defaultIfBlack(data.status,'');
                            $('#show_cart_info').removeClass('active').css('cursor','pointer');
                            $('#show_card_info').show();
//                            var href = global.getContextPath() + '/NewDriverInfo.html?accountId='+data.accountId+'&vehicleInfoId='+data.vehicleInfoId;
                            $('#show_card_info').data('accountId',data.accountId);
                            $('#show_card_info').data('vehicleInfoId',data.vehicleInfoId);
                            $('#vehicle_info').html("运输车辆信息："+car_num+" 司机："+driverInfoList);
                            
                            $('#show_card_info').click(function(){
                            	var accountId = $(this).data('accountId');
                            	var vehicleInfoId = $(this).data('vehicleInfoId');
                            	var href = global.getContextPath() + '/VehicleAndDriverInfo.html?accountId='+accountId+'&vehicleInfoId='+vehicleInfoId;
                            	window.open(href);
                            });
                        }
                    } else {
                    	alert("页面参数错误，或数据已不存在。");
                    	window.close();
                    }
                }
            });
            
            var table = new CBSTable({
        		page : $('.handle-page'),
        		content : $('.handle-content > table > tbody'),
        		url : '/admin/quotedmanage/quotedManagerList',
        		method  : 'post',
        		pageNumber : 0,
        		pageSize : 10,
        		rowRender : function(index, row){
        			var el = $('<tr>\
	            					<td id="telphone"></td>\
	            					<td id="realName"></td>\
	            					<td id="idno"></td>\
	            					<td id="price"></td>\
	            					<td id="remark"></td>\
	            					<td id="status"></td>\
	            					<td id="hisRemark"></td>\
	            					<td id="completed"></td>\
	            					<td id="invalid"></td>\
	            					<td >\
	            						<div class="owe-to">\
	            				    		<p class="">操作</p>\
	            				    		<span class="owe-span">\
	            				    			<ul class="deadline">\
	            				    				<li>\
	            				    					<p>应标时间</p>\
	            				    					<b id="createdTime"></b>\
	            				    				</li>\
	            				    			</ul>\
	            				    		</span>\
	            				    		<span class="plat_handle">\
	            				    			<ol class="plat_ul">\
	            				    			</ol>\
	            				    		</span>\
	            				    	</div>\
	            					</td>\
	            				</tr>');
        			var buttons=row.actionList;
        			if(buttons!=null) {
        				$.each(buttons,function(i,j){
        					el.find('.plat_ul').append("<li id=action_"+j.code+">"+j.name+"</li>");
        				});
        				el.find('.plat_ul').append("<li id='action_remark'>备注</li>");
        			} else {
        				el.find('.plat_ul').append("<li id='action_remark'>备注</li>");
        			}
        			var price=global.defaultIfBlack(row.price,'');
        			price=price?price+' 元':'';
        			el.find('#telphone').html(global.defaultIfBlack(row.telphone,''));
        			el.find('#realName').html(global.defaultIfBlack(row.realName,''));
        			el.find('#idno').html(global.defaultIfBlack(row.idno,''));
        			el.find('#price').html(price);
        			el.find('#remark').html(global.defaultIfBlack(row.remark,''));
        			el.find('#status').html(global.defaultIfBlack(row.statusCn,''));
        			el.find('#hisRemark').html(global.defaultIfBlack(row.hisRemark,''));
        			el.find('#completed').html(global.defaultIfBlack(row.completed,''));
        			el.find('#invalid').html(global.defaultIfBlack(row.invalid,''));
        			el.find('#createdTime').html(global.defaultIfBlack(row.createdTime,''));
        			
        			el.find('#action_15').click(function(){//作废
        				var options = {
                			data : {},
                			text : '请输入作废理由',
                			callback:function(data){
                				_this.doInvalid(row.quotedId);
                			}
                		}
                		new CBSPop(options);
        			});
        			el.find('#action_17').click(function(){//关闭
        				var options = {
                    		data : {},
                    		text : '请输入关闭理由',
                    		callback:function(data){
                    			_this.doCloseQuoted(row.quotedId);
                    		}
                    	}
                    	new CBSPop(options);
        			});
        			el.find('#action_remark').click(function(){//备注
        				var options = {
                    		data : {},
                    		text : '请输入备注',
                    		callback:function(data){
                    			_this.doRemark(row.quotedId);
                    		}
                    	}
                    	new CBSPop(options);
        			});
        			el.find('#action_13').click(function(){//中标
        				var options = {
            				data : {quotedId : row.quotedId},
            				text : '是否确认',
            				callback:function(data){
            					_this.doWin(row.quotedId);
            				}
            			}
            			new CBSConfirm(options);
        			});
        			return el;
        		},
        		onBeforeLoad : function(param){
        			return param.queryType != null;
        		}
        	});
        	table.load({
        		queryType:0,
        		sourceId:options.sourceId
        	});
        } else {
        	alert("页面参数错误");
        	//window.close();
        }
    }
	this.init(options);
	
};

CBSBidManageInfo.prototype.fillPriceContentFromJsonData = function (data) {
    if (!data) return;
    this.data = data;
    var _this = this;
    this.columns.each(function (i, j) {
    	var _this = $(this);
    	var pName = _this.data("column");
    	var pValue = data[pName];
    	if(pName=='startTime'||pName=='endTime') {
    		if(pValue) {
    			pValue = moment(pValue, "YYYY-MM-DD HH:mm:ss").format("YYYY-MM-DD HH:mm");
    		}
    	}
    	_this.text(pValue);
    });
};

//中标
CBSBidManageInfo.prototype.doWin=function(quotedId) {
	var _this=this;
	var url="/admin/quotedmanage/winTheBid";
	$.ajax({
        url: global.server + url,
        type: "POST",
        data: {quotedId:quotedId},
        success: function (msg) {
            if (msg && msg.status && msg.status.statusCode == global.status.success) {
            	window.location.reload();
            } else {
            	alert(msg.status.errorMsg);
            }
        }
    });
}

//作废
CBSBidManageInfo.prototype.doInvalid=function(quotedId) {
	var _this = this;
	var result={};
	var reason=$('#action_reason').val();
	result.quotedId=quotedId;
	result.remark=reason;
	var url = "/admin/quotedmanage/invalidTheBid";
	$.ajax({
        url: global.server + url,
        type: "POST",
        data: result,
        success: function (msg) {
            if (msg && msg.status && msg.status.statusCode == global.status.success) {
            	window.location.reload();
            } else {
            	alert(msg.status.errorMsg);
            }
        }
    });
}

//关闭
CBSBidManageInfo.prototype.doCloseQuoted=function(quotedId) {
	var _this = this;
	var result={};
	var reason=$('#action_reason').val();
	result.quotedId=quotedId;
	result.remark=reason;
	var url = "/admin/quotedmanage/closeQuotedInfo";
	$.ajax({
        url: global.server + url,
        type: "POST",
        data: result,
        success: function (msg) {
            if (msg && msg.status && msg.status.statusCode == global.status.success) {
            	window.location.reload();
            } else {
            	alert(msg.status.errorMsg);
            }
        }
    });
}

//备注
CBSBidManageInfo.prototype.doRemark=function(quotedId) {
	var _this = this;
	var result={};
	var reason=$('#action_reason').val();
	result.targetId=quotedId;
	result.content=reason;
	result.remarkType='01';
	var url = "/admin/remark/addRemark";
	$.ajax({
        url: global.server + url,
        type: "POST",
        data: result,
        success: function (msg) {
            if (msg && msg.status && msg.status.statusCode == global.status.success) {
            	window.location.reload();
            } else {
            	alert(msg.status.errorMsg);
            }
        }
    });
}

//关闭竞标
CBSBidManageInfo.prototype.doClose=function() {
	var _this = this;
	var result={};
	var reason=$('#close_reason').val();
	result.sourceId=_this.opt.sourceId;
	result.reason=reason;
	var url = "/admin/source/close";
	$.ajax({
        url: global.server + url,
        type: "POST",
        data: result,
        success: function (msg) {
            if (msg && msg.status && msg.status.statusCode == global.status.success) {
            	window.close();
            	window.opener.location.reload();
            } else {
            	alert(msg.status.errorMsg);
            }
        }
    });
} 

CBSBidManageInfo.prototype.getButtons=function() {
	var _this=this;
	var url="/admin/source/wtd";
	$.ajax({
        url: global.server + url,
        type: "get",
        data: {sourceId:_this.opt.sourceId},
        success: function (msg) {
            if (msg && msg.status && msg.status.statusCode == global.status.success) {
            	if(msg.data!=undefined) {
            		var buttons=msg.data;
            		$.each(buttons,function(i,j) {
            			$('#action_'+j.code+'').show();
            			if(j.code=='07') {
            				$('#close_current').show();
            				$('#close_current').click(function(){
            					var options = {
            		    			data : {},
            		    			text : '请输入关闭理由',
            		    			callback:function(data){
            		    				_this.doClose();
            		    			}
            		    		}
            		    		new CBSPop(options);
            				});
            			}
            		});
            	}
            } else {
            	alert(msg.status.errorMsg);
            }
        }
    });
}