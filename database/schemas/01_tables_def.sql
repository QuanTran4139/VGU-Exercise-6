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
    LecturerId VARCHAR(10),
    ClassId VARCHAR(10),
    Content LONGBLOB, 

    PRIMARY KEY (LecturerId,ClassId),
    FOREIGN KEY (LecturerId)
		REFERENCES Lecturer (LecturerId),
	FOREIGN KEY (ClassId)
		REFERENCES Class (ClassId)
);