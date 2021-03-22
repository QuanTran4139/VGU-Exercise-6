CREATE TABLE IF NOT EXISTS AcademicYear (
    AYearId INT,
    
    PRIMARY KEY (AYearId)
);


CREATE TABLE IF NOT EXISTS Semester (
	SemesterId VARCHAR(10),
    AYearId INT NOT NULL,
    
    PRIMARY KEY(SemesterId,AYearId),
	FOREIGN KEY (AYearId)
		REFERENCES AcademicYear (AYearId),
	CONSTRAINT Semester
    UNIQUE (SemesterId)
);

CREATE TABLE IF NOT EXISTS Faculty (
    FacultyId VARCHAR(10),
	FacultyName VARCHAR(100) NOT NULL,
    
    PRIMARY KEY (FacultyId),
    CONSTRAINT Faculty
    UNIQUE (FacultyName)
); 

CREATE TABLE IF NOT EXISTS FacultyInAcademicYear (
	FacultyId VARCHAR(100),
    AYearId INT NOT NULL,
    
    PRIMARY KEY(FacultyId,AYearId),
	FOREIGN KEY (FacultyId)
		REFERENCES Faculty (FacultyId),
	FOREIGN KEY (AYearId)
		REFERENCES AcademicYear (AYearId)
);
    
CREATE TABLE IF NOT EXISTS Program (
    ProgramId VARCHAR(10),
    ProgramName VARCHAR(100) NOT NULL,
    
    PRIMARY KEY (ProgramId),
	CONSTRAINT Program
    UNIQUE (ProgramName)
);

CREATE TABLE IF NOT EXISTS ProgramInFacultyInAcademicYear (
	ProgramId VARCHAR(100),
	FacultyId VARCHAR(10),
    AYearId INT,
    PRIMARY KEY(ProgramId,FacultyId,AYearId),
	FOREIGN KEY (ProgramId)
		REFERENCES Program (ProgramId),
	FOREIGN KEY (FacultyId)
		REFERENCES FacultyInAcademicYear (FacultyId),
	FOREIGN KEY (AYearId)
		REFERENCES FacultyInAcademicYear (AYearId)
);

CREATE TABLE IF NOT EXISTS Module (
    ModuleId VARCHAR(10),
    ModuleName VARCHAR(100) NOT NULL,
    
	PRIMARY KEY (ModuleId)
);

CREATE TABLE IF NOT EXISTS ModuleInProgramInAcademicYear (
	ModuleId VARCHAR(10),
	ProgramId VARCHAR(10),
    AYearId INT,
    PRIMARY KEY(ModuleId,ProgramId,AYearId),
	FOREIGN KEY (ModuleId)
		REFERENCES Module (ModuleId),
	FOREIGN KEY (ProgramId)
		REFERENCES ProgramInFacultyInAcademicYear (ProgramId),
	FOREIGN KEY (AYearId)
		REFERENCES ProgramInFacultyInAcademicYear (AYearId)
);

CREATE TABLE IF NOT EXISTS Class (
    ClassId VARCHAR(10),
    Size INT NOT NULL,
   
    PRIMARY KEY(ClassId)

);

CREATE TABLE IF NOT EXISTS ClassInSemesterInModule (
	ClassId VARCHAR(10),
	SemesterId VARCHAR(10),
    ModuleId VARCHAR(10),
    
    PRIMARY KEY (ClassId,SemesterId,ModuleId),
    FOREIGN KEY (ClassId)
		REFERENCES Class (ClassId),
    FOREIGN KEY (SemesterId)
		REFERENCES Semester (SemesterId),
	FOREIGN KEY (ModuleId)
		REFERENCES ModuleInProgramInAcademicYear (ModuleId)
);
    

CREATE TABLE IF NOT EXISTS Lecturer (
    LecturerId VARCHAR(10),
    LecturerName VARCHAR(100) NOT NULL,
	PRIMARY KEY (LecturerId)
); 

CREATE TABLE IF NOT EXISTS Teaching (
    LecturerId VARCHAR(10),
    ClassId VARCHAR(10),

    PRIMARY KEY (LecturerId,ClassId),
    FOREIGN KEY (LecturerId)
		REFERENCES Lecturer (LecturerId),
	FOREIGN KEY (ClassId)
		REFERENCES Class (ClassId)
);

CREATE TABLE IF NOT EXISTS Questionnaire (
    LecturerId VARCHAR(10),
    ClassId VARCHAR(10),
    Content LONGBLOB, 

    PRIMARY KEY (LecturerId,ClassId),
    FOREIGN KEY (LecturerId)
		REFERENCES Lecturer (LecturerId),
	FOREIGN KEY (ClassId)
		REFERENCES Class (ClassId)
);

INSERT INTO `academicyear` (`AYearId`) VALUES 	(2001),
												(2002),
												(2003),
												(2004);

INSERT INTO `faculty` (`FacultyId`, `FacultyName`) VALUES 	('CENG', 'Computer And Engineering'),
															('ECO', 'Economy'),
															('ARC', 'Architecture' );

INSERT INTO `facultyinacademicyear` (`FacultyId`, `AYearId`) VALUES ( 'CENG', 2001),
																	( 'CENG', 2002),
																	( 'CENG', 2003),
																	( 'CENG', 2004),
																	( 'ECO', 2001),
																	( 'ECO', 2002),
																	( 'ECO', 2003),
																	( 'ECO', 2004),
																	( 'ARC', 2001),
																	( 'ARC', 2002),
																	( 'ARC', 2003),
																	( 'ARC', 2004);

