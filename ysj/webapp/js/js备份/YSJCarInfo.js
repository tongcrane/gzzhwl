/**
 * 
 */
var YSJCarInfo = function(options){
	this.opt = $.extend({}, options);
	this.el = $('<div class="tab" style="display:block;"> \
			<div class="driver_w clearfix dri_w"> \
			<div class="driver_w_l"> \
				<dl class="driver_info_l"> \
					<dd> \
						<strong>车牌号</strong><span data-column="plateNumber" class="text-column"></span> \
					</dd> \
				</dl> \
			</div> \
			<div class="driver_w_r"> \
				<ul class="clearfix driver_pics"> \
					<li> \
						<p class="pic_w" data-column="licenseImageRefId"> \
							<img class="add-btn picture" src="img/photo.png"> \
						</p> \
						<p class="txt">车辆行驶证照片</p> \
					</li> \
					<li> \
						<p class="pic_w" data-column="licenseImage2RefId"> \
							<img class="add-btn picture" src="img/photo.png"> \
						</p> \
						<p class="txt">车辆行驶证照片</p> \
					</li> \
					<li> \
						<p class="pic_w" data-column="ocImage2RefId"> \
							<img class="add-btn picture" src="img/photo.png"> \
						</p> \
						<p class="txt">车辆营运证照片</p> \
					</li> \
					<li> \
						<p class="pic_w" data-column="ocImage3RefId"> \
							<img class="add-btn picture" src="img/photo.png"> \
						</p> \
						<p class="txt">车辆营运证照片</p> \
					</li> \
					<li> \
						<p class="pic_w" data-column="viImageRefId"> \
							<img class="add-btn picture" src="img/photo.png"> \
						</p> \
						<p class="txt">商业保险照片</p> \
					</li> \
					<li> \
						<p class="pic_w" data-column="ciImageRefId"> \
							<img class="add-btn picture" src="img/photo.png"> \
						</p> \
						<p class="txt">强制险照片</p> \
					</li> \
					<li class="left-pic"> \
						<p class="pic_w" data-column="ocImageRefId"> \
							<img class="add-btn picture" src="img/photo.png"> \
						</p> \
						<p class="txt">车辆营运证照片</p> \
					</li> \
				</ul> \
			</div> \
		</div> \
	</div>');
	
	this.addDriver = function(driverInfo){
		var _this = this;
		var num = $('.tab_tit').find('.driver_tab_title').size();
		var el = $('<li class="driver_tab_title"><a href="javascript:;" class="check"><strong>司机信息</strong></a></li>');
		el.insertBefore('.edit_btn');
		var append = $('#info_tab')
		var driver = new YSJDriverInfo({
			append : append,
			data : driverInfo
		});
	}
	
	this.bindEvent = function(){
		var _this = this;
		$('.tab_tit').on('click','li',function(){
			$(this).siblings('li').removeClass('current');
			$(this).addClass('current');
			var i = $(this).index();
			$('#info_tab>.tab').hide();
			$('#info_tab>.tab:eq('+i+')').show();
		});
	}
	
	
	this.init = function(options){
		var _this = this;
		$("#info_tab").append(_this.el);
		_this.bindEvent();
		 if (options && options.vehicleInfoId) {
	        var url = "/api/driver/queryDriverAndVehicleDetail?vehicleInfoId=" + options.vehicleInfoId;
	        var _this = this;
	        $.get(global.server + url, function (msg) {
	            if (msg && msg.status && msg.status.statusCode == global.status.success) {
	            	var data = msg.data;
	            	_this.fillData(data);
	            }
	        });
		 } 
	}
	
	this.fillData = function(data){
		var _this = this;
//		var allowSave = data.canEdit;
		var allowSave = true;
		
		_this.el.find('.text-column').each(function(i, j){
			var self = $(j);
			var _p = self.data('column');
			self.text(data[_p]);
		});
		
		_this.el.find('.pic_w').each(function(i, j){
			var _p = $(this).data('column');
			var imageId = data[_p];
			if(imageId){
				$(this).find('.picture').attr('src',global.imgsrc(imageId,{}));
			}
		});
		var driverList = data.driverList;
    	$.each(driverList, function(i, driver){
    		_this.addDriver(driver);
    	});
    	
    	var status = data.status;
    	if(status == '00'){//审核通过
    		
    	} else if(status == '02'){ //审核不通过
    		
    	} else if(status == '01'){//审核中
    		allowSave = false;
    	}
    	
    	$('.edit_btn').click(function(){
    		if(!allowSave){
        		alert("审核中的资料不允许修改");
        	} else {
        		location.href = global.getContextPath() + '/edit_driver_info.htm?vehicleInfoId='+data.vehicleInfoId;
        	}
    		return false;
    	});
    	
	}
	
	this.init(options);
};