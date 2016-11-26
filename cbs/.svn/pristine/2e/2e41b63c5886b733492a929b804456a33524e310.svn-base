/**
 * 
 */
var ViewMap = function(options){
	this.opt = $.extend({}, options);
	var _this = this;
	this.complete = false;
	this.data = null;
	
	this.icon = {
		"sender":{
			size : new CBSMap.Size(28,40),
			image : "img/map_start.png"
		},
		"receiver":{
			size : new CBSMap.Size(28,40),
			image : "img/map_end.png"
		},
		"current": {
			size : new CBSMap.Size(28,28),
			image : "img/map_normal.png"
		},
		"normal": {
			size : new CBSMap.Size(28,28),
			image : "img/map_normal.png"
		},
		"exception": {
			size : new CBSMap.Size(28,28),
			image : "img/map_exception.png"
		}
	}
	
	
	this.init = function(options){
		var _this = this;
		$('#search_type').multiselect({
			header : false,
			multiple : false,
			selectedList : 1,
			noneSelectedText: '请选择',
			minWidth : 104,
			click: function(event, ui){
				$('#search_input').prop('placeholder',ui.placeholder);
		    }
		});
		
		_this.amap = new CBSMap({
			target : "map",
			onMarkerShow : _this.onMarkerShow,
			onComplete : _this.onComplete
		});
	}
	
	this.onComplete = function(){
		var self = _this;
		self.complete = true;
		var type = self.opt.type;
		var billNo = self.opt.billNo;
		if(type && billNo){
			$('#search_input').val(billNo);
			$('#search_type').multiselect('setValue', type);
			self.search()
		}
	}
	
	//地图上点的click时间
	this.onMarkerShow = function(param){
		var self = _this;
		var data = param.data;
		var track = data.track;
		if(track == '01'){//是轨迹点
			if(data.isException == '01'){
				return self.getExceptionTemplate(data);
			} else {
				return self.getNormalTemplate(data);
			}
		} else if(track == '02'){//是起止点
			console.log(data);
			return null;
		} else if(track == '03'){//最新点
			return self.getNormalTemplate(data);
		}
	}
	
	//普通点
	this.getNormalTemplate = function(data){
		var speed = data.speed;
		if(speed==null){
			speed = 0;
		}
		var el = $('<div class="map_tips"> \
			<div class="tip_con"> \
				<span>订单号</span> \
				<p>'+data.orderNo+'</p> \
			</div> \
			<div class="tip_con"> \
				<span>车牌号</span> \
				<p>'+data.plateNumber+'</p> \
			</div> \
			<div class="tip_con"> \
				<span>获取时间</span> \
				<p>'+data.feedbackTime+'</p> \
			</div> \
			<div class="tip_con"> \
				<span>位置</span> \
				<p>'+data.longitude+','+data.latitude+'</p> \
			</div> \
			<div class="tip_con"> \
				<span>行驶速度</span> \
				<p><b>'+speed+'</b><i class="speed">&nbsp;km/h</i></p> \
			</div> \
		</div>');
		return el.prop('outerHTML');
	}
	
	//异常点
	this.getExceptionTemplate = function(data){
		var speed = data.speed;
		if(speed==null){
			speed = 0;
		}
		var el = $('<div class="map_tips"> \
			<div class="tip_con"> \
				<span>订单号</span> \
				<p>'+data.orderNo+'</p> \
			</div> \
			<div class="tip_con"> \
				<span>车牌号</span> \
				<p>'+global.defaultIfBlack(data.plateNumber,'')+'</p> \
			</div> \
			<div class="tip_con"> \
				<span>异常类型</span> \
				<p>'+global.defaultIfBlack(data.itemName,'')+'</p> \
			</div> \
			<div class="tip_con"> \
				<span>异常开始时间</span> \
				<p>'+global.defaultIfBlack(data.feedbackTime,'')+'</p> \
			</div> \
			<div class="tip_con"> \
				<span>异常结束时间</span> \
				<p>'+global.defaultIfBlack(data.endTime,'')+'</p> \
			</div> \
			<div class="tip_con"> \
				<span>位置</span> \
				<p>'+data.longitude+','+data.latitude+'</p> \
			</div> \
			<div class="tip_con"> \
				<span>行驶速度</span> \
				<p><b>'+global.defaultIfBlack(speed,'')+'</b><i class="speed">&nbsp;km/h</i></p> \
			</div> \
		</div>');
		return el.prop('outerHTML');
	}
	
	this.search = function(){
		var _this = this;
		if(_this.complete){
			var billNo = $('#search_input').val();
			if(billNo == ''){
				alert("请输入查询信息");
				return;
			}
			var param = {};
			param.type = $('#search_type').val();
			param.billNo = billNo;
			_this.getData(param);
		} else {
			alert("请等待地图初始化之后查询");
		}
	}
	
	this.getData = function(param){
		var _this = this;

		_this.amap.clearMarker();
		
		$.get(global.server+'/admin/maptrans/list', param, function(msg){
			if (msg && msg.status && msg.status.statusCode == global.status.success) {
				var data = msg.data;
				_this.data = data;
				_this.showMap(data);
			} else {
				_this.data = null;
				alert(msg.status.errorMsg);
			}
		});
	}
	
	this.showMap = function(data){
		var _this = this;
		var exdata = _this.getExData(data);
		
		var orderInfo = data.info.orderInfo;
		
		var sender = _this.getFullAddress(orderInfo.sendRegion, orderInfo.sendAddress);
		var receiver = _this.getFullAddress(orderInfo.receiveRegion, orderInfo.receiveAddress);
		
		var senderEx = $.extend({type : 'sender'}, exdata);
		var receiverEx = $.extend({type : 'receiver'}, exdata);
		
		_this.resolveAddr(sender, senderEx);
		
		
		var lng = orderInfo.receiveLng;
		var lat = orderInfo.receiveLat;
		if(lng && lat){
			var amap = _this.amap;
			amap.addMarker({
				param:{
					data : $.extend({track : '02'}, receiverEx)
				},
				icon : _this.icon[receiverEx.type],
				position: new AMap.LngLat(lng, lat)
			});
		} else {
			_this.resolveAddr(receiver, receiverEx)
		}
		
//		console.log(sender);
//		console.log(receiver);
		
		
		var track = data.track;
		_this.showTrack(track, exdata);
		
		var amap = _this.amap;
		amap.fitView();
	}
	
	this.resolveAddr = function(addr, exdata){
		var _this = this;
		_this.amap.geoCoder(addr, _this.addPoint, exdata);
	}
	
	this.addPoint = function(point, param){
		var self = _this;
		var amap = self.amap;
		$.each(point, function(i, j){
			amap.addMarker({
				param:{
					data : $.extend({track : '02'}, param)
				},
				icon : self.icon[param.type],
				position: j.location
			});
		});
		
		amap.fitView();
	}
	
	this.getExData = function(data){
		var orderInfo = data.info.orderInfo;
		var loadInfo = data.info.loadInfo;
		var orderNo = orderInfo.orderNo;//订单号
		var plateNumber = loadInfo.plateNumber;//车牌号
		
		var needStartTime = orderInfo.needStartTime;//预计发车时间
		var needArriveTime = orderInfo.needArriveTime;//预计到达时间
		var pickUpTime = orderInfo.pickUpTime;//提货时间
		
		var exdata = {
			orderNo : orderNo,
			plateNumber : plateNumber,
			needStartTime : needStartTime,
			needArriveTime : needArriveTime,
			pickUpTime : pickUpTime
		}
		return exdata;
	}
	
	this.getFullAddress = function(region,addr){
		var _region = [];
		$.each(region,function(i, j){
			_region.push(j.regionName);
		});
		var full = _region.reverse();
		var fullAddr = full.join('');
		return fullAddr + addr;
	}
	
	this.showTrack = function(track, exdata){
		var _this = this;
		var amap = _this.amap;
		$.each(track, function(i, j){
			if(j.longitude && j.latitude){
				if(j.isException == '01'){
					amap.addMarker({
						param:{
							data:$.extend({track : '01'},exdata, j)
						},
						icon : _this.icon.exception,
						position: new AMap.LngLat(j.longitude,j.latitude)
					});
				} else {
					amap.addMarker({
						param:{
							data:$.extend({track : '01'},exdata, j)
						},
						icon : _this.icon.normal,
						position: new AMap.LngLat(j.longitude,j.latitude)
					});
				}
				
			}
		});
	}
	
	this.getCurrent = function(){
		var _this = this;
		var data = _this.data;
		if(data != null){
			var amap = _this.amap;
			var exdata = _this.getExData(data);
			var loadId = data.info.loadInfo.loadId;
			_this.removeCurrent();
			$.get(global.server + '/admin/maptrans/getgeo', {loadId:loadId}, function(msg){
				if (msg && msg.status && msg.status.statusCode == global.status.success) {
					var position = msg.data;
					amap.addMarker({
						param:{
							data:$.extend({track : '03'}, exdata, position)
						},
						icon : _this.icon.current,
						position: new AMap.LngLat(position.longitude,position.latitude)
					});
				} else {
					alert("获取数据超时，可能设备未在线，请重新获取。");
				}
			});
		} else {
			
		}
	}
	
	this.removeCurrent = function(){
		var _this = this;
		var amap = _this.amap;
		var markers = amap.getAllMarker();
		var mid = null;
		$.each(markers, function(i, j){
			var data = j.data;
			if(data.track == '03'){
				mid = j.mid;
				return false;
			}
		});
		amap.removeMarker(mid);
	}
	
	this.init(options);
}

$(function(){
	var type = global.QueryString.type;
	var billNo = global.QueryString.billNo;
	var param = {
		type : type,
		billNo : billNo
	}
	var view = new ViewMap(param);
	
	$('.search_btn').click(function(){
		view.search();
	});
	
	
	$('.get_current').click(function(){
		view.getCurrent();
	});
})
