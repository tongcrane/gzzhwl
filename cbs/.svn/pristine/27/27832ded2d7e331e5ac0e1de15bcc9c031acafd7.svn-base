/**
 * 
 */
var CBSImageInfo = function(options){
	
	var openImg = function(imageId){
    	var url = global.imgsrc(imageId);
    	var body = document.getElementsByTagName("body")[0];
    	var el = document.createElement("a");
    	body.appendChild(el);
    	el.href = url;
    	el.target = '_blank';
    	el.click();
    	body.removeChild(el);
    } 
	
	this.showPic = function(imageId){
//		var el = $('<div id="show-pic"> \
//			<div class="content"> \
//				<div class="content-img"></div> \
//				<span></span> \
//			</div> \
//		</div>');
//		
//		el.find('.content-img').css({'background-image':'url('+ global.imgsrc(imageId) +')'});
//		el.find('.content > span').click(function(event){
//			el.remove();
//		});
//		el.find('.content').click(function(event){
//			event.stopPropagation();
//			return false;
//		});
//		el.click(function(event){
//			el.remove();
//		});
//		$('body').append(el);
//		el.show();
		openImg(imageId);
	}
	
	this.init = function(options){
		this.opt = $.extend({}, options);
		var _this = this;
		var imageId = _this.opt.imageId;
		if(imageId){
			_this.opt.view.css({'background-image':'url('+ global.imgsrc(imageId) +')'});
			
			_this.opt.view.click(function(){
				_this.showPic(imageId)
			});
			
		}
	}
	
	this.init(options);
}
