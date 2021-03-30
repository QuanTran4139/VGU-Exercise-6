$(document).ready(function()
{
	$.get("/Questionnaire/api/program?action=dump", function(data, status)
	{
		//alert("Data: " + data + " Status: " + status);
		console.log(JSON.stringify(data));
	});
});