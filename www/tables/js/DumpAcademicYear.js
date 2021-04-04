$(document).ready(function()
{
	$.get("/Questionnaire/api/academicYear?action=dump", function(data, status)
	{
		//console.log(JSON.stringify(data));
		/*for (var i = 0; i < data.length; i++)
		{
			document.getElementById("AYearID").innerHTML = data[i];
		}*/
		
		console.log(JSON.stringify(data));
		var txt = "", x;
		var a=0;
		txt += "<table border='1' stripe='1'>"
		for (x in data)
		{
			txt += "<tr><td>" + JSON.stringify(data[x].AYearID) + "</td>";
		}
		txt += "</table>"
		document.getElementById("AYearID_2").innerHTML = txt;
	});
	
});