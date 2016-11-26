var CBSMap = function(options){
	"use strict";
	var defaultOpt = {
		target : null,
		onClick : $.noop,
		onComplete : $.noop,
		onMarkerShow : function(param){
			return null;
		}
	};
	
	this.plugin = false;
	
	this.opt = $.extend(defaultOpt, options);
	
	this.marker = {};
	
	this.init = function(options){
		var _this = this;
		var map = _this.map = new AMap.Map(_this.opt.target, {
	        resizeEnable: true,
	        dragEnable: true,
	        keyboardEnable: false,
	        doubleClickZoom: false,
	    });
		
		_this.infoWin = new AMap.InfoWindow({
			isCustom : false,
			autoMove : true,
			closeWhenClickMap : false,
			offset: new AMap.Pixel(7, -14),
			showShadow : false
		});
		
		map.on('complete', function() {
	        _this.onComplete();
	    });
		
		map.plugin(["AMap.ToolBar","AMap.Geocoder"], function() {
			_this.pluginComplete();
			_this.map.addControl(new AMap.ToolBar({
//				offset : new AMap.Pixel(50,50),//相对于地图容器左上角的偏移量，正数代表向右下偏移
				ruler : false,//标尺键盘是否可见
				locate : false,//是否显示定位按钮
				liteStyle: true,//是否使用精简模式
				direction:false//方向键盘是否可见
			}));
        });
		
		map.on('click', function(e) {
			_this.onClick.call(_this, e);
	    });
		
	};
	
	this.pluginComplete = function(){
		var _this = this;
		_this.plugin = true;
	}
	
	this.onComplete = function(){
		var _this = this;
		_this.opt.onComplete();
	}
	
	//正向地理编码（地址-坐标）
	this.geoCoder = function(address, callback, param){
		var _this = this;
		var pluginComplete = _this.plugin;
		if(!pluginComplete){
			setTimeout(function() { _this.geoCoder(); }, 1000);
		} else {
			var geocoder = new AMap.Geocoder({
//	            city: "010", //城市，默认：“全国”
	            radius: 1000 //范围，默认：500
	        });
			geocoder.getLocation(address, function(status, result) {
				if (status === 'complete' && result.info === 'OK') {
					callback(result.geocodes, param);
				}
			});
		}
	}
	////逆地理编码（坐标-地址）
	this.geoDecoder = function(lnglat, callback, param){
		var _this = this;
		var pluginComplete = _this.plugin;
		if(!pluginComplete){
			setTimeout(function() { _this.geoDecoder(); }, 1000);
		} else {
			var geocoder = new AMap.Geocoder({
//	            city: "010", //城市，默认：“全国”
	            radius: 1000, //范围，默认：500
	            extensions: "all"
	        });
			geocoder.getAddress(lnglat, function(status, result) {
	            if (status === 'complete' && result.info === 'OK') {
	                callback(lnglat, result.regeocode, param);
	            }
	        });
		}
	}
	
	this.getMap = function(){
		var _this = this;
		return _this.map;
	}
	
	//鼠标点击事件
	this.onClick = function(e){
		var _this = this;
		_this.closeInfo();
		_this.opt.onClick(e);
	}
	
	this.init(options);
}

CBSMap.Size = function(width, height){
	this.getWidth = function(){
		return width;
	}
	this.getHeight = function(){
		return height;
	}
	this.getAmapSize = function(){
		return new AMap.Size(width, height)
	}
}

CBSMap.prototype.addMarker = function(options){
	var _this = this;
	var mid = global.uuid();
	var opt = $.extend({}, options);
	var param = $.extend({"mid" : mid}, opt.param);
	var icon = opt.icon;
	var marker = new AMap.Marker({
        map : _this.map,
        extData : param,
		position : opt.position,
        icon: new AMap.Icon({            
            size: icon.size.getAmapSize(),  //图标大小
            image: icon.image,
            imageSize : icon.size.getAmapSize()
        })
    });
	
	var label = opt.label;
	if(label){
		marker.setLabel({
	        offset: new AMap.Pixel(-10, -20),
	        content: label
	    });
	}
	
	_this.marker[mid] = marker;
	marker.on('click', function(e) {
//		console.log(e.target.getIcon().getImageSize());
		var extData = marker.getExtData();
		var position = e.target.getPosition();
		_this.showInfo(position, extData);
    });
	return marker;
}

CBSMap.prototype.getAllMarker = function(){
	var _this = this;
	var data = _this.map.getAllOverlays('marker');
	return $.map(data, function(value, key){
		return value.getExtData();
	});
}


CBSMap.prototype.clearMarker = function(){
	var _this = this;
	_this.map.clearMap();
}

CBSMap.prototype.fitView = function(){
	var _this = this;
	_this.map.setFitView();
}



CBSMap.prototype.removeMarker = function(mid){
	if(!mid)
		return null;
	var _this = this;
	var allMarker = _this.marker;
	var marker = allMarker[mid];
	if(marker){
		marker.setMap(null);
		delete allMarker[mid];
	}
}

CBSMap.prototype.showInfo = function(position, extData){
	var _this = this;
	var content = _this.opt.onMarkerShow(extData);
	if(content != null){
		_this.infoWin.setContent(content);
		_this.infoWin.open(_this.map, position);
	} else {
		_this.closeInfo();
	}
}

CBSMap.prototype.closeInfo = function(){
	var _this = this;
	var isOpen = _this.infoWin.getIsOpen();
	if(isOpen){
		_this.infoWin.close();
	}
}