INSERT INTO `academicyear` (`AYearId`) VALUES ('2001');
INSERT INTO `academicyear` (`AYearId`) VALUES ('2002');
INSERT INTO `academicyear` (`AYearId`) VALUES ('2003');
INSERT INTO `academicyear` (`AYearId`) VALUES ('2004');

INSERT INTO `faculty` (`FacultyId`, `FacultyName`, `AYearId`) VALUES ('1', 'CS', '2001');
INSERT INTO `faculty` (`FacultyId`, `FacultyName`, `AYearId`) VALUES ('2', 'EE', '2001');
INSERT INTO `faculty` (`FacultyId`, `FacultyName`, `AYearId`) VALUES ('3', 'BA', '2001');
INSERT INTO `faculty` (`FacultyId`, `FacultyName`, `AYearId`) VALUES ('4', 'ARCH', '2001');

INSERT INTO `lecturer` (`LecturerId`, `LecturerName`) VALUES ('1', 'BOB');
INSERT INTO `lecturer` (`LecturerId`, `LecturerName`) VALUES ('2', 'BOB');
INSERT INTO `lecturer` (`LecturerId`, `LecturerName`) VALUES ('3', 'ALICE');
INSERT INTO `lecturer` (`LecturerId`, `LecturerName`) VALUES ('4', 'HENRY');

INSERT INTO `program` (`ProgramId`, `ProgramName`, `FacultyId`) VALUES ('1', 'ComS', '1');
INSERT INTO `program` (`ProgramId`, `ProgramName`, `FacultyId`) VALUES ('2', 'ElecE', '2');
INSERT INTO `program` (`ProgramId`, `ProgramName`, `FacultyId`) VALUES ('3', 'BusA', '3');
INSERT INTO `program` (`ProgramId`, `ProgramName`, `FacultyId`) VALUES ('4', 'Architect', '4');

INSERT INTO `module` (`ModuleId`, `ModuleName`, `ProgramId`) VALUES ('1', 'CS', '1');
INSERT INTO `module` (`ModuleId`, `ModuleName`, `ProgramId`) VALUES ('2', 'EEIT', '2');
INSERT INTO `module` (`ModuleId`, `ModuleName`, `ProgramId`) VALUES ('3', 'BA', '3');
INSERT INTO `module` (`ModuleId`, `ModuleName`, `ProgramId`) VALUES ('4', 'ARCH', '4');

INSERT INTO `semester` (`SemesterId`, `SemesterName`, `AYearId`) VALUES ('1', 'SS', '2001');
INSERT INTO `semester` (`SemesterId`, `SemesterName`, `AYearId`) VALUES ('2', 'WS', '2001');

INSERT INTO `class` (`ClassId`, `Size`, `SemesterId`, `ModuleId`) VALUES ('1', '20', '1', '1');
INSERT INTO `class` (`ClassId`, `Size`, `SemesterId`, `ModuleId`) VALUES ('2', '30', '1', '1');
INSERT INTO `class` (`ClassId`, `Size`, `SemesterId`, `ModuleId`) VALUES ('3', '50', '2', '3');

INSERT INTO `questionnaire` (`LecturerId`, `ClassId`) VALUES ('1', '1');
INSERT INTO `questionnaire` (`LecturerId`, `ClassId`) VALUES ('2', '2');
INSERT INTO `questionnaire` (`LecturerId`, `ClassId`) VALUES ('4', '1');

INSERT INTO `teaching` (`LecturerId`, `ClassId`) VALUES ('1', '1');
INSERT INTO `teaching` (`LecturerId`, `ClassId`) VALUES ('2', '2');
INSERT INTO `teaching` (`LecturerId`, `ClassId`) VALUES ('4', '1');
INSERT INTO `teaching` (`LecturerId`, `ClassId`) VALUES ('1', '2');
INSERT INTO `teaching` (`LecturerId`, `ClassId`) VALUES ('2', '3');

