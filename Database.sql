create table user_data(
	
	pin varchar(4) not null,
	username varchar(10) not null,
	pass varchar(20) not null,
	balance int,
	account_type varchar(10) default 'checking',
	approved bool default false
	--account_type checking, savings, employee
);

create table transaction_log(

	transaction_id serial primary key,
	transaction_type varchar(20) not null,
	--transfer origin and target are the pins and account origin and target are the account types
	transfer_origin varchar(10),
	type_origin varchar(10),
	transfer_target varchar(10),
	type_target varchar(10),
	--if the transaction is withdrawl or deposit, then origin and target are the same pin
	amount int not null,
	approved bool default false 

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
