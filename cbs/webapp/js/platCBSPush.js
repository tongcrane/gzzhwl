$(function() {
	
	/*$('.search-content').bind('click', function(e){
		return false;
	});
	var advSearch = {
		show : function() {
			$('.search-content').show(10, function(){
				$('body').one('click', function(){
					advSearch.hide();
				});
			});
		},
		hide : function() {
			$('.search-content').hide();
			$('body').unbind();
		}
	}*/

	// 点击高级搜索，搜索框显示
	/*$('.advance-sea').click(function(event) {
		advSearch.show();
	});

	$('.sea-close').click(function(event) {
		advSearch.hide();
	});*/
	
	var doPublic=function(sourceId) {
		var result={};
    	result.sourceId = sourceId;
        var url = "/admin/source/public";
        $.ajax({
            url: global.server + url,
            contentType: "application/json",
            type: "POST",
            data: JSON.stringify(result),
            dataType: "json",
            success: function (msg) {
                if (msg && msg.status && msg.status.statusCode == global.status.success) {
                	window.location.reload();
                } else {
                	alert(msg.status.errorMsg);
                }
            }
        });
	}
	
	var doReject=function(sourceId){
		var reason=$('#action_reason').val();
		var url = "/admin/source/reject";
		$.ajax({
	        url: global.server + url,
	        type: "POST",
	        data: {sourceId:sourceId,reason:reason},
	        success: function (msg) {
	            if (msg && msg.status && msg.status.statusCode == global.status.success) {
	            	table.load({
	            		queryType:0,
	            		sourceType:'01'
	            	});
	            } else {
	            	alert(msg.status.errorMsg);
	            }
	        }
	    });
	}
	
	var openSource = function(sourceId){
    	var url = global.getContextPath() + '/checkSourceList.html?sourceId='+sourceId;
    	var body = document.getElementsByTagName("body")[0];
    	var el = document.createElement("a");
    	body.appendChild(el);
    	el.href = url;
    	el.target = '_blank';
    	el.click();
    	body.removeChild(el);
    }
	
	var table = new CBSTable({
		page : $('.handle-page'),
		content : $('.handle-content > table > tbody'),
		url : '/admin/source/queryOrderSourceList',
		method  : 'GET',
		pageNumber : 0,
		pageSize : 10,
		rowRender : function(index, row){
			var el = $('<tr>\
							<td>\
								<div class="f-width order-num to-info"></div>\
							</td>\
							<td>\
								<div class="order-line to-info"></div>\
							</td>\
							<td>\
								<div class="order-list to-info"></div>\
							</td>\
							<td>\
								<div class="owe-to">\
						    		<p class="">操作</p>\
						    		<span class="owe-span">\
						    			<ul class="deadline">\
						    				<li>\
						    					<p>计划发车时间</p>\
						    					<b class="b1"></b>\
						    				</li>\
						    				<li>\
						    					<p>计划到达时间</p>\
						    					<b class="b2"></b>\
						    				</li>\
						    				<li>\
						    					<p>推送时间</p>\
						    					<b class="b3"></b>\
						    				</li>\
						    			</ul>\
						    		</span>\
									<span class="plat_handle">\
						    			<ol class="plat_ul">\
						    			</ol>\
						    		</span>\
						    	</div>\
							</td>\
						</tr>');
			var startP=global.defaultIfBlack(row.startCodePCn,'');
			var startC=global.defaultIfBlack(row.startCodeCCn,'');
			var endP=global.defaultIfBlack(row.endCodePCn,'');
			var endC=global.defaultIfBlack(row.endCodeCCn,'');
			var start=startP+startC;
			var end=endP+endC;
			//var lineInfo=start?start+" - "+end:end;
			var lineInfo=startP?startP+" - "+endP:'';
			
			var length = global.defaultIfBlack(row.needLength,'')?row.needLength:'';
			if(length) {
				length=(length=='其他')?" 车长 :"+length:" 车长: "+length+'米';
			}
			var hisRemark=row.hisRemark;
			if(hisRemark) {
				hisRemark=" 备注："+hisRemark;
			} else {
				hisRemark='';
			}
			
			var buttons=row.actionList;
			if(buttons!=null) {
				$.each(buttons,function(i,j){
					el.find('.plat_ul').append("<li id=action_"+j.code+">"+j.name+"</li>");
				});
			}
			
			el.find('.order-num').html("单号："+global.defaultIfBlack(row.orderNo,''));
			el.find('.order-line').html(lineInfo);
			el.find('.order-list').html(global.defaultIfBlack(row.statusCn,'')+"：车型："+global.defaultIfBlack(row.needType)+length+hisRemark);
			el.find('.b1').html(global.defaultIfBlack(row.needStartTime,''));
			el.find('.b2').html(global.defaultIfBlack(row.needArriveTime,''));
			el.find('.b3').html(global.defaultIfBlack(row.createdTime,''));
			el.find('.to-info').click(function(){
				openSource(row.sourceId);
			});
			el.find('#action_02').click(function(){
				var options = {
        				data : {sourceId : row.sourceId},
        				text : '是否确认',
        				callback:function(data){
        					doPublic(row.sourceId);
        				}
        			}
        		new CBSConfirm(options);
				//doPublic(row.sourceId);
			});
			el.find('#action_03').click(function(){
				var options = {
		    		data : {},
		    		text : '请输入拒绝理由',
		    		callback:function(data){
		    			doReject(row.sourceId);
		    		}
		    	}
		    	new CBSPop(options);
			});
			return el;
		},
		onBeforeLoad : function(param){
			return param.queryType != null;
		}
	});
	
	$('#btn_search').click(function(){
		var queryContent=$('.list-search').val();
		if(queryContent==null||queryContent=='') {
			table.load({
				queryType:0,
				sourceType:'01'
			});
		} else {
			table.load({
				queryType:0,
				sourceType:'01',
				queryContent:queryContent
			});
		}
	});
	
	/*$('#btn_adv_search').click(function(){
		var columns=$('.column');
		var data = {};
		columns.each(function(i, j){
			var _this = $(this);
			var pName = _this.data("column");
			var pValue = _this.val();
			data[pName] = pValue;
		});
		data.queryType=1;
		table.load(data);
		advSearch.hide();
	});*/
	
	table.load({
		queryType:0,
		sourceType:'01'
	});

});