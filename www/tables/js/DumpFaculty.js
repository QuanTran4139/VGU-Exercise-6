// $(document).ready(function()
// {
	// $.get("/Questionnaire/api/faculty?action=dump", function(data, status)
	// {
		// console.log(JSON.stringify(data));
		// var txt = "", x;
		// txt += "<table border='1'>"
		// for (x in data)
		// {
			// // txt += "<tr><td>" + JSON.stringify(data[x].FacultyID) + "</td>";
			// // txt += "<td>" + JSON.stringify(data[x].FacultyName) + "</td></tr>";
			
			// txt += "<tr><td>" + data[x].FacultyID + "</td>";
			// txt += "<td>" + data[x].FacultyName + "</td>";
			// txt += "<td>" + "<button id='delFButton'>Delete</button></td></tr>";
		// }
		// txt += "<tr><td><input type='text'>" + "</td>";
		// txt += "<td><input type='text'>" + "</td>";
		// txt += "<td>" + "<button id='addFButton'>Add</button></td></tr>";
		// txt += "</table>";
		// document.getElementById("facultyID_2").innerHTML = txt;
	// });
// });

$(document).ready(function()
{
	$.ajax(
	{
		url: "/Questionnaire/api/faculty?action=dump",
        type: 'GET',
        dataType: 'json',
        success: function(data)
		{
            console.log(JSON.stringify(data));
			var txt = "", x;
			txt += "<table border='1' stripe='1' id='F'>"
			txt += "<th>" +'FacultyID' + "</th>"
			txt += "<th>" +'FacultyName' + "</th>"
			for (x in data)
			{
				txt += "<tr><td>" + data[x].FacultyID + "</td>";
				txt += "<td>" + data[x].FacultyName + "</td>";
				txt += "<td><button class='delFButton' id='delFButton'onclick='deleteFRow(this)'>Delete</button></td></tr>";
			}
			txt += "<tr><td><input id='facultyID' name='facultyID' type='text'>" + "</td>";
			txt += "<td><input id='facultyName' name='facultyName' type='text'>" + "</td>";
			txt += "<td>" + "<button id='addFButton'>Add</button></td></tr>";
			txt += "</table>";
			document.getElementById("facultyID_2").innerHTML = txt;
			
			$("#addFButton").on("click", function(event)
			{
				var facultyID = $('input[name="facultyID"]').val();
				var facultyName = $('input[name="facultyName"').val();
				var sendData =
				{
					"id": facultyID,
					"name": facultyName
				};
				console.log("sendData: " + JSON.stringify(sendData));
				$.ajax(
				{
					url: '/Questionnaire/api/faculty',
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