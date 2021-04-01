$(document).ready(function()
{
	var txt ="";
	let arrClass = [];
	$.getJSON("/Questionnaire/api/class?action=dump", function(data, status)
	{
		let arr_class = [];
		$.each(data, function(index, value)
		{
			arr_class.push(value.ClassID);
			arrClass = data;
		});
		$.each(arr_class, function(index, value)
		{
			$('#cid').append('<option value="' + value + '">' + value + '</option>');
		});
	});
	
	$("#SubmitClassID").on("click", function (event)
	{
		var classQuery = $('#cid').val();
		console.log(classQuery);
		if (classQuery != 0)
		{
			$.ajax({
			type: "GET",
			url: "/Questionnaire/api/class?action=getClassOptions",
			data: {cid:$("#cid").val()},
			success: function(data)
			{
				console.log(data);
				console.log(data.Semesters[0].SemesterID);
			}
			})
			event.preventDefault();
		}
		else
		{
			alert("Please select an option");
		}
	});
	
	//document.getElementById("").innerHTML = txt;
});