INSERT INTO `lecturer` (`LecturerId`, `LecturerName`) VALUES 	('1', 'BOB'),
																('2', 'BOB'),
																('3', 'ALICE'),
																('4', 'HENRY'),
																('5', 'PETER');

INSERT INTO `program` (`ProgramId`, `ProgramName`) VALUES 	('CS', 'Computer Science'),
															('EE', 'Electrical Engineering'),
															('BA', 'Business Administrator'),
															('AR', 'Architecture');

INSERT INTO `programinfacultyinacademicyear` (`ProgramId`, `FacultyId`, `AYearId`) VALUES 	( 'CS', 'CENG', 2001),
																							( 'CS', 'CENG', 2002),
																							( 'EE', 'CENG', 2001),
																							( 'BA', 'ECO', 2002),
																							( 'AR', 'ARC', 2002);

INSERT INTO `module` (`ModuleId`, `ModuleName`) VALUES 	('ComCa', 'Calculus'),
														('ComAl', 'Algebra'),
														('EECa', 'Calculus'),
														('EEAl', 'Algebra'),
														('Ba', 'BusinessAdmin'),
														('Phy', 'Physics');

INSERT INTO `moduleinprograminacademicyear` (`ModuleId`, `ProgramId`, `AYearId`) VALUES 	( 'ComCa', 'CS', 2001),
																							( 'ComAl', 'CS', 2001),
																							( 'EECa', 'EE', 2001),
																							( 'EEAl', 'EE', 2001),
																							( 'Ba', 'BA', 2001),
																							( 'Phy', 'AR', 2001);


INSERT INTO `semester` (`SemesterId`, `AYearId`) VALUES 	( 'SS01', 2001),
																		( 'SS02', 2002),
																		( 'SS03', 2003),
																		( 'SS04', 2004),
																		( 'WS01', 2001),
																		( 'WS02', 2002),
																		( 'WS03', 2003),
																		( 'WS04', 2004);


INSERT INTO `class` (`ClassId`, `Size`) VALUES 	('1', '20'),
												('2', '30'),
												('3', '50'),
												('4', '50'),
												('5', '30'),
												('6', '50');
                                                
INSERT INTO `classinsemesterinmodule` (`ClassId`, `SemesterId`, `ModuleId`) VALUES 	('1', 'WS01', 'EECa'),
																					('2', 'WS01', 'ComCa'),
																					('3', 'WS01', 'EECa'),
																					('4', 'SS01', 'ComCa'),
																					('5', 'WS01', 'Ba'),
																					('6', 'SS01', 'Phy');

INSERT INTO `questionnaire` (`LecturerId`, `ClassId`) VALUES 	('1', '1'),
																('2', '2'),
																('4', '1');

INSERT INTO `teaching` (`LecturerId`, `ClassId`) VALUES ('1', '1'),
														('1', '2'),
														('1', '4'),
														('1', '5'),
														('1', '6'),
														('2', '2'),
														('2', '3'),
														('3', '2'),
														('4', '1'),
														('5', '1');
                                                        
DELIMITER //

DROP PROCEDURE IF EXISTS GetTotalClassesSize  //
CREATE PROCEDURE GetTotalClassesSize (
	IN inAYearId INT,
    IN inSemesterId  VARCHAR(10),
	IN inFacultyName  VARCHAR(100),
    IN inProgramName  VARCHAR(100),
    IN inModuleName  VARCHAR(100),
    IN inLecturerId  VARCHAR(10),
    OUT statusCode INT
)
BEGIN
	CASE
		WHEN inAYearId NOT IN (SELECT AYearId FROM AcademicYear) 			THEN SET statusCode = 401; -- wrong AY
		WHEN inSemesterId NOT IN (SELECT SemesterId FROM Semester) 			THEN SET statusCode = 402; -- wrong SEM
		WHEN inFacultyName NOT IN (SELECT FacultyName FROM Faculty) 		THEN SET statusCode = 403; -- wrong FAC
		WHEN inProgramName NOT IN (SELECT ProgramName FROM Program) 		THEN SET statusCode = 404; -- wrong PRO
		WHEN inModuleName NOT IN (SELECT ModuleName FROM Module) 			THEN SET statusCode = 405; -- wrong MOD
		WHEN inLecturerId NOT IN (SELECT LecturerId FROM Teaching) 			THEN SET statusCode = 406; -- wrong LEC
		ELSE SET statusCode = 200;
	
        SELECT SUM(Size) AS TotalClassesSize
		FROM Class
		NATURAL JOIN Teaching
        NATURAL JOIN Module
        NATURAL JOIN Program
        NATURAL JOIN Faculty
        NATURAL JOIN Semester
        NATURAL JOIN AcademicYear
		NATURAL JOIN ClassInSemesterInModule
        NATURAL JOIN ProgramInFacultyInAcademicYear
		NATURAL JOIN ModuleInProgramInAcademicYear
        NATURAL JOIN FacultyInAcademicYear
		WHERE LecturerId = inLecturerId
        AND SemesterId = inSemesterId
        AND ModuleName = inModuleName
		AND ProgramName = inProgramName
        AND FacultyName = inFacultyName
		AND AYearId = inAYearId;
	END CASE;
END//

DELIMITER ;