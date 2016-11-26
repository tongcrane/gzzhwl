var CBSDriver = function(options){
	this.opt = $.extend({}, options);
	this.columns = $('.column');
	this.bankColumns = $('.bank-column');
	this.imageColumns = $('.image-column');
	this.needColumns=$('.require');
	this.singleColumns = $('.single');
	this.inModify = false;
	this.imageList = [];
	
    var _this = this;
    
    this.init = function (options) {
    	var _this = this;
    	
    	$('.use-date').each(function(i, j){
            $(this).datetimepicker({
                timepicker:false,
                format:'Y-m-d',
                autoclose:true,
                todayHighlight:true,
                keyboardNavigation:false
            });
        });
        
        
        _this.needColumns.click(function(){
        	$(this).siblings('.tips').hide();
        });
        
        _this.needColumns.change(function(){
        	$(this).siblings('.tips').hide();
        });
        
        _this.currentStep = _this.opt.step;
        
        new CBSAutocomplete({
    		target : $('.autocomplete-ajax-belong'),
    		url : '/admin/staff/getDepartList',
    		method : 'get',
    	    valueField : 'departId',
    	    textField : 'name',
    	    //appendTo : '.search-content',
    	    onSelect : function(row){
    	    	$('.autocomplete-ajax-belong').val(row.name);
    	    	$('.autocomplete-ajax-belong').attr('data',row.departId);
    	    },
    	    onBlur:function() {
    	    	$('.autocomplete-ajax-belong').attr('data','');
    	    }
    	})
        
        new CBSAutocomplete({
    		target : $('.autocomplete-ajax-use'),
    		url : '/admin/staff/getDepartList',
    		method : 'get',
    	    valueField : 'departId',
    	    textField : 'name',
    	    //appendTo : '.search-content',
    	    onSelect : function(row){
    	    	$('.autocomplete-ajax-use').val(row.name);
    	    	$('.autocomplete-ajax-use').attr('data',row.departId);
    	    },
    	    onBlur:function() {
    	    	$('.autocomplete-ajax-use').attr('data','');
    	    }
    	})
        
        
        if (options && options.driverInfoId) {
            console.log('init driver data with id: ' + options.driverInfoId);
            var url = "/admin/driver/queryDriverDetail?driverInfoId=" + options.driverInfoId;
            var _this = this;
            $.get(global.server + url, function (msg) {
                if (msg && msg.status && msg.status.statusCode == global.status.success) {
                    var data = msg.data;
                    _this.fillContentFromJsonData(data);
                    _this.modify();
                    $('.car-information p').html('修改司机信息');
                    $('.title_val').html('修改司机信息');
                }
            }).done(function (msg) {
            	var data = msg.data;
            	$('select').multiselect({
					header : false,
					multiple : false,
					selectedList : 1,
					noneSelectedText: '请选择'
				});
            });
        } else {
            console.log('new driver');
            // 加载照片控件
            if (typeof CBSImage === "function") {
            	this.imageColumns.each(function(i, j){
                	var image=new CBSImage({
                		view:$(this),
                		category:$(this).data("column")
                	});
                	_this.imageList.push(image);
                });
            }
            $('select').multiselect({
				header : false,
				multiple : false,
				selectedList : 1,
				noneSelectedText: '请选择'
			});
        }
        
      
    }
    
    this.onBeforeClose = function(e){
    	console.log(e);
    	event.returnValue="确定离开当前页面吗？";
    }
    
    this.onClose = function(){
    	
    }
	this.init(options);
};

CBSDriver.prototype.doSave = function () {
    var _this = this;
    var valid = _this.validToPost();
    if(valid) {
    	var result = _this.getJsonDataFromContent();
        var url = null;
        if(_this.inModify){
        	url = "/admin/driver/updateDriver";
        	result.driverInfoId = _this.opt.driverInfoId;
        } else {
        	url = "/admin/driver/saveDriver";
        }
        $.ajax({
            url: global.server + url,
            type: "POST",
            data: result,
            success: function (msg) {
                if (msg && msg.status && msg.status.statusCode == global.status.success) {
                	var driverInfoId = null;
                	if(_this.inModify){
                		driverInfoId = _this.opt.driverInfoId;
                	} else {
                		driverInfoId = msg.data.driverInfoId;
                		
                	}
                	if(_this.currentStep == 1){
                		if(confirm('保存成功，是否前去下一页？')){
                			window.onbeforeunload = null;
                            window.onunload = null;
                			location.href = global.getContextPath() + '/addDriverInfoTwo.html?driverInfoId='+driverInfoId;
                		} else {
//                			window.onbeforeunload = _this.onBeforeClose;
//                		    window.onunload = _this.onClose;
                			window.onbeforeunload = null;
                            window.onunload = null;
                			window.close();			
                		}
                	} else {
                		alert("保存成功。");
//            			window.onbeforeunload = _this.onBeforeClose;
//            		    window.onunload = _this.onClose;
                		window.onbeforeunload = null;
                        window.onunload = null;
                		window.close();
                		window.opener.location.reload();
                	}
                	
                } else {
                	console.log(msg.status.errorMsg);
                	window.onbeforeunload = null;
                    window.onunload = null;
                    alert(msg.status.errorMsg);
                	//alert('保存失败，录入信息有误。');
                }
            }
        });
    } else {
    	
    }
    
};

