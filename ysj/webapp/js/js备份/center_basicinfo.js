var UserCenter = function(options){
	this.opt = $.extend({}, options);
	this.readonly = this.opt.readonly;
	this.imageList = [];
	var _this = this;
	
	this.init = function(options){
		var _this = this;
		$.get(global.server+'/api/agent/query',function(msg){
			if (msg && msg.status && msg.status.statusCode == global.status.success) {
                var data = msg.data;
                data.checkStatus = data.status;
                var user = global.getUser();
                var fullData = $.extend({},user,data);
                if(data){
                    var statusCn = null;
                    switch(data.status){
    	                case "01":
    	                	statusCn='待审核';
    	                	break;
    	                case "02":
    	                	statusCn='未通过';
    	                	break;
    	                case "00":
    	                	statusCn='通过';
    	                	break;
                    }
                    fullData.statusCn = statusCn;
                }
                _this.data = fullData;
                _this.fillData(fullData);
            }
		}).done(function(msg){

			$('.to_edit').click(function(){
				var valid = _this.validToUpdate();
				if(!valid){
					alert("当前资料不能修改。");
				} else {
					location.href = global.getContextPath() + '/edit_material.htm';
				}
			});
			
			
		});
		
	};
	
	this.uploadHead = function(data){
		global.changeHead(data.imageId);
	}
	
	this.fillData = function(data){
		var _this = this;
		
		if (typeof CBSImage === "function") {
			$('.user_head').each(function(i, j){
				var self = $(j);
				var column = self.data('column');
				var image = new CBSImage({
            		view: self,
            		category: column,
            		imageId:data.userHead,
            		onSelect:function(){
            			var url = '/api/account/userhead';
            			var result = image.postToServer(url);
            			_this.uploadHead(result);
            		}
                });
			});
		}
		
		if(_this.opt.readonly){
			$('.text-column').each(function(i, j){
				var self = $(j);
				var column = self.data('column');
				self.text(data[column]);
			});
			
			$('.pic-column').each(function(i, j){
				var self = $(j);
				var column = self.data('column');
				var imageId = data[column];
				if(imageId){
					self.find('img').attr('src',global.imgsrc(imageId,{}));
				} else {
					self.find('img').attr('src','img/photo.png');
				}
			});
			
		} else {
			$('.text-column').each(function(i, j){
				var self = $(j);
				var column = self.data('column');
				self.text(data[column]);
			});
			$('.text-column').each(function(i, j){
				var self = $(j);
				var column = self.data('column');
				self.val(data[column]);
			});
			if (typeof YSJImage === "function") {
				$('.pic-column').each(function(i, j){
					var self = $(j);
					var column = self.data('column');
					var imageId = data[column];
					var image = new YSJImage({
                		view: self,
                		delBtn : self.find('.close'),
                		category: column,
                   		exClass :self.data('category'),
                		imageId:imageId
                    });
                	_this.imageList.push(image);
				});
			}
		}
	}
	
	this.getFormData = function(){
		var data = {};
		$('.text-column').each(function(i, j){
			var self = $(j);
			var column = self.data('column');
			data[column] = self.val();
		});
		$.each(_this.imageList, function(i, j){
			var result = j.postToServer('/api/agent/uploadAgentImage');
			var column = result.column;
			var imageId = result.imageId;
			if(imageId != null){
				data[column] = imageId;
			} else {
				data[column] = "";
			}
		});
		return data;
	}
	
	this.validToPost = function(data){
		var _this = this;
		var realName = data.realName;
		var idno = data.idno;
		if(realName==''){
			alert("请输入姓名");
			return false;
		}
		if(global.isChinese(realName)){
			if(realName.length>5){
				alert("请输入正确的姓名");
				return false;
			}
		} else {
			alert("请输入正确的姓名");
			return false;
		}
		if(idno == ''){
			alert("请输入正确的身份证号");
			return false;
		}
		if(!IdCardNoUtil.check18IdCardNo(idno)){
			alert("请输入正确的身份证号");
			return false;
		}
		if(data.idFImageRefId==''){
			alert("请上传身份证正面照");
			return false;
		}
		if(data.idBImageRefId==''){
			alert("请上传身份证背面照");
			return false;
		}
//		if(!data.busImageRefId){
//			return false;
//		}
		return true;
	}
	
	this.validToUpdate = function(){
		var _this = this;
		var status = _this.data.checkStatus;
		if(status=='01' || status == '00'){
			return false;
		} else {
			return true;
		}
	}
	
	this.updateInfo = function(){
		var _this = this;
		if(!_this.opt.readonly){
			var valid = _this.validToUpdate();
			if(!valid){
				alert("当前资料不能修改。");
				return false;
			}
			var data = _this.getFormData();
			if(_this.validToPost(data)){
				$.post(global.server+'/api/agent/addOrUpdateAgent', data, function(msg){
					if (msg && msg.status && msg.status.statusCode == global.status.success) {
						alert("保存完成");
						location.href = global.getContextPath() + '/center_basicinfo.htm';
	                }
				});
			}
		} else {
			console.log("init...");
		}
	}
	
	this.init(options);
};
