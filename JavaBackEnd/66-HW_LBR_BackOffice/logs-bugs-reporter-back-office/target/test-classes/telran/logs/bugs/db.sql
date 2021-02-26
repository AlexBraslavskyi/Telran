DELETE bugs;
DELETE programmers;
TRUNCATE TABLE bugs RESTART IDENTITY;
insert into programmers (id,name,email) VALUES
(1, 'Moshe','moshe@gmail.com');

insert into bugs (id, description, date_open, date_close, status, seriousness, opening_method, programmer_id)
VALUES
(1,'desc','2000-01-01',null,'ASSIGNED','MINOR','MANUAL',1),
(2,'desc','2000-01-01',null,'ASSIGNED','CRITICAL','MANUAL',1),
(3,'desc','2000-01-01',null,'OPENED','BLOCKING','AUTOMATIC',null);