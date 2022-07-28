dhtmlxEvent(window, "load", () => {
	layout = new dhtmlXLayoutObject(document.body, "2U");
	layout.cells("a").setText("Files");
	layout.cells("a").setWidth(250);
	layout.cells("b").setText("View");
	toolbar = layout.attachToolbar();
	toolbar.setIconsPath("static/icons/");
	toolbar.loadXML("static/data/homeToolbar.xml");
	toolbar.attachEvent("onclick", function(id) {
		if (id == "home") {
			location.href = "college";
		};
		if (id == "refresh") {
			location.reload();
		};
		if (id == "login") {
			location.href = "login";
		};
	});
	toolbar.attachEvent("onXLE", function() {
		toolbar.addSpacer("refresh");
	});
	lazyLoad();
});