DELIMITER //
DROP PROCEDURE IF EXISTS DumpQuestionnaire //
CREATE PROCEDURE DumpQuestionnaire()
BEGIN
        SELECT *
        FROM Questionnaire;
END//
DELIMITER ;