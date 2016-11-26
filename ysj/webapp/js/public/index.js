/*
* @Author: Administrator
* @Date:   2016-07-07 10:42:26
* @Last Modified by:   Administrator
* @Last Modified time: 2016-07-15 14:55:00
*/
'use strict';
$(function() {
	// banner
	var num=0;
	var timer;
	var nextFn=function(){
		$('.banner li').eq(num).stop().fadeOut(1000);
		num++;
		if(num>1){
			num=0;
		}
		$('.banner li').eq(num).stop().fadeIn(1000);
		$('.dianBg li').eq(num).addClass('current').siblings('li').removeClass('current');
	}
	var prevFn=function(){
		$('.banner li').eq(num).stop().fadeOut(1000);
		num--;
		if(num<0){
			num=1;
		}
		$('.banner li').eq(num).stop().fadeIn(1000);
		$('.dianBg li').eq(num).addClass('current').siblings('li').removeClass('current');
	}
	$('.rightBtn').click(nextFn);
	$('.leftBtn').click(prevFn);
	$('.dianBg li').click(function(event) {
		$('.banner li').eq(num).stop().fadeOut(1000);
		var i=$(this).index();
		num=i;
		$('.banner li').eq(num).stop().fadeIn(1000);
		$('.dianBg li').eq(num).addClass('current').siblings('li').removeClass('current');
	});
	timer=setInterval(nextFn, 3000);
	$('.bannerBg').hover(function() {
		clearInterval(timer);
	}, function() {
		clearInterval(timer);
		timer=setInterval(nextFn, 3000);
	});

	// 最新货源圆圈控制
	$("#supplyBg").on('mouseover', 'li .bid', function(event) {
		$(this).parents('li').siblings().removeClass('active');
		$(this).parents('li').addClass('active');
//		$(this).siblings().removeClass('active');
//		$(this).find(".circle").addClass('active').parents("li").siblings().find('.circle').removeClass('active');
	}).on('mouseout', 'li .bid', function(event) {
		$(this).parents('li').removeClass('active');
//		$(this).find(".circle").removeClass('active');
	});

	//获取首页数据 --(数据源ajaxAction.js)
	getAjax.getSourceAndQutoedCnt()();
	getAjax.getHotLineSourceList()({"sourceCount":"3"});
	getAjax.getNewSource()({"sourceCount":"4"});
	if(global.isLogin()){
		getAjax.getLineList()();
	}else{
		//未登陆时,首页按钮地址设置
		$("a").attr("href",global.getContextPath() + "/login.htm");
	}
});