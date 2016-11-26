$(document).ready(function() {
	
	//设置下拉列表框样式
	$(".mySelect3").styleSelect({styleClass: "selectFruits",optionsWidth: 1,speed: 'fast'});
		$(".mySelect").styleSelect({styleClass: "selectDark"});
		
	//点击选择照片
	$(".detail-photos p").click(function(){
		var f = $(this).children("input.file-upload")[0];		
		f.click();
	});

	//上传照片
	$("input.file-upload").change(function(Obj){

		var f = this.value;
		$.ajax({
			type: "POST",
			url: global.server + "/admin/vehiclemanage/uploadImage",
			dataType:"JSON",
			async:true,
			data: { image: f },
			success: function (data) {
				alert(data.status.errorMsg);
				/*
				if(data.error==1){
					$("#msg").html(data.message);
                    $(".reg_hint").css("display","block");
				}
				if(!(data.error != 0)){
					var btn = $("#getMobileCode");
					btn.attr("disabled", "true");
					btn.css({"background-image": "none"});
					btn.css({"background-color": "#f2f2f2"});
					settime(btn[0]);
				}
				*/
			}
		});
		
	});
	
});