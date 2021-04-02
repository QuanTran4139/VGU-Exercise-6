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
	FacultyId VARCHAR(10),
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
	ProgramId VARCHAR(10),
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
    SemesterId VARCHAR(10),
    ModuleId VARCHAR(10),
   
    PRIMARY KEY (ClassId,SemesterId,ModuleId),
    FOREIGN KEY (SemesterId)
		REFERENCES Semester (SemesterId),
	FOREIGN KEY (ModuleId)
		REFERENCES ModuleInProgramInAcademicYear (ModuleId),
	CONSTRAINT Class
		UNIQUE (ClassId)

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
	QuestionnaireId INT AUTO_INCREMENT,
    LecturerId VARCHAR(10) NOT NULL,
    ClassId VARCHAR(10) NOT NULL, 
    Gender CHAR,
    Question0 ENUM('Never','Rarely','Sometimes','Often','Always') NOT NULL,
	Question1 ENUM('1','2','3','4','5','N/A') NOT NULL,
    Question2 ENUM('1','2','3','4','5','N/A') NOT NULL,
    Question3 ENUM('1','2','3','4','5','N/A') NOT NULL,
    Question4 ENUM('1','2','3','4','5','N/A') NOT NULL,
    Question5 ENUM('1','2','3','4','5') NOT NULL,
    Question6 ENUM('1','2','3','4','5') NOT NULL,
    Question7 ENUM('1','2','3','4','5') NOT NULL,
    Question8 ENUM('1','2','3','4','5','N/A') NOT NULL,
    Question9 ENUM('1','2','3','4','5','N/A') NOT NULL,
    Question10 ENUM('1','2','3','4','5','N/A') NOT NULL,
    Question11 ENUM('1','2','3','4','5','N/A') NOT NULL,
    Question12 ENUM('1','2','3','4','5','N/A') NOT NULL,
    Question13 ENUM('1','2','3','4','5','N/A') NOT NULL,
    Question14 ENUM('1','2','3','4','5','N/A') NOT NULL,
    Question15 ENUM('1','2','3','4','5','N/A') NOT NULL,
    Question16 ENUM('1','2','3','4','5','N/A') NOT NULL,
    Question17 ENUM('1','2','3','4','5','N/A') NOT NULL,
    Comment TEXT,
    
    PRIMARY KEY (QuestionnaireId,LecturerId,ClassId),
    FOREIGN KEY (LecturerId)
		REFERENCES Teaching (LecturerId),
	FOREIGN KEY (ClassId)
		REFERENCES Teaching (ClassId)
	CONSTRAINT Questionnaire
		UNIQUE (QuestionnaireId)
);