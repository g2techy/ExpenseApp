 Truncate Table user_details;

 SET IDENTITY_INSERT user_details ON
 GO

 Insert Into user_details (user_id, user_name, password, first_name, last_name, pwd_attempt_cnt, is_active)
			  Values (1, 'G2', 'Admin@123', 'G2', 'Chaudhari', 0, 1);

SET IDENTITY_INSERT user_details OFF
GO