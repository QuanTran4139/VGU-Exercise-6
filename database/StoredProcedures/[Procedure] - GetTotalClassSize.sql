DELIMITER //

DROP PROCEDURE IF EXISTS GetTotalClassesSize  //
CREATE PROCEDURE GetTotalClassesSize (
	IN inLecturerID  VARCHAR(100),
    IN inModuleName  VARCHAR(100),
    IN inProgramName  VARCHAR(100),
    IN inFacultyName  VARCHAR(100),
	IN inSemesterName  VARCHAR(100),
    IN inAYearId VARCHAR(10),
    OUT statusCode INT
)
BEGIN
	CASE
		WHEN inAYearId NOT IN (SELECT AYearId FROM AcademicYear) 			THEN SET statusCode = 401; -- wrong AY
		WHEN inSemesterName NOT IN (SELECT SemesterName FROM Semester) 		THEN SET statusCode = 402; -- wrong SEM
		WHEN inFacultyName NOT IN (SELECT FacultyName FROM Faculty) 		THEN SET statusCode = 403; -- wrong FAC
		WHEN inProgramName NOT IN (SELECT ProgramName FROM Program) 		THEN SET statusCode = 404; -- wrong PRO
		WHEN inModuleName NOT IN (SELECT ModuleName FROM Module) 			THEN SET statusCode = 405; -- wrong MOD
		WHEN inLecturerID NOT IN (SELECT LecturerId FROM Lecturer) 			THEN SET statusCode = 406; -- wrong LEC
		ELSE SET statusCode = 200;
	
        SELECT SUM(Size) AS TotalClassesSize
		FROM Class
        NATURAL JOIN Lecturer L
		NATURAL JOIN Teaching T
        NATURAL JOIN Module M
        NATURAL JOIN Program P
        NATURAL JOIN Faculty F
        NATURAL JOIN Semester S
        NATURAL JOIN AcademicYear A
		NATURAL JOIN classinsemestermoduleacademicyear
        NATURAL JOIN facultyinacademicyear
        NATURAL JOIN moduleinprograminacademicyear
        NATURAL JOIN programinfacultyinacademicyear
        NATURAL JOIN semesterinacademicyear
        
		WHERE L.LecturerId = inLecturerID
        AND S.SemesterName = inSemesterName
        AND M.ModuleName = inModuleName
		AND P.ProgramName = inProgramName
        AND F.FacultyName = inFacultyName
		AND A.AYearId = inAYearId;
	END CASE;
    IF NOT EXISTS ( SELECT SUM(Size)
		FROM Class
        NATURAL JOIN Lecturer L
		NATURAL JOIN Teaching T
        NATURAL JOIN Module M
        NATURAL JOIN Program P
        NATURAL JOIN Faculty F
        NATURAL JOIN Semester S
        NATURAL JOIN AcademicYear A
		NATURAL JOIN classinsemestermoduleacademicyear
        NATURAL JOIN facultyinacademicyear
        NATURAL JOIN moduleinprograminacademicyear
        NATURAL JOIN programinfacultyinacademicyear
        NATURAL JOIN semesterinacademicyear
        
		WHERE L.LecturerId = inLecturerID
        AND S.SemesterName = inSemesterName
        AND M.ModuleName = inModuleName
		AND P.ProgramName = inProgramName
        AND F.FacultyName = inFacultyName
		AND A.AYearId = inAYearId) THEN
		SELECT 0 AS TotalClassesSize;
	END IF;
END//

DELIMITER ;