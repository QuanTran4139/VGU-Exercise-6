CALL GetTotalClassesSize('2001','WS01','CENG01','EE01','EECa1','1',@statusCode);

CALL GetTotalClassesSize('2001','WS01','CENG01','CS01','ComCa1','1',@statusCode);

// Modified for current procedures + tests (20/03/21)
CALL GetTotalClassesSize('BOB', 'Calculus', 'Electrical Engineering', 'Computer And Engineering', 'WinterSemester', '2001', @statusCode);
// Expected code results - 130

