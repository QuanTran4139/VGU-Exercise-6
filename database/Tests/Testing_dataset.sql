INSERT INTO `academicyear` (`AYearId`) VALUES ('2001');
INSERT INTO `academicyear` (`AYearId`) VALUES ('2002');
INSERT INTO `academicyear` (`AYearId`) VALUES ('2003');
INSERT INTO `academicyear` (`AYearId`) VALUES ('2004');

INSERT INTO `faculty` (`FacultyId`, `FacultyName`) VALUES ('CENG', 'Computer And Engineering');
INSERT INTO `faculty` (`FacultyId`, `FacultyName`) VALUES ('ECO', 'Economy');
INSERT INTO `faculty` (`FacultyId`, `FacultyName`) VALUES ('ARC', 'Architecture' );

INSERT INTO `facultyinacademicyear` (`FacultyId`, `AYearId`) VALUES ( 'CENG', '2001');
INSERT INTO `facultyinacademicyear` (`FacultyId`, `AYearId`) VALUES ( 'CENG', '2002');
INSERT INTO `facultyinacademicyear` (`FacultyId`, `AYearId`) VALUES ( 'CENG', '2003');
INSERT INTO `facultyinacademicyear` (`FacultyId`, `AYearId`) VALUES ( 'CENG', '2004');
INSERT INTO `facultyinacademicyear` (`FacultyId`, `AYearId`) VALUES ( 'ECO', '2001');
INSERT INTO `facultyinacademicyear` (`FacultyId`, `AYearId`) VALUES ( 'ECO', '2002');
INSERT INTO `facultyinacademicyear` (`FacultyId`, `AYearId`) VALUES ( 'ECO', '2003');
INSERT INTO `facultyinacademicyear` (`FacultyId`, `AYearId`) VALUES ( 'ECO', '2004');
INSERT INTO `facultyinacademicyear` (`FacultyId`, `AYearId`) VALUES ( 'ARC', '2001');
INSERT INTO `facultyinacademicyear` (`FacultyId`, `AYearId`) VALUES ( 'ARC', '2002');
INSERT INTO `facultyinacademicyear` (`FacultyId`, `AYearId`) VALUES ( 'ARC', '2003');
INSERT INTO `facultyinacademicyear` (`FacultyId`, `AYearId`) VALUES ( 'ARC', '2004');

INSERT INTO `lecturer` (`LecturerId`, `LecturerName`) VALUES ('1', 'BOB');
INSERT INTO `lecturer` (`LecturerId`, `LecturerName`) VALUES ('2', 'BOB');
INSERT INTO `lecturer` (`LecturerId`, `LecturerName`) VALUES ('3', 'ALICE');
INSERT INTO `lecturer` (`LecturerId`, `LecturerName`) VALUES ('4', 'HENRY');
INSERT INTO `lecturer` (`LecturerId`, `LecturerName`) VALUES ('5', 'PETER');

INSERT INTO `program` (`ProgramId`, `ProgramName`) VALUES ('CS', 'Computer Science');
INSERT INTO `program` (`ProgramId`, `ProgramName`) VALUES ('EE', 'Electrical Engineering');
INSERT INTO `program` (`ProgramId`, `ProgramName`) VALUES ('BA', 'Business Administrator');
INSERT INTO `program` (`ProgramId`, `ProgramName`) VALUES ('AR', 'Architecture');

INSERT INTO `programinfaculty` (`ProgramId`, `FacultyId`) VALUES ( 'CS', 'CENG');
INSERT INTO `programinfaculty` (`ProgramId`, `FacultyId`) VALUES ( 'EE', 'CENG');
INSERT INTO `programinfaculty` (`ProgramId`, `FacultyId`) VALUES ( 'BA', 'ECO');
INSERT INTO `programinfaculty` (`ProgramId`, `FacultyId`) VALUES ( 'AR', 'ARC');

INSERT INTO `module` (`ModuleId`, `ModuleName`) VALUES ('ComCa', 'Calculus');
INSERT INTO `module` (`ModuleId`, `ModuleName`) VALUES ('ComAl', 'Algebra');
INSERT INTO `module` (`ModuleId`, `ModuleName`) VALUES ('EECa', 'Calculus');
INSERT INTO `module` (`ModuleId`, `ModuleName`) VALUES ('EEAl', 'Algebra');
INSERT INTO `module` (`ModuleId`, `ModuleName`) VALUES ('Ba', 'BusinessAdmin');
INSERT INTO `module` (`ModuleId`, `ModuleName`) VALUES ('Phy', 'Physics');

