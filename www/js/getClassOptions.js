$(document).ready(function () {
	$("#SubmitClassID").on("click", function (event)
	{
		var classQuery = $("#cid").val();
		console.log(classQuery);
		if (classQuery != 0)
		{
			$.ajax({
			type: "GET",
			url: "/Questionnaire/api/class?action=getClassOptions",
			data: {"cid":cid},
			//dataType: "json",
			processData: false,
			contentType: false,
			success: function(data)
			{
				console.log(data);
			}
			})
			.done(function (data) {
				console.log(data);
			});
			// success: function(data)
			// {
				// console.log(data);
			// };
			event.preventDefault();
		}
		else
		{
			alert("Please select an option");
		}
	});
});