var CBSHeadMenu = function(options){
	this.opt = $.extend({}, options);
	var _this = this;
	
	var open = function(href){
    	var body = document.getElementsByTagName("body")[0];
    	var el = document.createElement("a");
    	body.appendChild(el);
    	el.href = href;
    	el.target = '_blank';
    	el.click();
    	body.removeChild(el);
    }
	
	this.getMenu = function(cid){
		var _this = this;
		var data = global.getMenu();
		_this.show(data);
//		var cid = 1;
		_this.leftMenu = new CBSLeftMenu(cid);
		
	}
	
	this.init = function(options){
		var _this = this;
		var spm = global.QueryString.spm;
		var category = undefined;
		if(spm){
			category = spm.split(".")[0];
		}
		if(!isNaN(category)){
			_this.getMenu(category);
		} else {
			var first = _this.getFirstCategory();
	     	var firstMenu = _this.getFirstMenu(first.menuId);
	     	_this.toFirst(firstMenu, first.menuId);
		}
		var parent = _this.opt.target;
		parent.find('.content-detail').find("li[category=category_"+category+"]").addClass('active');
	};
	
	this.getFirstCategory = function(){
		var data = global.getMenu();
		var first = [];
		$.each(data,function(i,v){
			first = v;
			return false;
		});
		return first;
	};
	
	this.getFirstMenu = function(category){
		var _this = this;
		var data = global.getMenu();
		var array = [];
		$.each(data,function(i,v){
			if(v.menuId == category){
				array = v.subMenu;
				return false;
			}
		});
		var first = {};
		$.each(array,function(i, j){
			if(j.isLeaf == '1'){
				first = j;
				return false;
			} else {
				var _subMenu = j.subMenu;
				$.each(_subMenu,function(i, j){
					if(j.isLeaf == '1'){
						first = j;
						return false;
					} 
				});
				return false;
			}
		});
		return first;
	};
	
	this.show = function(data){
		var _this = this;
		if(data){
			var parent = _this.opt.target;
			var container = parent.find('.content-detail');
			var context = global.getContextPath();
	        $.each(data,function(i, v){
	            var $menuLi= $('<li><span></span><a href="javascript:void(0);"></a></li>');
	            $menuLi.find("span").css("background-image", "url("+context+"/img/menu" + v.menuId + ".png)");
//	            $menuLi.find("span").css("background-position", "0 0");
	            $menuLi.find("a").html(v.menuName);
	            $menuLi.click(function(){
	            	store.remove("current_menu_category");
	            	store("current_menu_category", JSON.stringify(v.menuId));
	            	var firstMenu = _this.getFirstMenu(v.menuId);
	            	_this.toFirst(firstMenu, v.menuId);
	            });
	            $menuLi.hover(function(){
	            	$menuLi.siblings().removeClass('current');
	                $menuLi.addClass('current');
	            },function(){
	            	$menuLi.removeClass('current');
	            });
	            $menuLi.attr('category',"category_"+v.menuId);
	            container.append($menuLi);
	        })
		}
	};
	
	this.toFirst = function(menu, cid){
		if(!$.isEmptyObject(menu)){
			var href = menu.menuPath;
			var ref = menu.menuId;
			if(href != ''){
//				console.log(href+"?spm="+cid+"."+ref);
//				console.log(global.getContextPath())
				var url = global.getContextPath() + href+"?spm="+cid+"."+ref;
				if(ref == '152'){//查看地图
					open(url);
				} else {
					location.href = url;
				}
			}
		}
	};
	
	
	this.init(options);
};


