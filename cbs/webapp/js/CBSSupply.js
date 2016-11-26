var CBSSupply = function(options){
	this.opt = $.extend({}, options);
	this.columns = $('.column');
	this.supplyColumns=$('.supply_column');
	this.bankColumns=$('.bank_column');
	this.needColumns=$('.required');
	this.pcdColumns = $('.pcd-column');
	this.inModify = false;
	this.pcdList = [];
	
    var _this = this;
    
    this.init = function (options) {
    	var _this = this;
        
    	_this.needColumns.click(function(){
        	$(this).siblings('.tips').hide();
        });
        
        _this.needColumns.change(function(){
        	$(this).siblings('.tips').hide();
        });
        
        
        if (options && options.supplyId) {
            console.log('init supply data with id: ' + options.supplyId);
            var url = "/admin/supply/getSupplyDetail?supplyId=" + options.supplyId;
            var _this = this;
            $.get(global.server + url, function (msg) {
                if (msg && msg.status && msg.status.statusCode == global.status.success) {
                    var data = msg.data;
                    _this.fillSupplyContentFromJsonData(data.supplyInfo);
                    _this.fillBankContentFromJsonData(data.bankInfo);
                    _this.modify();
                    //$('.car-information p').html('修改车辆信息');
                    //$('.title_val').html('修改车辆信息');
                } else {
                	alert("页面参数错误");
                	window.close();
                }
            }).done(function (msg) {
            	var data = msg.data;
            	$('select').multiselect({
					header : false,
					multiple : false,
					selectedList : 1,
					noneSelectedText: '请选择'
				});
            	 _this.pcdColumns.each(function(i, j){
             		$(this).append('<select class="pcd-select-p"></select>');
             		$(this).append('<select class="pcd-select-c"></select>');
             		$(this).append('<select class="pcd-select-d"></select>');
             		var category = $(this).data('column');
             		var value = data.supplyInfo[category];
             		var pcd = new CBSPcdSelect({width:100,category:category,initCode:value}, $(this));
             		_this.pcdList.push(pcd);
             	});
            });
        } else {
            console.log('new supply');
            _this.pcdColumns.each(function(i, j){
        		$(this).append('<select class="pcd-select-p"></select>');
        		$(this).append('<select class="pcd-select-c"></select>');
        		$(this).append('<select class="pcd-select-d"></select>');
        		var category = $(this).data('column');
        		var pcd = new CBSPcdSelect({width:100,category:category}, $(this));
        		_this.pcdList.push(pcd);
        	});
            $('select').multiselect({
				header : false,
				multiple : false,
				selectedList : 1,
				noneSelectedText: '请选择'
			});
        };
        
    };
    
	this.init(options);
	
};

CBSSupply.prototype.doSave = function () {
    var _this = this;
    var valid = _this.validToPost();
    if(valid){
    	var result = _this.getJsonDataFromContent();
    	var type=$("input[name='suportType']:checked").val();
    	result.type=type;
        var url = null;
        if(_this.inModify){
        	url = "/admin/supply/updateSupply";
        	result.supplyId = _this.opt.supplyId;
        } else {
        	url = "/admin/supply/saveSupply";
        }
        $.ajax({
            url: global.server + url,
            type: "POST",
            data: result,
            success: function (msg) {
                if (msg && msg.status && msg.status.statusCode == global.status.success) {
                	alert("保存成功。");
            		window.close();
            		window.opener.location.reload();
                	
                } else {
                	console.log(msg.status.errorMsg);
                    alert(msg.status.errorMsg);
                	//alert('保存失败，录入信息有误。');
                }
            }
        });
    } else {
    	
    }
};

CBSSupply.prototype.getJsonDataFromContent = function(){
	var data = {};
	this.columns.each(function(i, j){
		var _this = $(this);
		var pName = _this.data("column");
		var pValue = _this.val();
		data[pName] = pValue;
	});
	$.each(this.pcdList,function(i, j){
		var result = j.getValue();
		var pName = result.category;
		var pValue = result.code;
		data[pName] = pValue;
	});
	return data;
};

