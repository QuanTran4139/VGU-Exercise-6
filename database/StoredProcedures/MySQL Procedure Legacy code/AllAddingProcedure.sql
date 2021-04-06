DELIMITER //
DROP PROCEDURE IF EXISTS AddAcademicYear //
CREATE PROCEDURE AddAcademicYear(
IN inAYearId INT,
OUT statusCode INT
)
BEGIN
	CASE 
		WHEN (LENGTH(inAYearId) != 4) THEN SET statusCode = 401;
		WHEN inAYearId IN (SELECT AYearId FROM AcademicYear) THEN SET statusCode = 490;
		ELSE SET statusCode = 200;
        INSERT INTO AcademicYear (AYearId)
		VALUES (inAYearId);
        END CASE;
END//
/*--------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS AddSemester //
CREATE PROCEDURE AddSemester(
IN inAYearId INT,
IN inSemester ENUM ('WS', 'SS'),
OUT statusCode INT
)
BEGIN
	CASE
		WHEN inAYearId NOT IN (SELECT AYearId FROM AcademicYear) THEN SET statusCode = 401;
        WHEN CONCAT(inSemester, (SELECT RIGHT (inAYearId , 2))) IN (SELECT SemesterId FROM Semeseter) THEN SET statusCode = 490;
        ELSE SET statusCode = 200;
        INSERT INTO Semester (SemesterId, AYearId)
		VALUES (CONCAT(inSemester, (SELECT RIGHT (inAYearId , 2))),inAYearId);
	END CASE;
END//
/*--------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS AddFaculty //
CREATE PROCEDURE AddFaculty(
IN inFacultyId VARCHAR(10),
IN inFacultyName VARCHAR(100),
OUT statusCode INT
)
BEGIN
	CASE
		WHEN (SELECT REGEXP_LIKE(inFacultyId, '^[A-Z]+$', 'c') = 0) THEN SET statusCode = 413;
        WHEN (SELECT REGEXP_LIKE(inFacultyName, '(^[A-Z][a-z]+){1}(\\s[A-Z][a-z]+)*$', 'c') = 0) THEN SET statusCode = 403;
        WHEN inFacultyId IN (SELECT FacultyId FROM Faculty) THEN SET statusCode = 490;
        WHEN inFacultyName IN (SELECT FacultyName FROM Faculty) THEN SET statusCode = 490;
        ELSE SET statusCode = 200;
		INSERT INTO Faculty (FacultyId, FacultyName)
		VALUES (inFacultyId, inFacultyName);
	END CASE;
END//
/*--------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS AddFacultyInAcademicYear //
CREATE PROCEDURE AddFacultyInAcademicYear(
IN inFacultyId VARCHAR(10),
IN inAYearId INT,
OUT statusCode INT
)
BEGIN
	CASE
		WHEN inAYearId NOT IN (SELECT AYearId FROM AcademicYear) THEN SET statusCode = 401;
        WHEN inFacultyId NOT IN (SELECT FacultyId FROM Faculty) THEN SET statusCode = 413;
        WHEN inAYearId IN (SELECT AYearId FROM FacultyInAcademicYear) 
        AND  inFacultyId IN (SELECT FacultyId FROM FacultyInAcademicYear) THEN SET statusCode = 490;
        ELSE SET statusCode = 200;
        INSERT INTO FacultyInAcademicYear (FacultyId, AYearId)
		VALUES (inFacultyId, inAYearId);
        END CASE;
END//
/*--------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS AddProgram //
CREATE PROCEDURE AddProgram(
IN inProgramId VARCHAR(10),
IN inProgramName VARCHAR(100),
OUT statusCode INT
)
BEGIN
	CASE 
		WHEN (SELECT REGEXP_LIKE(inProgramId, '^[A-Z]+$', 'c') = 0) THEN SET statusCode = 414;
        WHEN (SELECT REGEXP_LIKE(inProgramName, '(^[A-Z][a-z]+){1}(\\s[A-Z][a-z]+)*$', 'c') = 0) THEN SET statusCode = 404;
        WHEN inProgramId IN (SELECT ProgramId FROM Program) THEN SET statusCode = 490;
        WHEN inProgramName IN (SELECT ProgramName FROM Program) THEN SET statusCode = 490;
		ELSE SET statusCode = 200;
		INSERT INTO Program (ProgramId, ProgramName)
		VALUES (inProgramId, inProgramName);
	END CASE;
END//
/*--------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS AddProgramInFacultyInAcademicYear //
CREATE PROCEDURE AddProgramInFacultyInAcademicYear(
IN inProgramId VARCHAR(10),
IN inFacultyId VARCHAR(10),
IN inAYearId INT,
OUT statusCode INT
)
BEGIN
	CASE
		WHEN inAYearId NOT IN (SELECT AYearId FROM AcademicYear) THEN SET statusCode = 401;
        WHEN inFacultyId NOT IN (SELECT FacultyId FROM Faculty) THEN SET statusCode = 413;
        WHEN inProgramId NOT IN (SELECT ProgramId FROM Program) THEN SET statusCode = 414;
        WHEN inAYearId IN (SELECT AYearId FROM ProgramInFacultyInAcademicYear)
        AND  inFacultyId IN (SELECT FacultyId FROM ProgramInFacultyInAcademicYear)
        AND  inProgramId IN (SELECT ProgramId FROM ProgramInFacultyInAcademicYear) THEN SET statusCode = 490;
        ELSE SET statusCode = 200;
        INSERT INTO ProgramInFacultyInAcademicYear (ProgramId, FacultyId, AYearId)
		VALUES (inProgramId, inFacultyId, inAYearId);
	END CASE;
END//
/*--------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS AddLecturer //
CREATE PROCEDURE AddLecturer(
IN inLecturerId VARCHAR(10),
IN inLecturerName VARCHAR(100),
OUT statusCode INT
)
BEGIN
	CASE
		-- WHEN (SELECT REGEXP_LIKE(inLecturerId, '[A-Z0-9]+', 'c') = 0 THEN SET statusCode = 416;
        WHEN (SELECT REGEXP_LIKE(inLecturerName, '(^[A-Z][a-z]+){1}(\\s[A-Z][a-z]+)*$', 'c') = 0) THEN SET statusCode = 406;
        WHEN inLecturerId IN (SELECT LecturerId FROM Lecturer) THEN SET statusCode = 490;
        ELSE SET statusCode = 200;
		INSERT INTO Lecturer (LecturerId, LecturerName)
		VALUES (inLecturerId, inLecturerName);
	END CASE;
END//
/*--------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS AddModule //
CREATE PROCEDURE AddModule(
IN inModuleId VARCHAR(10),
IN inModuleName VARCHAR(100),
OUT statusCode INT
)
BEGIN
	CASE
		WHEN (SELECT REGEXP_LIKE(inModuleId, '[A-Z0-9]+', 'c') = 0) THEN SET statusCode = 415;
        WHEN (SELECT REGEXP_LIKE(inModuleName, '(^[A-Z][a-z]+){1}(\\s[A-Z][a-z]+)*$', 'c') = 0) THEN SET statusCode = 405;
        WHEN inModuleName IN (SELECT ModuleId FROM Module) THEN SET statusCode = 490;
		ELSE SET statusCode = 200;
		INSERT INTO Module (ModuleId, ModuleName)
		VALUES (inModuleId, inModuleName);
	END CASE;
END//
/*--------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS AddModuleInProgramInAcademicYear //
CREATE PROCEDURE AddModuleInProgramInAcademicYear(
IN inProgramId VARCHAR(10),
IN inModuleId VARCHAR(10),
IN inAYearId INT,
OUT statusCode INT
)
BEGIN
	CASE
		WHEN inAYearId NOT IN (SELECT AYearId FROM AcademicYear) THEN SET statusCode = 401;
        WHEN inModuleId NOT IN (SELECT ModuleId FROM Module) THEN SET statusCode = 415;
        WHEN inProgramId NOT IN (SELECT ProgramId FROM Program) THEN SET statusCode = 414;
        WHEN inAYearId IN (SELECT AYearId FROM ModuleInProgramInAcademicYear)
        AND  inModuleId IN (SELECT ModuleId FROM ModuleInProgramInAcademicYear)
        AND  inProgramId IN (SELECT ProgramId FROM ModuleInProgramInAcademicYear) THEN SET statusCode = 490;
        ELSE SET statusCode = 200;
        INSERT INTO ModuleInProgramInAcademicYear (ModuleId, ProgramId, AYearId)
		VALUES (inModuleId, inProgramId, inAYearId);
        END CASE;
END//
/*--------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS AddClass //
CREATE PROCEDURE AddClass(
IN inClassId VARCHAR(10),
IN inSize INT,
IN inSemesterId VARCHAR(10),
IN inModuleId VARCHAR(10),
OUT statusCode INT
)
BEGIN
	CASE
		-- WHEN (SELECT REGEXP_LIKE(inClassId, '[A-Z0-9]+', 'c') = 0)THEN SET statusCode = 416;
		WHEN inModuleId NOT IN (SELECT ModuleId FROM Module) THEN SET statusCode = 415;
        WHEN inSemesterId NOT IN (SELECT SemesterId FROM Semester) THEN SET statusCode = 402;
        WHEN inClassId IN (SELECT ClassId FROM Class) THEN SET statusCode = 490;
        ELSE SET statusCode = 200;
		INSERT INTO Class (ClassId, Size, SemesterId, ModuleId)
		VALUES (inClassId, inSize, inSemesterId, inModuleId);
	END CASE;
END//
/*--------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS AddTeaching //
CREATE PROCEDURE AddTeaching(
IN inLecturerId VARCHAR(10),
IN inClassId VARCHAR(10),
OUT statusCode INT
)
BEGIN
	CASE
		WHEN inLecturerId NOT IN (SELECT LecturerId FROM Lecturer) THEN SET statusCode = 416;
        WHEN inClassId NOT IN (SELECT ClassId FROM Class) THEN SET statusCode = 407;
        WHEN inLecturerId IN (SELECT LecturerId FROM Teaching) 
        AND  inClassId IN (SELECT ClassId FROM Teaching) THEN SET statusCode = 490;
        ELSE SET statusCode = 200;
		INSERT INTO Teaching (LecturerId, ClassId)
		VALUES (inLecturerId, inClassId);
	END CASE;
END//

DELIMITER ; 