INSERT INTO `moduleinprogram` (`ModuleId`, `ProgramId`) VALUES ( 'ComCa', 'CS');
INSERT INTO `moduleinprogram` (`ModuleId`, `ProgramId`) VALUES ( 'ComAl', 'CS');
INSERT INTO `moduleinprogram` (`ModuleId`, `ProgramId`) VALUES ( 'EECa', 'EE');
INSERT INTO `moduleinprogram` (`ModuleId`, `ProgramId`) VALUES ( 'EEAl', 'EE');
INSERT INTO `moduleinprogram` (`ModuleId`, `ProgramId`) VALUES ( 'Ba', 'BA');
INSERT INTO `moduleinprogram` (`ModuleId`, `ProgramId`) VALUES ( 'Phy', 'AR');

INSERT INTO `semester` (`SemesterId`, `SemesterName`) VALUES ('SS', 'SummerSemester');
INSERT INTO `semester` (`SemesterId`, `SemesterName`) VALUES ('WS', 'WinterSemester');

INSERT INTO `semesterinacademicyear` (`SemesterId`, `AYearId`) VALUES ( 'SS', '2001');
INSERT INTO `semesterinacademicyear` (`SemesterId`, `AYearId`) VALUES ( 'SS', '2002');
INSERT INTO `semesterinacademicyear` (`SemesterId`, `AYearId`) VALUES ( 'SS', '2003');
INSERT INTO `semesterinacademicyear` (`SemesterId`, `AYearId`) VALUES ( 'SS', '2004');
INSERT INTO `semesterinacademicyear` (`SemesterId`, `AYearId`) VALUES ( 'WS', '2001');
INSERT INTO `semesterinacademicyear` (`SemesterId`, `AYearId`) VALUES ( 'WS', '2002');
INSERT INTO `semesterinacademicyear` (`SemesterId`, `AYearId`) VALUES ( 'WS', '2003');
INSERT INTO `semesterinacademicyear` (`SemesterId`, `AYearId`) VALUES ( 'WS', '2004');


INSERT INTO `class` (`ClassId`, `Size`, `SemesterId`, `ModuleId`) VALUES ('1', '20', 'SS', 'ComCa');
INSERT INTO `class` (`ClassId`, `Size`, `SemesterId`, `ModuleId`) VALUES ('2', '30', 'SS', 'ComCa');
INSERT INTO `class` (`ClassId`, `Size`, `SemesterId`, `ModuleId`) VALUES ('3', '50', 'WS', 'EECa');
INSERT INTO `class` (`ClassId`, `Size`, `SemesterId`, `ModuleId`) VALUES ('4', '50', 'SS', 'ComCa');
INSERT INTO `class` (`ClassId`, `Size`, `SemesterId`, `ModuleId`) VALUES ('5', '30', 'WS', 'EECa');
INSERT INTO `class` (`ClassId`, `Size`, `SemesterId`, `ModuleId`) VALUES ('6', '50', 'WS', 'EECa');


INSERT INTO `questionnaire` (`LecturerId`, `ClassId`) VALUES ('1', '1');
INSERT INTO `questionnaire` (`LecturerId`, `ClassId`) VALUES ('2', '2');
INSERT INTO `questionnaire` (`LecturerId`, `ClassId`) VALUES ('4', '1');

INSERT INTO `teaching` (`LecturerId`, `ClassId`) VALUES ('1', '1');
INSERT INTO `teaching` (`LecturerId`, `ClassId`) VALUES ('1', '2');
INSERT INTO `teaching` (`LecturerId`, `ClassId`) VALUES ('1', '4');
INSERT INTO `teaching` (`LecturerId`, `ClassId`) VALUES ('1', '5');
INSERT INTO `teaching` (`LecturerId`, `ClassId`) VALUES ('1', '6');
INSERT INTO `teaching` (`LecturerId`, `ClassId`) VALUES ('2', '2');
INSERT INTO `teaching` (`LecturerId`, `ClassId`) VALUES ('2', '3');
INSERT INTO `teaching` (`LecturerId`, `ClassId`) VALUES ('3', '2');
INSERT INTO `teaching` (`LecturerId`, `ClassId`) VALUES ('4', '1');
INSERT INTO `teaching` (`LecturerId`, `ClassId`) VALUES ('5', '1');




