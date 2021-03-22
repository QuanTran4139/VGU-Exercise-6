/*
    Test if the Academic Year is created correctly 
*/
CALL createAcademicYear('1', @statusCode);
-- expected: no error code


/*
    Test if the Academic Year is not created when the id already exists in the database
*/
CALL createAcademicYear('1', @statusCode);
CALL createAcademicYear('1', @statusCode);
-- expected: error code 402


/* 
    Test if the request is rejected when AYearId is invalid
*/
CALL createAcademicYear('@', @statusCode);
-- expected: error code 460