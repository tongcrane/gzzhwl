// 更新：
// 05.27: 1、保证回调执行顺序：error > ready > load；2、回调函数this指向img本身
// 04-02: 1、增加图片完全加载后的回调 2、提高性能

/**
 * 图片头数据加载就绪事件 - 更快获取图片尺寸
 * @version	2011.05.27
 * @see		http://blog.phpdr.net/js-get-image-size.html
 * @param	{String}	图片路径
 * @param	{Function}	尺寸就绪
 * @param	{Function}	加载完毕 (可选)
 * @param	{Function}	加载错误 (可选)
 * @example imgReady('http://www.google.com.hk/intl/zh-CN/images/logo_cn.png', function () {
		alert('size ready: width=' + this.width + '; height=' + this.height);
	});
 */
var resizeImg = function(el, callback){
	var maxwidth = el.attr("maxwidth");
	var maxheight = el.attr("maxheight");
	
	var opt = $.extend({
		maxwidth : 100,
		maxheight : 100
	}, {
		maxwidth : maxwidth,
		maxheight : maxheight
	});
	
	var url = el.attr("src");
	if(url != ""){
		maxwidth = maxwidth*1;
		maxheight = maxheight*1;
		
		imgReady(url, function () {
			var currentWidth = this.width;
			var currentHeight = this.height;
			
			var normal = maxheight/maxwidth;
			var current = currentHeight/currentWidth;
			
			var data = {};
			
			if(currentWidth < maxwidth && currentHeight < maxheight){
				data.width = currentWidth;
				data.height = currentHeight;
			} else {
				if(current > normal){
					data.height = maxheight;
					data.width = Math.round(currentWidth * maxheight / currentHeight);
					el.addClass("over-height");
				} else {
					data.width = maxwidth;
					data.height = Math.round(current*maxwidth);
					el.addClass("over-width");
				}
			}
			data.target = el;
			callback && callback.call(data);
		});
	}
};

var imgReady = (function () {
	var list = [], intervalId = null,

	// 用来执行队列
	tick = function () {
		var i = 0;
		for (; i < list.length; i++) {
			list[i].end ? list.splice(i--, 1) : list[i]();
		};
		!list.length && stop();
	},

	// 停止所有定时器队列
	stop = function () {
		clearInterval(intervalId);
		intervalId = null;
	};

	return function (url, ready, load, error) {
		var onready, width, height, newWidth, newHeight,
			img = new Image();

		img.src = url;

		// 如果图片被缓存，则直接返回缓存数据
		if (img.complete) {
			ready.call(img);
			load && load.call(img);
			return;
		};

		width = img.width;
		height = img.height;

		// 加载错误后的事件
		img.onerror = function () {
			error && error.call(img);
			onready.end = true;
			img = img.onload = img.onerror = null;
		};

		// 图片尺寸就绪
		onready = function () {
			newWidth = img.width;
			newHeight = img.height;
			if (newWidth !== width || newHeight !== height ||
				// 如果图片已经在其他地方加载可使用面积检测
				newWidth * newHeight > 1024
			) {
				ready.call(img);
				onready.end = true;
			};
		};
		onready();

		// 完全加载完毕的事件
		img.onload = function () {
			// onload在定时器时间差范围内可能比onready快
			// 这里进行检查并保证onready优先执行
			!onready.end && onready();

			load && load.call(img);

			// IE gif动画会循环执行onload，置空onload即可
			img = img.onload = img.onerror = null;
		};

		// 加入队列中定期执行
		if (!onready.end) {
			list.push(onready);
			// 无论何时只允许出现一个定时器，减少浏览器性能损耗
			if (intervalId === null) intervalId = setInterval(tick, 40);
		};
	};
})();