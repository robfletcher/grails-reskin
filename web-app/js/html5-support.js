$(document).ready(function() {
	if (!Modernizr.inputtypes.date) {
		$("input[type=date]").datepicker({dateFormat: $.datepicker.W3C});
	}
//	if (!Modernizr.inputtypes.range) {
//		$("input[type=range]").each(function() {
//			console.log($(this));
//			var min, max = null;
//			if ($(this).attr("min") != undefined) {
//				min = parseInt($(this).attr("min"));
//			}
//			if ($(this).attr("max") != undefined) {
//				max = parseInt($(this).attr("max"));
//			}
//			console.log("min", min, "max", max);
//			$(this).slider({min: min, max: max});
//		});
//	}
});