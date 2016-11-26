var CBSOrderDetail = function(options){
	this.opt = $.extend({}, options);
//	this.imageColumns = $('.car-img');

	this.init = function(options){
		var _this = this;
		var orderId = _this.opt.orderId;
		$.get(global.server+'/admin/order/getOrderDetail',{orderId:orderId}, function(msg){
			if (msg && msg.status && msg.status.statusCode == global.status.success) {
				var data = msg.data;
				_this.data = data;
				_this.fillContentFromJsonData(data);
			}
		});
		//_this.getRemarkHis(orderId);
	};
	

	this.init(options);
};
CBSOrderDetail.prototype.getFullAddress = function(info){
	if(info!=null){
		var pcd = [];
		var area = info.area;
		$.each(area, function(i, j){
			pcd.push(j.regionName);
		});
		var str = pcd.reverse().join("");
		return str+info.address;
	} else {
		return "";
	}
};

CBSOrderDetail.prototype.fillData = function(data, selector){
    $(selector).each(function(i, j){
    	var properties = $(j).data('column');
    	var pList = properties.split(',');
    	var $self = $(j);
    	if(data!=null){
    		var value = [];
    		$.each(pList,function(i, j){
    			if(data[j]){
    				if(j=='createdTime'||j=="needStartTime"||j=="needArriveTime") {
    					var _date=moment(data[j]).format("YYYY-MM-DD HH:mm");
        				value.push(_date);
        			} else if(j=="needLength") {
        				if(data[j] == '其他'){
        					value.push(data[j]);
        				} else {
        					value.push(data[j]+" 米");
        				}
        			}else {
        				value.push(data[j]);
        			}
    			}
        	});
    		$self.text(value.join(' '));
    	} else {
    		$self.text("");
    	}
    });
}

