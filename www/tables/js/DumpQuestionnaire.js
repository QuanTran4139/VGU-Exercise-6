$(document).ready(function()
{
	$.ajax(
	{
		url: "/Questionnaire/api/questionnaire?action=dump",
        type: 'GET',
        dataType: 'json',
        success: function(data)
		{
            console.log(JSON.stringify(data));
			var txt = "", x;
			txt += "<table border='1' stripe='1' id='Q'>"
			txt += "<th>" +' LecturerID' + "</th>"
			txt += "<th>" +' ClassID' + "</th>"
			txt += "<th>" +' QuestionnaireID' + "</th>"
			txt += "<th>" +' Total students' + "</th>"
			for (x in data)
			{
				txt += "<tr><td>" + JSON.stringify(data[x].LecturerID) + "</td>";
				txt += "<td>" + JSON.stringify(data[x].ClassID) + "</td>";
				txt += "<td>" + JSON.stringify(data[x].QuestionnaireID) + "</td>";
				txt += "<td>" + JSON.stringify(data[x].gender) + "</td>";
				txt += "<td>" + "<button class='delQButton' id='delQButton'onclick='deleteQRow(this)'>Delete</button></td></tr>";
			}
			txt += "<tr><td><input id='QID' name='QID' type='text'>" + "</td>";
			txt += "<td><input id='LID' name='LID' type='text'>" + "</td>";
			txt += "<td><input id='CID' name='CID' type='text'>" + "</td>";
			txt += "<td><input id='Gender' name='Gender' type='text'>" + "</td>";
			txt += "<td><input id='Comment' name='Comment' type='text'>" + "</td>";
			txt += "<td>" + "<button id='addQButton'>Add</button></td></tr>";
			txt += "</table>"
			document.getElementById("QuestionaireID").innerHTML = txt;
			/*$("#addQButton").on("click", function(event)
			{
				var QID = $('input[name="QID"]').val();
				var LID = $('input[name="LID"').val();
				var CID = $('input[name="CID"').val();
				var Gender = $('input[name="Gender"').val();
				var Comment = $('input[name="Comment"').val();
				var sendData =
				{
					"lid": LID,
					"cid": CID,
					"gender": Gender,
					"cid": CID,
					"cid": CID,
				};
				console.log("sendData: " + JSON.stringify(sendData));
				$.ajax(
				{
					url: '/Questionnaire/api/questionnaire',
					type: 'PUT',
					processData: false,
					data: JSON.stringify(sendData),
					success: function(data)
					{
						console.log(data);
						console.log("Sent");
					}
				});
			});*/
        }
	});
});