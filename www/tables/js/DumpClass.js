$(document).ready(function()
{
	$.get("/Questionnaire/api/class?action=dump", function(data, status)
	{
		
	//alert("Data: " + data + " Status: " + status);
		console.log(JSON.stringify(data));
		
	var txt = "", x;
		
		txt += "<table border='1'>"
		for (x in data)
		{
			txt += "<tr><td>" + JSON.stringify(data[x].ClassID) + "</td>";
			txt += "<td>" + JSON.stringify(data[x].Size) + "</td>";
			txt += "<td>" + JSON.stringify(data[x].SemesterID) + "</td>";
			txt += "<td>" + JSON.stringify(data[x].ModuleID) + "</td></tr>";
		}
		txt += "</table>"
		document.getElementById("ClassID").innerHTML = txt;

	});
});


