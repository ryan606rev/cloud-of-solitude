# cloud-of-solitude
Ryan Bovee's repository

I have created this repository for current and future projects.
Possibly even past projects if I get around to it.


Jr. Coding Challenge Requirements

Program must:
1. accept a .csv file DONE
2. pase the data from the .csv file 
    2a. ! must have exception for "," that are in a field
3. inset the records into a SQLite database
    3a. database must be nammed: <input-filename>.db
    3b. one table, 10 columns, A-J column header names
    3c. ! must be able to isolate the file name with the complication that...
    3c cont. the target file has been named with a . in the file name
4. records that have (a) null field(s) are aplaced in a separate db: <input-filename>-bad.db
5. after the dbs are created output a log file: <input-filename>.log; must contain:
    5a. number of records received
    5b. number of records successful
    5c. number of records failed (number of records in the bad database)
6. make an effort optimise solution
7. every run should have the same result
8. README with: summary, instructions, [overview: arroach, design decisions, assumptions]

! denotes an implied requirement based on the operating realities


TODO:
! figure out how to do the exception for the commas within the values
! get the array into the sqlite database(create the mehtod that calls insert() in a new class)
! be able to print the rows with null fields (print back to csv file)
I need to sort the data into two tables

Task 1 monday: figure out how to create the database file from within javaa COMPLETE

fix the create table statement COMPELETE
! see how to write the log, will have to be done java side not sqlite side

