$(document).ready(function()
{
	$.get("/Questionnaire/api/lecturer?action=dump", function(data, status)
	{
		console.log(JSON.stringify(data));
<<<<<<< HEAD
		
		var txt = "", x;
		
		txt += "<table border='1'>"
		for (x in data)
		{
			txt += "<tr><td>" + JSON.stringify(data[x].LecturerID) + "</td>";
			txt += "<td>" + JSON.stringify(data[x].LecturerName) + "</td></tr>";
		}
		txt += "</table>"
		document.getElementById("LecturerID").innerHTML = txt;
=======
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
>>>>>>> 464a93eb38d4ca3de85b007f854c10ea9a8c97b4
	});
});