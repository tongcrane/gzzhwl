$(function(){
    /*var openCustom = function(orderId){
        var url = global.getContextPath() + '/checkBusOverView.html?orderId='+orderId;
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
        url : '/admin/order/pageOrderList',
        method  : 'GET',
        pageNumber : 0,
        pageSize : 10,
        rowRender : function(index, row){
            var el = $('<tr>\
        			<td class="orderNo to-info"></td>\
        			<td class="customerName to-info"></td>\
        			<td class="consignNo to-info"></td>\
        			<td class="loadNo to-info"></td>\
        			<td class="plateNumber to-info"></td>\
        			<td class="statusCn to-info"></td>\
        			<td class="text-gray createdTime to-info"></td>\
        		</tr>');
            
            var createdTime=row.createdTime;
            if(createdTime) {
            	createdTime=moment(createdTime).format("YYYY-MM-DD HH:mm");
            }
            el.find(".orderNo").html(row.orderNo);
            el.find(".customerName").html(row.customerName);
            el.find(".consignNo").html(row.consignNo);
            el.find(".loadNo").html(row.loadNo);
            el.find(".plateNumber").html(row.plateNumber);
            el.find(".statusCn").html(row.statusCn);
            el.find(".createdTime").html(createdTime);
            /*el.find("td").click(function(){
                openCustom(row.orderId);
            });*/
            return el;
        },
        onBeforeLoad : function(param){
            return param.queryType != null;
        }
    });
    $('#btn_search').click(function(){
        table.load({
            queryType:0,
            keyWord:$('.list-search').val()
        });
    });
    
    var sort='01';
    $('.bus_select').click(function(){
    	if(sort=='01') {
    		sort='00';
    	} else {
    		sort='01';
    	}
    	table.load({
            queryType:0,
            keyWord:$('.list-search').val(),
            sort:sort
        });
    });

    table.load({
        queryType:0
    });
});