//车检异常反馈
CBSOrderDetail.prototype.fillVCFeedBack=function(data) {
	var _this=this;
	var VCFeedBackList=data.VCFeedBackList;
    if(VCFeedBackList==undefined) {
    	$('.VCFeedBack-info').remove();
    } else {
    	var feedBackList=_this.splitFeedBackList(VCFeedBackList.feedBackList);
    	var driverManagerList=feedBackList.driverManagerList;
        var sourceManagerList=feedBackList.sourceManagerList;
        var vehicleManagerList=feedBackList.vehicleManagerList;
        if(VCFeedBackList.actionTime) {
        	var _date=moment(VCFeedBackList.actionTime).format("YYYY-MM-DD HH:mm");
    		$('.vc_action_time').text(_date);
    	} else {
    		if(driverManagerList.length==0&&sourceManagerList.length==0&&vehicleManagerList.length==0) {
	        	$('.VCFeedBack-info').remove();
	        }
    	}
        if(driverManagerList.length>0) {
    		var _t=$('<div class="un_content">\
    				<h4>司机异常</h4>\
    				<div class="un_bg vc_driver_feedback">\
    				</div>\
    			</div>');
    		$('.VCFeedBack-info').append(_t);
    		$.each(driverManagerList,function(i,j){
        		var el=$('<div class="un_div">\
						<span class="un_num"></span>\
						<span class="un_time"></span>\
						<span class="un_name"></span>\
						<span class="un_cost"></span>\
						<div class="un_reason">\
                            <span class="un_rea_span"></span>\
                            <ul class="pic">\
                            </ul>\
                        </div>\
					</div>');
        		var _date=j.feedbackTime;
        		if(_date){
        			_date=moment(_date).format("YYYY-MM-DD HH:mm");
        		}
        		el.find('.un_num').text((i+1)+".");
    			el.find('.un_time').text(_date);
    			el.find('.un_name').text(j.itemName);
    			el.find('.un_cost').text(j.itemPrice?j.itemPrice+' 元':'');
    			el.find('.un_rea_span').text(j.itemDesc);
    			var atta=j.atta;
    			if(atta.length>0) {
    				 $.each(atta,function(k,l){
    					 var _p = $('<li class="images car-img" data-column="imageId"></li>');
         				el.find('.pic').append(_p);
     	        		var category = _p.data("column");
     	        		var imageId = l.fileId;
     	            	var image = new CBSImageInfo({
     	            		view: _p,
     	            		category: category,
     	            		imageId: imageId
     	                });
    				 });
    			}
        		$('.vc_driver_feedback').append(el);
        	});
    	}
    	if(sourceManagerList.length>0) {
    		var _t=$('<div class="un_content">\
    				<h4>货主异常</h4>\
    				<div class="un_bg vc_source_feedback">\
    				</div>\
    			</div>');
    		$('.VCFeedBack-info').append(_t);
    		$.each(sourceManagerList,function(i,j){
    			var el=$('<div class="un_div">\
						<span class="un_num"></span>\
						<span class="un_time"></span>\
						<span class="un_name"></span>\
						<span class="un_cost"></span>\
						<div class="un_reason">\
                            <span class="un_rea_span"></span>\
                            <ul class="pic">\
                            </ul>\
                        </div>\
					</div>');
    			var _date=j.feedbackTime;
        		if(_date){
        			_date=moment(_date).format("YYYY-MM-DD HH:mm");
        		}
    			el.find('.un_num').text((i+1)+".");
    			el.find('.un_time').text(_date);
    			el.find('.un_name').text(j.itemName);
    			el.find('.un_cost').text(j.itemPrice?j.itemPrice+' 元':'');
    			el.find('.un_rea_span').text(j.itemDesc);
    			var atta=j.atta;
    			if(atta.length>0) {
    				 $.each(atta,function(k,l){
    					 var _p = $('<li class="images car-img" data-column="imageId"></li>');
         				el.find('.pic').append(_p);
     	        		var category = _p.data("column");
     	        		var imageId = l.fileId;
     	            	var image = new CBSImageInfo({
     	            		view: _p,
     	            		category: category,
     	            		imageId: imageId
     	                });
    				 });
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
    		$('.VCFeedBack-info').append(_t);
    		$.each(vehicleManagerList,function(i,j){
    			var el=$('<div class="un_div">\
						<span class="un_num"></span>\
						<span class="un_time"></span>\
						<span class="un_name"></span>\
						<span class="un_cost"></span>\
						<div class="un_reason">\
                            <span class="un_rea_span"></span>\
                            <ul class="pic">\
                            </ul>\
                        </div>\
					</div>');
    			var _date=j.feedbackTime;
        		if(_date){
        			_date=moment(_date).format("YYYY-MM-DD HH:mm");
        		}
    			el.find('.un_num').text((i+1)+".");
    			el.find('.un_time').text(_date);
    			el.find('.un_name').text(j.itemName);
    			el.find('.un_cost').text(j.itemPrice?j.itemPrice+' 元':'');
    			el.find('.un_rea_span').text(j.itemDesc);
    			var atta=j.atta;
    			if(atta.length>0) {
    				 $.each(atta,function(k,l){
    					 var _p = $('<li class="images car-img" data-column="imageId"></li>');
         				el.find('.pic').append(_p);
     	        		var category = _p.data("column");
     	        		var imageId = l.fileId;
     	            	var image = new CBSImageInfo({
     	            		view: _p,
     	            		category: category,
     	            		imageId: imageId
     	                });
    				 });
    			}
        		$('.vc_vehicle_feedback').append(el);
        	});
    	}
    }
}

//靠台异常反馈
CBSOrderDetail.prototype.fillCTSFeedBack=function(data) {
	var _this=this;
	var CTSFeedBackList=data.CTSFeedBackList;
    if(CTSFeedBackList==undefined) {
    	$('.CTSFeedBack-info').remove();
    } else {
    	var feedBackList=_this.splitFeedBackList(CTSFeedBackList.feedBackList);
    	var driverManagerList=feedBackList.driverManagerList;
        var sourceManagerList=feedBackList.sourceManagerList;
        var vehicleManagerList=feedBackList.vehicleManagerList;
        if(CTSFeedBackList.actionTime) {
        	var _date=moment(CTSFeedBackList.actionTime).format("YYYY-MM-DD HH:mm");
        	$('.cts_action_time').text(_date);
        } else {
        	if(driverManagerList.length==0&&sourceManagerList.length==0&&vehicleManagerList.length==0) {
	        	$('.CTSFeedBack-info').remove();
	        }
        }
        if(driverManagerList.length>0) {
    		var _t=$('<div class="un_content">\
    				<h4>司机异常</h4>\
    				<div class="un_bg cts_driver_feedback">\
    				</div>\
    			</div>');
    		$('.CTSFeedBack-info').append(_t);
    		$.each(driverManagerList,function(i,j){
        		var el=$('<div class="un_div">\
						<span class="un_num"></span>\
						<span class="un_time"></span>\
						<span class="un_name"></span>\
						<span class="un_cost"></span>\
						<div class="un_reason">\
                            <span class="un_rea_span"></span>\
                            <ul class="pic">\
                            </ul>\
                        </div>\
					</div>');
        		var _date=j.feedbackTime;
        		if(_date){
        			_date=moment(_date).format("YYYY-MM-DD HH:mm");
        		}
        		el.find('.un_num').text((i+1)+".");
    			el.find('.un_time').text(_date);
    			el.find('.un_name').text(j.itemName);
    			el.find('.un_cost').text(j.itemPrice?j.itemPrice+' 元':'');
    			el.find('.un_rea_span').text(j.itemDesc);
    			var atta=j.atta;
    			if(atta.length>0) {
    				 $.each(atta,function(k,l){
    					 var _p = $('<li class="images car-img" data-column="imageId"></li>');
         				el.find('.pic').append(_p);
     	        		var category = _p.data("column");
     	        		var imageId = l.fileId;
     	            	var image = new CBSImageInfo({
     	            		view: _p,
     	            		category: category,
     	            		imageId: imageId
     	                });
    				 });
    			}
        		$('.cts_driver_feedback').append(el);
        	});
    	}
    	if(sourceManagerList.length>0) {
    		var _t=$('<div class="un_content">\
    				<h4>货主异常</h4>\
    				<div class="un_bg cts_source_feedback">\
    				</div>\
    			</div>');
    		$('.CTSFeedBack-info').append(_t);
    		$.each(sourceManagerList,function(i,j){
    			var el=$('<div class="un_div">\
						<span class="un_num"></span>\
						<span class="un_time"></span>\
						<span class="un_name"></span>\
						<span class="un_cost"></span>\
						<div class="un_reason">\
                            <span class="un_rea_span"></span>\
                            <ul class="pic">\
                            </ul>\
                        </div>\
					</div>');
    			var _date=j.feedbackTime;
        		if(_date){
        			_date=moment(_date).format("YYYY-MM-DD HH:mm");
        		}
    			el.find('.un_num').text((i+1)+".");
    			el.find('.un_time').text(_date);
    			el.find('.un_name').text(j.itemName);
    			el.find('.un_cost').text(j.itemPrice?j.itemPrice+' 元':'');
    			el.find('.un_rea_span').text(j.itemDesc);
    			var atta=j.atta;
    			if(atta.length>0) {
    				 $.each(atta,function(k,l){
    					 var _p = $('<li class="images car-img" data-column="imageId"></li>');
         				el.find('.pic').append(_p);
     	        		var category = _p.data("column");
     	        		var imageId = l.fileId;
     	            	var image = new CBSImageInfo({
     	            		view: _p,
     	            		category: category,
     	            		imageId: imageId
     	                });
    				 });
    			}
        		$('.cts_source_feedback').append(el);
        	});
    	}
    	if(vehicleManagerList.length>0) {
    		var _t=$('<div class="un_content">\
    				<h4>车辆异常</h4>\
    				<div class="un_bg cts_vehicle_feedback">\
    				</div>\
    			</div>');
    		$('.CTSFeedBack-info').append(_t);
    		$.each(vehicleManagerList,function(i,j){
    			var el=$('<div class="un_div">\
						<span class="un_num"></span>\
						<span class="un_time"></span>\
						<span class="un_name"></span>\
						<span class="un_cost"></span>\
						<div class="un_reason">\
                            <span class="un_rea_span"></span>\
                            <ul class="pic">\
                            </ul>\
                        </div>\
					</div>');
    			var _date=j.feedbackTime;
        		if(_date){
        			_date=moment(_date).format("YYYY-MM-DD HH:mm");
        		}
    			el.find('.un_num').text((i+1)+".");
    			el.find('.un_time').text(_date);
    			el.find('.un_name').text(j.itemName);
    			el.find('.un_cost').text(j.itemPrice?j.itemPrice+' 元':'');
    			el.find('.un_rea_span').text(j.itemDesc);
    			var atta=j.atta;
    			if(atta.length>0) {
    				 $.each(atta,function(k,l){
    					 var _p = $('<li class="images car-img" data-column="imageId"></li>');
         				el.find('.pic').append(_p);
     	        		var category = _p.data("column");
     	        		var imageId = l.fileId;
     	            	var image = new CBSImageInfo({
     	            		view: _p,
     	            		category: category,
     	            		imageId: imageId
     	                });
    				 });
    			}
        		$('.cts_vehicle_feedback').append(el);
        	});
    	}
    }
}

//发车异常反馈
CBSOrderDetail.prototype.fillTripFeedBack=function(data) {
	var _this=this;
	var TripFeedBackList=data.TripFeedBackList;
    if(TripFeedBackList==undefined) {
    	$('.trip-info').remove();
    } else {
    	var feedBackList=_this.splitFeedBackList(TripFeedBackList.feedBackList);
    	var driverManagerList=feedBackList.driverManagerList;
        var sourceManagerList=feedBackList.sourceManagerList;
        var vehicleManagerList=feedBackList.vehicleManagerList;
        if(driverManagerList.length==0&&sourceManagerList.length==0&&vehicleManagerList.length==0) {
        	if(TripFeedBackList.actionTime) {
        		var _date=moment(TripFeedBackList.actionTime).format("YYYY-MM-DD HH:mm");
        		$('.trip_action_time').text(_date);
            } else {
            	if(driverManagerList.length==0&&sourceManagerList.length==0&&vehicleManagerList.length==0) {
		        	$('.trip-info').remove();
		        }
            }
        } else {
        	if(TripFeedBackList.actionTime) {
        		var _date=moment(TripFeedBackList.actionTime).format("YYYY-MM-DD HH:mm");
        		$('.trip_action_time').text(_date);
        	}
        }
        if(driverManagerList.length>0) {
    		var _t=$('<div class="un_content">\
    				<h4>司机异常</h4>\
    				<div class="un_bg trip_driver_feedback">\
    				</div>\
    			</div>');
    		$('.trip-info').append(_t);
    		$.each(driverManagerList,function(i,j){
        		var el=$('<div class="un_div">\
						<span class="un_num"></span>\
						<span class="un_time"></span>\
						<span class="un_name"></span>\
						<span class="un_cost"></span>\
						<div class="un_reason">\
                            <span class="un_rea_span"></span>\
                            <ul class="pic">\
                            </ul>\
                        </div>\
					</div>');
        		var _date=j.feedbackTime;
        		if(_date){
        			_date=moment(_date).format("YYYY-MM-DD HH:mm");
        		}
        		el.find('.un_num').text((i+1)+".");
    			el.find('.un_time').text(_date);
    			el.find('.un_name').text(j.itemName);
    			el.find('.un_cost').text(j.itemPrice?j.itemPrice+' 元':'');
    			el.find('.un_rea_span').text(j.itemDesc);
    			var atta=j.atta;
    			if(atta.length>0) {
    				 $.each(atta,function(k,l){
    					 var _p = $('<li class="images car-img" data-column="imageId"></li>');
         				el.find('.pic').append(_p);
     	        		var category = _p.data("column");
     	        		var imageId = l.fileId;
     	            	var image = new CBSImageInfo({
     	            		view: _p,
     	            		category: category,
     	            		imageId: imageId
     	                });
    				 });
    			}
        		$('.trip_driver_feedback').append(el);
        	});
    	}
    	if(sourceManagerList.length>0) {
    		var _t=$('<div class="un_content">\
    				<h4>货主异常</h4>\
    				<div class="un_bg trip_source_feedback">\
    				</div>\
    			</div>');
    		$('.trip-info').append(_t);
    		$.each(sourceManagerList,function(i,j){
    			var el=$('<div class="un_div">\
						<span class="un_num"></span>\
						<span class="un_time"></span>\
						<span class="un_name"></span>\
						<span class="un_cost"></span>\
						<div class="un_reason">\
                            <span class="un_rea_span"></span>\
                            <ul class="pic">\
                            </ul>\
                        </div>\
					</div>');
    			var _date=j.feedbackTime;
        		if(_date){
        			_date=moment(_date).format("YYYY-MM-DD HH:mm");
        		}
    			el.find('.un_num').text((i+1)+".");
    			el.find('.un_time').text(_date);
    			el.find('.un_name').text(j.itemName);
    			el.find('.un_cost').text(j.itemPrice?j.itemPrice+' 元':'');
    			el.find('.un_rea_span').text(j.itemDesc);
    			var atta=j.atta;
    			if(atta.length>0) {
    				 $.each(atta,function(k,l){
    					 var _p = $('<li class="images car-img" data-column="imageId"></li>');
         				el.find('.pic').append(_p);
     	        		var category = _p.data("column");
     	        		var imageId = l.fileId;
     	            	var image = new CBSImageInfo({
     	            		view: _p,
     	            		category: category,
     	            		imageId: imageId
     	                });
    				 });
    			}
        		$('.trip_source_feedback').append(el);
        	});
    	}
    	if(vehicleManagerList.length>0) {
    		var _t=$('<div class="un_content">\
    				<h4>车辆异常</h4>\
    				<div class="un_bg trip_vehicle_feedback">\
    				</div>\
    			</div>');
    		$('.trip-info').append(_t);
    		$.each(vehicleManagerList,function(i,j){
    			var el=$('<div class="un_div">\
						<span class="un_num"></span>\
						<span class="un_time"></span>\
						<span class="un_name"></span>\
						<span class="un_cost"></span>\
						<div class="un_reason">\
                            <span class="un_rea_span"></span>\
                            <ul class="pic">\
                            </ul>\
                        </div>\
					</div>');
    			var _date=j.feedbackTime;
        		if(_date){
        			_date=moment(_date).format("YYYY-MM-DD HH:mm");
        		}
    			el.find('.un_num').text((i+1)+".");
    			el.find('.un_time').text(_date);
    			el.find('.un_name').text(j.itemName);
    			el.find('.un_cost').text(j.itemPrice?j.itemPrice+' 元':'');
    			el.find('.un_rea_span').text(j.itemDesc);
    			var atta=j.atta;
    			if(atta.length>0) {
    				 $.each(atta,function(k,l){
    					 var _p = $('<li class="images car-img" data-column="imageId"></li>');
         				el.find('.pic').append(_p);
     	        		var category = _p.data("column");
     	        		var imageId = l.fileId;
     	            	var image = new CBSImageInfo({
     	            		view: _p,
     	            		category: category,
     	            		imageId: imageId
     	                });
    				 });
    			}
        		$('.trip_vehicle_feedback').append(el);
        	});
    	}
    }
}

//发车反馈
CBSOrderDetail.prototype.fillTripDetail=function(data) {
	var tripFeedBackDetail=data.tripFeedBackDetail;
    if(tripFeedBackDetail) {
    	var el=$('<div class="clearfix trip-detail">\
				<div class="left fl">\
				<div class="layout clearfix">\
					<p class="fl stage ">客户单号</p>\
					<span class="fr spa_n trip-column customerBillNo" data-column="customerBillNo"></span>\
				</div>\
				<div class="layout clearfix">\
					<p class="fl stage ">实际重量</p>\
					<span class="fr spa_n trip-column goodsWeight" data-column="goodsWeight"><b class="more_unit"></b>&nbsp;&nbsp;kg</span>\
				</div>\
				<div class="layout clearfix">\
					<p class="fl stage ">实际体积</p>\
					<span class="fr spa_n trip-column goodsVolume" data-column="goodsVolume"><b class="more_unit"></b>&nbsp;&nbsp;m³</span>\
				</div>\
			</div>\
            <div class="fr">\
                <p class="images car-img" data-column="contractImageRefId"></p>\
                <span class="img_word">货主托运合同照片</span>\
            </div>\
		</div>');
    	var goodsWeight=tripFeedBackDetail.goodsWeight;
    	var goodsVolume=tripFeedBackDetail.goodsVolume;
    	el.find('.customerBillNo').text(tripFeedBackDetail.customerBillNo);
    	el.find('.goodsWeight').text(goodsWeight?goodsWeight+' kg':'');
    	el.find('.goodsVolume').text(goodsVolume?goodsVolume+' m³':'');
    	if (typeof CBSImageInfo === "function") {
        	el.find('.car-img').each(function(i, j){
        		var category = $(this).data("column");
        		var imageId = tripFeedBackDetail[category];
            	var image = new CBSImageInfo({
            		view: $(this),
            		category: $(this).data("column"),
            		imageId: imageId
                });
            });
        };
    	$('.trip-info').append(el);
    }
}

//在途跟踪
CBSOrderDetail.prototype.fillTrackFeedBack=function(data,loadNo) {
	var transitFeedBackList=data.transitFeedBackList;
	if(transitFeedBackList&&transitFeedBackList.length>0) {
		var main=$('<h3>跟踪信息</h3><div class="main_box">');
        $('.track_feedback').append(main);
		$.each(transitFeedBackList.reverse(),function(i,j){
	    	var el=$('<div class="box_line">\
					<b class="circle"></b>\
					<span class="track_time"></span>\
					<span class="track_people"></span>\
	    			<a class="track_location" href="javascript:void(0);"></a>\
					<div class="track_content">\
						<div class="abnormal">\
							<div class="ab_line">\
								<span class="ab_reason">异常：<i class="itemName"></i></span>\
								<span class="ab_start">开始时间：<i class="track_start"></i></span>\
								<span class="ab_end">结束时间：<i class="track_end"></i></span>\
							</div>\
							<div class="ab_remark"></div>\
							<ul class="pic">\
							</ul>\
						</div>\
					</div>\
				</div>')
			var longitude=j.longitude;
	    	var latitude=j.latitude;
	    	var track_position='';
	    	if(longitude) {
	    		track_position=longitude+","+latitude;
	    		el.find('.track_location').addClass('active');
	    		el.find('.track_location').attr('href',global.getContextPath() + '/ViewMap.html?type=02&billNo='+loadNo).attr('target','_blank');
	    	} else {
	    		track_position=j.address;
	    		el.find('.track_location').addClass('no_hand');
	    	}
	    	var createdTime=j.createdTime;
	    	var feedbackTime=j.feedbackTime;
	    	var endTime=j.endTime;
	    	if(createdTime) {
	    		createdTime=moment(createdTime).format("YYYY-MM-DD HH:mm");
	    	}
	    	if(feedbackTime) {
	    		feedbackTime=moment(feedbackTime).format("YYYY-MM-DD HH:mm");
	    	}
	    	if(endTime) {
	    		endTime=moment(endTime).format("YYYY-MM-DD HH:mm");
	    	}
	    	var isException=j.isException;
	    	if(isException=='00') {
	    		el.find('.ab_line').hide();
	    	} else if(isException=='01') {
	    		el.find('.ab_line').show();
	    	}
			el.find('.track_time').text(createdTime);
			if(j.realName) {
				if(j.position) {
					el.find('.track_people').html(j.realName+'(<i class="people">'+j.position+'</i>)');
				} else {
					el.find('.track_people').html(j.realName);
				}
			} else {
				el.find('.track_people').text('');
			}
	    	el.find('.track_location').text(track_position);
	    	el.find('.itemName').text(j.itemName);
	    	el.find('.track_start').text(feedbackTime);
	    	el.find('.track_end').text(endTime);
	    	el.find('.ab_remark').text(j.itemDesc);
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
			$('.main_box').append(el);
	    });
	}
}

//根据异常类型重新组装发车阶段异常数据
CBSOrderDetail.prototype.splitFeedBackList=function(data) {
	var feedBackList={};
	var driverManagerList=new Array();
	var sourceManagerList=new Array();
	var vehicleManagerList=new Array();
	$.each(data,function(i,j){
		if(j.type=='03') {
			driverManagerList.push(j);
		} else if(j.type=='01') {
			sourceManagerList.push(j);
		} else if(j.type=='02') {
			vehicleManagerList.push(j);
		}
	});
	feedBackList.driverManagerList=driverManagerList;
	feedBackList.sourceManagerList=sourceManagerList;
	feedBackList.vehicleManagerList=vehicleManagerList;
	return feedBackList;
}

CBSOrderDetail.prototype.fillContentFromJsonData = function (data) {
    if (!data) return;
    var _this = this;
    //console.log(data);
    var consignmentInfo = data.consignmentInfo;
    if(consignmentInfo){
    	_this.fillData(consignmentInfo,'.consignmentInfo-column');
    } else {
    	$('.consign-info').remove();
    }
    //提货单
    var loadInfo=data.loadInfo;
    if(loadInfo) {
    	_this.fillData(loadInfo,'.load-column');
    	var driverInfo=loadInfo.driverInfo;
    	var realName=$('span[data-column="_realName"]');
    	var telphone=$('span[data-column="_telphone"]');
    	$.each(driverInfo,function(i,j){
    		if(j.realName) {
    			$(realName[i]).text(j.realName);
    		}
    		if(j.telphone) {
    			$(telphone[i]).text(j.telphone);
    		}
    	});
    } else {
    	$('.load-info').remove();
    }
    
    //车辆异常反馈
    _this.fillVCFeedBack(data);
    
    //靠台异常反馈
    _this.fillCTSFeedBack(data);
    
    //发车异常反馈
    _this.fillTripFeedBack(data);
    
    //发车反馈
    _this.fillTripDetail(data);
    
    //在途跟踪
    if(data.loadInfo) {
    	var loadNo=data.loadInfo.loadNo;
        _this.fillTrackFeedBack(data,loadNo);
    }
    
    var arriveInfo=data.arriveInfo;
    if(arriveInfo) {
    	//到达
    	var arriveDetail=arriveInfo.arriveInfo;
    	if(arriveDetail) {
    		var arriveTime=arriveDetail.arriveTime;
    		if(arriveTime) {
    			var createdTime=arriveDetail.createdTime;
    			if(createdTime) {
    				createdTime=moment(createdTime).format("YYYY-MM-DD HH:mm");
    			}
    			arriveTime=moment(arriveTime).format("YYYY-MM-DD HH:mm");
    			$('span[data-column="createdTime"]').text(createdTime);
        	    $('span[data-column="createdBy"]').text(arriveDetail.realName+'('+arriveDetail.position+')');
        	    $('i[data-column="arriveTime"]').text(arriveTime);
    		} else {
    			$('.arrive_info').remove();
    		}
    	}
    	//电子回单
    	var elecReceipt=arriveInfo.elecReceipt;
    	if(elecReceipt) {
    		var elecFeedbackList=elecReceipt.elecFeedbackList;
    		var receiptId=elecReceipt.receiptId;
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
        	    				<div class="un_bg arrive_source_feedback">\
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
        	        		$('.arrive_source_feedback').append(el);
        	        	});
        			}
        			if(vehicleManagerList.length>0) {
        				var _t=$('<div class="un_content">\
        	    				<h4>车辆异常</h4>\
        	    				<div class="un_bg arrive_vehicle_feedback">\
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
        	        		$('.arrive_vehicle_feedback').append(el);
        	        	});
        			}
        			if(driverManagerList.length>0) {
        				var _t=$('<div class="un_content">\
        	    				<h4>司机异常</h4>\
        	    				<div class="un_bg arrive_driver_feedback">\
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
        	        		$('.arrive_driver_feedback').append(el);
        	        	});
        			}
    			}
    			if(receiptId) {
    				var _t=$('<div class="un_content">\
    						<h4></h4>\
    						<div class="un_bg no_bg">\
    							<div>\
		    						<span class="un_time"></span>\
		    						<span class="un_name _name1"></span>\
		    						<span class="arrive_time _time1">上传回单<i></i></span>\
    								<span class="un_name _name2"></span>\
		    						<span class="arrive_time _time2">审核不通过<i>2016-10-29 15:34</i></span>\
		    						<span class="images image-column no_radius" data-column="receiptImageRefId"></span>\
    							</div>\
    						</div>\
    					</div>');
	    				var createdTime=elecReceipt.createdTime;
						if(createdTime) {
							createdTime=moment(createdTime).format("YYYY-MM-DD HH:mm");
						}
    					var updatedTime=elecReceipt.updatedTime;
    					if(updatedTime) {
    						updatedTime=moment(updatedTime).format("YYYY-MM-DD HH:mm");
    					}
    					var opTime=elecReceipt.opTime;
    					if(opTime) {
    						opTime=moment(opTime).format("YYYY-MM-DD HH:mm");
    					}
    					_t.find('.un_time').text(createdTime);
    					_t.find('._name1').text(global.defaultIfBlack(elecReceipt.subName,'')+'('+elecReceipt.subPosition+')');
    					_t.find('._time1>i').text(opTime);
    					var checkPosition=elecReceipt.checkPosition;
    					if(checkPosition) {
    						checkPosition='('+checkPosition+')';
    					} else {
    						checkPosition='';
    					}
    					_t.find('._name2').text(global.defaultIfBlack(elecReceipt.checkName,'')+checkPosition);
    					_t.find('._time2').html(elecReceipt.statusCn+'<i>'+updatedTime+'</i>');
    					var _image=_t.find('.image-column');
    					if (typeof CBSImageInfo === "function") {
    						var category = _image.data("column");
    		        		var imageId = elecReceipt.receiptImageRefId;
    		            	var image = new CBSImageInfo({
    		            		view: _image,
    		            		category: category,
    		            		imageId: imageId
    		                });
    		    		};
    					$('.elecReceipt').append(_t);
    			}
    		}
    	} else {
    		$('.elecReceipt').remove();
    	}
    	//纸质回单
    	var printReceipt=arriveInfo.printReceipt;
    	if(printReceipt) {
    		var _t=$('<div class="un_content">\
    				<h4></h4>\
    				<div class="un_bg no_bg">\
    					<div>\
    						<span class="un_time"></span>\
    						<span class="un_name"></span>\
    						<span class="company"><i></i><b></b></span>\
    						<span class="arrive_time">收到时间<i></i></span>\
    					</div>\
    				</div>\
    			</div>');
    		var signTime=printReceipt.signTime;
    		if(signTime) {
    			signTime=moment(signTime).format("YYYY-MM-DD HH:mm");
    		}
    		var createdTime=printReceipt.createdTime;
    		if(createdTime) {
    			createdTime=moment(createdTime).format("YYYY-MM-DD HH:mm");
    		}
    		_t.find('.un_time').text(createdTime);
    		_t.find('.un_name').text(global.defaultIfBlack(printReceipt.subName,'')+'('+global.defaultIfBlack(printReceipt.subPosition,'')+')');
    		_t.find('.company>i').text(printReceipt.expressDelivery);
    		_t.find('.company>b').text(printReceipt.billNo);
    		_t.find('.arrive_time>i').text(signTime);
    		$('.printReceipt').append(_t);
    	} else {
    		$('.printReceipt').remove();
    	}
    } else {
    	$('.arrive_info').remove();
    	$('.elecReceipt').remove();
    	$('.printReceipt').remove();
    }
    
    var chargeInfo = data.chargeInfo;
    _this.fillData(chargeInfo,'.chargeInfo-column');
    
    var orderBaseInfo = data.orderBaseInfo
    _this.fillData(orderBaseInfo,'.orderBaseInfo-column');
    var isPredict=orderBaseInfo.isPredict;
    if(isPredict=='01') {
    	$('i[data-column="isPredict"]').text('准确');
    } else if(isPredict=='02') {
    	$('i[data-column="isPredict"]').text('预估');
    }
    

    var orderDetailInfo = data.orderDetailInfo;
    _this.fillData(orderDetailInfo,'.orderDetailInfo-column');

    var orderLineInfo = data.orderLineInfo;
    _this.fillData(orderLineInfo,'.orderLineInfo-column');
    

    var orderReceiverInfo = data.orderReceiverInfo;
    _this.fillData(orderReceiverInfo,'.orderReceiverInfo-column');
    var orderSenderInfo = data.orderSenderInfo;
    _this.fillData(orderSenderInfo,'.orderSenderInfo-column');

};

