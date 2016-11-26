$(function() {
	
	/*var editHr=function(staffId) {
		var url = global.getContextPath() + '/addHRInfo.html?staffId='+staffId;
    	var body = document.getElementsByTagName("body")[0];
    	var el = document.createElement("a");
    	body.appendChild(el);
    	el.href = url;
    	el.target = '_blank';
    	el.click();
    	body.removeChild(el);
	}*/
	
	/*var openHr = function(staffId){
    	var url = global.getContextPath() + '/checkHRInfo.html?staffId='+staffId;
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
		url : '/admin/staff/getStaffList',
		method  : 'GET',
		pageNumber : 0,
		pageSize : 10,
		rowRender : function(index, row){
			var el = $('<tr>\
					<td><span class="check-box check"></span></td>\
					<td class="realName to-info"></td>\
					<td class="number to-info"></td>\
					<td class="telphone to-info"></td>\
					<td class="departName to-info"></td>\
					<td class="position to-info"></td>\
					<td class="email to-info"></td>\
				</tr>');
			
//			var email=row.email;
//			if(email) {
//				email=" - 邮箱："+email;
//			} else {
//				email='';
//			}
//			el.find('.staff-info').html(global.defaultIfBlack(row.realName,'')+" "+global.defaultIfBlack(row.number,''));
//			el.find('.staff-list').html(global.defaultIfBlack(row.telphone,'')+"; "+global.defaultIfBlack(row.departName,'')+": "+global.defaultIfBlack(row.position,'')+email);
//			el.find('.edit').click(function(){
//				editHr(row.staffId);
//			});
			/*el.find('.to-info').click(function(){
				openHr(row.staffId);
			});*/
			el.find('.realName').html(global.defaultIfBlack(row.realName,''));
			el.find('.number').html(global.defaultIfBlack(row.number,''));
			el.find('.telphone').html(global.defaultIfBlack(row.telphone,''));
			el.find('.departName').html(global.defaultIfBlack(row.departName,''));
			el.find('.position').html(global.defaultIfBlack(row.position,''));
			el.find('.email').html(global.defaultIfBlack(row.email,''));
			el.find('.check-box').data(row).val(row.staffId);
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
			keyword:$('.list-search').val()
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
	
	$('.handle > .handle-btn > .del-human').click(function(){
		var selected=$('td > .checked');
		if(selected.length>0) {
			if(confirm('确认删除？')){
				selected.each(function() {  
			    	var staffId=$(this).val();
			    	$.ajax({
				        url: global.getServer() + '/admin/staff/delStaff',
				        type: "POST",
				        data: {staffId:staffId},
				        success: function (msg) {
				            if (msg && msg.status && msg.status.statusCode == global.status.success) {
				            	table.reload();
				            } else {
				            	alert('无法删除当前用户');
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