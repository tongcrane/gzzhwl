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
	
	/*var editPermission=function(staffId) {
		var url = global.getContextPath() + '/addPermissionInfo.html?staffId='+staffId;
    	var body = document.getElementsByTagName("body")[0];
    	var el = document.createElement("a");
    	body.appendChild(el);
    	el.href = url;
    	el.target = '_blank';
    	el.click();
    	body.removeChild(el);
	}*/
	
	/*var openPermission = function(staffId){
    	var url = global.getContextPath() + '/checkPermissionInfo.html?staffId='+staffId;
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
		//url : '/admin/staff/getPermissionList',
		url:'/admin/staff/pageStaff',
		method  : 'get',
		pageNumber : 0,
		pageSize : 10,
		rowRender : function(index, row){
			var el = $('<tr>\
					<td class="realName to-info"></td>\
					<td class="number to-info"></td>\
					<td class="telphone to-info"></td>\
					<td class="departName to-info"></td>\
					<td class="departInfo to-info"></td>\
				</tr>');
			
//			el.find('.per-info').html(global.defaultIfBlack(row.realName,'')+" "+global.defaultIfBlack(row.number,''));
//			el.find('.per-list').html(global.defaultIfBlack(row.telphone,'')+" "+global.defaultIfBlack(row.departName,'')+str);
//			el.find('.edit').click(function(){
//				editPermission(row.staffId);
//			});
			/*el.find('.to-info').click(function(){
				openPermission(row.staffId);
			});*/
			var departInfo=row.departInfo;
			var str="";
			if(departInfo.length>0) {
				$.each(departInfo,function(i,j){
					if(departInfo.length==0) {
						str=j.departName;
					}
					if(i==departInfo.length-1) {
						str=str+j.departName;
					} else {
						str=str+j.departName+'、';
					}
				});
			}
			el.find('.realName').html(global.defaultIfBlack(row.realName,''));
			el.find('.number').html(global.defaultIfBlack(row.number,''));
			el.find('.telphone').html(global.defaultIfBlack(row.telphone,''));
			el.find('.departName').html(global.defaultIfBlack(row.departName,''));
			el.find('.departInfo').html(str);
			return el;
		},
		onBeforeLoad : function(param){
			return param.queryType != null;
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
	
	table.load({
		queryType:0,
		keyword:$('.list-search').val()
	});

});