$(document).ready(function() {
	$(reviewTextArea).hide();
	$(reviewButton).click(function(){
		$(reviewTextArea).toggle("slow");		
	});
});