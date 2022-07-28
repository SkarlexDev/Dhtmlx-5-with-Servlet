dhtmlxEvent(window, "load", () => {
	layout = new dhtmlXLayoutObject(document.body, "3W");
	layout.cells("a").setText("Files");
	layout.cells("a").setWidth(250);
	layout.cells("b").setText("View");
	layout.cells("c").setText("Manage TimeTable data");
	layout.cells("c").setWidth(400);
	toolbar = layout.attachToolbar();
	toolbar.setIconsPath("static/icons/");
	toolbar.loadXML("static/data/toolbar.xml");

	toolbar.attachEvent("onclick", function(id) {
		if (id == "home") {
			location.href = "college";
		};
		if (id == "refresh") {
			location.reload();
		};
		if (id == "newCollege") {
			newCollege = layout.cells("b").attachForm();
			newCollege.loadStruct("static/data/collegeForm.xml");
			newCollege.enableLiveValidation(true);
			newCollege.attachEvent("onButtonClick", function() {
				if (newCollege.validate()) {
					postNewCollege(newCollege.getFormData());
				}
			});
		};
		if (id == "logout") {
			logout();
		};
	});
	toolbar.attachEvent("onXLE", function() {
		toolbar.addSpacer("newCollege");
	});

	manage = layout.cells("c").attachToolbar();
	manage.setIconsPath("static/icons/");
	manage.loadXML("static/data/manageToolbar.xml");
	manage.attachEvent("onclick", function(id) {
		manageOption = layout.cells("c").attachForm();
		manageOption.enableLiveValidation(true);
		if (id == "add") {
			$.ajax({
				type: "GET",
				url: "formManager",
				dataType: "xml",
				data: "add",
				success: function() {
					manageOption.loadStruct(window.location.origin + "/college-manager/" + this.url);
				},
			});

			manageConfig();
			manageOption.attachEvent("onButtonClick", function() {
				if (manageOption.validate()) {
					postNewTimetableData(manageOption.getFormData());
				} else {
					dhtmlx.alert("Invalid data");
				}
			});
		};
		if (id == "edit") {
			$.ajax({
				type: "GET",
				url: "formManager",
				dataType: "xml",
				data: "edit",
				success: function() {
					manageOption.loadStruct(window.location.origin + "/college-manager/" + this.url);
				},
			});

			manageConfig();
			manageOption.attachEvent("onButtonClick", function() {
				if (manageOption.validate()) {
					postEditTimetableData(manageOption.getFormData());
				} else {
					dhtmlx.alert("Invalid data");
				}
			});
		};
		if (id == "delete") {
			manageOption.loadStruct("static/data/deleteForm.xml");
			manageConfig();
			manageOption.attachEvent("onButtonClick", function() {
				if (manageOption.validate()) {
					postDeletetTimetableData(manageOption.getFormData());
				} else {
					dhtmlx.alert("Invalid data");
				}
			});
		};
	});
	lazyLoad();
});