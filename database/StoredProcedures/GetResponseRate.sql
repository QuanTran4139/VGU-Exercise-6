DELIMITER //
DROP PROCEDURE IF EXISTS GetResponseRate //
CREATE PROCEDURE GetResponseRate(
IN inLecturerId VARCHAR(10),
IN inClassId VARCHAR(10),
OUT statusCode INT
)
BEGIN
	CASE
		WHEN inCLassId NOT IN (SELECT ClassId FROM Class) THEN SET statusCode = 407; -- wrong ClassID
        WHEN inLecturerId NOT IN (SELECT LecturerId FROM Lecturer) THEN SET statusCode = 416; -- wrong Lecturer
        ELSE SET statusCode = 200; -- Success
        SELECT ClassId, LecturerId, COUNT(QuestionnaireId) / (SELECT Size FROM Class WHERE ClassId = QuestionnaireId) * 100 AS Response_Rate
        FROM Questionnaire
        WHERE ClassId = inClassId
		AND	  LecturerId = inLecturerId
        GROUP BY ClassId;
	END CASE;
END//

DELIMITER ;
