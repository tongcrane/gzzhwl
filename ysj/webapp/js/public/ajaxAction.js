var getAjax = (function(){
	return {
		//首页:常跑线路接口 /api/line/getLineList
		getLineList : function(){
			return function(params){
				$.get(global.server+"/api/line/getLineList",params,function(strData){
					if (strData && strData.status && strData.status.statusCode == global.status.success) {
						var data = strData.data,
							departureCodeCn,destinationCodeCn;
						if(params){
							$.each(data,function(i,item){
								var lineInfoId = item.lineInfoId,count = i;
								if(params.lineInfoId && lineInfoId == params.lineInfoId){
									var _this = $(".depart:eq("+params.ct+")").find(".go_deatil");
									_this.attr("href",_this.attr("href")+"&count="+count);
								}
							});
						}else{
							$.each(data,function(i,item){
								departureCodeCn = item.departureCodeCn,
								destinationCodeCn = item.destinationCodeCn,
								lineInfoId = item.lineInfoId,
								destinationCode = item.destinationCode,
								departureCode = item.departureCode;
								if(i>3){
									$(".oftengoadd").hide();
								}
								// href="/supply_hall.htm?q='+destinationCode+'&e='+departureCode+'&count='+i+'&key='+lineInfoId+'"
								$(".oftengoadd").before(
								'<li class="clearfix">\
									<a style="display: block; height: 100%; weight: 100%;">\
									<span class="departPlace">\
										<i class="font14">起运</i>\
										<i class="font24">'+departureCodeCn+'</i>\
									</span>\
									<span class="line"></span>\
									<span class="destination">\
										<i class="font14">目的</i>\
										<i class="font24">'+destinationCodeCn+'</i>\
									</span>\
									</a>\
								</li>')
							});
						}
					}
				})
			}
		},
		//首页:热门线路列表 /api/source/getHotLineSourceList
		getHotLineSourceList : function(){
			var _this = this;
			return function(params){
				$.get(global.server+"/api/source/getHotLineSourceList",params,function(strData){
					if (strData && strData.status && strData.status.statusCode == global.status.success) {
						var data = strData.data,html="",addr_str = "",startCodeP="",endCodeP="",ct=0;
						$.each(data,function(i,item){
							var regionName = item.regionName,
								sourceList = item.sourceList?item.sourceList:"",
								tophtml="",htmlLi="",htmlContext="",
								lineInfoId = item.lineInfoId,
								deatilHtml="";
							if(global.isLogin()){
								if(lineInfoId){
									_this.getLineList()({"lineInfoId":lineInfoId,"sign":1,"ct":ct});
									ct++;
								}
							}
							if(i<3){
								$.each(sourceList,function(j,items){
									var startCodePCn = items.startCodeCCn,
										endCodePCn = items.endCodeCCn,
										needLength = items.needLength,
										goodsWeight = items.goodsWeight,
										needType = items.needType,
										goodsName = items.goodsName,
										startTime = items.startTime,
										sourceId = items.sourceId,
										needStartTime = items.needStartTime,
										url ="";
										
										if(j==0 && items){
											startCodeP = items.startCodeP;
											endCodeP = items.endCodeP;
										}
									
									if(j<3){
										if(global.isLogin()){
											url = "supply_detail.htm?id="+sourceId;
										}else{
											url="login.htm"
										}
										htmlLi += 
											'<li>'+
												'<a href="'+url+'" class="route_w">'+
													'<p class="route clearfix">'+
														'<span class="route_l">'+
															'<i>出发</i>'+
															'<i class="city">'+startCodePCn+'</i>'+
														'</span>'+
														'<span class="route_r">'+
															'<i>到达</i>'+
															'<i class="city">'+endCodePCn+'</i>'+
														'</span>'+
													'</p>'+
													'<p class="car">'+
														'<span class="w88">'+
															'<i>车长</i>'+
															'<i class="font16">'+needLength+'米</i>'+
														'</span>'+
														'<span class="w100">'+
															'<i>重量</i>'+
															'<i class="font16">'+goodsWeight+'吨</i>'+
														'</span>'+
														'<span>'+
															'<i>车型</i>'+
															'<i class="font16">'+needType+'</i>'+
														'</span>'+
													'</p>'+
													'<p class="car bale">'+
														'<span>'+
															'<i class="w88">货物类型</i>'+
															'<i class="bag w178">'+goodsName+'</i>'+
														'</span>'+
													'</p>'+
													'<p class="car bale time">'+
														'<span>'+
															'<i>出发时间</i>'+
															'<i class="date">&nbsp;&nbsp;&nbsp;'+needStartTime+'</i>'+
														'</span>'+     
													'</p>'+
												'</a>'+
											'</li>';
									}
								})
								for(var s=0;s<(3-sourceList.length);s++){
									htmlLi+='<li>\
												<span class="no_data">暂无数据</span>\
											</li>'
								}

								if(global.isLogin()){
									if(sourceList!=""){
										deatilHtml = '<a href="supply_hall.htm?e='+startCodeP+'&q='+endCodeP+'&key='+lineInfoId+'" class="more go_deatil">更多></a>';
									}else{
										deatilHtml ='<a href="supply_hall.htm" data-status="null" class="more go_deatil">更多></a>';
									}
								}else{
									deatilHtml ='<a href="login.htm" data-status="null" class="more go_deatil">更多></a>';
								}
								tophtml =  '<li class="clearfix">'+
												'<span class="tit">'+
													'<i class="place">'+regionName+'</i>'+
													'<i class="start">&nbsp;出发路线</i>'+
												'</span>'+
												deatilHtml+
												'<span class="line"></span>'+
											'</li>';
							}
							htmlContext = tophtml + htmlLi;
							html += '<ul class="depart">'+htmlContext+'</ul>'
						})
						$(".depart_list").html(html);
					}
				});
			}
		},
		//首页:最新线路列表 /api/source/getNewSource
		getNewSource : function(){
			return function(params){
				$.get(global.server+"/api/source/getNewSource",params,function(strData){
					if (strData && strData.status && strData.status.statusCode == global.status.success) {
						var html="",data = strData.data;
						$.each(data,function(i,item){
							var endCodePCn = item.endCodePCn,
								startCodePCn = item.startCodePCn,
								needLength = item.needLength,
								goodsWeight = item.goodsWeight,
								needType = item.needType,
								goodsName = item.goodsName,
								updatedTime = item.updatedTime,
								sourceId = item.sourceId,
								s = updatedTime.split("-"),  
								day = s[2].substring(0,2),
								url ="";

								if(global.isLogin()){
									url = "supply_detail.htm?id="+sourceId;
								}else{
									url = "login.htm"
								}

								html += '<li class="clearfix">\
										<span class="date">\
											<i class="day">'+day+'</i>\
											<i>'+s[0]+'/'+s[1]+'</i>\
										</span>\
										<a href="'+url+'">\
											<div class="bid">\
												<div class="bid_l">\
													<p class="route">\
														<span class="city">'+startCodePCn+'</span>\
														<span class="city">'+endCodePCn+'</span>\
													</p>\
													<p class="car">\
														<span class="w85">\
															<i>车长</i>\
															<i class="font16">'+needLength+'米</i>\
														</span>\
														<span class="w88">\
															<i>重量</i>\
															<i class="font16">'+goodsWeight+'吨</i>\
														</span>\
														<span>\
															<i>车型</i>\
															<i class="font16">'+needType+'</i>\
														</span>\
													</p>\
													<p class="car bag">\
														<span>\
															<i class="w85">货物类型</i>\
															<i class="font16 w88">'+goodsName+'</i>\
														</span>\
													</p>\
												</div>\
												<div class="bid_r">\
													<button>竞价</button>\
												</div>\
											</div>\
										</a>\
										<span class="circle"></span>\
									</li>';
						})

						if(data.length<4){
							var count = 4 - data.length;
							for(var i=0;i<count;i++){
								html += '<li class="clearfix">\
											<span class="date">\
												<i class="day"></i>\
												<i></i>\
											</span>\
											<div class="bid">\
												<div class="no_data">\
													暂无数据\
												</div>\
											</div>\
											<span class="circle active"></span>\
										</li>';
							}
						}
						
						$(".newSource").html(html)
					}
				});
			}
		},
		//首页:新增货源数量 /api/source/getSourceAndQutoedCnt
		getSourceAndQutoedCnt : function(){
			return function(params){
				$.get(global.server+"/api/source/getSourceAndQutoedCnt",params,function(strData){
					if (strData && strData.status && strData.status.statusCode == global.status.success) {
						var data = strData.data;
						$(".qutoedCnt").text(parseInt(data.qutoedCnt)+123);
						$(".sourceCnt").text(parseInt(data.sourceCnt)+123);
					}
				});
			}
		},
		//忘记密码页:忘记密码   /api/account/resetPassword
		resetPassword : function(){
			return function(params){
				$.post(global.server+"/api/account/resetPassword",params,function(strData){
					if (strData && strData.status && strData.status.statusCode == global.status.success) {
						window.confirm("密码修改成功");
						setTimeout(function(){
							window.location.href= global.getContextPath() + "/login.htm";
						},2000)
					}
				});
			}
		},
		//忘记密码页:获取or验证图片验证码    /api/account/captcha 
		captcha : function(){
			var _this = this;
			return function(params){
				var ndata = new Date().getTime(),
					errorMsg = "",
					url = global.server+"/api/account/captcha?nocache="+ ndata;
			    $.ajaxSetup({
			    	async : false
			    });
				if(!params){
					$.get(url,params,function(strData){
						if (strData && strData.status && strData.status.statusCode == global.status.success) {
							var imgSrc = strData.data.image,
								validId = strData.data.validId;
							$("#reg_val_code").attr("data-id",validId);
							$(".picCode").find("img").attr("src","data:image/png;base64,"+imgSrc);
						}
					})
				}else{
					$.post(url,params,function(strData){
						if (strData && strData.status && strData.status.statusCode == global.status.success) {
							console.log(strData.data);
							if(strData.data != true){
								errorMsg ="error";
								_this.captcha()();
								$("#reg_val_code").focus().css("border","1px solid red");
							}
						}
					})
				}

				return errorMsg;
			}
		},
		//忘记密码页:判断手机号是否已注册   /api/account/isExist
		isExist : function(){
			var _this = this;
			return function(params){
				var errorMsg = "";
			    $.ajaxSetup({
			    	async : false
			    });
				$.get(global.server+"/api/account/isExist",params,function(strData){
					if(strData.status.statusCode == "200"){
						errorMsg ="error";
					}else if(strData.status.statusCode == "11017"){
						if(params.isSendCode){
							$("#dxcode").removeAttr("readonly");
							_this.generate()(params);
						}
					}
				})	
				return errorMsg;		
			}
		},
		//忘记密码页:发送手机验证码  /api/account/generate
		generate : function(){
			return function(params){
				$.post(global.server+"/api/account/generate",params,function(strData){
					if (strData && strData.status && strData.status.statusCode == global.status.success) {
						$(".getTelCode").hide();
						$(".hideclock").show(function(){
							var _this = $(this);
							setInterval(function(){
								if(_this.find("em").text() != "1"){
									_this.find("em").text(parseInt(_this.find("em").text())-1);
								}else{
									$(".hideclock").hide();
									$(".getTelCode").show();
								}
							},1000)
						});
					}else{
						window.confirm(strData.status.errorMsg);
					}
				});
			}
		},
		//忘记密码页:验证短信验证码是否正确  /api/account/validSmsCode
		validSmsCode : function(){
			return function(params){
				var errorMsg = "";
			    $.ajaxSetup({
			    	async : false
			    });
				$.post(global.server+"/api/account/validSmsCode",params,function(strData){
					if (strData && strData.status && strData.status.statusCode == global.status.success) {
						$(".confirm_info").hide();
						$(".confirm_pwd").show();
					}else{
						errorMsg="error";
						window.confirm("验证码错误！")
					}
				});
				return errorMsg;
			}
		},
		//忘记密码页:修改密码   /api/account/changePassword
		changePassword : function(){
			return function(params){
				$.post(global.server+"/api/account/changePassword",params,function(strData){
					if (strData && strData.status && strData.status.statusCode == global.status.success) {
					}
				});
			}
		}
	}
}());
