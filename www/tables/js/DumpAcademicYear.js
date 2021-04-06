var usedData, arrayData = [];
$(document).ready(function()
{
	$.ajax(
	{
		url: "/Questionnaire/api/academicYear?action=dump",
        type: 'GET',
        dataType: 'json',
        success: function(data)
		{
			usedData = data;
			$.each(data, function(index, value)
			{
				arrayData.push(value.AYearID);
			});
			console.log(arrayData + "fuckkkkkkkkkkkkk");
            console.log(JSON.stringify(data));
			var txt = "", x;
			txt += "<table border='1' stripe='1' id='AY'>"
			for (x in data)
			{
				txt += "<tr onclick='deleteRow(this)'><td>" + data[x].AYearID + "</td>";
				txt += "<td>" + "<button class='delAYButton' id='delAYButton'>Delete</button></td>"
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
						console.log(data);
						console.log("Sent");
					}
				});
			});
        }
	});
});

function deleteRow(r)
{
	var rIndex = r.rowIndex;
	console.log("row index: " + rIndex);
	document.getElementById("AY").deleteRow(rIndex);
	
	var valueToDelete = arrayData[rIndex];
	console.log(valueToDelete);
	var value2 = parseInt(arrayData.splice(rIndex, 1));
	console.log(typeof value2 + value2);
	var dataToDelete = {"yid":value2};
	console.log(dataToDelete);
	$.ajax(
	{
		type: 'DELETE',
		url: "/Questionnaire/api/academicYear&yid=" + value2,
		//contentType: "application/json",
		//dataType: "text",
		//data: dataToDelete,
		success: function(data)
		{
			console.log(data);
		}
	});
}