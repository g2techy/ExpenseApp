
Truncate Table type_master;

Insert Into type_master(type_id, type_name, is_active, created_by, created_date) Values(1, 'TradeType', 1, 1, getDate());
Insert Into type_master(type_id, type_name, is_active, created_by, created_date) Values(2, 'Exchange', 1, 1, getDate());
Insert Into type_master(type_id, type_name, is_active, created_by, created_date) Values(3, 'ExpenseCategory', 1, 1, getDate());
Insert Into type_master(type_id, type_name, is_active, created_by, created_date) Values(4, 'ExpenseTag', 1, 1, getDate());
Insert Into type_master(type_id, type_name, is_active, created_by, created_date) Values(5, 'ExpenseFundSource', 1, 1, getDate());
