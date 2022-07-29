function doLogin(form){
	$.ajax({
		type: "POST",
		url: "login",
		data: form,
		success: () => {
			location.href = "admin"
			},
		error: (a) => {
			dhtmlx.alert("Status error: " + a.responseText);
		}
	});
}

function doOnLoad() {
	loginForm = new dhtmlXForm("form_container");
	loginForm.loadStruct("static/data/login.xml");

	loginForm.enableLiveValidation(true);

	loginForm.attachEvent("onButtonClick", function() {
		if (loginForm.validate()) {
			doLogin(loginForm.getFormData());
		} else {
			dhtmlx.alert("All fields are Required!");
		}
	});	
}