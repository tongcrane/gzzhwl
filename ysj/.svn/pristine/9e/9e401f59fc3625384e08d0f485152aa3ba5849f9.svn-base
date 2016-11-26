(function(window,angular,undefined){
	'use strict';
	var services = angular.module('main_services',['ngResource','ngRoute'])
	.factory('login',login)
	.factory('isExit',isExit)
	.factory('getRecommendSource',getRecommendSource)
	.factory('getloadId',getloadId)
	.factory('getQOList',getQOList)
	.factory('getQuotedOrderList',getQuotedOrderList)
	.factory('loaBillQuery',loaBillQuery)
	.factory('getLoaBill',getLoaBill)
	.factory('trackInfoQuery',trackInfoQuery)
	.factory('getTrackInfo',getTrackInfo)
	.factory('geoQuery',geoQuery)
	.factory('getGeo',getGeo)
	.factory('getMessageList',getMessageList)
	.factory('getInfo',getInfo)
	.factory('doAddQuote',doAddQuote)
	.service('driverLen',getDriverLen)
	.service('getAgent',getAgent)
	.factory('getUserhead',getUserhead)
	.factory('getLineListQuery',getLineListQuery)
	.factory('getLineList',getLineList)
	.factory('selectLines',selectLines)
	.factory('getallLinesQuery',getallLinesQuery)
	.factory('getallLines',getallLines)
	.service('getPageIndex',getPageIndex)
	.service('lineId',getLineId)
	.factory('getCityQuery',getCityQuery)
	.factory('getCity',getCity)
	.factory('addLine',addLine)
	.factory('delLine',delLine)
	.factory('subRegionQuery',subRegionQuery)
	.factory('getSubReg',getSubReg)
	.factory('defSupCode',reSupCode)
	.factory('qOrderDetailQuery',qOrderDetailQuery)
	.factory('getQuotedDeatil',getQuotedDeatil)
	.service('driverLen',getDriverLen)
	.factory('getRecordList',getRecordList)
	.factory('closeQuotedInfo',closeQuotedInfo)
	.factory('invalidTheBid',invalidTheBid)
	.factory('doAddQuote',doAddQuote)
	.factory('orderlistQuery',orderlistQuery)
	.factory('getOrderList',getOrderList)
	.service('defOrderParam',getOrderParam)
	.factory('gRDList',gRDList)
	.factory('getBidVehicleInfo',getBidVehicleInfo)
	.factory('finishQuotedInfo',finishQuotedInfo)
	.factory('getLoadBillByQuotedId',getLoadBillByQuotedId)
	.factory('pageDAVList',pageDAVList)
	.factory('qDVDeatil',qDVDeatil)
	.factory('getVehicleInfo',getVehicleInfo)
	.factory('getDriverInfo',getDriverInfo)
	.factory('queryHall',queryHall)
	.factory('responseObserver', function responseObserver($q, $window,$timeout) {
		var count =0;
	    return {
	        'responseError': function(errorResponse) {
	            switch(errorResponse.statusText){
	            	case "Forbidden":
	            		if($.cookie("user") && count==0){
	            			$(".signout_dialog").show();
	            			global.logout();
	            			$timeout(function(){
	            				$window.location = global.getContextPath() + "/login_dialog.htm";
	            			},2000)
	            		}else if(!$.cookie("user") && count==0){
	               	 		$window.location =global.getContextPath() + "/login_dialog.htm";
	            		}
	            		count++;
	            	break;
	            }
	            return $q.reject(errorResponse);
	        }
	    }
	})

	function queryHall($resource){
		return $resource(global_config.server + "/api/source/queryHall",{},{
	        get:{
	            method:"GET"
	        }
	    })
	}
	queryHall.$inject=['$resource']

	function getDriverInfo($resource){
		return $resource(global_config.server + "/api/driver/getDriverInfo",{},{
	        get:{
	            method:"GET"
	        }
	    })
	}
	getDriverInfo.$inject=['$resource']

	function getVehicleInfo($resource){
		return $resource(global_config.server + "/api/vehicle/getVehicleInfo",{},{
	        get:{
	            method:"GET"
	        }
	    })
	}
	getVehicleInfo.$inject=['$resource']

	function qDVDeatil($resource){
		return $resource(global_config.server + "/api/driver/queryDriverandVehicleDetail",{},{
	        post:{
	            method:"POST"
	        }
	    })
	}
	qDVDeatil.$inject=['$resource']

	function pageDAVList($resource){
		return $resource(global_config.server + "/api/driver/pageDriverAndVehicleList",{},{
	        post:{
	            method:"POST"
	        }
	    })
	}
	pageDAVList.$inject=['$resource']

	function isExit($resource){
		return $resource(global_config.server + "/api/account/isExist",{},{
	        get:{
	            method:"GET"
	        }
	    })
	}
	isExit.$inject=['$resource']

	function login($resource){
		return $resource(global_config.server + "/api/account/login",{},{
	        post:{
	            method:"POST"
	        }
	    })
	}
	login.$inject=['$resource']

	function getLoadBillByQuotedId($resource){
		return $resource(global_config.server + "/api/quoted/getLoadBillByQuotedId",{},{
	        get:{
	            method:"GET"
	        }
	    })
	}
	getLoadBillByQuotedId.$inject=['$resource']

	function finishQuotedInfo($resource){
		return $resource(global_config.server + "/api/quoted/finishQuotedInfo",{},{
	        post:{
	            method:"POST"
	        }
	    })
	}
	finishQuotedInfo.$inject=['$resource']

	function gRDList($resource){
		return $resource(global_config.server + "/api/driver/pageUsableDriverAndVehicleList",{},{
	        get:{
	            method:"GET"
	        }
	    })
	}
	gRDList.$inject = ['$resource']

	function getBidVehicleInfo($resource){
		return $resource(global_config.server + "/api/quoted/getBidVehicleInfo",{},{
	        get:{
	            method:"GET"
	        }
	    })
	}
	getBidVehicleInfo.$inject = ['$resource']

	function getOrderParam(){
		return {
			sourceType: "0"
		}
	}

	function orderlistQuery($resource){
		return $resource(global_config.server + "/api/quoted/getQuotedOrderList",{},{
	        get:{
	            method:"GET"
	        }
	    })
	}
	orderlistQuery.$inject = ['$resource']

	function getOrderList(sRQ,defParam,pageIndex,$q,$log){
		return function(){
			var delay = $q.defer();
			sRQ.get({sourceType: defParam.sourceType,pageIndex: pageIndex.index,pageSize: 5},function(data){
				delay.resolve(data);
			},function(data){
				delay.reject('Error');
			})
			return delay.promise;
		}
	}
	getOrderList.$inject =['orderlistQuery','defOrderParam','getPageIndex','$q','$log']

	function invalidTheBid($resource){
		return $resource(global_config.server+"/api/quoted/invalidTheBid",{},{
	        break:{
	            method:"POST"
	        }
	    })
	}
	invalidTheBid.$inject=['$resource']

	function closeQuotedInfo($resource){
		return $resource(global_config.server+"/api/quoted/closeQuotedInfo",{},{
	        giveUp:{
	            method:"POST"
	        }
	    })
	}
	closeQuotedInfo.$inject=['$resource']	

	function doAddQuote($resource){
		return $resource(global_config.server+"/api/quoted/addQuotedInfo",{},{
	        saveQuote:{
	            method:"POST"
	        }
	    })
	}
	doAddQuote.$inject=['$resource']	

	function getRecordList($resource){
		return $resource(global_config.server+"/api/record/getRecordList",{},{
	        get:{
	            method:"GET"
	        }
	    })
	}
	getRecordList.$inject = ['$resource']

	function getDriverLen(){
		return {
			len: "",
			status: "",
			price: "",
			mark:""
		}
	}

	function qOrderDetailQuery($resource){
		return $resource(global_config.server+"/api/quoted/getQuotedOrderDetail",{},{
	        get:{
	            method:"GET"
	        }
	    })
	}
	qOrderDetailQuery.$inject = ['$resource']

	function getQuotedDeatil(qODQ,$q,$log){
		return function(){
			var delay = $q.defer();
			qODQ.get({quotedId:global.QueryString.quotedId},function(data){
				delay.resolve(data)
			},function(){
				delay.reject('Error');
			})
			return delay.promise;
		}
	}
	getQuotedDeatil.$inject =['qOrderDetailQuery','$q','$log']	

	function reSupCode(){
		return {
			code: ""
		}
	}

	function subRegionQuery($resource){
		return $resource(global_config.server+"/api/region/findByCode",{},{
	        get:{
	            method:"GET"
	        }
	    })
	}
	subRegionQuery.$inject = ['$resource']

	function getSubReg(sRQ,dcode,$q,$log){
		return function(){
			var delay = $q.defer();
			sRQ.get({regionCode: dcode.code},function(data){
				delay.resolve(data)
			},function(){
				delay.reject('Error');
			})
			return delay.promise;
		}
	}
	getSubReg.$inject =['subRegionQuery','defSupCode','$q','$log']

	function delLine($resource){
		return $resource(global_config.server+"/api/line/delLine",{}, {
			deleteLine:{
				method:"POST",
				headers:{
					'Authorization':global.getUserId() + ':' + global.getToken()
				}
			}
		})
	}

	function addLine($resource){
		return $resource(global_config.server+"/api/line/addLine",{},{
	        saveLine:{
	            method:"POST"
	        }
	    })
	}
	addLine.$inject=['$resource']

	function getCityQuery($resource){
		return $resource(global_config.server+"/api/region/list",{},{
	        get:{
	            method:"GET"
	        }
	    })
	}
	getCityQuery.$inject = ['$resource']

	function getCity(gCQ,$q,$log){
		return function(){
			var delay = $q.defer();
			gCQ.get(function(data){
				delay.resolve(data)
			},function(){
				delay.reject('Error');
			})
			return delay.promise;
		}
	}
	getCity.$inject = ['getCityQuery','$q','$log']

	function getLineId(){
		return {
			lineId:""
		}
	}

	function getPageIndex(){
		return {
			index: "0"
		}
	}

	function getallLinesQuery($resource){
		return $resource(global_config.server+"/api/source/query",{},{
	        get:{
	            method:"GET"
	        }
	    })
	}
	getallLinesQuery.$inject = ['$resource']

	function selectLines($resource){
		return $resource(global_config.server+"/api/source/query", {}, {
	        get:{
	            method:"GET"
	        }
	    })
	}
	selectLines.$inject=['$resource']

	function getallLines(gAQ,lineId,pageIndex,$location,$route,$q,$log){
		return function(){
			var delay = $q.defer();
			gAQ.get({lineId:lineId.lineId,pageIndex:pageIndex.index,pageSize:5},function(data){
				delay.resolve(data)
			},function(){
				delay.reject('Error');
			})
			return delay.promise;
		}
	}
	getallLines.$inject = ['getallLinesQuery','lineId','getPageIndex','$location','$route','$q','$log']

	function getLineListQuery($resource){
		return $resource(global_config.server+"/api/line/getLineList",{},{
	        get:{
	            method:"GET"
	        }
	    })
	}
	getLineListQuery.$inject = ['$resource'];

	function getLineList(gLQ,$q,$log){
		return function(){
			var delay = $q.defer();
			gLQ.get(function(data){
				delay.resolve(data);
			},function(){
				delay.reject('Error');
			})
			return delay.promise;
		}
	}
	getLineList.$inject =['getLineListQuery','$q','$log']

	function getUserhead($resource){
		return $resource(global_config.server+"/api/account/userhead",{},{
	        get:{
	            method:"get"
	        }
	    })
	}
	getUserhead.$inject=['$resource']

	function getAgent($resource){
		return $resource(global_config.server+"/api/agent/query",{},{
	        get:{
	            method:"get"
	        }
	    })
	}
	getAgent.$inject=['$resource']

	function getDriverLen(){
		return {
			len: "",
			status: "",
			price: "",
			mark:""
		}
	}

	function doAddQuote($resource){
		return $resource(global_config.server+"/api/quoted/addQuotedInfo",{},{
	        saveQuote:{
	            method:"POST"
	        }
	    })
	}
	doAddQuote.$inject=['$resource']	

	function getInfo($resource){
		return $resource(global_config.server+"/api/source/info",{},{
	        get:{
	            method:"GET"
	        }
	    });
	}
	getInfo.$inject = ['$resource']

	function getMessageList($resource){
		return $resource(global_config.server+"/api/message/getMessageList",{},{
	        get:{
	            method:"GET"
	        }
	    });
	}
	getMessageList.$inject = ['$resource']

	function geoQuery($resource){
		return $resource(global_config.server+"/api/trip/getgeo",{},{
	        get:{
	            method:"GET"
	        },
	    });
	}
	geoQuery.$inject=['$resource'];

	function getGeo(gQy,$q,$log){
		return function(){
			var delay = $q.defer();
			gQy.get({loadId: global.QueryString.loadId},function(data){
				delay.resolve(data);
			},function(){
				delay.reject('Error');
			})
			return delay.promise;
		}
	}
	getGeo.$inject=['geoQuery','$q','$log'];


	function trackInfoQuery($resource){
		return $resource(global_config.server+"/api/trip/trackInfo",{},{
	        get:{
	            method:"GET"
	        },
	    });
	}
	trackInfoQuery.$inject=['$resource'];

	function getTrackInfo(tIQ,$q,$log){
		return function(){
			var delay = $q.defer();
			tIQ.get({loadId: global.QueryString.loadId},function(data){
				delay.resolve(data);
			},function(data){
				if(data.statusText=="Forbidden" && data.status == "403"){
					$.cookie("user","");
					window.confirm("您的帐号已经被登出");
	                window.location.href = global.getContextPath()+"/login_dialog.htm"
	            }
				delay.reject('Error');
			})
			return delay.promise;
		}
	}
	getTrackInfo.$inject=['trackInfoQuery','$q','$log'];

	function loaBillQuery($resource){
		return $resource(global_config.server+"/api/quoted/getLoadBillByQuotedId",{},{
	        get:{
	            method:"GET"
	        },
	    });
	}
	loaBillQuery.$inject=['$resource'];

	function getLoaBill(lBQ,$q,$log){
		return function(){
			var delay = $q.defer();
			lBQ.get({quotedId: global.QueryString.quotedId},function(data){
				delay.resolve(data);
			},function(){
				delay.reject('Error');
			})
			return delay.promise;
		}
	}
	getLoaBill.$inject=['loaBillQuery','$q','$log'];


	function getQOList($resource){
		return $resource("/text.json");
	}
	getQOList.$inject=['$resource'];

	function getQuotedOrderList(gQ,$q,$log){
		return function(){
			var delay = $q.defer();
			gQ.get({sourceType:'0'},function(data){
				delay.resolve(data);
			},function(){
				delay.reject('Error');
			})
			return delay.promise;
		}
	}
	getQuotedOrderList.$inject=['getQOList','$q','$log'];

	function getRecommendSource($resource){
		return $resource(global_config.server+"/api/source/getRecommendSource",{},{
	        get:{
	            method:"GET"
	        },
	    });
	}
	getRecommendSource.$inject=['$resource'];

	function getloadId(){
		return {
			loadId: ""
		}
	}
})(window,window.angular)
