$(document).ready(function()
{
	$.get("/Questionnaire/api/class?action=dump", function(data, status)
	{
		//alert("Data: " + data + " Status: " + status);
		console.log(JSON.stringify(data));
	});
});