$(document).ready(function()
{
	$.ajax(
	{
		url: "/Questionnaire/api/academicYear?action=dump",
        type: 'GET',
        dataType: 'json',
        success: function(data)
		{
            console.log(JSON.stringify(data));
			var txt = "", x;
			txt += "<table border='1' stripe='1' id='AY'>"
			for (x in data)
			{
				txt += "<tr><td>" + data[x].AYearID + "</td>";
				txt += "<td>" + "<button id='delAYButton'>Delete</button></td>"
			}
			txt += "<tr><td><input id='AY' name='AY' type='text'>" + "</td>";
			txt += "<td>" + "<button id='addAYButton'>Add</button></td></tr>";
			txt += "</table>";
			document.getElementById("AYearID_2").innerHTML = txt;
			
			$("#addAYButton").on("click", function(event)
			{
				var value = $('input[name="AY"]').val();
				var valueInt = parseInt(value);
				var sendData = {"yid": valueInt};
				console.log("sendData: " + JSON.stringify(sendData));
				console.log(typeof value + value);
				console.log(typeof valueInt + valueInt);
				$.ajax(
				{
					url: '/Questionnaire/api/academicYear',
					type: 'PUT',
					processData: false,
					data: JSON.stringify(sendData),
					success: function(data)
					{
						console.log("Sent");
					}
				});
			});
        }
	});
});