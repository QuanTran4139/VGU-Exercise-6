$(document).ready(function()
{
	$.ajax(
	{
		url: "/Questionnaire/api/teaching?action=dump",
        type: 'GET',
        dataType: 'json',
        success: function(data)
		{
            console.log(JSON.stringify(data));
			var txt = "", x;
			txt += "<table border='1' stripe='1' id='T'>"
			txt += "<th>" +' LecturerID' + "</th>"
			txt += "<th>" +' ClassID' + "</th>"
			for (x in data)
			{
				txt += "<tr><td>" + JSON.stringify(data[x].LecturerID) + "</td>"; 
				txt += "<td>" + JSON.stringify(data[x].ClassID) + "</td>";
				txt += "<td>" + "<button class='delTButton' id='delTButton'onclick='deleteTRow(this)'>Delete</button></td></tr>";
			}
			txt += "<tr><td><input id='LeID' name='LeID' type='text'>" + "</td>";
			txt += "<td><input id='classId' name='classId' type='text'>" + "</td>";
			txt += "<td>" + "<button id='addTButton'>Add</button></td></tr>";
			txt += "</table>"
			document.getElementById("lecturerID_2").innerHTML = txt;
			$("#addTButton").on("click", function(event)
			{
				var LeID = $('input[name="LeID"]').val();
				var classId = $('input[name="classId"').val();
				var sendData =
				{
					"lid": LeID,
					"cid": classId
				};
				console.log("sendData: " + JSON.stringify(sendData));
				$.ajax(
				{
					url: '/Questionnaire/api/teaching',
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