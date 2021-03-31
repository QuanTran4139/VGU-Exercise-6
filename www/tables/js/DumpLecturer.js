$(document).ready(function()
{
	$.get("/Questionnaire/api/lecturer?action=dump", function(data, status)
	{
		console.log(JSON.stringify(data));
		var txt = "", x;
		var txt_2 = "";
		txt += "<table border='1'>"
		for (x in data)
		{
			txt += "<tr><td>" + JSON.stringify(data[x].LecturerID) + "</td>" + "<td>" + JSON.stringify(data[x].LecturerName) + "</td></tr>";
		}
		
		txt += "</table>"
		document.getElementById("lecturerID").innerHTML = txt_2;
		document.getElementById("lecturerName").innerHTML = txt;
	});
});