$(document).ready(function()
{
	$.ajax(
	{
		url: "/Questionnaire/api/class?action=dump",
        type: 'GET',
        dataType: 'json',
        success: function(data)
		{
            console.log(JSON.stringify(data));
			var txt = "", x;
			txt += "<table border='1' stripe='1' id='C'>"
			txt += "<th>" +' ClassID' + "</th>"
			txt += "<th>" +' Size' + "</th>"
			txt += "<th>" +' SemesterID' + "</th>"
			txt += "<th>" +' ModuleID' + "</th>"
			for (x in data)
			{
				txt += "<tr><td>" + data[x].ClassID + "</td>";
				txt += "<td>" + data[x].Size + "</td>";
				txt += "<td>" + data[x].SemesterID + "</td>";
				txt += "<td>" + data[x].ModuleID + "</td>";
				txt += "<td>" + "<button class='delCButton' id='delCButton'onclick='deleteCRow(this)'>Delete</button></td></tr>";
			}
			txt += "<tr><td><input id='classID' name='classID' type='text'>" + "</td>";
			txt += "<td><input id='classSize' name='classSize' type='text'>" + "</td>";
			txt += "<td><input id='semesterID' name='semesterID' type='text'>" + "</td>";
			txt += "<td><input id='moduleID' name='moduleID' type='text'>" + "</td>";
			txt += "<td>" + "<button id='addCButton'>Add</button></td></tr>";
			txt += "</table>";
			document.getElementById("ClassID").innerHTML = txt;
			
			$("#addCButton").on("click", function(event)
			{
				var classID = $('input[name="classID"]').val();
				var semesterID = $('input[name="semesterID"').val();
				var moduleID = $('input[name="moduleID"]').val();
				var size = $('input[name="classSize"]').val();
				var sizeInt = parseInt(size);
				var sendData =
				{
					"cid": classID,
					"size": sizeInt,
					"sid": semesterID,
					"mid": moduleID
				};
				console.log("sendData: " + typeof sendData + JSON.stringify(sendData));
				$.ajax(
				{
					url: '/Questionnaire/api/class',
					type: 'PUT',
					processData: false,
					//contentType: 'application/json',
					dataType: 'text',
					data: JSON.stringify(sendData),
					success: function(data)
					{
						console.log("sendData: " + typeof sendData + JSON.stringify(sendData));
						console.log(data);
						console.log("Sent");
					},
					error: function(result)
					{
						console.log(result.status + ' ' + result.statusText);
					}
				});
			});
        }
	});
});