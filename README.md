# Project details
### Project Name
CSV (Sprint 1)
### Team members and contributions (include cs logins)
Jakob Wismar (jwismar)
### Total estimated time it took to complete project
10 hours
### GitHub repository
[https://github.com/jakobwismar/Sprint-1-CSV](https://github.com/jakobwismar/CSV)
# Design choices
### Explain the relationships between classes/interfaces.
- ``Parser.java`` takes in a row formatter interface implemented by different classes for different formatting.
### Discuss any specific data structures you used, why you created it, and other high level explanations.
- Created ``Args`` class to organize the command line arguments into a structure that defines the purpose of each argument and verifies the completeness of all arguments.
# Errors/Bugs
No known errors or bugs exist in the program.
# Tests
The test suite for this assignment is incomplete.
# Usage
### Running the tests you wrote/were provided
- The test suite for this assignment is incomplete.
### Building and running your program
* In the project directory, execute ``Main.java`` with the following configurations: ``-f FilePath -s SearchText [-i SearchColumnIndex |" -c SearchColumnName] [-h]``, where:
  * ``FilePath`` is the path to a CSV file.
  * ``SearchText`` is the phrase to be searched for.
  * ``SearchColumnIndex`` (optional) is the index of the column to be searched.
  * ``SearchColumnName`` (optional) is the name of the column to be searched, if the CSV file has column headers.
  * ``[-h]`` specifies whether the provided CSV file includes a header.