CBSSupply.prototype.fillSupplyContentFromJsonData = function (data) {
    if (!data) return;
    this.data = data;
    var _this = this;
    this.supplyColumns.each(function (i, j) {
    	var _this = $(this);
    	var pName = _this.data("column");
    	var pValue = data[pName];
    	_this.val(pValue);
    });
    if(data.type=='01') {
    	$('.toggle_p_c').html('身份证号码');
    	$('.people').attr('checked','checked');
    } else if(data.type=='02') {
    	$('.toggle_p_c').html('税务登记证号');
    	$('.company').attr('checked','checked');
    }
};

CBSSupply.prototype.fillBankContentFromJsonData = function (data) {
    if (!data) return;
    this.data = data;
    var _this = this;
    this.bankColumns.each(function (i, j) {
    	var _this = $(this);
    	var pName = _this.data("column");
    	var pValue = data[pName];
    	_this.val(pValue);
    });
};

CBSSupply.prototype.modify = function () {
    this.inModify = true;
};

CBSSupply.prototype.validKeyExpress=function() {
	var _this=this;
	var valid = true;
	
	var type=$("input[name='suportType']:checked").val();
	var taxRegCode=$("input[data-column='taxRegCode']");
	var telphone=$("input[data-column='mobile']");
	var validTelphone = /^1[0-9]{10}$/;
	var validAllNum = /^[0-9]*$/;
	var fullName=$('input[data-column="fullName"]');
	if($.trim(fullName.val())!='') {
		if(!global.isChinese(fullName.val())) {
			valid=false;
			fullName.siblings('.tips').show();
		} else {
			fullName.siblings('.tips').hide();
		}
	} else {
		fullName.siblings('.tips').hide();
	}
	var contactName=$('input[data-column="contactName"]');
	if($.trim(contactName.val())!='') {
		if(!global.isChinese(contactName.val())) {
			valid=false;
			contactName.siblings('.tips').show();
		} else {
			contactName.siblings('.tips').hide();
		}
	} else {
		contactName.siblings('.tips').hide();
	}
	if(type=='01') {
		if($.trim(taxRegCode.val())!='') {
			var valid_idno=IdCardNoUtil.checkIdCardNo(taxRegCode.val());
			if(valid_idno) {
				taxRegCode.siblings('.tips').hide();
			} else {
				valid=false;
				taxRegCode.siblings('.tips').html('请输入正确的身份证号');
				taxRegCode.siblings('.tips').show();
			}
		}
	} else if(type=='02') {
		if($.trim(taxRegCode.val())!='') {
			if(!validAllNum.test(taxRegCode.val())) {
				valid=false;
				taxRegCode.siblings('.tips').html('请输入正确的税务登记号');
				taxRegCode.siblings('.tips').show();
			} else {
				if(taxRegCode.val().length<20) {
					valid=false;
					taxRegCode.siblings('.tips').html('请输入正确的税务登记号');
					taxRegCode.siblings('.tips').show();
				} else {
					taxRegCode.siblings('.tips').hide();
				}
			}
		}
	}
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

CBSSupply.prototype.validToPost = function () {
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
		
		if(_this.data('column') == 'address'){
			address = pValue;
		}
	});
    
    $.each(this.pcdList,function(i, j){
		var result = j.getValue();
		var _p=j.pSelect.getValue();
		var _c=j.cSelect.getValue();
		var _d=j.dSelect.getValue();
		if(_p==''||_c==''||_d==''){
			valid = false;
			$('div[data-column="'+ result.category+'"]').find('.tips').show();
		} else {
			if(address != ''){
				$('div[data-column="'+ result.category+'"]').find('.tips').hide();
			}
		}
	});
    
    
    if(valid){
    	return _this.validKeyExpress();
    }
    return valid;
};