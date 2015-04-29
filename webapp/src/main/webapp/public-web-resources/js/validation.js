$(function() {
	console.log('---------------> ici');
	$('#name').keyup(function() {
		if ($.trim($('#name').val()) == '') {
			$('#name').next('.error-message').fadeIn().text(mandatoryMessage);
		} else {
			$('#name').next('.error-message').hide();
		}
	});
	$('#introduced').keyup(function() {
		if (!$.trim($('#introduced').val().match(datePattern))) {
			$('#introduced').next('.error-message').fadeIn().text(expectedDateFormat);
		} else {
			$('#introduced').next('.error-message').hide();
		}
	});
	$('#discontinued').keyup(function() {
		if (!$.trim($('#discontinued').val().match(datePattern))) {
			$('#discontinued').next('.error-message').fadeIn().text(expectedDateFormat);
		} else {
			$('#discontinued').next('.error-message').hide();
		}
	});		
});