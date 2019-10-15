
-- Truncate Table type_value_master;

insert into type_value_master(type_value_name, type_id, parent_type_value_id, is_active, created_by, created_date) 
						values('Bye', 1, 0, 1, 1, getdate());

insert into type_value_master(type_value_name, type_id, parent_type_value_id, is_active, created_by, created_date) 
						values('Sell', 1, 0, 1, 1, getdate());

insert into type_value_master(type_value_name, type_id, parent_type_value_id, is_active, created_by, created_date) 
						values('NSE', 2, 0, 1, 1, getdate());

insert into type_value_master(type_value_name, type_id, parent_type_value_id, is_active, created_by, created_date) 
						values('BSE', 2, 0, 1, 1, getdate());