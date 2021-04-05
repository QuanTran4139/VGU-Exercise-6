DELIMITER //
DROP PROCEDURE IF EXISTS GetCountByQuestion0 //
CREATE PROCEDURE GetCountByQuestion0(
IN inLecturerId VARCHAR(10),
IN inClassId VARCHAR(10),
OUT statusCode INT
)

BEGIN
	CASE
		WHEN inCLassId NOT IN (SELECT ClassId FROM Class) THEN SET statusCode = 407; -- wrong ClassID
        WHEN inLecturerId NOT IN (SELECT LecturerId FROM Lecturer) THEN SET statusCode = 416; -- wrong Lecturer
        ELSE SET statusCode = 200; -- Success
		SELECT ClassId, LecturerId, SUM(CASE WHEN Question0 = 1 THEN 1 ELSE 0 END) AS R1,
									SUM(CASE WHEN Question0 = 2 THEN 1 ELSE 0 END) AS R2,
									SUM(CASE WHEN Question0 = 3 THEN 1 ELSE 0 END) AS R3,
									SUM(CASE WHEN Question0 = 4 THEN 1 ELSE 0 END) AS R4,
									SUM(CASE WHEN Question0 = 5 THEN 1 ELSE 0 END) AS R5,
									SUM(CASE WHEN Question0 = "N/A" THEN 1 ELSE 0 END) AS RNA
		FROM Questionnaire
        WHERE ClassId = inClassId AND LecturerId = inLecturerId
        GROUP BY ClassId;
	END CASE;
