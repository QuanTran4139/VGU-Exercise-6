DELIMITER //
DROP PROCEDURE IF EXISTS DumpModuleInProgramInAcademicYear //
CREATE PROCEDURE DumpModuleInProgramInAcademicYear()
BEGIN
        SELECT *
        FROM ModuleInProgramInAcademicYear;
END//
DELIMITER ; 