create table user_data(
	username varchar(10) primary key,
	pass int not null,
	is_employee bool not null,
	pin int primary key,
	balance int not null,
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
	approved bool references user_data(balance)
	);
