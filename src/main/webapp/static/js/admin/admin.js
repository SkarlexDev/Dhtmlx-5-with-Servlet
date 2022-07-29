dhtmlxEvent(window, "load", () => {
	layout = new dhtmlXLayoutObject(document.body, "3W");
	layout.cells("a").setText("Files");
	layout.cells("a").setWidth(250);
	layout.cells("b").setText("View");
	layout.cells("c").setText("Manager");
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
			newCollegeLayout();
		};
		if (id == "manageTimetable") {
			manageTimetableLayout();
		};
		if (id == "manageTeacher") {
			manageTeacherLayout();
		};
		if (id == "logout") {
			logout();
		};
	});
	toolbar.attachEvent("onXLE", function() {
		toolbar.addSpacer("manageTeacher");
	});
	
	lazyLoad();
});