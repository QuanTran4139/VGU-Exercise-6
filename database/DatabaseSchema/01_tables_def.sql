CREATE TABLE IF NOT EXISTS AcademicYear (
    AYearId VARCHAR(10),

    PRIMARY KEY (AYearId)
);

CREATE TABLE IF NOT EXISTS Semester (
   SemesterId VARCHAR(10),
   SemesterName VARCHAR(100) NOT NULL,
   AYearId VARCHAR(10) NOT NULL,
    PRIMARY KEY (SemesterId,AYearId),
    FOREIGN KEY (AYearId)
		REFERENCES AcademicYear (AYearId)
		ON DELETE CASCADE,
        CONSTRAINT SemesterInAcademicYear
        UNIQUE (AYearId, SemesterName)
);

CREATE TABLE IF NOT EXISTS Faculty (
    FacultyId VARCHAR(10),
	FacultyName VARCHAR(100) NOT NULL,
	AYearId VARCHAR(10) NOT NULL,

    PRIMARY KEY (FacultyId),
	FOREIGN KEY (AYearId)
		REFERENCES AcademicYear (AYearId)
		ON DELETE CASCADE,
    CONSTRAINT FacultyInAcademicYear
        UNIQUE (AYearId, FacultyName)
);

CREATE TABLE IF NOT EXISTS Program (
    ProgramId VARCHAR(10),
    ProgramName VARCHAR(100) NOT NULL,
    FacultyId VARCHAR(10) NOT NULL,

    PRIMARY KEY (ProgramId),
	FOREIGN KEY (FacultyId)
		REFERENCES Faculty (FacultyId)
		ON DELETE CASCADE,
    CONSTRAINT ProgramInFaculty
        UNIQUE (FacultyId, ProgramName)
);

CREATE TABLE IF NOT EXISTS Module (
    ModuleId VARCHAR(10),
    ModuleName VARCHAR(100) NOT NULL,
    ProgramId VARCHAR(10) NOT NULL,

    PRIMARY KEY (ModuleId),
	FOREIGN KEY (ProgramId)
		REFERENCES Program (ProgramId)
		ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Class (
    ClassId VARCHAR(10),
    ClassSize VARCHAR(10) NOT NULL,
    SemesterId VARCHAR(10) NOT NULL,
    ModuleId VARCHAR(10) NOT NULL,

    PRIMARY KEY (ClassId),
	FOREIGN KEY (SemesterId)
		REFERENCES Semester (SemesterId)
		ON DELETE CASCADE,
	FOREIGN KEY (ModuleId)
		REFERENCES Module (ModuleId)
		ON DELETE CASCADE,
    CONSTRAINT ClassInModule
        UNIQUE (ModuleId, ClassId),
	CONSTRAINT ClassInSemester
        UNIQUE (SemesterId, ClassId)

);

CREATE TABLE IF NOT EXISTS Lecturer (
    LecturerId VARCHAR(10),
    LecturerName VARCHAR(100) NOT NULL,

    PRIMARY KEY (LecturerId)
);

CREATE TABLE IF NOT EXISTS Teaching (
    LecturerId VARCHAR(10) NOT NULL,
    ClassId VARCHAR(10) NOT NULL,

    PRIMARY KEY (LecturerId,ClassId),
    FOREIGN KEY (LecturerId)
		REFERENCES Lecturer (LecturerId)
		ON DELETE CASCADE,
	FOREIGN KEY (ClassId)
		REFERENCES Class (ClassId)
		ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Questionnaire (
    LecturerId VARCHAR(10) NOT NULL,
    ClassId VARCHAR(10) NOT NULL,
    Content LONGBLOB,

    PRIMARY KEY (LecturerId,ClassId),
    FOREIGN KEY (LecturerId)
		REFERENCES Lecturer (LecturerId)
		ON DELETE CASCADE,
	FOREIGN KEY (ClassId)
		REFERENCES Class (ClassId)
		ON DELETE CASCADE
);
