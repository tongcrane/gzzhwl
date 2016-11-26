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
	
	var editHr=function(staffId) {
		var url = global.getContextPath() + '/addHRInfo.html?staffId='+staffId;
    	var body = document.getElementsByTagName("body")[0];
    	var el = document.createElement("a");
    	body.appendChild(el);
    	el.href = url;
    	el.target = '_blank';
    	el.click();
    	body.removeChild(el);
	}
	
	var openHr = function(staffId){
    	var url = global.getContextPath() + '/checkHRInfo.html?staffId='+staffId;
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
		url : '/admin/staff/getStaffList',
		method  : 'GET',
		pageNumber : 0,
		pageSize : 10,
		rowRender : function(index, row){
			var el = $('<tr> \
							<td width="30" class="f-width"> \
								<input class="check-box" type="checkbox"/>\
							</td>\
							<td width="30">\
								<a href="javascript:void(0);" class="edit"></a>\
							</td>\
							<td width="210" class="name_num staff-info to-info">\
							</td>\
							<td width="710" class="main staff-list to-info">\
							</td>\
						</tr>');
			var email=row.email;
			if(email) {
				email=" - 邮箱："+email;
			} else {
				email='';
			}
			el.find('.staff-info').html(global.defaultIfBlack(row.realName,'')+" "+global.defaultIfBlack(row.number,''));
			el.find('.staff-list').html(global.defaultIfBlack(row.telphone,'')+"; "+global.defaultIfBlack(row.departName,'')+": "+global.defaultIfBlack(row.position,'')+email);
			el.find('.edit').click(function(){
				editHr(row.staffId);
			});
			el.find('.to-info').click(function(){
				openHr(row.staffId);
			});
			el.find('.f-width > .check-box').data(row).val(row.staffId);
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
			keyword:$('.list-search').val()
		});
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
	
	$('.handle-data>.handle-delete').click(function(){
		var selected=$('.f-width > .check-box:checked');
		if(selected.length>0) {
			if(confirm('确认删除？')){
				$('.f-width > .check-box:checked').each(function() {  
			    	var staffId=$(this).val();
			    	$.ajax({
				        url: global.server + '/admin/staff/delStaff',
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