delete from marks;
delete from students;
delete from subjects;
insert into students (stid, name) values (1, 'Moshe'), (2, 'Sara');

insert into subjects (suid, subject) values (1, 'Java'), ( 2, 'React');
insert into marks (id, student_stid, subject_suid, mark) values (1, 1, 1, 100), 
(2, 1, 1, 50),(3, 1, 2, 80),(4, 2, 1, 75),(5, 2, 2, 60),(6, 2, 2, 100) ;