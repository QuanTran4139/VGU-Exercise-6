DELIMITER //
DROP PROCEDURE IF EXISTS GetClassOptions //
CREATE PROCEDURE GetClassOptions(
IN inCLassId VARCHAR(10),
OUT statusCode INT
)
BEGIN
	CASE
		WHEN inCLassId NOT IN (SELECT ClassId FROM Class) THEN SET statusCode = 407; -- wrong ClassID
        ELSE SET statusCode = 200; -- Success
        SELECT AYearId, SemesterId, FacultyId, FacultyName, ProgramId, ProgramName, LecturerId, LecturerName
        FROM Class
					NATURAL JOIN Semester
					NATURAL JOIN Faculty
                    NATURAL JOIN Program
                    NATURAL JOIN Module
                    NATURAL JOIN Lecturer
                    NATURAL JOIN ProgramInFacultyInAcademicYear
                    NATURAL JOIN ModuleInProgramInAcademicYear
                    NATURAL JOIN FacultyInAcademicYear
                    NATURAL JOIN Teaching
                    NATURAL JOIN AcademicYear
		WHERE ClassId = inClassId;
		END CASE;         
END//
DELIMITER ;