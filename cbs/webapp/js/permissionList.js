$(function() {
	$('.search-content').bind('click', function(e){
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
	}

	// 点击高级搜索，搜索框显示
	$('.advance-sea').click(function(event) {
		advSearch.show();
	});

	$('.sea-close').click(function(event) {
		advSearch.hide();
	});
	
	var editPermission=function(staffId) {
		var url = global.getContextPath() + '/addPermissionInfo.html?staffId='+staffId;
    	var body = document.getElementsByTagName("body")[0];
    	var el = document.createElement("a");
    	body.appendChild(el);
    	el.href = url;
    	el.target = '_blank';
    	el.click();
    	body.removeChild(el);
	}
	
	var openPermission = function(staffId){
    	var url = global.getContextPath() + '/checkPermissionInfo.html?staffId='+staffId;
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
		//url : '/admin/staff/getPermissionList',
		url:'/admin/staff/pageStaff',
		method  : 'get',
		pageNumber : 0,
		pageSize : 10,
		rowRender : function(index, row){
			var el = $('<tr>\
							<td width="30">\
								<a href="javascript:void(0);" class="edit"></a>\
							</td>\
							<td width="210">\
								<div class="name_num per-info to-info"></div>\
							</td>\
							<td width="710">\
								<div class="main per-list to-info">\
								</div>\
							</td>\
						</tr>');
			var departInfo=row.departInfo;
			var str="";
			if(departInfo.length>0) {
				str=" - 所属数据权限部门：";
				$.each(departInfo,function(i,j){
					str+=j.departName+";";
				});
			}
			el.find('.per-info').html(global.defaultIfBlack(row.realName,'')+" "+global.defaultIfBlack(row.number,''));
			el.find('.per-list').html(global.defaultIfBlack(row.telphone,'')+" "+global.defaultIfBlack(row.departName,'')+str);
			el.find('.edit').click(function(){
				editPermission(row.staffId);
			});
			el.find('.to-info').click(function(){
				openPermission(row.staffId);
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
	
	/*$('.handle-data>.handle-delete').click(function(){
		var selected=$('.f-width > .check-box:checked');
		if(selected.length>0) {
			if(confirm('确认删除？')){
				$('.f-width > .check-box:checked').each(function() {  
			    	var customerId=$(this).val();
			    	$.ajax({
				        url: global.server + '/admin/customer/removeCustomer',
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
	});*/
	
	table.load({
		queryType:0,
		keyword:$('.list-search').val()
	});

});