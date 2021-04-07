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

Calling `questionnaire?action=getCounts?cid=_ClassID_&lid=_LecturerID_&q=_Question_` (Question can be `gender` or an `int` in the range 0-17) will return the counts of the answers to all questions in the corresponding Questionnaire.

The response is formatted as follows:

- `question=gender`

```json
{
    "M": 0,
    "F": 0,
    "N": 0,
}
```

- questions 0-4,8-17

```json
{
    "1": 0,
    "2": 0,
    "3": 0,
    "4": 0,
    "5": 0,
}
```

- questions 5-7

```json
{
    "1": 0,
    "2": 0,
    "3": 0,
    "4": 0,
    "5": 0,
    "N/A": 0,
}
```

Replace the `0`s with real counts.

##### `getResponseRate`

Calling `questionnaire?action=getResponseRate&cid=_ClassID_&lid=_LecturerID_` will return a `float` representing the response rate of the corresponding questionnaire.

#### `class`

##### `getClassOptions`

Calling `class?action=getClassOptions&cid=_ClassID_` will return lists of semesters, faculties, programs and lecturers.

The response is formatted as follows:

```json
{
    SemesterIDs: ["123", "456"],
    FacultyNames: ["qwe", "rty"],
    ProgramNames: ["asd", "fgh"],
    Lecturers: [
        {"id": "1a", "name": "John Doe"},
        {"id": "2b", "name": "Joe Smith"}
    ]
}
```

## `PUT`

To add an object to the database, make a `PUT` request to the corresponding resource with a JSON request body formatted as listed. (All parameters are `String`s unless specified otherwise; `id` values have a max length of 10, `name` values have a max length of 100)

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

`comment`'s max length is 500.

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
- `moduleInProgramInAcademicYear`: `mid` (ModuleID), `yid` (AcademicYearID)
- `program`: `pid` (ProgramID)
- `programInFacultyInAcademicYear`: `pid` (ProgramID), `fid` (FacultyID), `yid` (AcademicYearID)
- `questionnaire`: `lid` (LecturerID), `cid` (ClassID), `qid` (QuestionnaireID)
- `semester`: `sid` (SemesterID)
- `teaching`: `lid` (LecturerID), `cid` (ClassID)
