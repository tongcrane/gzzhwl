jQuery.extend({
	createDownloadForm : function(id, setting) {
		var action = setting.url;
		var formId = 'jForm' + id;
		var form = $('<form></form>').attr({
			action : action,
			method : "POST",
			name : formId,
			id : formId,
			target : "_blank"
		}).css('display','none').appendTo('body');
		var params = setting.param;
		params.Authorization = global.getUserId() + ':' + global.getToken();
		if(params != null){
			for(var name in params){
				$("<input />").attr({name:name,value:params[name],type:'hidden'}).appendTo(form);
			}
		}
		return form;
	},
	ajaxDownload : function(s) {
		s = jQuery.extend({}, s);
		var id = new Date().getTime();
		var form = jQuery.createDownloadForm(id, s);
		form.submit();
		form.remove();
	}
});
