create database OnlineExams;
use OnlineExams;
-- ............................... Student table ............................. --
create table Students(
ID int AUTO_INCREMENT,
Password varchar(200),
Name varchar(64),
PhoneNumber int,
Email varchar(100),
budget int,
primary key(ID));
alter table Students AUTO_INCREMENT=1; -- start incrementing from id=1 --
-- to insert dont add id. -- 


-- ........................... Exams  table ........................... --
create table Exams(
ID int AUTO_INCREMENT,
Type varchar(32),
Subject varchar(32),
primary key(ID));
alter table Exams AUTO_INCREMENT=1000; -- start incrementing exam id from 1000. --




-- ..................... Student to Exam relationship table ........................ --
create table Student2Exam(
ID int AUTO_INCREMENT,
StudentResult int,
StudentID int,
ExamID int,
primary key(ID),
foreign key(StudentID) references Students(ID),
foreign key(ExamID) references Exams(ID));
alter table student2exam AUTO_INCREMENT=2000;
alter table student2exam add totalExamsMark int ;



-- ................ Questions Table ................ --

create table Questions(
QuestionID int AUTO_INCREMENT,
Text varchar(2000),
Answer int,
primary key(QuestionID));
alter table Questions AUTO_INCREMENT=2000;
alter table Questions add ExamID int;
alter table Questions add foreign key(ExamID) references Exams(ID);


-- we have to insert question ID manually here. using loop in java starting question id= 2000 --
 

-- ................... Manager table ................ --
create table Manager(
SSN int,
PhoneNumber int,
Name varchar(90),
budget int,
primary key(SSN));

alter table manager add Password varchar(32);
-- ................. Teachers table ............... --
create table Teachers(
ID int AUTO_INCREMENT,
Name varchar(100),
Email varchar(200),
budget int,
primary key(ID));
alter table Teachers add Password varchar(32);
alter table Teachers AUTO_INCREMENT=3000;
-- teacher IDS auto increment. only add the other values --

alter table Exams add TeacherID int;
alter table Exams add foreign key(TeacherID) references Teachers(ID);

-- ............. Fees table ........... --
create table Fees(
FeeID int,
amountPaid int,
primary key(FeeID));

alter table Fees add TeacherID int;
alter table Fees add foreign key(TeacherID) references Teachers(ID);
alter table Fees add StudentID int;
alter table Fees add foreign key(StudentID) references Students(ID);
alter table Fees add ManagerSSN int;
alter table Fees add foreign key(ManagerSSN) references Manager(SSN);
-- we have to insert Fee ID manually here. start from fee id = 6000 with loops. --

Insert into manager values(1,0599,'leo',2000,'leopass');
Insert into teachers (Name,Email,Password,budget) values('muath','muath@gmail.com','1234',0);

insert into exams (Type,Subject,TeacherID) values ('quiz','Java',3000);
insert into Questions values(1,'what is used to find and fix bugs in the Java programs_JVM#JRE#JDK#JDB' ,4,1000);
insert into Questions values(2,'Which of the following is true about the anonymous inner class?_It has only methods#Objects cant be created#It has a fixed class name#It has no class name' ,4,1000);
insert into Questions values(3,'In which memory a String is stored, when we create a string using new operator?_Stack#String memory#Heap memory#Random storage space' ,3,1000);
insert into Questions values(4,'Which keyword is used for accessing the features of a package?_package#import#extends#export' ,2,1000);
insert into Questions values(5,'What is the initial quantity of the ArrayList list?_5#10#0#100' ,2,1000);
Insert into students (password,Name,PhoneNumber,Email,budget) values ('1234','ahmad',599,'ahmad@gmail.com',200);

insert into exams (Type,Subject,TeacherID) values ('midterm','Java',3000);
insert into Questions values(6,'Which of the following is a mutable class in java?_java.lang.String#java.lang.Byte#java.lang.Short#java.lang.StringBuilder' ,4,1001);
insert into Questions values(7,'Which is a valid keyword in java?_interface#string#Float#unsigned' ,1,1001);
insert into Questions values(8,'Java declaration statement must end with_Comma#A colon#A semicolon#Full stop' ,3,1001);
insert into Questions values(9,'Which one of the following is access keyword?_Public#Private#Protected#All of these' ,4,1001);
insert into Questions values(10,'The java visibility modifier is_Public#Private#Protected#All of these' ,1,1001);
insert into Questions values(11,'In an object oriented programming_Class create objects#Objects create classes#Classes use methods to communicate between them#None of these' ,1,1001);
insert into Questions values(12,'Which one of the following is not a wrap class?_Random#Byte#Double#Short' ,1,1001);
insert into Questions values(13,'The control flow which is not used in java is_Break#Continue#Goto#None of these' ,3,1001);
insert into Questions values(14,'For the method that does not return a value, the keyword used is_Boolean#Void#Null#Short' ,2,1001);
insert into Questions values(15,'Java’s coordinate system has the origin (0,0) in the_Lower left corner#Lower right corner#Upper right corner#Upper left corner' ,4,1001);

insert into exams (Type,Subject,TeacherID) values ('quiz','Math',3000);
insert into Questions values(16,'What is 1004 divided by 2?_52#502#520#5002' ,2,1002);
insert into Questions values(17,'How much is 90 – 19?_71#81#109#89' ,1,1002);
insert into Questions values(18,'A number is divisible by 3 if the sum of its digits is divisible by_2#3#1#5' ,2,1002);
insert into Questions values(19,'How many digits are there in 1000?_One digit#Two digits#Three digits#Four digits' ,4,1002);
insert into Questions values(20,'Complete the sequence 13, 16, ……, 22._17#18#19#20' ,3,1002);
alter table students ADD COLUMN question varchar(106) ;
alter table students ADD COLUMN answer varchar(106) ;