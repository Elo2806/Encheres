function changeType(type) {
	var elements = document.getElementsByClassName(type);
	var text = elements.length;
	for (var i = 0; i < elements.length; i++) {
		if(elements[i].disabled){
			elements[i].checked = false;
		}
		elements[i].disabled = !elements[i].disabled
	}
	alert("texte : " + text);
}

//function selectType(init) {
//	if(init){
//		changeType(init);
//	}else{
//		changeType('achat');
//		changeType('vente');
//	}
//	
//
//}