$(document).ready(function()
	{
		//var data = $('#inputFacultyInAYear').serializeObject();
		 $.ajax({
			type: 'GET',
			url: "/Questionnaire/api/academicYear?action=dump",
			dataType : 'json',
			data: JSON.stringify(data),
			success: function (response)
			{
				console.log(response);   
			},
			error:function(request, textStatus, errorThrown)
			{
				alert(errorThrown);
			}
		});
	});