function Clone() {
	
	// Selecting last id 
	var lastname_id= $('.element input[type=text]:nth-child(1)').last().attr('id');
	var split_id = lastname_id.split('.');
	
	// New index
	var index = Number(split_id[0][9]) + 1;
	
	var clone=$('.element').clone();
	
	$(clone).find('input[type=text]:nth-child(1)')[0]['id']="chapitres"+index+".nomChapitre";
	$(clone).find('input[type=text]:nth-child(1)')[1]['id']="chapitres"+index+".temps";
	$(clone).find('input[type=text]:nth-child(1)')[2]['id']="chapitres"+index+".prix";
	
	$(clone).find('input[type=text]:nth-child(1)')[0]['name']="chapitres["+index+"].nomChapitre";
	$(clone).find('input[type=text]:nth-child(1)')[1]['name']="chapitres["+index+"].temps";
	$(clone).find('input[type=text]:nth-child(1)')[2]['name']="chapitres["+index+"].prix";
	
	$(clone).find('input[type=text]:nth-child(1)')[0]['value']="";
	$(clone).find('input[type=text]:nth-child(1)')[1]['value']="";
	$(clone).find('input[type=text]:nth-child(1)')[2]['value']="";
	
	$('.results').append(clone[0]);
	  
}