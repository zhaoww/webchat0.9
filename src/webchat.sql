use db_app;
create table sixin(id int(11)  primary key AUTO_INCREMENT, 
		fromCode varchar(20) not null,  toCode varchar(20),
		content varchar(4000) ,created_dt timestamp  default now());

