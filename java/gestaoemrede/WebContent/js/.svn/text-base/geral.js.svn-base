function autotab(tabFrom, tabToId){
	
	var dado = tabFrom.value; 
	
	var dia = dado.substring(0,2);
	dia = dia.replace("_","");
	
	var mes = dado.substring(3,5);
	mes = mes.replace("_","");
	mes = mes.replace("_","");
	
	var ano = dado.substring(5,9);
	ano = ano.replace("/","");
	ano = ano.replace("_","");
	ano = ano.replace("_","");
	ano = ano.replace("_","");
	ano = ano.replace("_","");
	
	
	var total = dia.length + mes.length + ano.length;

	var maxLength = tabFrom.getAttribute("maxlength");
	
	if ( total == 8 ){
		var tabTo = document.getElementById(tabToId);
		alert("alvo: "+tabTo);
		tabTo.focus();
	}  
}


function replaceAll(string, token, newtoken) {
	
	while (string.indexOf(token) != -1) {
 		string = string.replace(token, newtoken);
	}
	
	return string;
}


function inicializaFocoCursor(campo){
	var elemento = document.getElementById(campo);
	if (elemento==null){
		alert("elemento nao encontrado:"+campo);
	}
	else{
		elemento.focus();
	}
	
}



var MudarCampo = true;

document.onkeypress = HabilitarTABAutom;

function HabilitarTABAutom(evt)
{
	var codTecla;

 	var e = evt ? evt : window.event; 

	if(e.keyCode)  // IE
	{
		codTecla = e.keyCode;
	}
	else // Netscape/Firefox/Opera
	{
		codTecla = e.which;
	}

	if ( (codTecla < 48) || (codTecla >  255) )
		MudarCampo = false;
	else
		MudarCampo = true;

}

function tabAutom(quem, e) 
{
	if ( ( quem.value.length == quem.maxLength ) && ( MudarCampo ) ){
		var i=0,j=0, indice=-1;
		// Localiza em qual form est� o input no documento
		for (i=0; i<document.forms.length; i++) {
			for (j=0; j<document.forms[i].elements.length; j++) {
				if (document.forms[i].elements[j].name == quem.name) {
					indice=i;
					break;
				}
			}
			if (indice != -1)
		         break;
		}

		// Localiza o input no documento e verifica se existe outro input para receber o foco
		for (i=0; i<document.forms[indice].elements.length; i++) 
		{
			if (document.forms[indice].elements[i].name == quem.name) 
			{
				while ( ((i+1) < document.forms[indice].elements.length) && 
				        ( (document.forms[indice].elements[(i+1)].type == "hidden") || 
					  (document.forms[indice].elements[(i+1)].name == "Lim") ) )
				{
							i++;
				}
				if ( (i+1) < document.forms[indice].elements.length )
				{
					document.forms[indice].elements[(i+1)].focus();
					MudarCampo = false;
				}
				break;
			} 
		}
	} 
}

