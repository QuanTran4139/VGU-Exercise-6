DELIMITER //
DROP PROCEDURE IF EXISTS GetClassOptions //
CREATE PROCEDURE GetClassOptions(
)
BEGIN
        SELECT ClassId, CONCAT(ModuleId, SemesterId) AS Class_Name
        FROM Class;
END//
DELIMITER ;