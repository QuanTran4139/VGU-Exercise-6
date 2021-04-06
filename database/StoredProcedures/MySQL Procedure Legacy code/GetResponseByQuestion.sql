DECLARE @Question VARCHAR(20)
DECLARE @QRY VARCHAR(MAX)
DELIMITER //
DROP PROCEDURE IF EXISTS GetResponseByQuestion //
CREATE PROCEDURE GetResponseByQuestion(
IN inLecturerId VARCHAR(10),
IN inClassId VARCHAR(10),
IN inQuestionNumber ENUM ('1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17'),
OUT statusCode INT
)

BEGIN
	CASE
		WHEN inCLassId NOT IN (SELECT ClassId FROM Class) THEN SET statusCode = 407; -- wrong ClassID
        WHEN inLecturerId NOT IN (SELECT LecturerId FROM Lecturer) THEN SET statusCode = 416; -- wrong Lecturer
        ELSE SET statusCode = 200; -- Success
        SET @Question = CONCAT('Question', inQuestionNumber);
		SET @QRY = CONCAT('SELECT ', @Question,', ClassId, LecturerId, SUM(CASE WHEN ', @Question, ' = 1 THEN 1 ELSE 0 END) AS R1,
																	   SUM(CASE WHEN ', @Question, ' = 2 THEN 1 ELSE 0 END) AS R2,
                                                                       SUM(CASE WHEN ', @Question, ' = 3 THEN 1 ELSE 0 END) AS R3,
                                                                       SUM(CASE WHEN ', @Question, ' = 4 THEN 1 ELSE 0 END) AS R4,
                                                                       SUM(CASE WHEN ', @Question, ' = 5 THEN 1 ELSE 0 END) AS R5,
                                                                       SUM(CASE WHEN ', @Question, ' = "N/A" THEN 1 ELSE 0 END) AS RNA FROM Questionnaire WHERE ClassId = ',inClassId ,' AND LecturerId = ', inLecturerId, ' GROUP BY ClassId;');
        PREPARE dynamic_statement FROM @QRY;
        EXECUTE dynamic_statement;
        DEALLOCATE PREPARE dynamic_statement;
	END CASE;
END//

DELIMITER ;

