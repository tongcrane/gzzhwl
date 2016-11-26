var CBSOrderMap = function(options){
	this.opt = $.extend({address:"",geo:{
		lng:"",
		lat:""
	},onClose:$.noop}, options);
	
	this.amap = null;
	this.complete = false;
	
	this.result = null;
	
	this.icon = {
		"normal": {
			size : new CBSMap.Size(28,28),
			image : "img/map_normal.png"
		}
	}
	
	var _this = this;
	
	this.el = $('<div class="mo_del" style="display: block;"> \
				<div class="model_content_map"> \
					<div id="map_container"></div> \
					<i class="clo_se"></i> \
				</div> \
			</div>');
	
	this.destory = function(){
		var _this = this;
		_this.el.remove();
		_this.opt.onClose(_this.result);
	}
	
	this.init = function(options){
		var _this = this;
		var address = _this.opt.address;
		var geo = _this.opt.geo;
		
		$('body').append(_this.el);
		var target = _this.el.find('#map_container');
		
		_this.amap = new CBSMap({
			target : target.get(0),
			onClick : _this.onClick,
			onMarkerShow : _this.onMarkerShow,
			onComplete : _this.onComplete
		});
		
		_this.el.find('.clo_se').click(function(){
			_this.destory();
		});
		
		var bygeo = false;
		
		if(geo!=null){
			if(geo.lng && geo.lat){
				_this.initByGeo(geo);
				bygeo = true;
			}
		}
		
		if(!bygeo){
			if(address){
				_this.initByAddress(address);
			}
		}
	}
	
	this.initByAddress = function(address){
		var _this = this;
		_this.amap.geoCoder(address, _this.initPoint, {});
	}
	
	this.initPoint = function(point, param){
		var self = _this;
		$.each(point, function(i, j){
			self.addPoint(j.location ,j,{});
		});
	}
	
	this.initByGeo = function(geo){
		var _this = this;
		_this.amap.geoDecoder(new AMap.LngLat(geo.lng,geo.lat), _this.addPoint, {});
	}
	
	//地图上点的click时间
	this.onMarkerShow = function(param){
		var self = _this;
		var data = param.data;
		return self.getTemplate(data);
	}
	
	this.getTemplate = function(data){
		var el = $('<div class="map_tips">'+data.address+'</div>');
		return el.prop('outerHTML');
	}
	
	this.onComplete = function(){
		var self = _this;
		self.complete = true;
	}
	
	this.onClick = function(e){
		var self = _this;
		if(self.complete){
			var lnglat = e.lnglat;
			_this.amap.geoDecoder(lnglat, _this.addPoint, {});
		}
	}
	
	this.addPoint = function(lnglat, address, param){
		var self = _this;
		var amap = self.amap;
		amap.clearMarker();
		var marker = amap.addMarker({
			param:{
				data : {"address":address.formattedAddress}
			},
			icon : self.icon['normal'],
			position: lnglat
		});
		var contextMenu = new AMap.ContextMenu();  //创建右键菜单
		contextMenu.addItem("删除标记", function(e) {//右键添加Marker标记
			amap.clearMarker();
			_this.result = null;
		}, 0);
		marker.on('rightclick', function(e) {//地图绑定鼠标右击事件——弹出右键菜单
		    contextMenu.open(amap.getMap(), e.lnglat);
		    contextMenuPositon = e.lnglat;
		});
		var extData = marker.getExtData();
		var position = lnglat;
		amap.showInfo(position, extData);
		
		var result = {};
		result.address = address.formattedAddress;
		result.lnglat = {
			lng : lnglat.lng,
			lat : lnglat.lat
		}
		_this.result = result;
		amap.fitView();
	}
	
	this.init(options);
}