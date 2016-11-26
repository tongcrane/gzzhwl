/*
    global settings
*/
if (!console) {
    console = {
        log: function () { }
    }
}

Array.prototype.shuffle = function () {
    for (var rnd, tmp, i = this.length; i; rnd = parseInt(Math.random() * i), tmp = this[--i], this[i] = this[rnd], this[rnd] = tmp)
        ;
    return this;
}
String.prototype.overflow = function (num) {
    if (this.length > num) {
        return this.substring(0, num) + '...';
    } else {
        return this.toString();
    }
}
//去除左右全角半角空格
String.prototype.newtrim = function(){
	var strTrim = this.replace(/(^\s*)|(\s*$)/g, "");
	strTrim = strTrim.replace(/^[\s　\t]+|[\s　\t]+$/, "");
	var strf=strTrim;
	strf = strf.replace(/(^\s*)|(\s*$)/g, "");
	strf = strf.replace(/^[\s　\t]+|[\s　\t]+$/, "");
	return strf;
}

String.prototype.overflowNoPoint = function (num) {
    if (this.length > num) {
        return this.substring(0, num);
    } else {
        return this.toString();
    }
}

if (!global_config) {
    var global_config = {};
}

var global = $.extend(global_config, {
    "ajaxstate":true,
    "counting": 0,
    "defaultIfBlack" : function(str, value){
    	if(str === undefined || str == null){
    		if(value){
    			return value;
    		} else {
    			return "";
    		}
    	} else {
    		return str;
    	}
    },
    "isChinese" : function(txt){
		var reg = /^[\u4E00-\u9FA5]+$/;
		return reg.test(txt);
	},
    "status": {
       "success": 200
    },
    "imgsrc": function (id, opt) {
    	var url = global.server+'/api/image/'+id + "?";
    	for(var p in opt){
    		var param = p + "=" + opt[p] +"&";
    		url += param;
    	}
        return url.substring(0, url.length-1);
    },
    "clen": function (str) {
        str = str + "";
        return str.match(/[^ -~]/g) == null ? str.length : str.length + str.match(/[^ -~]/g).length;
    },
    "encode": function (unencoded) {
        return encodeURIComponent(unencoded).replace(/'/g, "%27").replace(/"/g, "%22");
    },
    "decode": function (encoded) {
        return decodeURIComponent(encoded.replace(/\+/g, " "));
    },
    "htmlEncode": function (html) {
        if (html && html.length > 0)
            return html.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/ /g, "&nbsp;").replace(/\'/g, "&#39;").replace(/\"/g, "&quot;").replace(/\n/g, "<br>");
        else
            return "";
    },
    "htmlDecode": function (text) {
        if (text && text.length > 0)
            return text.replace(/&amp;/g, "&").replace(/&lt;/g, "<").replace(/&gt;/g, ">").replace(/&nbsp;/g, " ").replace(/&#39;/g, "\'").replace(/&quot;/g, "\"").replace("<br>", "\n");
        else
            return "";
    },
    "uuid4": function () {
        return Math.floor((1 + Math.random()) * 0x10000)
            .toString(16)
            .substring(1);
    },
    "uuid": function () {
        return global.uuid4() + global.uuid4() + '-' + global.uuid4() + '-' + global.uuid4() + '-' +
            global.uuid4() + '-' + global.uuid4() + global.uuid4() + global.uuid4();
    },
    "uuid8": function () {
        return global.uuid4() + global.uuid4();
    },
    "uuid16": function () {
        return global.uuid4() + global.uuid4() + global.uuid4() + global.uuid4();
    },
    Base64: {
        // private property
        _keyStr: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",
        // public method for encoding
        encode: function (input) {
            var output = "";
            var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
            var i = 0;

            input = global.Base64._utf8_encode(input);

            while (i < input.length) {

                chr1 = input.charCodeAt(i++);
                chr2 = input.charCodeAt(i++);
                chr3 = input.charCodeAt(i++);

                enc1 = chr1 >> 2;
                enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
                enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
                enc4 = chr3 & 63;

                if (isNaN(chr2)) {
                    enc3 = enc4 = 64;
                } else if (isNaN(chr3)) {
                    enc4 = 64;
                }

                output = output +
                    this._keyStr.charAt(enc1) + this._keyStr.charAt(enc2) +
                    this._keyStr.charAt(enc3) + this._keyStr.charAt(enc4);

            }

            return output;
        },

        // public method for decoding
        decode: function (input) {
            var output = "";
            var chr1, chr2, chr3;
            var enc1, enc2, enc3, enc4;
            var i = 0;

            input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

            while (i < input.length) {

                enc1 = this._keyStr.indexOf(input.charAt(i++));
                enc2 = this._keyStr.indexOf(input.charAt(i++));
                enc3 = this._keyStr.indexOf(input.charAt(i++));
                enc4 = this._keyStr.indexOf(input.charAt(i++));

                chr1 = (enc1 << 2) | (enc2 >> 4);
                chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
                chr3 = ((enc3 & 3) << 6) | enc4;

                output = output + String.fromCharCode(chr1);

                if (enc3 != 64) {
                    output = output + String.fromCharCode(chr2);
                }
                if (enc4 != 64) {
                    output = output + String.fromCharCode(chr3);
                }

            }

            output = global.Base64._utf8_decode(output);

            return output;

        },

        // private method for UTF-8 encoding
        _utf8_encode: function (string) {
            string = string.replace(/\r\n/g, "\n");
            var utftext = "";

            for (var n = 0; n < string.length; n++) {

                var c = string.charCodeAt(n);

                if (c < 128) {
                    utftext += String.fromCharCode(c);
                } else if ((c > 127) && (c < 2048)) {
                    utftext += String.fromCharCode((c >> 6) | 192);
                    utftext += String.fromCharCode((c & 63) | 128);
                } else {
                    utftext += String.fromCharCode((c >> 12) | 224);
                    utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                    utftext += String.fromCharCode((c & 63) | 128);
                }

            }

            return utftext;
        },

        // private method for UTF-8 decoding
        _utf8_decode: function (utftext) {
            var string = "";
            var i = 0;
            var c = c1 = c2 = 0;

            while (i < utftext.length) {

                c = utftext.charCodeAt(i);

                if (c < 128) {
                    string += String.fromCharCode(c);
                    i++;
                } else if ((c > 191) && (c < 224)) {
                    c2 = utftext.charCodeAt(i + 1);
                    string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                    i += 2;
                } else {
                    c2 = utftext.charCodeAt(i + 1);
                    c3 = utftext.charCodeAt(i + 2);
                    string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                    i += 3;
                }

            }

            return string;
        }
    }

});

global.uploadStart = function () {
	var src = global.getContextPath()+"/img/loading.gif";
    $('<div class="uploading-overlay"><img src="'+src+'"></div>').appendTo('body');
    $('.uploading-overlay').css({
        'display': 'block',
        'opacity': '0.5'
    });
};

global.uploadEnd = function () {
    $('.uploading-overlay').css({
        'display': 'none',
        'opacity': '0'
    }).remove();
};
global.QueryString = function () {
    // This function is anonymous, is executed immediately and 
    // the return value is assigned to QueryString!
    var query_string = {};
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        // If first entry with this name
        if (typeof query_string[pair[0]] === "undefined") {
            query_string[pair[0]] = pair[1];
            // If second entry with this name
        } else if (typeof query_string[pair[0]] === "string") {
            var arr = [query_string[pair[0]], pair[1]];
            query_string[pair[0]] = arr;
            // If third or later entry with this name
        } else {
            query_string[pair[0]].push(pair[1]);
        }
    }
    return query_string;
}();
// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
// 例子： 
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}


