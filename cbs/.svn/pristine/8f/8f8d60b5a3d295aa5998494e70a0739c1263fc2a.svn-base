$(function(){
//    $('.search-content').bind('click', function(e){
//        return false;
//    });
	var onBodyDown = function(event){
    	if(($(event.target).parents(".search-content").length>0)){
    		
    	} else {
    		advSearch.hide();
    	}
    };
    
    var advSearch = {
        show : function() {
            $('.search-content').show(10, function(){
//                $('body').one('click', function(){
//                    advSearch.hide();
//                });
            	$("body").bind("mousedown", onBodyDown);
            });
        },
        hide : function() {
        	$("body").unbind("mousedown", onBodyDown);
            $('.search-content').hide();
//            $('body').unbind();
        }
    }
	
    var table = new CBSTable({
        page : $('.handle-page'),
        content : $('.handle-content > table > tbody'),
        url : '/admin/trip/pageTripList',
        method  : 'GET',
        pageNumber : 0,
        pageSize : 10,
        rowRender : function(index, row){
        	var el=$('<tr>\
					<td><b class="index to-info"></b></td>\
					<td class="loadNo to-info"></td>\
					<td class="plateNumber to-info"></td>\
					<td class="realName to-info"></td>\
					<td class="telphone to-info"></td>\
					<td class="line to-info"></td>\
					<td class="statusCn to-info"></td>\
					<td class="needArriveTime to-info"></td>\
				</tr>');
            var $index=index+1;
            var realName='';
            var telphone='';
            var driverList=row.driverList;
            if(driverList.length>0) {
            	realName=driverList[0].realName;
            	telphone=driverList[0].telphone;
            }
            var needArriveTime=row.needArriveTime;
            if(needArriveTime) {
            	needArriveTime=moment(needArriveTime).format("YYYY-MM-DD HH:mm");
            }
            el.find(".index").html($index);
            el.find(".loadNo").html(row.loadNo);
            el.find(".plateNumber").html(row.plateNumber);
            el.find(".realName").html(realName);
            el.find(".telphone").html(telphone);
            el.find(".line").html(row.startCodeCCn+"-"+row.endCodeCCn);
            el.find(".statusCn").html(row.statusCn);
            el.find(".needArriveTime").html(needArriveTime);
            el.find(".to-info").click(function(){
                openTrip(row.loadId);
            });
            /*var toDoList=row.toDoList;
            if(toDoList!=null) {
            	$.each(toDoList,function(i,j){
                	if(j.code=='02') {
                		el.find('span').removeClass('.edit-nobg').addClass('edit').addClass('to-info');
                		el.find('.edit').click(function(){
                			editOrder(row.orderId);
                		});
                	}
                });
            }*/
            return el;
        },
        onBeforeLoad : function(param){
            return param.queryType != null;;
        }
    });
    //详情
    var openTrip = function(loadId){
        var url = global.getContextPath() + '/checkBusFeed.html?loadId='+loadId;
        var body = document.getElementsByTagName("body")[0];
        var el = document.createElement("a");
        body.appendChild(el);
        el.href = url;
        el.target = '_blank';
        el.click();
        body.removeChild(el);
    }
    
    //搜索/高级搜索
    $('#btn_search').click(function(){
        table.load({
            queryType:0,
            keyWord:$('.list-search').val()
        });
    });
    /*$('.advance-sea').click(function(event) {
        advSearch.show();
    });

    $('.sea-close').click(function(event) {
        advSearch.hide();
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
    });*/
    
    var sort='01';
    var tripStatus='';
    $('.sortBtn').click(function(){
    	if(sort=='01') {
    		sort='00';
    	} else {
    		sort='01';
    	}
    	table.load({
            queryType:0,
            keyWord:$('.list-search').val(),
            status:tripStatus,
            sort:sort
        });
    });

    table.load({
        queryType:0
    });
    
    //状态筛选
    var trip_status=$('.trip_status li');
	$.each(trip_status,function(){
		$(this).click(function(){
			table.load({
	    		queryType:0,
	    		keyWord:$('.list-search').val(),
	    		sort:sort,
	    		status:$(this).attr('value')
	    	});
			tripStatus=$(this).attr('value');
		});
	});

});