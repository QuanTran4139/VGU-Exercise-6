-- Questionnaire --
DELIMITER //
DROP PROCEDURE IF EXISTS DeleteQuestionnaire //
CREATE PROCEDURE DeleteQuestionnaire(
IN inLecturerId VARCHAR(10),
IN inClassId VARCHAR(10),
IN inQuestionnaireId INT,
OUT statusCode INT
)
BEGIN
	CASE
		WHEN inLecturerId NOT IN ( SELECT LecturerId FROM Questionnaire ) THEN SET statusCode = 416;
        WHEN inClassId NOT IN ( SELECT ClassId FROM Questionnaire ) THEN SET statusCode = 407;
        WHEN inQuestionnaireId NOT IN ( SELECT QuestionnaireId FROM Questionnaire ) THEN SET statusCode = 407; 
        ELSE SET statusCode = 200;
        DELETE FROM Questionnaire
        WHERE  LecturerId = inLecturerId AND inClassId = ClassId AND inQuestionnaireId = QuestionnaireId;
        END CASE;
END//
DELIMITER ; 

-- Teaching --
DELIMITER //
DROP PROCEDURE IF EXISTS DeleteTeaching //
CREATE PROCEDURE DeleteTeaching(
IN inLecturerId VARCHAR(10),
IN inClassId VARCHAR(10),
OUT statusCode INT
)
BEGIN
	CASE
		WHEN inLecturerId NOT IN ( SELECT LecturerId FROM Teaching ) THEN SET statusCode = 416;
        WHEN inClassId NOT IN ( SELECT ClassId FROM Teaching ) THEN SET statusCode = 407;
        ELSE SET statusCode = 200;
		SET FOREIGN_KEY_CHECKS=0;
        DELETE FROM Teaching
        WHERE  LecturerId = inLecturerId AND inClassId = ClassId;
        SET FOREIGN_KEY_CHECKS=1;
        END CASE;
END//
DELIMITER ; 

-- Lecturer --
DELIMITER //
DROP PROCEDURE IF EXISTS DeleteLecturer //
CREATE PROCEDURE DeleteLecturer(
IN inLecturerId VARCHAR(10),
OUT statusCode INT
)
BEGIN
	CASE
		WHEN inLecturerId NOT IN ( SELECT LecturerId FROM Lecturer ) THEN SET statusCode = 416;
        ELSE SET statusCode = 200;
        SET FOREIGN_KEY_CHECKS=0;
        DELETE FROM Lecturer
        WHERE  LecturerId = inLecturerId;
        SET FOREIGN_KEY_CHECKS=1;
        END CASE;
END//
DELIMITER ; 

-- Class --
DELIMITER //
DROP PROCEDURE IF EXISTS DeleteClass //
CREATE PROCEDURE DeleteClass(
IN inClassId VARCHAR(10),
OUT statusCode INT
)
BEGIN
	CASE
        WHEN inClassId NOT IN ( SELECT ClassId FROM Class ) THEN SET statusCode = 407;
        ELSE SET statusCode = 200;
		SET FOREIGN_KEY_CHECKS=0;
        DELETE FROM Class
        WHERE ClassId = inClassId;
        SET FOREIGN_KEY_CHECKS=1;
        END CASE;
END//
DELIMITER ; 

-- Module In Program In Academic Year -
DELIMITER //
DROP PROCEDURE IF EXISTS DeleteModuleInProgramInAcademicYear //
CREATE PROCEDURE DeleteModuleInProgramInAcademicYear(
IN inModuleId VARCHAR(10),
IN inAYearId INT,
OUT statusCode INT
)
BEGIN
	CASE
		WHEN inModuleId NOT IN ( SELECT ModuleId FROM ModuleInProgramInAcademicYear ) THEN SET statusCode = 415;
        WHEN inAYearId NOT IN ( SELECT AYearId FROM ModuleInProgramInAcademicYear ) THEN SET statusCode = 401;
        ELSE SET statusCode = 200;
		SET FOREIGN_KEY_CHECKS=0;
        DELETE FROM ModuleInProgramInAcademicYear
        WHERE  ModuleId = inModuleId;
        SET FOREIGN_KEY_CHECKS=1;
        END CASE;
END//
DELIMITER ; 

-- Module --
DELIMITER //
DROP PROCEDURE IF EXISTS DeleteModule //
CREATE PROCEDURE DeleteModule(
IN inModuleId VARCHAR(10),
OUT statusCode INT
)
BEGIN
	CASE
        WHEN inModuleId NOT IN ( SELECT ModuleId FROM Module ) THEN SET statusCode = 415;
        ELSE SET statusCode = 200;
		SET FOREIGN_KEY_CHECKS=0;
        DELETE FROM Module
        WHERE ModuleId = inModuleId;
        SET FOREIGN_KEY_CHECKS=1;
        END CASE;
