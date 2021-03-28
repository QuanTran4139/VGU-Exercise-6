DELIMITER //
DROP PROCEDURE IF EXISTS DumpAcademicYear //
CREATE PROCEDURE DumpAcademicYear()
BEGIN
        SELECT *
        FROM AcademicYear;
END//
DELIMITER ;