var CBSLeftMenu = function(cid){
	
	this.container = $('.list-info-content > .list-Info-left')
	
	this.init = function(cid){
		var _this = this;
		var data = _this.getMenu(cid);
		_this.show(data, cid);
		_this.bindEvent();
	}
	
	this.bindEvent = function(){
		var _this = this;
		
		_this.container.find('.title-icon').click(function(){
			$(this).next().toggle(200);
		});
		
		_this.container.find(".left_menu").click(function(){
			var href = $(this).data('href');
			var ref = $(this).attr('ref');
			var cid = $(this).attr('cid');
			if(href != ''){
				var href = global.getContextPath() + href+"?spm="+cid+"."+ref;
				if(ref == '152'){//查看地图
					open(href);
				} else {
					location.href = href;
				}
			}
			return false;
		});
	};
	
	this.show = function(data, cid){
		var _this = this;
		var container = _this.container;
		var ol = $('<ol></ol>');
		
		var spm = global.QueryString.spm;
		var mid = null;
		if(spm){
			mid = spm.split(".")[1];
		}
		
		$.each(data,function(i, j){
			var $li = $('<li></li>');
			var $row = $('<a href="javascript:void(0);"></a>');
			$row.html(j.menuName);
			$li.append($row);
			var _subMenu = j.subMenu;
			if(_subMenu.length > 0){
				$row.addClass('title-icon');
				var ul = $('<ul class="two-menu"></ul>');
				$.each(_subMenu,function(i, j){
					var $row = $('<li><a href="javascript:void(0);"></a></li>');
					if(j.menuId == mid){
						_this.changeTitle(j);
						$row.find('a').addClass("current");
					}
					$row.find('a').html(j.menuName);
					$row.data('href', j.menuPath);
					$row.attr('ref', j.menuId);
					$row.attr('cid', cid);
					$row.addClass('left_menu');
					ul.append($row);
				});
				$li.append(ul);
			} else {
				$li.addClass('left_menu');
				$li.data('href', j.menuPath);
				$li.attr('cid', cid);
				if(j.menuId == mid){
					_this.changeTitle(j);
					$row.addClass("current");
				}
				$li.attr('ref', j.menuId);
			}
			ol.append($li);
		});
		container.append(ol);
		// container.append($('<div class="e-mail"> \
		// 			<span class="setting"></span> \
		// 			<a class="mail" href="javascript:void(0);"></a> \
		// 		</div>'))
	};
	
	this.changeTitle = function(menu){
		$('title').text(menu.menuName);
	}
	
	
	this.showHeader = function(name){
		if(name == null){
			$('.list-info-title > h2').remove();
		} else {
			$('.list-info-title > h2').html(name);
		}
		
	}
	
	this.getMenu = function(category){
		var _this = this;
		var data = global.getMenu();
		var array = [];
		var categoryName = null;
		$.each(data,function(i,v){
			if(v.menuId == category){
				array = v.subMenu;
				categoryName = v.menuName;
				return false;
			}
		});
		_this.showHeader(categoryName);
		return array;
	}
	
	
	
	this.init(cid);
}

var CBSHeader = function(options){
	this.opt = $.extend({}, options);
	this.container = this.opt.target;
	
	this.el = $('<div class="header-content"> \
				<h2>系统后台</h2> \
				<div class="header-icon"> \
					<div class="header-contents"> \
						<ul class="content-detail"> \
						</ul> \
					</div> \
					<div class="header-message"></div> \
					<div class="header-users"> \
						<div class="person"> \
							<div class="detail"> \
								<img src="img/person.png" class="fl" > \
								<div class="fr produce"> \
									<h4><b class="realName"></b>&nbsp;&nbsp;<span class="number"></span></h4> \
									<p class="email"></p> \
									<a href="javascript:void(0);" id="changePass">修改密码</a> \
									<button id="show_detail">个人详情</button> \
								</div> \
							</div> \
							<div class="exit"><a href="javascript:void(0);">退出系统</a></div> \
						</div> \
					</div> \
				</div> \
			</div>');
	
	this.init = function(){
		var _this = this;
		var staff=global.getUser();
		_this.el.find("#show_detail").click(function(){
			new CBSUserInfo();
		});
		_this.el.find('#changePass').click(function(){
			new CBSChangePass();
		});
		_this.el.find(".exit").click(function(){
			_this.logout();
		});
		_this.el.find(".produce .realName").html(staff.realName);
		_this.el.find(".produce .number").html(staff.number);
		_this.el.find(".produce .email").html(staff.email);
		_this.container.append(_this.el);
		
		_this.menu = new CBSHeadMenu(this.opt);
	};
	
	this.logout = function(){
		global.doLoginOut();
	}
	
	this.init();
};


