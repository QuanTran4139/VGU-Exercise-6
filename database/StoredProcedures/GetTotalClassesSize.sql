/*
   Get Total classes size by SUM of the "sizes" of all the classes of the given lecturer, 
   for the given module, 
   in the given program, 
   of the given faculty, 
   in the given semester, 
   for the given academic year.
*/
DELIMITER //

DROP PROCEDURE IF EXISTS GetTotalClassesSize  //
CREATE PROCEDURE GetTotalClassesSize (
	IN inAYearId VARCHAR(10),
    IN inSemesterId  VARCHAR(10),
    IN inFacultyId  VARCHAR(10),
    IN inProgramId  VARCHAR(10),
    IN inModuleId  VARCHAR(10),
    IN inLecturerId  VARCHAR(10),
    OUT statusCode INT
)
BEGIN
	IF inAYearId NOT IN (SELECT AYearId FROM AcademicYear)
    AND inSemesterId NOT IN (SELECT SemesterId FROM Semester)
    AND inFacultyId NOT IN (SELECT FacultyId FROM Faculty)
    AND inProgramId NOT IN (SELECT ProgramId FROM Program)
    AND inModuleId NOT IN (SELECT ModuleId FROM Module)
    AND inLecturerId NOT IN (SELECT LecturerId FROM Teaching) THEN
		SET statusCode = 405; -- invalid code
	ELSE
		SET statusCode = 200;

        SELECT SUM(Size) AS TotalClassesSize
		FROM Class
		NATURAL JOIN Teaching
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