CBSDriver.prototype.getJsonDataFromContent = function(){
	var data = {};
	this.columns.each(function(i, j){
		var _this = $(this);
		var pName = _this.data("column");
		var pValue = _this.val();
		data[pName] = pValue;
	});
	this.bankColumns.each(function(i, j){
		var _this = $(this);
		var pName = _this.data("column");
		var pValue = _this.val();
		data[pName] = pValue;
	});
	$.each(this.imageList,function(i, j){
		var result = j.postToServer('/admin/driver/imageUpload');
		if(result != null){
			var pName = result.column;
			var pValue = result.imageId;
			data[pName] = pValue;
		}
	});
	var belongDepartId=$('.autocomplete-ajax-belong').attr('data');
	var useDepartId=$('.autocomplete-ajax-use').attr('data');
	if(belongDepartId) {
		data.belongDepartId=belongDepartId;
	}
	if(useDepartId) {
		data.useDepartId=useDepartId;
	}
	return data;
};

CBSDriver.prototype.fillBankInfo = function () {
    var _this = this;
    var driverInfoId = _this.opt.driverInfoId;
    var url = '/admin/driver/queryDriverBankDetail';
    $.get(global.server + url,{"driverInfoId":driverInfoId}, function (msg) {
        if (msg && msg.status && msg.status.statusCode == global.status.success) {
        	var data = msg.data;
        	_this.bankColumns.each(function(i, j){
        		var _this = $(this);
        		var pName = _this.data("column");
        		var pValue = data[pName];
        		_this.val(pValue);
        	});
        }
    })
};

CBSDriver.prototype.fillContentFromJsonData = function (data) {
    if (!data) return;
    this.data = data;
    var _this = this;
    this.columns.each(function (i, j) {
    	var _this = $(this);
    	var pName = _this.data("column");
    	var pValue = data[pName];
    	_this.val(pValue);
    });
    // 加载照片控件
    if (typeof CBSImage === "function") {
    	this.imageColumns.each(function(i, j){
    		var category = $(this).data("column");
    		var imageId = data[category];
        	var image = new CBSImage({
        		view: $(this),
        		category: $(this).data("column"),
        		imageId: imageId
            });
        	_this.imageList.push(image);
        });
    }
    //部门
    $('.autocomplete-ajax-belong').val(data.belongDepartName);
    $('.autocomplete-ajax-belong').attr('data',data.belongDepartId);
    $('.autocomplete-ajax-use').val(data.useDepartName);
    $('.autocomplete-ajax-use').attr('data',data.useDepartId);
    _this.fillBankInfo();
};

CBSDriver.prototype.modify = function () {
    this.inModify = true;
};

CBSDriver.prototype.validDate=function() {
	var dlIssueDate=$('input[data-column="dlIssueDate"]').val();
	var dlStartDate=$('input[data-column="dlStartDate"]').val();
	var dlEndDate=$('input[data-column="dlEndDate"]').val();
	
	var qcIssueDate=$('input[data-column="qcIssueDate"]').val();
	var qcStartDate=$('input[data-column="qcStartDate"]').val();
	var qcEndDate=$('input[data-column="qcEndDate"]').val();
	
	var dl=moment(dlIssueDate,"YYYY-MM-DD");
	var d_s=moment(dlStartDate,"YYYY-MM-DD");
	var d_e=moment(dlEndDate,"YYYY-MM-DD");
	if(dlStartDate&&dlEndDate) {
		if(dlIssueDate) {
			var time_valid_dl=d_s.isBefore(dl);
			if(time_valid_dl) {
				alert("驾驶证有效期开始时间不能早于初次领证日期");
				return false;
			}
		}
		var time_valid_d=d_e.isAfter(d_s);
		if(!time_valid_d) {
			alert('驾驶证有效期结束时间不能早于开始时间');
			return false;
		}
	}
	
	if(qcStartDate&&qcEndDate) {
		var q_s=moment(qcStartDate,"YYYY-MM-DD");
		var q_e=moment(qcEndDate,"YYYY-MM-DD");
		if(qcIssueDate) {
			var qc=moment(qcIssueDate,"YYYY-MM-DD");
			var time_valid_qc=q_s.isBefore(qc);
			if(time_valid_qc) {
				alert("从业资格证有效期开始时间不能早于初次领证日期");
				return false;
			}
		}
		var time_valid_q=q_e.isAfter(q_s);
		if(!time_valid_q) {
			alert('从业资格证有效期结束时间不能早于开始时间');
			return false;
		}
	}
	return true;
}

