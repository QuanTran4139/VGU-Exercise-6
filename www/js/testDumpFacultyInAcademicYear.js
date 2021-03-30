$(document).ready(function()
{
	$.get("/Questionnaire/api/facultyInAcademicYear?action=dump", function(data, status)
	{
		//alert("Data: " + data + " Status: " + status);
		console.log(JSON.stringify(data));
	});
});