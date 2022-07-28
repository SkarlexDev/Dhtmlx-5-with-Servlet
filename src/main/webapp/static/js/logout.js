function logout() {
	$.ajax({
		type: "GET",
		url: "doLogout",
		success: () => {
			location.href = "college"
		}
	});
}