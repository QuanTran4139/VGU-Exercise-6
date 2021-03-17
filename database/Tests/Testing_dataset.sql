INSERT INTO `academicyear` (`AYearId`) VALUES ('2001');
INSERT INTO `academicyear` (`AYearId`) VALUES ('2002');
INSERT INTO `academicyear` (`AYearId`) VALUES ('2003');
INSERT INTO `academicyear` (`AYearId`) VALUES ('2004');

INSERT INTO `faculty` (`FacultyId`, `FacultyName`, `AYearId`) VALUES ('CENG01', 'Computer And Engineering', '2001');
INSERT INTO `faculty` (`FacultyId`, `FacultyName`, `AYearId`) VALUES ('ECO01', 'Economy', '2001');
INSERT INTO `faculty` (`FacultyId`, `FacultyName`, `AYearId`) VALUES ('ARC01', 'Architecture', '2001');
INSERT INTO `faculty` (`FacultyId`, `FacultyName`, `AYearId`) VALUES ('CENG02', 'Computer And Engineering', '2002');
INSERT INTO `faculty` (`FacultyId`, `FacultyName`, `AYearId`) VALUES ('ECO02', 'Economy', '2002');
INSERT INTO `faculty` (`FacultyId`, `FacultyName`, `AYearId`) VALUES ('ARC02', 'Architecture', '2002');
INSERT INTO `faculty` (`FacultyId`, `FacultyName`, `AYearId`) VALUES ('CENG03', 'Computer And Engineering', '2003');
INSERT INTO `faculty` (`FacultyId`, `FacultyName`, `AYearId`) VALUES ('ECO03', 'Economy', '2003');
INSERT INTO `faculty` (`FacultyId`, `FacultyName`, `AYearId`) VALUES ('ARC03', 'Architecture', '2003');
INSERT INTO `faculty` (`FacultyId`, `FacultyName`, `AYearId`) VALUES ('CENG04', 'Computer And Engineering', '2004');
INSERT INTO `faculty` (`FacultyId`, `FacultyName`, `AYearId`) VALUES ('ECO04', 'Economy', '2004');
INSERT INTO `faculty` (`FacultyId`, `FacultyName`, `AYearId`) VALUES ('ARC04', 'Architecture', '2004');

INSERT INTO `lecturer` (`LecturerId`, `LecturerName`) VALUES ('1', 'BOB');
INSERT INTO `lecturer` (`LecturerId`, `LecturerName`) VALUES ('2', 'BOB');
INSERT INTO `lecturer` (`LecturerId`, `LecturerName`) VALUES ('3', 'ALICE');
INSERT INTO `lecturer` (`LecturerId`, `LecturerName`) VALUES ('4', 'HENRY');
INSERT INTO `lecturer` (`LecturerId`, `LecturerName`) VALUES ('5', 'PETER');

INSERT INTO `program` (`ProgramId`, `ProgramName`, `FacultyId`) VALUES ('CS01', 'CS', 'CENG01');
INSERT INTO `program` (`ProgramId`, `ProgramName`, `FacultyId`) VALUES ('EE01', 'EE', 'CENG01');
INSERT INTO `program` (`ProgramId`, `ProgramName`, `FacultyId`) VALUES ('BA01', 'BA', 'ECO01');
INSERT INTO `program` (`ProgramId`, `ProgramName`, `FacultyId`) VALUES ('AR01', 'Arch', 'ARC01');
INSERT INTO `program` (`ProgramId`, `ProgramName`, `FacultyId`) VALUES ('CS02', 'CS', 'CENG02');
INSERT INTO `program` (`ProgramId`, `ProgramName`, `FacultyId`) VALUES ('EE02', 'EE', 'CENG02');
INSERT INTO `program` (`ProgramId`, `ProgramName`, `FacultyId`) VALUES ('BA02', 'BA', 'ECO02');
INSERT INTO `program` (`ProgramId`, `ProgramName`, `FacultyId`) VALUES ('AR02', 'Arch', 'ARC02');
INSERT INTO `program` (`ProgramId`, `ProgramName`, `FacultyId`) VALUES ('CS03', 'CS', 'CENG03');
INSERT INTO `program` (`ProgramId`, `ProgramName`, `FacultyId`) VALUES ('EE03', 'EE', 'CENG03');
INSERT INTO `program` (`ProgramId`, `ProgramName`, `FacultyId`) VALUES ('BA03', 'BA', 'ECO03');
INSERT INTO `program` (`ProgramId`, `ProgramName`, `FacultyId`) VALUES ('AR03', 'Arch', 'ECO03');
INSERT INTO `program` (`ProgramId`, `ProgramName`, `FacultyId`) VALUES ('CS04', 'CS', 'CENG04');
INSERT INTO `program` (`ProgramId`, `ProgramName`, `FacultyId`) VALUES ('EE04', 'EE', 'CENG04');
INSERT INTO `program` (`ProgramId`, `ProgramName`, `FacultyId`) VALUES ('BA04', 'BA', 'ECO04');
INSERT INTO `program` (`ProgramId`, `ProgramName`, `FacultyId`) VALUES ('AR04', 'Arch', 'ARC04');

