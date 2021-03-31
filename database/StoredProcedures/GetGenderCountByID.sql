DELIMITER //
DROP PROCEDURE IF EXISTS GetGenderCountByID //
CREATE PROCEDURE GetGenderCountByID(
IN inClassId VARCHAR(10),
IN inLecturerId VARCHAR(10),
OUT statusCode INT
)
BEGIN
	CASE
		WHEN inClassId NOT IN (SELECT ClassId FROM Questionnaire) THEN SET statusCode = 407; -- wrong Class Id
        WHEN inLecturerId NOT IN (SELECT LecturerId FROM Questionnaire) THEN SET statusCode = 416; -- wrong Lecturer Id
        ELSE SET statusCode = 200; -- Success
        SELECT Gender, COUNT(QuestionnaireId) AS Count
		FROM Questionnaire
        WHERE inClassId = ClassId AND inLecturerId = LecturerId
        GROUP BY Gender;
		END CASE;         
END//
DELIMITER ;