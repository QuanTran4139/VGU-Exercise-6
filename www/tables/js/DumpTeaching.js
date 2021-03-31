$(document).ready(function()
{
	$.get("/Questionnaire/api/teaching?action=dump", function(data, status)
	{
		console.log(JSON.stringify(data));
		var txt = "", x;
		var txt_2 = "";
		txt += "<table border='1'>"
		for (x in data)
		{
			txt += "<tr><td>" + JSON.stringify(data[x].LecturerID) + "</td>" + "<td>" + JSON.stringify(data[x].ClassID) + "</td></tr>";
		}
		
		txt += "</table>"
		document.getElementById("lecturerID_2").innerHTML = txt_2;
		document.getElementById("classID").innerHTML = txt;
	});
});