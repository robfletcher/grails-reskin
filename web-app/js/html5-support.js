$(document).ready(function() {
	if (!Modernizr.inputtypes.date) {
		$("input[type=date], input.date").datepicker({dateFormat: $.datepicker.W3C});
	}
});