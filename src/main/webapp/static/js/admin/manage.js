function manageConfig() {
	manageOption.attachEvent("onXLE", function() {
		document.getElementsByName("studyYear")[0].setAttribute('type', 'number');
	});
}