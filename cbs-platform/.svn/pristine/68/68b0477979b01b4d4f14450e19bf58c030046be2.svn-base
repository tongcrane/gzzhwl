$(function(){
	
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
    
    /*$('.use-date').each(function(i, j){
		var time = $(this).data('time');
		if(time){
			$(this).datetimepicker({
        		timepicker:true,
        		format:'Y-m-d H:i',
        		autoclose:true,
        		todayHighlight:true,
        		keyboardNavigation:false
    		});
		} else {
			$(this).datetimepicker({
        		timepicker:false,
        		format:'Y-m-d',
        		autoclose:true,
        		todayHighlight:true,
        		keyboardNavigation:false
    		});
		}
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
    
    var table = new CBSTable({
        page : $('.handle-page'),
        content : $('.handle-content > table > tbody'),
        url : '/admin/lend/getLendPage',
        method  : 'GET',
        pageNumber : 0,
        pageSize : 10,
        rowRender : function(index, row){
            var el=$('<tr>\
			<td class="lendNo to-info"></td>\
			<td class="departName to-info"></td>\
			<td class="plateNumber to-info"></td>\
			<td class="line to-info"></td>\
			<td class="statusCn to-info"></td>\
			<td class="text-gray startTime to-info"></td>\
			<td class="text-gray realReturnedTime to-info"></td>\
            </tr>');
            
            var realReturnedTime=row.realReturnedTime;
            if(realReturnedTime) {
            	realReturnedTime=moment(realReturnedTime).format("YYYY-MM-DD");
            }
            el.find(".lendNo").html(row.lendNo);
            el.find(".departName").html(row.departName);
            el.find(".plateNumber").html(row.plateNumber);
            el.find(".line").html(row.departureCodeCn?row.departureCodeCn+"-"+row.destinationCodeCn:"");
            el.find(".statusCn").html(row.statusCn);
            el.find(".startTime").html(row.startTime);
            el.find(".realReturnedTime").html(realReturnedTime);
//            el.find(".cancel").attr("lendId",row.lendId);
            /*el.find(".to-info").click(function(){
                openBorrow(row.lendId);
            });*/
            /*el.find('.check_box').click(function(){
                var flag=$(this).attr('checked');
                if(flag===undefined) {
                    $(this).attr('checked','checked');
                    $("tr:eq("+(index+1)+")").css('background-color','#FBEDC1');
                } else {
                    $(this).removeAttr('checked');
                    $("tr:eq("+(index+1)+")").removeAttr("style");
                }
            });*/
            return el;
        },
        onBeforeLoad : function(param){
//        	$('.all-check').removeClass('checked');
            return param.queryType != null;;
        }
    });
    //详情
    /*var openBorrow = function(lendId){
        var url = global.getContextPath() + '/checkBorrowCar.html?lendId='+lendId;
        var body = document.getElementsByTagName("body")[0];
        var el = document.createElement("a");
        body.appendChild(el);
        el.href = url;
        el.target = '_blank';
        el.click();
        body.removeChild(el);
    }*/
    //全选
    /*$('.all_check').click(function(){
    	var check_box=$('.check_box');
    	var flag=$(this).is(':checked');
    	if(flag){
	          $.each(check_box,function(i,j){
	          	if(!$(j).attr('checked')){
	          		$(this).click();
	          	}
	          });
    	} else {
    		$.each(check_box,function(i,j){
            	if($(j).attr('checked')){
            		$(this).click();
            	}
            });
    	}
    });*/
    //收回车辆
    /*var returnCar = function(){
    	var lendIds = [];
    	$('.f-width > .cancel:checked').each(function () {
            var lendId = $(this).attr("lendId");
            lendIds.push(lendId);
        });
    	var lendId = lendIds.join(",");
    	$.ajax({
            url: global.server + '/admin/lend/return',
            type: "POST",
            data: {lendIds: lendId},
            success: function (msg) {
                if (msg && msg.status && msg.status.statusCode == global.status.success) {
                	alert("车辆已归还");
                    table.load({
                        queryType: 0
                    });
                } else {
                	alert("有车辆不允许归还");
                }
            }
        });
    	
    }
    
    $('.handle-data>.handle-export').click(function() {
        var selected = $('.f-width > .cancel:checked');
        var status = $(".f-width>.cancel").attr("status");
        if (selected.length > 0) {
        	var options = {
    			data : {},
    			text : '是否归还车辆',
    			callback:function(data){
    				returnCar();
    			}
    		}
    		new CBSConfirm(options);
        } else {
            alert("请选择要归还的车辆");
        }
    });*/
    
    
    /*$('.handle-data>.handle-export').click(function(){
		var selected=$('.f-width > .cancel:checked');
		if(selected.length>0) {
			if(confirm('确认归还车辆?')){
				$('.f-width > .cancel:checked').each(function() {  
			    	var lendId=$(this).attr('lendId');
			    	console.log(lendId)
			    	$.ajax({
				        url: global.server + '/admin/lend/return',
				        type: "POST",
				        data: {lendId:lendId},
				        success: function (msg) {
				            if (msg && msg.status && msg.status.statusCode == global.status.success) {
				            	table.reload();
				            } else {
				            	alert(msg.status.errorMsg)
				            }
				        }
				    });
			    });
			}
		} else {
			alert("请选择要归还的车辆");
		}
	});*/
    
    //搜索
    $('#btn_search').click(function(){
        table.load({
            queryType:0,
            queryContent:$('.list-search').val()
        });
    });
    //高级搜索
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
    
    var start_time_sort='01';
    var real_returned_time_sort="01";
    var useStatus='';
    $('.start_time_sort').click(function(){
    	if(start_time_sort=='01') {
    		start_time_sort='00';
    		$(this).css('background-image','url('+global.getContextPath()+'/img/icon/time_up.png)');
    	} else {
    		start_time_sort='01';
    		$(this).css('background-image','url('+global.getContextPath()+'/img/icon/time_down.png)');
    	}
    	table.load({
            queryType:0,
            keyWord:$('.list-search').val(),
            status:useStatus,
            sortField:'startTime',
            sort:start_time_sort
        });
    });
    
    $('.real_returned_time_sort').click(function(){
    	if(real_returned_time_sort=='01') {
    		real_returned_time_sort='00';
    		$(this).css('background-image','url('+global.getContextPath()+'/img/icon/time_up.png)');
    	} else {
    		real_returned_time_sort='01';
    		$(this).css('background-image','url('+global.getContextPath()+'/img/icon/time_down.png)');
    	}
    	table.load({
            queryType:0,
            keyWord:$('.list-search').val(),
            status:useStatus,
            sortField:'realReturnedTime',
            sort:real_returned_time_sort
        });
    });

    table.load({
        queryType:0
    });
    
    //状态筛选
    /*var use_status=$('.use_status li');
	$.each(use_status,function(){
		$(this).click(function(){
			table.load({
	    		queryType:0,
	    		keyWord:$('.list-search').val(),
	    		status:$(this).attr('value')
	    	});
			useStatus=$(this).attr('value');
		});
	});*/
	
});