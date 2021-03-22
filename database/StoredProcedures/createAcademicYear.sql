/* 
     Creates a Academic Year
*/
DELIMITER //

DROP PROCEDURE IF EXISTS createAcademicYear //
CREATE PROCEDURE createAcademicYear (
	IN inAYearId VARCHAR(10),
    OUT statusCode INT
)
BEGIN
	IF inAYearId REGEXP '[^a-zA-Z0-9]+' THEN
		SET statusCode = 460; /* invalid id */
    ELSEIF inAYearId IN (SELECT AYearId FROM AcademicYear) THEN
		SET statusCode = 490; /* duplication error */
	ELSE
		SET statusCode = 200; /* valid */
		INSERT INTO AcademicYear (AYearId)
		VALUES (inAYearId);
	END IF;
END//

DELIMITER ;
