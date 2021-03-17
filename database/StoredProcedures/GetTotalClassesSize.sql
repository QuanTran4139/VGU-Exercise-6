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
	IN inLecturerName  VARCHAR(100),
    IN inModuleName  VARCHAR(100),
    IN inProgramName  VARCHAR(100),
    IN inFacultyName  VARCHAR(100),
	IN inSemesterName  VARCHAR(100),
    IN inAYearId VARCHAR(10),
    OUT statusCode INT
)
BEGIN
	IF inAYearId NOT IN (SELECT AYearId FROM AcademicYear)
    AND inSemesterName NOT IN (SELECT SemesterName FROM Semester)
    AND inFacultyName NOT IN (SELECT FacultyName FROM Faculty)
    AND inProgramName NOT IN (SELECT ProgramName FROM Program)
    AND inModuleName NOT IN (SELECT ModuleName FROM Module)
    AND inLecturerName NOT IN (SELECT LecturerName FROM Lecturer) THEN
		SET statusCode = 405; -- invalid code
	ELSE
		SET statusCode = 200;

        SELECT SUM(Size) AS TotalClassesSize
		FROM Class
        NATURAL JOIN Lecturer
		NATURAL JOIN Teaching
        NATURAL JOIN Module
        NATURAL JOIN Program
        NATURAL JOIN Faculty
        NATURAL JOIN Semester
        NATURAL JOIN AcademicYear
		WHERE LecturerName = inLecturerName
        AND SemesterName = inSemesterName
        AND ModuleName = inModuleName
		AND ProgramName = inProgramName
        AND FacultyName = inFacultyName
		AND AYearId = inAYearId;
	END IF;
    IF NOT EXISTS ( SELECT SUM(Size)
		FROM Class
        NATURAL JOIN Lecturer
		NATURAL JOIN Teaching
        NATURAL JOIN Module
        NATURAL JOIN Program
        NATURAL JOIN Faculty
        NATURAL JOIN Semester
        NATURAL JOIN AcademicYear
		WHERE LecturerName = inLecturerName
        AND SemesterName = inSemesterName
        AND ModuleName = inModuleName
		AND ProgramName = inProgramName
        AND FacultyName = inFacultyName
		AND AYearId = inAYearId) THEN
		SELECT 0 AS TotalClassesSize;
	END IF;
END//

DELIMITER ;
