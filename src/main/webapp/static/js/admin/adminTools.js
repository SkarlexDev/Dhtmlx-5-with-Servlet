function postNewCollege(form) {
	$.ajax({
		type: "POST",
		url: "newCollege",
		data: form,
		success: () => {
			dhtmlx.alert({
				text: "Success!",
				ok: "OK",
				callback: () => {
					lazyLoad();
				}
			});
		},
		error: (a) => {
			dhtmlx.alert("Status error: " + a.responseText);
		}
	});
}

function postNewTimetableData(form) {
	$.ajax({
		type: "POST",
		url: "add",
		data: form,
		success: () => {
			dhtmlx.alert({
				text: "Success!",
				ok: "OK",
				callback: () => {
					treeEventInteract(form.studyYear + form.collegeName);
				}
			});
		},
		error: (a) => {
			dhtmlx.alert("Status error: " + a.responseText);
		}
	});
}

function postEditTimetableData(form) {
	$.ajax({
		type: "POST",
		url: "edit",
		data: form,
		success: () => {
			treeEventInteract(form.studyYear + form.collegeName);
		},
		error: (a) => {
			dhtmlx.alert("Status error: " + a.responseText);
		}
	});
}

function postDeletetTimetableData(form) {
	$.ajax({
		type: "POST",
		url: "delete",
		data: form,
		success: () => {
			dhtmlx.alert({
				text: "Success!",
				ok: "OK",
				callback: () => {
					treeEventInteract(form.studyYear + form.collegeName);
				}
			});
		},
		error: (a) => {
			dhtmlx.alert("Status error: " + a.responseText);
		}
	});
}