END//
DELIMITER ; 

-- Program In Faculty In Academic Year --
DELIMITER //
DROP PROCEDURE IF EXISTS DeleteProgramInFacultyInAcademicYear //
CREATE PROCEDURE DeleteProgramInFacultyInAcademicYear(
IN inProgramId VARCHAR(10),
IN inAYearId INT,
OUT statusCode INT
)
BEGIN
	CASE
        WHEN inProgramId NOT IN ( SELECT ProgramId FROM ProgramInFacultyInAcademicYear ) THEN SET statusCode = 414;
        WHEN inAYearId NOT IN ( SELECT AYearId FROM ProgramInFacultyInAcademicYear ) THEN SET statusCode = 401;
        ELSE SET statusCode = 200;
		SET FOREIGN_KEY_CHECKS=0;
        DELETE FROM ProgramInFacultyInAcademicYear
        WHERE ProgramId = inProgramId AND AYearId = inAYearId;
        SET FOREIGN_KEY_CHECKS=1;
        END CASE;
END//
DELIMITER ; 

-- Program --
DELIMITER //
DROP PROCEDURE IF EXISTS DeleteProgram //
CREATE PROCEDURE DeleteProgram(
IN inProgramId VARCHAR(10),
OUT statusCode INT
)
BEGIN
	CASE
        WHEN inProgramId NOT IN ( SELECT ProgramId FROM Program ) THEN SET statusCode = 414;
        ELSE SET statusCode = 200;
		SET FOREIGN_KEY_CHECKS=0;
        DELETE FROM Program
        WHERE ProgramId = inProgramId;
        SET FOREIGN_KEY_CHECKS=1;
        END CASE;
END//
DELIMITER ; 

-- Faculty In Academic Year --
DELIMITER //
DROP PROCEDURE IF EXISTS DeleteFacultyInAcademicYear //
CREATE PROCEDURE DeleteFacultyInAcademicYear(
IN inFacultyId VARCHAR(10),
IN inAYearId INT,
OUT statusCode INT
)
BEGIN
	CASE
        WHEN inFacultyId NOT IN ( SELECT FacultyId FROM FacultyInAcademicYear ) THEN SET statusCode = 413;
        WHEN inAYearId NOT IN ( SELECT AYearId FROM FacultyInAcademicYear ) THEN SET statusCode = 401;
        ELSE SET statusCode = 200;
		SET FOREIGN_KEY_CHECKS=0;
        DELETE FROM FacultyInAcademicYear
        WHERE FacultyId = inFacultyId AND AYearId = inAYearId;
        SET FOREIGN_KEY_CHECKS=1;
        END CASE;
END//
DELIMITER ; 

-- Faculty --
DELIMITER //
DROP PROCEDURE IF EXISTS DeleteFaculty //
CREATE PROCEDURE DeleteFaculty(
IN inFacultyId VARCHAR(10),
OUT statusCode INT
)
BEGIN
	CASE
        WHEN inFacultyId NOT IN ( SELECT FacultyId FROM Faculty ) THEN SET statusCode = 413;
        ELSE SET statusCode = 200;
		SET FOREIGN_KEY_CHECKS=0;
        DELETE FROM Faculty
        WHERE FacultyId = inFacultyId;
        SET FOREIGN_KEY_CHECKS=1;
        END CASE;
END//
DELIMITER ;

-- Semester --
DELIMITER //
DROP PROCEDURE IF EXISTS DeleteSemester //
CREATE PROCEDURE DeleteSemester(
IN inSemesterId VARCHAR(10),
OUT statusCode INT
)
BEGIN
	CASE
        WHEN inSemesterId NOT IN ( SELECT SemesterId FROM Semester ) THEN SET statusCode = 402;
        ELSE SET statusCode = 200;
		SET FOREIGN_KEY_CHECKS=0;
        DELETE FROM Semester
        WHERE SemesterId = inSemesterId;
        SET FOREIGN_KEY_CHECKS=1;
        END CASE;
END//
DELIMITER ;

-- Academic Year --
DELIMITER //
DROP PROCEDURE IF EXISTS DeleteAcademicYear //
CREATE PROCEDURE DeleteAcademicYear(
IN inAYearId INT,
OUT statusCode INT
)
BEGIN
	CASE
        WHEN inAYearId NOT IN ( SELECT AYearId FROM AcademicYear ) THEN SET statusCode = 401;
        ELSE SET statusCode = 200;
		SET FOREIGN_KEY_CHECKS=0;
        DELETE FROM AcademicYear
        WHERE AYearId = inAYearId;
        SET FOREIGN_KEY_CHECKS=1;
        END CASE;
END//
DELIMITER ;