CBSDriver.prototype.validKeyExpress=function() {
	var _this=this;
	var valid=true;
	
	var telphone=$("input[data-column='telphone']");
	var validTelphone = /^1[0-9]{10}$/;
	var validAllNum = /^[0-9]*$/;
	if($.trim(telphone.val())!=''){
		if(!validTelphone.test(telphone.val())) {
			valid=false;
			telphone.siblings('.tips').show();
		} else {
			telphone.siblings('.tips').hide();
		}
	}
	var cardNo = $("input[data-column='cardNo']");
	if($.trim(cardNo.val())!=''){
		if(!validAllNum.test(cardNo.val())) {
			valid=false;
			cardNo.siblings('.tips').show();
		} else {
			cardNo.siblings('.tips').hide();
		}
	} else {
		cardNo.siblings('.tips').hide();
	}
	var belongDepartId=$('.autocomplete-ajax-belong');
	var useDepartId=$('.autocomplete-ajax-use');
	if($.trim(belongDepartId.val())!='') {
		var belongDepartId=belongDepartId.attr('data');
		if(belongDepartId) {
	    	$('.autocomplete-ajax-belong').siblings('.tips').hide();
	    } else {
	    	valid=false;
	    	$('.autocomplete-ajax-belong').siblings('.tips').show();
	    }
	}
	if($.trim(useDepartId.val())!='') {
	    var useDepartId=useDepartId.attr('data');
	    if(useDepartId) {
	    	$('.autocomplete-ajax-use').siblings('.tips').hide();
	    } else {
	    	valid=false;
	    	$('.autocomplete-ajax-use').siblings('.tips').show();
	    }
	}
	var bankName = $("input[data-column='bankName']");
	if($.trim(bankName.val())!=''){
		if(!global.isChinese(bankName.val())) {
			valid=false;
			bankName.siblings('.tips').show();
		} else {
			bankName.siblings('.tips').hide();
		}
	} else {
		bankName.siblings('.tips').hide();
	}
	var depositBank = $("input[data-column='depositBank']");
	if($.trim(depositBank.val())!=''){
		if(!global.isChinese(depositBank.val())) {
			valid=false;
			depositBank.siblings('.tips').show();
		} else {
			depositBank.siblings('.tips').hide();
		}
	} else {
		depositBank.siblings('.tips').hide();
	}
	var cardName = $("input[data-column='cardName']");
	if($.trim(cardName.val())!=''){
		if(!global.isChinese(cardName.val())) {
			valid=false;
			cardName.siblings('.tips').show();
		} else {
			cardName.siblings('.tips').hide();
		}
	} else {
		cardName.siblings('.tips').hide();
	}
	return valid;
}

CBSDriver.prototype.validToPost = function () {
    var _this = this;
    var valid = true;
    this.needColumns.each(function(i, j){
		var _this = $(this);
		var pValue = $.trim(_this.val());
		if( pValue=='' || pValue == null) {
			valid = false;
			_this.siblings('.tips').show();
		}else{
			_this.siblings('.tips').hide();
		}
	});
    if(valid){
    	var data = {};
    	this.singleColumns.each(function(i, j){
    		var _this = $(this);
    		var pName = _this.data("column");
    		var pValue = _this.val();
    		if(pValue) {
    			data[pName]=pValue;
    		}
    	});
    	var valid_date=_this.validDate();
    	if(valid_date) {
    		var valid_key=_this.validKeyExpress();
    	    if(valid_key) {
    	    	if(!$.isEmptyObject(data)){
            		if (_this.opt.driverInfoId) {
                		data.driverInfoId = _this.opt.driverInfoId;
                	}
            		var url = '/admin/driver/driverUniqueValidate';
                	$.ajax({
                        url: global.server + url,
                        type: "post",
                        async : false,
                        data: data,
                        success: function (msg) {
                            if (msg && msg.status && msg.status.statusCode == global.status.success) {
                            	var hasIdno = msg.data.hasIdno;
                            	var hasQcNo = msg.data.hasQcNo;
                            	if(hasIdno){
                            		alert("该司机的身份证信息已存在。");
                            		valid = false;
                            	}
                            	if(hasQcNo){
                            		alert("该司机的从业资格证信息已存在。");
                            		valid = false;
                            	}
                            } 
                        }
                    });
            	}
    	    } else {
    	    	valid=false;
    	    }
    	} else {
    		valid=false;
    	}
    }
    return valid;
};