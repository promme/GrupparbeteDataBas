insert into user (User_id,Username, Password,Status,IP_address,registry_date) values (1,'user1','12345',0,'1',CURDATE());
insert into persondata (Name, Email,Personnr) values ('Namn','namn@namn.com','1111111111111');
insert into userperson values (1, '1111111111111');
insert into admin(admin_id,address) values(1,'Gatan 1');
insert into admininfo values(123456789,1);

insert into user (User_id,Username, Password,Status,IP_address,registry_date) values (2,'user2','12345',0,'1',CURDATE());
insert into userperson values (2, '1111111111111');


insert into user (User_id,Username, Password,Status,IP_address,registry_date) values (3,'user3','12345',0,'1',CURDATE());
insert into persondata (Name, Email,Personnr) values ('Namn3','namn3@namn.com','1111111111113');
insert into userperson values (3, '1111111111113');
insert into admin(admin_id,address) values(3,'Gatan 3');
insert into admininfo values(323456789,3);
insert into admininfo values(333456789,3);
insert into admininfo values(423456789,3);

select * from admininfo;