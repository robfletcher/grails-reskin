$(function(){
	// enable jquery datepicker if browser does not support input type=date
	if (!Modernizr.inputtypes.date) {
		$("input[type=date], input.date").datepicker({dateFormat: $.datepicker.W3C});
	}

	//add enhance-loaded class, fade in site post-PE
	$("html").addClass("enhanced-loaded");
});
