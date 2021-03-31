$(document).ready(function()
{
	$.get("/Questionnaire/api/module?action=dump", function(data, status)
	{
		console.log(JSON.stringify(data));
	var txt = "", x;
		txt_2 = "";
	txt += "<table border='1'>"
		for (x in data)
		{
			txt += "<tr><td>" + JSON.stringify(data[x].ModuleID) + "</td>";
			txt += "<td>" + JSON.stringify(data[x].ModuleName) + "</td></tr>";
		}
		txt += "</table>"
		document.getElementById("ModuleID").innerHTML = txt;
		document.getElementById("ModuleName").innerHTML = txt_2;
	});
});