CBSOrderDetail.prototype.toOrderDetail = function(){
	var _this = this;
	var orderId = _this.data.orderDetailInfo.orderId;
	var url = global.getContextPath() + '/checkBusinessSource.html?orderId='+orderId;
	var body = document.getElementsByTagName("body")[0];
	var el = document.createElement("a");
	body.appendChild(el);
	el.href = url;
	el.target = '_blank';
	el.click();
	body.removeChild(el);
}


CBSOrderDetail.prototype.toConsignDetail = function(){
	var _this = this;
	var consignId = _this.data.consignmentInfo.consignId;
	var url = 'checkBusContract.html?consignId='+consignId;
	var body = document.getElementsByTagName("body")[0];
	var el = document.createElement("a");
	body.appendChild(el);
	el.href = url;
	el.target = '_blank';
	el.click();
	body.removeChild(el);
}

CBSOrderDetail.prototype.toLoadDetail=function(){
	var _this = this;
	var loadId = _this.data.loadInfo.loadId;
	var url = 'checkBusVehicles.html?loadId='+loadId;
	var body = document.getElementsByTagName("body")[0];
	var el = document.createElement("a");
	body.appendChild(el);
	el.href = url;
	el.target = '_blank';
	el.click();
	body.removeChild(el);
}



$(function(){
	
	var orderId = global.QueryString.orderId;

	var param = {
		orderId : orderId
	};
	var overView = new CBSOrderDetail(param);
	
	$('.order-info .to_order_detail').click(function(){
		overView.toOrderDetail();
	});
	
	$('.consign-info .to_order_detail').click(function(){
		overView.toConsignDetail();
	});
	
	$('.load-info .to_order_detail').click(function(){
		overView.toLoadDetail();
	});
	
});
