var usedData, arrayData = [];
$(document).ready(function()
{
	$.ajax(
	{
		url: "/Questionnaire/api/facultyInAcademicYear?action=dump",
        type: 'GET',
        dataType: 'json',
        success: function(data)
		{
			/*usedData = data;
			$.each(data, function(index, value)
			{
				arrayData.push(value.AYearID);
			});
			console.log(arrayData);*/
            console.log(JSON.stringify(data));
			var txt = "", x;
			txt += "<table border='1' stripe='1' id='FAY'>"
			txt += "<th>" +' FacultyID' + "</th>"
			txt += "<th>" +' AYearID' + "</th>"
			for (x in data)
			{
				txt += "<tr><td>" + JSON.stringify(data[x].FacultyID) + "</td>";
				txt += "<td>" + JSON.stringify(data[x].AYearID) + "</td>";
				txt += "<td>" + "<button class='delFAYButton' id='delFAYButton'onclick='deleteFAYRow(this)'>Delete</button></td></tr>";
			}
			txt += "<tr><td><input id='facID' name='facID' type='text'>" + "</td>";
			txt += "<td><input id='AYearID' name='AYearID' type='text'>" + "</td>";
			txt += "<td>" + "<button id='addFAYButton'>Add</button></td></tr>";
			txt += "</table>";
			document.getElementById("facultyID").innerHTML = txt;
			$("#addFAYButton").on("click", function(event)
			{
				var facID = $('input[name="facID"]').val();
				var AYearID = $('input[name="AYearID"').val();
				var AYearIDInt = parseInt(AYearID);
				var sendData =
				{
					"fid": facID,
					"yid": AYearIDInt
				};
				console.log("sendData: " + JSON.stringify(sendData));
				$.ajax(
				{
					url: '/Questionnaire/api/facultyInAcademicYear',
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