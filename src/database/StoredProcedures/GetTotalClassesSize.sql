DELIMITER //

DROP PROCEDURE IF EXISTS GetTotalClassesSize  //
CREATE PROCEDURE GetTotalClassesSize (
	IN inAYearId INT,
    IN inSemesterId  VARCHAR(10),
	IN inFacultyName  VARCHAR(100),
    IN inProgramName  VARCHAR(100),
    IN inModuleName  VARCHAR(100),
    IN inLecturerId  VARCHAR(10),
    OUT statusCode INT
)
BEGIN
	CASE
		WHEN inAYearId NOT IN (SELECT AYearId FROM AcademicYear) 			THEN SET statusCode = 401; -- wrong AY
		WHEN inSemesterId NOT IN (SELECT SemesterId FROM Semester) 			THEN SET statusCode = 402; -- wrong SEM
		WHEN inFacultyName NOT IN (SELECT FacultyName FROM Faculty) 		THEN SET statusCode = 403; -- wrong FAC
		WHEN inProgramName NOT IN (SELECT ProgramName FROM Program) 		THEN SET statusCode = 404; -- wrong PRO
		WHEN inModuleName NOT IN (SELECT ModuleName FROM Module) 			THEN SET statusCode = 405; -- wrong MOD
		WHEN inLecturerId NOT IN (SELECT LecturerId FROM Teaching) 			THEN SET statusCode = 406; -- wrong LEC
		ELSE SET statusCode = 200;
	
        SELECT SUM(Size) AS TotalClassesSize
		FROM Class
		NATURAL JOIN Teaching
        NATURAL JOIN Module
        NATURAL JOIN Program
        NATURAL JOIN Faculty
        NATURAL JOIN Semester
        NATURAL JOIN AcademicYear
		NATURAL JOIN ClassInSemesterInModule
        NATURAL JOIN ProgramInFacultyInAcademicYear
		NATURAL JOIN ModuleInProgramInAcademicYear
        NATURAL JOIN FacultyInAcademicYear
		WHERE LecturerId = inLecturerId
        AND SemesterId = inSemesterId
        AND ModuleName = inModuleName
		AND ProgramName = inProgramName
        AND FacultyName = inFacultyName
		AND AYearId = inAYearId;
	END CASE;
END//

DELIMITER ;