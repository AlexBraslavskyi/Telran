delete from marks;
delete from students;
delete from subjects;
insert into students (stid, name) values (1, 'Moshe');

insert into subjects (suid, subject) values (1, 'Java'), ( 2, 'React');
insert into marks (id, student_stid, subject_suid, mark) values (1, 1, 1, 100), 
(2, 1, 1, 100),(3, 1, 1, 70),(4, 1, 1, 70),(5, 1, 1, 60),(6, 1, 1, 100),(7, 1, 1, 65) ;