$(document).ready(function()
{
	$.ajax(
	{
		url: "/Questionnaire/api/lecturer?action=dump",
        type: 'GET',
        dataType: 'json',
        success: function(data)
		{
            console.log(JSON.stringify(data));
			var txt = "", x;
			txt += "<table border='1' stripe='1' id='L'>"
			txt += "<th>" +' LecturerID' + "</th>"
			txt += "<th>" +' LecturerName' + "</th>"
			for (x in data)
			{
				txt += "<tr><td>" + JSON.stringify(data[x].LecturerID) + "</td>";
				txt += "<td>" + JSON.stringify(data[x].LecturerName) + "</td>";
				txt += "<td>" + "<button class='delLButton' id='delLButton'onclick='deleteLRow(this)'>Delete</button></td></tr>";
			}
			txt += "<tr><td><input id='LecID' name='LecID' type='text'>" + "</td>";
			txt += "<td><input id='LecName' name='LecrName' type='text'>" + "</td>";
			txt += "<td>" + "<button id='addLButton'>Add</button></td></tr>";
			txt += "</table>"
			document.getElementById("LecturerID").innerHTML = txt;
			$("#addLButton").on("click", function(event)
			{
				var LecID = $('input[name="LecID"]').val();
				var LecName = $('input[name="LecName"').val();
				var sendData =
				{
					"id": LecID,
					"name": LecName
				};
				console.log("sendData: " + JSON.stringify(sendData));
				$.ajax(
				{
					url: '/Questionnaire/api/lecturer',
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