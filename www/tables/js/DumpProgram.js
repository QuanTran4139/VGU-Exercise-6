$(document).ready(function()
{
	$.get("/Questionnaire/api/program?action=dump", function(data, status)
	{
		//alert("Data: " + data + " Status: " + status);
		console.log(JSON.stringify(data));
		
		var txt = "", x;
		txt += "<table border='1'>"
		for (x in data)
		{
			txt += "<tr><td>" + JSON.stringify(data[x].ProgramID) +"</td>";
			txt += "<td>" + JSON.stringify(data[x].ProgramName) + "</td></tr>";
		}
		txt += "</table>"
		document.getElementById("ProgramID").innerHTML = txt;
		
	});	
});