var CBSUserInfo = function(){
	var staff=global.getUser();
	var context = global.getContextPath();
	this.el = $('<div class="per_model"> \
		<div class="per_detail"> \
			<h3 class="title">个人详情</h3>\
			<div class="message"> \
				<img src="'+context+'/img/person.png" class="fl"> \
				<div class="fr mess_con"> \
					<div class="mess_div short"> \
						<label for="">姓名:</label> \
						<input type="text" value="" class="column" data-column="realName" readonly/> \
					</div> \
					<div class="mess_div short"> \
						<label for="">工号:</label> \
						<input type="text" value="" class="column" data-column="number" readonly/> \
					</div> \
					<div class="mess_div long"> \
						<label for="">部门:</label> \
						<input type="text" value="" class="column" data-column="departName" readonly/> \
					</div> \
					<div class="mess_div short"> \
						<label for="">职级:</label> \
						<input type="text" value="" class="column" data-column="position" readonly/> \
					</div> \
					<div class="mess_div long"> \
						<label for="">邮箱:</label> \
						<input type="text" value="" class="column" data-column="email" readonly/> \
					</div> \
					<div class="mess_div long mess_phone"> \
						<label for="">电话:</label> \
						<input maxlength="11" type="text" value="" class="column" data-column="telphone" readonly/> \
					</div> \
					<div class="mess_div long"> \
						<input type="text" value="" class="errorMsg" readonly/> \
					</div> \
				</div> \
			</div> \
			<div class="submit"><a href="javascript:void(0);">修改</a></div> \
			<span class="mess_close"></span> \
		</div> \
	</div>');
	
	
	this.init = function(){
		var _this = this;
		_this.el.find('.mess_close').click(function(){
			_this.destory();
		});
		_this.el.find('.submit>a').click(function(){
			var t=$('.submit a').text();
			if(t=='修改') {
				$('.mess_phone ').addClass('active');
				$('.mess_phone input').focus().removeAttr('readonly');
				$('.submit a').text('提交');
			} else if(t=='提交') {
				var telphone=_this.el.find($('.mess_phone input')).val();
				var validTel=/^1[0-9]{10}$/;
				if(!validTel.test(telphone)) {
					$('.errorMsg').css('color', 'red').val('手机号格式错误');
				} else {
					$('.errorMsg').val('');
					var url="/admin/staff/editStaff";
					$.ajax({
			            url: global.server + url,
			            type: "POST",
			            data: {staffId:staff.staffId,telphone:telphone},
			            success: function (msg) {
			                if (msg && msg.status && msg.status.statusCode == global.status.success) {
			                	//alert('提交成功');
			                	$('.mess_phone ').removeClass('active');
			    				$('.mess_phone input').attr('readonly');
			    				$('.submit a').text('修改');
			                } else {
			                	alert('提交失败');
			                }
			            }
			        });
				}
			}
		});
		_this.fillData();
		$('body').append(_this.el);
	};
	
	
	this.fillData = function(){
		var _this = this;
		//var staff=$.parseJSON($.cookie('user'));
		var columns=_this.el.find('.column');
		
		var url = "/admin/staff/getStaffInfo?staffId=" + staff.staffId;
		$.get(global.server + url, function (msg) {
            if (msg && msg.status && msg.status.statusCode == global.status.success) {
                var data = msg.data;
                columns.each(function (i, j) {
                	var _this = $(this);
                	var pName = _this.data("column");
                	var pValue = data[pName];
                	_this.val(pValue);
                });
            }
        })
	};
	
	this.destory = function(){
		var _this = this;
		_this.el.remove();
	};
	
	this.init();
	
}

var CBSChangePass=function() {
	var staff=global.getUser();
	this.el=$('<div class="per_model">\
		<div class="edit_pass">\
			<h3>修改密码</h3>\
			<div class="password">\
				<img src="img/person.png" class="fl">\
				<div class="fr pass_con">\
					<div class="pass_div">\
						<label for="">原密码</label>\
						<input maxlength="10" type="password" id="oldPass"><b class="op"></b>\
					</div>\
					<div class="pass_div">\
						<label for="">新密码</label>\
						<input maxlength="10" type="password" id="newPass"><b class="np"></b>\
					</div>\
					<div class="pass_div">\
						<label for="">再次新密码</label>\
						<input maxlength="10" type="password" id="configPass"><b class="cp"></b>\
					</div>\
				</div>\
			</div>\
			<div class="pass_sub"><a href="javascript:void(0);">提交</a></div>\
			<span class="pass_close"></span>\
		</div>\
	</div>');
	
	this.init = function(){
		var _this = this;
		_this.el.find('.pass_close').click(function(){
			_this.destory();
		});
		_this.el.find('.pass_sub>a').click(function(){
			var oldPassVal=$('#oldPass').val();
			var newPassVal=$('#newPass').val();
			var configPassVal=$('#configPass').val();
			var validPass=/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,10}$/;
			if($.trim(oldPassVal)=='') {
				$('.op').css({'color':'red','font-size':'12px','margin-left':'10px'}).html('密码不能为空');
				return false;
			} else {
				$('.op').html('')
			}
			if($.trim(newPassVal)=='') {
				$('.np').css({'color':'red','font-size':'12px','margin-left':'10px'}).html('密码不能为空');
				return false;
			} else if(!validPass.test(newPassVal)){
				$('.np').css({'color':'red','font-size':'12px','margin-left':'10px'}).html('密码为6-10位英文、数字组合');
				return false;
			} else {
				$('.np').html('');
			}
			if($.trim(configPassVal)=='') {
				$('.cp').css({'color':'red','font-size':'12px','margin-left':'10px'}).html('请再次输入密码');
				return false;
			} else if(newPassVal!=configPassVal) {
				$('.cp').css({'color':'red','font-size':'12px','margin-left':'10px'}).html('两次输入密码不一致');
				return false;
			} else {
				$('.cp').html('');
			}
			var url='/admin/staff/changePassword';
			$.ajax({
	            url: global.server + url,
	            type: "POST",
	            data: {oldPassword:$.md5_32(oldPassVal),newPassword:$.md5_32(newPassVal)},
	            success: function (msg) {
	                if (msg && msg.status && msg.status.statusCode == global.status.success) {
	                	alert('修改成功');
	                	global.doLoginOut();
	                } else {
	                	$('.np').css({'color':'red','font-size':'12px','margin-left':'10px'}).html(msg.status.errorMsg);
	                }
	            }
	        });
		});
		$('body').append(_this.el);
	};
	
	this.destory = function(){
		var _this = this;
		_this.el.remove();
	};
	
	this.init();
}

$(function(){
	var target = $('header#header');
	var opt = {
		target : target
	}
	var header = new CBSHeader(opt);
});



