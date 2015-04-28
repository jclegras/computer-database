$(function() {
	var mandatoryMessage = "<spring:message code='validation.notNull' javaScriptEscape='true' />";	
	$('#name').keyup(function() {
		if ($.trim($('#name').val()) == '') {
			$('#name').next('.error-message').fadeIn().text(mandatoryMessage);
		} else {
			$('#name').next('.error-message').hide();
		}
	});
	var datePattern = "<spring:message code='validation.date.pattern' javaScriptEscape='true' />";
	var expectedDateFormat = "<spring:message code='validation.format.short' javaScriptEscape='true' />";
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