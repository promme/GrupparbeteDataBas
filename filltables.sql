-- Create an Admin
insert into user (User_id,Username, Password,IP_address,registry_date) values (1,'user1','12345','1',CURDATE());
insert into persondata (firstName,lastName, Email,Personnr) values ('Namn','Namn','namn@namn.com','1111111111111');
insert into userperson (User_id,Personnr) values (1, '1111111111111');
insert into admin(admin_id,address) values(1,'Gatan 1');
insert into admininfo(Mobile_nr,Admin_id) values(123456789,1);

-- Create an other account for the previous person
insert into user (User_id,Username, Password,IP_address,registry_date) values (2,'user2','12345','1',CURDATE());
insert into userperson values (2, '1111111111111');

-- Create a user with many phone numbers
insert into user (User_id,Username, Password,IP_address,registry_date) values (3,'user3','12345','1',CURDATE());
insert into persondata (firstName,lastName, Email,Personnr) values ('Namn3','Namn3','namn3@namn.com','1111111111113');
insert into userperson (User_id,Personnr) values (3, '1111111111113');
insert into admin(admin_id,address) values(3,'Gatan 3');
insert into admininfo(Mobile_nr,Admin_id) values(323456789,3);
insert into admininfo(Mobile_nr,Admin_id) values(333456789,3);
insert into admininfo(Mobile_nr,Admin_id) values(423456789,3);

-- Create a banned user
insert into user (User_id,Username, Password,IP_address,registry_date) values (4,'user4','12345','1',CURDATE());
insert into persondata (firstName,lastName, Email,Personnr) values ('Namn4','Nman4','namn4@namn.com','1111111111114');
insert into userperson (User_id,Personnr) values (4, '1111111111114');
insert into BannedUser(User_id,daysBanned) values(4,100);


insert into user (User_id,Username, Password,IP_address,registry_date) values (100,'tester','test','1',CURDATE());
insert into admin(admin_id,address) values(100,'Gatan 100');
