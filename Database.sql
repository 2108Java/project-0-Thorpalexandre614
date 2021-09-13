create table user_data(
	
	pin int primary key,
	username varchar(10) not null,
	pass varchar(20) not null,
	balance int not null,
	is_employee bool not null
);

create table transaction_log(

	transaction_id serial primary key,
	transaction_type varchar(10) not null,
	--only one of the three can be not null for a given transaction
	transfer_origin int references user_data(pin),
	transfer_target int references user_data(pin), 
	--if the transaction is withdrawl or deposit, then origin and target are the same pin
	balance_origin int references user_data(pin),
	balance_target int references user_data(pin),
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

insert into user_data values(1234, 'User1', 'password', 100, false);