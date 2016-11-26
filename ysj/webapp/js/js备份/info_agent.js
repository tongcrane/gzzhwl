var InfoAgent = function(){
	this.imageList = [];
	
	this.init = function(){
		var _this = this;
		var data = global.getUser();
		
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
            		category: column,
            		delBtn : self.find('.close'),
               		exClass :$(this).data('category'),
            		imageId:imageId
                });
            	_this.imageList.push(image);
			});
		}
	}
	
	this.getFormData = function(){
		var _this = this;
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
		var realName = data.realName;
		var idno = data.idno;
		if(realName==''){
			alert('请填写姓名');
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
			alert('请填写正确的身份证号');
			return false;
		}
		if(!IdCardNoUtil.check18IdCardNo(idno)){
			alert('请填写正确的身份证号');
			return false;
		}
		if(data.idFImageRefId == ''){
			alert('请上传身份证正面照片');
			return false;
		}
		if(data.idBImageRefId == ''){
			alert('请上传身份证背面照片');
			return false;
		}
		return true;
	}
	
	this.updateInfo = function(){
		var _this = this;
		var data = _this.getFormData();
		if(_this.validToPost(data)){
			$.post(global.server+'/api/agent/addOrUpdateAgent', data, function(msg){
				if (msg && msg.status && msg.status.statusCode == global.status.success) {
//					alert("保存完成");
					new YSJRegDialog();
                } else {
                	alert(msg.status.errorMsg);
                }
			});
		}
	}
	
	this.init();
}

var YSJRegDialog = function(){
	
	this.init = function(){
		var _this = this;
		_this.el = $('<div class="dialog_wrap" style="display:none;"> \
    		<div class="dialog_done dialog_w"> \
		  		<p class="star"> \
		  			<img src="'+ global.getContextPath() +'/img/star.png"> \
		  		</p> \
		  		<p class="txt"> \
		  			<span class="txt_d">恭喜你！账号信息保存成功</span></br> \
		  		</p> \
		  		<p class="txt txt_we"> \
		  			<span class="txt_w">运势界，欢迎您的加入！</span>  \
		  		</p> \
		  		<p class="txt txt_p"> \
		  			只有完善司机车辆的信息，并审核通过后，才能进行竞标以</br>及后续的业务处理  \
		  		</p> \
		  		<p class="txt txt_dr"> \
		  			<a href="'+ global.getContextPath() + '/edit_driver_info.htm">继续完善司机车辆信息</a> \
		  		</p> \
		  		<p class="txt_a"> \
		  			<a href="' + global.getContextPath() + '/index.htm">首页&nbsp;&nbsp;></a> \
		  		</p> \
		  		<span class="close"></span> \
			</div> \
			<div class="mask"></div> \
    	</div>');
		
		$('body').append(_this.el);
		
		_this.show();
	};
	
	this.show = function(){
		var _this = this;
		_this.el.show();
	};
	
	this.hide = function(){
		var _this = this;
		_this.el.remove();
	};
	
	this.init();
}

$(function(){
	
	var info = new InfoAgent();
	
	$('.cars_btn').click(function(){
		info.updateInfo();
	});
})

