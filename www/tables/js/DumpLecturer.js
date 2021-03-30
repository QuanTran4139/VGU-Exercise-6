$(document).ready(function()
{
	$.get("/Questionnaire/api/lecturer?action=dump", function(data, status)
	{
		//alert("Data: " + data + " Status: " + status);
		console.log(JSON.stringify(data));
	});
});