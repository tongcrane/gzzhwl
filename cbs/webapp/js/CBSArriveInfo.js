var CBSArriveInfo = function(options){
	this.opt = $.extend({}, options);
	this.columns=$('.column');
	this.imageList = [];

	this.init = function(options){
		var _this = this;
		var loadId = _this.opt.loadId;
		_this.getData(loadId);
	};
	
	
	this.getData = function(loadId){
		var _this = this;
		$.get(global.server+'/admin/arrived/getArriveDetail',{loadId:loadId}, function(msg){
			if (msg && msg.status && msg.status.statusCode == global.status.success) {
				var data = msg.data;
				_this.data = data;
				_this.fillContentFromJsonData(data.loadVehicleInfo);
				_this.fillContentArrive(data.arriveInfo);
				_this.fillContentElecReceipt(data.elecReceipt);
				_this.fillContentPrintReceipt(data.printReceipt);
			}
		}).done(function(msg){
			var data = msg.data;
			
			$('span[data-column="plateNumber"]').click(function(){
				var url = global.getContextPath() + '/checkCarInfomation.html?vehicleInfoId='+msg.data.loadVehicleInfo.vehicleInfoId;
		    	var body = document.getElementsByTagName("body")[0];
		    	var el = document.createElement("a");
		    	body.appendChild(el);
		    	el.href = url;
		    	el.target = '_blank';
		    	el.click();
		    	body.removeChild(el);
			});
			//货主异常
			$('.source_feedback').click(function(){
				var options = {
			    	data : {
			    		loadId : loadId,
			    		status : '11',
			    		type : '01'
			    	},
			    	title : '货主异常反馈'
			    }
			    new CBSFeedBack(options);
			});
			//车辆异常
			$('.vehicle_feedback').click(function(){
				var options = {
				    data : {
				    	loadId : loadId,
				    	status : '11',
				    	type : '02'
				    },
				    title : '车辆异常反馈'
				 }
				 new CBSFeedBack(options);
			});
			//电子回单
			$('.elec_receipt').click(function(){
				$('.mo_del').show();
				$('.upload').show().siblings('div').hide();
				var _date=moment(new Date()).format("YYYY-MM-DD HH:mm");
				$('input[data-column="actionTime"]').val(_date);
				$('input[data-column="actionTime"]').each(function(i, j){
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
		        });
			});
			if (typeof CBSImage === "function") {
	        	$('.elec-image-column').each(function(i, j){
	            	var image = new CBSImage({
	            		view: $(this),
	            		category: $(this).data("column")
	                });
	            	_this.imageList.push(image);
	            });
	        }
			$('.action_elec_receipt').click(function(){
				var actionTime=$('input[data-column="actionTime"]').val();
				if(actionTime) {
					var imageId="";
					$.each(_this.imageList,function(i, j){
						var result = j.postToServer('/admin/arrived/uploadElecImage');
						if(result) {
							imageId=result.imageId;
							var result={};
							result.actionTime=actionTime;
							result.loadId=data.arriveInfo.loadId;
							result.imageId=imageId;
							$.ajax({
						        url: global.server + '/admin/arrived/elecReceipt',
						        type: "post",
						        data: result,
						        success: function (msg) {
						            if (msg && msg.status && msg.status.statusCode == global.status.success) {
						            	window.location.reload();
						            } else {
						            	alert(msg.status.errorMsg);
						            }
						        }
						    });
						} else {
							alert('请上传电子回单图片');
							return false;
						}
					});
				} else {
					alert('请选择回单时间');
					return false;
				}
			});
			//纸质回单
			$('.print_receipt').click(function(){
				$('.mo_del').show();
				$('.receipt_time').show().siblings('div').hide();
				$('input[name="way"]').change(function(){
					var type=$('input[name="way"]:checked').val();
					if(type=='01') {
						$('.part_1').show();
						$('.part_2').show();
						$('.part_3').hide();
					} else if(type=='02') {
						$('.part_1').hide();
						$('.part_2').hide();
						$('.part_3').show();
					}
				});
				var _date=moment(new Date()).format("YYYY-MM-DD HH:mm");
				$('input[data-column="signTime"]').val(_date);
				$('input[data-column="signTime"]').each(function(i, j){
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
		        });
				$('.action_print_submit').click(function(){
					var type=$('input[name="way"]:checked').val();
					if(type=='01') {
						var expressDelivery=$('input[data-column="expressDelivery"]').val();
						var billNo=$('input[data-column="billNo"]').val();
						if(expressDelivery) {
							if(billNo) {
								var result={};
								result.loadId=loadId;
								result.type=type;
								result.expressDelivery=expressDelivery;
								result.billNo=billNo;
								$.ajax({
							        url: global.server + '/admin/arrived/printReceipt',
							        type: "post",
							        data: result,
							        success: function (msg) {
							            if (msg && msg.status && msg.status.statusCode == global.status.success) {
							            	window.location.reload();
							            } else {
							            	alert(msg.status.errorMsg);
							            }
							        }
							    });
							} else {
								alert('请填写快递单号');
								return false;
							}
						} else {
							alert('请填写快递公司');
							return false;
						}
					} else if(type=='02') {
						var signTime=$('input[data-column="signTime"]').val();
						if(signTime) {
							var result={};
							result.loadId=loadId;
							result.type=type;
							result.signTime=signTime;
							$.ajax({
						        url: global.server + '/admin/arrived/printReceipt',
						        type: "post",
						        data: result,
						        success: function (msg) {
						            if (msg && msg.status && msg.status.statusCode == global.status.success) {
						            	window.location.reload();
						            	window.opener.location.reload();
						            } else {
						            	alert(msg.status.errorMsg);
						            }
						        }
						    });
						} else {
							alert('请选择上交时间');
							return false;
						}
					}
				});
			});
			//关闭单据
			$('.arrive_close').click(function(){
				$('.mo_del').show();
				$('.clos_e').show().siblings('div').hide();
				$('.action_arrive_close').click(function(){
					var loadId=data.arriveInfo.loadId;
					var reason=$('textarea[data-column="close_reason"]').val();
   					if(reason) {
						var result={};
						result.loadId=loadId;
						result.reason=reason;
						$.ajax({
					        url: global.server + '/admin/arrived/close',
					        type: "post",
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
					} else {
						alert('请输入关闭的原因');
					}
				});
			});
		});
	};
	
	this.init(options);
};


CBSArriveInfo.prototype.fillContentFromJsonData = function (data) {
    if (!data) return;
    var _this = this;
    this.columns.each(function (i, j) {
    	var _this = $(this);
    	var pName = _this.data("column");
    	var pValue = data[pName];
    	if (pName=='length'){
    		if(pValue==null||pValue=='') {
    			_this.text('');
    		} else if(pValue!='其他'){
				_this.text(pValue+" 米");
			} else {
    			_this.text(pValue);
    		}
		} else if(pName=='needStartTime'||pName=='tripTime'||pName=='needArriveTime'){
			if(pValue) {
				var _date=moment(pValue).format("YYYY-MM-DD HH:mm");
				_this.text(_date)
			}
		} else {
			_this.text(pValue);
		}
    });
    var startCodePCn=data.startCodePCn;
    var startCodeCCn=data.startCodeCCn;
    var endCodePCn=data.endCodePCn;
    var endCodeCCn=data.endCodeCCn;
    $('span[data-column="_start"]').text(startCodePCn+" - "+startCodeCCn);
    $('span[data-column="_end"]').text(endCodePCn+" - "+endCodeCCn);
    var driverInfo=data.driverInfo;
    if(driverInfo) {
    	if(driverInfo[0]) {
    		$('span[data-column="realName1"]').text(driverInfo[0].realName);
    		$('span[data-column="telphone1"]').text(driverInfo[0].telphone);
    	}
    	if(driverInfo[1]) {
    		$('span[data-column="realName2"]').text(driverInfo[1].realName);
    		$('span[data-column="telphone2"]').text(driverInfo[1].telphone);
    	}
    }
};

CBSArriveInfo.prototype.fillContentArrive=function(data) {
	if (!data) return;
    var _this = this;
    var createdTime=data.createdTime;
	if(createdTime) {
		createdTime=moment(createdTime).format("YYYY-MM-DD HH:mm");
	}
    var arriveTime=data.arriveTime;
	if(arriveTime) {
		arriveTime=moment(arriveTime).format("YYYY-MM-DD HH:mm");
	}
    $('span[data-column="createdTime"]').text(createdTime);
    $('span[data-column="createdBy"]').text(data.realName+'('+data.position+')');
    $('i[data-column="arriveTime"]').text(arriveTime);
}

CBSArriveInfo.prototype.fillContentElecReceipt=function(data) {
	if(data) {
		var elecFeedbackList=data.elecFeedbackList;
		var receiptId=data.receiptId;
		if(elecFeedbackList.length==0&&!receiptId) {
			$('.elecReceipt').remove();
		} else {
			if(elecFeedbackList.length>0) {
				var driverManagerList = new Array();
				var sourceManagerList = new Array();
				var vehicleManagerList = new Array();
				$.each(elecFeedbackList, function(i, j) {
					if (j.type == '03') {
						driverManagerList.push(j);
					} else if (j.type == '01') {
						sourceManagerList.push(j);
					} else if (j.type == '02') {
						vehicleManagerList.push(j);
					}
				});
				if(sourceManagerList.length>0) {
					var _t=$('<div class="un_content">\
		    				<h4>货主异常</h4>\
		    				<div class="un_bg vc_source_feedback">\
		    				</div>\
		    			</div>');
		    		$('.elecReceipt').append(_t);
		    		$.each(sourceManagerList,function(i,j){
		        		var el=$('<div>\
								<span class="un_time"></span>\
								<span class="un_name"></span>\
								<span class="delay"></span>\
								<span class="un_cost"></span>\
								<div class="delay_reason">\
                                    <span class="rea_con"></span>\
                                    <ul class="pic"></ul>\
                                </div>\
							</div>');
		        		var _date=j.feedbackTime;
		        		if(_date){
		        			_date=moment(_date).format("YYYY-MM-DD HH:mm");
		        		}
		        		var endTime=j.endTime;
						if(endTime) {
							endTime=moment(endTime).format("YYYY-MM-DD HH:mm");
						}
		        		el.find('.un_time').text(_date);
		    			el.find('.un_name').text(j.realName+'('+j.position+')');
		    			el.find('.delay').text(j.itemName);
		    			el.find('.un_cost').text(endTime);
		    			el.find('.rea_con').text(j.itemDesc);
		    			var atta=j.atta;
    	    	    	if(atta){
    	    	    		if (typeof CBSImageInfo === "function") {
    	    		    		$.each(atta,function(i, j){
    	    		    			var _p = $('<li class="images car-img" data-column="imageId"></li>');
    	    	    				el.find('.pic').append(_p);
    	    		        		var category = _p.data("column");
    	    		        		var imageId = j.fileId;
    	    		            	var image = new CBSImageInfo({
    	    		            		view: _p,
    	    		            		category: category,
    	    		            		imageId: imageId
    	    		                });
    	    		    		});
    	    	    		};
    	    	    	}
		        		$('.vc_source_feedback').append(el);
		        	});
				}
				if(vehicleManagerList.length>0) {
					var _t=$('<div class="un_content">\
		    				<h4>车辆异常</h4>\
		    				<div class="un_bg vc_vehicle_feedback">\
		    				</div>\
		    			</div>');
		    		$('.elecReceipt').append(_t);
		    		$.each(vehicleManagerList,function(i,j){
		        		var el=$('<div>\
								<span class="un_time"></span>\
								<span class="un_name"></span>\
								<span class="delay"></span>\
								<span class="un_cost"></span>\
								<div class="delay_reason">\
                                    <span class="rea_con"></span>\
                                    <ul class="pic"></ul>\
                                </div>\
							</div>');
		        		var _date=j.feedbackTime;
		        		if(_date){
		        			_date=moment(_date).format("YYYY-MM-DD HH:mm");
		        		}
		        		var endTime=j.endTime;
						if(endTime) {
							endTime=moment(endTime).format("YYYY-MM-DD HH:mm");
						}
		        		el.find('.un_time').text(_date);
		    			el.find('.un_name').text(j.realName+'('+j.position+')');
		    			el.find('.delay').text(j.itemName);
		    			el.find('.un_cost').text(endTime);
		    			el.find('.rea_con').text(j.itemDesc);
		    			var atta=j.atta;
    	    	    	if(atta){
    	    	    		if (typeof CBSImageInfo === "function") {
    	    		    		$.each(atta,function(i, j){
    	    		    			var _p = $('<li class="images car-img" data-column="imageId"></li>');
    	    	    				el.find('.pic').append(_p);
    	    		        		var category = _p.data("column");
    	    		        		var imageId = j.fileId;
    	    		            	var image = new CBSImageInfo({
    	    		            		view: _p,
    	    		            		category: category,
    	    		            		imageId: imageId
    	    		                });
    	    		    		});
    	    	    		};
    	    	    	}
		        		$('.vc_vehicle_feedback').append(el);
		        	});
				}
				if(driverManagerList.length>0) {
					var _t=$('<div class="un_content">\
		    				<h4>司机异常</h4>\
		    				<div class="un_bg vc_driver_feedback">\
		    				</div>\
		    			</div>');
		    		$('.elecReceipt').append(_t);
		    		$.each(driverManagerList,function(i,j){
		        		var el=$('<div>\
								<span class="un_time"></span>\
								<span class="un_name"></span>\
								<span class="delay"></span>\
								<span class="un_cost"></span>\
								<div class="delay_reason">\
                                    <span class="rea_con"></span>\
                                    <ul class="pic"></ul>\
                                </div>\
							</div>');
		        		var _date=j.feedbackTime;
		        		if(_date){
		        			_date=moment(_date).format("YYYY-MM-DD HH:mm");
		        		}
		        		var endTime=j.endTime;
						if(endTime) {
							endTime=moment(endTime).format("YYYY-MM-DD HH:mm");
						}
		        		el.find('.un_time').text(_date);
		    			el.find('.un_name').text(j.realName+'('+j.position+')');
		    			el.find('.delay').text(j.itemName);
		    			el.find('.un_cost').text(endTime);
		    			el.find('.rea_con').text(j.itemDesc);
		    			var atta=j.atta;
    	    	    	if(atta){
    	    	    		if (typeof CBSImageInfo === "function") {
    	    		    		$.each(atta,function(i, j){
    	    		    			var _p = $('<li class="images car-img" data-column="imageId"></li>');
    	    	    				el.find('.pic').append(_p);
    	    		        		var category = _p.data("column");
    	    		        		var imageId = j.fileId;
    	    		            	var image = new CBSImageInfo({
    	    		            		view: _p,
    	    		            		category: category,
    	    		            		imageId: imageId
    	    		                });
    	    		    		});
    	    	    		};
    	    	    	}
		        		$('.vc_driver_feedback').append(el);
		        	});
				}
			}
			if(receiptId) {
				var _t=$('<div class="un_content">\
						<h4></h4>\
						<div class="un_bg no_bg">\
							<div>\
								<span class="un_time">2016-08-29 15:34</span>\
								<span class="un_name _name1"></span>\
								<span class="arrive_time _time1">上传回单<i></i></span>\
								<span class="un_name _name2"></span>\
								<span class="arrive_time _time2"><i></i></span>\
								<span class="images image-column" data-column="receiptImageRefId"></span>\
								<button class="long">审核</button>\
							</div>\
						</div>\
					</div>');
				var createdTime=data.createdTime;
				if(createdTime) {
					createdTime=moment(createdTime).format("YYYY-MM-DD HH:mm");
				}
					var updatedTime=data.updatedTime;
					if(updatedTime) {
						updatedTime=moment(updatedTime).format("YYYY-MM-DD HH:mm");
					}
					var opTime=data.opTime;
					if(opTime) {
						opTime=moment(opTime).format("YYYY-MM-DD HH:mm");
					}
					_t.find('.un_time').text(createdTime);
					_t.find('._name1').text(global.defaultIfBlack(data.subName,'')+'('+data.subPosition+')');
					_t.find('._time1>i').text(opTime);
					var checkPosition=data.checkPosition;
					if(checkPosition) {
						checkPosition='('+checkPosition+')';
					} else {
						checkPosition='';
					}
					_t.find('._name2').text(global.defaultIfBlack(data.checkName,'')+checkPosition);
					_t.find('._time2').html(data.statusCn+'<i>'+opTime+'</i>');
					var _image=_t.find('.image-column');
					if (typeof CBSImageInfo === "function") {
						var category = _image.data("column");
		        		var imageId = data.receiptImageRefId;
		            	var image = new CBSImageInfo({
		            		view: _image,
		            		category: category,
		            		imageId: imageId
		                });
		    		};
		    		var status=data.status;
		    		if(status=='01') {
						_t.find('.long').click(function(){
							$('.mo_del').show();
							$('.review').show().siblings('div').hide();
						});
						$('.verify_elec_receipt').click(function(){
							var result={};
							var verify=$("input[name='review']:checked").val();
							if(verify) {
								result.verifyResult=verify;
								result.loadId=data.loadId;
								result.receiptId=data.receiptId;
								$.ajax({
							        url: global.server + '/admin/arrived/verifyElecReceipt',
							        type: "post",
							        data: result,
							        success: function (msg) {
							            if (msg && msg.status && msg.status.statusCode == global.status.success) {
							            	window.location.reload();
							            } else {
							            	alert(msg.status.errorMsg);
							            }
							        }
							    });
							} else {
								alert('请确认审核结果');
								return false;
							}
							
						});
					} else if(status=='00'){
						_t.find('.long').remove();
						$('.elec_receipt').hide();
						$('.source_feedback').hide();
						$('.vehicle_feedback').hide();
						$('.print_receipt').show();
						$('.arrive_close').show();
					} else {
						_t.find('.long').remove();
					}
					$('.elecReceipt').append(_t);
			}
		}
	}  else {
		$('.elecReceipt').remove();
	}
}

CBSArriveInfo.prototype.fillContentPrintReceipt=function(data) {
	if(data) {
		var _t=$('<div class="un_content">\
				<h4></h4>\
				<div class="un_bg no_bg">\
					<div>\
						<span class="un_time"></span>\
						<span class="un_name"></span>\
						<span class="company"><i></i><b></b></span>\
						<span class="arrive_time">收到时间<i></i></span>\
						<button class="waybill">收到纸质运单</button>\
					</div>\
				</div>\
			</div>');
		var signTime=data.signTime;
		if(signTime) {
			signTime=moment(signTime).format("YYYY-MM-DD HH:mm");
		}
		var createdTime=data.createdTime;
		if(createdTime) {
			createdTime=moment(createdTime).format("YYYY-MM-DD HH:mm");
		}
		_t.find('.un_time').text(createdTime);
		_t.find('.un_name').text(global.defaultIfBlack(data.subName,'')+'('+global.defaultIfBlack(data.subPosition,'')+')');
		_t.find('.company>i').text(data.expressDelivery);
		_t.find('.company>b').text(data.billNo);
		_t.find('.arrive_time>i').text(signTime);
		$('.printReceipt').append(_t);
		var status=data.status;
		if(status=='01') {
			_t.find('.waybill').click(function(){
				$('.mo_del').show();
				$('.waybill_time').show().siblings('div').hide();
				var _date=moment(new Date()).format("YYYY-MM-DD HH:mm");
				$('input[data-column="signTime"]').val(_date);
				$('input[data-column="signTime"]').each(function(i, j){
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
		        });
				$('.action_print_receipt').click(function(){
					var signTime=$('input[data-column="signTime"]').val();
					if(signTime) {
						var result={};
						result.loadId=data.loadId;
						result.receiptId=data.receiptId;
						result.signTime=signTime;
						$.ajax({
					        url: global.server + '/admin/arrived/confirmPrintReceipt',
					        type: "post",
					        data: result,
					        success: function (msg) {
					            if (msg && msg.status && msg.status.statusCode == global.status.success) {
					            	window.location.reload();
					            	window.opener.location.reload();
					            } else {
					            	alert(msg.status.errorMsg);
					            }
					        }
					    });
					} else {
						alert('请选择收到纸质回单的时间');
						return false;
					}
				});
			});
		} else {
			$('.print_receipt').hide();
			$('.arrive_close').hide();
			_t.find('.waybill').remove();
		}
	} else {
		$('.printReceipt').remove();
	}
}

$(function(){
	
	var loadId = global.QueryString.loadId;
	var param = {
		loadId : loadId
	};
	var arrive=new CBSArriveInfo(param);
	
});