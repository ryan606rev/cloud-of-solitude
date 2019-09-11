# cloud-of-solitude
Ryan Bovee's repository
https://github.com/ryan606rev/cloud-of-solitude

Introduction:
I have created this repository for current and future projects. I still have many old project that I havn not 
pushed up here but I will once I get them fixed up and ready to show. My plan is for this to be my main sample code
repository. There is at least one pieces of trash up here though.

Dear MS3 Staff,

In this repository you will find a folder called: CSVParser
This file is my eclipse project folder. I tried making and executable .jar which I have incuded in this repository
along with the .exe file that I tried to create from it. I wanted to make an easy way to launch the program but this
project was more work than expected and based on the requirements document it didn't seem like you expected it to be 
all dressed up anyway. That is my assumption anyway.

Steps to launch:
1. Download the folder: CSVParser
2. Open the project in eclipse or whatever compatible IDE.
3. The class "CSVParser.java" contains the main method.
4. When you run the project you will be prompted to enter the file path of the .csv file you want to parse.
5. The requested files and database will be exported to: C:\CSVParser

Jr. Coding Challenge Overview:
    Like many projects, as soon as I read the requirments my mind went strait to work rationalizing how the
end product work work and what I would need to do in order to get there. Suprising no one it was not that simple and 
there were in fact hurdles for me navigate. When I first started work on the project I focused on being able to read 
the CSV file. There maybe scars from that still in the code that I forgot to clean up. When I first started I was not 
using Maven. I decided that since I was coming back to java after a several year hiatus that I did not want new stuff
adding to the challenge of the project. Hours in however, I realized that it would help me with my dependencies and it
ended up making my life easier in that respect. Im sure there is much more it could have helped me with if I had taken 
the time to learn it all but this project already ended up taking much longer than I expected. 

I decided to parse the csv file with opencsv straight into an array. When I read the requirements I took parse to mean
take the text in the csv and turn it into usable strings. Late in the development process I discovered a method in 
opencsv that was called Parse(). Apparently that would have let me assign different data types to the different columns and allowed me to set them up in java objects. But at that point much of the code was already centered around
manipulating the arrays. I am not sure if my solution uses less memory but it seemed to work okay with this data set.

I have three classes. Maybe more would have been better but I thought it worked out okay this way this time. I have
one pretty much just for my main, another for importing and dealing with the arrays, and a third for interacting with
SQLite. Alot of the base code in this project I got from online tutorials or books form the library. This is mostly 
true for the section that deal directly with SQLite, opencsv, and printing the log. Those were either new to me or 
utilities that I have not used since my undergrad. The more complex methods such as the quality control method is 
completely me. It took me many tries to get that right and it even ended up being my final hurdle, getting that 
to work correctly.

That is probably the most complex part of the whole project because it involves three arrays, a nested for loop, a 
boolean value, and many pointers. I do not think I have written anything like that since I was working on my last game.
The whole idea is that the csv file is parsed directly into the master array. The master array then needs to be sorted 
because the data entries that are missing fields cannot go into the database. The solution was to separate the master
array into the good array that would go into the database and the temporary bad array to be returned in a new csv file.
A count from the sorting process (good) would later be used to tell my insert method how many entries to put into the 
database, this saved me from creating a new appropriately sized "good array." This was important to me because I knew 
that I was using alot of memory as it was and I wanted to avoid making it worse. The temp bad array is later 
transferred into a new appropriately sized array so that a long list of null fields would not be printing out in
the new csv-bad file.

I had an issue inserting into the table that created in my database. The java tutorials were basically all identical
so I ended up looking directly at the SQLite text commands and that is where I found my solution.

In the end I believe there were still a few errors thrown by SQLite about a few of the entries but those will have to 
remain for now because I have run out of time. Overall I do Believe that I have satisfied the requirements for this 
project. I hope my effort shows in the finished product because it was quite a bit of work to get it to this point. I 
really enjoyed the easter eggs. I found two, on accident of course, who would go looking for those at a time like 
this. HELLOWORLD HAS NOTHING ON ME came at just the right time. I made my output espically verbose because I was 
trying to solve an issue. When I saw that, I was relieved because I knew that it meant that the creators had a good 
sense of humor and respect for programming heritage. It reminded me that this was made knowing that the task might 
give a young programmer some grief. Also the encouraging words I saw were much appreciated. There were times when I 
was so worn out and I did not even want to look at a computer screen for the rest of the day but also others when I 
was almost shaking was excitement because I finally worked past a major hurdle. I hope this project is a positive 
testament to my potential and problem solving skills. Given more time there is no doubt more comments to be written 
and optimization all around. But for now I will just say this: Thank you for this opportunity and for spending your 
time reviewing me as a candidate.

Ryan Bovee

Below I have left some of my notes that I threw in here at various times. It is not complete becuse I also did
some work in a physical journal.

If ther are any questions please feel free to contact me. My contact information is on my linkedin and whit my
recruiter.


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

