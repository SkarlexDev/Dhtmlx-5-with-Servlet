function treeEventInteract(id) {
	$.ajax({
		type: "GET",
		url: "timetable",
		data: id,
		success: function(data) {
			layout.cells("b").attachHTMLString(data);
		},
	});
	return true;
}

function lazyLoad() {
	myTree = layout.cells("a").attachTree();
	myTree.setImagesPath("static/codebase/imgs/");
	myTree.attachEvent("onSelect", function(id) {
		treeEventInteract(id);
	});
	$.ajax({
		type: "GET",
		url: "tree",
		dataType: "xml",
		success: function() {
			myTree.loadXML(window.location.origin + "/college-manager/" + this.url);
		},
	});
}