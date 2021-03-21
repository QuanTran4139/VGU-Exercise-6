  
INSERT INTO `academicyear` (`AYearId`) VALUES 	(2001),
												(2002),
												(2003),
												(2004);

INSERT INTO `faculty` (`FacultyId`, `FacultyName`) VALUES 	('CENG', 'Computer And Engineering'),
															('ECO', 'Economy'),
															('ARC', 'Architecture' );

INSERT INTO `facultyinacademicyear` (`FacultyName`, `AYearId`) VALUES ( 'Computer And Engineering', 2001),
																	( 'Computer And Engineering', 2002),
																	( 'Computer And Engineering', 2003),
																	( 'Computer And Engineering', 2004),
																	( 'Economy', 2001),
																	( 'Economy', 2002),
																	( 'Economy', 2003),
																	( 'Economy', 2004),
																	( 'Architecture', 2001),
																	( 'Architecture', 2002),
																	( 'Architecture', 2003),
																	( 'Architecture', 2004);

INSERT INTO `lecturer` (`LecturerId`, `LecturerName`) VALUES 	('1', 'BOB'),
																('2', 'BOB'),
																('3', 'ALICE'),
																('4', 'HENRY'),
																('5', 'PETER');

INSERT INTO `program` (`ProgramId`, `ProgramName`) VALUES 	('CS', 'Computer Science'),
															('EE', 'Electrical Engineering'),
															('BA', 'Business Administrator'),
															('AR', 'Architecture');

INSERT INTO `programinfacultyinacademicyear` (`ProgramName`, `FacultyId`, `AYearId`) VALUES 	( 'Computer Science', 'CENG', 2001),
																							    ( 'Computer Science', 'CENG', 2002),
																							    ( 'Electrical Engineering', 'CENG', 2001),
																							    ( 'Business Administrator', 'ECO', 2002),
																							    ( 'Architecture', 'ARC', 2002);

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

INSERT INTO `semester` (`SemesterId`) VALUES 	('SS'),
												('WS');

INSERT INTO `semesterinacademicyear` (`SemesterId`, `AYearId`) VALUES 	( 'SS', 2001),
																		( 'SS', 2002),
																		( 'SS', 2003),
																		( 'SS', 2004),
																		( 'WS', 2001),
																		( 'WS', 2002),
																		( 'WS', 2003),
																		( 'WS', 2004);


INSERT INTO `class` (`ClassId`, `Size`) VALUES 	('1', '20'),
												('2', '30'),
												('3', '50'),
												('4', '50'),
												('5', '30'),
												('6', '50');
                                                
INSERT INTO `classinsemesterinmoduleinacademicyear` (`ClassId`, `SemesterId`, `ModuleId`,`AYearId`) VALUES 	('1', 'WS', 'EECa',2001),
																										    ('2', 'WS', 'ComCa',2001),
																										    ('3', 'WS', 'EECa',2001),
																										    ('4', 'SS', 'ComCa',2001),
																										    ('5', 'WS', 'Ba',2001),
																										    ('6', 'SS', 'Phy',2001);

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