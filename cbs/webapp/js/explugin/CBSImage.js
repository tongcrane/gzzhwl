var CBSImage = function(options) {
	
	this.hasUpload = true;
	this.category = null;
	this.imageId = null;
	
	var _this = this;
	
	this.template = {
		photo: function (data, init) {
			if(init){
				var el = $('<img class="picture" src="' + global.imgsrc(data.imageId,{}) + '" />');
				return el;
			} else {
				var el = $('<img class="picture" src="' + data.imgContent + '" />');
				return el;
			}
		}
	};
	
	this.fillData = function(data) {
		var _this = this;
		var isInit = data.init;
		if(isInit){
			this.hasUpload = true;
			this.imageId = data.imageId;
		} else {
			this.file = data.file;
			this.hasUpload = false;
			this.imageId = null;
		}
		this.el = this.template.photo(data, isInit);
		
		return this;
	};
	
	
	this.postToServer = function(url) {
		var _this = this;
		if(_this.hasUpload){
			if(_this.imageId){
				return {"column":_this.category,"imageId":_this.imageId};
			} else {
				return null;
			}
		} else {
			if (this.file) {
				if (window.FormData) {
					var result = {};
					var success = false;
					var formdata = new FormData();
					formdata.append("image", this.file);
					$.ajax({
						url: global.server + url,
						type: 'POST',
						data: formdata,
						cache: false,
						async: false,
						contentType: false,
						processData: false,
						success: function(msg) {
				            if (msg && msg.status && msg.status.statusCode == global.status.success) {
				            	var imageId = msg.data.imageId;
				            	_this.imageId = imageId;
				            	_this.hasUpload = true;
				            	result.column = _this.category;
				            	result.imageId = imageId;
				            	success = true;
				            } else {
				            	console.log('upload occur error' + msg.status.errorMsg);
				            }
						}
					});
					if(success){
						return result;
					} else {
						return null;
					}
				} else {
					console.log('not support formdata');
					return null;
				}
			} else {
				return null;
			}
		}
	};
	
	this.dropPhoto = function(data) {
		var _this = this;
		var target = _this.opt.view;
		target.siblings('.picture').remove();
		_this.fillData(data);
		target.after(this.el);
	};

	this.init = function(options) {
		this.opt = $.extend({}, options);
		var _this = this;
		var target = _this.opt.view;
		
		this.category = _this.opt.category;
		
		target.after($('<input type="file" class="hide"/>'));
		
		var readMultipleFiles = function(evt) {
			var files = evt.target.files;
			if (files) {
				for (var i = 0; i < files.length; i++) {
					if (files[i].type != 'image/jpeg' && files[i].type != 'image/gif' && files[i].type != 'image/png') {
						alert("文件类型错误，请上传JPG、GIF、PNG格式的文件！");
						continue;
					}
					if (files[i].size > 2097152) {
						alert("文件过大，请上传2M以内的文件！");
						continue;
					}
					console.log('FileUpload:' +files[i].name+' will be uploaded ...');
					(function(i) {
						var reader = new FileReader();
						reader.onload = function(event) {
							var imageObj = {
								"init": false,
								"imgContent": event.target.result,
								"file": files[i]
							};
							_this.dropPhoto(imageObj);
						};
						reader.readAsDataURL(files[i]);
					})(i);
				}
				evt.target.outerHTML = evt.target.outerHTML;
			} else {
				console.log("Failed to load files"); 
			}
		}
//		
		target.on('click', function() {
			var trigger = target.siblings('input:file')[0];
			trigger.addEventListener('change', readMultipleFiles, false);
			trigger.click();
		});
		
		if(_this.opt.imageId){
			var imageObj = {
				"init": true,
				"imageId": _this.opt.imageId
			};
			_this.dropPhoto(imageObj);
		}
	};
	
	this.init(options);
};