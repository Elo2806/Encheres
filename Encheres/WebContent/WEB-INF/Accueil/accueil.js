function selectType(type) {
	var elements = document.getElementsByClassName(type);
	var text = elements.length;
	for (var i = 0; i < elements.length; i++) {
		elements[i].disabled = !elements[i].disabled
	}
	alert("texte : " + text);
}
