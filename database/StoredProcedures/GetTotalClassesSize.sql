/*
   Get sport centers from a city identified by the cityId
*/
DELIMITER //

DROP PROCEDURE IF EXISTS GetTotalClassesSize  //
CREATE PROCEDURE GetTotalClassesSize (
	IN inAYearId INT,
    IN inSemesterId INT,
    IN inFacultyId INT,
    IN inProgramId VARCHAR(5),
    IN inModuleId INT,
    IN inLecturerId INT,
    IN inClassId INT,
    OUT statusCode INT
)
BEGIN
	IF inAYearId NOT IN (SELECT AYearId FROM AcademicYear)
    AND inSemesterId NOT IN (SELECT SemesterId FROM Semester)
    AND inFacultyId NOT IN (SELECT FacultyId FROM Faculty)
    AND inProgramId NOT IN (SELECT ProgramId FROM Program)
    AND inModuleId NOT IN (SELECT ModuleId FROM Module)
    AND inLecturerId NOT IN (SELECT LecturerId FROM Lecturer)
    AND inClassId NOT IN (SELECT ClassId FROM Class) THEN
		SET statusCode = 405; -- invalid code
	ELSE
		SET statusCode = 200;

        SELECT SUM(Size) AS TotalClassesSize
		FROM Class
		NATURAL JOIN Lecturer
        NATURAL JOIN Module
        NATURAL JOIN Program
        NATURAL JOIN Faculty
        NATURAL JOIN Semester
        NATURAL JOIN AcademicYear
		WHERE LecturerId = inLecturerId
        AND SemesterId = inSemesterId
        AND ModuleId = inModuleId
		AND ProgramId = inProgramId
        AND FacultyId = inFacultyId
		AND AYearId = inAYearId;
	END IF;
END//

DELIMITER ;
