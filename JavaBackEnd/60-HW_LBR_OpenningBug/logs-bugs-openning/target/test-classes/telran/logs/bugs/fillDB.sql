DELETE FROM bugs;
DELETE FROM artifacts;
DELETE FROM programmers;

insert into programmers (id,name) VALUES
(1,'Moshe'),(2,'Sara'),(3,'Avraham');


insert into artifacts (artifact_id,programmer_id) VALUES
('class',1), ('authorization',3), ('authentication',2);
