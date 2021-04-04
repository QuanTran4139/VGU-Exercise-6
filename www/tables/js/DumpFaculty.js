$(document).ready(function()
{
	$.get("/Questionnaire/api/faculty?action=dump", function(data, status)
	{
		console.log(JSON.stringify(data));
		var txt = "", x;
		txt += "<table border='1'>"
		for (x in data)
		{
			txt += "<tr><td>" + JSON.stringify(data[x].FacultyID) + "</td>";
			txt += "<td>" + JSON.stringify(data[x].FacultyName) + "</td></tr>";
		}
		
		txt += "</table>"
		document.getElementById("facultyID_2").innerHTML = txt;
	});
});