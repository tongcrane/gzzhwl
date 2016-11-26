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

	 /*$('.use-date').datetimepicker({
	 	timepicker:false,
		format:'Y-m-d',
		autoclose:true,
		todayHighlight:true,
		keyboardNavigation:false
	 });*/

	/*var openDriver = function(driverInfoId){
		var url = global.getContextPath() + '/addDriverInfoOne.html?driverInfoId='+driverInfoId;
    	var body = document.getElementsByTagName("body")[0];
    	var el = document.createElement("a");
    	body.appendChild(el);
    	el.href = url;
    	el.target = '_blank';
    	el.click();
    	body.removeChild(el);
    }*/
	/*var openDriverInfo = function(driverInfoId){
		var url = global.getContextPath() + '/checkDriverInfomation.html?driverInfoId='+driverInfoId;
		var body = document.getElementsByTagName("body")[0];
		var el = document.createElement("a");
		body.appendChild(el);
		el.href = url;
		el.target = '_blank';
		el.click();
		body.removeChild(el);
	}*/
	
	var table = new CBSTable({
		page : $('.handle-page'),
		content : $('.handle-content > table > tbody'),
		url : '/admin/driver/page',
		method  : 'POST',
		pageNumber : 0,
		pageSize : 10,
		rowRender : function(index, row){
			var el = $('<tr>\
					<td><span class="check-box check"></span></td>\
					<td class="realName to-info"></td>\
					<td class="telphone to-info"></td>\
					<td class="dlType to-info"></td>\
					<td class="attributes to-info"></td>\
					<td class="qcType to-info"></td>\
				</tr>');
			
//			var qcType=global.defaultIfBlack(row.qcType,'')?" 从业资格证类型："+global.defaultIfBlack(row.qcType,''):"";
//			var cardName=global.defaultIfBlack(row.cardName,'')?" - 开户名："+global.defaultIfBlack(row.cardName,''):"";
//			var depositBank=global.defaultIfBlack(row.depositBank,'')?" 开户行："+global.defaultIfBlack(row.depositBank,''):"";
//			el.find('.car-num').html(global.defaultIfBlack(row.realName,'')+" "+global.defaultIfBlack(row.telphone,''));
//			el.find('.car-list').html(global.defaultIfBlack(row.attributes,'')+" 准驾车型"+global.defaultIfBlack(row.dlType,'')+qcType+cardName+depositBank);
//			el.find('.attr').html(row.attributes?row.attributes:"&nbsp;");
//			el.find('.b1').html(global.defaultIfBlack(row.dlEndDate,''));
//			el.find('.b2').html(global.defaultIfBlack(row.qcEndDate,''));
//			el.find('.edit').click(function(){
//				openDriver(row.driverInfoId);
//			});
			/*el.find('.to-info').click(function(){
				openDriverInfo(row.driverInfoId);
			});*/
			el.find('.realName').html(global.defaultIfBlack(row.realName,''));
			el.find('.telphone').html(global.defaultIfBlack(row.telphone,''));
			el.find('.dlType').html(global.defaultIfBlack(row.dlType,''));
			el.find('.attributes').html(global.defaultIfBlack(row.attributes,''));
			el.find('.qcType').html(global.defaultIfBlack(row.qcType,''));
			el.find('.check-box').data(row).val(row.driverInfoId);
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
			if(null!=pValue&&""!=pValue) {
				data[pName] = pValue;
			}
		});
		data.queryType=1;
		table.load(data);
		advSearch.hide();
	});*/
	
	$('.handle > .handle-btn > .del-driver').click(function(){
		var selected=$('td > .checked');
		if(selected.length>0) {
			if(confirm('确认删除？')){
				selected.each(function() {  
			    	var driverInfoId=$(this).val();
			    	$.ajax({
				        url: global.getServer() + '/admin/driver/removeDriver',
				        type: "POST",
				        data: {driverInfoId:driverInfoId},
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
		queryType:0,
		keyWord:$('.list-search').val()
	});
	
});