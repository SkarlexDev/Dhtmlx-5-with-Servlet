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

function postManagedForm(form, id, tag) {
	$.ajax({
		type: "POST",
		url: id,
		data: form,
		success: () => {
			dhtmlx.alert({
				text: "Success!",
				ok: "OK",
				callback: () => {
					if (tag == "1") {
						treeEventInteract(form.studyYear + form.collegeName);
					}
				}
			});
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