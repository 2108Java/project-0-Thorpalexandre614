create table user_data(
	
	pin varchar(4) primary key,
	username varchar(10) not null,
	pass varchar(20) not null,
	balance int,
	account_type varchar(10) default 'checking'
	--account_type checking, savings, employee
);

create table transaction_log(

	transaction_id serial primary key,
	transaction_type varchar(10) not null,
	--only one of the three can be not null for a given transaction
	transfer_origin varchar(4),
	transfer_target varchar(4), 
	--if the transaction is withdrawl or deposit, then origin and target are the same pin
	amount int not null,
	approved bool not null 

	);

create table user_log(
	
	log_id serial primary key,
	log_username varchar(10) references user_data(username),
	log_password varchar(20)
	--log_timestamp timestamp
);

drop table user_data;
drop table transaction_log;
truncate user_data;
truncate transaction_log;

insert into user_data values('1234', 'User1', 'password', 100, false);