END//
/*--------------------------------------------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS GetCountByQuestion1 //
CREATE PROCEDURE GetCountByQuestion1(
IN inLecturerId VARCHAR(10),
IN inClassId VARCHAR(10),
OUT statusCode INT
)

BEGIN
	CASE
		WHEN inCLassId NOT IN (SELECT ClassId FROM Class) THEN SET statusCode = 407; -- wrong ClassID
        WHEN inLecturerId NOT IN (SELECT LecturerId FROM Lecturer) THEN SET statusCode = 416; -- wrong Lecturer
        ELSE SET statusCode = 200; -- Success
		SELECT ClassId, LecturerId, SUM(CASE WHEN Question1 = 1 THEN 1 ELSE 0 END) AS R1,
									SUM(CASE WHEN Question1 = 2 THEN 1 ELSE 0 END) AS R2,
									SUM(CASE WHEN Question1 = 3 THEN 1 ELSE 0 END) AS R3,
									SUM(CASE WHEN Question1 = 4 THEN 1 ELSE 0 END) AS R4,
									SUM(CASE WHEN Question1 = 5 THEN 1 ELSE 0 END) AS R5,
									SUM(CASE WHEN Question1 = "N/A" THEN 1 ELSE 0 END) AS RNA
		FROM Questionnaire
        WHERE ClassId = inClassId AND LecturerId = inLecturerId
        GROUP BY ClassId;
	END CASE;
END//
/*--------------------------------------------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS GetCountByQuestion2 //
CREATE PROCEDURE GetCountByQuestion2(
IN inLecturerId VARCHAR(10),
IN inClassId VARCHAR(10),
OUT statusCode INT
)

BEGIN
	CASE
		WHEN inCLassId NOT IN (SELECT ClassId FROM Class) THEN SET statusCode = 407; -- wrong ClassID
        WHEN inLecturerId NOT IN (SELECT LecturerId FROM Lecturer) THEN SET statusCode = 416; -- wrong Lecturer
        ELSE SET statusCode = 200; -- Success
		SELECT ClassId, LecturerId, SUM(CASE WHEN Question2 = 1 THEN 1 ELSE 0 END) AS R1,
									SUM(CASE WHEN Question2 = 2 THEN 1 ELSE 0 END) AS R2,
									SUM(CASE WHEN Question2 = 3 THEN 1 ELSE 0 END) AS R3,
									SUM(CASE WHEN Question2 = 4 THEN 1 ELSE 0 END) AS R4,
									SUM(CASE WHEN Question2 = 5 THEN 1 ELSE 0 END) AS R5,
									SUM(CASE WHEN Question2 = "N/A" THEN 1 ELSE 0 END) AS RNA
		FROM Questionnaire
        WHERE ClassId = inClassId AND LecturerId = inLecturerId
        GROUP BY ClassId;
	END CASE;
END//
/*--------------------------------------------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS GetCountByQuestion3 //
CREATE PROCEDURE GetCountByQuestion3(
IN inLecturerId VARCHAR(10),
IN inClassId VARCHAR(10),
OUT statusCode INT
)

BEGIN
	CASE
		WHEN inCLassId NOT IN (SELECT ClassId FROM Class) THEN SET statusCode = 407; -- wrong ClassID
        WHEN inLecturerId NOT IN (SELECT LecturerId FROM Lecturer) THEN SET statusCode = 416; -- wrong Lecturer
        ELSE SET statusCode = 200; -- Success
		SELECT ClassId, LecturerId, SUM(CASE WHEN Question3 = 1 THEN 1 ELSE 0 END) AS R1,
									SUM(CASE WHEN Question3 = 2 THEN 1 ELSE 0 END) AS R2,
									SUM(CASE WHEN Question3 = 3 THEN 1 ELSE 0 END) AS R3,
									SUM(CASE WHEN Question3 = 4 THEN 1 ELSE 0 END) AS R4,
									SUM(CASE WHEN Question3 = 5 THEN 1 ELSE 0 END) AS R5,
									SUM(CASE WHEN Question3 = "N/A" THEN 1 ELSE 0 END) AS RNA
		FROM Questionnaire
        WHERE ClassId = inClassId AND LecturerId = inLecturerId
        GROUP BY ClassId;
	END CASE;
END//
/*--------------------------------------------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS GetCountByQuestion4 //
CREATE PROCEDURE GetCountByQuestion4(
IN inLecturerId VARCHAR(10),
IN inClassId VARCHAR(10),
OUT statusCode INT
)

BEGIN
	CASE
		WHEN inCLassId NOT IN (SELECT ClassId FROM Class) THEN SET statusCode = 407; -- wrong ClassID
        WHEN inLecturerId NOT IN (SELECT LecturerId FROM Lecturer) THEN SET statusCode = 416; -- wrong Lecturer
        ELSE SET statusCode = 200; -- Success
		SELECT ClassId, LecturerId, SUM(CASE WHEN Question4 = 1 THEN 1 ELSE 0 END) AS R1,
									SUM(CASE WHEN Question4 = 2 THEN 1 ELSE 0 END) AS R2,
									SUM(CASE WHEN Question4 = 3 THEN 1 ELSE 0 END) AS R3,
									SUM(CASE WHEN Question4 = 4 THEN 1 ELSE 0 END) AS R4,
									SUM(CASE WHEN Question4 = 5 THEN 1 ELSE 0 END) AS R5,
									SUM(CASE WHEN Question4 = "N/A" THEN 1 ELSE 0 END) AS RNA
		FROM Questionnaire
        WHERE ClassId = inClassId AND LecturerId = inLecturerId
        GROUP BY ClassId;
	END CASE;
END//
/*--------------------------------------------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS GetCountByQuestion5 //
CREATE PROCEDURE GetCountByQuestion5(
IN inLecturerId VARCHAR(10),
IN inClassId VARCHAR(10),
OUT statusCode INT
)

BEGIN
	CASE
		WHEN inCLassId NOT IN (SELECT ClassId FROM Class) THEN SET statusCode = 407; -- wrong ClassID
        WHEN inLecturerId NOT IN (SELECT LecturerId FROM Lecturer) THEN SET statusCode = 416; -- wrong Lecturer
        ELSE SET statusCode = 200; -- Success
		SELECT ClassId, LecturerId, SUM(CASE WHEN Question5 = 1 THEN 1 ELSE 0 END) AS R1,
									SUM(CASE WHEN Question5 = 2 THEN 1 ELSE 0 END) AS R2,
									SUM(CASE WHEN Question5 = 3 THEN 1 ELSE 0 END) AS R3,
									SUM(CASE WHEN Question5 = 4 THEN 1 ELSE 0 END) AS R4,
									SUM(CASE WHEN Question5 = 5 THEN 1 ELSE 0 END) AS R5,
									SUM(CASE WHEN Question5 = "N/A" THEN 1 ELSE 0 END) AS RNA
		FROM Questionnaire
        WHERE ClassId = inClassId AND LecturerId = inLecturerId
        GROUP BY ClassId;
	END CASE;
END//
/*--------------------------------------------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS GetCountByQuestion6 //
CREATE PROCEDURE GetCountByQuestion6(
IN inLecturerId VARCHAR(10),
IN inClassId VARCHAR(10),
OUT statusCode INT
)

BEGIN
	CASE
		WHEN inCLassId NOT IN (SELECT ClassId FROM Class) THEN SET statusCode = 407; -- wrong ClassID
        WHEN inLecturerId NOT IN (SELECT LecturerId FROM Lecturer) THEN SET statusCode = 416; -- wrong Lecturer
        ELSE SET statusCode = 200; -- Success
		SELECT ClassId, LecturerId, SUM(CASE WHEN Question6 = 1 THEN 1 ELSE 0 END) AS R1,
									SUM(CASE WHEN Question6 = 2 THEN 1 ELSE 0 END) AS R2,
									SUM(CASE WHEN Question6 = 3 THEN 1 ELSE 0 END) AS R3,
									SUM(CASE WHEN Question6 = 4 THEN 1 ELSE 0 END) AS R4,
									SUM(CASE WHEN Question6 = 5 THEN 1 ELSE 0 END) AS R5,
									SUM(CASE WHEN Question6 = "N/A" THEN 1 ELSE 0 END) AS RNA
		FROM Questionnaire
        WHERE ClassId = inClassId AND LecturerId = inLecturerId
        GROUP BY ClassId;
	END CASE;
END//
/*--------------------------------------------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS GetCountByQuestion7 //
CREATE PROCEDURE GetCountByQuestion7(
IN inLecturerId VARCHAR(10),
IN inClassId VARCHAR(10),
OUT statusCode INT
)

BEGIN
	CASE
		WHEN inCLassId NOT IN (SELECT ClassId FROM Class) THEN SET statusCode = 407; -- wrong ClassID
        WHEN inLecturerId NOT IN (SELECT LecturerId FROM Lecturer) THEN SET statusCode = 416; -- wrong Lecturer
        ELSE SET statusCode = 200; -- Success
		SELECT ClassId, LecturerId, SUM(CASE WHEN Question7 = 1 THEN 1 ELSE 0 END) AS R1,
									SUM(CASE WHEN Question7 = 2 THEN 1 ELSE 0 END) AS R2,
									SUM(CASE WHEN Question7 = 3 THEN 1 ELSE 0 END) AS R3,
									SUM(CASE WHEN Question7 = 4 THEN 1 ELSE 0 END) AS R4,
									SUM(CASE WHEN Question7 = 5 THEN 1 ELSE 0 END) AS R5,
									SUM(CASE WHEN Question7 = "N/A" THEN 1 ELSE 0 END) AS RNA
		FROM Questionnaire
        WHERE ClassId = inClassId AND LecturerId = inLecturerId
        GROUP BY ClassId;
	END CASE;
END//
/*--------------------------------------------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS GetCountByQuestion8 //
CREATE PROCEDURE GetCountByQuestion8(
IN inLecturerId VARCHAR(10),
IN inClassId VARCHAR(10),
OUT statusCode INT
)

BEGIN
	CASE
		WHEN inCLassId NOT IN (SELECT ClassId FROM Class) THEN SET statusCode = 407; -- wrong ClassID
        WHEN inLecturerId NOT IN (SELECT LecturerId FROM Lecturer) THEN SET statusCode = 416; -- wrong Lecturer
        ELSE SET statusCode = 200; -- Success
		SELECT ClassId, LecturerId, SUM(CASE WHEN Question8 = 1 THEN 1 ELSE 0 END) AS R1,
									SUM(CASE WHEN Question8 = 2 THEN 1 ELSE 0 END) AS R2,
									SUM(CASE WHEN Question8 = 3 THEN 1 ELSE 0 END) AS R3,
									SUM(CASE WHEN Question8 = 4 THEN 1 ELSE 0 END) AS R4,
									SUM(CASE WHEN Question8 = 5 THEN 1 ELSE 0 END) AS R5,
									SUM(CASE WHEN Question8 = "N/A" THEN 1 ELSE 0 END) AS RNA
		FROM Questionnaire
        WHERE ClassId = inClassId AND LecturerId = inLecturerId
        GROUP BY ClassId;
	END CASE;
END//
/*--------------------------------------------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS GetCountByQuestion9 //
CREATE PROCEDURE GetCountByQuestion9(
IN inLecturerId VARCHAR(10),
IN inClassId VARCHAR(10),
OUT statusCode INT
)

BEGIN
	CASE
		WHEN inCLassId NOT IN (SELECT ClassId FROM Class) THEN SET statusCode = 407; -- wrong ClassID
        WHEN inLecturerId NOT IN (SELECT LecturerId FROM Lecturer) THEN SET statusCode = 416; -- wrong Lecturer
        ELSE SET statusCode = 200; -- Success
		SELECT ClassId, LecturerId, SUM(CASE WHEN Question9 = 1 THEN 1 ELSE 0 END) AS R1,
									SUM(CASE WHEN Question9 = 2 THEN 1 ELSE 0 END) AS R2,
									SUM(CASE WHEN Question9 = 3 THEN 1 ELSE 0 END) AS R3,
									SUM(CASE WHEN Question9 = 4 THEN 1 ELSE 0 END) AS R4,
									SUM(CASE WHEN Question9 = 5 THEN 1 ELSE 0 END) AS R5,
									SUM(CASE WHEN Question9 = "N/A" THEN 1 ELSE 0 END) AS RNA
		FROM Questionnaire
        WHERE ClassId = inClassId AND LecturerId = inLecturerId
        GROUP BY ClassId;
	END CASE;
END//
/*--------------------------------------------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS GetCountByQuestion10 //
CREATE PROCEDURE GetCountByQuestion10(
IN inLecturerId VARCHAR(10),
IN inClassId VARCHAR(10),
OUT statusCode INT
)

BEGIN
	CASE
		WHEN inCLassId NOT IN (SELECT ClassId FROM Class) THEN SET statusCode = 407; -- wrong ClassID
        WHEN inLecturerId NOT IN (SELECT LecturerId FROM Lecturer) THEN SET statusCode = 416; -- wrong Lecturer
        ELSE SET statusCode = 200; -- Success
		SELECT ClassId, LecturerId, SUM(CASE WHEN Question10 = 1 THEN 1 ELSE 0 END) AS R1,
									SUM(CASE WHEN Question10 = 2 THEN 1 ELSE 0 END) AS R2,
									SUM(CASE WHEN Question10 = 3 THEN 1 ELSE 0 END) AS R3,
									SUM(CASE WHEN Question10 = 4 THEN 1 ELSE 0 END) AS R4,
									SUM(CASE WHEN Question10 = 5 THEN 1 ELSE 0 END) AS R5,
									SUM(CASE WHEN Question10 = "N/A" THEN 1 ELSE 0 END) AS RNA
		FROM Questionnaire
        WHERE ClassId = inClassId AND LecturerId = inLecturerId
        GROUP BY ClassId;
	END CASE;
END//
/*--------------------------------------------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS GetCountByQuestion11 //
CREATE PROCEDURE GetCountByQuestion11(
IN inLecturerId VARCHAR(10),
IN inClassId VARCHAR(10),
OUT statusCode INT
)

BEGIN
	CASE
		WHEN inCLassId NOT IN (SELECT ClassId FROM Class) THEN SET statusCode = 407; -- wrong ClassID
        WHEN inLecturerId NOT IN (SELECT LecturerId FROM Lecturer) THEN SET statusCode = 416; -- wrong Lecturer
        ELSE SET statusCode = 200; -- Success
		SELECT ClassId, LecturerId, SUM(CASE WHEN Question11 = 1 THEN 1 ELSE 0 END) AS R1,
									SUM(CASE WHEN Question11 = 2 THEN 1 ELSE 0 END) AS R2,
									SUM(CASE WHEN Question11 = 3 THEN 1 ELSE 0 END) AS R3,
									SUM(CASE WHEN Question11 = 4 THEN 1 ELSE 0 END) AS R4,
									SUM(CASE WHEN Question11 = 5 THEN 1 ELSE 0 END) AS R5,
									SUM(CASE WHEN Question11 = "N/A" THEN 1 ELSE 0 END) AS RNA
		FROM Questionnaire
        WHERE ClassId = inClassId AND LecturerId = inLecturerId
        GROUP BY ClassId;
	END CASE;
END//
/*--------------------------------------------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS GetCountByQuestion12 //
CREATE PROCEDURE GetCountByQuestion12(
IN inLecturerId VARCHAR(10),
IN inClassId VARCHAR(10),
OUT statusCode INT
)

BEGIN
	CASE
		WHEN inCLassId NOT IN (SELECT ClassId FROM Class) THEN SET statusCode = 407; -- wrong ClassID
        WHEN inLecturerId NOT IN (SELECT LecturerId FROM Lecturer) THEN SET statusCode = 416; -- wrong Lecturer
        ELSE SET statusCode = 200; -- Success
		SELECT ClassId, LecturerId, SUM(CASE WHEN Question12 = 1 THEN 1 ELSE 0 END) AS R1,
									SUM(CASE WHEN Question12 = 2 THEN 1 ELSE 0 END) AS R2,
									SUM(CASE WHEN Question12 = 3 THEN 1 ELSE 0 END) AS R3,
									SUM(CASE WHEN Question12 = 4 THEN 1 ELSE 0 END) AS R4,
									SUM(CASE WHEN Question12 = 5 THEN 1 ELSE 0 END) AS R5,
									SUM(CASE WHEN Question12 = "N/A" THEN 1 ELSE 0 END) AS RNA
		FROM Questionnaire
        WHERE ClassId = inClassId AND LecturerId = inLecturerId
        GROUP BY ClassId;
	END CASE;
END//
/*--------------------------------------------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS GetCountByQuestion13 //
CREATE PROCEDURE GetCountByQuestion13(
IN inLecturerId VARCHAR(10),
IN inClassId VARCHAR(10),
OUT statusCode INT
)

BEGIN
	CASE
		WHEN inCLassId NOT IN (SELECT ClassId FROM Class) THEN SET statusCode = 407; -- wrong ClassID
        WHEN inLecturerId NOT IN (SELECT LecturerId FROM Lecturer) THEN SET statusCode = 416; -- wrong Lecturer
        ELSE SET statusCode = 200; -- Success
		SELECT ClassId, LecturerId, SUM(CASE WHEN Question13 = 1 THEN 1 ELSE 0 END) AS R1,
									SUM(CASE WHEN Question13 = 2 THEN 1 ELSE 0 END) AS R2,
									SUM(CASE WHEN Question13 = 3 THEN 1 ELSE 0 END) AS R3,
									SUM(CASE WHEN Question13 = 4 THEN 1 ELSE 0 END) AS R4,
									SUM(CASE WHEN Question13 = 5 THEN 1 ELSE 0 END) AS R5,
									SUM(CASE WHEN Question13 = "N/A" THEN 1 ELSE 0 END) AS RNA
		FROM Questionnaire
        WHERE ClassId = inClassId AND LecturerId = inLecturerId
        GROUP BY ClassId;
	END CASE;
END//
/*--------------------------------------------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS GetCountByQuestion14 //
CREATE PROCEDURE GetCountByQuestion14(
IN inLecturerId VARCHAR(10),
IN inClassId VARCHAR(10),
OUT statusCode INT
)

BEGIN
	CASE
		WHEN inCLassId NOT IN (SELECT ClassId FROM Class) THEN SET statusCode = 407; -- wrong ClassID
        WHEN inLecturerId NOT IN (SELECT LecturerId FROM Lecturer) THEN SET statusCode = 416; -- wrong Lecturer
        ELSE SET statusCode = 200; -- Success
		SELECT ClassId, LecturerId, SUM(CASE WHEN Question14 = 1 THEN 1 ELSE 0 END) AS R1,
									SUM(CASE WHEN Question14 = 2 THEN 1 ELSE 0 END) AS R2,
									SUM(CASE WHEN Question14 = 3 THEN 1 ELSE 0 END) AS R3,
									SUM(CASE WHEN Question14 = 4 THEN 1 ELSE 0 END) AS R4,
									SUM(CASE WHEN Question14 = 5 THEN 1 ELSE 0 END) AS R5,
									SUM(CASE WHEN Question14 = "N/A" THEN 1 ELSE 0 END) AS RNA
		FROM Questionnaire
        WHERE ClassId = inClassId AND LecturerId = inLecturerId
        GROUP BY ClassId;
	END CASE;
END//
/*--------------------------------------------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS GetCountByQuestion15 //
CREATE PROCEDURE GetCountByQuestion15(
IN inLecturerId VARCHAR(10),
IN inClassId VARCHAR(10),
OUT statusCode INT
)

BEGIN
	CASE
		WHEN inCLassId NOT IN (SELECT ClassId FROM Class) THEN SET statusCode = 407; -- wrong ClassID
        WHEN inLecturerId NOT IN (SELECT LecturerId FROM Lecturer) THEN SET statusCode = 416; -- wrong Lecturer
        ELSE SET statusCode = 200; -- Success
		SELECT ClassId, LecturerId, SUM(CASE WHEN Question15 = 1 THEN 1 ELSE 0 END) AS R1,
									SUM(CASE WHEN Question15 = 2 THEN 1 ELSE 0 END) AS R2,
									SUM(CASE WHEN Question15 = 3 THEN 1 ELSE 0 END) AS R3,
									SUM(CASE WHEN Question15 = 4 THEN 1 ELSE 0 END) AS R4,
									SUM(CASE WHEN Question15 = 5 THEN 1 ELSE 0 END) AS R5,
									SUM(CASE WHEN Question15 = "N/A" THEN 1 ELSE 0 END) AS RNA
		FROM Questionnaire
        WHERE ClassId = inClassId AND LecturerId = inLecturerId
        GROUP BY ClassId;
	END CASE;
END//
/*--------------------------------------------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS GetCountByQuestion16 //
CREATE PROCEDURE GetCountByQuestion16(
IN inLecturerId VARCHAR(10),
IN inClassId VARCHAR(10),
OUT statusCode INT
)

BEGIN
	CASE
		WHEN inCLassId NOT IN (SELECT ClassId FROM Class) THEN SET statusCode = 407; -- wrong ClassID
        WHEN inLecturerId NOT IN (SELECT LecturerId FROM Lecturer) THEN SET statusCode = 416; -- wrong Lecturer
        ELSE SET statusCode = 200; -- Success
		SELECT ClassId, LecturerId, SUM(CASE WHEN Question16 = 1 THEN 1 ELSE 0 END) AS R1,
									SUM(CASE WHEN Question16 = 2 THEN 1 ELSE 0 END) AS R2,
									SUM(CASE WHEN Question16 = 3 THEN 1 ELSE 0 END) AS R3,
									SUM(CASE WHEN Question16 = 4 THEN 1 ELSE 0 END) AS R4,
									SUM(CASE WHEN Question16 = 5 THEN 1 ELSE 0 END) AS R5,
									SUM(CASE WHEN Question16 = "N/A" THEN 1 ELSE 0 END) AS RNA
		FROM Questionnaire
        WHERE ClassId = inClassId AND LecturerId = inLecturerId
        GROUP BY ClassId;
	END CASE;
END//
/*--------------------------------------------------------------------------------------------------------*/
DROP PROCEDURE IF EXISTS GetCountByQuestion17 //
CREATE PROCEDURE GetCountByQuestion17(
IN inLecturerId VARCHAR(10),
IN inClassId VARCHAR(10),
OUT statusCode INT
)

BEGIN
	CASE
		WHEN inCLassId NOT IN (SELECT ClassId FROM Class) THEN SET statusCode = 407; -- wrong ClassID
        WHEN inLecturerId NOT IN (SELECT LecturerId FROM Lecturer) THEN SET statusCode = 416; -- wrong Lecturer
        ELSE SET statusCode = 200; -- Success
		SELECT ClassId, LecturerId, SUM(CASE WHEN Question17 = 1 THEN 1 ELSE 0 END) AS R1,
									SUM(CASE WHEN Question17 = 2 THEN 1 ELSE 0 END) AS R2,
									SUM(CASE WHEN Question17 = 3 THEN 1 ELSE 0 END) AS R3,
									SUM(CASE WHEN Question17 = 4 THEN 1 ELSE 0 END) AS R4,
									SUM(CASE WHEN Question17 = 5 THEN 1 ELSE 0 END) AS R5,
									SUM(CASE WHEN Question17 = "N/A" THEN 1 ELSE 0 END) AS RNA
		FROM Questionnaire
        WHERE ClassId = inClassId AND LecturerId = inLecturerId
        GROUP BY ClassId;
	END CASE;
END//

DELIMITER ;