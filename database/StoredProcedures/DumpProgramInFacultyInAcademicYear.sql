DELIMITER //
DROP PROCEDURE IF EXISTS DumpProgramInFacultyInAcademicYear //
CREATE PROCEDURE DumpProgramInFacultyInAcademicYear()
BEGIN
        SELECT *
        FROM ProgramInFacultyInAcademicYear;
END//
DELIMITER ; 