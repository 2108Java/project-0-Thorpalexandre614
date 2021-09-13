create table user_data(
	
	pin int primary key,
	username varchar(10) not null,
	pass int not null,
	balance int not null,
	is_employee bool not null,
);

create table transaction_log(

	transaction_id serial primary key,
	withdrawl_amount int,
	deposit_amount int,
	transfer_amount int,
	--only one of the three can be not null for a given transaction
	transfer_origin int references user_data(pin),
	transfer_target int references user_data(pin), 
	--if the transaction is withdrawl or deposit, then origin and target are the same pin
	balance_origin int references user_data(balance),
	balance_target int references user_data(balance),
	approved bool references user_data(balance)

	);

create table user_log(
	
	log_id serial primary key,
	log_username varchar(10) references user_data(username),
	--log_timestamp 
	
);