INSERT INTO `module` (`ModuleId`, `ModuleName`, `ProgramId`) VALUES ('ComCa1', 'Calculus', 'CS01');
INSERT INTO `module` (`ModuleId`, `ModuleName`, `ProgramId`) VALUES ('ComAl1', 'Algebra', 'CS01');
INSERT INTO `module` (`ModuleId`, `ModuleName`, `ProgramId`) VALUES ('EECa1', 'Calculus', 'EE01');
INSERT INTO `module` (`ModuleId`, `ModuleName`, `ProgramId`) VALUES ('EEAl1', 'Algebra', 'EE01');
INSERT INTO `module` (`ModuleId`, `ModuleName`, `ProgramId`) VALUES ('Ba1', 'BusinessAdmin', 'BA01');
INSERT INTO `module` (`ModuleId`, `ModuleName`, `ProgramId`) VALUES ('Phy1', 'Physics', 'AR01');
INSERT INTO `module` (`ModuleId`, `ModuleName`, `ProgramId`) VALUES ('ComCa2', 'Calculus', 'CS02');
INSERT INTO `module` (`ModuleId`, `ModuleName`, `ProgramId`) VALUES ('ComAl2', 'Algebra', 'CS02');
INSERT INTO `module` (`ModuleId`, `ModuleName`, `ProgramId`) VALUES ('EECa2', 'Calculus', 'EE02');
INSERT INTO `module` (`ModuleId`, `ModuleName`, `ProgramId`) VALUES ('EEAl2', 'Algebra', 'EE02');
INSERT INTO `module` (`ModuleId`, `ModuleName`, `ProgramId`) VALUES ('Ba2', 'BusinessAdmin', 'BA02');
INSERT INTO `module` (`ModuleId`, `ModuleName`, `ProgramId`) VALUES ('Phy2', 'Physics', 'AR02');
INSERT INTO `module` (`ModuleId`, `ModuleName`, `ProgramId`) VALUES ('ComCa3', 'Calculus', 'CS03');
INSERT INTO `module` (`ModuleId`, `ModuleName`, `ProgramId`) VALUES ('ComAl3', 'Algebra', 'CS03');
INSERT INTO `module` (`ModuleId`, `ModuleName`, `ProgramId`) VALUES ('EECa3', 'Calculus', 'EE03');
INSERT INTO `module` (`ModuleId`, `ModuleName`, `ProgramId`) VALUES ('EEAl3', 'Algebra', 'EE03');
INSERT INTO `module` (`ModuleId`, `ModuleName`, `ProgramId`) VALUES ('Ba3', 'BusinessAdmin', 'BA03');
INSERT INTO `module` (`ModuleId`, `ModuleName`, `ProgramId`) VALUES ('Phy3', 'Physics', 'AR03');
INSERT INTO `module` (`ModuleId`, `ModuleName`, `ProgramId`) VALUES ('ComCa4', 'Calculus', 'CS04');
INSERT INTO `module` (`ModuleId`, `ModuleName`, `ProgramId`) VALUES ('ComAl4', 'Algebra', 'CS04');
INSERT INTO `module` (`ModuleId`, `ModuleName`, `ProgramId`) VALUES ('EECa4', 'Calculus', 'EE04');
INSERT INTO `module` (`ModuleId`, `ModuleName`, `ProgramId`) VALUES ('EEAl4', 'Algebra', 'EE04');
INSERT INTO `module` (`ModuleId`, `ModuleName`, `ProgramId`) VALUES ('Ba4', 'BusinessAdmin', 'BA04');
INSERT INTO `module` (`ModuleId`, `ModuleName`, `ProgramId`) VALUES ('Phy4', 'Physics', 'AR04');

INSERT INTO `semester` (`SemesterId`, `SemesterName`, `AYearId`) VALUES ('SS01', 'SummerSemester', '2001');
INSERT INTO `semester` (`SemesterId`, `SemesterName`, `AYearId`) VALUES ('WS01', 'WinterSemester', '2001');
INSERT INTO `semester` (`SemesterId`, `SemesterName`, `AYearId`) VALUES ('SS02', 'SummerSemester', '2002');
INSERT INTO `semester` (`SemesterId`, `SemesterName`, `AYearId`) VALUES ('WS02', 'WinterSemester', '2002');
INSERT INTO `semester` (`SemesterId`, `SemesterName`, `AYearId`) VALUES ('SS03', 'SummerSemester', '2003');
INSERT INTO `semester` (`SemesterId`, `SemesterName`, `AYearId`) VALUES ('WS03', 'WinterSemester', '2003');
INSERT INTO `semester` (`SemesterId`, `SemesterName`, `AYearId`) VALUES ('SS04', 'SummerSemester', '2004');
INSERT INTO `semester` (`SemesterId`, `SemesterName`, `AYearId`) VALUES ('WS04', 'WinterSemester', '2004');

INSERT INTO `class` (`ClassId`, `Size`, `SemesterId`, `ModuleId`) VALUES ('1', '20', 'SS01', 'ComCa1');
INSERT INTO `class` (`ClassId`, `Size`, `SemesterId`, `ModuleId`) VALUES ('2', '30', 'SS01', 'ComCa1');
INSERT INTO `class` (`ClassId`, `Size`, `SemesterId`, `ModuleId`) VALUES ('3', '50', 'WS01', 'EECa1');
INSERT INTO `class` (`ClassId`, `Size`, `SemesterId`, `ModuleId`) VALUES ('4', '50', 'SS01', 'ComCa1');
INSERT INTO `class` (`ClassId`, `Size`, `SemesterId`, `ModuleId`) VALUES ('5', '30', 'WS01', 'EECa1');
INSERT INTO `class` (`ClassId`, `Size`, `SemesterId`, `ModuleId`) VALUES ('6', '50', 'WS01', 'EECa1');


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




