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
			console.log(arrayData);
            console.log(JSON.stringify(data));
			var txt = "", x;
			txt += "<table border='1' stripe='1' id='AY'>"
			txt += "<th>" +' AcademicYearID' + "</th>"
			for (x in data)
			{
				txt += "<tr id ='AYear'><td>" + data[x].AYearID + "</td>";
				txt += "<td>" + "<button class='delAYButton' id='data[x].AYearID'onclick='deleteRow(this)'>Delete</button></td>"
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
	var rIndex = r.parentNode.parentNode.rowIndex;
	console.log("row index: " + rIndex);
	var valueToDelete = arrayData[rIndex-1];
	console.log(valueToDelete);
	document.getElementById("AY").deleteRow(rIndex);
	var value2 = parseInt(arrayData.splice(rIndex-1, 1));
	console.log(typeof value2 + value2);
	var dataToDelete = {"yid":value2};
	console.log(dataToDelete);
	sendDelete(value2);
};
function sendDelete(id){
		$.ajax
		({
			type: 'Delete',
			url: "/Questionnaire/api/academicYear?yid=" + id,
			//contentType: "application/json",
			//dataType: "text",
			success: function(data,textStatus,jqXHR)
			{
				alert(textStatus);
				console.log(data);
			}
		});
};