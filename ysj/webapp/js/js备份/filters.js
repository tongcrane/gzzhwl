(function(window,angular,undefined){
	'use strict';
	var filters = angular.module("main_filters",[])
	.filter("filter",filterCity)
	.filter("addsource",addsource)
	.filter("timeFilter",timeFilter)
	.filter("limitNum",limitNum)
	.filter("getCompArr",getCompArr)//获取详细地址
	.filter("ageTime",ageTime)
	.filter("getIdNo",getIdNo)
	.filter("getTel",getTel)

	function getTel(){
		return function(input){
			if(input){
				return input.substring(0,3)+"****"+input.substring(input.length,input.length-4)
			}
		}
	}

	function getIdNo(){
		return function(input){
			if(input){
				return input.substring(input.length,input.length-4)
			}
		}
	}

	function ageTime(){
		return function(input,hasBegin){
	        var pattern = /^[1-9]\d*|0$/; //匹配非负整数
	        var timeObj = "";
	        if (pattern.test(input)) {
	        	if(input < 60){
	        		timeObj = '1分钟后';
	        	} else if(input < 60*60){
	        		timeObj = parseInt((input/60)) + '分钟后';
	        	} else if(input < 24*60*60){
	        		timeObj = parseInt((input/60/60)) + '小时后';
	        	} else {
	        		timeObj = parseInt((input/24/60/60)) + '天后';
	        	}
	        }
	        if(hasBegin == 1){
	        	return timeObj+"结束";
	        }else{
	        	return timeObj+"开始";
	        }
	        
		}
	}

	function getCompArr(){
		return function(input){
			var add ="";
			if(input){
				for(var i=input.length-1;i>=0;i--){
					add += input[i].regionName + "  ";
				}
			}
			return add;
		}
	}

	function filterCity(){
		return  function(input){
			if(input){
				if(input.indexOf("省")>0){
					return input.replace("省","");
				}else if(input.indexOf("市")>0){
					return input.replace("市","");
				}else if(input.indexOf("壮族自治区")>0){
					return input.replace("壮族自治区","");
				}else if(input.indexOf("回族自治区")>0){
					return input.replace("回族自治区","");
				}else if(input.indexOf("维吾尔自治区")>0){
					return input.replace("维吾尔自治区","");
				}else if(input.indexOf("自治区")>0){
					return input.replace("自治区","");
				}
			}
		}
	}

	function addsource(){
		return function(add){
			if(add.length>0){
				var addr_str="",senderAddr=[];
				add = $.grep(add, function(n) {return $.trim(n).length > 0;})
				senderAddr = add.reverse();
				for(var i=0;i< senderAddr.length;i++){
					addr_str += senderAddr[i].regionName;
				}
			}
			return addr_str;
		}
	}

	function timeFilter(){
		return function(time,param){
			time = global.fromNow(time)
			if(param==0){
				time = time+"开始"
			}else{
				time = time+"结束"
			}
			return time;
		}
	}

	function limitNum(){
		return function(items,param){
			var list=[];
			if(items){
				for(var i=0;i<items.length;i++){
					if(i<param){
						list.push(items[i])
					}
				}
			}
			return list;
		}
	}
})(window,window.angular)