//if(global.getUserId()){
//	$.ajaxSetup({
//	    headers: {
//	        'Authorization': global.getUserId() + ':' + global.getToken()
//	    },
//	    cache: false,
//	});
//}

$.ajaxSetup({
	timeout : 20000,
	error : function(xhr, status, e) {
		if (xhr.status == 403) {
			
		} else {

		}
	},
	beforeSend: function () {
        if(global.ajaxstate){
            global.uploadStart();
        }
//        return global.ajaxstate;
        return true;
    },
    complete: function (obj,status) {
        if(status == 'error'){
            if(obj.statusText == "Forbidden"){
                global.uploadEnd();
                global.counting++;
                if (global.counting == 1) {
                    alert("您的帐号已经被登出");
                    $.removeCookie("user");
//                  if (opener == null) {
//                    	location.href = global.getContextPath()+'/login.html';
//                  } else {
//                  	window.close();
//                      window.opener.location.href = global.getContextPath()+'/login.html';
//                  }
                }
                global.ajaxstate = false;
                return false;
            }
            global.uploadEnd();
            global.ajaxstate = false;
        }else if(status == 'timeout'){
            global.uploadEnd();
            global.counting++;
            if (global.counting == 1) {
                alert("网络超时");
            }
            global.ajaxstate = false;
        }
        global.uploadEnd();
    }
});

$("<input type='hidden' id='_temp_location'/>").val(location.href).appendTo($("body"));