$(document).ready(function()
{
	$.ajax(
	{
		url: "/Questionnaire/api/module?action=dump",
        type: 'GET',
        dataType: 'json',
        success: function(data)
		{
            console.log(JSON.stringify(data));
			var txt = "", x;
			txt += "<table border='1' stripe='1' id='M'>"
			txt += "<th>" +' ModuleID' + "</th>"
			txt += "<th>" +' ModuleName' + "</th>"
			for (x in data)
			{
				txt += "<tr><td>" + JSON.stringify(data[x].ModuleID) + "</td>";
				txt += "<td>" + JSON.stringify(data[x].ModuleName) + "</td>";
				txt += "<td>" + "<button class='delMButton' id='delMButton'onclick='deleteMRow(this)'>Delete</button></td></tr>";
			}
			txt += "<tr><td><input id='MID' name='MID' type='text'>" + "</td>";
			txt += "<td><input id='MName' name='MName' type='text'>" + "</td>";
			txt += "<td>" + "<button id='addMButton'>Add</button></td></tr>";
			txt += "</table>"
			document.getElementById("ModuleID").innerHTML = txt;
			$("#addMButton").on("click", function(event)
			{
				var MolID = $('input[name="MID"]').val();
				var MolName = $('input[name="MName"').val();
				var sendData =
				{
					"id": MolID,
					"name": MolName
				};
				console.log("sendData: " + JSON.stringify(sendData));
				$.ajax(
				{
					url: '/Questionnaire/api/module',
					type: 'PUT',
					processData: false,
					data: JSON.stringify(sendData),
					success: function(data)
					{
						console.log(data);
						console.log("Sent");
					}
				});
			});
        }
	});
});