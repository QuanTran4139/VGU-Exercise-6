$(document).ready(function()
{
	$.ajax(
	{
		url: "/Questionnaire/api/semester?action=dump",
        type: 'GET',
        dataType: 'json',
        success: function(data)
		{
            console.log(JSON.stringify(data));
			var txt = "", x;
			txt += "<table border='1' stripe='1' id='S'>"
			txt += "<th>" +' SemesterID' + "</th>"
			txt += "<th>" +' AcademicYearID' + "</th>"
			for (x in data)
			{
				txt += "<tr><td>" + JSON.stringify(data[x].SemesterID) + "</td>";
				txt += "<td> " + JSON.stringify(data[x].AYearID) + "</td>";
				txt += "<td>" + "<button class='delSButton' id='data[x].AYearID'onclick='deleteSRow(this)'>Delete</button></td></tr>";
			}
			txt += "<tr><td><input id='SID' name='SID' type='text'>" + "</td>";
			txt += "<td><input id='AYID' name='AYID' type='text'>" + "</td>";
			txt += "<td>" + "<button id='addSButton'>Add</button></td></tr>";
			txt += "</table>";
			document.getElementById("semesterID").innerHTML = txt;
			$("#addSButton").on("click", function(event)
			{
				var SID = $('input[name="SID"]').val();
				var AYID = $('input[name="AYID"').val();
				var AYIDInt = parseInt(AYID);
				var sendData =
				{
					"sid": SID,
					"yid": AYIDInt
				};
				console.log("sendData: " + JSON.stringify(sendData));
				$.ajax(
				{
					url: '/Questionnaire/api/semester',
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