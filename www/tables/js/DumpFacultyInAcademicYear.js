$(document).ready(function()
{
	$.get("/Questionnaire/api/facultyInAcademicYear?action=dump", function(data, status)
	{
		console.log(JSON.stringify(data));
		var txt = "", x;
		txt += "<table border='1'>"
		for (x in data)
		{
			txt += "<tr><td>" + JSON.stringify(data[x].FacultyID) + "</td>";
			txt += "<td>" + JSON.stringify(data[x].AYearID) + "</td></tr>";
		}
		
		txt += "</table>"
		document.getElementById("facultyID").innerHTML = txt
	});
});