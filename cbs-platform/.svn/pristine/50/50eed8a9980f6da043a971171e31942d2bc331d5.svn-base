$(function() {
	
	/*var onBodyDown = function(event){
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
     }*/
    
	// 点击高级搜索，搜索框显示
	/*$('.advance-sea').click(function(event) {
		advSearch.show();
	});

	$('.sea-close').click(function(event) {
		advSearch.hide();
	});*/
	
	/*$('select').each(function(i, j){
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
	});*/
	
	/*var editCustom=function(customerId) {
		var url = global.getContextPath() + '/addCustomInfo.html?custId='+customerId;
    	var body = document.getElementsByTagName("body")[0];
    	var el = document.createElement("a");
    	body.appendChild(el);
    	el.href = url;
    	el.target = '_blank';
    	el.click();
    	body.removeChild(el);
	}*/
	
	/*var openCustom = function(customerId){
    	var url = global.getContextPath() + '/checkCustomInfo.html?custId='+customerId;
    	var body = document.getElementsByTagName("body")[0];
    	var el = document.createElement("a");
    	body.appendChild(el);
    	el.href = url;
    	el.target = '_blank';
    	el.click();
    	body.removeChild(el);
    }*/
	
	/*var getPcd = function(data){
		var p = "";
		var c = "";
		var d = "";
		$.each(data,function(i, j){
			if(j.regionLevel == 1){
				p = j.regionName;
			} else if(j.regionLevel == 2){
				c = j.regionName;
			} else if(j.regionLevel == 3){
				d = j.regionName;
			}
		});
		var result = '';
		if(p){
			result+=p;
		}
		if(c){
			result+=c;
		}
		if(d){
			result+=d;
		}
		return result;
	}*/
	
	var table = new CBSTable({
		page : $('.handle-page'),
		content : $('.handle-content > table > tbody'),
		url : '/admin/customer/pageCustList',
		method  : 'get',
		pageNumber : 0,
		pageSize : 10,
		rowRender : function(index, row){
			var el = $('<tr>\
					<td><span class="check-box check"></span></td>\
					<td class="fullName to-info"></td>\
					<td class="contactName to-info"></td>\
					<td class="mobile to-info"></td>\
					<td class="telphone to-info"></td>\
					<td class="isAgreement to-info"></td>\
				</tr>');
			
//			var area = getPcd(row.area);
//			var mobile=global.defaultIfBlack(row.mobile,'')?global.defaultIfBlack(row.mobile,'')+'':';';
//			var telphone=global.defaultIfBlack(row.telphone,'')?global.defaultIfBlack(row.telphone,'')+';':'';
//			el.find('.custom-info').html(global.defaultIfBlack(row.fullName,''));
//			el.find('.custom-list').html(global.defaultIfBlack(row.contactName,'')+" "+mobile+" "+telphone+" "+exStatus(global.defaultIfBlack(row.isAgreement,''))+" - "+global.defaultIfBlack(area,'')+global.defaultIfBlack(row.address,''));
//			el.find('.edit').click(function(){
//				editCustom(row.customerId);
//			});
			/*el.find('.to-info').click(function(){
				openCustom(row.customerId);
			});*/
			var isAgreement=exStatus(global.defaultIfBlack(row.isAgreement,''));
			el.find('.fullName').html(global.defaultIfBlack(row.fullName,''));
			el.find('.contactName').html(global.defaultIfBlack(row.contactName,''));
			el.find('.mobile').html(global.defaultIfBlack(row.mobile,''));
			el.find('.telphone').html(global.defaultIfBlack(row.telphone,''));
			el.find('.isAgreement').html(isAgreement);
			el.find('.check-box').data(row).val(row.customerId);
			el.find('.check').click(function(){
                var flag=$(this).hasClass('checked');
                console.log(flag)
                if(flag) {
                    $(this).removeClass('checked');
                } else {
                    $(this).addClass('checked');
                }
            });
			return el;
		},
		onBeforeLoad : function(param){
			return param.queryType != null;
		}
	});
	
	var exStatus=function(code) {
		if(code=="0") {
			return "非合同客户";
		} else if(code=="1") {
			return "合同客户";
		}
	}
	
	//全选
    $('.all-check').click(function(){
    	var check_box=$('.check');
    	var flag=$(this).hasClass('checked');
    	if(flag){
    		$(this).removeClass('checked');
    		$.each(check_box,function(i,j) {
    			$(j).removeClass('checked');
    		});
    	} else {
    		$(this).addClass('checked');
    		$.each(check_box,function(i,j) {
    			$(j).addClass('checked');
    		});
    	}
    });
	
	//搜索
	$('#btn_search').click(function(){
		table.load({
			queryType:0,
			keyWord:$('.list-search').val()
		});
	});
	
	//高级搜索
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
	
	$('.handle > .handle-btn > .del-customer').click(function(){
		var selected=$('td > .checked');
		if(selected.length>0) {
			if(confirm('确认删除？')){
				$('td > .checked').each(function() {  
			    	var customerId=$(this).val();
			    	$.ajax({
				        url: global.getServer() + '/admin/customer/removeCustomer',
				        type: "POST",
				        data: {custId:customerId},
				        success: function (msg) {
				            if (msg && msg.status && msg.status.statusCode == global.status.success) {
				            	table.reload();
				            }
				        }
				    });
			    });
			}
		} else {
			alert("请选择要删除的记录");
		}
	});
	
	table.load({
		queryType:0
	});

});