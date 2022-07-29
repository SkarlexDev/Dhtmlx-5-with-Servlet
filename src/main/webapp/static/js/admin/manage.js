function newCollegeLayout() {
	layout.cells("c").detachToolbar();
	layout.cells("c").setText("New College");
	newCollege = layout.cells("c").attachForm();
	newCollege.loadStruct("static/data/collegeForm.xml");
	newCollege.enableLiveValidation(true);
	newCollege.attachEvent("onButtonClick", function() {
		if (newCollege.validate()) {
			postNewCollege(newCollege.getFormData());
		}
	});
}

function manageTimetableLayout() {
	layout.cells("c").detachToolbar();
	layout.cells("c").setText("Manage TimeTable data");
	layout.cells("c").attachForm();
	manageInnerToolBar();
	manage.attachEvent("onclick", function(id) {
		manageOption = layout.cells("c").attachForm();
		manageOption.enableLiveValidation(true);
		toolBarFunctions(id, "formManager", "1");
	});
}

function manageTeacherLayout() {
	layout.cells("c").detachToolbar();
	layout.cells("c").setText("Manage Teacher data");
	layout.cells("c").attachForm();
	manageInnerToolBar();
	manage.attachEvent("onclick", function(id) {
		manageOption = layout.cells("c").attachForm();
		manageOption.enableLiveValidation(true);
		toolBarFunctions(id + "Teacher", "teacherFormManager", "2");
	});
}


function manageInnerToolBar() {
	manage = layout.cells("c").attachToolbar();
	manage.setIconsPath("static/icons/");
	manage.loadXML("static/data/manageToolbar.xml");
}

function toolBarFunctions(id, target, tag) {
	if (id == "add" || id == "addTeacher" || id == "edit" || id == "editTeacher" || id == "deleteTeacher") {
		toolbarStruct(id, target, tag);
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
}

function toolbarStruct(id, target, tag) {
	$.ajax({
		type: "GET",
		url: target,
		dataType: "xml",
		data: id,
		success: function() {
			manageOption.loadStruct(window.location.origin + "/college-manager/" + this.url);
		},
	});
	if (tag == "1") {
		manageConfig();
	}
	manageOption.attachEvent("onButtonClick", function() {
		if (manageOption.validate()) {
			postManagedForm(manageOption.getFormData(), id, tag);
		} else {
			dhtmlx.alert("Invalid data");
		}
	});
}


function manageConfig() {
	manageOption.attachEvent("onXLE", function() {
		document.getElementsByName("studyYear")[0].setAttribute('type', 'number');
	});
}

