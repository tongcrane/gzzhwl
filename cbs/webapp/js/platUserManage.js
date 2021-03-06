/*
* @Author: zhihong-zh
* @Date:   2016-06-22 09:30:30
* @Last Modified by:   zhihong-zh
* @Last Modified time: 2016-06-22 09:37:58
*/

$(function() {
//	$('.search-content').bind('click', function(e){
//		return false;
//	});
//	var advSearch = {
//		show : function() {
//			$('.search-content').show(10, function(){
//				$('body').one('click', function(){
//					advSearch.hide();
//				});
//			});
//		},
//		hide : function() {
//			$('.search-content').hide();
//			$('body').unbind();
//		}
//	}
	
	var onBodyDown = function(event){
    	if(($(event.target).parents(".search-content").length>0)){
    		
    	} else {
    		advSearch.hide();
    	}
    };
    
    var advSearch = {
        show : function() {
            $('.search-content').show(10, function(){
            	$("body").bind("mousedown", onBodyDown);
            });
        },
        hide : function() {
        	$("body").unbind("mousedown", onBodyDown);
            $('.search-content').hide();
        }
    }

	// 点击高级搜索，搜索框显示
	$('.advance-sea').click(function(event) {
		advSearch.show();
	});

	$('.sea-close').click(function(event) {
		advSearch.hide();
	});
	
	$('select').each(function(i, j){
		var minWidth = $(j).data('width');
		$(j).multiselect({
			minWidth : minWidth,
			header : false,
			multiple : false,
			selectedList : 1,
			appendTo : '.search-content',
			position:{
				my: 'left top',
			    at: 'left bottom'
			},
			noneSelectedText: '请选择',
		})
	});
	
	var openAccount = function(accountId){
    	var url = global.getContextPath() + '/checkUserManage.html?accountId='+accountId;
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
		url : '/admin/account/pageAccountList',
		method  : 'GET',
		pageNumber : 0,
		pageSize : 10,
		rowRender : function(index, row){
			var el = $('<tr> \
						<td> \
							<div class="f-width"> \
								<input class="check-box" type="checkbox"/> \
							</div> \
						</td> \
						<td> \
							<div class="car-num to-info"></div> \
						</td> \
						<td> \
							<div class="car-list to-info"></div> \
						</td> \
						<td> \
							<div class="owe-to"> \
					    		<p class="check_by to-info"></p> \
					    		<span class="owe-span"> \
					    			<ul class="deadline"> \
					    				<li> \
					    					<p>冻结/解冻时间</p> \
					    					<b class="b1"></b> \
					    				</li> \
					    			</ul> \
					    		</span> \
					    	</div> \
						</td> \
					</tr>');
			el.find('.car-num').html(global.defaultIfBlack(row.realName,'')+" "+global.defaultIfBlack(row.telphone,''));
			//el.find('.car-list').html(exStatus(row.status,'')+": 身份证号"+global.defaultIfBlack(row.idno,'')+"-公司名称"+global.defaultIfBlack(row.companyFullName,''));
			el.find('.car-list').html(exStatus(row.status,'')+": 身份证号"+global.defaultIfBlack(row.idno,''));
			el.find('.check_by').html("操作人："+global.defaultIfBlack(row.checkBy,''));
			el.find('.b1').html(global.defaultIfBlack(row.operationTime));
			el.find('.f-width > .check-box').data(row).val(row.accountId);
			el.find('.to-info').click(function(){
				openAccount(row.accountId);
			});
			el.find('.f-width > .check-box').click(function(){
				var flag=$(this).attr('checked');
				if(flag===undefined) {
					$(this).attr('checked','checked');
					$("tr:eq("+index+")").css('background-color','#FBEDC1');
				} else {
					$(this).removeAttr('checked');
					$("tr:eq("+index+")").removeAttr("style");
				}
			});
			return el;
		},
		onBeforeLoad : function(param){
			return param.queryType != null;
		}
	});
	
	$('#btn_search').click(function(){
		table.load({
			queryType:0,
			queryContent:$('.list-search').val()
		});
	});
	
	$('#btn_adv_search').click(function(){
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
	});

	var exStatus=function(code) {
		if(code=="00") {
			return "正常";
		} else if(code=="04") {
			return "冻结";
		}
	}
	
	$('#freeze').click(function(){
		var text="";  
	    $('.f-width > .check-box:checked').each(function() {  
	    	var accountId=$(this).val();
	    	$.ajax({
		        url: global.server + '/admin/account/freezeAccount',
		        type: "POST",
		        data: {accountId:accountId},
		        success: function (msg) {
		            if (msg && msg.status && msg.status.statusCode == global.status.success) {
		            	table.reload();
		            }
		        }
		    });
	    });
	});
	
	$('#thaw').click(function(){
		var text="";  
	    $('.f-width > .check-box:checked').each(function() {  
	    	var accountId=$(this).val();
	    	$.ajax({
		        url: global.server + '/admin/account/thawAccount',
		        type: "POST",
		        data: {accountId:accountId},
		        success: function (msg) {
		            if (msg && msg.status && msg.status.statusCode == global.status.success) {
		            	table.reload();
		            }
		        }
		    });
	    });
	});
	
	table.load({
		queryType:0
	});
	
});