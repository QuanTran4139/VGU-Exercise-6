# API endpoints and calls

## API endpoints

The following endpoints can be reached at `/Questionnaire/api/endpoint_name`, where `endpoint_name` is replaced with the appropriate endpoint

- `academicYear`
- `class`
- `faculty`
- `facultyInAcademicYear`
- `lecturer`
- `module`
- `moduleInProgramInAcademicYear`
- `program`
- `programInFacultyInAcademicYear`
- `questionnaire`
- `semester`
- `teaching`

## `GET`

### General actions

These are possible values for the `action` parameter handled by *all* endpoints

#### `dump`

This dumps the entire table for each resource/endpoint. The response is formatted as follows:

```json
[
    {
        name1: value1,
        name2: value2,
        name3: value3,
    },
    {
        name1: value4,
        name2: value5,
        name3: value6,
    },
]
```

### Endpoint-specific actions

#### `questionnaire`

##### `getCounts`

Calling `questionnaire?action=getCounts` will return the counts of the answers to all questions in the Questionnaire.

The response is formatted as follows:

```json
{
    "gender": {
        "M": count(gender == "M"),
        "F": count(gender == "F"),
        "N": count(gender == "N"),
    },
    "qa": [
        [a0, a1, a2, a3],
        [a0, a1, a2, a3],
        [a0, a1, a2, a3],
    ]
}
```

Where `"qa"` (questions-answers) is an array of questions, and each question is an array of answers, where `a0 = count(answer == "N/A")`, `a1 = count(answer == 1)`, and so on, with the length of each answer being the number of possible answers (including "N/A").

##### `getResponseRate`

Calling `questionnaire?action=getResponseRate&cid=_ClassID_&lid=_LecturerID_&qid=_QuestionnaireID` will return a `float` representing the response rate of the corresponding questionnaire.

#### `class`

##### `getClassOptions`

Calling `class?action=getClassOptions&cid=_ClassID_` will return lists of semesters, faculties, programs and lecturers.

The response is formatted as follows:

```json
{
    "semesters": [
        {"SemesterID": "WS2020", "AYearID": 2020},
        {"SemesterID": "SS2021", "AYearID": 2021},
    ],
    "faculties": [
        {"FacultyID": "A", "FacultyName": "AAAA"},
        {"FacultyID": "B", "FacultyName": "bruh"},
    ],
    "programs": [
        {"ProgramID": "A1", "ProgramName": "hello"},
        {"ProgramID": "B2", "ProgramName": "world"},
    ],
    "lecturers": [
        {"LecturerID": "1", "LecturerName": "Bob"},
        {"LecturerID": "2", "LecturerName": "Alice"},
    ]
}
```

## `PUT`

To add an object to the database, make a `PUT` request to the corresponding resource with a JSON request body formatted as listed. (All parameters are `String`s unless specified otherwise.)

### `academicYear`

```json
{
    "yid": _AcademicYearID_
}
```

`yid` must be an `int`

### `class`

```json
{
    "cid": _ClassID_,
    "size": _ClassSize_,
    "sid": _SemesterID_,
    "mid": _ModuleID_
}
```

`size` must be an `int`

### `faculty`

```json
{
    "id": _FacultyID_,
    "name": _FacultyName_
}
```

### `facultyInAcademicYear`

```json
{
    "fid": _FacultyID_,
    "yid": _AcademicYearID_
}
```

`yid` must be an `int`

### `lecturer`

```json
{
    "id": _LecturerID_,
    "name": _LecturerName_
}
```

### `module`

```json
{
    "id": _ModuleID_,
    "name": _ModuleName_
}
```

### `moduleInProgramInAcademicYear`

```json
{
    "mid": _ModuleID_,
    "yid": _AcademicYearID_
}
```

`yid` must be an `int`

### `program`

```json
{
    "id": _ProgramID_,
    "name": _ProgramName_
}
```

### `programInFacultyInAcademicYear`

```json
{
    "pid": _ProgramID_,
    "fid": _FacultyID_,
    "yid": _AcademicYearID_
}
```

`yid` must be an `int`

### `questionnaire`

```json
{
    "lid": _LecturerID_,
    "cid": _ClassID_,
    "gender": _Gender_,
    "qa": [_answers_],
    "comment": _Comment_,
}
```

`qa` is an `int` array of length 18, where each element can be in the range 0-5 (except 5,6,7) and `0` represents "N/A".

### `semester`

```json
{
    "sid": _SemesterID_,
    "yid": _AcademicYearID_
}
```

`yid` must be an `int`

### `teaching`

```json
{
    "lid": _LecturerID_,
    "cid": _ClassID_
}
```

## `DELETE`

 To delete a resource/object from the database, make a `DELETE` request to the corresponding resource in `api/` with the provided parameters:

- `academicYear`: `yid` (AcademicYearID)
- `class`: `cid` (ClassID)
- `faculty`: `id` (FacultyID)
- `facultyInAcademicYear`: `fid` (FacultyID), `yid` (AcademicYearID)
- `lecturer`: `lid` (LecturerID)
- `module`: `mid` (ModuleID)
- `moduleInProgramInAcademicYear`: `mid` (ModuleID), `pid` (ProgramID), `yid` (AcademicYearID)
- `program`: `pid` (ProgramID)
- `programInFacultyInAcademicYear`: `pid` (ProgramID), `fid` (FacultyID), `yid` (AcademicYearID)
- `questionnaire`: `lid` (LecturerID), `cid` (ClassID), `qid` (QuestionnaireID)
- `semester`: `sid` (SemesterID)
- `teaching`: `lid` (LecturerID), `cid` (ClassID)
