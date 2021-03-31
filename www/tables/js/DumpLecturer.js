$(document).ready(function()
{
	$.get("/Questionnaire/api/lecturer?action=dump", function(data, status)
	{
		console.log(JSON.stringify(data));
		
		var txt = "", x;
		
		txt += "<table border='1'>"
		for (x in data)
		{
			txt += "<tr><td>" + JSON.stringify(data[x].LecturerID) + "</td>";
			txt += "<td>" + JSON.stringify(data[x].LecturerName) + "</td></tr>";
		}
		txt += "</table>"
		document.getElementById("LecturerID").innerHTML = txt;

	});
});