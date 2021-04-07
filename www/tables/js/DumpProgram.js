$(document).ready(function()
{
	$.ajax(
	{
		url: "/Questionnaire/api/program?action=dump",
        type: 'GET',
        dataType: 'json',
        success: function(data)
		{
            console.log(JSON.stringify(data));
			var txt = "", x;
			txt += "<table border='1' stripe='1' id='P'>"
			txt += "<th>" +' ProgramID' + "</th>"
			txt += "<th>" +' ProgramName' + "</th>"
			for (x in data)
			{
				txt += "<tr><td>" + JSON.stringify(data[x].ProgramID) +"</td>";
				txt += "<td>" + JSON.stringify(data[x].ProgramName) + "</td>";
				txt += "<td>" + "<button class='delPButton' id='delPButton'onclick='deletePRow(this)'>Delete</button></td></tr>";
			}
			txt += "<tr><td><input id='PID' name='PID' type='text'>" + "</td>";
			txt += "<td><input id='PName' name='PName' type='text'>" + "</td>";
			txt += "<td>" + "<button id='addPButton'>Add</button></td></tr>";
			txt += "</table>"
			document.getElementById("ProgramID").innerHTML = txt;
			$("#addPButton").on("click", function(event)
			{
				var PID = $('input[name="PID"]').val();
				var PName = $('input[name="PName"').val();
				var sendData =
				{
					"id": PID,
					"name": PName
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