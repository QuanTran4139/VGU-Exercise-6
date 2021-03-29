DELIMITER //
DROP PROCEDURE IF EXISTS DumpFacultyInAcademicYear //
CREATE PROCEDURE DumpFacultyInAcademicYear()
BEGIN
        SELECT *
        FROM FacultyInAcademicYear;
END//
DELIMITER ; 