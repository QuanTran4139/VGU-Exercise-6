$(document).ready(function()
{
	$.get("/Questionnaire/api/questionnaire?action=dump", function(data, status)
	{
		//alert("Data: " + data + " Status: " + status);
		console.log(JSON.stringify(data));
	var txt = "", x;
		txt += "<table border='1'>"
		for (x in data)
		{
			txt += "<tr><td>" + JSON.stringify(data[x].QuestionnaireID) + "</td>";
			txt += "<td>" + JSON.stringify(data[x].LecturerID) + "</td>";
			txt += "<td>" + JSON.stringify(data[x].ClassID) + "</td>";
			txt += "<td>" + JSON.stringify(data[x].gender) + "</td>";
		}
		txt += "</table>"
		document.getElementById("QuestionaireID").innerHTML = txt;
	});
});