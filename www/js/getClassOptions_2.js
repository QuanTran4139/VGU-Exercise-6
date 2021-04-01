var semester, faculty, program, lecturerName;
var lecturerQuery;
var lecturerIndex;
var classQuery;
$(document).ready(function()
{
	var txt ="";
	var arrClass = [],arrLec=[];
	$.getJSON("/Questionnaire/api/class?action=dump", function(data, status)
	{
		let arrClass2 = [];
		$.each(data, function(index, value)
		{
			arrClass2.push(value.ClassID);
			arrClass = data;
		});
		$.each(arrClass2, function(index, value)
		{
			$('#cid').append('<option value="' + value + '">' + value + '</option>');
		});
	});
 
	$("#cid").on("change", function (event)
	{
		$("#lecturerID").val("---Select---").trigger('change');
		classQuery = $('#cid').val();
		console.log(classQuery);
		if (classQuery != 0)
		{
			$.ajax(
			{
				type: "GET",
				url: "/Questionnaire/api/class?action=getClassOptions",
				data: {cid:$("#cid").val()},
				success: function(data)
				{
					console.log(data);
					console.log(data.Semesters[0].SemesterID);
					var arrLec2 = [];
					$.each(data.Lecturers, function(index, value)
					{
						arrLec2.push(value.LecturerName);
						arrLec=data.Lecturers;
					});
					$.each(arrLec2, function(index, value)
					{
						$('#lecturerID').append('<option value="' + value + '">' + value + '</option>');
						$("#lecturerID").on("change", function(event)
						{
							lecturerQuery = $('#lecturerID').val();
							if (lecturerQuery != 0 || lecturerQuery != '')
							{
								lecturerIndex = arrLec2.indexOf(lecturerQuery);
								semester = data.Semesters[lecturerIndex].SemesterID;
								faculty = data.Faculties[lecturerIndex].FacultyName;
								program = data.Programs[lecturerIndex].ProgramName;
								
								document.getElementById("SemesterID").innerHTML = semester;
								document.getElementById("ProgramName").innerHTML = program;
								document.getElementById("FacultyName").innerHTML = faculty;
							}
							else
							{
								alert("Please select an option");
							}
						});
					});
					console.log(arrLec2);
				}
			})
			event.preventDefault();
		}
		else
		{
			alert("Please select an option");
		}
	});
});