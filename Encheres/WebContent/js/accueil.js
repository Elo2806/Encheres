function changeType(type) {
	var elements = document.getElementsByClassName(type);
	var text = elements.length;
	
	for (var i = 0; i < elements.length; i++) {
//		if(!elements[i].disabled){
//			elements[i].checked = false;
//		}else{
//			elements[i].checked = true;
//		}
		elements[i].disabled = !elements[i].disabled;
		elements[i].checked = !elements[i].disabled;

	}
}

function selectType(init=false,type = 'vente') {
	
	if(init){
		changeType(type);
	}else{
		changeType('achat');
		changeType('vente');
	}
}